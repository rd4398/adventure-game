package weapons;

/**
 * This class represents the arrow as a weapon which player uses to kill the otyugh during the
 * traversal in the dungeon.
 */
public class Arrow implements Weapon {
  private static final int DEFAULT_DAMAGE = 50;
  private final int damage;

  /**
   * Construct the arrow object with default damage.
   */
  public Arrow() {
    damage = DEFAULT_DAMAGE;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
}
