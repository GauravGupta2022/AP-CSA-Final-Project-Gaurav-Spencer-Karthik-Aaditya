// import java.awt.*;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import javax.swing.*;

// public class ClassroomGUI {
//     private int totalDesks;
//     private boolean isStudentView = false;
//     private Desk[][] desks;
//     private boolean isGroupSeating;
//     private Classroom classroom; // Assuming Classroom is defined elsewhere

//     public ClassroomGUI(Classroom classroom) {
//         this.classroom = classroom;
//         desks = classroom.getDesks();
//         isGroupSeating = checkGroupSeating(); // Check seating type
//         JFrame frame = new JFrame("Classroom Layout");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(800, 600);

//         JPanel controlPanel = new JPanel();
//         JButton toggleViewButton = new JButton("Switch to Student View");

//         toggleViewButton.addActionListener(e -> {
//             isStudentView = !isStudentView;
//             toggleViewButton.setText(isStudentView ? "Switch to Teacher View" : "Switch to Student View");
//             invertDeskArray();
//             frame.repaint();
//         });

//         controlPanel.add(toggleViewButton);
//         frame.setVisible(true);
//         frame.add(controlPanel, BorderLayout.NORTH);
//         frame.add(new ClassroomPanel(), BorderLayout.CENTER);
//     }

//     private boolean checkGroupSeating() {
//         try (BufferedReader br = new BufferedReader(new FileReader("GridSeating.txt"))) {
//             String seatingType = br.readLine();
//             return "group".equalsIgnoreCase(seatingType);
//         } catch (IOException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     private void invertDeskArray() {
//         int numRows = desks.length;
//         int numCols = desks[0].length;
//         Desk[][] invertedDesks = new Desk[numRows][numCols];

//         for (int i = 0; i < numRows; i++) {
//             for (int j = 0; j < numCols; j++) {
//                 invertedDesks[i][j] = desks[numRows - 1 - i][j];
//             }
//         }
//         desks = invertedDesks;
//     }

//     public void initializeStudentInformation(Desk[][] desks) {
//         if (desks != null) {
//             for (int row = 0; row < desks.length; row++) {
//                 for (int col = 0; col < desks[row].length; col++) {
//                     if (desks[row][col] != null) {
//                         // System.out.println("Student: " + desks[row][col].getStudent().getName());
//                         // System.out.println("ID: " + desks[row][col].getStudent().getID());
//                     }
//                 }
//             }
//         }
//     }

//     class ClassroomPanel extends JPanel {
//         @Override
//         public void paintComponent(Graphics g) {
//             super.paintComponent(g);

//             if (isStudentView) {
//                 drawStudentView(g);
//             } else {
//                 drawTeacherView(g);
//             }

//             if (desks != null) {
//                 if (isGroupSeating) {
//                     drawGroupSeating(g);
//                 } else {
//                     drawGridSeating(g);
//                 }
//             }

//             // Draw the door
//             g.setColor(Color.RED);
//             g.fillRect(830, 230, 30, 60);
//             g.setColor(Color.WHITE);
//             g.drawString("Door", 830, 260);
//         }

//         private void drawTeacherView(Graphics g) {
//             // Draw the front of the classroom
//             g.setColor(Color.RED);
//             g.fillRect(50, 10, 800, 30);
//             g.setColor(Color.WHITE);
//             g.drawString("Front of the Classroom", 350, 30);

//             // Draw the back of the classroom
//             g.setColor(Color.RED);
//             g.fillRect(50, 520, 800, 30);
//             g.setColor(Color.WHITE);
//             g.drawString("Back of the Classroom", 350, 540);
//         }

//         private void drawStudentView(Graphics g) {
//             // Draw the front of the classroom at the bottom
//             g.setColor(Color.RED);
//             g.fillRect(50, 520, 800, 30);
//             g.setColor(Color.WHITE);
//             g.drawString("Front of the Classroom", 350, 540);

//             // Draw the back of the classroom at the top
//             g.setColor(Color.RED);
//             g.fillRect(50, 10, 800, 30);
//             g.setColor(Color.WHITE);
//             g.drawString("Back of the Classroom", 350, 30);
//         }

