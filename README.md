# Chalmers Defense

[![Build Status](https://app.travis-ci.com/Danilll01/TDA367-OO-Projekt.svg?branch=main)](https://app.travis-ci.com/Danilll01/TDA367-OO-Projekt)

## Images
### Main screen of the game
![MainScreen](/Documents/images/MainScreen.png)

### Game view
![MainScreen](/Documents/images/GameScreen.png)

### Information about the different towers
<img src="/Documents/images/TowerInformation.png" width="450">

## Filestructure
Path                                    | Comment
--------------------------------------- | -------------
`/Documents`                            | All documents associated with the project
`/core/src/com/mygdx/chalmersdefense`   | The main folder for the code
`/desktop/src/test/java/`               | Folder containing all JUnit tests
`/core/assets`                          | Location for all assets in the game

2D Tower Defense game where you have to place towers and manage your money in order so save Chalmers University from the feared
corona virus!

## How to run the program
### Option 1:
* Clone the repo and run the DesktopLauncher file in your IDE

### Option 2:
* Download the most resent release from releases and run the jar file (java must be installed before running.)

## Creators

Github name   | Real name
------------- | -------------
Danilll01     | Daniel Persson
Turbobus      | Joel Båtsman Hilmersson
Elin1043      | Elin Forsberg
jennycarlsson | Jenny Carlsson

## Known Issues
### Mac OS
* Currently the application does not work properly for macOS. The program is able to start and run but a problem occurs regarding the circle around the towers. They appear slightly oval and the positioning is incorrect.
* Changing from fullscreen to window mode on a Mac causes the program to crash.

Note: Same problems as above can be expected for Linux.

### Scaling
* When exiting the fullscreen mode the text sometimes becomes hard to read because of a scaling issue with libGDX. 

## Current UML class-diagram
![UML class-diagram](/Documents/UMLclass-diagramChalmersDefense.png)
