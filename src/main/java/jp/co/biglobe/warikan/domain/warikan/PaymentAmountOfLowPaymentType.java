package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 支払い区分が「少なめ」の人の支払い金額
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentAmountOfLowPaymentType {
    @Getter
    private final Money money;
}
