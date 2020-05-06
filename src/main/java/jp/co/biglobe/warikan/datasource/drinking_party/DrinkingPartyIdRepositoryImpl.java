package jp.co.biglobe.warikan.datasource.drinking_party;

import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyId;
import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyIdRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DrinkingPartyIdRepositoryImpl implements DrinkingPartyIdRepository {
    public DrinkingPartyId allocate() {
        return new DrinkingPartyId(UUID.randomUUID().toString());
    }
}
