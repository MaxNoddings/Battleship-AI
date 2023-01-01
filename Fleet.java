// Max Noddings
// CS110
// Final Project!

/**
Fleet class will have instance variables for each type of ship. It will have one constructor that intitializes all the ship
feilds. Also, an updateFleet method that informs the appropriate ship that it has been hit and returns true if this sank the ship,
false if it did not. The gameOver method will return true if alll ships are sunk, returns false if not!
*/
public class Fleet
{
   private Ship battleShip;
   private Ship aircraftCarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   
   
   /**
   Constructor that initializes all the ship fields!
   */
   public Fleet()
   {
      battleShip = new Battleship();
      aircraftCarrier = new AircraftCarrier();
      cruiser = new Cruiser();
      sub = new Sub();
      destroyer = new Destroyer();
   }
   
   
   
   /**
   updateFleet method that informs the appropriate ship that it has been hit and returns true if this sank the ship, 
   false if it did not.
   @param ShipType i
   @return boolean
   */
   public boolean updateFleet(ShipType i)
   {
      // Write a series of if statments that correspond to each different shipType.
      if (i == ShipType.ST_BATTLESHIP)
      {
         return battleShip.hit();
      }
      else if (i == ShipType.ST_AIRCRAFT_CARRIER)
      {
         return aircraftCarrier.hit();
      }
      else if (i == ShipType.ST_CRUISER)
         return cruiser.hit();
      else if (i == ShipType.ST_SUB)
         return sub.hit();
      else if (i == ShipType.ST_DESTROYER)
         return destroyer.hit();
         
      // If return true has not already happened, return false:
      return false;
   }
   
   
   
   /**
   The gameOver method will return true if all ships are sunk, returns false if not!
   @return boolean
   */
   public boolean gameOver()
   {
      // If all ships are sunk, return true, else return false. 
      if (battleShip.getSunk() == true && aircraftCarrier.getSunk() == true && cruiser.getSunk() == true && 
          sub.getSunk() == true && destroyer.getSunk() == true)
         return true;
      else
         return false;
   }
   
   
   
   /**
   Getter for battleShip!
   @return Ship battleShip
   */
   public Ship getBattleship()
   {
      return battleShip;
   }
   /**
   Getter for aircraftCarrier!
   @return Ship aircraftCarrier
   */
   public Ship getAircraftCarrier()
   {
      return aircraftCarrier;
   }
   /**
   Getter for cruiser!
   @return Ship cruiser
   */
   public Ship getCruiser()
   {
      return cruiser;
   }
   /**
   Getter for sub!
   @return Ship sub
   */
   public Ship getSub()
   {
      return sub;
   }
   /**
   Getter for destroyer!
   @return Ship destroyer
   */
   public Ship getDestroyer()
   {
      return destroyer;
   }
}