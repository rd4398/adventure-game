package weapons;

/**
 * This interface represents weapon that a player can carry during traversal in the dungeon.
 */
public interface Weapon {
  /**
   * This method is used to get the damage done by the weapon.
   *
   * @return the damage done by the weapon
   */
  int getDamage();
}
