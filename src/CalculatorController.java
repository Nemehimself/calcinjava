import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;

public class CalculatorController {
    private Calculator model;
    private CalculatorView view;

    public CalculatorController(Calculator model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
        initializeButtons();
        view.show();
    }

    private void initializeButtons() {
        String[] buttonValues = view.getButtonValues();
        
        for (String buttonValue : buttonValues) {
            JButton button = view.createButton(buttonValue);
            button.addActionListener(new ButtonClickListener());
            view.addButton(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonValue = button.getText();

            if (Arrays.asList(view.getRightSymbols()).contains(buttonValue)) {
                handleOperatorButton(buttonValue);
            } else if (Arrays.asList(view.getTopSymbols()).contains(buttonValue)) {
                handleTopButton(buttonValue);
            } else {
                handleNumberOrDecimalButton(buttonValue);
            }
        }
    }

    private void handleOperatorButton(String buttonValue) {
        if (buttonValue.equals("=")) {
            if (model.getA() != null && model.getOperator() != null) {
                String result = model.calculate(
                    model.getOperator(),
                    model.getA(),
                    view.getDisplayText()
                );
                view.setDisplayText(result);
                model.clearAll();
            }
        } else if ("+-×÷".contains(buttonValue)) {
            if (model.getOperator() == null) {
                model.setA(view.getDisplayText());
                view.setDisplayText("0");
            }
            model.setOperator(buttonValue);
        }
    }

    private void handleTopButton(String buttonValue) {
        switch (buttonValue) {
            case "AC":
                model.clearAll();
                view.setDisplayText("0");
                break;
            case "+/-":
                String toggledValue = model.toggleSign(view.getDisplayText());
                view.setDisplayText(toggledValue);
                break;
            case "%":
                String percentValue = model.applyPercentage(view.getDisplayText());
                view.setDisplayText(percentValue);
                break;
        }
    }

    private void handleNumberOrDecimalButton(String buttonValue) {
        if (buttonValue.equals(".")) {
            if (!view.getDisplayText().contains(".")) {
                view.setDisplayText(view.getDisplayText() + buttonValue);
            }
        } else if ("0123456789".contains(buttonValue)) {
            if (view.getDisplayText().equals("0")) {
                view.setDisplayText(buttonValue);
            } else {
                view.setDisplayText(view.getDisplayText() + buttonValue);
            }
        }
    }
}