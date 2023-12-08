import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame {

    private JTextArea output;
    private JTextField input;

    public ClientUI() {
        Color light = Color.decode("#fafafa");
        Color dark = Color.decode("#181818");
        Font font = new Font("Arial", Font.PLAIN, 16);

        output = new JTextArea();
        output.setForeground(light);
        output.setBackground(dark);
        output.setFont(font);
        output.setBorder(BorderFactory.createLineBorder(light, 1));
        output.setEditable(false);

        input = new JTextField();
        input.setForeground(light);
        input.setBackground(dark);
        input.setFont(font);
        input.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton button = new JButton("Send");
        button.setForeground(light);
        button.setBackground(dark);
        button.setFont(font);
        button.addActionListener(l -> sendMessage());

        // Hier wird die Höhe des Buttons auf 40 Pixel gesetzt (Beispielwert)
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, 40));

        this.setTitle("ChatRoom");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(400, 300));
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        // Verwenden Sie BorderLayout
        this.setLayout(new BorderLayout());

        // Fügen Sie die Komponenten zum Süden (BOTTOM) hinzu
        this.add(output, BorderLayout.CENTER);

        // Erstellen Sie ein Panel für input und button und fügen Sie es im Süden hinzu
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(input, BorderLayout.CENTER);

        // Fügen Sie den Button im Westen (LEFT) des inputPanels hinzu
        inputPanel.add(button, BorderLayout.EAST);

        this.add(inputPanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    public void sendMessage(){
        String msg = input.getText();
        output.append("You: " +msg +"\n");
        input.setText("");
    }

    public void printOutput(String msg) {
        output.append(msg + "\n"); // Füge die Nachricht zum output hinzu
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientUI());
    }
}