package jp.co.biglobe.warikan.api.warikan;

import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyId;
import jp.co.biglobe.warikan.domain.warikan.PaymentWeight;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.Min;

/**
 * 支払い割合の変更リクエスト
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ChangeRequest {

    /**
     * 飲み会 ID
     */
    @NonNull
    private final String drinkingPartyId;

    /**
     * 支払い区分が「多め」の人の支払い割合
     */
    @Min(0)
    private final int highPaymentWeight;

    /**
     * 支払い区分が「普通」の人の支払い割合
     */
    @Min(0)
    private final int middlePaymentWeight;

    /**
     * 支払い区分が「少なめ」の人の支払い割合
     */
    @Min(0)
    private final int lowPaymentWeight;

    public DrinkingPartyId getDrinkingPartyId() {
        return new DrinkingPartyId(drinkingPartyId);
    }

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