//         private void drawGroupSeating(Graphics g) {
//             // Implement the group seating layout
//             g.setColor(Color.GRAY);
//             int tableWidth = 80;
//             int tableHeight = 60;
//             int panelWidth = tableWidth / 2;
//             int panelHeight = tableHeight / 2;
//             int tableGap = 20;

//             int startX = 100;
//             int startY = 70;
//             int rows = desks.length;
//             int cols = desks[0].length;

//             for (int row = 0; row < rows; row++) {
//                 for (int col = 0; col < cols; col++) {
//                     int x = startX + col * (tableWidth + tableGap);
//                     int y = startY + row * (tableHeight + tableGap);

//                     g.fillRect(x, y, tableWidth, tableHeight);

//                     // Draw the four panels inside each table
//                     g.fillRect(x, y, panelWidth, panelHeight);
//                     g.fillRect(x + panelWidth, y, panelWidth, panelHeight);
//                     g.fillRect(x, y + panelHeight, panelWidth, panelHeight);
//                     g.fillRect(x + panelWidth, y + panelHeight, panelWidth, panelHeight);
//                     g.drawRect(x, y, panelWidth, panelHeight);
//                     g.drawRect(x + panelWidth, y, panelWidth, panelHeight);
//                     g.drawRect(x, y + panelHeight, panelWidth, panelHeight);
//                     g.drawRect(x + panelWidth, y + panelHeight, panelWidth, panelHeight);

//                     if (desks[row][col] != null) {
//                         // Display the student info in each table
//                         String studentName = desks[row][col].getStudent().getName();
//                         g.drawString(studentName, x + 5, y + 20);
//                         String studentId = desks[row][col].getStudent().getID();
//                         g.drawString(String.valueOf(studentId), x + 5, y + 40);
//                     }
//                 }
//             }
//         }

//         private void drawGridSeating(Graphics g) {
//             // Implement the grid seating layout
//             int deskWidth = 120;
//             int deskHeight = 50;
//             int deskGap = 10;

//             int startX = 50;
//             int startY = 50;
//             int rows = classroom.getNumRows();
//             int cols = classroom.getNumCols();

//             for (int row = 0; row < rows; row++) {
//                 for (int col = 0; col < cols; col++) {
//                     int x = startX + col * (deskWidth + deskGap);
//                     int y = startY + row * (deskHeight + deskGap);
//                     g.setColor(Color.BLUE);
//                     g.fillRect(x, y, deskWidth, deskHeight);
//                     g.setColor(Color.WHITE);

