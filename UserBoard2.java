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
public class UserBoard2 extends Board
{
   private ArrayList<Move> moves;
   private Random rand;
   private int movesLeft = 100;
   private int computerMoveType;
   private Move lastHit;
   private Move randomMove;
   
   /**
   Make a constructor that takes in a String filename and passes it to the Board constructor.
   Also, this constructor will initialize the rand and move instance variables. To Initialize the moves
   array list, have a for loop inside of a for loop that adds all the possible moves on the 10 x 10 board.
   @param String filename
   */
   public UserBoard2(String filename)
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
      
      // Initialize computer move type.
      computerMoveType = 1;
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
      
      // create a move variable for the square to the right of the last hit and for the randomMove.
      Move rightSquare;
      Move leftSquare;
      Move upSquare;
      Move downSquare;
      
      // Initialize a typeHIT variable.
      CellStatus typeHIT;
      
      // If it is the first computer move type....
      if (computerMoveType == 1)
      {
         // Pick a random move from the moves list and strike.
         moveIndex = rand.nextInt(movesLeft);
         randomMove = moves.get(moveIndex);
         //System.out.println(moves.contains(randomMove));
         typeHIT = super.applyMoveToLayout(randomMove);
         moves.remove(moveIndex);
         movesLeft--;
         //System.out.println(moves.contains(randomMove));

         
         // Add the randomMove to the returnString ArrayList.
         returnStrings[0] = randomMove.toString();
         returnStrings[1] = null;
         
         // If the random move was a hit....
         if (typeHIT.equals(CellStatus.AIRCRAFT_CARRIER) || typeHIT.equals(CellStatus.BATTLESHIP) || typeHIT.equals(CellStatus.CRUISER) || 
               typeHIT.equals(CellStatus.DESTROYER) || typeHIT.equals(CellStatus.SUB))
         {
            // Set the computer move to 2 and the last hit to the random move that was striked.
            computerMoveType = 2;
            lastHit = randomMove; 
           //  System.out.println("There was a hit and the computerMoveType has been changed to 2.");
         }
         else
         {
            computerMoveType = 1;
         }
      }
      
      // Else - If it is the second computer move type....
      else if (computerMoveType == 2)
      {
         // System.out.println("Excecuting computerMoveType 2.");
         
         // Locate the square to the right of the last hit.
         rightSquare = new Move(lastHit);
         int rightCol = rightSquare.col() + 1;
         rightSquare.setCol(rightCol);
         returnStrings[0] = rightSquare.toString();
         returnStrings[1] = null;
         
         // Initialize contains boolean variable 
         boolean contains = false;
         
         // Use the toString of each Move in the move list. Iterate through the moves list and if the n'th Move's toString equals the toString of the rightSquare,
         // then that specific move can be  striked....
         for (int i = 0; i < moves.size(); i++)
         {
            if (moves.get(i).toString().equals(rightSquare.toString()))
            
            {
               contains = true;
               moves.remove(i);
              //  System.out.println("Contains = true");
            }
         }
         
         // If the square to the right of the current last hit is in the moves list....
         if (contains = true)
         {
            // Strike the square to the right of the last hit.
            typeHIT = super.applyMoveToLayout(rightSquare);
            movesLeft--;
            
            // System.out.println("Applying Move to Layout, so Contains must equal true.");
            
            // If the strike to the right was a hit....
            if (typeHIT.equals(CellStatus.AIRCRAFT_CARRIER) || typeHIT.equals(CellStatus.BATTLESHIP) || typeHIT.equals(CellStatus.CRUISER) || 
               typeHIT.equals(CellStatus.DESTROYER) || typeHIT.equals(CellStatus.SUB))
            {
               // Set the computer move to second,  Set the last hit to the strike.
               computerMoveType = 2;
               lastHit = rightSquare;
               
               // If the ship that was hit is sunk... go to the the square that was just hit and check it's CellStatus
               if (super.getIfSunk() == true)
               {
                  // Set the computer move to first.
                  computerMoveType = 1;
               }
            }
            else
            {
               // If the original random move == last hit...
               if (lastHit == randomMove)
               {
                  // Set the computer move to fourth. 
                  computerMoveType = 4;
                  // System.out.println("The computerMoveType is " + computerMoveType);
               }
               
               else
               {
                  // Set the last hit back to the original random move and set the computer move to third.
                  lastHit = randomMove;
                  computerMoveType = 3;
                  
                  // System.out.println("The computerMoveType is " + computerMoveType);
               }   
            }
         }
         else
         {
            // System.out.println("Contains = false");
            
            // If the original random move == last hit...
           if (lastHit == randomMove)
           {
              // Set the computer move to fourth. 
              computerMoveType = 4;
              // System.out.println("The computerMoveType is " + computerMoveType);
           }
               
           else
           {
              // Set the last hit back to the original random move and set the computer move to third.
              lastHit = randomMove;
              computerMoveType = 3;
                
              // System.out.println("The computerMoveType is " + computerMoveType);        
           } 
         }
      }
      
