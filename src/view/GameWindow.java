package view;

import common.Treasure;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


import controller.IGameController;
import location.Cave;
import location.Location;

import location.Tunnel;
import players.Directions;

/**
 * This class represents the actual window where the dungeon will be displayed to the user.
 */
class GameWindow extends JPanel {
  private Location[][] grid;
  private IGameController controller;
  private ImageLoader loader;
  private GridBagLayout layout;

  public GameWindow(IGameController controller) {
    this.controller = controller;
    loader = new ImageLoader();
    layout = new GridBagLayout();
    setLayout(layout);
    grid = controller.getGrid();
    repaint();
  }

  @Override
  public void repaint() {
    if (controller == null) {
      return;
    }
    grid = controller.getGrid();
    int x = 0;
    int y = 0;
    int r = grid.length;
    int c = grid[0].length;
    try {
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.fill = GridBagConstraints.VERTICAL;

      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          constraints.gridx = j;
          constraints.gridy = i;
          JLabel temp = new JLabel();
          add(temp, constraints);
        }
      }
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          JLabel temp = (JLabel) getComponent(grid[0].length * i + j);
          removeMouseListener(temp);
        }
      }
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          JLabel temp = (JLabel) getComponent(grid[0].length * i + j);
          temp.setIcon(new ImageIcon(loader.black));
          if (grid[i][j] instanceof Cave) {
            String filename = imageLoader(grid[i][j]);
            BufferedImage img = loader.locations.get(filename);
            if (((Cave) grid[i][j]).hasMonster()) {
              img = overlay(img, loader.otyugh, 0);
            }
            if (((Cave) grid[i][j]).hasArrows()) {
              img = overlay(img, loader.arrow, 0);
            }
            if (((Cave) grid[i][j]).hasTreasure()) {
              List<Treasure> treasureList = new ArrayList<>();
              treasureList = grid[i][j].getTreasureList();
              for (Treasure treasure : treasureList) {
                if (treasure.getName().toString().equals("RUBY")) {
                  img = overlay(img, loader.ruby, 0);
                }
                if (treasure.getName().toString().equals("DIAMOND")) {
                  img = overlay(img, loader.diamond, 10);
                }
                if (treasure.getName().toString().equals("SAPPHIRE")) {
                  img = overlay(img, loader.sapphire, 20);
                }
              }
            }
            if (grid[i][j].getSmell().toString().equals("WEAK")) {
              img = overlay(img, loader.stench1, 0);
            }
            if (grid[i][j].getSmell().toString().equals("STRONG")) {
              img = overlay(img, loader.stench2, 0);
            }
            if (grid[i][j].equals(controller.getPlayerCurrentLocation())) {
              x = j;
              y = i;
              img = overlay(img, loader.player, 10);
            }
            if (!grid[i][j].getExploredStatus()) {

              img = overlay(img, loader.black, 0);
            }
            temp.setIcon(new ImageIcon(img));
          }
          if (grid[i][j] instanceof Tunnel) {
            String filename = imageLoader(grid[i][j]);
            BufferedImage img = loader.locations.get(filename);
            if (((Tunnel) grid[i][j]).hasArrows()) {
              img = overlay(img, loader.arrow, 0);
            }
            if (grid[i][j].getSmell().toString().equals("WEAK")) {
              img = overlay(img, loader.stench1, 0);
            }
            if (grid[i][j].getSmell().toString().equals("STRONG")) {
              img = overlay(img, loader.stench2, 0);
            }
            if (grid[i][j].equals(controller.getPlayerCurrentLocation())) {
              x = j;
              y = i;
              img = overlay(img, loader.player, 10);
            }
            if (!grid[i][j].getExploredStatus()) {
              img = overlay(img, loader.black, 0);
            }

            temp.setIcon(new ImageIcon(img));
          }
        }
      }
      for (Directions dir : grid[y][x].getDirections()) {
        for (Component comp : getComponents()) {
          GridBagConstraints gbc = layout.getConstraints(comp);
          switch (dir) {
            case NORTH:
              if ((x % r) == gbc.gridx && ((y - 1 + c) % c) == gbc.gridy) {
                comp.addMouseListener(new MouseHandler(Directions.NORTH));
              }
              break;
            case SOUTH:
              if ((x % r) == gbc.gridx && ((y + 1 + c) % c) == gbc.gridy) {
                comp.addMouseListener(new MouseHandler(Directions.SOUTH));
              }
              break;
            case WEST:
              if (((x - 1 + r) % r) == gbc.gridx && (y % c) == gbc.gridy) {
                comp.addMouseListener(new MouseHandler(Directions.WEST));
              }
              break;
            case EAST:
              if (((x + 1 + r) % r) == gbc.gridx && (y % c) == gbc.gridy) {
                comp.addMouseListener(new MouseHandler(Directions.EAST));
              }
              break;
            default:
              break;
          }
        }
      }

    } catch (IOException io) {
      JDialog dialog;
      dialog = new JDialog();
      dialog.add(new JLabel(io.getMessage(), JLabel.CENTER));
      dialog.setSize(new Dimension(300, 100));
      dialog.setLocationRelativeTo(null);
      dialog.setVisible(true);
    }
  }

  private void removeMouseListener(JLabel label) {
    for (MouseListener mouse : label.getMouseListeners()) {
      label.removeMouseListener(mouse);
    }
  }


  private BufferedImage overlay(BufferedImage starting, BufferedImage ending, int offset)
          throws IOException {
    int w = Math.max(starting.getWidth(), ending.getWidth());
    int h = Math.max(starting.getHeight(), ending.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(ending, offset, offset, null);
    return combined;
  }

  private String imageLoader(Location loc) {
    StringBuilder loadImg = new StringBuilder();
    List<Directions> exits = loc.getDirections();
    if (exits.contains(Directions.NORTH)) {
      loadImg.append("N");
    }
    if (exits.contains(Directions.EAST)) {
      loadImg.append("E");
    }
    if (exits.contains(Directions.WEST)) {
      loadImg.append("W");
    }
    if (exits.contains(Directions.SOUTH)) {
      loadImg.append("S");
    }
    return loadImg.toString();
  }

  /**
   * Private class that represents the mouse listener which will be used to move the player using
   * mouse click.
   */
  private class MouseHandler extends MouseAdapter {
    Directions direction;

    /**
     * Initialize the constructor for Mouse Handler class.
     * @param direction the direction which the player moves
     */
    public MouseHandler(Directions direction) {
      this.direction = direction;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
      controller.movePlayer(direction);
    }
  }
}
