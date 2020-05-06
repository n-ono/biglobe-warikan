package jp.co.biglobe.warikan.api.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class WarikanResponse {

    /**
     * 支払い区分が「多め」の参加者の支払い金額
     */
    @Getter
    private final int paymentAmountOfHighParticipants;

    /**
     * 支払い区分が「普通」の参加者の支払い金額
     */
    @Getter
    private final int paymentAmountOfMiddleParticipants;

    /**
     * 支払い区分が「少なめ」の参加者の支払い金額
     */
    @Getter
    private final int paymentAmountOfLowParticipants;

    /**
     * 不足金額
     */
    @Getter
    private final int shortfall;
}
