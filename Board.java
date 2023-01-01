// Max Noddings
// CS110
// Final Project!

// Import arraylists and scanner!
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
The board class. Will have instance variables: layout which is a two dimensional arraylist (arraylist inside of an arraylist), fleet which is 
a fleet, and SIZE which is an int. 
*/
public class Board
{
   private ArrayList<ArrayList<CellStatus>> layout;
   private Fleet fleet;
   private final static int SIZE = 10;
   private boolean ifSunk;
   
   /**
   Board Constructor. Initializes the layout. Sets all cells (or elements in the arraylist) to CellStatus.NOTHING. Calls loadFromFile()
   and passes it a filename - the board is then updated with the ships in place. Then the constructor initializes fleet.  
   @param String filename
   */
   public Board(String filename)
   {
      // Initialize a single arraylist of cellstatus elements
      ArrayList<CellStatus> rowList;
      
      // Initialize layout.
      layout = new ArrayList<ArrayList<CellStatus>>(SIZE);
      
      // For each element in layout.
      for (int row = 0; row < SIZE; row++)
      {
         // Create an Arraylist of 10 CellStatus Elements.
         rowList = new ArrayList<CellStatus>(SIZE);
         
         // For each element in rowList.
         for (int col = 0; col < SIZE; col++)
         {
            rowList.add(CellStatus.NOTHING);
         }
         
         // Add the rowList to the layout.
         layout.add(rowList);
      }
      
      // Call the loadFromFile method to update the board with battleships.
      loadFromFile(filename);
      
      // Initialize a fleet.
      fleet = new Fleet();
   }
   
   
   /**
   Load From file method. This will take in a filename, opens the filename, and then updates the grid layout according to the file contents. 
   @param String filename
   */
   
   public void loadFromFile(String filename)
   {
      //Declare variables that will be used.
      String battleShip;
      CellStatus craft;
      String coor1;
      String coor2;
      int rowIndex;
      int colIndex;
      int endRowIndex;
      int endColIndex;
      
      //Try/catch
      try
      {
         // Open the file.
         File file = new File(filename);
         Scanner inFile = new Scanner(file);
         
         // While the file has a next line, read in the battleship!
         while (inFile.hasNext())
         {
            battleShip = inFile.nextLine();
            
            // Series of if statments to determine what type of craft the battleship is.
            if (battleShip.charAt(0) == 'A')
               craft = CellStatus.AIRCRAFT_CARRIER;
            else if (battleShip.charAt(0) == 'B')
               craft = CellStatus.BATTLESHIP;
            else if (battleShip.charAt(0) == 'C')
               craft = CellStatus.CRUISER;
            else if (battleShip.charAt(0) == 'D')
            {
               craft = CellStatus.DESTROYER;
            }
            else 
               craft = CellStatus.SUB;
            
            // Assign the coordinates of the beginning of the battleShip. Trim the coordinate for any potential whitespaces.
            coor1 = battleShip.substring(2,5);
            coor1 = coor1.trim();
            
            // Assign the coordinated of the end of the battleShip. Use if statments to determine whether or not the specific coordinate has a 10.
            if (battleShip.length() == 9)
               coor2 = battleShip.substring(6,9);
            else if (battleShip.length() == 8)
            {
               coor2 = battleShip.substring(5,8);
               coor2 = coor2.trim();
            }
            else
               coor2 = battleShip.substring(5,7);
            
            // Series of if statements to determine what the row index the battleship will be placed at.
            if (coor1.charAt(0) == 'A')
               rowIndex = 0;
            else if (coor1.charAt(0) == 'B')
               rowIndex = 1;
            else if (coor1.charAt(0) == 'C')
               rowIndex = 2;
            else if (coor1.charAt(0) == 'D')
               rowIndex = 3;
            else if (coor1.charAt(0) == 'E')
               rowIndex = 4;
            else if (coor1.charAt(0) == 'F')
               rowIndex = 5;
            else if (coor1.charAt(0) == 'G')
               rowIndex = 6;
            else if (coor1.charAt(0) == 'H')
               rowIndex = 7;
            else if (coor1.charAt(0) == 'I')
               rowIndex = 8;
            else 
               rowIndex = 9;
            
            // Assign the column index number! If the coordinate has a length of 3, you know it will be column 10 - index 9.
            if (coor1.length() == 3)
               colIndex = 9;
            else
               colIndex = Character.getNumericValue(coor1.charAt(1)) - 1;
            
            
            // If the rows (letters) of coor1 and coor2 are the same, iterate through the columb numbers, else iterate through the rows.
            if (coor1.charAt(0) == coor2.charAt(0))
            {
               // Assign the ending column index number in the same fashion as above.
               if (coor2.length() == 3)
               {
                  endColIndex = 9;
               }
               else
                  endColIndex = Character.getNumericValue(coor2.charAt(1)) - 1;
               
               // Assign spots on the grid to the craft.
               for (int i = colIndex; i <= endColIndex; i++)
                  layout.get(rowIndex).set(i, craft);
            }
            else // the columbs of coor1 and coor2 are the same
            {
               // Series of if statements to determine what the row index is the end.
               if (coor2.charAt(0) == 'A')
                  endRowIndex = 0;
               else if (coor2.charAt(0) == 'B')
                  endRowIndex = 1;
               else if (coor2.charAt(0) == 'C')
                  endRowIndex = 2;
               else if (coor2.charAt(0) == 'D')
                  endRowIndex = 3;
               else if (coor2.charAt(0) == 'E')
                  endRowIndex = 4;
               else if (coor2.charAt(0) == 'F')
                  endRowIndex = 5;
               else if (coor2.charAt(0) == 'G')
                  endRowIndex = 6;
               else if (coor2.charAt(0) == 'H')
                  endRowIndex = 7;
               else if (coor2.charAt(0) == 'I')
                  endRowIndex = 8;
               else 
                  endRowIndex = 9;
                  
               // Assign spots on the grid to the craft.
               for (int i = rowIndex; i <= endRowIndex; i++)
                  layout.get(i).set(colIndex, craft);
            }    
         }
      }
      
      catch (FileNotFoundException e)
      {
         System.out.println("Problem loading battleships. Board not updated.");
      }
   }
   
   
   
