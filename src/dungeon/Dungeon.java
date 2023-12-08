package dungeon;

import common.Jewels;
import common.Randomizer;
import common.Treasure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import location.AbstractLocation;
import location.Cave;
import location.Location;
import location.Tunnel;

import players.Directions;
import players.Player;

/**
 * This class represents the dungeon which will be traversed by the player.
 * It contains all the related functionalities like creation of dungeon.
 */
public class Dungeon implements Dungeons {
  protected Location[][] grid;
  protected final Randomizer random;
  protected Player player;
  protected Location end;

  /**
   * Construct a Dungeon object by initializing the random number generation.
   *
   * @param seed the seed for predicted random numbers
   */
  public Dungeon(int... seed) {
    if (seed.length > 0) {
      random = new Randomizer(seed[0]);
    } else {
      random = new Randomizer();
    }
    List<Treasure> treasureList = new ArrayList<>();
    player = new Player("Player1", treasureList, null);
  }

  @Override
  public void createDungeon(int rows, int columns, int degree, int percentage, boolean isWrap) {
    grid = new Location[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = new Cave();
      }
    }
    // 2D list to store the edges of the network.
    List<List<Integer>> edges = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i + 1 < rows) {
          int finalI = i;
          int finalJ = j;
          // Add the horizontal edges of the grid to the list for unwrapped dungeons
          edges.add(new ArrayList<>() {
            {
              add(finalI);
              add(finalJ);
              add(finalI + 1);
              add(finalJ);
            }
          });
          // Add the horizontal edges of the grid to list for wrapped dungeon
        } else if (isWrap) {
          int finalI = i;
          int finalJ = j;
          edges.add(new ArrayList<>() {
            {
              add(finalI);
              add(finalJ);
              add(0);
              add(finalJ);
            }
          });
        }
        // Add the vertical edges of the grid to the list for unwrapped dungeons
        if (j + 1 < columns) {
          int finalI = i;
          int finalJ = j;
          edges.add(new ArrayList<>() {
            {
              add(finalI);
              add(finalJ);
              add(finalI);
              add(finalJ + 1);
            }
          });
        }
        // Add the vertical edges of the grid to the list for wrapped dungeons
        else if (isWrap) {
          int finalI = i;
          int finalJ = j;
          edges.add(new ArrayList<>() {
            {
              add(finalI);
              add(finalJ);
              add(finalI);
              add(0);
            }
          });
        }

      }
    }
    // Kruskal Algorithm using disjoint sets
    DisjointSet ds = new DisjointSet();
    int[] universe = new int[rows * columns + 1];
    for (int i = 0; i < rows * columns + 1; i++) {
      universe[i] = i;
    }
    ds.makeSet(universe);
    // check for the cycles that are happening
    for (int i = 0; i < rows * columns - 1; i++) {
      int randomNumber = random.generateRandomNumber(0, edges.size());
      List<Integer> coordinates = edges.get(randomNumber);
      int x1 = coordinates.get(0);
      int y1 = coordinates.get(1);
      int x2 = coordinates.get(2);
      int y2 = coordinates.get(3);
      int pos1 = x1 * columns + y1 + 1;
      int pos2 = x2 * columns + y2 + 1;
      int z1 = ds.find(pos1);
      int z2 = ds.find(pos2);
      if (z1 == z2) {
        i--;
        continue;
      }
      edges.remove(coordinates);
      ds.union(pos1, pos2);
      // Add the edge to the network based on the direction
      if (x2 == (x1 + 1) % rows && y2 == y1) {
        grid[x1][y1].connect(grid[x2][y2], Directions.SOUTH);
        grid[x2][y2].connect(grid[x1][y1], Directions.NORTH);
      } else {
        grid[x1][y1].connect(grid[x2][y2], Directions.EAST);
        grid[x2][y2].connect(grid[x1][y1], Directions.WEST);
      }
    }
    // Check for degree of interconnectivity
    for (int i = 0; i < degree && edges.size() > 0; i++) {
      int randomNumber = random.generateRandomNumber(0, edges.size());
      List<Integer> coordinates = edges.get(randomNumber);
      int x1 = coordinates.get(0);
      int y1 = coordinates.get(1);
      int x2 = coordinates.get(2);
      int y2 = coordinates.get(3);
      edges.remove(coordinates);
      degree--;
      // Add edge to the network based on interconnectivity
      if (x2 == (x1 + 1) % rows && y2 == y1) {
        grid[x1][y1].connect(grid[x2][y2], Directions.SOUTH);
        grid[x2][y2].connect(grid[x1][y1], Directions.NORTH);
      } else if (y2 == (y1 + 1) % columns && x2 == x1) {
        grid[x1][y1].connect(grid[x2][y2], Directions.EAST);
        grid[x2][y2].connect(grid[x1][y1], Directions.WEST);
      } else {
        throw new IllegalStateException("Invalid direction");
      }
    }
    // Check for the number of exits of every location of grid and convert them to tunnel if number
    // of exits are 2
    int tunnelCount = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j].getDirections().size() == 2) {
          Tunnel tunnel = new Tunnel(grid[i][j].getId(),
                  ((AbstractLocation) grid[i][j]).getMap());
          List<Directions> directions = tunnel.getDirections();
          tunnel.getConnections(directions.get(0)).connect(tunnel,
                  Directions.getOppositeDirection(directions.get(0)));
          tunnel.getConnections(directions.get(1)).connect(tunnel,
                  Directions.getOppositeDirection(directions.get(1)));
          grid[i][j] = tunnel;
          tunnelCount++;
        }
      }
    }

    // Calculate the number of caves in which the treasure will go based on percentage
    int caveCount = rows * columns - tunnelCount;
    int treasureCount = (int) Math.ceil(percentage / (float) 100 * caveCount);
    // Randomly add the treasure to the caves
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j] instanceof Cave && treasureCount > 0) {
          int randomTreasureCount = random.generateRandomNumber(1, 4);
          switch (randomTreasureCount) {
            case 3:
              grid[i][j].addTreasure(new Treasure(Jewels.DIAMOND));
              grid[i][j].addTreasure(new Treasure(Jewels.SAPPHIRE));
              grid[i][j].addTreasure(new Treasure(Jewels.RUBY));

              break;
            case 2:
              grid[i][j].addTreasure(new Treasure(Jewels.SAPPHIRE));
              grid[i][j].addTreasure(new Treasure(Jewels.RUBY));

              break;
            case 1:
              grid[i][j].addTreasure(new Treasure(Jewels.RUBY));
              break;
            default:
              break;
          }
          treasureCount--;
        }
      }
    }
    // Repeat this.
    int rep = 0;
    while (end == null && rep < 200) {
      rep++;
      checkPath();
    }
    if (rep == 200) {
      throw new IllegalStateException("Dungeon cannot be created with given values");
    }
  }


  @Override
  public void markEnd(Location cave) {
    this.end = cave;
  }

  @Override
  public boolean atEnd() {
    return player.getCurrentLocation().equals(end);
  }


  /**
   * This is the helper method which checks the path length between start and end locations
   * for the dungeon.
   *
   * @return whether path is valid or not
   */
  private boolean checkPath() {
    Location[][] network = getGrid();
    Queue<Location> q2;
    int startRowIndex;
    int startColumnIndex;
    Location startCave;
    int count = 0;
    Location endCave = null;
    // Use BFS to check the path length between start and end cave. Keep selecting the start and end
    // randomly until the path length is atleast 5
    startRowIndex = random.generateRandomNumber(0, network.length);
    startColumnIndex = random.generateRandomNumber(0, network[0].length);
    startCave = network[startRowIndex][startColumnIndex];
    boolean flag = false;
    Queue<Location> q1 = new LinkedList<>();
    if (startCave instanceof Tunnel) {
      return false;
    }
    Set<Location> visited = new HashSet<>();
    flag = true;
    q1.add(startCave);
    visited.add(startCave);
    while (!q1.isEmpty()) {
      q2 = new LinkedList<>(q1);
      q1.clear();
      while (!q2.isEmpty()) {
        List<Location> connections = q2.poll().getNeighbors();
        for (Location connection : connections) {
          if (!visited.contains(connection)) {
            q1.add(connection);
            visited.add(connection);
          }

          if (count > 5 && connection instanceof Cave) {
            endCave = connection;
          }
        }
      }
      count++;
    }

    if (endCave != null) {
      player.enterStart((Cave) startCave);
      player.getCurrentLocation().setExplored();
      markEnd(endCave);
    } else {
      throw new IllegalStateException("Cannot create dungeon for given size");
    }
    return true;
  }

  /**
   * This method is used to get the grid for the printing and testing internally, thus I am not
   * returning a deep copy.
   *
   * @return the grid
   */
  @Override
  public Location[][] getGrid() {
    return this.grid;
  }

  /**
   * This method is used to get the player for testing purposes and thus deep copy is not returned
   * here.
   *
   * @return the player
   */
  @Override
  public Player getPlayer() {
    return this.player;
  }
}