//                     if (desks[row][col] != null) {
//                         // Display the student info in each desk
//                         String studentName = desks[row][col].getStudent().getName();
//                         g.drawString(studentName, x + 5, y + 20);
//                         String studentId = desks[row][col].getStudent().getID();
//                         g.drawString(String.valueOf(studentId), x + 5, y + 30);
//                     }
//                 }
//             }
//         }
//     }
// }
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ClassroomGUI {
    private boolean isStudentView = false;
    private Desk[][] desks;
    private boolean usingGridSeating;
    private Classroom classroom; // Assuming Classroom is defined elsewhere

    public ClassroomGUI(Classroom classroom, boolean usingGridSeating) {
        this.classroom = classroom;
        this.desks = classroom.getDesks();
        this.usingGridSeating = usingGridSeating;
        JFrame frame = new JFrame("Classroom Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel controlPanel = new JPanel();
        JButton toggleViewButton = new JButton("Switch to Student View");
        JButton randomizeButton = new JButton("Randomize Names");

        toggleViewButton.addActionListener(e -> {
            isStudentView = !isStudentView;
            toggleViewButton.setText(isStudentView ? "Switch to Teacher View" : "Switch to Student View");
            invertDeskArray();
            frame.repaint();
        });

        randomizeButton.addActionListener(e -> {
            randomizeStudentNames();
            frame.repaint();
        });

        controlPanel.add(toggleViewButton);
        controlPanel.add(randomizeButton);
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(new ClassroomPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void invertDeskArray() {
        int numRows = desks.length;
        int numCols = desks[0].length;
        Desk[][] invertedDesks = new Desk[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                invertedDesks[i][j] = desks[numRows - 1 - i][j];
            }
        }
        desks = invertedDesks;
    }

    private void randomizeStudentNames() {
        ArrayList <Student> students = new ArrayList<>();
        for (Desk[] row : desks) {
            for (Desk desk : row) {
                if (desk != null && desk.getStudent() != null) {
                    students.add(desk.getStudent());
                }
            }
        }
        Collections.shuffle(students);
        int index = 0;
        for (Desk[] row : desks) {
            for (Desk desk : row) {
                if (desk != null && index < students.size()) {
                    desk.setStudent(students.get(index));
                    index++;
                }
            }
        }
    }

    public void initializeStudentInformation(Desk[][] desks) {
        if (desks != null) {
            for (int row = 0; row < desks.length; row++) {
                for (int col = 0; col < desks[row].length; col++) {
                    if (desks[row][col] != null) {
                        // System.out.println("Student: " + desks[row][col].getStudent().getName());
                        // System.out.println("ID: " + desks[row][col].getStudent().getID());
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
                if (!usingGridSeating) {
                    drawGroupSeating(g);
                } else {
                    drawGridSeating(g);
                }
            }

            // Draw the door
            g.setColor(Color.RED);
            g.fillRect(830, 230, 30, 60);
            g.setColor(Color.WHITE);
            g.drawString("Door", 830, 260);
        }

        private void drawTeacherView(Graphics g) {
            // Draw the front of the classroom
            g.setColor(Color.RED);
            g.fillRect(50, 10, 800, 30);
            g.setColor(Color.WHITE);
            g.drawString("Back of the Classroom", 350, 30);//touched

            // Draw the back of the classroom
            g.setColor(Color.RED);
            g.fillRect(50, 520, 800, 30);
            g.setColor(Color.WHITE);
            g.drawString("Front of the Classroom", 350, 540);//touched
        }

        private void drawStudentView(Graphics g) {
            // Draw the front of the classroom at the bottom
            g.setColor(Color.RED);
            g.fillRect(50, 520, 800, 30);
            g.setColor(Color.WHITE);
            g.drawString("Back of the Classroom", 350, 540);//touched

            // Draw the back of the classroom at the top
            g.setColor(Color.RED);
            g.fillRect(50, 10, 800, 30);
            g.setColor(Color.WHITE);
            g.drawString("Front of the Classroom", 350, 30);//touched
        }

        private void drawGroupSeating(Graphics g) {
            // Implement the group seating layout
            int tableWidth = 220;
            int tableHeight = 120;
            int tableGap = 20;

            int startX = 100;
            int startY = 70;
            int rows = desks.length / 2;
            int cols = desks[0].length / 2;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int x = startX + col * (tableWidth + tableGap);
                    int y = startY + row * (tableHeight + tableGap);

                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, tableWidth, tableHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, tableWidth, tableHeight);

                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            int deskX = x + j * (tableWidth / 2);
                            int deskY = y + i * (tableHeight / 2);
                            if (desks[row * 2 + i][col * 2 + j] != null && desks[row * 2 + i][col * 2 + j].getStudent() != null) {
                                // Display the student info in each desk
                                String studentName = desks[row * 2 + i][col * 2 + j].getStudent().getName();
                                g.drawString(studentName, deskX + 5, deskY + 20);
                                String studentId = desks[row * 2 + i][col * 2 + j].getStudent().getID();
                                g.drawString(String.valueOf(studentId), deskX + 5, deskY + 40);
                            }
                        }
                    }
                }
            }
        }

        private void drawGridSeating(Graphics g) {
            // Implement the grid seating layout
            int deskWidth = 120;
            int deskHeight = 50;
            int deskGap = 10;

            int startX = 50;
            int startY = 50;
            int rows = classroom.getNumRows();
            int cols = classroom.getNumCols();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int x = startX + col * (deskWidth + deskGap);
                    int y = startY + row * (deskHeight + deskGap);
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, deskWidth, deskHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, deskWidth, deskHeight);

                    if (desks[row][col] != null && desks[row][col].getStudent() != null) {
                        // Display the student info in each desk
                        String studentName = desks[row][col].getStudent().getName();
                        g.drawString(studentName, x + 5, y + 20);
                        String studentId = desks[row][col].getStudent().getID();
                        g.drawString(String.valueOf(studentId), x + 5, y + 30);
                    }
                }
            }
        }
    }
}
