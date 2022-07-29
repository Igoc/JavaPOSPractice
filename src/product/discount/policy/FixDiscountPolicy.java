package product.discount.policy;

import product.discount.condition.DiscountCondition;

import java.util.List;

public class FixDiscountPolicy extends DiscountPolicy {
    private final int amount;

    public FixDiscountPolicy(List<DiscountCondition> discountConditions, int amount) {
        super(discountConditions);

        this.amount = amount;
    }

    @Override
    protected int getDiscountAmount(int price) {
        return Math.min(price, amount);
    }
}