package monsters;

/**
 * This is the monster interface which represents the monsters in our adventure game.
 * This interface specifies general functionalities that a monster can have.
 */
public interface Monster {
  /**
   * This method is used to determine whether the monster is alive or dead.
   *
   * @return true if monster is dead
   *         false if monster is still alive
   */
  boolean isDead();

  /**
   * This method is used to get the health of the monster.
   *
   * @return health of the monster
   */
  int getHealth();

  /**
   * This method is used to update the health of the monster after the player shoots him with
   * the arrows.
   */
  void updateHealth(int value);

  /**
   * This method is used to determine whether the monster is injured or not after being hit by
   * arrow.
   *
   * @return true if monster is injured
   *         false if monster is not injured
   */
  boolean isInjured();
}
