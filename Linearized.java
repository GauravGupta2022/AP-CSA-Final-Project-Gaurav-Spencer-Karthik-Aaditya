private JPanel panel; 
public Linearized() {

    initialize();
}

private void initialize(){
  frame = new JFrame();
  frame.setTitle("GUI #1");
  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  frame.setSize(800, 500);
  frame.setLocationRelativeTo(null);

  panel = new JPanel(new GridLayout());

  for(int i = numRows; i <= numsCols; i++){
    JButton button = new JButton("Name and Id Number: " + getName() + getID());
    panel.add(button);

  }

  frame.add(panel);


  frame.pack();
  frame.setVisible(true);


}