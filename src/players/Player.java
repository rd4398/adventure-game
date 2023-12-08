package players;

import common.Treasure;

import java.util.ArrayList;
import java.util.List;

import location.Cave;
import location.Location;

/**
 * This class represents all the operations that a player can perform during the game.
 */
public class Player implements Players {
  protected final String name;
  protected final List<Treasure> treasureList;
  protected Location location;

  /**
   * Constructs the player object by initializing name, treasure list and location.
   *
   * @param name         name of the player
   * @param treasureList the list of treasure collected by the player
   * @param location     the location of the player
   */
  public Player(String name, List<Treasure> treasureList, Location location) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    if (treasureList == null) {
      throw new IllegalArgumentException("treasure list cannot be null");
    }
    this.name = name;
    this.treasureList = treasureList;
    this.location = location;
  }

  @Override
  public void movePlayer(Directions direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    this.location = this.location.getConnections(direction);
  }

  @Override
  public void collectTreasure(List<Treasure> treasure) {
    for (Treasure value : treasure) {
      if (value == null) {
        throw new IllegalArgumentException("Treasure cannot be empty or null");
      }
      treasureList.add(value);
    }
  }

  @Override
  public Description getDescription() {
    return new Description(this.name, this.treasureList, this.location);
  }

  @Override
  public void enterStart(Cave startCave) {
    if (startCave == null) {
      throw new IllegalArgumentException("Staring cave cannot be null");
    }
    this.location = startCave;
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Location getCurrentLocation() {
    return this.location;
  }

  @Override
  public List<Treasure> getTreasureList() {
    List<Treasure> copy = new ArrayList<>();
    for (Treasure treasure : this.treasureList) {
      copy.add(treasure);
    }
    return copy;
  }
}
