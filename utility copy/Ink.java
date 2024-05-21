package utility;
import java.util.Scanner;

import warrior.*;

/*
 * Class for all of our in games prints
 */
public class Ink {
  
  public Ink() {
    // do nothing
  } // constructor

  public void welcome() {
    System.out.printf("""
      __        __             _             ____   ___ ____  _  _   
      \\ \\      / /_ _ _ __ ___(_)_ __ ___   |___ \\ / _ \\___ \\| || |  
       \\ \\ /\\ / / _` | '__/ __| | '_ ` _ \\    __) | | | |__) | || |_ 
        \\ V  V / (_| | |  \\__ \\ | | | | | |  / __/| |_| / __/|__   _|
         \\_/\\_/ \\__,_|_|  |___/_|_| |_| |_| |_____|\\___/_____|  |_|  
        """);
  } // welcome()

  public boolean continueGame(Scanner input) {
    boolean isContinue = false;
    System.out.println("(1) continue game (2) new game");
    int choice = input.nextInt();
    if(choice == 1) {
      isContinue = !isContinue;
    }
    return isContinue;
  } // continue()

  public void printWarriorMenu() {
    System.out.println("###################################################");
    System.out.println("Pick your Warrior: \nHuman(1)\nElf(2)\nOrc(3)\n");
    System.out.println("###################################################\n");
  }

  public void printWeaponMenu() {
    System.out.println("###################################################");
    System.out.println("Pick your Weapon: \n(1) DAGGER\n(2) SWORD\n(3) AXE");
    System.out.println("###################################################\n");
  }

  public void printArmourMenu() {
    System.out.println("###################################################");
    System.out.println("Pick your Armour: \n(1) LEATHER\n(2) CHAINMAIL\n(3) PLATEMAIL");
    System.out.println("###################################################\n");
  }

  public void printSaveConfirmation() {
    System.out.println("Your game has been saved!");
  } // printSaveConfirmation()

  public void printTurnMenu() {
    System.out.println("###################################################");
    System.out.println("Attack Type?: (1) NORMAL (2) HEAVY (3) EXIT (4) SAVE");
    System.out.println("###################################################\n");
  }

  public void printStats(Warrior player, Warrior enemy) {
    System.out.println("\n****************************************************");
    System.out.printf("* %-33s%-33s\n",
      "Player: " + player.getWarriorType(), "Enemy: " + enemy.getWarriorType());
    System.out.printf("* Health: %-25dHealth: %-25d\n", player.getHealth(), 
      enemy.getHealth());
    System.out.printf("* Strength: %-23dStrength: %-23d\n", 
      player.getStrength(), enemy.getDexterity());
    System.out.printf("* Dexterity: %-22dDexterity: %-22d\n", 
      player.getDexterity(), enemy.getDexterity());
    System.out.println("****************************************************\n");
  }

  public void printGameOver(String winner) {
    System.out.printf("""
       ____                         ___                 
      / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ 
     | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|
     | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |   
      \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|\n %s   
        """, winner + " wins!");
  } // printGameOver()

} // class
