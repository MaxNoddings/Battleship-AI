// Max Noddings
// CS110
// Final Project!

// Import ArrayList and Random!
import java.util.ArrayList;
import java.util.Random;

/**
The UserBoard class! Will have instance variables moves which is an ArrayList, and rand which is of type Random. Also there will be a instance variable
that tracks the size of the moves array since the array will decrease by one each time a move is used.
*/
public class UserBoard extends Board
{
   private ArrayList<Move> moves;
   private Random rand;
   private int movesLeft = 100;
   
   /**
   Make a constructor that takes in a String filename and passes it to the Board constructor.
   Also, this constructor will initialize the rand and move instance variables. To Initialize the moves
   array list, have a for loop inside of a for loop that adds all the possible moves on the 10 x 10 board.
   @param String filename
   */
   public UserBoard(String filename)
   {
      super(filename);
      
      // Initialize random object.
      rand = new Random();
      
      // Create moves ArrayList with a size of 100.
      moves = new ArrayList<Move>(100);
      
      // For loop inside of a for loop that adds all the possible moves to the moves arraylist.
      for (int row = 1; row < 11; row++)
      {
         for (int col = 1; col < 11; col++)
         {
            moves.add(new Move(row,col));
         }
      }
   }
   
   
   
   
   /**
   makeComputerMove method! Randomly selects a move from the moves ArrayList and then makes that 
   computer move against UserBoard. Doesn't take in anything, but
   returns an array of two Strings. The first String is the move that the computer made in a user 
   readable form "C6". The second String is either null, or if the move resulted in being sunk the 
   String is something along the lines of "You sunk my Battleship." . 
   @return String[]
   */
   public String[] makeComputerMove()
   {
      // Create an Array that will be what the method returns. It will have a size of 2. Also,
      // create a variable that will be assigned to a spefic index in the moves array!
      String[] returnStrings = new String[2];
      int moveIndex;
      
      // Generate a random move and apply it to the UserBoard layout. After that move is applied,
      // delete it from the arrayList so that move cannot be made again!
      moveIndex = rand.nextInt(movesLeft);
      Move randomMove = moves.get(moveIndex);
      CellStatus typeHIT = super.applyMoveToLayout(randomMove);
      System.out.println(typeHIT);
      moves.remove(moveIndex);
      movesLeft--;
      
      // Add the randomMove to the returnString ArrayList.
      returnStrings[0] = randomMove.toString();
      
      // If the random move caused a hit...
      if (typeHIT == CellStatus.AIRCRAFT_CARRIER)
      {
         if (super.getFleet().getAircraftCarrier().getSunk() == true)
            returnStrings[1] = "You sank my Aircraft Carrier!";
         else
            returnStrings[1] = null;
         return returnStrings;
      }
      // Add in more else if statments for other types of ships.
      else if (typeHIT == CellStatus.BATTLESHIP)
      {
         if (super.getFleet().getBattleship().getSunk() == true)
            returnStrings[1] = "You sank my Battleship!";
         else
            returnStrings[1] = null;
         return returnStrings;
      }
      else if (typeHIT == CellStatus.CRUISER)
      {
         if (super.getFleet().getCruiser().getSunk() == true)
            returnStrings[1] = "You sank my Cruiser!";
         else
            returnStrings[1] = null;
         return returnStrings;
      }
      else if (typeHIT == CellStatus.DESTROYER)
      {
         if (super.getFleet().getDestroyer().getSunk() == true)
            returnStrings[1] = "You sank my Destroyer!";
         else
            returnStrings[1] = null;
         return returnStrings;
      }
      else if (typeHIT == CellStatus.SUB)
      {
         if (super.getFleet().getSub().getSunk() == true)
            returnStrings[1] = "You sank my Submarine!";
         else
            returnStrings[1] = null;
         return returnStrings;
      }
      else
      {
         returnStrings[1] = null;
         return returnStrings;
      }
   }
   
   
   
   /**
   toString method that will fully display the UserBoard! Pretty Similar to the toString for the ComputerBoard, but for
   this one it will take the second character of the toString from CellStatus.
   @return String
   */
   public String toString()
   {
      // Create the ArrayList with Strings A through J
      ArrayList<String> alphaRowList = new ArrayList<String>(10);
      alphaRowList.add("A");
      alphaRowList.add("B");
      alphaRowList.add("C");
      alphaRowList.add("D");
      alphaRowList.add("E");
      alphaRowList.add("F");
      alphaRowList.add("G");
      alphaRowList.add("H");
      alphaRowList.add("I");
      alphaRowList.add("J");
      
      // Create a string variable the will be constantly added to and then returned in this method! Add the 
      // first line to this variable.
      String userBoardDisplay = "USER\n\t";
      
      // Add the top line of column numbers to UBD.
      for (int i = 1; i < 11; i++)
      {
         userBoardDisplay += i + "\t";
      }
      
      // Add a newline character
      userBoardDisplay += "\n";
      
      // For each row in the layout.
      for (int row = 0; row < 10; row++)
      {
         // Add the letter of the row to UBD with a tab after.
         userBoardDisplay += alphaRowList.get(row) + "\t";
         
         for (int col = 0; col < 10; col++)
         {
            // Add the status of the specific computer coordinate to UBD. Then add a tab character.
            userBoardDisplay += (super.getLayout().get(row).get(col).toString().charAt(1) + "\t");
         }
         
         // Add a newline character.
         userBoardDisplay += "\n";
      }
      
      // Return UBD!
      return userBoardDisplay;
   }
}