package location;

import java.util.List;

import monsters.Monster;
import weapons.Weapon;

/**
 * This interface represents the updated location in the dungeon which now consists of monster,
 * arrows. It also supports all the previous functionalities of a location in the dungeon.
 */
public interface Locationv2 extends Location {
  /**
   * This method is used to determine whether the monster is present at a current location or not.
   *
   * @return true if monster is present at the location
   *         false if monster is not present at the location.
   */
  boolean hasMonster();

  /**
   * This method is used to determine whether the location has arrows that the player can pick
   * while traversing the dungeon.
   *
   * @return true if arrows are found.
   *         false if arrows are not found.
   */
  boolean hasArrows();

  /**
   * This method is used to get the number of Arrows present in a location.
   *
   * @return the number of arrows
   */
  List<Weapon> getArrows();

  /**
   * This method is used to add arrows to the particular location in the dungeon.
   * The location can be a tunnel or a cave.
   *
   * @param count the number of arrows to be added
   */
  void addArrows(int count);

  /**
   * This method adds a monster to the location.
   *
   * @param m the monster to be added
   */
  void addMonster(Monster m);

  /**
   * This method is used to kill the monster at a particular location.
   */
  void killMonster();

  /**
   * This method is used to check whether the monster is injured or not.
   *
   * @return true if monster is injured
   *         false if monster is not injured
   */
  boolean isMonsterInjured();

  /**
   * This method is used to determine whether the location has treasure or not.
   *
   * @return true if location has treasure
   *         false if location does not have treasure
   */
  boolean hasTreasure();

  /**
   * This method is used to remove arrows at a particular location after player has picked them.
   */
  void removeArrows();
}
