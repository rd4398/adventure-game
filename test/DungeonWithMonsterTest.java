import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import java.util.Set;

import dungeon.DungeonMonsterImpl;
import location.Cave;
import location.Location;
import location.Locationv2;
import location.Tunnel;
import monsters.Otyugh;
import players.Directions;
import players.Player;

/**
 * This class consists of all the junit tests for the functionality of the dungeon with monster.
 */
public class DungeonWithMonsterTest {

  private Locationv2 cave;
  private Locationv2 cave2;
  private Locationv2 tunnel;
  private DungeonMonsterImpl dungeon;

  @Before
  public void setup() {
    cave = new Cave();
    cave2 = new Cave();
    tunnel = new Tunnel();
  }

  @Test
  public void testAddMonster() {
    cave.addMonster(new Otyugh(100));
    assertTrue(cave.hasMonster());
  }

  @Test
  public void testHasMonster() {
    assertFalse(cave2.hasMonster());
  }


  @Test
  public void testAddCaveArrows() {
    cave.addArrows(4);
    assertEquals(4, cave.getArrows().size());
  }

  @Test
  public void testAddTunnelArrows() {
    tunnel.addArrows(3);
    assertEquals(3, tunnel.getArrows().size());
  }

  @Test
  public void testMonsterNeverAtStart() {
    dungeon = new DungeonMonsterImpl(4, 4, 2, 20, false,
            2, 6);
    Location[][] network = dungeon.getGrid();
    Player player = dungeon.getPlayer();
    Locationv2 start = (Locationv2) player.getCurrentLocation();
    assertFalse(start.hasMonster());
  }

  @Test
  public void testNonWrappingEntireTraversal() {
    dungeon = new DungeonMonsterImpl(4, 4, 2, 20, false,
            2, 6);
    Location[][] network = dungeon.getGrid();
    Player player = dungeon.getPlayer();
    Set<Location> visited = new HashSet<>();
    int caveCount = 0;
    int tunnelCount = 0;
    int cavesWithTreasure = 0;
    int locCount = 0;
    int locWithArrows = 0;
    int monsterCount = 0;
    char[] temp = {'N', 'W', 'E', 'S', 'W', 'S', 'E', 'W', 'W', 'W', 'N', 'E', 'W', 'N', 'N',
                   'E', 'S', 'N', 'E', 'E'};
    for (char c : temp) {
      switch (c) {
        case 'N':
          dungeon.movePlayerToLocation(Directions.NORTH);
          break;
        case 'E':
          dungeon.movePlayerToLocation(Directions.EAST);
          break;
        case 'W':
          dungeon.movePlayerToLocation(Directions.WEST);
          break;
        case 'S':
          dungeon.movePlayerToLocation(Directions.SOUTH);
          break;
        default:
          break;
      }
      Locationv2 current = (Locationv2) player.getCurrentLocation();
      if (!visited.contains(current)) {
        locCount++;
        if (current instanceof Cave) {
          if (current.getTreasureList().size() > 0) {
            dungeon.collectTreasureAtLocation();
            cavesWithTreasure++;
          }
          caveCount++;
          visited.add(current);
        }
        if (current instanceof Tunnel) {
          tunnelCount++;
          assertFalse(current.hasMonster());
          visited.add(current);
        }
        if (current.hasMonster()) {
          monsterCount++;
        }
        if (current.hasArrows()) {
          locWithArrows++;
          dungeon.collectArrowsAtLocation();
        }
        if (dungeon.atEnd()) {
          assertTrue(current.hasMonster());
        }
      }
    }

    assertEquals(16, locCount);
    assertEquals(8, caveCount);
    assertEquals(8, tunnelCount);
    assertEquals(2, monsterCount);
    assertEquals(2, locWithArrows);
    assertEquals(2, cavesWithTreasure);
    assertEquals(4, player.getTreasureList().size());
    assertEquals("SAPPHIRE", player.getTreasureList().get(0).getName().toString());
    assertEquals("Arrow Count :7", player.getDescription().toString().substring(14, 28));
  }

  @Test
  public void testWrappingEntireTraversal() {
    dungeon = new DungeonMonsterImpl(3, 4, 1, 50, true,
            3, 6);
    Location[][] network = dungeon.getGrid();
    Player player = dungeon.getPlayer();
    Set<Location> visited = new HashSet<>();
    int caveCount = 0;
    int tunnelCount = 0;
    int cavesWithTreasure = 0;
    int locCount = 0;
    int locWithArrows = 0;
    int monsterCount = 0;

    char[] temp = {'E', 'W', 'E', 'S', 'N', 'N', 'E', 'E', 'W', 'N', 'E', 'N', 'W',
                   'E', 'N', 'E', 'N'};
    for (char c : temp) {
      switch (c) {
        case 'N':
          dungeon.movePlayerToLocation(Directions.NORTH);
          break;
        case 'E':
          dungeon.movePlayerToLocation(Directions.EAST);
          break;
        case 'W':
          dungeon.movePlayerToLocation(Directions.WEST);
          break;
        case 'S':
          dungeon.movePlayerToLocation(Directions.SOUTH);
          break;
        default:
          break;
      }
      Locationv2 current = (Locationv2) player.getCurrentLocation();
      if (!visited.contains(current)) {
        locCount++;
        if (current instanceof Cave) {
          if (current.getTreasureList().size() > 0) {
            dungeon.collectTreasureAtLocation();
            cavesWithTreasure++;
          }
          caveCount++;
          visited.add(current);
        }
        if (current instanceof Tunnel) {
          tunnelCount++;
          assertFalse(current.hasMonster());
          visited.add(current);
        }
        if (current.hasMonster()) {
          monsterCount++;
        }
        if (current.hasArrows()) {
          locWithArrows++;
          dungeon.collectArrowsAtLocation();
        }
      }
    }

    assertEquals(12, locCount);
    assertEquals(8, caveCount);
    assertEquals(4, tunnelCount);
    assertEquals(3, monsterCount);
    assertEquals(4, locWithArrows);
    assertEquals(4, cavesWithTreasure);
    assertEquals(8, player.getTreasureList().size());
    assertEquals("SAPPHIRE", player.getTreasureList().get(0).getName().toString());
  }

  @Test
  public void testSmellAtLocation() {
    dungeon = new DungeonMonsterImpl(1, 8, 0, 20, false,
            1, 6);

    for (int i = 0; i < 4; i++) {
      dungeon.movePlayerToLocation(Directions.EAST);
      assertEquals("NOSMELL", dungeon.getSmellAtLocation().toString());
    }
    dungeon.movePlayerToLocation(Directions.EAST);
    assertEquals("WEAK", dungeon.getSmellAtLocation().toString());
    dungeon.movePlayerToLocation(Directions.EAST);
    assertEquals("STRONG", dungeon.getSmellAtLocation().toString());
  }

}
