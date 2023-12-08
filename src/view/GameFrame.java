package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.IGameController;
import players.Directions;

/**
 * This class represents the game frame where the dungeon and player will be visible to user.
 * It also consists of settings for the game in a menu. The user can also see description of player
 * and location.
 */
public class GameFrame extends JFrame implements IGameFrame {
  private final GameWindow panel;
  private final Information description;
  private final IGameController controller;

  /**
   * Construct the main frame of the view and initialize with the controller.
   *
   * @param controller the controller for the game.
   */
  public GameFrame(IGameController controller) {
    super("Graphical Adventure Game");
    this.controller = controller;
    setSize(1000, 1200);
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    JMenu settings = new JMenu("Settings");
    menuBar.add(settings);

    settings.addSeparator();
    JMenuItem restartOld = new JMenuItem("Restart Game");
    restartOld.addActionListener(new MenuHandler());
    settings.add(restartOld);
    settings.addSeparator();
    JMenuItem restartNew = new JMenuItem("New Game");
    restartNew.addActionListener(new MenuHandler());
    settings.add(restartNew);
    settings.addSeparator();
    JMenuItem quit = new JMenuItem("Quit");
    settings.add(quit);
    quit.addActionListener(new MenuHandler());
    panel = new GameWindow(controller);
    JScrollPane pane = new JScrollPane(panel);
    pane.setPreferredSize(new Dimension(1000, 800));
    pane.setMinimumSize(new Dimension(1000, 800));
    constraints.gridy = 1;
    add(pane, constraints);
    constraints.gridy = 2;
    addKeyListener(new KeyHandler());
    description = new Information(controller);
    description.setSize(new Dimension(1000, 575));
    description.setMinimumSize(new Dimension(1000, 575));
    description.setPreferredSize(new Dimension(1000, 575));
    description.setMaximumSize(new Dimension(1000, 575));
    constraints.gridy = 3;
    add(description, constraints);
    makeVisible();
    requestFocus();
  }

  @Override
  public void refresh() {
    description.repaint();
    panel.repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
    setLocationRelativeTo(null);
  }

  @Override
  public void showGameStatus(boolean gameResult) {
    String endMsg = "";
    if (gameResult) {
      endMsg = "Game Ended ! You Won !";
    } else {
      endMsg = "Game Ended ! Monster Killed You !";
    }
    JDialog dialog1 = new JDialog(this, "Game state");
    dialog1.add(new JLabel(endMsg, JLabel.CENTER));
    dialog1.setSize(new Dimension(300, 100));
    dialog1.setLocationRelativeTo(null);
    dialog1.setVisible(true);
  }

  /**
   * Private class which acts as the listener for menu that is present on the main frame.
   */
  private class MenuHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      String command = actionEvent.getActionCommand();
      switch (command) {
        case "Quit":
          int dialogButton = JOptionPane.YES_NO_OPTION;
          JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
                  "Warning", dialogButton);

          if (dialogButton == JOptionPane.YES_OPTION) {
            System.exit(NORMAL);
          }
          break;
        case "New Game":
          new ConfigFrame(controller);
          dispose();
          break;
        case "Restart Game":
          controller.restartGame();
          break;
        default:
          break;
      }
    }
  }

  /**
   * This class represents the key listener and listens to all the key events that take place
   * on the main frame.
   */
  private class KeyHandler extends KeyAdapter {
    boolean shoot = false;

    @Override
    public void keyReleased(KeyEvent keyEvent) {

      Directions direction = null;
      if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
        direction = Directions.NORTH;
      }
      if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
        direction = Directions.SOUTH;
      }
      if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
        direction = Directions.WEST;
      }
      if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
        direction = Directions.EAST;
      }
      if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
        shoot = true;
      } else {
        try {
          if (shoot && direction != null) {
            SpinnerNumberModel model = new SpinnerNumberModel(1, 0, 100,
                     1);
            JSpinner spinner = new JSpinner(model);
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
            JOptionPane.showMessageDialog(GameFrame.this, spinner);
            int distance = ((Integer) spinner.getValue());
            try {
              boolean result = controller.shootArrow(direction, distance);
              if (result) {
                JOptionPane.showMessageDialog(GameFrame.this,
                        "You shot Otyugh!");
              } else {
                JOptionPane.showMessageDialog(GameFrame.this,
                        "Your arrow missed Otyugh!");
              }
            } catch (IllegalStateException ise) {
              JOptionPane.showMessageDialog(GameFrame.this,
                      "No arrows to shoot");
            }
            controller.refreshView();
            shoot = false;
          } else if (direction != null) {
            controller.movePlayer(direction);
          }
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(GameFrame.this, e.getMessage());
        }
      }
      try {
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
          controller.collectArrowsFromLocation();
        }
      } catch (IllegalStateException e) {
        JOptionPane.showMessageDialog(GameFrame.this, e.getMessage());
      }
      try {
        if (keyEvent.getKeyCode() == KeyEvent.VK_T) {
          controller.collectTreasureAtLocation();
        }
      } catch (IllegalStateException e) {
        JOptionPane.showMessageDialog(GameFrame.this, e.getMessage());
      }
    }
  }
}