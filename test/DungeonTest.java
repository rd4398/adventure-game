import common.Randomizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import java.util.Set;

import dungeon.Dungeon;
import dungeon.Dungeons;
import location.Cave;
import location.Location;
import location.Tunnel;
import players.Directions;
import players.Player;

/**
 * This class represents the junit tests related to the functionality of dungeon in the game.
 */
public class DungeonTest {
  private Dungeons wrappingDungeon;
  private Dungeons unwrappingDungeon;
  private Randomizer randomizer;

  @Before
  public void setup() {
    wrappingDungeon = new Dungeon(5);
    unwrappingDungeon = new Dungeon(3);
    wrappingDungeon.createDungeon(3, 4, 1, 50, true);
    unwrappingDungeon.createDungeon(4, 4, 2, 20, false);
    randomizer = new Randomizer(6);
  }


  @Test
  public void testRandomizer() {
    int n = randomizer.generateRandomNumber(4, 12);
    assertEquals(9, n);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRandomizer() {
    int n = randomizer.generateRandomNumber(12, 4);
    assertEquals(9, n);
  }


  @Test
  public void testCreateDungeon() {
    assertFalse(unwrappingDungeon.atEnd());
  }

  @Test
  public void testUnwrapEntireTraversal() {
    Location[][] network = unwrappingDungeon.getGrid();
    Player player = unwrappingDungeon.getPlayer();
    Set<Location> visited = new HashSet<>();
    int caveCount = 0;
    int tunnelCount = 0;
    int cavesWithTreasure = 0;
    int locCount = 0;
    char[] temp = {'S', 'E', 'E', 'W', 'W', 'N', 'N', 'S', 'E', 'N', 'N', 'W', 'E', 'E', 'E', 'S',
                   'W', 'S', 'E', 'S'};
    for (char c : temp) {
      switch (c) {
        case 'N':
          player.movePlayer(Directions.NORTH);
          break;
        case 'E':
          player.movePlayer(Directions.EAST);
          break;
        case 'W':
          player.movePlayer(Directions.WEST);
          break;
        case 'S':
          player.movePlayer(Directions.SOUTH);
          break;
        default:
          break;
      }
      Location current = player.getCurrentLocation();
      if (!visited.contains(current)) {
        locCount++;
        if (current instanceof Cave) {
          if (current.getTreasureList().size() > 0) {
            player.collectTreasure(current.getTreasureList());
            cavesWithTreasure++;
          }
          caveCount++;
          visited.add(current);
        }
        if (current instanceof Tunnel) {
          tunnelCount++;
          visited.add(current);
        }
      }
    }

    assertEquals(8, caveCount);
    assertEquals(8, tunnelCount);
    assertEquals(2, cavesWithTreasure);
    assertEquals(16, locCount);
    assertEquals(4, player.getTreasureList().size());
    assertEquals("SAPPHIRE", player.getTreasureList().get(0).getName().toString());
  }

  @Test
  public void testUnwrapStartEndTraversal() {
    Location[][] network = unwrappingDungeon.getGrid();
    Player player = unwrappingDungeon.getPlayer();
    Set<Location> visited = new HashSet<>();
    int caveCount = 0;
    int tunnelCount = 0;
    int cavesWithTreasure = 0;
    int locCount = 0;
    int pathCount = 0;
    char[] temp = {'E', 'N', 'N', 'E', 'S', 'S', 'E', 'S'};
    for (char c : temp) {
      switch (c) {
        case 'N':
          player.movePlayer(Directions.NORTH);
          break;
        case 'E':
          player.movePlayer(Directions.EAST);
          break;
        case 'W':
          player.movePlayer(Directions.WEST);
          break;
        case 'S':
          player.movePlayer(Directions.SOUTH);
          break;
        default:
          break;
      }
      Location current = player.getCurrentLocation();
      if (!visited.contains(current)) {
        locCount++;
        if (current instanceof Cave) {
          if (current.getTreasureList().size() > 0) {
            cavesWithTreasure++;
          }
          caveCount++;
          visited.add(current);
        }
        if (current instanceof Tunnel) {
          tunnelCount++;
          visited.add(current);
        }
        if (unwrappingDungeon.atEnd()) {
          break;
        }
      }
      pathCount++;
    }

    assertEquals(4, caveCount);
    assertEquals(4, tunnelCount);
    assertEquals(1, cavesWithTreasure);
    assertEquals(8, locCount);
    assertEquals(7, pathCount);
  }

  @Test
  public void testWrapTraversal() {
    Location[][] network = wrappingDungeon.getGrid();
    Player player = wrappingDungeon.getPlayer();
    Set<Location> visited = new HashSet<>();
    int caveCount = 0;
    int tunnelCount = 0;
    int cavesWithTreasure = 0;
    int locCount = 0;
    char[] temp = {'S', 'N', 'S', 'E', 'E', 'E', 'N', 'S', 'S', 'E', 'E', 'E', 'W', 'S', 'E'};
    for (char c : temp) {

      switch (c) {
        case 'N':
          player.movePlayer(Directions.NORTH);
          break;
        case 'E':
          player.movePlayer(Directions.EAST);
          break;
        case 'W':
          player.movePlayer(Directions.WEST);
          break;
        case 'S':
          player.movePlayer(Directions.SOUTH);
          break;
        default:
          break;
      }
      Location current = player.getCurrentLocation();
      if (!visited.contains(current)) {
        locCount++;
        if (current instanceof Cave) {
          if (current.getTreasureList().size() > 0) {
            cavesWithTreasure++;
          }
          caveCount++;
          visited.add(current);
        }
        if (current instanceof Tunnel) {
          tunnelCount++;
          visited.add(current);
        }
      }
    }

    assertEquals(6, caveCount);
    assertEquals(6, tunnelCount);
    assertEquals(3, cavesWithTreasure);
    assertEquals(12, locCount);
  }

  @Test
  public void testWrappingDungeon() {
    Location[][] network = wrappingDungeon.getGrid();
    assertEquals(9, network[0][0].getConnections(Directions.NORTH).getId());
    assertEquals(8, network[1][0].getConnections(Directions.WEST).getId());
  }
}
