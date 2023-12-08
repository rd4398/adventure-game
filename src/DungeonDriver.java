import java.io.BufferedReader;
import java.io.InputStreamReader;


import controller.Controller;
import controller.DungeonController;
import controller.GameController;
import controller.IGameController;
import dungeon.DungeonMonsterImpl;
import dungeon.DungeonWithMonster;
import view.GameFrame;
import view.IGameFrame;


/**
 * This class represents the driver class of the dungeon model.
 */
public class DungeonDriver {
  /**
   * This is the main method and the game starts from this method.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    int rows = 0;
    int columns = 0;
    int interconnectivity = 0;
    boolean wrapping = false;
    int treasure = 0;
    int monster = 0;
    if (args.length > 0) {
      try {
        for (int i = 0; i < args.length; i += 2) {
          switch (args[i]) {
            case "-rows":
              rows = Integer.parseInt(args[i + 1]);
              break;
            case "-columns":
              columns = Integer.parseInt(args[i + 1]);
              break;
            case "-interconnectivity":
              interconnectivity = Integer.parseInt(args[i + 1]);
              break;
            case "-wrapping":
              wrapping = Boolean.parseBoolean(args[i + 1]);
              break;
            case "-treasure":
              treasure = Integer.parseInt(args[i + 1]);
              break;
            case "-monsters":
              monster = Integer.parseInt(args[i + 1]);
              break;
            default:
              System.out.println("Invalid format of input!");
              System.out.println("Please enter arguments in the format: "
                      + "java - jar Project4-DungeonWithController.jar -rows 6 -columns 8 "
                      + "-interconnectivity 1 -wrapping true -treasure 20 -monsters 2");
              break;
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("Please check input format");
      }
      DungeonWithMonster model = new DungeonMonsterImpl(rows, columns, interconnectivity, treasure,
              wrapping, monster);
      DungeonController controller = new Controller(
              new BufferedReader(new InputStreamReader(System.in)), System.out);

      controller.play(model);
    } else {

      DungeonWithMonster model = new DungeonMonsterImpl(10, 10, 1, 60,
              false, 2, 6);
      IGameController controller = new GameController(model, 6);
      IGameFrame view = new GameFrame(controller);
      controller.setView(view);
    }

  }
}
