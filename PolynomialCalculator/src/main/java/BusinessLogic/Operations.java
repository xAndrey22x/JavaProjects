package BusinessLogic;

import DataModels.Monomial;
import DataModels.Polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public Polynomial checkStringInput(String input) throws Exception {
        if (input.length() <= 0)
            throw new Exception("You need to enter a polynom in the TextField");
        Polynomial p = new Polynomial();
        Pattern pattern = Pattern.compile("([+-]\\d*\\.?\\d*)[x]\\^(\\d+)|([+-]\\d*\\.?\\d*)[x]|([+-]\\d*\\.?\\d*|0)");
        Matcher matcher = pattern.matcher(input);
        String checker = "";
        while (matcher.find()) {
            Monomial m = null;
            if (matcher.group(1) != null && matcher.group(2) != null) {
                checker += matcher.group(1) + "x^" + matcher.group(2);
                m = matcherConversion(matcher.group(1), matcher.group(2));
            } else if (matcher.group(1) == null && matcher.group(2) != null) {
                checker += "x^" + matcher.group(2);
                m = matcherConversion("1.0", matcher.group(2));
            } else if (matcher.group(3) != null) {
                checker += matcher.group(3) + "x";
                m = matcherConversion(matcher.group(3), "1");
            } else if (matcher.group(4) != null) {
                checker += matcher.group(4);
                m = matcherConversion(matcher.group(4), "0");
            }
            if (!p.getMonomial().containsKey(m.getPower()))
                p.addMonom(m);
            else
                p.addMonom(new Monomial(m.getCoefficient() + p.getMonomial().get(m.getPower()), m.getPower()));
        }
        if (!input.equals(checker))
            throw new Exception("Input invalid :" + input + "\n Insert an input following +2x^5+4x^3-7x^2+3x+2");
        else return p;
    }

    public Monomial matcherConversion(String coefficient, String power) throws Exception {
        Monomial m;
        Double coeff = 0.0;
        Integer pow;
        try {
            coeff = Double.parseDouble(coefficient);
        } catch (NumberFormatException ex) {
            if (coefficient.equals("+") && !power.equals("0"))
                coeff = 1.0;
            else if (!power.equals("0"))
                coeff = -1.0;
            else throw new Exception("Input invalid, too many: " + coefficient + "\n Insert an input following +2x^5+4x^3-7x^2+3x+2");
        }
        try {
            pow = Integer.parseInt(power);
        } catch (NumberFormatException ex) {
            pow = 1;
        }
        m = new Monomial(coeff, pow);
        return m;
    }

    public Polynomial polyAddition(Polynomial p1, Polynomial p2) {
        Polynomial p3 = new Polynomial();
        p3.getMonomial().putAll(p1.getMonomial());
        for (Map.Entry<Integer, Double> entryP2 : p2.getMonomial().entrySet()) {
            if (p3.getMonomial().containsKey(entryP2.getKey()))
                p3.addMonom(new Monomial((entryP2.getValue() + p3.getMonomial().get(entryP2.getKey())), entryP2.getKey()));
            else p3.addMonom(new Monomial(entryP2.getValue(), entryP2.getKey()));
        }
        return p3;
    }

    public Polynomial polySubtraction(Polynomial p1, Polynomial p2) {
        Polynomial p3 = new Polynomial();
        p3.getMonomial().putAll(p1.getMonomial());
        for (Map.Entry<Integer, Double> entryP2 : p2.getMonomial().entrySet()) {
            if (p3.getMonomial().containsKey(entryP2.getKey()))
                p3.addMonom(new Monomial((p3.getMonomial().get(entryP2.getKey()) - entryP2.getValue()), entryP2.getKey()));
            else p3.addMonom(new Monomial(-entryP2.getValue(), entryP2.getKey()));
        }
        return p3;
    }

    public Monomial monoMulti(Monomial m1, Monomial m2) {
        return new Monomial(m1.getCoefficient() * m2.getCoefficient(), m1.getPower() + m2.getPower());
    }

    public Monomial monomAddToPoly(Monomial m1, Polynomial p) {
        Monomial m2 = null;
        for (Map.Entry<Integer, Double> entryP : p.getMonomial().entrySet()) {
            if (entryP.getKey().equals(m1.getPower()))
                m2 = new Monomial(m1.getCoefficient() + entryP.getValue(), m1.getPower());
        }
        return m2;
    }

    public Polynomial polyMultiplication(Polynomial p1, Polynomial p2) {
        Polynomial p3 = new Polynomial();
        Monomial m1, m2, m3;
        for (Map.Entry<Integer, Double> entryP1 : p1.getMonomial().entrySet()) {
            m1 = new Monomial(entryP1.getValue(), entryP1.getKey());
            for (Map.Entry<Integer, Double> entryP2 : p2.getMonomial().entrySet()) {
                m2 = new Monomial(entryP2.getValue(), entryP2.getKey());
                m3 = monoMulti(m1, m2);
                if (monomAddToPoly(m3, p3) != null)
                    p3.addMonom(monomAddToPoly(m3, p3));
                else p3.addMonom(m3);
            }
        }
        return p3;
    }

    public Polynomial polyDerivative(Polynomial p1) {
        Polynomial p2 = new Polynomial();
        Monomial m1;
        for (Map.Entry<Integer, Double> entryP1 : p1.getMonomial().entrySet()) {
            m1 = new Monomial(entryP1.getValue() * entryP1.getKey(), entryP1.getKey() - 1);
            p2.addMonom(m1);
        }
        return p2;
    }

    public Polynomial polyIntegrate(Polynomial p1) {
        Polynomial p2 = new Polynomial();
        Monomial m1;
        for (Map.Entry<Integer, Double> entryP1 : p1.getMonomial().entrySet()) {
            m1 = new Monomial((entryP1.getValue() / (entryP1.getKey() + 1)), (entryP1.getKey() + 1));
            p2.addMonom(m1);
        }
        return p2;
    }

    public int degreePoly(Polynomial p1) {
        for (Map.Entry<Integer, Double> entry : p1.getMonomial().entrySet()) {
            if (entry.getValue() != 0) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public Monomial firstMonom(Polynomial p1) {
        for (Map.Entry<Integer, Double> entry : p1.getMonomial().entrySet()) {
            if (entry.getValue() != 0) {
                return new Monomial(entry.getValue(), entry.getKey());
            }
        }
        return new Monomial(0, 0);
    }

    public List<Polynomial> polyDivide(Polynomial p1, Polynomial p2) throws Exception {
        Polynomial quotient = new Polynomial(), remainder = new Polynomial();
        List<Polynomial> qr = new ArrayList<>(2);
        if (degreePoly(p1) < degreePoly(p2) && firstMonom(p1).getCoefficient() != 0)
            throw new Exception("The first polynom needs to be with the bigger degree!");
        if (firstMonom(p2).getCoefficient() == 0)
            throw new Exception("Cannot divide by 0!");
        while (degreePoly(p1) >= degreePoly(p2)) {
            Monomial m1 = firstMonom(p1);
            Monomial m2 = firstMonom(p2);
            Monomial m3;
            if (m2.getCoefficient() != 0 && m1.getPower() - m2.getPower() >= 0)
                m3 = new Monomial(m1.getCoefficient() / m2.getCoefficient(),
                        m1.getPower() - m2.getPower());
            else break;
            Polynomial aux = new Polynomial();
            aux.addMonom(m3);
            quotient.addMonom(m3);
            remainder = polySubtraction(p1, polyMultiplication(aux, p2));
            p1.getMonomial().putAll(remainder.getMonomial());
        }
        qr.add(quotient);
        qr.add(remainder);
        return qr;
    }
}
