package jp.co.biglobe.warikan.domain.warikan

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WarikanServiceTest extends Specification {
    def "20000 円を多め 1 人と普通 2 人で割り勘する"() {
        setup:
        def service = new WarikanService(
                new BillingAmount(Money.of(20000)),
                new ParticipantsOfHighPaymentType(1),
                new ParticipantsOfMiddlePaymentType(2),
                new ParticipantsOfLowPaymentType(0)
        )
        def expected = new WarikanResult(
                new PaymentAmountPerPaymentType(
                        new PaymentAmountOfHighPaymentType(Money.of(7500)),
                        new PaymentAmountOfMiddlePaymentType(Money.of(6250)),
                        new PaymentAmountOfLowPaymentType(Money.zero())
                ),
                new Shortfall(Money.of(0))
        )

        when:
        def actual = service.calculate()

        then:
        assert actual == expected
    }

    def "20000 円を少なめ 3 人で割り勘する"() {
        setup:
        def service = new WarikanService(
                new BillingAmount(Money.of(20000)),
                new ParticipantsOfHighPaymentType(0),
                new ParticipantsOfMiddlePaymentType(0),
                new ParticipantsOfLowPaymentType(3)
        )
        def expected = new WarikanResult(
                new PaymentAmountPerPaymentType(
                        new PaymentAmountOfHighPaymentType(Money.zero()),
                        new PaymentAmountOfMiddlePaymentType(Money.zero()),
                        new PaymentAmountOfLowPaymentType(Money.of(6666))
                ),
                new Shortfall(Money.of(2))
        )

        when:
        def actual = service.calculate()

        then:
        assert actual == expected
    }
}
