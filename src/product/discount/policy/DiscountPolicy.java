package product.discount.policy;

import payment.PaymentInformation;
import product.discount.condition.DiscountCondition;

import java.util.List;

/* TODO: DiscountPolicy를 상속받는 서브 클래스 구현
 * - 할인을 적용하지 않는 NoneDiscountPolicy 구현
 * - 고정 금액만큼 할인해주는 FixDiscountPolicy 구현
 * - 일정 비율만큼 할인해주는 RateDiscountPolicy 구현
 */
public abstract class DiscountPolicy {
    private final List<DiscountCondition> discountConditions;

    public DiscountPolicy(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public int calculateDiscountAmount(PaymentInformation paymentInformation, int price) {
        for (DiscountCondition discountCondition : discountConditions) {
            if (discountCondition.isSatisfiedBy(paymentInformation)) {
                return getDiscountAmount(price);
            }
        }

        return 0;
    }

    protected abstract int getDiscountAmount(int price);
}