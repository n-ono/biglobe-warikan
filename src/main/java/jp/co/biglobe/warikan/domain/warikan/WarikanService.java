package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;

import static jp.co.biglobe.warikan.domain.warikan.PaymentType.*;

/**
 * 割り勘サービス
 */
@AllArgsConstructor
public class WarikanService {
    public WarikanResult calculate(
            BillingAmount billingAmount,
            ParticipantsOfHighPaymentType participantsOfHighPaymentType,
            ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType,
            ParticipantsOfLowPaymentType participantsOfLowPaymentType
    ) {
        PaymentWeight totalPaymentWeight = totalPaymentWeight(
                participantsOfHighPaymentType,
                participantsOfMiddlePaymentType,
                participantsOfLowPaymentType
        );
        PaymentAmountPerPaymentType paymentAmountPerPaymentType = new PaymentAmountPerPaymentType(
                calculatePaymentAmountOfHighPaymentType(billingAmount, participantsOfHighPaymentType, totalPaymentWeight),
                calculatePaymentAmountOfMiddlePaymentType(billingAmount, participantsOfMiddlePaymentType, totalPaymentWeight),
                calculatePaymentAmountOfLowPaymentType(billingAmount, participantsOfLowPaymentType, totalPaymentWeight)
        );
        Shortfall shortfall = calculateShortfall(
                billingAmount,
                participantsOfHighPaymentType,
                participantsOfMiddlePaymentType,
                participantsOfLowPaymentType,
                paymentAmountPerPaymentType
        );

        return new WarikanResult(paymentAmountPerPaymentType, shortfall);
    }

    private PaymentAmountOfHighPaymentType calculatePaymentAmountOfHighPaymentType(
            BillingAmount billingAmount,
            ParticipantsOfHighPaymentType participants,
            PaymentWeight totalPaymentWeight
    ) {
        if (participants.hasParticipants()) {
            return new PaymentAmountOfHighPaymentType(
                    billingAmount.multiply(HIGH).divide(totalPaymentWeight).getMoney()
            );
        } else {
            return new PaymentAmountOfHighPaymentType(Money.zero());
        }
    }

    private PaymentAmountOfMiddlePaymentType calculatePaymentAmountOfMiddlePaymentType(
            BillingAmount billingAmount,
            ParticipantsOfMiddlePaymentType participants,
            PaymentWeight totalPaymentWeight
    ) {
        if (participants.hasParticipants()) {
            return new PaymentAmountOfMiddlePaymentType(
                    billingAmount.multiply(MIDDLE).divide(totalPaymentWeight).getMoney()
            );
        } else {
            return new PaymentAmountOfMiddlePaymentType(Money.zero());
        }
    }

    private PaymentAmountOfLowPaymentType calculatePaymentAmountOfLowPaymentType(
            BillingAmount billingAmount,
            ParticipantsOfLowPaymentType participants,
            PaymentWeight totalPaymentWeight
    ) {
        if (participants.hasParticipants()) {
            return new PaymentAmountOfLowPaymentType(
                    billingAmount.multiply(LOW).divide(totalPaymentWeight).getMoney()
            );
        } else {
            return new PaymentAmountOfLowPaymentType(Money.zero());
        }
    }

    private Shortfall calculateShortfall(
            BillingAmount billingAmount,
            ParticipantsOfHighPaymentType participantsOfHighPaymentType,
            ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType,
            ParticipantsOfLowPaymentType participantsOfLowPaymentType,
            PaymentAmountPerPaymentType paymentAmountPerPaymentType
    ) {
        Money billingAmountMoney = billingAmount.getMoney();
        Money highPaymentTypeMoney = paymentAmountPerPaymentType
                .getMoneyOfHighPaymentType()
                .multiply(participantsOfHighPaymentType.getValue());
        Money middlePaymentTypeMoney = paymentAmountPerPaymentType
                .getMoneyOfMiddlePaymentType()
                .multiply(participantsOfMiddlePaymentType.getValue());
        Money lowPaymentTypeMoney = paymentAmountPerPaymentType
                .getMoneyOfLowPaymentType()
                .multiply(participantsOfLowPaymentType.getValue());

        return new Shortfall(
                billingAmountMoney.minus(highPaymentTypeMoney).minus(middlePaymentTypeMoney).minus(lowPaymentTypeMoney)
        );
    }

    private PaymentWeight totalPaymentWeight(
            ParticipantsOfHighPaymentType participantsOfHighPaymentType,
            ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType,
            ParticipantsOfLowPaymentType participantsOfLowPaymentType
    ) {
        PaymentWeight high = HIGH.getWeight().multiply(participantsOfHighPaymentType.getValue());
        PaymentWeight middle = MIDDLE.getWeight().multiply(participantsOfMiddlePaymentType.getValue());
        PaymentWeight low = LOW.getWeight().multiply(participantsOfLowPaymentType.getValue());

        return high.plus(middle).plus(low);
    }
}
