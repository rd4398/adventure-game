## Graphical Adventure Game

## Overview
Graphical Adventure Game is an adventure game which consists of a 2D dungeon of caves and tunnels. The player enters a cave and explores the dungeon to collect the treasure hidden in the caves. The treasure is in the form of gems including ruby, diamond and sapphires. The dungeon consists of monsters that can kill the player before reaching the end. However, the player has the ability to kill the monsters using arrows which are found in caves and tunnels. The player wins if he is able to reach the end without being killed by the monsters.
###List of Features
- The start and end point of the dungeon is a cave and is selected randomly everytime.
- The dungeon consists of caves and tunnels. The caves can have 1,3 or 4 exits whereas a tunnel can have only 2 exits.
- The description of location of player can be visualized after every move along with the treasure collected by the player.
- The dungeon can be wrapping or a non-wrapping one.
- The dungeon consists of monsters that can kill the player before reaching the end.
- Player starts with three arrows and can collect more arrows that are found in caves and tunnels.
- The player can shoot an arrow towards the monster in order to injure / kill it.
- The GUI for this game is made using Swing.
- Initially the screen is entirely blank and dungeon becomes visible as the player moves and explores the locations.
###How to run
####Text Mode
java -jar Project5-GraphicalAdventureGame.jar -rows 10 -columns 10 -interconnectivity 2 -treasure 50 -wrapping false -monsters 2 
####Graphics Mode
java -jar Project5-GraphicalAdventureGame.jar

## Controls
### Keyboard
- Arrow Keys : Move the player in N/S/E/W directions respectively
- A: Collect arrows from the location
- T: Collect Treasure from location
- S + Arrow Keys: Shoot the monster in the direction specified using arrow key
#### Mouse
- Use mouse click to move the player in required direction.

## Description of Examples
The dungeon_game.png in the /res folder shows the graphical adventure game. The top part of the frame consists of a menu where user can restart, start a new game or quit the game. Player moving and exploring the dungeon is captured in the screenshot. The bottom part of the frame shows the description of player and location based on current location of the player. The user can view the controls in the bottom right of the frame.The player can pick treasure, arrows and can shoot the monster by sensing the level of stench. 

## Design / Model Changes
- Added classes to the initial design which extend the existing classes for the model.
- Added helper methods whenever required
- Saving the parameters of dungeon in the model
- Adding class for loading images and Information

## Assumptions
- The player collects entire treasure from the cave after entering it and cannot collect it in parts.
- The start of dungeon for the game is a cave and cannot be a tunnel.
- The end of dungeon for the game is a cave and cannot be a tunnel.
- If the cave has treasure, it is exactly one amount of every type of treasure. Example: A cave can have only 1 diamond, 1 ruby or 1 sapphire and not multiple amount. It can have 1 diamond and 1 Ruby and similar combinations.
- The player collects all the arrows present in a cave or tunnel.
- There can be maximum of 5 arrows at a particular location
###Limitations
- The player has no freedom to collect partial amount of treasure.
- The creation of dungeon will throw exception if path length of five is not possible between start and end for a given configuration. This is because start and end is chosen randomly.
- For the GUI mode, the user is given a dungeon to play as soon as game starts and has no freedom to start game with custom settings right from the beginning. However, the user can change settings later. 
- If the user decides to start the new game, he/she has to enter the dungeon configuration and press Ok. They cannot quit in between. The user can quit the game only from main frame and not the settings frame.
## Citations
- To understand and implement [Disjoint Sets](https://www.techiedelight.com/disjoint-set-data-structure-union-find-algorithm/) for creating the dungeon.
- Implementing [BFS](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/) for the dungeon.
- Exploring and traversing a [maze](https://medium.com/geekculture/exploring-and-traversing-a-maze-c822790506a9).
- [Quit](https://stackoverflow.com/questions/33017359/how-to-make-window-close-on-clicking-exit-menuitem) action from JMenu.
- Understand and Implement [Key Listener](https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html)
- Understand and Implement [Mouse Listener](https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html)
- Implement [JSpinner](https://stackoverflow.com/questions/10107422/jspinner-in-joptionpane)
