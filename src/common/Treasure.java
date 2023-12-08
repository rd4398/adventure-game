package common;

import java.util.Objects;


/**
 * This class represents the treasure that the player will collect from the caves in the dungeon.
 * The treasure consists of diamonds, rubies and sapphires.
 */
public class Treasure {
  private final Jewels name;

  /**
   * Construct a treasure object which will be placed in a cave for a player to collect.
   *
   * @param name name of treasure - Eg. Diamonds.
   */
  public Treasure(Jewels name) {
    if (name == null) {
      throw new IllegalArgumentException("Name of treasure cannot be empty");
    }
    this.name = name;
  }

  /**
   * This method is used to get the name of the treasure type.
   *
   * @return the name
   */
  public Jewels getName() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Treasure)) {
      return false;
    }
    Treasure other = (Treasure) o;
    return Objects.equals(this.name, other.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

}
