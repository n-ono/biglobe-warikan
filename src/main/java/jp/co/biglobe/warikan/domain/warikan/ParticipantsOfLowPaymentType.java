package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 支払い区分が「少なめ」の参加者数
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ParticipantsOfLowPaymentType {
    @Getter
    private final int value;

    public boolean hasParticipants() {
        return 0 < value;
    }
}
