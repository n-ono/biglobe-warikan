package jp.co.biglobe.warikan.api.payment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 支払い金額計算のリクエスト
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class PaymentCalculationRequest {

    /**
     * 請求金額
     */
    private int billingAmount;

    /**
     * 支払い区分が「多め」のメンバ数
     */
    private int largeMembers;

    /**
     * 支払い区分が「普通」のメンバ数
     */
    private int mediumMembers;

    /**
     * 支払い区分が「少なめ」のメンバ数
     */
    private int smallMembers;
}
