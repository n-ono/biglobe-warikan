package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PaymentType {
    HIGH(new PaymentWeight(12)),
    MIDDLE(new PaymentWeight(10)),
    LOW(new PaymentWeight(8));

    @Getter
    private final PaymentWeight weight;
}
