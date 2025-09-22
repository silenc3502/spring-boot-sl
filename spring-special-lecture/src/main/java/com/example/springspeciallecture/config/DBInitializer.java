package com.example.springspeciallecture.config;

import com.example.springspeciallecture.account.entity.*;
import com.example.springspeciallecture.account.repository.AccountLoginTypeRepository;
import com.example.springspeciallecture.account.repository.AccountRepository;
import com.example.springspeciallecture.account.repository.AccountRoleTypeRepository;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.account_profile.repository.AccountProfileRepository;
import com.example.springspeciallecture.game_chip.entity.GameChip;
import com.example.springspeciallecture.game_chip.entity.GameChipImage;
import com.example.springspeciallecture.game_chip.entity.GameChipImageType;
import com.example.springspeciallecture.game_chip.repository.GameChipImageRepository;
import com.example.springspeciallecture.game_chip.repository.GameChipRepository;
import com.example.springspeciallecture.orders.entity.OrderItem;
import com.example.springspeciallecture.orders.entity.OrderStatus;
import com.example.springspeciallecture.orders.entity.Orders;
import com.example.springspeciallecture.orders.repository.OrderItemRepository;
import com.example.springspeciallecture.orders.repository.OrdersRepository;
import com.example.springspeciallecture.payments.entity.PaymentMethod;
import com.example.springspeciallecture.payments.entity.PaymentStatus;
import com.example.springspeciallecture.payments.entity.Payments;
import com.example.springspeciallecture.payments.repository.PaymentsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
@Component
public class DBInitializer {

    private final AccountRoleTypeRepository accountRoleTypeRepository;
    private final AccountLoginTypeRepository accountLoginTypeRepository;
    private final AccountProfileRepository accountProfileRepository;

    private final GameChipRepository gameChipRepository;
    private final AccountRepository accountRepository;
    private final GameChipImageRepository gameChipImageRepository;

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;

    private final PaymentsRepository paymentsRepository;

    @PostConstruct
    private void init () {
        log.debug("initializer 시작!");

        initAccountRoleTypes();
        initAccountLoginTypes();

        initAccounts();
        initGameChips();
        initOrders();
        initPayments();

        log.debug("initializer 종료!");
    }

    private void initAccountRoleTypes() {
        try {
            final Set<RoleType> roles =
                    accountRoleTypeRepository.findAll().stream()
                            .map(AccountRoleType::getRoleType)
                            .collect(Collectors.toSet());

            for (RoleType type: RoleType.values()) {
                if (!roles.contains(type)) {
                    final AccountRoleType role = new AccountRoleType(type);
                    accountRoleTypeRepository.save(role);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void initAccountLoginTypes() {
        try {
            final Set<LoginType> loginTypesInDB =
                    accountLoginTypeRepository.findAll().stream()
                            .map(AccountLoginType::getLoginType)
                            .collect(Collectors.toSet());

            for (LoginType type : LoginType.values()) {
                if (!loginTypesInDB.contains(type)) {
                    final AccountLoginType loginType = new AccountLoginType(type);
                    accountLoginTypeRepository.save(loginType);
                }
            }
        } catch (Exception e) {
            log.error("LoginType 초기화 실패", e);
        }
    }

    private void initAccounts() {
        try {
            if (!accountRepository.findAll().isEmpty()) {
                log.debug("계정 이미 존재, 생성 스킵");
                return;
            }

            // Role, LoginType 가져오기 (기본값)
            AccountRoleType roleType = accountRoleTypeRepository.findByRoleType(RoleType.NORMAL)
                    .orElseThrow(() -> new IllegalStateException("USER RoleType 없음"));
            AccountLoginType loginType = accountLoginTypeRepository.findByLoginType(LoginType.KAKAO)
                    .orElseThrow(() -> new IllegalStateException("EMAIL LoginType 없음"));

            // 계정 생성
            Account account = new Account(roleType, loginType);
            accountRepository.save(account);

            // 프로필 생성
            AccountProfile profile = new AccountProfile(account, "testuser", "test@example.com");
            accountProfileRepository.save(profile);

            log.debug("테스트용 계정 + 프로필 생성 완료!");
        } catch (Exception e) {
            log.error("계정 초기화 실패", e);
        }
    }

    private void initGameChips() {
        try {
            if (!gameChipRepository.findAll().isEmpty()) {
                log.debug("GameChip 이미 존재, 생성 스킵");
                return;
            }

            List<Account> accounts = accountRepository.findAll();
            if (accounts.isEmpty()) {
                log.warn("계정이 없습니다. GameChip 생성 스킵");
                return;
            }

            Random random = new Random();
            IntStream.range(1, 11).forEach(i -> {
                Account account = accounts.get(random.nextInt(accounts.size()));
                GameChip chip = new GameChip(
                        "Chip " + i,
                        "Description " + i,
                        random.nextInt(5000) + 500,
                        account
                );
                gameChipRepository.save(chip);

                GameChipImage image = new GameChipImage(
                        ("dummyImageData" + i).getBytes(),
                        "image_" + i + ".jpg",
                        GameChipImageType.THUMBNAIL,
                        chip
                );
                gameChipImageRepository.save(image);
            });

            log.debug("GameChip + Image 초기화 완료!");
        } catch (Exception e) {
            log.error("GameChip 초기화 실패", e);
        }
    }

    private void initOrders() {
        try {
            if (!ordersRepository.findAll().isEmpty()) {
                log.debug("Orders 이미 존재, 생성 스킵");
                return;
            }

            List<Account> accounts = accountRepository.findAll();
            List<GameChip> chips = gameChipRepository.findAll();
            Random random = new Random();

            IntStream.range(0, 100).forEach(i -> {
                Account account = accounts.get(random.nextInt(accounts.size()));
                Orders order = new Orders(
                        account.getId(),
                        OrderStatus.PAID
                );
                ordersRepository.save(order);

                // 주문 아이템 1~3개 랜덤
                int itemCount = random.nextInt(3) + 1;
                IntStream.range(0, itemCount).forEach(j -> {
                    GameChip chip = chips.get(random.nextInt(chips.size()));
                    OrderItem item = new OrderItem(
                            order,
                            chip.getId(),
                            (long)(random.nextInt(5) + 1),
                            (long) chip.getPrice()
                    );
                    orderItemRepository.save(item);
                });
            });

            log.debug("Orders + OrderItem 초기화 완료!");
        } catch (Exception e) {
            log.error("Orders 초기화 실패", e);
        }
    }

    private void initPayments() {
        try {
            if (paymentsRepository.count() >= 100) {
                log.debug("Payments 100개 이상 존재, 생성 스킵");
                return;
            }

            List<Orders> orders = ordersRepository.findAll();
            Random random = new Random();

            IntStream.range(0, orders.size()).forEach(i -> {
                Orders order = orders.get(i);

                // 주문 아이템 총 금액 계산
                long totalAmount = orderItemRepository.findByOrders(order).stream()
                        .mapToLong(item -> item.getPrice() * item.getQuantity())
                        .sum();

                Payments payment = new Payments(
                        order,
                        PaymentMethod.CARD,
                        totalAmount,
                        PaymentStatus.SUCCESS,
                        "TX-" + System.currentTimeMillis()
                );
                paymentsRepository.save(payment);
            });

            log.debug("Payments 초기화 완료!");
        } catch (Exception e) {
            log.error("Payments 초기화 실패", e);
        }
    }
}

