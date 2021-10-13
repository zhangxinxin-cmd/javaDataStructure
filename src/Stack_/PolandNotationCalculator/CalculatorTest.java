package Stack_.PolandNotationCalculator;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("12 * 3 - ( 3 + 12 * 23 / ( 2 + 4 ) ) + 13");
        System.out.println(calculator.getResult());
    }
}