       // Else - If it is the third computer move type....
      else if (computerMoveType == 3)
      {
         // System.out.println("Excecuting computerMoveType 3.");
         
         // Locate the square to the left of the last hit.
         leftSquare = new Move(lastHit);
         int leftCol = leftSquare.col() - 1;
         leftSquare.setCol(leftCol);
         returnStrings[0] = leftSquare.toString();
         returnStrings[1] = null;
         
         // Initialize contains boolean variable 
         boolean contains = false;
         
         // Use the toString of each Move in the move list. Iterate through the moves list and if the n'th Move's toString equals the toString of the rightSquare,
         // then that specific move can be  striked....
         for (int i = 0; i < moves.size(); i++)
         {
            if (moves.get(i).toString().equals(leftSquare.toString()))
            
            {
               contains = true;
               moves.remove(i);
               // System.out.println("Contains = true");
            }
         }
         
         // If the square to the right of the current last hit is in the moves list....
         if (contains = true)
         {
            // Strike the square to the left of the last hit.
            typeHIT = super.applyMoveToLayout(leftSquare);
            movesLeft--;
            
            // System.out.println("Applying Move to Layout, so Contains must equal true.");
            
            // If the strike to the left was a hit....
            if (typeHIT.equals(CellStatus.AIRCRAFT_CARRIER) || typeHIT.equals(CellStatus.BATTLESHIP) || typeHIT.equals(CellStatus.CRUISER) || 
               typeHIT.equals(CellStatus.DESTROYER) || typeHIT.equals(CellStatus.SUB))
            {
               // Set the computer move to 3,  Set the last hit to the strike.
               computerMoveType = 3;
               lastHit = leftSquare;
               
               // If the ship that was hit is sunk... go to the the square that was just hit and check it's CellStatus
               if (super.getIfSunk() == true)
               {
                  // Set the computer move to first.
                  computerMoveType = 1;
               }
            }
            else
            {
               // If the original random move == last hit...
               if (lastHit == randomMove)
               {
                  // Set the computer move to fifth. 
                  computerMoveType = 5;
                  // System.out.println("The computerMoveType is " + computerMoveType);
               }
               
               else
               {
                  // Set the last hit back to the original random move and set the computer move to second.
                  lastHit = randomMove;
                  computerMoveType = 2;
                  
                  // System.out.println("The computerMoveType is " + computerMoveType);
               }   
            }
         }
         else
         {
            // System.out.println("Contains = false");
            
            // If the original random move == last hit...
           if (lastHit == randomMove)
           {
              // Set the computer move to fifth. 
              computerMoveType = 5;
              // System.out.println("The computerMoveType is " + computerMoveType);
           }
               
           else
           {
              // Set the last hit back to the original random move and set the computer move to second.
              lastHit = randomMove;
              computerMoveType = 2;
                
              // System.out.println("The computerMoveType is " + computerMoveType);        
           } 
         }
      }
      
      
      // Else - If it is the fourth computer move type....
      else if (computerMoveType == 4)
      {
         // System.out.println("Excecuting computerMoveType 4.");
         
         // Locate the square above the last hit.
         upSquare = new Move(lastHit);
         int upRow = upSquare.row() + 1;
         upSquare.setRow(upRow);
         returnStrings[0] = upSquare.toString();
         returnStrings[1] = null;
         
         // Initialize contains boolean variable 
         boolean contains = false;
         
         // Use the toString of each Move in the move list. Iterate through the moves list and if the n'th Move's toString equals the toString of the upSquare,
         // then that specific move can be  striked....
         for (int i = 0; i < moves.size(); i++)
         {
            if (moves.get(i).toString().equals(upSquare.toString()))
            
            {
               contains = true;
               moves.remove(i);
              //  System.out.println("Contains = true");
            }
         }
         
         // If the square above the current last hit is in the moves list....
         if (contains = true)
         {
            // Strike the square above the last hit.
            typeHIT = super.applyMoveToLayout(upSquare);
            movesLeft--;
            
            // System.out.println("Applying Move to Layout, so Contains must equal true.");
            
            // If the strike above was a hit....
            if (typeHIT.equals(CellStatus.AIRCRAFT_CARRIER) || typeHIT.equals(CellStatus.BATTLESHIP) || typeHIT.equals(CellStatus.CRUISER) || 
               typeHIT.equals(CellStatus.DESTROYER) || typeHIT.equals(CellStatus.SUB))
            {
               // Set the computer move to 4,  Set the last hit to the strike.
               computerMoveType = 4;
               lastHit = upSquare;
               
               // If the ship that was hit is sunk... go to the the square that was just hit and check it's CellStatus
               if (super.getIfSunk() == true)
               {
                  // Set the computer move to first.
                  computerMoveType = 1;
               }
            }
            else
            {
               // If the original random move == last hit...
               if (lastHit == randomMove)
               {
                  // Set the computer move to third. 
                  computerMoveType = 3;
                  // System.out.println("The computerMoveType is " + computerMoveType);
               }
               
               else
               {
                  // Set the last hit back to the original random move and set the computer move to fifth.
                  lastHit = randomMove;
                  computerMoveType = 2;
                  
                  // System.out.println("The computerMoveType is " + computerMoveType);
               }   
            }
         }
         else
         {
            // System.out.println("Contains = false");
            
            // If the original random move == last hit...
           if (lastHit == randomMove)
           {
              // Set the computer move to third. 
              computerMoveType = 3;
              // System.out.println("The computerMoveType is " + computerMoveType);
           }
               
           else
           {
              // Set the last hit back to the original random move and set the computer move to fifth.
              lastHit = randomMove;
              computerMoveType = 5;
                
              // System.out.println("The computerMoveType is " + computerMoveType);        
           } 
         }
      }
      
      
      // Else - If it is the fifth computer move type....
      else if (computerMoveType == 5)
      {
         // System.out.println("Excecuting computerMoveType 5.");
         
         // Locate the square below the last hit.
         downSquare = new Move(lastHit);
         int downRow = downSquare.row() - 1;
         downSquare.setRow(downRow);
         returnStrings[0] = downSquare.toString();
         returnStrings[1] = null;
         
         // Initialize contains boolean variable 
         boolean contains = false;
         
         // Use the toString of each Move in the move list. Iterate through the moves list and if the n'th Move's toString equals the toString of the upSquare,
         // then that specific move can be  striked....
         for (int i = 0; i < moves.size(); i++)
         {
            if (moves.get(i).toString().equals(downSquare.toString()))
            
            {
               contains = true;
               moves.remove(i);
               // System.out.println("Contains = true");
            }
         }
         
         // If the square below the current last hit is in the moves list....
         if (contains = true)
         {
            // Strike the square below the last hit.
            typeHIT = super.applyMoveToLayout(downSquare);
            movesLeft--;
            
            // System.out.println("Applying Move to Layout, so Contains must equal true.");
            
            // If the strike below was a hit....
            if (typeHIT.equals(CellStatus.AIRCRAFT_CARRIER) || typeHIT.equals(CellStatus.BATTLESHIP) || typeHIT.equals(CellStatus.CRUISER) || 
               typeHIT.equals(CellStatus.DESTROYER) || typeHIT.equals(CellStatus.SUB))
            {
               // Set the computer move to 5,  Set the last hit to the strike.
               computerMoveType = 5;
               lastHit = downSquare;
               
               // If the ship that was hit is sunk... go to the the square that was just hit and check it's CellStatus
               if (super.getIfSunk() == true)
               {
                  // Set the computer move to first.
                  computerMoveType = 1;
               }
            }
            else
            {
               // Set Computer move to first. 
               computerMoveType = 1; 
            }
         }
         else
         {
            // System.out.println("Contains = false");
            
            // Set Computer move to first. 
            computerMoveType = 1; 
         }
      }



      
      return returnStrings;
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