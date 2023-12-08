package location;

import common.Treasure;

import java.util.List;
import java.util.Map;

import players.Directions;

/**
 * This class is used to get the description of a particular location in the dungeon.
 * This information will be passed to the driver for printing.
 */
public class LocationDescription {
  protected final int id;
  protected final Map<Directions, Location> connections;
  protected final List<Treasure> treasureList;
  protected final String locationType;

  /**
   * This method is used to get the description of location id for a particular location.
   *
   * @return location id
   */
  public int getId() {
    return id;
  }

  /**
   * This method is used to get the description of connections for a location.
   *
   * @return the connections
   */
  public Map<Directions, Location> getConnections() {
    return connections;
  }

  /**
   * This method is used to get the description of treasure in the location. Here I am already
   * getting a copy of treasure list which is being processed for display. Hence, I am returning
   * shallow copy.
   *
   * @return the treasure list
   */
  public List<Treasure> getTreasureList() {
    return treasureList;
  }

  /**
   * Construct the location description object in order to send it to the driver for display.
   *
   * @param id           the location id
   * @param connections  connections of location
   * @param treasureList treasure contained in the location
   */
  public LocationDescription(int id, Map<Directions, Location> connections,
                             List<Treasure> treasureList, String locationType) {
    this.id = id;
    this.connections = connections;
    this.treasureList = treasureList;
    this.locationType = locationType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nLocation id :").append(this.id);
    sb.append("\nLocation type :").append(this.locationType);
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
