package players;

import java.util.List;

import weapons.Weapon;

/**
 * This interface represents a player with arrows that is going to traverse the dungeon in the game.
 */
public interface PlayerWithArrows extends Players {
  /**
   * This method is used to determine whether player has arrows or not.
   *
   * @return true if player has atleast one arrow
   *         false if player has zero arrows
   */
  boolean hasArrows();

  /**
   * This method is used to determine the number of arrows that a player has.
   *
   * @return the count of arrows
   */
  int arrowCount();

  /**
   * This method is used to get the health of the player during the game.
   *
   * @return the health of the player.
   */
  int getHealth();

  /**
   * This method allows the player to shoot an arrow in order to attack Otyugh.
   *
   * @param direction the direction at which the arrow needs to be shot.
   * @param distance  the distance or range that the arrow will travel.
   * @return true if arrow hits the monster
   *         false if arrow misses the monster
   */
  boolean shootArrow(Directions direction, int distance);

  /**
   * This method allows the player to collect arrows from the current location while traversing
   * the dungeon.
   *
   * @param arrows the list of arrows
   */
  void collectArrows(List<Weapon> arrows);

  /**
   * This method is used to get the updated description of player and location while the player is
   * traversing the dungeon.
   *
   * @return the description
   */
  NewDescription getDescription();

  /**
   * This method is used by the monster to kill the player and reduce the health of player to zero.
   */
  void killPlayer();

  /**
   * This method is used to determine whether the player is alive or been eaten by monster.
   *
   * @return true if player is alive
   *         false if player is dead
   */
  boolean isAlive();
}
