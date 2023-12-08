package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.IGameController;

/**
 * This class represents the configuration settings window which the user will use to change the
 * settings of the dungeon. The settings include size, interconnectivity, wrapping / non wrapping,
 * monster count and treasure / arrow percentage.
 */
class ConfigFrame extends JFrame {
  private final JTextField rowsInput;
  private final JTextField columnsInput;
  private final JTextField interconnectivityInput;
  private final JTextField percentageInput;
  private final JTextField monstersInput;
  private final JTextField wrappingInput;
  private final IGameController controller;

  /**
   * Initialize the settings frame with text boxes, labels and a submit button which will help user
   * to update existing settings.
   *
   * @param controller the controller for the graphical adventure game
   */
  public ConfigFrame(IGameController controller) {
    super("Edit Configurations");
    this.controller = controller;
    setSize(400, 400);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    JLabel rows = new JLabel("Rows :");
    rows.setBounds(80, 20, 150, 20);
    rowsInput = new JTextField();
    rowsInput.setBounds(220, 20, 50, 20);
    add(rows);
    add(rowsInput);

    JLabel columns = new JLabel("Columns :");
    columns.setBounds(80, 60, 150, 20);
    columnsInput = new JTextField();
    columnsInput.setBounds(220, 60, 50, 20);
    add(columns);
    add(columnsInput);

    JLabel interconnectivity = new JLabel("Interconnectivity :");
    interconnectivity.setBounds(80, 100, 150, 20);
    interconnectivityInput = new JTextField();
    interconnectivityInput.setBounds(220, 100, 50, 20);
    add(interconnectivity);
    add(interconnectivityInput);

    JLabel percentage = new JLabel("Treasure % :");
    percentage.setBounds(80, 140, 150, 20);
    percentageInput = new JTextField();
    percentageInput.setBounds(220, 140, 50, 20);
    add(percentage);
    add(percentageInput);

    JLabel monsters = new JLabel("Monsters :");
    monsters.setBounds(80, 180, 150, 20);
    monstersInput = new JTextField();
    monstersInput.setBounds(220, 180, 50, 20);
    add(monsters);
    add(monstersInput);

    JLabel wrapping = new JLabel("Wrapping :");
    wrapping.setBounds(80, 220, 150, 20);
    wrappingInput = new JTextField();
    wrappingInput.setBounds(220, 220, 50, 20);
    add(wrapping);
    add(wrappingInput);
    JButton submit = new JButton();
    submit.setText("Ok");
    submit.setBounds(170, 270, 75, 25);
    submit.addActionListener(new ConfigHandler());
    add(submit);
  }

  /**
   * This class represents the action listener for the configuration frame. It is a private class
   * and manages all the actions for the frame.
   */
  private class ConfigHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

      try {
        boolean wrap = wrappingInput.getText().equalsIgnoreCase("True");
        try {
          controller.startNewGame(Integer.parseInt(rowsInput.getText()),
                  Integer.parseInt(columnsInput.getText()),
                  Integer.parseInt(interconnectivityInput.getText()),
                  Integer.parseInt(percentageInput.getText()),
                  wrap, Integer.parseInt(monstersInput.getText()));

          dispose();
        } catch (IllegalStateException ise) {
          JOptionPane.showMessageDialog(ConfigFrame.this,
                  "Cannot Create Dungeon for given parameters."
                          + "Please enter new parameters");
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(ConfigFrame.this, "Not an integer. "
                + "Please enter integer");
      }
    }
  }
}
