
import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import controller.GameController;
import controller.IGameController;
import players.Directions;

/**
 * This class consists of all the unit tests for the controller of the graphical adventure game.
 */
public class GUIControllerTest {
  IGameController controller;
  MockView view;
  MockModel model;
  StringBuffer log;

  @Before
  public void setup() {
    log = new StringBuffer();
    model = new MockModel(log);
    view = new MockView(log);
    controller = new GameController(model, 4);
    controller.setView(view);
  }

  @Test
  public void testMoveCommand() {
    controller.movePlayer(Directions.NORTH);
    assertEquals("Move command called\n"
            + "Game Status called\n"
            + "\n"
            + "Refresh called\n", log.toString());
  }

  @Test
  public void testShootCommand() {
    controller.shootArrow(Directions.EAST, 2);
    assertEquals("Shoot arrow called", log.toString());
  }

  @Test
  public void testPickArrowCommand() {
    controller.collectArrowsFromLocation();
    assertEquals("Collect arrows command called\n"
            + "Refresh called\n", log.toString());
  }

  @Test
  public void testPickTreasureCommand() {
    controller.collectTreasureAtLocation();
    assertEquals("Collect treasure command called\n"
            + "Refresh called\n", log.toString());
  }

}
