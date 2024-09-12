import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class SimpleTextEditor {

    private void createAndShowGUI() {
     
        JFrame frame = new JFrame("Simple Text Editor");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        JTextArea textArea = new JTextArea();
        
   
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleTextEditor().createAndShowGUI();
        });
    }
}
