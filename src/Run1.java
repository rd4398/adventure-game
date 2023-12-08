
import java.io.StringReader;
import java.util.HashSet;

import java.util.Set;

import controller.Controller;
import controller.DungeonController;

import dungeon.DungeonMonsterImpl;
import dungeon.DungeonWithMonster;

import location.Location;


/**
 * This class demonstrates a run where the player navigates through dungeon, picking treasure,
 * picking arrows and being eaten by Otyugh.
 */
public class Run1 {
  /**
   * This is the main method for this run demonstration.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    DungeonWithMonster dungeon = new DungeonMonsterImpl(2, 6, 0, 30,
            false, 1, 4);
    Location[][] network = dungeon.getGrid();
    Set<Location> visited = new HashSet<>();

    StringReader input = new StringReader("m w m n p t p a m w p a m s m w s w 1 s w 1 m w q");
    Appendable log = new StringBuffer();
    DungeonController controller = new Controller(input, log);
    controller.play(dungeon);
    System.out.println(log);

  }
}

