// Max Noddings
// CS110
// Final Project!

/**
The game class will put togather many of the other classes already created into one! The instance variables will
be computer which will be a ComputerBoard and player which will be a UserBoard. This class will have one constructor
and 5 other methods.
*/
public class Game
{
   // Instance variables.
   private ComputerBoard computer;
   private UserBoard2 player;
   
   
   
   /**
   The game will create two boards - a computer board and a user board. Therefore, it will initialize the computer
   and player instance variables!
   */
   public Game()
   {
      computer = new ComputerBoard("compFleet.txt");
      player = new UserBoard2("userFleet.txt");
   }
   
   
   
   /**
   The makeComputerMove method will make a computer move on the users board. To do this, just call the 
   makeComputerMove method from the UserBoard class! It will return an array of two Strings.
   @return String[]  
   */
   public String[] makeComputerMove()
   {
      return player.makeComputerMove();
   }
   
   
   
   /**
   The makePlayerMove method will call the makePlayerMove method from the ComputerBoard class! It will take in a string
   that represents the move to be made! Then it will create a new move from that string. It will then put that new move
   into the makePlayerMove method from the ComputerBoard class! It will return a String that is either null or a string 
   signaling that you sunk a ship!
   @param String coordinate
   @return String
   */
   public String makePlayerMove(String coordinate)
   {
      Move strike = new Move(coordinate);
      return computer.makePlayerMove(strike);
   }
   
   
   
   /**
   The computerDefeated method will check to see if the computer-player has been defeated in the game. It will not take in
   anything. It will only return a boolean!
   */
   public boolean computerDefeated()
   {
      // return the gameOver method from the computerBoard which extends board!
      return computer.gameOver();
   }
   
   
   
   /**
   The userDefeated method will check to see if the user-player has been defeated in the game. It will not take in
   anything. It will only return a boolean!
   */
   public boolean userDefeated()
   {
      // return the gameOver method from the userBoard which extends the Board class.
      return player.gameOver();
   }
   
   
   
   /**
   The toString method will return the string representations of both Boards in a nicely formatted way!
   @return String bothBoards
   */
   public String toString()
   {
      // First initialize a new String that will be added to and then returned at the end! Add the computer board to bothBoards.
      String bothBoards = computer.toString();
      
      // Add a newline character to bothBoards to have one space in between the computer board and the users board.
      bothBoards += "\n";
      
      // Add the user board to bothBoards and then return bothBoards.
      bothBoards += player.toString();
      return bothBoards;
   }
   
   
   
   /**
   Getter for the computer board!
   @return ComputerBoard computer
   */
   public ComputerBoard getComputerBoard()
   {
      return computer;
   }
   
   
   
   /**
   Getter for the player board!
   @return UserBoard player
   */
   public UserBoard2 getPlayerBoard()
   {
      return player;
   }
}


