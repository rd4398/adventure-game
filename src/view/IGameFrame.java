package view;

/**
 * A view for Graphical Adventure Game: display the game board and provide visual interface
 * for users.
 */
public interface IGameFrame {
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * This method is used to show the game status whether the game has ended, did the player win or
   * lose.
   * @param status true if game ends and player is alive
   *               false if game ends and player is dead
   */
  void showGameStatus(boolean status);
}
