import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import util.CalculatorHistory;

public class History extends JFrame {
    public History(GraphicApp parent, CalculatorHistory history) {
        super("Historial de Operaciones");
        
        setBounds(10, 10, 250, 325);
        add(new JList<String>(history.getList()), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                parent.isHistoryOpen = false;
            }
        });
        setResizable(false);
        setVisible(true);
    }
}
