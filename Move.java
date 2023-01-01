// Max Noddings
// CS110
// Final Project!

/**
Move Class. It will have two constructors, two getters, and one toString. It will have two instance variables - row and columb - both int's.
One constructor will create a move object from two integers which represent the indicies of a two dimensional array
, the other constructor will create a move object from a String consisting of a letter and a number. One getter will 
return which row the move object was created in. The other getter will return which columb the move object was created in.
The toString will return a 2 to 3 character string consisting of a letter in the range A-J which will be the represent the 
row followed by a number in the range of 1 - 10 which will represent the columb. 
*/

public class Move
{
   private int row;
   private int col;
   
   /**
   This constructor will create a move object from two integers which represent the indicies of a two dimensional array.
   @param int row
   @param int col
   */
   public Move(int row, int col)
   {
      this.row = row - 1;
      this.col = col - 1;
   }
   /**
   This constructor will create a move object from a String consisting of a letter and a number. Use a series of if statements
   to transfer a letter into a discernable row number. Also use parse int!
   @param String coordinate
   */
   public Move(String coordinate)
   {
      // Convert the character at index 0 to upper case just in case the user entered in a lower case letter. 
      char alphaRow = Character.toUpperCase(coordinate.charAt(0));
      
      // If the character at index 0 is A it is in row 1 etc....
      if (alphaRow == 'A')
      {
         row = 0;
      }
      else if (alphaRow == 'B')
      {
         row = 1;
      }
      else if (alphaRow == 'C')
      {
         row = 2;
      }
      else if (alphaRow == 'D')
      {
         row = 3;
      }
      else if (alphaRow == 'E')
      {
         row = 4;
      }
      else if (alphaRow == 'F')
      {
         row = 5;
      }
      else if (alphaRow == 'G')
      {
         row = 6;
      }
      else if (alphaRow == 'H')
      {
         row = 7;
      }
      else if (alphaRow == 'I')
      {
         row = 8;
      }
      else if (alphaRow == 'J')
      {
         row = 9;
      }
      
      // If the string length for the coordinate is 3 the column will be the 9th index!
      if (coordinate.length() == 3)
         col = 9;
      else
      {
         //Assign the columb to the number specified by the second string character in the coordinate. Use Character.getNumericValue.
         col = Character.getNumericValue(coordinate.charAt(1)) - 1;
      }
   }
   /**
   This is a copy constuctor of a move object.
   @param Move move
   */
   public Move(Move move)
   {
      this.col = move.col;
      this.row = move.row;
   }
   
   
   
   /**
   Make a getter that will return which row the move object was created in.
   @return int row
   */
   public int row()
   {
      return row;
   }
   /**
   Make a getter that will return which columb the move object was created in.
   @return int row
   */
   public int col()
   {
      return col;
   }
   
   /**
   Make a setter for the row!
   @param int row
   */
   public void setRow(int row)
   {
      this.row = row;
   }
   
   /**
   Make a setter for column!
   @param int col
   */
   public void setCol(int col)
   {
      this.col = col;
   }
   
   
   
   /**
   toString will return a 2 to 3 character string consisting of a letter in the range A-J which will be the represent the 
   row followed by a number in the range of 1 - 10 which will represent the columb.
   @return String
   */
   public String toString()
   {
      //Declare a few variables that will be used to create the string.
      String rowString = null;
      String colString = null;
      
      // Use a series of if statements to determine the letter of the row.
      if (row == 0)
         rowString = "A";
      else if (row == 1)
         rowString = "B";
      else if (row == 2)
         rowString = "C";
      else if (row == 3)
         rowString = "D";
      else if (row == 4)
         rowString = "E";
      else if (row == 5)
         rowString = "F";
      else if (row == 6)
         rowString = "G";
      else if (row == 7)
         rowString = "H";
      else if (row == 8)
         rowString = "I";
      else if (row == 9)
         rowString = "J";
      
      // Assign colString
      colString = (col + 1) + "";
      
      // Return concatination.
      return rowString + colString;     
   }
   
   
}