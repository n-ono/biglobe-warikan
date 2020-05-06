package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentWeights {
    private final int highPaymentWeight;
    private final int middlePaymentWeight;
    private final int lowPaymentWeight;

    public PaymentWeight getHighPaymentWeight() {
        return PaymentWeight.of(highPaymentWeight);
    }

    public PaymentWeight getMiddlePaymentWeight() {
        return PaymentWeight.of(middlePaymentWeight);
    }

    public PaymentWeight getLowPaymentWeight() {
        return PaymentWeight.of(lowPaymentWeight);
    }
}
