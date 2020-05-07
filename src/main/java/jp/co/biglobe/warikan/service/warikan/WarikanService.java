package jp.co.biglobe.warikan.service.warikan;

import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyId;
import jp.co.biglobe.warikan.domain.warikan.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WarikanService {
    private final PaymentWeightRepository repository;

    public WarikanResult calculate(
            DrinkingPartyId drinkingPartyId,
            BillingAmount billingAmount,
            ParticipantsOfHighPaymentType participantsOfHighPaymentType,
            ParticipantsOfMiddlePaymentType participantsOfMiddlePaymentType,
            ParticipantsOfLowPaymentType participantsOfLowPaymentType
    ) {
        WarikanDrinkingPartyId warikanDrinkingPartyId = DrinkingPartyIdTranslator.translate(drinkingPartyId);

        return new jp.co.biglobe.warikan.domain.warikan.WarikanService(repository).calculate(
                warikanDrinkingPartyId,
                billingAmount,
                participantsOfHighPaymentType,
                participantsOfMiddlePaymentType,
                participantsOfLowPaymentType
        );
    }

    public void changePaymentWeight(
            DrinkingPartyId drinkingPartyId,
            PaymentWeight paymentWeightOfHighPaymentType,
            PaymentWeight paymentWeightOfMiddlePaymentType,
            PaymentWeight paymentWeightOfLowPaymentType
    ) {
        WarikanDrinkingPartyId warikanDrinkingPartyId = DrinkingPartyIdTranslator.translate(drinkingPartyId);

        new jp.co.biglobe.warikan.domain.warikan.WarikanService(repository).change(
                warikanDrinkingPartyId,
                paymentWeightOfHighPaymentType,
                paymentWeightOfMiddlePaymentType,
                paymentWeightOfLowPaymentType
        );
    }
}
