package jp.co.biglobe.warikan.domain.warikan;

import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentWeight {
    @Getter
    private final int value;

    public PaymentWeight plus(PaymentWeight other) {
        return new PaymentWeight(value + other.value);
    }

    public PaymentWeight multiply(int n) {
        return new PaymentWeight(value * n);
    }
}
