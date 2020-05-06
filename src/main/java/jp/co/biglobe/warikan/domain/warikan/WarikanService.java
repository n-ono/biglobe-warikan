package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;

/**
 * 割り勘サービス
 */
@AllArgsConstructor
public class WarikanService {
    private final PaymentWeightRepository repository;

    public WarikanResult calculate(
            WarikanDrinkingPartyId partyId,
            BillingAmount billingAmount,
            ParticipantsOfHighPaymentType participantsOfHighPaymentType,
            ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType,
            ParticipantsOfLowPaymentType participantsOfLowPaymentType
    ) {
        PaymentWeights weights = repository.findById(partyId);
        PaymentWeight totalPaymentWeight = totalPaymentWeight(
                weights,
                participantsOfHighPaymentType,
                participantsOfMiddlePaymentType,
                participantsOfLowPaymentType
        );
        PaymentAmountPerPaymentType paymentAmountPerPaymentType = new PaymentAmountPerPaymentType(
                calculatePaymentAmountOfHighPaymentType(billingAmount, weights.getHighPaymentWeight(), participantsOfHighPaymentType, totalPaymentWeight),
                calculatePaymentAmountOfMiddlePaymentType(billingAmount, weights.getMiddlePaymentWeight(), participantsOfMiddlePaymentType, totalPaymentWeight),
                calculatePaymentAmountOfLowPaymentType(billingAmount, weights.getLowPaymentWeight(), participantsOfLowPaymentType, totalPaymentWeight)
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
            PaymentWeight highPaymentWeight,
            ParticipantsOfHighPaymentType participants,
            PaymentWeight totalPaymentWeight
    ) {
        if (participants.hasParticipants()) {
            return new PaymentAmountOfHighPaymentType(
                    billingAmount.multiply(highPaymentWeight).divide(totalPaymentWeight).getMoney()
            );
        } else {
            return new PaymentAmountOfHighPaymentType(Money.zero());
        }
    }

    private PaymentAmountOfMiddlePaymentType calculatePaymentAmountOfMiddlePaymentType(
            BillingAmount billingAmount,
            PaymentWeight middlePaymentWeight,
            ParticipantsOfMiddlePaymentType participants,
            PaymentWeight totalPaymentWeight
    ) {
        if (participants.hasParticipants()) {
            return new PaymentAmountOfMiddlePaymentType(
                    billingAmount.multiply(middlePaymentWeight).divide(totalPaymentWeight).getMoney()
            );
        } else {
            return new PaymentAmountOfMiddlePaymentType(Money.zero());
        }
    }

    private PaymentAmountOfLowPaymentType calculatePaymentAmountOfLowPaymentType(
            BillingAmount billingAmount,
            PaymentWeight lowPaymentWeight,
            ParticipantsOfLowPaymentType participants,
            PaymentWeight totalPaymentWeight
    ) {
        if (participants.hasParticipants()) {
            return new PaymentAmountOfLowPaymentType(
                    billingAmount.multiply(lowPaymentWeight).divide(totalPaymentWeight).getMoney()
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

    public void change(
            WarikanDrinkingPartyId warikanDrinkingPartyId,
            PaymentWeight highPaymentWeight,
            PaymentWeight middlePaymentWeight,
            PaymentWeight lowPaymentWeight
    ) {
        repository.change(
                warikanDrinkingPartyId,
                highPaymentWeight,
                middlePaymentWeight,
                lowPaymentWeight
        );
    }

    private PaymentWeight totalPaymentWeight(
            PaymentWeights weights,
            ParticipantsOfHighPaymentType participantsOfHighPaymentType,
            ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType,
            ParticipantsOfLowPaymentType participantsOfLowPaymentType
    ) {
        PaymentWeight high = weights.getHighPaymentWeight().multiply(participantsOfHighPaymentType.getValue());
        PaymentWeight middle = weights.getMiddlePaymentWeight().multiply(participantsOfMiddlePaymentType.getValue());
        PaymentWeight low = weights.getLowPaymentWeight().multiply(participantsOfLowPaymentType.getValue());

        return high.plus(middle).plus(low);
    }
}
