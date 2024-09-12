import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class SimpleTextEditor {

    // Method to create and display the GUI
    private void createAndShowGUI() {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("Simple Text Editor");

        // Set the default close operation so the application exits when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTextArea for text input
        JTextArea textArea = new JTextArea();
        
        // Add a JScrollPane to the text area to handle large amounts of text
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Set the size of the window
        frame.setSize(600, 400);

        // Make the window visible
        frame.setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new SimpleTextEditor().createAndShowGUI();
        });
    }
}
