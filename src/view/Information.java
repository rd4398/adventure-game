package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.IGameController;

/**
 * This class represents the information that is displayed on the screen for the user during the
 * game. The information consists of player description, location description and the instructions
 * to play the game.
 */
class Information extends JPanel {
  private final IGameController controller;
  private final JTextArea gameDesc;

  /**
   * Construct the Information class object and initialize it with the constructor.
   *
   * @param controller the controller for the graphical adventure game
   */
  public Information(IGameController controller) {
    this.controller = controller;
    setPreferredSize(new Dimension(1000, 700));
    setMaximumSize(new Dimension(1000, 700));
    setMinimumSize(new Dimension(1000, 700));
    setLayout(null);
    JLabel title = new JLabel("Player and Location Description");
    title.setBounds(200, 50, 300, 30);
    JLabel controlTitle = new JLabel("Controls");
    controlTitle.setBounds(625, 50, 300, 30);
    gameDesc = new JTextArea();
    gameDesc.setSize(new Dimension(320, 270));
    gameDesc.setPreferredSize(new Dimension(320, 270));
    gameDesc.setBounds(150, 100, 320, 270);
    Font font = new Font("Segoe Script", Font.BOLD, 12);
    gameDesc.setFont(font);
    gameDesc.setBackground(Color.DARK_GRAY);
    gameDesc.setForeground(Color.CYAN);
    JTextArea controls = new JTextArea();
    controls.setSize(new Dimension(320, 250));
    controls.setPreferredSize(new Dimension(320, 250));
    controls.setBounds(500, 100, 350, 250);
    controls.setFont(font);
    controls.setEditable(false);
    controls.setBackground(Color.DARK_GRAY);
    controls.setForeground(Color.WHITE);
    controls.setText(setControlsString());

    add(title);
    add(gameDesc);
    add(controls);
    add(controlTitle);

  }

  @Override
  public void repaint() {
    if (controller == null) {
      return;
    }
    gameDesc.setText(controller.getInformation().toString());
  }

  private String setControlsString() {
    StringBuilder sb = new StringBuilder();
    sb.append("-----------------------Keyboard Controls--------------------- \n")
            .append("\nArrow Keys : Move player in required direction\n")
            .append("\n t : Collect Treasure\n")
            .append("\n a : Collect Arrows\n")
            .append("\n s + Arrow Keys (For Direction) : Shoot Arrow\n")
            .append("\n")
            .append("\n-----------------------Mouse Controls----------------------------- \n")
            .append("\n- Move player using mouse click in valid direction\n");
    return sb.toString();
  }
}
