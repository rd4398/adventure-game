package location;

import common.Treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import players.Directions;
import weapons.Weapon;

/**
 * This class represents the updated location description for tunnel and cave by extending the old
 * description.
 */
public class NewLocationDescription extends LocationDescription {
  private List<Weapon> arrows;

  /**
   * Construct the new location description object using the necessary parameters.
   *
   * @param id           the id of location
   * @param connections  the connections for a location
   * @param treasureList the list of treasure present at a location
   * @param locationType the type of location cave / tunnel
   * @param arrows       the arrows present at the location
   */
  public NewLocationDescription(int id, Map<Directions, Location> connections,
                                List<Treasure> treasureList, String locationType,
                                List<Weapon> arrows) {
    super(id, connections, treasureList, locationType);
    this.arrows = arrows;
  }

  /**
   * This method is used to get the list of arrows present at a location.
   *
   * @return the list of arrows
   */
  public List<Weapon> getArrows() {
    return new ArrayList<>(this.arrows);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nLocation id :").append(this.id);
    sb.append("\nLocation type :").append(this.locationType);
    sb.append("\nArrows at location ").append(arrows.size());
    sb.append("\nThe connections for the location are :");
    for (Directions directions : getConnections().keySet()) {
      sb.append("\n").append(directions).append(": ").append(getConnections().get(directions));
    }
    if (treasureList.size() > 0) {
      sb.append("\nThe treasure at the location is");
      for (Treasure treasure : treasureList) {
        sb.append("\n").append(treasure.getName().toString());
      }
    } else {
      sb.append("\nNo treasure in this location");
    }
    return sb.toString();
  }
}
