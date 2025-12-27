import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Arrays;

public class CalculatorView {
    private static final int BOARD_WIDTH = 360;
    private static final int BOARD_HEIGHT = 540;

    private static final Color CUSTOM_LIGHT_GRAY = new Color(212, 212, 210);
    private static final Color CUSTOM_DARK_GRAY = new Color(80, 80, 80);
    private static final Color CUSTOM_BLACK = new Color(28, 28, 28);
    private static final Color CUSTOM_ORANGE = new Color(255, 149, 0);

    private static final String[] BUTTON_VALUES = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    private static final String[] RIGHT_SYMBOLS = {"÷", "×", "-", "+", "="};
    private static final String[] TOP_SYMBOLS = {"AC", "+/-", "%"};

    private JFrame frame;
    private JLabel displayLabel;
    private JPanel buttonsPanel;

    public CalculatorView() {
        initializeFrame();
        initializeDisplay();
        initializeButtonsPanel();
    }

    private void initializeFrame() {
        frame = new JFrame("Calculator");
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void initializeDisplay() {
        displayLabel = new JLabel("0");
        displayLabel.setBackground(CUSTOM_BLACK);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setOpaque(true);

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
    }

    private void initializeButtonsPanel() {
        buttonsPanel = new JPanel(new GridLayout(5, 4));
        buttonsPanel.setBackground(CUSTOM_BLACK);
        frame.add(buttonsPanel);
    }

    public JButton createButton(String buttonValue) {
        JButton button = new JButton(buttonValue);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setFocusable(false);
        button.setOpaque(true);
        button.setBorder(new LineBorder(CUSTOM_BLACK));

        if (Arrays.asList(TOP_SYMBOLS).contains(buttonValue)) {
            button.setBackground(CUSTOM_LIGHT_GRAY);
            button.setForeground(CUSTOM_BLACK);
        } else if (Arrays.asList(RIGHT_SYMBOLS).contains(buttonValue)) {
            button.setBackground(CUSTOM_ORANGE);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(CUSTOM_DARK_GRAY);
            button.setForeground(Color.WHITE);
        }

        return button;
    }

    public void addButton(JButton button) {
        buttonsPanel.add(button);
    }

    public String getDisplayText() {
        return displayLabel.getText();
    }

    public void setDisplayText(String text) {
        displayLabel.setText(text);
    }

    public void show() {
        frame.setVisible(true);
    }

    public String[] getButtonValues() {
        return BUTTON_VALUES;
    }

    public String[] getRightSymbols() {
        return RIGHT_SYMBOLS;
    }

    public String[] getTopSymbols() {
        return TOP_SYMBOLS;
    }
}