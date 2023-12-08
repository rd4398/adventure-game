package dungeon;

import common.Pungency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import location.Cave;
import location.Location;
import location.Locationv2;
import location.Tunnel;

import monsters.Otyugh;
import players.Directions;
import players.NewDescription;
import players.PlayerImpl;
import players.PlayerWithArrows;

/**
 * This class represents the extended version of the dungeon that consists of monsters and arrows.
 * It supports all the functionalities associated with the dungeon.
 */
public class DungeonMonsterImpl extends Dungeon implements DungeonWithMonster {
  private static final int MAX_ARROWS_AT_LOCATION = 5;
  private int rows;
  private int columns;
  private int interconnectivity;
  private int monsterCount;
  private boolean wrap;
  private int percentage;
  private int[] seed;

  /**
   * Construct the dungeon object using the necessary parameters in order to create the dungeon with
   * monsters.
   *
   * @param rows         the rows for the dungeon
   * @param columns      the columns for the dungeon
   * @param degree       the degree of interconnectivity
   * @param percentage   the percentage of treasure count
   * @param isWrap       whether the dungeon is wrapping or non-wrapping
   * @param monsterCount the count for monsters in the dungeon
   * @param seed         the seed to produce deterministic random values
   */
  public DungeonMonsterImpl(int rows, int columns, int degree, int percentage, boolean isWrap,
                            int monsterCount, int... seed) {

    super(seed);
    if (rows <= 0 || columns <= 0 || degree < 0 || percentage < 0 || monsterCount <= 0
            || monsterCount >= (rows * columns)) {
      throw new IllegalArgumentException("Invalid dungeon parameters");
    }
    this.rows = rows;
    this.columns = columns;
    this.interconnectivity = degree;
    this.percentage = percentage;
    this.monsterCount = monsterCount;
    this.wrap = isWrap;
    if (seed.length > 0) {
      this.seed = seed;
    }
    int count = 100;
    while (count > 0) {
      count--;
      createDungeon(rows, columns, degree, percentage, isWrap);
      if (getCaveCount() <= monsterCount) {
        continue;
      }
      addMonsterAtLocation(monsterCount);
      addArrowsAtLocation(percentage);
      break;
    }
    player = new PlayerImpl(player.getName(), player.getTreasureList(),
            player.getCurrentLocation());
  }

  /**
   * Helper method to add the monsters to the dungeon.
   *
   * @param mcount the number of monsters to be added
   */
  private void addMonsterAtLocation(int mcount) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].equals(end)) {
          ((Locationv2) grid[i][j]).addMonster(new Otyugh(100));
          ((Locationv2) end).addMonster(new Otyugh(100));
          break;
        }
      }
    }

    mcount--;
    Set<Location> visited = new HashSet<>();
    int r = grid.length;
    int c = grid[0].length;
    visited.add(end);
    while (mcount > 0) {
      int i = random.generateRandomNumber(0, r);
      int j = random.generateRandomNumber(0, c);
      if (visited.contains(grid[i][j])) {
        continue;
      }
      if (grid[i][j] instanceof Tunnel || grid[i][j].equals(player.getCurrentLocation())) {
        visited.add(grid[i][j]);
        continue;
      }
      visited.add(grid[i][j]);
      ((Locationv2) grid[i][j]).addMonster(new Otyugh(100));
      mcount--;
    }

  }

  /**
   * Helper method to add arrows at the location.
   *
   * @param percentage the percentage of locations containing the arrows
   */
  private void addArrowsAtLocation(int percentage) {
    Set<Location> visited = new HashSet<>();
    int r = grid.length;
    int c = grid[0].length;

    int arrowCount = (int) Math.ceil(percentage / (float) 100 * getCaveCount());
    while (arrowCount > 0) {
      int i = random.generateRandomNumber(0, r);
      int j = random.generateRandomNumber(0, c);
      if (visited.contains(grid[i][j])) {
        continue;
      }
      visited.add(grid[i][j]);
      ((Locationv2) grid[i][j]).addArrows(random
              .generateRandomNumber(1, MAX_ARROWS_AT_LOCATION));
      arrowCount--;
    }

  }

  /**
   * Helper method to count the number of caves in the dungeon.
   *
   * @return the cave count
   */
  private int getCaveCount() {
    int c = 0;
    for (Location[] locations : grid) {
      for (int j = 0; j < grid[0].length; j++) {
        if (locations[j] instanceof Cave) {
          c++;
        }
      }
    }
    return c;
  }

  @Override
  public boolean hasMonsterAtLocation() {
    Locationv2 temp = (Locationv2) player.getCurrentLocation();
    return temp.hasMonster();
  }

  @Override
  public boolean gameStatus() {
    return ((PlayerWithArrows) player).getHealth() == 0 || atEnd();
  }

  @Override
  public Pungency getSmellAtLocation() {
    int cnt = 0;
    List<Locationv2> locationList = new ArrayList<>();
    Locationv2 currLocation = (Locationv2) getPlayer().getCurrentLocation();
    List<Directions> temp = new ArrayList<>();
    temp.addAll(currLocation.getDirections());
    for (Directions directions : temp) {
      locationList.add((Locationv2) currLocation.getConnections(directions));
      if (((Locationv2) currLocation.getConnections(directions)).hasMonster()) {
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

  @Override
  public void movePlayerToLocation(Directions direction) {
    try {
      if (isPlayerAlive()) {
        player.movePlayer(direction);
        player.getCurrentLocation().setExplored();
      }
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Invalid direction");
    }
  }

  @Override
  public void collectTreasureAtLocation() {
    if (hasTreasureAtLocation()) {
      player.collectTreasure(player.getCurrentLocation().getTreasureList());
      player.getCurrentLocation().removeTreasure(player.getCurrentLocation().getTreasureList());
    } else {
      throw new IllegalStateException("Cannot collect treasure");
    }
  }

  @Override
  public void collectArrowsAtLocation() {
    if (hasArrowsAtLocation()) {
      ((PlayerImpl) player).collectArrows(((Locationv2) player.getCurrentLocation()).getArrows());
      ((Locationv2) player.getCurrentLocation()).removeArrows();

    } else {
      throw new IllegalStateException("Cannot pick arrows");

    }
  }

  @Override
  public NewDescription getDescription() {
    return (NewDescription) player.getDescription();
  }

  @Override
  public List<Directions> getNextMoves() {
    return player.getCurrentLocation().getDirections();
  }

  @Override
  public boolean shootArrowToLocation(Directions direction, int distance) {
    return ((PlayerImpl) player).shootArrow(direction, distance);
  }

  @Override
  public boolean hasArrowsAtLocation() {
    Locationv2 temp = (Locationv2) player.getCurrentLocation();
    return temp.hasArrows();
  }

  @Override
  public boolean hasTreasureAtLocation() {
    try {
      return player.getCurrentLocation().getTreasureList().size() > 0;
    } catch (IllegalStateException ise) {
      return false;
    }
  }

  @Override
  public boolean isPlayerAlive() {
    return ((PlayerImpl) player).isAlive();
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public int getColumns() {
    return columns;
  }

  @Override
  public int getInterconnectivity() {
    return interconnectivity;
  }

  @Override
  public int getPercentage() {
    return percentage;
  }

  @Override
  public int getMonstersCount() {
    return monsterCount;
  }

  @Override
  public boolean getWrapStatus() {
    return wrap;
  }

  @Override
  public int[] getSeed() {
    return seed;
  }
}
