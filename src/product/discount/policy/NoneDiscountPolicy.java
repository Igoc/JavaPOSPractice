package product.discount.policy;

import java.util.ArrayList;

public class NoneDiscountPolicy extends DiscountPolicy {
    public NoneDiscountPolicy() {
        super(new ArrayList<>());
    }

    @Override
    protected int getDiscountAmount(int price) {
        return 0;
    }
}