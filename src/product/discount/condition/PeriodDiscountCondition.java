package product.discount.condition;

import payment.PaymentInformation;

import java.time.LocalDateTime;

public class PeriodDiscountCondition implements DiscountCondition {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public PeriodDiscountCondition(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isSatisfiedBy(PaymentInformation paymentInformation) {
        return startDate.isBefore(paymentInformation.getDate()) && endDate.isAfter(paymentInformation.getDate());
    }
}