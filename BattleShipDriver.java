// Max Noddings
// CS110
// Final Project!

// Imports
import java.io.*;
import java.util.Scanner;
import java.util.Random;

/**
The Battleship driver class will fully implement the game class and will put everything togather into a full game of
battleship! 
*/
public class BattleShipDriver
{
   public static void main(String[] args) throws IOException
   {
      // Create a game!
      Game battleShip = new Game();
      
      // Create a scanner object!
      Scanner keyboard = new Scanner(System.in);
      
      // Initialize a variable for a string array! Also, initialize  a move variable! Initialize a boolean validInput variable!
      String[] computerMoveList;
      Move move;
      boolean validInput = false;
      String strMove = null;
      
      // Print an introductory line!
      System.out.print("Welcome to Battleship!\n\n");
      
      /* Decide who will go first. Pick a random number between 0 and 1. If 0 the computer will make its first move, 
      if 1 the user will be prompted to make their first move. */
      Random rand = new Random();
      int whosTurn = rand.nextInt(2);
      if (whosTurn == 0)
      {
         System.out.print("The computer won the coin toss and gets to go first.\nPress enter to continue!\n");
         keyboard.nextLine();
         computerMoveList = battleShip.makeComputerMove();
         System.out.print("The computer chose: " + computerMoveList[0] + "\n");
         whosTurn = 1;
      }
      else // It is the users turn.
      {
         // Get the strike coordinates from the user.
         System.out.print("You won the coin toss and get to go first.\n");
         System.out.print("Move? ");
         strMove = keyboard.nextLine().toUpperCase();
         
         // Validate input!
         while (validInput == false)
         {
            if ((strMove.length() > 3) || (strMove.length() < 2) || ((strMove.charAt(0) != 'A') && (strMove.charAt(0) != 'B') && (strMove.charAt(0) != 'C') && (strMove.charAt(0) != 'D')
                  && (strMove.charAt(0) != 'E') && (strMove.charAt(0) != 'F') && (strMove.charAt(0) != 'G') && (strMove.charAt(0) != 'H') 
                  && (strMove.charAt(0) != 'I') && (strMove.charAt(0) != 'J')) || ((strMove.charAt(1) != '1') && (strMove.charAt(1) != '2')
                  && (strMove.charAt(1) != '3') && (strMove.charAt(1) != '4') && (strMove.charAt(1) != '5') && (strMove.charAt(1) != '6')
                  && (strMove.charAt(1) != '7') && (strMove.charAt(1) != '8') && (strMove.charAt(1) != '9')) || ((strMove.length() == 3) 
                  && (Integer.parseInt(strMove.substring(1,3)) != 10)))
            {
               System.out.print("Invalid Coordinate, try again: ");
               strMove = keyboard.nextLine().toUpperCase();
            } 
            else
            {
               validInput = true;
            }
         }
         
         // Set validInput back to false for future iterations of input validation. 
         validInput = false;
         
         // Make the move!
         battleShip.makePlayerMove(strMove);
         whosTurn = 0;
      }
      
      // Print out the computer board and the user board!
      System.out.print(battleShip);
      
      // While the computer and the user isn't defeated:
      while (battleShip.computerDefeated() == false && battleShip.userDefeated() == false)
      {
         if (whosTurn == 0) // If it is the computers turn.
         {
            System.out.print("It is now the computers turn.\nPress enter to continue!\n");
            keyboard.nextLine();
            computerMoveList = battleShip.makeComputerMove();
            System.out.print("The computer chose: " + computerMoveList[0] + "\n");
            whosTurn = 1;
         }
         else // It is the users turn
         {
            System.out.print("Your turn: ");
            strMove = keyboard.nextLine().toUpperCase();
            
            // Validate input!
            while (validInput == false)
            {
               while ((strMove.length() > 3) || (strMove.length() < 2) || ((strMove.charAt(0) != 'A') && (strMove.charAt(0) != 'B') && (strMove.charAt(0) != 'C') && (strMove.charAt(0) != 'D')
                     && (strMove.charAt(0) != 'E') && (strMove.charAt(0) != 'F') && (strMove.charAt(0) != 'G') && (strMove.charAt(0) != 'H') 
                     && (strMove.charAt(0) != 'I') && (strMove.charAt(0) != 'J')) || ((strMove.charAt(1) != '1') && (strMove.charAt(1) != '2')
                     && (strMove.charAt(1) != '3') && (strMove.charAt(1) != '4') && (strMove.charAt(1) != '5') && (strMove.charAt(1) != '6')
                     && (strMove.charAt(1) != '7') && (strMove.charAt(1) != '8') && (strMove.charAt(1) != '9')) || ((strMove.length() == 3) 
                     && (Integer.parseInt(strMove.substring(1,3)) != 10)))
               {
                  System.out.print("Invalid Coordinate, try again: ");
                  strMove = keyboard.nextLine().toUpperCase();
               } 

               // Create a move object! This will also go into the moveTaken method.
               move = new Move(strMove);
               
               // Check to see if the move has been taken!
               if (battleShip.getComputerBoard().moveTaken(move))
               {
                  System.out.print("Location not available, try again: ");
                  strMove = keyboard.nextLine().toUpperCase();
                  validInput = false;
               }
               else
               {
                  validInput = true;
               }
            }
            
            // Set validInput back to false for future iterations of input validation. 
            validInput = false;

            // Make the move!           
            battleShip.makePlayerMove(strMove);
            whosTurn = 0;
         }
         
         // Print out the computer board and the user board!
         System.out.print(battleShip);
      }
      
      // Display a game over message indicating who won.
      if (battleShip.computerDefeated() == true)
         System.out.println("GAME OVER\nYou won! You beat the computer.");
      else
         System.out.println("GAME OVER\nThe computer won! You were beaten by the computer.");
   }
}