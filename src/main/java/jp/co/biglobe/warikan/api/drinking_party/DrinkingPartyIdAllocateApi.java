package jp.co.biglobe.warikan.api.drinking_party;

import jp.co.biglobe.warikan.service.drinking_party.DrinkingPartyIdAllocateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrinkingPartyIdAllocateApi {
    private final DrinkingPartyIdAllocateService service;

    @GetMapping("/drinking-party/allocate")
    public String allocate() {
        return service.allocate().getValue();
    }
}
