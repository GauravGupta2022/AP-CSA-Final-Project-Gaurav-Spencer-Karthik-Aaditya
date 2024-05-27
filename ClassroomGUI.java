import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class ClassroomGUI {
    private int totalDesks;
    private boolean isStudentView = false;
    private Desk[][] desks;
    private boolean isGroupSeating;
    private Classroom classroom; // Assuming Classroom is defined elsewhere

    public ClassroomGUI(Classroom classroom) {
        this.classroom = classroom;
        desks = classroom.getDesks();
        isGroupSeating = checkGroupSeating(); // Check seating type
        JFrame frame = new JFrame("Classroom Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel controlPanel = new JPanel();
        JButton toggleViewButton = new JButton("Switch to Student View");

        toggleViewButton.addActionListener(e -> {
            isStudentView = !isStudentView;
            toggleViewButton.setText(isStudentView ? "Switch to Teacher View" : "Switch to Student View");
            frame.repaint();
        });

        controlPanel.add(toggleViewButton);
        frame.setVisible(true);
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(new ClassroomPanel(), BorderLayout.CENTER);
    }

    private boolean checkGroupSeating() {
        try (BufferedReader br = new BufferedReader(new FileReader("seating_type.txt"))) {
            String seatingType = br.readLine();
            return "group".equalsIgnoreCase(seatingType);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void initializeStudentInformation(Desk[][] desks) {
        if (desks != null) {
            for (int row = 0; row < desks.length; row++) {
                for (int col = 0; col < desks[row].length; col++) {
                    if (desks[row][col] != null) {
                        System.out.println("Student: " + desks[row][col].getStudent().getName());
                        System.out.println("ID: " + desks[row][col].getStudent().getID());

                    }
                }
            }
        }
    }

    class ClassroomPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (isStudentView) {
                drawStudentView(g);
            } else {
                drawTeacherView(g);
            }

            if (desks != null) {
                if (isGroupSeating) {
                    drawGroupSeating(g);
                } else {
                    drawGridSeating(g);
                }
            }

            // Draw the door
            g.setColor(Color.RED);
            g.fillRect(700, 230, 30, 60);
            g.setColor(Color.WHITE);
            g.drawString("Door", 705, 260);
        }

        private void drawTeacherView(Graphics g) {
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
        }

        private void drawStudentView(Graphics g) {
            // Draw the front of the classroom at the bottom
            g.setColor(Color.BLUE);
            g.fillRect(50, 520, 700, 30);
            g.setColor(Color.WHITE);
            g.drawString("Front of the Classroom", 350, 540);

            // Draw the back of the classroom at the top
            g.setColor(Color.BLUE);
            g.fillRect(50, 10, 700, 30);
            g.setColor(Color.WHITE);
            g.drawString("Back of the Classroom", 350, 30);
        }

        private void drawGroupSeating(Graphics g) {
            // Implement the group seating layout
            g.setColor(Color.GRAY);
            int tableWidth = 80;
            int tableHeight = 60;
            int panelWidth = tableWidth / 2;
            int panelHeight = tableHeight / 2;
            int tableGap = 20;

            int startX = 100;
            int startY = 70;
            int rows = desks.length;
            int cols = desks[0].length;

            int deskIndex = 0;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (deskIndex >= totalDesks) break;

                    int x = startX + col * (tableWidth + tableGap);
                    int y = startY + row * (tableHeight + tableGap);

                    g.fillRect(x, y, tableWidth, tableHeight);

                    // Draw the four panels inside each table
                    g.setColor(new Color(0, 0, 0, 255));
                    g.fillRect(x, y, panelWidth, panelHeight);
                    g.fillRect(x + panelWidth, y, panelWidth, panelHeight);
                    g.fillRect(x, y + panelHeight, panelWidth, panelHeight);
                    g.fillRect(x + panelWidth, y + panelHeight, panelWidth, panelHeight);

                    g.drawRect(x, y, panelWidth, panelHeight);
                    g.drawRect(x + panelWidth, y, panelWidth, panelHeight);
                    g.drawRect(x, y + panelHeight, panelWidth, panelHeight);
                    g.drawRect(x + panelWidth, y + panelHeight, panelWidth, panelHeight);

                    if (desks[row][col] != null) {
                        // Display the student info in each table
                        String studentName = desks[row][col].getStudent().getName();
                        g.drawString(studentName, x + 5, y + 20);
                        String studentId = desks[row][col].getStudent().getID();
                        g.drawString(String.valueOf(studentId), x + 5, y + 40);
                        JLabel studentLabel = new JLabel(studentName + ", " + studentId, JLabel.CENTER);
                        studentLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Optional: Set font
                        studentLabel.setVisible(true);
                    }

                

                    deskIndex++;
                }
            }
        }

        private void drawGridSeating(Graphics g) {
            // Implement the grid seating layout
            g.setColor(new Color(0, 0, 0, 255));
            int deskWidth = 60;
            int deskHeight = 40;
            int deskGap = 10;

            int startX = 50;
            int startY = 50;
            int rows = classroom.getNumRows();
            int cols = classroom.getNumCols();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int x = startX + col * (deskWidth + deskGap);
                    int y = startY + row * (deskHeight + deskGap);

                    g.fillRect(x, y, deskWidth, deskHeight);

                    if (desks[row][col] != null) {
                        // Display the student info in each desk
                        String studentName = desks[row][col].getStudent().getName();
                        g.drawString(studentName, x + 5, y + 20);
                        String studentId = desks[row][col].getStudent().getID();
                        g.drawString(String.valueOf(studentId), x + 5, y + 30);
                        // Define transparency level for the rectangle (optional)
                        JLabel studentLabel = new JLabel(studentName + ", " + studentId, JLabel.CENTER);
                        studentLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Optional: Set font
                        studentLabel.setVisible(true);
                    }
                }
            }
        }
    }
}