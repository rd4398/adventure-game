package controller;

import location.Location;
import players.Directions;
import players.NewDescription;
import view.IGameFrame;

/**
 * This interface represents the features that the graphical adventure game will have. It acts as
 * the interface for the controller which communicates with the view and model.
 */
public interface IGameController {
  /**
   * This method controls the functionality of collecting arrows from a particular location. It
   * communicates with the model to perform this operation
   */
  void collectArrowsFromLocation();

  /**
   * This method controls the functionality of collecting the treasure from a particular location.
   * It communicates with the model to perform this operation.
   */
  void collectTreasureAtLocation();

  /**
   * This method controls the movement of the player in the entire dungeon. The player can move
   * using keys on keyboard or by mouse click.
   *
   * @param direction the direction in which the player wants to move
   */
  void movePlayer(Directions direction);

  /**
   * This method controls the functionality of shooting an arrow by the player during the game.
   *
   * @param direction the direction in which the arrow needs to be shot
   * @param distance  the distance for the arrow to travel
   * @return true if arrow hits the monster
   *         false if arrow misses the monster
   */
  boolean shootArrow(Directions direction, int distance);

  /**
   * This method is used to set the view for the graphical adventure game. The controller
   * communicates with the view.
   *
   * @param view the view for the graphical adventure game
   */
  void setView(IGameFrame view);

  /**
   * This method is used by the controller to access the current location of the player.
   *
   * @return the current location of the player.
   */
  Location getPlayerCurrentLocation();

  /**
   * This method is used by the controller to access the dungeon that is being created by the model.
   * It then passes this dungeon to the view where it is displayed
   *
   * @return the dungeon that the model creates
   */
  Location[][] getGrid();

  /**
   * This method communicates with model to access the description of player and location
   * based on the current location of player.
   *
   * @return the description of location as well as player
   */
  NewDescription getInformation();

  /**
   * This method is used to check whether the player is alive or is being eaten by the monster.
   *
   * @return true if player is alive
   *         false if player is dead
   */
  boolean isPlayerAlive();

  /**
   * This method is used to check the status  of the game whether the game has ended or not.
   *
   * @return true if game has ended
   *         false if game has not been ended
   */
  boolean gameStatus();

  /**
   * This method controls the functionality of starting a new game for the user based on the inputs
   * provided.
   *
   * @param rows       the rows for the dungeon
   * @param columns    the columns for the dungeon
   * @param degree     the degree of interconnectivity for the dungeon
   * @param percentage the percentage of caves having treasure
   * @param wrap       whether the dungeon is wrapping or not
   * @param monsters   the number of monsters present in the dungeon
   */
  void startNewGame(int rows, int columns, int degree, int percentage, boolean wrap, int monsters);

  /**
   * This method controls the functionality of restarting the same game after user selects this
   * option from the menu.
   */
  void restartGame();

  /**
   * This method is used to refresh the view as and when required.
   */
  void refreshView();

}
