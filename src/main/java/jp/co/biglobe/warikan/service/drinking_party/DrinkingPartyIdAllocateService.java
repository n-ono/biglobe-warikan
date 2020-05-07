package jp.co.biglobe.warikan.service.drinking_party;

import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyId;
import jp.co.biglobe.warikan.domain.drinking_party.DrinkingPartyIdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DrinkingPartyIdAllocateService {
    private final DrinkingPartyIdRepository repository;

    public DrinkingPartyId allocate() {
        return repository.allocate();
    }
}
