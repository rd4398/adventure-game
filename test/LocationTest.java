import common.Jewels;
import common.Treasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import location.Cave;
import location.Location;
import location.Tunnel;
import players.Directions;

/**
 * This class contains all the junit tests related to the functionality of location i.e cave and
 * tunnel.
 */
public class LocationTest {
  private Location cave;
  private Location cave2;
  private Location cave3;
  private Location cave4;
  private Location tunnel;
  private Location tunnel2;
  private Treasure treasure;

  @Before
  public void setup() {
    List<Treasure> treasureList = new ArrayList<>();
    List<Treasure> treasureList2 = new ArrayList<>();
    treasureList.add(new Treasure(Jewels.DIAMOND));
    treasureList.add(new Treasure(Jewels.RUBY));
    treasureList2.add(new Treasure(Jewels.DIAMOND));
    cave = new Cave(treasureList);
    cave2 = new Cave();
    cave3 = new Cave(treasureList2);
    cave4 = new Cave();
    cave.connect(cave2, Directions.EAST);
    cave.connect(cave3, Directions.WEST);
    cave.connect(cave4, Directions.NORTH);
    tunnel = new Tunnel();
    tunnel2 = new Tunnel();
    tunnel.connect(tunnel2, Directions.NORTH);
    tunnel.connect(cave3, Directions.EAST);
    treasure = new Treasure(Jewels.DIAMOND);
  }

  @Test
  public void testCaveCreationWithTreasure() {
    assertEquals(338, cave.getId());
  }

  @Test
  public void testTreasureList() {
    List<Treasure> temp = cave.getTreasureList();
    assertEquals("DIAMOND", temp.get(0).getName().toString());
    assertEquals("RUBY", temp.get(1).getName().toString());
  }

  @Test
  public void testAddTreasure() {
    cave.addTreasure(new Treasure(Jewels.SAPPHIRE));
    assertEquals("SAPPHIRE", cave.getTreasureList().get(2).getName().toString());
    assertEquals(3, cave.getTreasureList().size());
  }

  @Test
  public void testRemoveTreasure() {
    List<Treasure> temp = new ArrayList<>();
    temp.add(new Treasure(Jewels.RUBY));
    cave.removeTreasure(temp);
    assertEquals("DIAMOND", cave.getTreasureList().get(0).getName().toString());
    assertEquals(1, cave.getTreasureList().size());
  }

  @Test
  public void testCaveWithoutTreasure() {
    cave2 = new Cave();
    assertEquals(295, cave2.getId());
  }

  @Test
  public void testConnections() {
    Location temp = cave.getConnections(Directions.WEST);
    Location temp1 = cave.getConnections(Directions.EAST);
    Location temp2 = cave.getConnections(Directions.NORTH);
    assertEquals(328, temp.getId());
    assertEquals(cave3, temp);
    assertEquals(cave2, temp1);
    assertEquals(cave4, temp2);
  }

  @Test
  public void testConnectedDirections() {
    assertTrue(cave.getDirections().contains(Directions.WEST));
    assertTrue(cave.getDirections().contains(Directions.EAST));
    assertTrue(cave.getDirections().contains(Directions.NORTH));
  }

  @Test
  public void testCaveNeighbors() {
    List<Location> temp = cave.getNeighbors();
    assertTrue(temp.contains(cave2));
    assertTrue(temp.contains(cave3));
    assertTrue(temp.contains(cave4));
  }

  @Test
  public void testCaveDescription() {
    assertEquals("\nLocation id :1\n" +
            "Location type :Cave\n" +
            "The connections for the location are :\n" +
            "WEST: ID -> 3\n" +
            "NORTH: ID -> 4\n" +
            "EAST: ID -> 2\n" +
            "The treasure at the location is\n" +
            "DIAMOND\n" +
            "RUBY", cave.getDescription().
            toString());
  }

  @Test
  public void testCreateTunnel() {
    assertEquals(263, tunnel.getId());
  }

  @Test(expected = IllegalStateException.class)
  public void testTunnelTreasureList() {
    List<Treasure> temp = tunnel.getTreasureList();
  }

  @Test(expected = IllegalStateException.class)
  public void testTunnelAddTreasure() {
    tunnel.addTreasure(new Treasure(Jewels.SAPPHIRE));
  }

  @Test(expected = IllegalStateException.class)
  public void testTunnelRemoveTreasure() {
    List<Treasure> temp = new ArrayList<>();
    temp.add(new Treasure(Jewels.RUBY));
    tunnel.removeTreasure(temp);
  }

  @Test
  public void testTunnelConnections() {
    Location temp = tunnel.getConnections(Directions.NORTH);
    Location temp1 = tunnel.getConnections(Directions.EAST);
    assertEquals(cave3, temp1);
    assertEquals(tunnel2, temp);
  }

  @Test
  public void testTunnelConnectedDirections() {
    assertTrue(tunnel.getDirections().contains(Directions.EAST));
    assertTrue(tunnel.getDirections().contains(Directions.NORTH));
  }

  @Test
  public void testTunnelCaveNeighbors() {
    List<Location> temp = tunnel.getNeighbors();
    assertTrue(temp.contains(tunnel2));
    assertTrue(temp.contains(cave3));
  }

  @Test
  public void testTunnelDescription() {
    assertEquals("\nLocation id :5\n" +
            "Location type :Tunnel\n" +
            "The connections for the location are :\n" +
            "NORTH: ID -> 6\n" +
            "EAST: ID -> 3\n" +
            "No treasure in this location", tunnel.getDescription().
            toString());
  }

  @Test
  public void testTreasure() {
    assertEquals("DIAMOND", treasure.getName().toString());
  }


}
