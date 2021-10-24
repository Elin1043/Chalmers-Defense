# Chalmers Defense

[![Build Status](https://app.travis-ci.com/Danilll01/TDA367-OO-Projekt.svg?branch=main)](https://app.travis-ci.com/Danilll01/TDA367-OO-Projekt)

Currently just a very simple startup thing with a graphics library added to it. Will be used as a base to hopefully
create a tower defense game based on defending Chalmers from corona viruses that comes along a path to infect Chalmers.

## Filestructure
Path                                    | Comment
--------------------------------------- | -------------
`/Documents`                            | All documents accociated with the project
`/core/src/com/mygdx/chalmersdefense`   | The main folder for the code
`/desktop/src/test/java/`               | Folder containing all JUnit tests
`/core/assets`                          | Location for all assets in the game


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
* Currently the application does not work properly for Mac OS. The program is able to start and run but a problem occurs regarding the circle around the towers. They appear slightly oval and the positioning is incorrect.
* Changing from fullscreen to window mode on a Mac causes the program to crash. 

Note: Same problems as above can be expected for Linux.

### Scaling
* When exiting the fullscreen mode the text sometimes becomes hard to read because of a scaling issue with libGDX. 

## Current UML class-diagram
![UML class-diagram](/Documents/UMLclass-diagramChalmersDefense.png)
