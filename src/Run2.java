
import java.io.StringReader;

import controller.Controller;
import controller.DungeonController;

import dungeon.DungeonMonsterImpl;
import dungeon.DungeonWithMonster;


/**
 * This class demonstrates a run where the player navigates the dungeon and is killed by Otyugh.
 */
public class Run2 {
  /**
   * This is the main method for this run demonstration.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    DungeonWithMonster dungeon = new DungeonMonsterImpl(3, 4, 1, 30,
            true, 2, 4);

    StringReader input = new StringReader("p m e m s m e m s m w");
    Appendable log = new StringBuffer();
    DungeonController controller = new Controller(input, log);
    controller.play(dungeon);
  }
}
