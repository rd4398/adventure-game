package controller;

import java.util.Random;

import dungeon.DungeonMonsterImpl;
import dungeon.DungeonWithMonster;
import location.Location;
import players.Directions;
import players.NewDescription;
import view.GameFrame;
import view.IGameFrame;

/**
 * This class represents the controller for the Graphical version of the adventure game. It is
 * linked to the model and view and communicates with both of them.
 */
public class GameController implements IGameController {
  private DungeonWithMonster model;
  private IGameFrame view;
  private int seed;

  /**
   * Construct the controller object and initialize it with the model in order to communicate with
   * it.
   *
   * @param model the model of the graphical adventure game
   * @param seed  the seed used for randomness
   */
  public GameController(DungeonWithMonster model, int... seed) {
    if (model == null) {
      throw new IllegalArgumentException("Model or view cannot be null");
    }
    this.model = model;
    this.seed = seed[0];
  }

  @Override
  public void setView(IGameFrame view) {
    this.view = view;
  }

  @Override
  public Location getPlayerCurrentLocation() {
    return model.getPlayer().getCurrentLocation();
  }

  @Override
  public void collectArrowsFromLocation() {
    model.collectArrowsAtLocation();
    view.refresh();
  }

  @Override
  public void collectTreasureAtLocation() {
    model.collectTreasureAtLocation();
    view.refresh();
  }

  @Override
  public void movePlayer(Directions direction) {
    model.movePlayerToLocation(direction);
    if (!model.isPlayerAlive() || model.atEnd()) {
      view.showGameStatus(model.isPlayerAlive());
    }
    view.refresh();
  }

  @Override
  public boolean shootArrow(Directions direction, int distance) {
    return model.shootArrowToLocation(direction, distance);
  }


  @Override
  public Location[][] getGrid() {
    return model.getGrid();
  }

  @Override
  public NewDescription getInformation() {
    return model.getDescription();
  }

  @Override
  public boolean isPlayerAlive() {
    return model.isPlayerAlive();
  }

  @Override
  public boolean gameStatus() {
    return model.gameStatus();
  }

  @Override
  public void startNewGame(int rows, int columns, int degree, int percentage,
                           boolean wrap, int monsters) {
    seed = new Random().nextInt();
    model = new DungeonMonsterImpl(rows, columns, degree, percentage, wrap, monsters, seed);
    view = new GameFrame(this);
    view.refresh();
  }

  @Override
  public void restartGame() {
    model = new DungeonMonsterImpl(model.getRows(), model.getColumns(),
            model.getInterconnectivity(), model.getPercentage(), model.getWrapStatus(),
            model.getMonstersCount(), model.getSeed());
    view.refresh();
  }

  @Override
  public void refreshView() {
    view.refresh();
  }
}
