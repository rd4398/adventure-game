package players;

import common.Treasure;

import java.util.List;

import location.Cave;
import location.Location;

/**
 * The players interface which consists of all the operations related to a player entering a
 * dungeon to start the game.
 */
public interface Players {
  /**
   * This method is used to move the player from one location to another from the possible set of
   * locations. The location can be a cave or tunnel based on direction the player selects.
   *
   * @param direction the direction in which the player wants to move
   */
  void movePlayer(Directions direction);

  /**
   * This method will be used by the player to collect the treasure from a location in dungeon
   * during the game.
   *
   * @param treasure the treasure that will be collected by the player
   */
  void collectTreasure(List<Treasure> treasure);

  /**
   * This method will be used to get the description of the player as the game progresses. The
   * description will include the treasure collected and moves of the player.
   */
  Description getDescription();

  /**
   * This method will be used by the player to start the game. The player will enter the cave that
   * is randomly picked as start.
   *
   * @param cave the cave which acts as starting point for the player in the game
   */
  void enterStart(Cave cave);

  /**
   * This method is used to get the current location of the player.
   *
   * @return the location of player
   */
  Location getCurrentLocation();

  /**
   * This method is used to get the list of treasure collected by the player.
   *
   * @return the treasure list
   */
  List<Treasure> getTreasureList();

  /**
   * This method is used to access the name of the player.
   *
   * @return the name of the player.
   */
  String getName();

}
