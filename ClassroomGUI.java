import javax.swing.*;
import java.awt.*;

public class ClassroomGUI {
    private int totalDesks;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClassroomGUI gui = new ClassroomGUI();
            gui.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Classroom Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel controlPanel = new JPanel();
        JLabel deskLabel = new JLabel("Total Desks:");
        JTextField deskField = new JTextField(5);
        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(e -> {
            try {
                totalDesks = Integer.parseInt(deskField.getText());
                frame.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        controlPanel.add(deskLabel);
        controlPanel.add(deskField);
        controlPanel.add(updateButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(new ClassroomPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    class ClassroomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the front of the classroom
            g.setColor(Color.BLUE);
            g.fillRect(50, 10, 700, 30);
            g.setColor(Color.WHITE);
            g.drawString("Front of the Classroom", 350, 30);

            // Draw the back of the classroom
            g.setColor(Color.BLUE);
            g.fillRect(50, 520, 700, 30);
            g.setColor(Color.WHITE);
            g.drawString("Back of the Classroom", 350, 540);

            // Draw tables with panels showing desks
            g.setColor(Color.GRAY);
            int tableWidth = 80;
            int tableHeight = 60;
            int panelWidth = tableWidth / 2;
            int panelHeight = tableHeight / 2;
            int tableGap = 20;

            int startX = 100;
            int startY = 70;
            int rows = 4;
            int cols = 5;

            int desksPerTable = totalDesks / (rows * cols);
            int remainingDesks = totalDesks % (rows * cols);

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int x = startX + col * (tableWidth + tableGap);
                    int y = startY + row * (tableHeight + tableGap);

                    g.fillRect(x, y, tableWidth, tableHeight);

                    // Draw the four panels inside each table
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, panelWidth, panelHeight);
                    g.fillRect(x + panelWidth, y, panelWidth, panelHeight);
                    g.fillRect(x, y + panelHeight, panelWidth, panelHeight);
                    g.fillRect(x + panelWidth, y + panelHeight, panelWidth, panelHeight);

                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, panelWidth, panelHeight);
                    g.drawRect(x + panelWidth, y, panelWidth, panelHeight);
                    g.drawRect(x, y + panelHeight, panelWidth, panelHeight);
                    g.drawRect(x + panelWidth, y + panelHeight, panelWidth, panelHeight);

                    // Display the number of desks in each table
                    int desksInThisTable = desksPerTable;
                    if (remainingDesks > 0) {
                        desksInThisTable++;
                        remainingDesks--;
                    }
                    g.drawString(String.valueOf(desksInThisTable), x + (tableWidth / 2) - 5, y + (tableHeight / 2));
                }
            }

            // Draw the door
            g.setColor(Color.RED);
            g.fillRect(700, 230, 30, 60);
            g.setColor(Color.WHITE);
            g.drawString("Door", 705, 260);
        }
    }
}
