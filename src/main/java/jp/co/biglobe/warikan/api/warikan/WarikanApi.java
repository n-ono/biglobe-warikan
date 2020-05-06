package jp.co.biglobe.warikan.api.warikan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarikanApi {

    @GetMapping("/warikan/calculate")
    public WarikanResponse calculate(WarikanRequest request) {
        // todo 計算結果を返す
        return new WarikanResponse(7500, 6250, 0, 0);
    }
}
