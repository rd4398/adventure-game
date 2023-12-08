import common.Jewels;
import common.Treasure;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import location.Cave;
import location.Location;
import location.Tunnel;
import players.Directions;
import players.Player;

/**
 * This class consists of all the junit tests related to functionality of a player.
 */
public class PlayerTest {
  private Location cave;
  private Location cave3;
  private Location tunnel2;
  private Player player;
  List<Treasure> playerTreasure;

  @Before
  public void setup() {
    List<Treasure> treasureList = new ArrayList<>();
    List<Treasure> treasureList2 = new ArrayList<>();
    playerTreasure = new ArrayList<>();
    treasureList.add(new Treasure(Jewels.DIAMOND));
    treasureList.add(new Treasure(Jewels.RUBY));
    treasureList2.add(new Treasure(Jewels.DIAMOND));
    cave = new Cave(treasureList);
    Location cave2 = new Cave();
    cave3 = new Cave(treasureList2);
    Location cave4 = new Cave();
    cave.connect(cave2, Directions.EAST);
    cave2.connect(cave, Directions.WEST);
    cave.connect(cave3, Directions.WEST);
    cave3.connect(cave, Directions.EAST);
    cave.connect(cave4, Directions.NORTH);
    cave4.connect(cave, Directions.SOUTH);

    Location tunnel = new Tunnel();
    cave.connect(tunnel, Directions.SOUTH);
    tunnel.connect(cave, Directions.NORTH);
    tunnel.connect(cave3, Directions.EAST);
    player = new Player("Player1", playerTreasure, cave);
  }

  @Test
  public void testCreatePlayer() {
    assertEquals("Player1", player.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayer() {
    new Player("", playerTreasure, cave);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayer2() {
    new Player(null, null, cave);
  }

  @Test
  public void testCollectTreasure() {
    List<Treasure> temp = new ArrayList<>();
    temp.add(new Treasure(Jewels.DIAMOND));
    temp.add(new Treasure(Jewels.RUBY));
    player.collectTreasure(temp);
    assertEquals("DIAMOND", player.getTreasureList().get(0).getName().toString());
    assertEquals(2, playerTreasure.size());
    assertEquals("RUBY", playerTreasure.get(1).getName().toString());
  }

  @Test
  public void testPlayerCurrentLocation() {
    assertEquals(382,
            player.getCurrentLocation().getId());
  }

  @Test
  public void testEnterStart() {
    Player player1 = new Player("Player2", playerTreasure, null);
    player1.enterStart((Cave) cave3);
    assertEquals(374, player1.getCurrentLocation().getId());
  }

  @Test
  public void testGetTreasureList() {
    List<Treasure> temp = new ArrayList<>();
    Player player2 = new Player("Player2", temp, null);
    temp.add(new Treasure(Jewels.DIAMOND));
    temp.add(new Treasure(Jewels.DIAMOND));
    temp.add(new Treasure(Jewels.DIAMOND));
    temp.add(new Treasure(Jewels.DIAMOND));
    temp.add(new Treasure(Jewels.DIAMOND));
    assertEquals(5, player2.getTreasureList().size());
  }

  @Test
  public void testMovePlayer() {
    assertEquals(402, player.getCurrentLocation().getId());
    player.movePlayer(Directions.EAST);
    assertEquals(403, player.getCurrentLocation().getId());
    player.movePlayer(Directions.WEST);
    player.movePlayer(Directions.SOUTH);
    assertEquals(406, player.getCurrentLocation().getId());
    player.movePlayer(Directions.NORTH);
    player.movePlayer(Directions.WEST);
    assertEquals(404, player.getCurrentLocation().getId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePlayer() {
    player.movePlayer(null);
    assertEquals(2, player.getCurrentLocation().getId());
  }

  @Test
  public void getPlayerDescription() {
    List<Treasure> temp = new ArrayList<>();
    temp.add(new Treasure(Jewels.DIAMOND));
    temp.add(new Treasure(Jewels.RUBY));
    player.collectTreasure(temp);
    assertEquals("Name: Player1\n" +
            "Treasure Collected :\n" +
            "DIAMOND: 1\n" +
            "RUBY: 1\n" +
            "Location Description :\n" +
            "Location id :1\n" +
            "Location type :Cave\n" +
            "The connections for the location are :\n" +
            "SOUTH: ID -> 5\n" +
            "WEST: ID -> 3\n" +
            "NORTH: ID -> 4\n" +
            "EAST: ID -> 2\n" +
            "The treasure at the location is\n" +
            "DIAMOND\n" +
            "RUBY", player.getDescription().toString()
    );
  }

}
