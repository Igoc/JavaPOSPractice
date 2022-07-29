import payment.PaymentInformation;
import pos.Pos;
import product.Product;
import product.discount.condition.DiscountCondition;
import product.discount.condition.PeriodDiscountCondition;
import product.discount.policy.DiscountPolicy;
import product.discount.policy.FixDiscountPolicy;
import product.discount.policy.NoneDiscountPolicy;
import product.discount.policy.RateDiscountPolicy;
import storage.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pos pos = new Pos();
        Storage storage = new Storage();

        pos.addMoney(10000);

        boolean exitState = false;

        while (!exitState) {
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 판매");
            System.out.println("3. 시재 조회");
            System.out.println("4. 재고 조회");
            System.out.println("0. 종료");

            int input = scanner.nextInt();

            String name;
            int price;
            int number;

            /* DONE: 사용자가 선택한 메뉴에 대한 동작 구현
             * - 상품 추가
             *  - 사용자로부터 추가하려는 상품의 이름, 가격, 개수를 입력받기
             *  - 추가하려는 상품의 총 가격만큼 시재를 차감
             *  - 창고에 재고를 추가
             *
             * - 상품 판매
             *  - 사용자로부터 판매하려는 상품의 이름, 개수를 입력받기
             *  - 창고에서 상품을 꺼내오기
             *  - 판매 금액만큼 시재를 추가
             *
             * - 시재 조회
             *  - 현재 시재를 출력
             *
             * - 재고 조회
             *  - 현재 보유하고 있는 상품들의 재고를 출력
             *
             * - 종료
             *  - 프로그램 종료
             */

            switch (input) {
                case 1:
                    /* DONE: 할인 정책과 할인 조건을 입력받는 기능 추가
                     * - 사용자로부터 하나의 할인 정책과 여러 개의 할인 조건을 입력받기
                     */

                    try {
                        System.out.print("상품명 = ");
                        name = scanner.next();

                        System.out.print("가격 = ");
                        price = scanner.nextInt();

                        System.out.print("개수 = ");
                        number = scanner.nextInt();

                        DiscountPolicy discountPolicy = createDiscountPolicy();

                        pos.subtractMoney(price * number);

                        List<Product> products = new ArrayList<>();

                        for (int index = 0; index < number; index++) {
                            products.add(new Product(name, price, discountPolicy));
                        }

                        storage.addProducts(products);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    /* DONE: 결제 정보 생성 기능 추가
                     * - 현재 날짜와 시각을 갖는 결제 정보 생성
                     * - 결제 정보에 따라 할인된 금액을 적용
                     */

                    try {
                        System.out.print("상품명 = ");
                        name = scanner.next();

                        System.out.print("개수 = ");
                        number = scanner.nextInt();

                        List<Product> products = storage.getProduct(name, number);
                        PaymentInformation paymentInformation = new PaymentInformation(LocalDateTime.now());

                        for (Product product : products) {
                            pos.addMoney(product.getPrice(paymentInformation));
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    pos.printMoney();

                    break;

                case 4:
                    storage.printProductsStock();

                    break;

                case 0:
                    exitState = true;

                    break;
            }
        }

        scanner.close();
    }

    private static DiscountPolicy createDiscountPolicy() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. 고정 할인 정책 생성");
        System.out.println("2. 비율 할인 정책 생성");
        System.out.println("0. 할인 정책 미적용");

        int input = scanner.nextInt();

        List<DiscountCondition> discountConditions = new ArrayList<>();

        int amount;
        double rate;

        switch (input) {
            case 1:
                System.out.print("할인 금액 = ");
                amount = scanner.nextInt();

                while (true) {
                    DiscountCondition discountCondition = createDiscountCondition();

                    if (discountCondition == null) {
                        break;
                    }

                    discountConditions.add(discountCondition);
                }

                return new FixDiscountPolicy(discountConditions, amount);

            case 2:
                System.out.print("할인 비율 = ");
                rate = scanner.nextDouble();

                while (true) {
                    DiscountCondition discountCondition = createDiscountCondition();

                    if (discountCondition == null) {
                        break;
                    }

                    discountConditions.add(discountCondition);
                }

                return new RateDiscountPolicy(discountConditions, rate);
        }

        return new NoneDiscountPolicy();
    }

    private static DiscountCondition createDiscountCondition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. 기간 할인 조건 생성");
        System.out.println("0. 종료");

        int input = scanner.nextInt();

        String startDateString;
        String endDateString;

        LocalDateTime startDate;
        LocalDateTime endDate;

        switch (input) {
            case 1:
                scanner.nextLine();

                System.out.print("시작일 (YYYY-MM-DD HH:MM:SS) = ");
                startDateString = scanner.nextLine();

                System.out.print("종료일 (YYYY-MM-DD HH:MM:SS) = ");
                endDateString = scanner.nextLine();

                startDate = LocalDateTime.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                endDate = LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                return new PeriodDiscountCondition(startDate, endDate);
        }

        return null;
    }
}