import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    private String A = "0";
    private String operator = null;
    private String B = null;

    public String calculate(String operatorToUse, String valueA, String valueB) {
        BigDecimal numA = new BigDecimal(valueA);
        BigDecimal numB = new BigDecimal(valueB);
        BigDecimal result = BigDecimal.ZERO;

        switch (operatorToUse) {
            case "+":
                result = numA.add(numB);
                break;
            case "-":
                result = numA.subtract(numB);
                break;
            case "×":
                result = numA.multiply(numB);
                break;
            case "÷":
                if (numB.compareTo(BigDecimal.ZERO) == 0) {
                    return "Error";
                }
                result = numA.divide(numB, 10, RoundingMode.HALF_UP);
                break;
        }

        return formatResult(result.doubleValue());
    }

    public String applyPercentage(String value) {
        double numDisplay = Double.parseDouble(value);
        numDisplay /= 100;
        return formatResult(numDisplay);
    }

    public String toggleSign(String value) {
        double numDisplay = Double.parseDouble(value);
        numDisplay *= -1;
        return formatResult(numDisplay);
    }

    private String formatResult(double numDisplay) {
        if (numDisplay % 1 == 0 && Math.abs(numDisplay) < 1e10) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }
}