   /**
   Apply move to layout method will take in a move and then update the board based on that move. It will
   check to see what the curent cell status is and then change it based on what is currently there. It will
   return whatever the orginal cell status was.
   @param Move strike
   @return CellStatus 
   */
   public CellStatus applyMoveToLayout(Move strike)
   {
      // Create a variable for the target coordinates of the move, and the specifc row and column of the strike, also a variable 
      // assigned to if the ship is sunk or not!
      ifSunk = false;
      int targetRow = strike.row();
      int targetCol = strike.col(); // Subtract one because the index starts at zero in an ArrayList
      CellStatus target = layout.get(targetRow).get(targetCol); // ERROR!!! Exception in thread "main" java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 10

      
      // Series of if statments to determine how to handle the targeted coordinate.
      if (target == CellStatus.NOTHING)
      {
         layout.get(targetRow).set(targetCol, CellStatus.NOTHING_HIT);
         return CellStatus.NOTHING;
      }
      else if (target == CellStatus.SUB)
      {
         layout.get(targetRow).set(targetCol, CellStatus.SUB_HIT);
         ifSunk = fleet.updateFleet(ShipType.ST_SUB);
         if (ifSunk == true)
         {
            changeToSunk(CellStatus.SUB_HIT);
         }
         return CellStatus.SUB;
      }
      else if (target == CellStatus.DESTROYER)
      {
         layout.get(targetRow).set(targetCol, CellStatus.DESTROYER_HIT);
         ifSunk = fleet.updateFleet(ShipType.ST_DESTROYER);
         if (ifSunk == true)
         {
            changeToSunk(CellStatus.DESTROYER_HIT);
         }
         return CellStatus.DESTROYER;
      }
      else if (target == CellStatus.CRUISER)
      {
         layout.get(targetRow).set(targetCol, CellStatus.CRUISER_HIT);
         ifSunk = fleet.updateFleet(ShipType.ST_CRUISER);
         if (ifSunk == true)
         {
            changeToSunk(CellStatus.CRUISER_HIT);
         }
         return CellStatus.CRUISER;
      }
      else if (target == CellStatus.BATTLESHIP)
      {
         layout.get(targetRow).set(targetCol, CellStatus.BATTLESHIP_HIT);
         ifSunk = fleet.updateFleet(ShipType.ST_BATTLESHIP);
         if (ifSunk == true)
         {
            changeToSunk(CellStatus.BATTLESHIP_HIT);
         }
         return CellStatus.BATTLESHIP;
      }
      else if (target == CellStatus.AIRCRAFT_CARRIER)
      {
         layout.get(targetRow).set(targetCol, CellStatus.AIRCRAFT_CARRIER_HIT);
         ifSunk = fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
         if (ifSunk == true)
         {
            changeToSunk(CellStatus.AIRCRAFT_CARRIER_HIT);
         }
         return CellStatus.AIRCRAFT_CARRIER;
      }
      else // The target has already been targeted.
      {
         if (target == CellStatus.NOTHING_HIT)
         {
            return CellStatus.NOTHING_HIT;
         }
         else if (target == CellStatus.SUB_HIT)
         {
            return CellStatus.SUB_HIT;
         }
         else if (target == CellStatus.DESTROYER_HIT)
         {
            return CellStatus.DESTROYER_HIT;
         }
         else if (target == CellStatus.CRUISER_HIT)
         {
            return CellStatus.CRUISER_HIT;
         }
         else if (target == CellStatus.BATTLESHIP_HIT)
         {
            return CellStatus.BATTLESHIP_HIT;
         }
         else
         {
            return CellStatus.AIRCRAFT_CARRIER_HIT;
         }
      } 
   }
   
   
   
