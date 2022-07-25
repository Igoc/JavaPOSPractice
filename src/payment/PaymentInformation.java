package payment;

import java.time.LocalDateTime;

public class PaymentInformation {
    private LocalDateTime date;

    public PaymentInformation(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}