package location;

import common.Pungency;
import common.Treasure;

import java.util.List;

import players.Directions;

/**
 * Location interface that contains the operations related to cave and tunnel in a dungeon.
 */
public interface Location {
  /**
   * This method is used to get the connection for a location based on the direction provided.
   *
   * @param direction the direction at which connection is required
   * @return the location as per the direction
   */
  Location getConnections(Directions direction);

  /**
   * This method will be used to get the unique id of a cave or tunnel.
   *
   * @return the id of location
   */
  int getId();

  /**
   * This method will be used to connect one location to other. The locations can be two caves,
   * two tunnels or a cave and tunnel.
   *
   * @param loc       the location to be connected
   * @param direction the direction of location
   */
  void connect(Location loc, Directions direction);

  /**
   * This method will be used to access the neighbouring locations for a given location.
   * The neighbor can be a cave or a tunnel.
   *
   * @return list of neighboring locations.
   */
  List<Location> getNeighbors();

  /**
   * This method will be used to access the available directions for the player to make a move.
   * The directions can be NORTH, SOUTH, EAST or WEST.
   *
   * @return the list of available directions
   */
  List<Directions> getDirections();

  /**
   * This method will be used to get the description of a particular location.
   *
   * @return the description of a location
   */
  LocationDescription getDescription();

  /**
   * This method is used to get the treasure list present in the cave.
   *
   * @return the treasure list
   */
  List<Treasure> getTreasureList();

  /**
   * This method is used to add treasure including Diamonds, Rubies and Sapphires to the cave after
   * the creation of Dungeon.
   *
   * @param treasure the treasure to be added
   */
  void addTreasure(Treasure treasure);

  /**
   * This method is used to remove treasure including Diamonds, Rubies and Sapphires from cave
   * after a player collects it.
   *
   * @param treasure the treasure to be added
   */
  void removeTreasure(List<Treasure> treasure);

  /**
   * This method is used to get smell at particular location of the dungeon. The smell can be
   * NOSMELL, WEAK or STRONG.
   *
   * @return the spell in the particular location.
   */
  Pungency getSmell();

  /**
   * This method is used to mark the location as explored after the player visits it during the
   * game.
   */
  void setExplored();

  /**
   * This method is used to get the status of location whether it is explored or not.
   *
   * @return true if location is explored
   *         false if location is not explored
   */
  boolean getExploredStatus();
}
