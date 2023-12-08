package location;

import common.Treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import monsters.Monster;
import players.Directions;

/**
 * This class represents the Tunnel as a location in Dungeon.
 */
public class Tunnel extends AbstractLocationImpl {
  /**
   * Construct the tunnel object during dungeon creation.
   *
   * @param id          the id of location
   * @param connections the connections of the tunnel
   */
  public Tunnel(int id, Map<Directions, Location> connections) {
    super(null, null);
    this.id = id;
    this.connections = connections;
  }

  /**
   * This is the empty constructor for the tunnel which will be used to reset the tunnel.
   */
  public Tunnel() {
    super(null, null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tunnel)) {
      return false;
    }
    Tunnel other = (Tunnel) o;
    return this.id == other.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public LocationDescription getDescription() {
    return new NewLocationDescription(this.id, this.connections, new ArrayList<>(),
            "Tunnel", arrows);
  }


  @Override
  public List<Treasure> getTreasureList() {
    throw new IllegalStateException("A tunnel cannot contain treasure");
  }

  @Override
  public void addTreasure(Treasure treasure) {
    throw new IllegalStateException("The treasure cannot be added to the tunnel as tunnel "
            + "cannot contain any treasure.");
  }

  @Override
  public void removeTreasure(List<Treasure> treasure) {
    throw new IllegalStateException("The treasure cannot be removed from the tunnel as tunnel "
            + "cannot contain any treasure.");
  }

  @Override
  public void addMonster(Monster m) {
    throw new IllegalStateException("Tunnel cannot have monster");
  }

  @Override
  public boolean isMonsterInjured() {
    throw new IllegalStateException("There cannot be a monster in tunnel");
  }

  @Override
  public boolean hasTreasure() {
    return false;
  }
}
