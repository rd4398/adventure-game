package controller;

import dungeon.DungeonWithMonster;

/**
 * This interface represents the controller for the dungeon game. It handles the user moves by
 * executing them using model and conveys output to user in some form
 */
public interface DungeonController {
  /**
   * Execute a single run of Text based adventure game. When the game is over, the play method ends
   *
   * @param model the not null model of the dungeon
   */
  void play(DungeonWithMonster model);
}
