public class App {
    public static void main(String[] args) throws Exception {
        Calculator model = new Calculator();
        CalculatorView view = new CalculatorView();
        new CalculatorController(model, view);
    }
}
