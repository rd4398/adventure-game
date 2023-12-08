package location;

import common.Treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import monsters.Monster;


/**
 * This class represents a cave as a location in the dungeon where a player can arrive.
 */
public class Cave extends AbstractLocationImpl {
  private final List<Treasure> treasureList;

  /**
   * This is the empty constructor for the cave which will be used to reset the cave.
   */
  public Cave() {
    super(null, null);
    treasureList = new ArrayList<>();
  }

  /**
   * This is the Cave constructor that initializes the Cave in a dungeon with treasure list.
   *
   * @param treasureList the list of treasure which the cave contains
   */
  public Cave(List<Treasure> treasureList) {
    super(null, null);
    this.treasureList = treasureList;
  }

  @Override
  public void addMonster(Monster m) {
    monster = m;
  }

  @Override
  public boolean isMonsterInjured() {
    return monster.isInjured();
  }

  @Override
  public boolean hasTreasure() {
    return treasureList.size() > 0;
  }

  @Override
  public void addTreasure(Treasure treasure) {
    this.treasureList.add(treasure);
  }

  @Override
  public void removeTreasure(List<Treasure> treasure) {
    for (int i = 0; i < treasure.size(); i++) {
      this.treasureList.remove(treasure.get(i));
    }
  }

  /**
   * Since the treasure consists of ENUM, it is going to be constant throughout the program. Thus
   * only shallow copy is returned.
   *
   * @return the treasure list
   */
  @Override
  public List<Treasure> getTreasureList() {
    List<Treasure> copyTreasure = new ArrayList<>();
    for (Treasure treasure : this.treasureList) {
      copyTreasure.add(treasure);
    }
    return copyTreasure;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cave)) {
      return false;
    }
    Cave other = (Cave) o;
    return this.id == other.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public LocationDescription getDescription() {
    return new NewLocationDescription(this.id, this.connections, this.treasureList,
            "Cave", arrows);
  }
}
