private JPanel panel; 
public Linearized() {

    initialize();
}

private void initialize(int numRows, int numCols, String name, int ID){
  frame = new JFrame();
  frame.setTitle("GUI #1");
  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  frame.setSize(800, 500);
  frame.setLocationRelativeTo(null);

  panel = new JPanel(new GridLayout(0, 2));

  JLabel frontLabel = new JLabel("Front of Classroom");
  JLabel backLabel = new JLabel("Back of Classroom");
  panel.add(frontLabel);
  panel.add(backLabel);


  for(int i = numRows; i <= numsCols; i++){
    JButton button = new JButton("Name and Id Number: " + getName() + getID());
    panel.add(button);

  }

  frame.add(panel);


  frame.pack();
  frame.setVisible(true);


}
