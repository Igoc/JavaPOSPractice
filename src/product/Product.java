package product;

import product.discount.policy.DiscountPolicy;

public class Product {
    private String name;
    private int price;
    private DiscountPolicy discountPolicy;

    public Product(String name, int price, DiscountPolicy discountPolicy) {
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        /* TODO: 할인 정책을 반영하도록 메서드 변경
         * - 적용 가능한 할인 정책이 있을 경우, 할인된 금액을 반환하도록 변경
         */

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}