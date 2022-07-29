package product.discount.policy;

import product.discount.condition.DiscountCondition;

import java.util.List;

public class RateDiscountPolicy extends DiscountPolicy {
    private final double rate;

    public RateDiscountPolicy(List<DiscountCondition> discountConditions, double rate) {
        super(discountConditions);

        this.rate = rate;
    }

    @Override
    protected int getDiscountAmount(int price) {
        return (int) (price * rate);
    }
}