   /**
   moveTaken method takes in a move and determines if the spot has already been moved upon!
   @param Move check
   @return boolean
   */
   public boolean moveTaken(Move check)
   {
      // Create a variable for the target coordinate.
      CellStatus target = layout.get(check.row()).get(check.col());
       
      // An if statment to check!
      if (target == CellStatus.NOTHING || target == CellStatus.SUB || target == CellStatus.DESTROYER || 
      target == CellStatus.CRUISER || target == CellStatus.BATTLESHIP || target == CellStatus.AIRCRAFT_CARRIER)
      {
         return false;
      }
      else
         return true;     
   }
   
   
   
   /**
   getLayout method returns a copy of the layout!
   @return ArrayList<ArrayList<CellStatus>> layout
   */
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      return layout;
   }
   
   
   
   /**
   getFleet method returns a reference to the Fleet object.
   @return Fleet fleet
   */
   public Fleet getFleet()
   {
      return fleet;
   }
   
   /**
   get ifSunk method returns the ifSunk boolean value.
   @return boolean ifSunk
   */
   public boolean getIfSunk()
   {
      return ifSunk;
   }
   
   
   
   /**
   The gameOver method returns true if all ship have been sunk and false othrwise.
   @return boolean
   */
   public boolean gameOver()
   {
     // The game is over if the gameOver method in fleet is true, else it is not over yet!
     return fleet.gameOver(); 
   }
   
   
   /**
   The changeToSunk method will take in the cell status of a particular ship that has been hit and then change its cell status to SUNK!
   @param CellStatus shipStatus
   */
   public void changeToSunk(CellStatus shipStatus)
   {
      // If the ship status is SUB_HIT, iterate through each element in the array and change each SUB_HIT element to SUB_SUNK.
      if (shipStatus == CellStatus.AIRCRAFT_CARRIER_HIT)
      {
         //For each element in layout.
         for (int row = 0; row < SIZE; row++)
         {
            for (int col = 0; col < SIZE; col++)
            {
               // If the cell status equals the shipStatus, change it to SUNK!
               if (shipStatus == layout.get(row).get(col))
               {
                  layout.get(row).set(col, CellStatus.AIRCRAFT_CARRIER_SUNK);
               }
            }
         }
      }
      else if (shipStatus == CellStatus.BATTLESHIP_HIT)
      {
         //For each element in layout.
         for (int row = 0; row < SIZE; row++)
         {
            for (int col = 0; col < SIZE; col++)
            {
               // If the cell status equals the shipStatus, change it to SUNK!
               if (shipStatus == layout.get(row).get(col))
                  layout.get(row).set(col, CellStatus.BATTLESHIP_SUNK);
            }
         }
      }
      else if (shipStatus == CellStatus.CRUISER_HIT)
      {
         //For each element in layout.
         for (int row = 0; row < SIZE; row++)
         {
            for (int col = 0; col < SIZE; col++)
            {
               // If the cell status equals the shipStatus, change it to SUNK!
               if (shipStatus == layout.get(row).get(col))
                  layout.get(row).set(col, CellStatus.CRUISER_SUNK);
            }
         }
      }
      else if (shipStatus == CellStatus.DESTROYER_HIT)
      {
         //For each element in layout.
         for (int row = 0; row < SIZE; row++)
         {
            for (int col = 0; col < SIZE; col++)
            {
               // If the cell status equals the shipStatus, change it to SUNK!
               if (shipStatus == layout.get(row).get(col))
                  layout.get(row).set(col, CellStatus.DESTROYER_SUNK);
            }
         }
      }
      else if (shipStatus == CellStatus.SUB_HIT)
      {
         //For each element in layout.
         for (int row = 0; row < SIZE; row++)
         {
            for (int col = 0; col < SIZE; col++)
            {
               // If the cell status equals the shipStatus, change it to SUNK!
               if (shipStatus == layout.get(row).get(col))
                  layout.get(row).set(col, CellStatus.SUB_SUNK);
            }
         }
      }
   }
} 