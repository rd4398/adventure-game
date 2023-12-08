package players;

import common.Randomizer;
import common.Treasure;

import java.util.ArrayList;
import java.util.List;

import location.Location;
import location.Locationv2;
import location.Tunnel;
import weapons.Arrow;
import weapons.Weapon;

/**
 * This class represents the player with arrows that traverses the dungeon. It has all the
 * capabilities of player without arrows since it extends the class. It also supports additional
 * functionality related to arrows.
 */
public class PlayerImpl extends Player implements PlayerWithArrows {
  private static final int DEFAULT_HEALTH = 100;
  private int health;
  private static int INITIAL_ARROW_COUNT = 3;
  private List<Weapon> arrows;
  private Randomizer random;

  /**
   * Construct the player with arrows object using the necessary parameters.
   *
   * @param name         name of the player
   * @param treasureList the list of treasure the player collects
   * @param location     the current location of the player
   */
  public PlayerImpl(String name, List<Treasure> treasureList, Location location) {
    super(name, treasureList, location);
    arrows = new ArrayList<>();
    arrows.add(new Arrow());
    arrows.add(new Arrow());
    arrows.add(new Arrow());
    health = DEFAULT_HEALTH;
    random = new Randomizer();
  }

  @Override
  public boolean hasArrows() {
    return arrows.size() > 0;
  }

  @Override
  public int arrowCount() {
    return arrows.size();
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public boolean shootArrow(Directions direction, int distance) {
    if (distance < 0) {
      throw new IllegalArgumentException("Distance to shoot cannot be zero");
    }
    if (arrows.size() == 0) {
      throw new IllegalStateException("No arrows to shoot");
    }
    arrows.remove(0);
    Locationv2 arrowLocation = (Locationv2) location;
    if (!arrowLocation.getDirections().contains(direction)) {
      return false;
    }
    while (distance > 0 || arrowLocation instanceof Tunnel) {
      if (arrowLocation instanceof Tunnel) {
        for (Directions dir : arrowLocation.getDirections()) {
          if (dir != Directions.getOppositeDirection(direction)) {
            arrowLocation = (Locationv2) arrowLocation.getConnections(dir);
            direction = dir;
            break;
          }
        }
        continue;
      }
      if (arrowLocation.getDirections().contains(direction)) {
        arrowLocation = (Locationv2) arrowLocation.getConnections(direction);
        distance--;
      } else {
        return false;
      }
    }
    if (arrowLocation.hasMonster()) {
      arrowLocation.killMonster();
      return true;
    }
    return false;
  }

  @Override
  public void movePlayer(Directions direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    if (getCurrentLocation().getDirections().contains(direction)) {
      this.location = this.location.getConnections(direction);
      if (((Locationv2) location).hasMonster()) {
        if (((Locationv2) location).isMonsterInjured()) {
          int r = random.generateRandomNumber(0, 1);
          if (r == 1) {
            health = 0;
          }
        } else {
          health = 0;
        }
      }
    } else {
      throw new IllegalArgumentException("Invalid direction");
    }
  }

  @Override
  public void collectArrows(List<Weapon> arrow) {
    for (Weapon weapon : arrow) {
      if (weapon == null) {
        throw new IllegalArgumentException("Arrow cannot be empty or null");
      }
    }
    arrows.addAll(arrow);
  }

  @Override
  public NewDescription getDescription() {
    return new NewDescription(name, treasureList, (Locationv2) location, arrows);
  }

  @Override
  public void killPlayer() {
    this.health = 0;
  }

  @Override
  public boolean isAlive() {
    return this.health > 0;
  }

}
