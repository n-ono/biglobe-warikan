package jp.co.biglobe.warikan.api.warikan;

import jp.co.biglobe.warikan.domain.warikan.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;

/**
 * 割り勘計算のリクエスト
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class WarikanRequest {

    /**
     * 請求金額
     */
    @Min(0)
    private final int billingAmount;

    /**
     * 支払い区分が「多め」の参加者数
     */
    @Min(0)
    private final int highParticipants;

    /**
     * 支払い区分が「普通」の参加者数
     */
    @Min(0)
    private final int middleParticipants;

    /**
     * 支払い区分が「少なめ」の参加者数
     */
    @Min(0)
    private final int lowParticipants;

    public BillingAmount getBillingAmount() {
        return new BillingAmount(Money.of(billingAmount));
    }

    public ParticipantsOfHighPaymentType getParticipantsOfHighPaymentType() {
        return new ParticipantsOfHighPaymentType(highParticipants);
    }

    public ParticipantsOfMiddlePaymentType getParticipantsOfMiddlePaymentType() {
        return new ParticipantsOfMiddlePaymentType(middleParticipants);
    }

    public ParticipantsOfLowPaymentType getParticipantsOfLowPaymentType() {
        return new ParticipantsOfLowPaymentType(lowParticipants);
    }
}
