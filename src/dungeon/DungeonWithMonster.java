package dungeon;

import common.Pungency;

import java.util.List;

import players.Directions;
import players.NewDescription;

/**
 * This interface represents the dungeon with monsters and is used in the adventure game.
 * It extends the dungeons interface and supports operations related to dungeon.
 */
public interface DungeonWithMonster extends Dungeons {
  /**
   * This method is used to determine whether there is monster present at a location in the dungeon.
   *
   * @return true if monster is present
   *         false if monster is not present
   */
  boolean hasMonsterAtLocation();

  /**
   * This method is used to find the status of the adventure game whether it is ended or not.
   *
   * @return true if game ends
   *         false if game does not end
   */
  boolean gameStatus();

  /**
   * This method is used to get the level of smell at a particular location in the dungeon. It will
   * be used by the player to sense the presence of monster nearby if it is present. It can be
   * STRONG, WEAK or NOSMELL.
   *
   * @return the level of smell a particular location can have.
   */
  Pungency getSmellAtLocation();

  /**
   * This method is used to move the player in a particular direction as per the direction.
   *
   * @param direction the direction in which the player needs to move.
   */
  void movePlayerToLocation(Directions direction);

  /**
   * This method is used to collect the treasure at a particular location during the traversal.
   */
  void collectTreasureAtLocation();

  /**
   * This method is used to collect the arrows at a particular location while traversing the
   * dungeon.
   */
  void collectArrowsAtLocation();

  /**
   * This method is used to get the description of player and location in a structured format in
   * order to display it to the view.
   *
   * @return the description of the player and location
   */
  NewDescription getDescription();

  /**
   * This method is used to get the list of next available moves for the player while traversing the
   * dungeon.
   *
   * @return the list of next moves for the player in order to move forward in the dungeon
   */
  List<Directions> getNextMoves();

  /**
   * This method is used to shoot the arrow to a particular location in order to kill the monster.
   *
   * @param direction the direction at which the arrow is shot.
   * @param distance  the distance for the arrow to travel
   * @return true if arrow hits the monster
   *         false if arrow misses the monster
   */
  boolean shootArrowToLocation(Directions direction, int distance);

  /**
   * This method is used to check whether there are arrows present at the location.
   *
   * @return true if arrows are present at the location
   *         false if arrows are not present at the location
   */
  boolean hasArrowsAtLocation();

  /**
   * This method is used to check whether the treasure is present at a particular location.
   *
   * @return true if the treasure is present at the location
   *         false if the treasure is not present at the location
   */
  boolean hasTreasureAtLocation();

  /**
   * This method is used to check whether the player is alive or is being killed by the monster.
   *
   * @return true if the player is alive
   *         false if player is dead
   */
  boolean isPlayerAlive();

  /**
   * This method is used to get the rows for the dungeon that is created.
   *
   * @return the rows of the dungeon
   */
  int getRows();

  /**
   * This method is used to return the columns for the dungeon that is created.
   *
   * @return the columns for the dungeon
   */
  int getColumns();

  /**
   * This method is used to get the degree of interconnectivity for the dungeon that is created.
   *
   * @return the degree of interconnectivity
   */
  int getInterconnectivity();

  /**
   * This method is used to get the percentage of locations having treasure and arrows from the
   * created dungeon.
   *
   * @return the percentage
   */
  int getPercentage();

  /**
   * This method is used to get the monster count for the dungeon.
   *
   * @return the number of monsters present in the dungeon
   */
  int getMonstersCount();

  /**
   * This method is used to get whether the dungeon is wrapped or not.
   *
   * @return true if dungeon is wrapping
   *         false if dungeon is non wrapping
   */
  boolean getWrapStatus();

  /**
   * This method is used to get the seed used for the creation of dungeon.
   *
   * @return the seed for the dungeon
   */
  int[] getSeed();
}
