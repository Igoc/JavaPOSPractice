import pos.Pos;
import product.Product;
import storage.Storage;

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
                    try {
                        System.out.print("Name = ");
                        name = scanner.next();

                        System.out.print("Price = ");
                        price = scanner.nextInt();

                        System.out.print("Number = ");
                        number = scanner.nextInt();

                        pos.subtractMoney(price * number);

                        List<Product> products = new ArrayList<>();

                        for (int index = 0; index < number; index++) {
                            products.add(new Product(name, price));
                        }

                        storage.addProducts(products);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    try {
                        System.out.print("Name = ");
                        name = scanner.next();

                        System.out.print("Number = ");
                        number = scanner.nextInt();

                        List<Product> products = storage.getProduct(name, number);

                        for (Product product : products) {
                            pos.addMoney(product.getPrice());
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
}