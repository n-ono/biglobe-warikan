package jp.co.biglobe.warikan.domain.warikan;

import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyId;

public class DrinkingPartyIdTranslator {
    public static WarikanDrinkingPartyId translate(DrinkingPartyId drinkingPartyId) {
        return new WarikanDrinkingPartyId(drinkingPartyId.getValue());
    }
}
