import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTerminalGUI {
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton executeButton;
    private JPanel panel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleTerminalGUI::new);  // Launch GUI on the Event Dispatch Thread
    }

    public SimpleTerminalGUI() {
        JFrame frame = new JFrame("Simple Terminal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputArea = new JTextArea(20, 50);  // Text area for output
        outputArea.setEditable(false);  // Make output area read-only
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField(40);  // Text field for user input
        executeButton = new JButton("Execute");

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(executeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // Event listener for the Execute button
        executeButton.addActionListener(new ExecuteCommandListener());
        inputField.addActionListener(new ExecuteCommandListener());  // Allows hitting 'Enter' to execute command
    }

    // Listener to handle command execution when 'Execute' button is pressed
    private class ExecuteCommandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = inputField.getText().trim();
            if (!command.isEmpty()) {
                // Add the command to the output area
                outputArea.append("mysh> " + command + "\n");

                // Execute the command in a separate thread
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.submit(() -> executeCommand(command));

                inputField.setText("");  // Clear the input field after execution
            }
        }
    }

    // Parses the input command and executes it
    private void executeCommand(String command) {
        String[] commandArgs = command.trim().split("\\s+");  // Parse the input command
        ProcessBuilder processBuilder = new ProcessBuilder(commandArgs);
        processBuilder.redirectErrorStream(true);  // Redirect error stream to output

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Read output from the command execution and append it to the output area
            while ((line = reader.readLine()) != null) {
                appendOutput(line);
            }

            process.waitFor();  // Wait for the process to finish
        } catch (IOException | InterruptedException ex) {
            appendOutput("Error: " + ex.getMessage());
        }
    }

    // Safely appends text to the output area from any thread
    private void appendOutput(String text) {
        SwingUtilities.invokeLater(() -> outputArea.append(text + "\n"));
    }
}
