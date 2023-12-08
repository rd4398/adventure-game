import common.Jewels;
import common.Treasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import location.Cave;
import location.Location;
import location.Locationv2;
import location.Tunnel;
import monsters.Otyugh;
import players.Directions;

import players.PlayerImpl;
import players.PlayerWithArrows;
import weapons.Arrow;
import weapons.Weapon;

/**
 * This class consists of all the junit tests for functionality related to a player with weapons.
 */
public class PlayerWithWeaponTest {
  private Locationv2 cave;
  private PlayerWithArrows player;

  @Before
  public void setup() {
    List<Treasure> treasureList = new ArrayList<>();
    List<Treasure> treasureList2 = new ArrayList<>();
    List<Treasure> playerTreasure = new ArrayList<>();
    treasureList.add(new Treasure(Jewels.DIAMOND));
    treasureList.add(new Treasure(Jewels.RUBY));
    treasureList2.add(new Treasure(Jewels.DIAMOND));
    cave = new Cave(treasureList);


    Locationv2 cave3 = new Cave(treasureList2);
    Location cave4 = new Cave();

    cave.connect(cave3, Directions.WEST);
    cave3.connect(cave, Directions.EAST);
    cave.connect(cave4, Directions.NORTH);
    cave4.connect(cave, Directions.SOUTH);

    Location tunnel = new Tunnel();
    cave.connect(tunnel, Directions.SOUTH);
    tunnel.connect(cave, Directions.NORTH);
    tunnel.connect(cave3, Directions.EAST);
    player = new PlayerImpl("Player1", playerTreasure, cave);
  }


  @Test
  public void testInitialArrows() {
    assertEquals(3, player.arrowCount());
  }

  @Test
  public void testPickArrowsFromCave() {
    List<Weapon> arrows = new ArrayList<>();
    cave.addArrows(2);
    arrows.add(new Arrow());
    arrows.add(new Arrow());
    player.collectArrows(arrows);
    assertEquals(5, player.arrowCount());
  }

  @Test
  public void testShootArrow() {
    Locationv2 cave2 = new Cave();
    cave2.addArrows(3);
    cave2.addMonster(new Otyugh(100));
    cave.connect(cave2, Directions.EAST);
    cave2.connect(cave, Directions.WEST);
    assertTrue(player.shootArrow(Directions.EAST, 1));
    assertEquals(2, player.arrowCount());
    assertTrue(cave2.hasMonster());
    assertTrue(player.shootArrow(Directions.EAST, 1));
    assertFalse(cave2.hasMonster());
    assertEquals(1, player.arrowCount());
  }

  @Test
  public void testShootArrowPassingFromTunnel() {
    Locationv2 c1 = new Cave();
    Locationv2 t1 = new Tunnel();
    Locationv2 c3 = new Cave();
    PlayerWithArrows player2 = new PlayerImpl("player2", new ArrayList<>(), c1);
    c1.connect(t1, Directions.SOUTH);
    t1.connect(c1, Directions.NORTH);
    t1.connect(c3, Directions.EAST);
    c3.connect(t1, Directions.WEST);
    c3.addMonster(new Otyugh(100));
    assertTrue(player2.shootArrow(Directions.SOUTH, 1));
    assertEquals(2, player2.arrowCount());
    assertTrue(c3.hasMonster());
    assertTrue(player2.shootArrow(Directions.SOUTH, 1));
    assertFalse(c3.hasMonster());
    assertEquals(1, player2.arrowCount());
  }

  @Test
  public void testShootArrowWithDistance2() {
    Locationv2 c1 = new Cave();
    Locationv2 c2 = new Cave();
    Locationv2 c3 = new Cave();
    PlayerWithArrows player2 = new PlayerImpl("player2", new ArrayList<>(), c1);
    c1.connect(c2, Directions.EAST);
    c2.connect(c1, Directions.WEST);
    c2.connect(c3, Directions.EAST);
    c3.connect(c2, Directions.WEST);
    c3.addMonster(new Otyugh(100));
    assertTrue(player2.shootArrow(Directions.EAST, 2));
    assertEquals(2, player2.arrowCount());
    assertTrue(c3.hasMonster());
    assertTrue(player2.shootArrow(Directions.EAST, 2));
    assertFalse(c3.hasMonster());
    assertEquals(1, player2.arrowCount());
  }

