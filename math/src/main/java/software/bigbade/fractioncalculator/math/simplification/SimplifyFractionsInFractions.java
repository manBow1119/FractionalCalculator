package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;

public class SimplifyFractionsInFractions implements ISimplifier {
    /**
     * @return true if a fraction is in the numerator/denominator of a fraction
     */
    @Override
    public boolean matches(IValue value) {
        return value instanceof FractionValue && (((FractionValue) value).getNumerator() instanceof FractionValue ||
                ((FractionValue) value).getDenominator() instanceof FractionValue);
    }

    /**
     * @return Fraction with only numbers in the numerator and denominator
     */
    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue = (FractionValue) value;
        IValue numerator = fractionValue.getNumerator();
        IValue denominator = fractionValue.getDenominator();
        if (numerator instanceof FractionValue) {
            consumer.printText("Simplify " + numerator.toString() + " by multiplying the denominator"
                    + " to the outer fraction's denominator");
            denominator = denominator.multiply(((FractionValue) numerator).getDenominator());
            numerator = ((FractionValue) numerator).getNumerator();
        }
        if (denominator instanceof FractionValue) {
            consumer.printText("Simplify " + denominator.toString() + " by multiplying the denominator"
                    + " to the outer fraction's denominator");
            numerator = numerator.multiply(((FractionValue) denominator).getDenominator());
            denominator = ((FractionValue) denominator).getNumerator();
        }
        return new FractionValue(numerator, denominator);
    }
}
