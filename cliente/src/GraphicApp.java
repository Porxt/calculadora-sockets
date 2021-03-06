import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import util.CalculatorHistory;
import util.Expression;
import connection.Client;
import java.io.IOException;

public class GraphicApp extends JFrame implements ActionListener {

    private JTextField screen;
    private boolean isResult = false;
    private CalculatorHistory history;
    protected boolean isHistoryOpen = false;

    public GraphicApp() {
        super("Calculadora");

        history = new CalculatorHistory();

        setBounds(10, 10, 350, 325);
        initComponents(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents(GraphicApp self) {
        JPanel buttons = new JPanel(new GridLayout(5, 4));
        JButton[] buttonsNumber;
        JButton buttonDot;
        JButton buttonClear;
        JButton buttonSum;
        JButton buttonSub;
        JButton buttonMul;
        JButton buttonDiv;
        JButton buttonPow;
        JButton buttonMod;
        JButton buttonEqual;
        JButton buttonHist;

        // Setup number buttons
        buttonsNumber = new JButton[11];
        for(int i = 0; i < 10; i++) {
            buttonsNumber[i] = new JButton(String.valueOf(i));
            buttonsNumber[i].addActionListener(this);
        }
        buttonDot = new JButton(".");
        buttonDot.addActionListener(this);

        // Setup operation buttons
        buttonSum = new JButton(" + ");
        buttonSum.addActionListener(this);

        buttonSub = new JButton(" - ");
        buttonSub.addActionListener(this);

        buttonMul = new JButton(" * ");
        buttonMul.addActionListener(this);

        buttonDiv = new JButton(" / ");
        buttonDiv.addActionListener(this);

        buttonPow = new JButton(" pow ");
        buttonPow.addActionListener(this);

        buttonMod = new JButton(" mod ");
        buttonMod.addActionListener(this);

        buttonEqual = new JButton("=");
        buttonEqual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text;
                Expression expression;
                Client client;
                String result;

                if(!isHistoryOpen) {
                    text = screen.getText();
                    text = text.replaceAll("mod", "%");
                    text = text.replaceAll("pow", "**");
                    expression = Expression.build(text.replaceAll(" ", ""));
                    if(expression != null) {
                        try {
                            client = new Client();
                            result = client.sendOperation(expression);
                        } catch(IOException ex) {
                            result = ex.getMessage();
                        }
                        history.add(expression, result);
                    } else result = "Error de Sintaxis";
                    screen.setText(result);
                    isResult = true;
                }
            }
        });

        buttonClear = new JButton("CE");
        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!isHistoryOpen) {
                    screen.setText("");
                    isResult = false;
                }
            }
        });

        buttonHist = new JButton("Hist");
        buttonHist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new History(self, history);
                isHistoryOpen = true;
            }
        });

        // Add buttons to panel
        buttons.add(buttonHist);
        buttons.add(buttonPow);
        buttons.add(buttonMod);
        buttons.add(buttonClear);
        for(int button = 7; button <= 9; button++) {
            buttons.add(buttonsNumber[button]);
        }
        buttons.add(buttonDiv);
        for(int button = 4; button <= 6; button++) {
            buttons.add(buttonsNumber[button]);
        }
        buttons.add(buttonMul);
        for(int button = 1; button <= 3; button++) {
            buttons.add(buttonsNumber[button]);
        }
        buttons.add(buttonSub);
        buttons.add(buttonsNumber[0]);
        buttons.add(buttonDot);
        buttons.add(buttonEqual);
        buttons.add(buttonSum);

        // Setup screen
        screen = new JTextField();
        screen.setEditable(false);
        screen.setPreferredSize(new Dimension(500, 50));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setFont(screen.getFont().deriveFont(14f));

        // Add components to frame
        add(screen, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String text;
        JButton button;
        if(!isHistoryOpen) {
            if(isResult) {
                screen.setText("");
                text = "";
                isResult = false;
            } else text = screen.getText();
            button = (JButton) e.getSource();
            text += button.getText();
            screen.setText(text);
        }
    }
}
