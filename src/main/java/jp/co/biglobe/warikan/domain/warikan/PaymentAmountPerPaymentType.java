package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentAmountPerPaymentType {
    private final PaymentAmountOfHighPaymentType paymentAmountOfHighPaymentType;
    private final PaymentAmountOfMiddlePaymentType paymentAmountOfMiddlePaymentType;
    private final PaymentAmountOfLowPaymentType paymentAmountOfLowPaymentType;

    public Money getMoneyOfHighPaymentType() {
        return paymentAmountOfHighPaymentType.getMoney();
    }

    public Money getMoneyOfMiddlePaymentType() {
        return paymentAmountOfMiddlePaymentType.getMoney();
    }

    public Money getMoneyOfLowPaymentType() {
        return paymentAmountOfLowPaymentType.getMoney();
    }
}
