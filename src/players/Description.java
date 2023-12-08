package players;

import common.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import location.Location;

/**
 * This class is used to represent the description of player and location as it moves around in the
 * dungeon during the game.
 */
public class Description {
  protected final String name;
  protected final List<Treasure> treasureList;
  protected final Location location;

  /**
   * Constructs the player description object by initializing name, treasure list and location.
   *
   * @param name         name of the player
   * @param treasureList the list of treasure collected by the player
   * @param location     the location of the player
   */
  public Description(String name, List<Treasure> treasureList, Location location) {
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

  /**
   * This method is used to get the description of player name.
   *
   * @return the name of player
   */
  public String getName() {
    return name;
  }

  /**
   * This method is used to get the description of treasure collected by player. Here I am already
   * getting a copy of treasure list which is being processed for display. Hence, I am returning
   * shallow copy.
   *
   * @return the treasure collected by player
   */
  public List<Treasure> getTreasureList() {

    return new ArrayList<>(treasureList);
  }

  /**
   * This method is used to get the description of player location.
   *
   * @return the location description for player
   */
  public Location getLocation() {
    return location;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(getName());
    if (getTreasureList().size() > 0) {
      sb.append("\nTreasure Collected :");
      Map<Treasure, Integer> temp = new HashMap<>();
      for (int i = 0; i < getTreasureList().size(); i++) {
        if (temp.containsKey(getTreasureList().get(i))) {
          temp.put(getTreasureList().get(i), temp.get(getTreasureList().get(i)) + 1);
        } else {
          temp.put(getTreasureList().get(i), 1);
        }
      }
      for (Treasure treasure : temp.keySet()) {
        sb.append("\n");
        sb.append(treasure.getName().toString()).append(": ").append(temp.get(treasure));
      }
    } else {
      sb.append("\nNo treasure collected yet! ");
    }
    sb.append("\nLocation Description :");
    sb.append(getLocation().getDescription().toString());
    return sb.toString();
  }
}
