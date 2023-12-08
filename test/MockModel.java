import common.Pungency;

import java.util.ArrayList;
import java.util.List;

import dungeon.DungeonWithMonster;
import location.Cave;
import location.Location;
import location.Locationv2;
import players.Directions;
import players.NewDescription;
import players.Player;

/**
 * This class acts as a mock model which will be used to test the controller functionality
 * independent of the actual model.
 */
public class MockModel implements DungeonWithMonster {
  private final StringBuffer log;
  Locationv2 cave;

  /**
   * Construct the mock model object in order to test the functionality of the controller.
   *
   * @param log log the controller data.
   */
  MockModel(StringBuffer log) {
    this.log = log;
    cave = new Cave();
  }

  @Override
  public boolean hasMonsterAtLocation() {
    return false;
  }

  @Override
  public boolean gameStatus() {
    return false;
  }

  @Override
  public Pungency getSmellAtLocation() {
    return Pungency.NOSMELL;
  }

  @Override
  public void movePlayerToLocation(Directions direction) {
    log.append("Move command called");
  }

  @Override
  public void collectTreasureAtLocation() {
    log.append("Collect treasure command called");
  }

  @Override
  public void collectArrowsAtLocation() {
    log.append("Collect arrows command called");
  }

  @Override
  public NewDescription getDescription() {
    return new NewDescription("abc", new ArrayList<>(), cave, new ArrayList<>());
  }

  @Override
  public List<Directions> getNextMoves() {
    List<Directions> temp = new ArrayList<>();
    temp.add(Directions.EAST);
    return temp;
  }

  @Override
  public boolean shootArrowToLocation(Directions direction, int distance) {
    log.append("Shoot arrow called");
    return false;
  }

  @Override
  public boolean hasArrowsAtLocation() {
    log.append("Inside has arrows\n");
    return false;
  }

  @Override
  public boolean hasTreasureAtLocation() {
    log.append("Inside has treasure\n");
    return false;
  }

  @Override
  public boolean isPlayerAlive() {
    return false;
  }

  @Override
  public int getRows() {
    return 0;
  }

  @Override
  public int getColumns() {
    return 0;
  }

  @Override
  public int getInterconnectivity() {
    return 0;
  }

  @Override
  public int getPercentage() {
    return 0;
  }

  @Override
  public int getMonstersCount() {
    return 0;
  }

  @Override
  public boolean getWrapStatus() {
    return false;
  }

  @Override
  public int[] getSeed() {
    return new int[0];
  }

  @Override
  public void createDungeon(int rows, int columns, int degree, int percentage, boolean isWrap) {
    log.append("Create Dungeon called");
  }

  @Override
  public Location[][] getGrid() {
    return new Location[0][];
  }

  @Override
  public void markEnd(Location cave) {
    log.append("Mark end called");
  }

  @Override
  public boolean atEnd() {
    return false;
  }

  @Override
  public Player getPlayer() {
    return null;
  }

  @Override
  public String toString() {
    return log.toString();
  }
}
