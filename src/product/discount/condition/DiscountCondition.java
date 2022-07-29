package product.discount.condition;

import payment.PaymentInformation;

/* DONE: DiscountCondition을 상속받는 서브 클래스 구현
 * - 특정 기간에만 할인 조건을 만족하는 PeriodDiscountCondition 구현
 */
public interface DiscountCondition {
    boolean isSatisfiedBy(PaymentInformation paymentInformation);
}