package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;

import static jp.co.biglobe.warikan.domain.warikan.PaymentType.*;

/**
 * 割り勘サービス
 */
@AllArgsConstructor
public class WarikanService {
    private final BillingAmount billingAmount;
    private final ParticipantsOfHighPaymentType participantsOfHighPaymentType;
    private final ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType;
    private final ParticipantsOfLowPaymentType participantsOfLowPaymentType;

    public WarikanResult calculate() {
        PaymentAmountPerPaymentType paymentAmountPerPaymentType = new PaymentAmountPerPaymentType(
                calculatePaymentAmountOfHighPaymentType(),
                calculatePaymentAmountOfMiddlePaymentType(),
                calculatePaymentAmountOfLowPaymentType()
        );
        Shortfall shortfall = calculateShortfall(paymentAmountPerPaymentType);

        return new WarikanResult(paymentAmountPerPaymentType, shortfall);
    }

    private PaymentAmountOfHighPaymentType calculatePaymentAmountOfHighPaymentType() {
        if (participantsOfHighPaymentType.hasParticipants()) {
            return new PaymentAmountOfHighPaymentType(
                    billingAmount.multiply(HIGH).divide(totalPaymentWeight()).getMoney()
            );
        } else {
            return new PaymentAmountOfHighPaymentType(Money.zero());
        }
    }

    private PaymentAmountOfMiddlePaymentType calculatePaymentAmountOfMiddlePaymentType() {
        if (participantsOfMiddlePaymentType.hasParticipants()) {
            return new PaymentAmountOfMiddlePaymentType(
                    billingAmount.multiply(MIDDLE).divide(totalPaymentWeight()).getMoney()
            );
        } else {
            return new PaymentAmountOfMiddlePaymentType(Money.zero());
        }
    }

    private PaymentAmountOfLowPaymentType calculatePaymentAmountOfLowPaymentType() {
        if (participantsOfLowPaymentType.hasParticipants()) {
            return new PaymentAmountOfLowPaymentType(
                    billingAmount.multiply(LOW).divide(totalPaymentWeight()).getMoney()
            );
        } else {
            return new PaymentAmountOfLowPaymentType(Money.zero());
        }
    }

    private Shortfall calculateShortfall(PaymentAmountPerPaymentType paymentAmountPerPaymentType) {
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

    private PaymentWeight totalPaymentWeight() {
        PaymentWeight high = HIGH.getWeight().multiply(participantsOfHighPaymentType.getValue());
        PaymentWeight middle = MIDDLE.getWeight().multiply(participantsOfMiddlePaymentType.getValue());
        PaymentWeight low = LOW.getWeight().multiply(participantsOfLowPaymentType.getValue());

        return high.plus(middle).plus(low);
    }
}
