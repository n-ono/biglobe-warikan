package jp.co.biglobe.warikan.domain.warikan;

public interface PaymentWeightRepository {
    PaymentWeights findById(WarikanDrinkingPartyId partyId);

    void change(
            WarikanDrinkingPartyId partyId,
            PaymentWeight highPaymentWeight,
            PaymentWeight middlePaymentWeight,
            PaymentWeight lowPaymentWeight
    );
}
