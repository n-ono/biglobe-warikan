package jp.co.biglobe.warikan.domain.warikan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Money {
    @Getter
    private final int value;

    public static Money of(int value) {
        return new Money(value);
    }

    public static Money zero() {
        return Money.of(0);
    }

    public Money minus(Money other) {
        return Money.of(value - other.value);
    }

    public Money multiply(int n) {
        return Money.of(value * n);
    }

    public Money divide(int n) {
        return Money.of(value / n);
    }
}
