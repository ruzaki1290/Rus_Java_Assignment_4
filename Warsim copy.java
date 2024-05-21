import warrior.*;
import weapon.*;
import armour.*;
import utility.*;

/* 1.) Track wins/losses in a seperate file (you will need to read/write to a file).
     a. Create a file where the results will be saved.
     b. Write a method that will save the the battle results.
     c. Write a method that will write the battle results to the file.
     d. Assign a number(to track) to each game???
   2.) Provide a way to view the battle history (win/loss record).
 */

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warsim {
  // Objects
  public static Scanner input = new Scanner(System.in);
  public static Random randNum = new Random();
  public static Ink ink = new Ink();
  public static IO io = new IO();
  public static List<Object> things = new ArrayList<>();
  
  // Player Objects
  public static Warrior player; // player
  public static Weapon pWeapon; // player weapon
  public static Armour pArmour; // player armour

  // Enemy Objects
  public static Warrior enemy; // enemy
  public static Weapon eWeapon; // enemy weapon
  public static Armour eArmour; // enemy armour

  // Game Vars
  public static boolean gameOver = false;
  public static boolean playerTurn = true; // player starts
  public static String who = "Player";
  public static String winner = "";
  public static int choice = 0;
  public static int attackType = 0;
  public static int damage = 0;
  
  //================>>
  // main method
  public static void main(String[] args) {

    // Prints welcome message w/ASCII art ;)
    ink.welcome();

    String filePath = "./gameSave.txt"; // specify the file path
    File file = new File(filePath); // create the file

    // Check if the file exists
    if (file.exists()) {
      // if the file exists give them the option!
      // Prompt the player to either a) continue b) play new game
      boolean isContinue = ink.continueGame(input);
      if(isContinue) {
        // open save file
        things = io.loadGame(player, pWeapon, pArmour, enemy, eWeapon, eArmour);
        player = (Warrior)things.get(0); // sets our local player
        pWeapon = (Weapon)things.get(1);
        pArmour = (Armour)things.get(2);
        enemy = (Warrior)things.get(3);
        eWeapon = (Weapon)things.get(4);
        eArmour = (Armour)things.get(5);
        
        // read in stats
        // create objects from stats
        // start the game
      }
      else {
        createGame();
      }
    } // if
    else {
      createGame();
    }

    //==================>>
    // Main Game Loop
    while(!gameOver) {
      if(playerTurn) {
        // player code
        ink.printTurnMenu();
        attackType = input.nextInt();
        if(attackType == 3) {
          gameOver = !gameOver;
          break; // breaks out of the while loop
        }
        if(attackType == 4) {
          // save game
          io.saveGame(player, pWeapon, pArmour, enemy, eWeapon, eArmour);
          gameOver = !gameOver;
          break;
        }
        damage = pWeapon.strike(attackType, 
          player.getDexterity(),
          player.getStrength());

        // take the damage and pass it into the enemy's armour
        // to reduce the damage
        if(damage > 0) { // if there is damage at all
          damage = eArmour.getFinalDamage(damage);
          
          // assign the damage amount to the enemy
          enemy.takeDamage(damage);

          // check to see if the enemy is dead!
          if(enemy.getHealth() <= 0) { // enemy is dead!
            winner = "Player";
            gameOver = !gameOver;
          }
        }
        else { // missed!
          System.out.println("Missed no damage!");
        }
      }
      else {
        // enemy code
        attackType = randNum.nextInt(2) + 1;
        damage = eWeapon.strike(attackType, 
          enemy.getDexterity(),
          enemy.getStrength());

        // take the damage and pass it into the player's armour
        // to reduce the damage
        if(damage > 0) { // if there is damage at all
          damage = pArmour.getFinalDamage(damage);
          
          // assign the damage amount to the player
          player.takeDamage(damage);

          // check to see if the player is dead!
          if(player.getHealth() <= 0) { // player is dead!
            winner = "Enemy";
            io.saveGameResults(winner);
            gameOver = !gameOver;
          }
        }
      }
    playerTurn = !playerTurn; // toggles turn each iteration
  } // while
  if(attackType != 4){ 
    ink.printGameOver(winner);
    io.saveGameResults(winner);
}
  else 
    ink.printSaveConfirmation();
} // main()

  //========================>>
  // Helper Methods
  public static void createWarrior(String who, int choice) {
    if(who.equals("Player")) { // player warrior creation
      switch (choice) {
        case 1: // Human
          player = new Human();
          player.setWarriorType("Human");
          break;
        case 2: // Elf
          player = new Elf();
          player.setWarriorType("Elf");
          break;
        case 3: // Orc
          player = new Orc();
          player.setWarriorType("Orc");
          break;
        default:
          System.out.println("Oops!");
          break;
      } // switch
    }
    else { // enemy warrior creation
      switch (choice) {
        case 1: // Human
          enemy = new Human();
          enemy.setWarriorType("Human");
          break;
        case 2: // Elf
          enemy = new Elf();
          enemy.setWarriorType("Elf");
          break;
        case 3: // Orc
          enemy = new Orc();
          enemy.setWarriorType("Orc");
          break;
        default:
          System.out.println("Oops!");
          break;
      } // switch
    }
  } // createWarrior()

  public static void createWeapon(String who, int choice) {
    switch (choice) {
      case 1: // Dagger
        if(who.equals("Player")) {
          pWeapon = new Dagger("Dagger");
        }
        else {
          eWeapon = new Dagger("Dagger");
        }
        break;
      case 2: // Sword
        if(who.equals("Player")) {
          pWeapon = new Sword("Sword");
        }
        else {
          eWeapon = new Sword("Sword");
        }
        break;
      case 3: // Axe
        if(who.equals("Player")) {
          pWeapon = new Axe("Axe");
        }
        else {
          eWeapon = new Axe("Axe");
        }
        break;
      default:
        System.out.println("Oops!");
        break;
    } // switch
  } // createWeapon()

  public static void createArmour(String who, int choice) {
    switch (choice) {
      case 1: // Leather
        if(who.equals("Player")) {
          pArmour = new Leather("Leather");
        }
        else {
          eArmour = new Leather("Leather");
        }
        break;
      case 2: // Chainmail
        if(who.equals("Player")) {
          pArmour = new Chainmail("Chainmail");
        }
        else {
          eArmour = new Chainmail("Chainmail");
        }
        break;
      case 3: // Platemail
        if(who.equals("Player")) {
          pArmour = new Platemail("Platemail");
        }
        else {
          eArmour = new Platemail("Platemail");
        }
        break;
      default:
        System.out.println("Oops!");
        break;
    } // switch
  } // createArmour()

  public static void createGame() {
    //=====================>>
    // Player Creation
    // Warrior
    ink.printWarriorMenu();
    choice = input.nextInt();
    createWarrior(who, choice);
    
    // Weapon
    ink.printWeaponMenu();
    choice = input.nextInt();
    createWeapon(who, choice);
    
    // Armour
    ink.printArmourMenu();
    choice = input.nextInt();
    createArmour(who, choice);

    // player is all setup
    // switch 'who' to Enemy
    who = "Enemy";

    //=====================>>
    // Enemy Creation
    // Warrior
    choice = randNum.nextInt(3) + 1;
    createWarrior(who, choice);

    // Weapon
    choice = randNum.nextInt(3) + 1; 
    createWeapon(who, choice);

    // Armour
    choice = randNum.nextInt(3) + 1; 
    createArmour(who, choice);

    ink.printStats(player, enemy);
  } // createGame()

} // class