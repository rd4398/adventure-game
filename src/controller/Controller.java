package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;


import dungeon.DungeonWithMonster;
import players.Directions;

/**
 * This class represents the controller for the text based adventure game. It handles the user input
 * and executes the model as per the commands entered by user.
 */
public class Controller implements DungeonController {
  private Appendable out;
  private Scanner in;

  /**
   * Construct the controller object by initializing the readable and appendable.
   *
   * @param in  the source to read from
   * @param out the target to append to
   */
  public Controller(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    this.in = new Scanner(in);
  }

  /**
   * Helper method to map the direction entered by user to actual direction.
   *
   * @param dir the direction entered by user (e,w,n,s)
   * @return the direction after mapping it to enum
   */
  private static Directions getRequiredDirection(String dir) {
    switch (dir.toLowerCase().charAt(0)) {
      case 'e':
        return Directions.EAST;
      case 'w':
        return Directions.WEST;
      case 'n':
        return Directions.NORTH;
      case 's':
        return Directions.SOUTH;
      default:
        throw new IllegalArgumentException("Invalid Direction");
    }
  }

  @Override
  public void play(DungeonWithMonster model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    try {
      String input = " ";
      boolean quitFlag = false;
      while (input.toLowerCase().charAt(0) != 'q') {
        out.append("\n");
        out.append(model.getDescription().toString());
        switch (model.getSmellAtLocation()) {
          case WEAK:
            out.append("\nThere is a weak smell of something deadly nearby");
            break;
          case STRONG:
            out.append("\nThere is a strong deadly smell.");
            break;
          default:
            break;
        }
        out.append("\nEnter your choice: Move{m}, Shoot Arrow{s}, Pick{p}, Quit{q}:\n");
        input = in.next();
        switch (input.toLowerCase().charAt(0)) {
          case 'q':
            quitFlag = true;
            break;
          case 'm':
            out.append("\nIn which direction you want to move? ")
                    .append(model.getNextMoves().toString()).append("\n");
            while (true) {
              input = in.next();
              if (input.toLowerCase().charAt(0) == 'q') {
                quitFlag = true;
                break;
              }
              try {
                model.movePlayerToLocation(getRequiredDirection(input));
                break;
              } catch (IllegalArgumentException iae) {
                out.append("\nYou have entered invalid move. Please check your input for "
                        + "direction");
              }
            }
            break;
          case 's':
            out.append("\nIn which direction you want to shoot?\n");
            Directions direction = null;
            while (true) {
              input = in.next();
              if (input.toLowerCase().charAt(0) == 'q') {
                quitFlag = true;
                break;
              }
              try {
                direction = getRequiredDirection(input);
                break;
              } catch (IllegalArgumentException iae) {
                out.append("\nYou have entered invalid move. Please check your input for "
                        + "direction");
              }
            }
            out.append("\nEnter the distance for shooting the arrow: \n");
            int distance = 0;
            while (true) {
              input = in.next();
              if (input.toLowerCase().charAt(0) == 'q') {
                quitFlag = true;
                break;
              }
              try {
                distance = Integer.parseInt(input);
                break;
              } catch (NumberFormatException nfe) {
                out.append("\nInvalid distance! Please enter a valid distance.");
              }
            }
            boolean injured = false;
            try {
              injured = model.shootArrowToLocation(direction, distance);
            } catch (IllegalStateException ise) {
              out.append("\nNo arrows to shoot");
            }
            if (injured) {
              out.append("\nYou hit the monster!!");
            } else {
              out.append("\nUnfortunately, you missed the monster");
            }
            break;
          case 'p':
            try {
              if (model.hasArrowsAtLocation() && model.hasTreasureAtLocation()) {
                out.append("\nWhat do you want to pick? Treasure[t], Arrow[a]\n");
                while (true) {
                  input = in.next();
                  if (input.toLowerCase().charAt(0) == 'q') {
                    quitFlag = true;
                    break;
                  }
                  if (input.toLowerCase().charAt(0) == 'a') {
                    try {
                      model.collectArrowsAtLocation();
                    } catch (IllegalStateException iae) {
                      out.append(iae.getMessage());
                    }
                    out.append("\nYou picked the arrows present at the location");
                    break;
                  } else if (input.toLowerCase().charAt(0) == 't') {
                    try {
                      model.collectTreasureAtLocation();
                    } catch (IllegalStateException iae) {
                      out.append(iae.getMessage());
                    }
                    out.append("\nYou picked the treasure present at the location.");
                    break;
                  } else {
                    out.append("\nYou entered invalid choice.");
                  }
                }
              } else if (model.hasTreasureAtLocation()) {
                model.collectTreasureAtLocation();
                out.append("\nYou picked the treasure present at the location.");
              } else if (model.hasArrowsAtLocation()) {
                model.collectArrowsAtLocation();
                out.append("\nYou picked the arrows present at the location.");
              } else {
                out.append("\nNothing to pick!");
              }
            } catch (IllegalStateException ise) {
              out.append("\nNothing to pick!");
            }
            break;
          default:
            out.append("\nInvalid move.");
            break;
        }
        if (model.hasMonsterAtLocation()) {
          if (model.gameStatus()) {
            out.append("\nYou just died!");
            break;
          } else {
            out.append("\nYou barely escaped the monster!");
          }
        }
        if (model.gameStatus()) {
          break;
        }
      }
      if (quitFlag) {
        out.append("\nQuitting game !\n");
      } else if (model.atEnd() && model.isPlayerAlive()) {
        out.append("\nYou won! Congratulations!\n");
      } else {
        out.append("\nGood Luck Next Time\n");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Failed to append");
    } catch (NoSuchElementException nse) {
      //Return naturally if input terminates
    }
  }

}