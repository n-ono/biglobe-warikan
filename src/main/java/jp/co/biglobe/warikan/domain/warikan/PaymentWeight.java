package jp.co.biglobe.warikan.domain.warikan;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PaymentWeight {
    @Getter
    private final int value;

    public static PaymentWeight of(int n) {
        return new PaymentWeight(n);
    }

    public PaymentWeight plus(PaymentWeight other) {
        return new PaymentWeight(value + other.value);
    }

    public PaymentWeight multiply(int n) {
        return new PaymentWeight(value * n);
    }
}
