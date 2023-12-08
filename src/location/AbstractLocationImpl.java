package location;

import common.Pungency;

import java.util.ArrayList;
import java.util.List;

import monsters.Monster;
import players.Directions;
import weapons.Arrow;
import weapons.Weapon;

/**
 * This class represents an abstract class for locations which consists of the updated functionality
 * for the locations along with the previous functionality.
 */
public abstract class AbstractLocationImpl extends AbstractLocation implements Locationv2 {
  protected Monster monster;
  protected List<Weapon> arrows;

  /**
   * Construct the updated abstract location object using the required parameters.
   *
   * @param monster the monster to be added
   * @param arrows  the arrows to be added at a location
   */
  public AbstractLocationImpl(Monster monster, List<Weapon> arrows) {
    super();
    this.monster = monster;
    if (arrows == null) {
      this.arrows = new ArrayList<>();
    } else {
      this.arrows = arrows;
    }
  }

  @Override
  public boolean hasMonster() {
    return monster != null;
  }

  @Override
  public boolean hasArrows() {
    return arrows.size() > 0;
  }

  @Override
  public List<Weapon> getArrows() {
    return arrows;
  }

  @Override
  public void killMonster() {
    monster.updateHealth(50);
    if (monster.getHealth() == 0) {
      monster = null;
    }
  }

  @Override
  public void addArrows(int n) {
    for (int i = 0; i < n; i++) {
      arrows.add(new Arrow());
    }
  }

  @Override
  public void removeArrows() {
    arrows.clear();
  }

  @Override
  public Pungency getSmell() {
    int cnt = 0;
    List<Locationv2> locationList = new ArrayList<>();
    List<Directions> temp = new ArrayList<>();
    temp.addAll(getDirections());
    for (Directions directions : temp) {
      locationList.add((Locationv2) getConnections(directions));
      if (((Locationv2) getConnections(directions)).hasMonster()) {
        cnt += 2;
      }
    }
    for (Locationv2 locationv2 : locationList) {
      for (Directions directions : locationv2.getDirections()) {
        if (((Locationv2) locationv2.getConnections(directions)).hasMonster()) {
          cnt++;
        }
      }
    }
    switch (cnt) {
      case 1:
        return Pungency.WEAK;
      case 2:
        return Pungency.STRONG;
      default:
        return Pungency.NOSMELL;
    }
  }
}
