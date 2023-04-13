package DataModels;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Double> monomials;

    public Polynomial() {
        monomials = new TreeMap<>(Collections.reverseOrder());
    }

    public void addMonom(Monomial m) {
        this.monomials.put(m.getPower(), m.getCoefficient());
    }

    public Map<Integer, Double> getMonomial() {
        return this.monomials;
    }

    @Override
    public boolean equals(Object obj) {
        Polynomial p = (Polynomial) obj;
        Iterator<Map.Entry<Integer, Double>> iteratorP1 = this.monomials.entrySet().iterator();
        Iterator<Map.Entry<Integer, Double>> iteratorP2 = p.getMonomial().entrySet().iterator();
        while(iteratorP1.hasNext()){
            Map.Entry<Integer, Double> entryP1 = iteratorP1.next();
            while(entryP1.getValue() == 0)
                entryP1 = iteratorP1.next();
            Map.Entry<Integer, Double> entryP2 = iteratorP2.next();
            while(entryP2.getValue() == 0)
                entryP2 = iteratorP2.next();
            if (!entryP1.getKey().equals(entryP2.getKey()))
                return false;
            if(!entryP1.getValue().equals(entryP2.getValue()))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, Double> entry : monomials.entrySet()) {
            String value = "", power = "^";
            DecimalFormat df;
            if (entry.getValue() % 1 != 0)
                df = new DecimalFormat("0.00");
            else df = new DecimalFormat("#.##");
            value += df.format(entry.getValue());
            power += entry.getKey();
            if (entry.getValue() == 1.0 && entry.getKey() != 0)
                value = "";
            if (entry.getValue() == -1.0 && entry.getKey() != 0)
                value = "-";
            if (entry.getKey() == 1)
                power = "";
            if (entry.getValue() > 0 && entry.getKey() != 0)
                result += " +" + value + "x" + power;
            else if (entry.getValue() < 0 && entry.getKey() != 0)
                result += " " + value + "x" + power;
            else if (entry.getValue() > 0)
                result += " +" + value;
            else if (entry.getValue() < 0)
                result += " " + value;
            else result += "";
        }
        if (result.equals(""))
            result += "0";
        return result;
    }

}
