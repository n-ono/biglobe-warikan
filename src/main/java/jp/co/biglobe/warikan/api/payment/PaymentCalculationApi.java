package jp.co.biglobe.warikan.api.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentCalculationApi {

    @GetMapping("/payment/calculate")
    public PaymentCalculationResponse calculate(PaymentCalculationRequest request) {
        // todo 計算結果を返す
        return new PaymentCalculationResponse(7500, 6250, 0, 0);
    }
}
