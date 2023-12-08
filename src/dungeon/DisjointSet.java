package dungeon;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the DisjointSet that will be used to create the dungeon.
 */
public class DisjointSet {
  private final Map<Integer, Integer> parent;

  /**
   * The constructor which constructs the disjoint set by creating a hash map.
   */
  public DisjointSet() {
    parent = new HashMap<>();
  }

  /**
   * The following method is used to perform the make set operation.
   *
   * @param universe the universal set
   */
  public void makeSet(int[] universe) {
    for (int i : universe) {
      parent.put(i, i);
    }
  }

  /**
   * This method is used to find the root of the set in which element `k` belongs.
   *
   * @param k the element to be searched for
   * @return the set to which the element belongs
   */
  public int find(int k) {
    if (parent.get(k) == k) {
      return k;
    }
    return find(parent.get(k));
  }

  /**
   * The following method is used to perform union of two subsets.
   *
   * @param a the first subset
   * @param b the second subset
   */
  public void union(int a, int b) {
    int x = find(a);
    int y = find(b);
    parent.put(x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i : parent.keySet()) {
      sb.append(i).append(": ").append(parent.get(i)).append("\t");
    }
    return sb.toString();
  }
}