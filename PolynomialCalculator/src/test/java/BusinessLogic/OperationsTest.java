package BusinessLogic;

import DataModels.Polynomial;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

    private static Polynomial p1, p2, p3, p4, p5;
    private static Polynomial addExp, subExp, multiExp, derivExp, intExp, quotientExp, remainderExp, addExpFalse, subExpFalse;
    private static final Operations op = new Operations();

    @BeforeClass
    public static void setUpBeforeClass() {
        try {
            p1 = op.checkStringInput("+4x^5-3x^4+x^2-8x+1");
            p2 = op.checkStringInput("+3x^4-x^3+x^2+2x-1");
            p3 = op.checkStringInput("+x^3+3x^2+5");
            p4 = op.checkStringInput("+x^3-2x^2+6x-5");
            p5 = op.checkStringInput("+x^2-1");
            addExp = op.checkStringInput("+4x^5-x^3+2x^2-6x");
            subExp = op.checkStringInput("+4x^5-6x^4+x^3-10x+2");
            addExpFalse = op.checkStringInput("+2x^5-x^3-x^2-6x");
            subExpFalse = op.checkStringInput("+4x^5+2x^4+x^3-15x+2");
            multiExp = op.checkStringInput("+12x^9-13x^8+7x^7+8x^6-35x^5+15x^4-7x^3-16x^2+10x-1");
            derivExp = op.checkStringInput("+20x^4-12x^3+2x-8");
            intExp = op.checkStringInput("+0.25x^4+x^3+5x");
            quotientExp = op.checkStringInput("+x-2");
            remainderExp = op.checkStringInput("+7x-7");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void polyAddition() {
        Polynomial act = op.polyAddition(p1, p2);
        assertEquals(addExp, act);
    }

    @Test
    public void polySubtraction() {
        Polynomial act = op.polySubtraction(p1, p2);
        assertEquals(subExp, act);
    }

    @Test
    public void polyAdditionFalse() {
        Polynomial act = op.polyAddition(p1, p2);
        Assertions.assertNotEquals(addExpFalse, act);
    }

    @Test
    public void polySubtractionFalse() {
        Polynomial act = op.polySubtraction(p1, p2);
        Assertions.assertNotEquals(subExpFalse, act);
    }

    @Test
    public void polyMultiplication() {
        Polynomial act = op.polyMultiplication(p1, p2);
        assertEquals(multiExp, act);
    }

    @Test
    public void polyDerivative() {
        Polynomial act = op.polyDerivative(p1);
        assertEquals(derivExp, act);
    }

    @Test
    public void polyIntegrate() {
        Polynomial act = op.polyIntegrate(p3);
        assertEquals(intExp, act);
    }

    @Test
    public void polyDivide() throws Exception {
        List<Polynomial> act = op.polyDivide(p4, p5);
        assertEquals(quotientExp, act.get(0));
        assertEquals(remainderExp, act.get(1));
    }
}