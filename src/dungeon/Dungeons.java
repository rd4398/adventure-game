package dungeon;

import location.Location;
import players.Player;

/**
 * Dungeon interface that contains all the operations which a general dungeon can have.
 */
public interface Dungeons {
  /**
   * This method will be used to create a wrapping dungeon for the game.
   *
   * @param rows       number of rows for the dungeon
   * @param columns    number of columns for the dungeon
   * @param degree     degree of interconnectivity
   * @param percentage percentage of treasure
   * @param isWrap     flag for creating wrapping or non wrapping dungeon
   */
  void createDungeon(int rows, int columns, int degree, int percentage, boolean isWrap);

  /**
   * This method will be used to get the dungeon after it is created.
   *
   * @return the created dungeon
   */
  Location[][] getGrid();

  /**
   * This method will be used to mark the end point for the player in the dungeon.
   *
   * @param cave the cave which will act as ending point
   */
  void markEnd(Location cave);

  /**
   * This method will check whether the player has reached the cave that is randomly marked end.
   * The game ends when the player reaches this cave.
   *
   * @return true if the location of player matches location of cave marked as end
   *         false if the location of player does not match the location of cave marked as end
   */
  boolean atEnd();

  /**
   * This method is used to get the player from the dungeon.
   *
   * @return the player
   */
  Player getPlayer();
}
