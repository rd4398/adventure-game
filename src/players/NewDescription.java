package players;

import common.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import location.Locationv2;
import weapons.Weapon;

/**
 * This class represents the updated description of player and location along with the existing old
 * description.
 */
public class NewDescription extends Description {
  private List<Weapon> arrows;

  /**
   * Construct the new description object using the required parameters.
   *
   * @param name         name of the player
   * @param treasureList the list of treasure
   * @param location     the location of player
   * @param arrows       the number of arrows player has
   */
  public NewDescription(String name, List<Treasure> treasureList, Locationv2 location,
                        List<Weapon> arrows) {
    super(name, treasureList, location);
    if (arrows == null) {
      throw new IllegalArgumentException("treasure list cannot be null");
    }
    this.arrows = arrows;
  }

  /**
   * This method is used to get the arrows that player has. Since there is no fields in Arrow class
   * to mutate, I am returning shallow copy.
   *
   * @return the list of arrows
   */
  public List<Weapon> getArrows() {
    return new ArrayList<>(arrows);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(getName());
    sb.append("\nArrow Count :").append(getArrows().size());
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