  @Test
  public void testMissArrowShotAtOtyugh() {
    Locationv2 c1 = new Cave();
    Locationv2 c2 = new Cave();
    Locationv2 c3 = new Cave();
    PlayerWithArrows player2 = new PlayerImpl("player2", new ArrayList<>(), c1);
    c1.connect(c2, Directions.EAST);
    c2.connect(c1, Directions.WEST);
    c2.connect(c3, Directions.EAST);
    c3.connect(c2, Directions.WEST);
    c3.addMonster(new Otyugh(100));
    assertFalse(player2.shootArrow(Directions.EAST, 3));
    assertTrue(c3.hasMonster());
    player2.movePlayer(Directions.EAST);
    assertFalse(player2.shootArrow(Directions.EAST, 2));
    assertTrue(c3.hasMonster());
  }

  @Test
  public void testKillPlayer() {
    Locationv2 cave2 = new Cave();
    cave2.addArrows(3);
    cave2.addMonster(new Otyugh(100));
    cave.connect(cave2, Directions.EAST);
    cave2.connect(cave, Directions.WEST);
    assertEquals(100, player.getHealth());
    assertTrue(player.isAlive());
    player.movePlayer(Directions.EAST);
    assertEquals(0, player.getHealth());
    assertFalse(player.isAlive());
  }

  @Test
  public void testPickArrowsFromTunnel() {
    Tunnel t1 = new Tunnel();
    t1.addArrows(3);
    assertEquals(3, t1.getArrows().size());
    List<Weapon> arrows = new ArrayList<>();
    arrows.add(new Arrow());
    arrows.add(new Arrow());
    arrows.add(new Arrow());
    player.collectArrows(arrows);
    assertEquals(6, player.arrowCount());

  }

  @Test
  public void testMonsterInjured() {
    Locationv2 c1 = new Cave();
    Locationv2 c2 = new Cave();
    Locationv2 c3 = new Cave();
    PlayerWithArrows player2 = new PlayerImpl("player2", new ArrayList<>(), c1);
    c1.connect(c2, Directions.EAST);
    c2.connect(c1, Directions.WEST);
    c2.connect(c3, Directions.EAST);
    c3.connect(c2, Directions.WEST);
    c3.addMonster(new Otyugh(100));
    assertTrue(player2.shootArrow(Directions.EAST, 2));
    assertTrue(c3.hasMonster());
  }

  @Test
  public void testPlayerChanceWhenMonsterInjured() {
    Locationv2 c1 = new Cave();
    Locationv2 c2 = new Cave();
    Locationv2 c3 = new Cave();
    PlayerWithArrows player2 = new PlayerImpl("player2", new ArrayList<>(), c1);
    c1.connect(c2, Directions.EAST);
    c2.connect(c1, Directions.WEST);
    c2.connect(c3, Directions.EAST);
    c3.connect(c2, Directions.WEST);
    c3.addMonster(new Otyugh(100));
    assertTrue(player2.shootArrow(Directions.EAST, 2));
    assertTrue(c3.hasMonster());
    player2.movePlayer(Directions.EAST);
    player2.movePlayer(Directions.EAST);
    assertEquals(100, player2.getHealth());
    assertTrue(player2.isAlive());
  }

  @Test
  public void testDescription() {
    PlayerWithArrows player2 = new PlayerImpl("player2", new ArrayList<>(), cave);
    assertEquals("Name: player2\n"
            + "Arrow Count :3\n"
            + "No treasure collected yet! \n"
            + "Location Description :\n"
            + "Location id :1\n"
            + "Location type :Cave\n"
            + "The connections for the location are :\n"
            + "Arrows at location 0\n"
            + "SOUTH: ID -> 4\n"
            + "NORTH: ID -> 3\n"
            + "WEST: ID -> 2\n"
            + "The treasure at the location is\n"
            + "DIAMOND\n"
            + "RUBY", player2.getDescription().toString());
  }

}
