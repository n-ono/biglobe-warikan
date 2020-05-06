package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 請求金額
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BillingAmount {
    @Getter
    private final Money money;

    public BillingAmount multiply(PaymentWeight paymentWeight) {
        return new BillingAmount(money.multiply(paymentWeight.getValue()));
    }

    public BillingAmount divide(PaymentWeight paymentWeight) {
        return new BillingAmount(money.divide(paymentWeight.getValue()));
    }
}
