package monsters;

/**
 * This class represents the smelly creatures named Otyugh which live in the dungeon and can kill
 * the player.
 */
public class Otyugh implements Monster {
  private int health;

  /**
   * Construct the Otyugh object and initialize its health.
   *
   * @param health the health of monster
   */
  public Otyugh(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("Health of monster cannot be negative");
    }
    this.health = health;
  }

  @Override
  public boolean isDead() {
    return health == 0;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public void updateHealth(int value) {
    health = health - value;
    if (health < 0) {
      health = 0;
    }
  }

  @Override
  public boolean isInjured() {
    return health > 0 && health < 100;
  }
}
