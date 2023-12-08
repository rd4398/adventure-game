package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import players.Directions;

/**
 * This class represents the location as an abstract class and contains all the common
 * functionalities related to Cave and Tunnel.
 */
public abstract class AbstractLocation implements Location {
  protected int id;
  protected boolean explored;
  protected Map<Directions, Location> connections;
  static int count = 0;

  /**
   * Construct the abstract location abject and initialize the location with a unique id.
   */
  protected AbstractLocation() {
    count++;
    this.id = count;
    connections = new HashMap<>();
    explored = false;
  }

  @Override
  public Location getConnections(Directions direction) {
    return connections.get(direction);
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void connect(Location loc, Directions direction) {
    connections.put(direction, loc);
  }

  @Override
  public List<Location> getNeighbors() {
    return new ArrayList<>(connections.values());
  }

  @Override
  public List<Directions> getDirections() {
    return new ArrayList<>(connections.keySet());
  }

  @Override
  public abstract LocationDescription getDescription();

  public Map<Directions, Location> getMap() {
    return this.connections;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID -> ").append(this.id);
    return sb.toString();
  }

  @Override
  public void setExplored() {
    this.explored = true;
  }

  @Override
  public boolean getExploredStatus() {
    return this.explored;
  }
}
