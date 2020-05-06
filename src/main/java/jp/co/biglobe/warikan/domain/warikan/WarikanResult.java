package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class WarikanResult {
    @Getter
    private final PaymentAmountPerPaymentType paymentAmountPerPaymentType;
    @Getter
    private final Shortfall shortfall;
}
