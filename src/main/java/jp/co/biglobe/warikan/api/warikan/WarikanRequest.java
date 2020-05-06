package jp.co.biglobe.warikan.api.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    private int billingAmount;

    /**
     * 支払い区分が「多め」の参加者数
     */
    private int highParticipants;

    /**
     * 支払い区分が「普通」の参加者数
     */
    private int middleParticipants;

    /**
     * 支払い区分が「少なめ」の参加者数
     */
    private int lowParticipants;
}
