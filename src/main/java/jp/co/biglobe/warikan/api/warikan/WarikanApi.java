package jp.co.biglobe.warikan.api.warikan;

import jp.co.biglobe.warikan.domain.warikan.PaymentWeightRepository;
import jp.co.biglobe.warikan.domain.warikan.WarikanResult;
import jp.co.biglobe.warikan.service.warikan.WarikanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WarikanApi {
    private final PaymentWeightRepository paymentWeightRepository;

    @GetMapping("/warikan/calculate")
    public WarikanResponse calculate(@Valid WarikanRequest request) {
        WarikanResult result = new WarikanService(paymentWeightRepository).calculate(
                request.getDrinkingPartyId(),
                request.getBillingAmount(),
                request.getParticipantsOfHighPaymentType(),
                request.getParticipantsOfMiddlePaymentType(),
                request.getParticipantsOfLowPaymentType()
        );

        return new WarikanResponse(
                result.getPaymentAmountPerPaymentType().getMoneyOfHighPaymentType().getValue(),
                result.getPaymentAmountPerPaymentType().getMoneyOfMiddlePaymentType().getValue(),
                result.getPaymentAmountPerPaymentType().getMoneyOfLowPaymentType().getValue(),
                result.getShortfall().getMoney().getValue()
        );
    }

    @PutMapping("/warikan/change")
    public void change(@Valid ChangeRequest request) {
        new WarikanService(paymentWeightRepository).changePaymentWeight(
                request.getDrinkingPartyId(),
                request.getHighPaymentWeight(),
                request.getMiddlePaymentWeight(),
                request.getLowPaymentWeight()
        );
    }
}
