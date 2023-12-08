import java.io.StringReader;

import controller.Controller;
import controller.DungeonController;
import dungeon.DungeonMonsterImpl;
import dungeon.DungeonWithMonster;


/**
 * This run demonstrates the player navigating the dungeon, injuring the Otyugh and barely
 * escaping it and further quitting the game.
 */
public class Run3 {
  /**
   * This is the main method for this run demonstration.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    DungeonWithMonster dungeon = new DungeonMonsterImpl(3, 4, 1, 30,
            true, 2, 4);

    StringReader input = new StringReader("p m e m s m e m s s w 1 m w m e q");
    Appendable log = new StringBuffer();
    DungeonController controller = new Controller(input, log);
    controller.play(dungeon);
  }
}
