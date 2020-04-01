package jp.co.biglobe.warikan.api.payment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class PaymentCalculationResponse {

    /**
     * 支払い区分が「多め」のメンバの支払い金額
     */
    @Getter
    private final int paymentOfLargeMember;

    /**
     * 支払い区分が「普通」のメンバの支払い金額
     */
    @Getter
    private final int paymentOfMediumMember;

    /**
     * 支払い区分が「少なめ」のメンバの支払い金額
     */
    @Getter
    private final int paymentOfSmallMember;

    /**
     * 不足金額
     */
    @Getter
    private final int balanceDue;
}
