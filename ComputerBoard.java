// Max Noddings
// CS110
// Final Project!

// Import ArrayList!
import java.util.ArrayList;

/**
The ComputerBoard class!
*/
public class ComputerBoard extends Board
{
   /**
   Constructor that takes in a string for the filename and passas the filename to the 
   Board constructor.
   @param filename
   */
   public ComputerBoard(String filename)
   {
      super(filename);
   }
   
   
   
   /**
   the makePlayerMove method will take in a move and make it aainst the computer board. Uses the applyMoveToLayout method
   from board. It returns either null or a String "You sank the ship" if the move sank a ship.
   @param Move strike
   @return String
   */
   public String makePlayerMove(Move strike)
   {
      // Apply the move to the layout and update the fleet if it hit. Assign it to a variable!
      CellStatus typeHIT = super.applyMoveToLayout(strike);
      
      // If the strike hit a battleship, check to see if the battleship is sunk! If it is sunk return a message, else return null.
      if (typeHIT == CellStatus.AIRCRAFT_CARRIER)
      {
         if (super.getFleet().getAircraftCarrier().getSunk() == true)
            return String.format("You sank my Aircraft Carrier!");
         else
            return null;
      }
      // Add in more else if statments for other types of ships.
      else if (typeHIT == CellStatus.BATTLESHIP)
      {
         if (super.getFleet().getBattleship().getSunk() == true)
            return String.format("You sank my Battleship!");
         else
            return null;
      }
      else if (typeHIT == CellStatus.CRUISER)
      {
         if (super.getFleet().getCruiser().getSunk() == true)
            return String.format("You sank my Cruiser!");
         else
            return null;
      }
      else if (typeHIT == CellStatus.DESTROYER)
      {
         if (super.getFleet().getDestroyer().getSunk() == true)
            return String.format("You sank my Destroyer!");
         else
            return null;
      }
      else if (typeHIT == CellStatus.SUB)
      {
         if (super.getFleet().getSub().getSunk() == true)
            return String.format("You sank my Submarine!");
         else
            return null;
      }
      else
         return null;
   }
   
   
   
   /**
   Make a toString method that will fully display the ComputerBoard!!! Make an arrayList with strings A through J to make the
   iterating and adding proscess better.
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
      
      // Create a string variable the will be constatly added to and then returned in this method! Add the 
      // first line to this variable.
      String computerBoardDisplay = "COMPUTER\n\t";
      
      // Add the top line of column numbers to CBD.
      for (int i = 1; i < 11; i++)
      {
         computerBoardDisplay += i + "\t";
      }
      
      // Add a newline character
      computerBoardDisplay += "\n";
      
      // For each row in the layout.
      for (int row = 0; row < 10; row++)
      {
         // Add the letter of the row to CBD with a tab after.
         computerBoardDisplay += alphaRowList.get(row) + "\t";
         
         for (int col = 0; col < 10; col++)
         {
            // Add the status of the specific computer coordinate to CBD. Then add a tab character.
            computerBoardDisplay += (super.getLayout().get(row).get(col).toString().charAt(0) + "\t");
         }
         
         // Add a newline character.
         computerBoardDisplay += "\n";
      }
      
      // Return CBD!
      return computerBoardDisplay; 
   }
}