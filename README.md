# CMPT 276 Group 19 Project

CMPT 276 D100 Fall 2021: Group 19 Project

## Table of Contents

- [Maze of The Dead](#maze-of-the-dead)
- [Instructions](#instructions)
  - [How To Play](#how-to-play)
  - [How To Build](#how-to-build)
  - [How To Run](#how-to-run)
  - [How To Test](#how-to-test)
  - [Generate Javadocs](#generate-javadocs)
- [Creators](#creators)

## Maze of the Dead

Zombies are everywhere and hunting down humans! In this 2D post apocalyptic dungeon
crawler, the player’s goal is to survive the zombie apocalypse. They need medical
supplies and weapons to survive. The player navigates through a maze trying to
collect these supplies, while avoiding zombie attacks and traps around the maze.
The player must escape from the maze alive.

## Instructions

### How to Play

To play the game you need to follow the instructions below. The controls of the game
are as follows:

- 'W', 'A', 'S', 'D' are used to move the player left, right, up, and down respectively.
- 'Esc' is used to pause the game.
- To see more instructions, click on the 'Instructions' button on the title screen.

### How To Build

To build the game you must have the following installed:

- [Java](https://www.java.com/en/) (Java 17)
- [Maven](https://maven.apache.org/) (we used Maven 3.8.3)
- [Git](https://git-scm.com/) (Optional)

Download the repository and unzip or clone the repository using git:

`git clone https://csil-git1.cs.surrey.sfu.ca/cmpt276f21_group19/project.git`

Build the project running the following maven command:

`mvn package`

This generates a jar file located in the `target` directory.

### How to Run

After following [how to build](#how-to-build), you can run the game by running the following command:

`mvn exec:java`

or

`java -jar target/MazeOfTheDead-1.0.jar`

Both commands will run the game.

### How to Test

After following [how to build](#how-to-build), you can test the game by running
the following command:

`mvn test`

### Generate Javadocs

To generate Javadocs for the project, run the following command:

`mvn javadoc:javadoc`

The Javadocs are located in the `target/site/apidocs` directory and can be viewed
by opening the `index.html` file in your browser.

## Creators

Created By:

- Dylan Young
- Kaung Si Thu
- Maisha Supritee Chowdhury
- Sajandeep Toor
