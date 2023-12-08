package players;

/**
 * This is an enum which represents the directions the player can move inside the dungeon to reach
 * the finish point.
 */
public enum Directions {
  NORTH, SOUTH, EAST, WEST;

  /**
   * This method is used to get the opposite direction for a given direction.
   *
   * @param direction the input direction
   * @return the opposite direction for a given input
   */
  public static Directions getOppositeDirection(Directions direction) {
    switch (direction) {
      case EAST:
        return Directions.WEST;
      case WEST:
        return Directions.EAST;
      case SOUTH:
        return Directions.NORTH;
      case NORTH:
        return Directions.SOUTH;
      default:
        return null;
    }
  }
}


