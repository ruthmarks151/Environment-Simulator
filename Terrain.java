/**********************************************************************************************************************/
import java.util.Random;
class Terrain
{
    protected int light; //integer from 0-100
    protected int water; //int from 0-100
    protected int temp; //int from 0-100
    protected Ecosystem parent; //The Ecosystem that initiated the terrain
    protected String type; //This is the terrain tile needed
    //Getters
    public int getLight(){return light;} //access light
    public int getWater(){return water;} //access water
    public int getTemp(){return temp;} //access temperature
    public String getType (){return type;} //access type (for terrain tile)

        
    public Terrain (Ecosystem creator) //default constructor, need creator/parent as input
    {
 parent = creator; 

 Random r = new Random (); //initate random class, creates int from 0-100
 do
 {
     light = (int) Math.round (r.nextDouble () * 101);
 }
 while (light > 100 && light < 0); //Nex
 do
 {
     water = (int) Math.round (r.nextDouble () * 101);
 }
 while (water > 100 && water < 0);
 do
 {
     temp = (int) Math.round (r.nextDouble () * 101);
 }
 while (temp > 100 && temp < 0);

 type = type (); //Save type as a String
    }


    //Classifies the type of terrain which is used for gui tiles
    public String type ()
    {
 int row = parent.getRow (); //this calls the ecosystem class to get Row and col in the grid
 int col = parent.getCol ();
 String up = "Dirt", left = "Dirt"; //up is the tile above this tile, left is tile to the left
 if (row == 0 && col == 0) //This is just default as no other terrain generated yet
 {
     if (water > 80)
  return "Water";
     if (water > 35)
     {
  if (light > 80)
      return "Short Grass";
  if (light > 50)
      return "Tall Grass";
  if (light > 20)
      return "Young Forest";
  return "Mature Forest";
     }
     if (water < 35 && temp > 55)
  return "Sand";
     return "Dirt";
 }

 else if (row == 0) //if first row, the left tile is generated (and not origin)
 {
     //get terrain type of left tile
     left = parent.getParent ().getEcosystem (row, col - 1).getTerrain ().getType ();
     //More lax to make it easier to look like tile on left.
     if (left.equals ("Water"))
  if (water > 75)
      return "Water";
     if (water > 30)
     {
  if (left.equals ("Short Grass"))
      if (light > 75)
   return "Short Grass";
      else if (left.equals ("Mature Forest"))
      {
   if (light < 25)
       return "Mature Forest";
      }
      else if (left.equals ("Young Forest"))
   if (light > 17 && light < 53)
       return "Young Forest";
   else if (left.equals ("Tall Grass"))
   {
       if (light > 47 && light < 83)
    return "Tall Grass";
   }
     }
     if (left.equals ("Sand"))
  if (water < 40 && temp > 50)
      return "Sand";

     //Else default initalization
     if (water > 80)
  return "Water";
     if (water > 35)
     {
  if (light > 80)
      return "Short Grass";
  if (light > 50)
      return "Tall Grass";
  if (light > 20)
      return "Young Forest";
  return "Mature Forest";
     }
     if (water < 35 && temp > 55)
  return "Sand";
     return "Dirt";
 }


 else
 { //This means it is not row 0;
   if (col == 0) //first column of the grid, so no left tile
   {
     //get terrain type of tile on row up
     up = parent.getParent ().getEcosystem (row - 1, col).getTerrain ().getType ();
     //More lax to make it easier to look like tile on top.
     if (up.equals ("Water"))
  if (water > 75)
      return "Water";
     if (water > 30)
     {
  if (up.equals ("Short Grass"))
      if (light > 75)
   return "Short Grass";
      else if (up.equals ("Mature Forest"))
      {
   if (light < 25)
       return "Mature Forest";
      }
      else if (up.equals ("Young Forest"))
   if (light > 17 && light < 53)
       return "Young Forest";
   else if (up.equals ("Tall Grass"))
   {
       if (light > 47 && light < 83)
    return "Tall Grass";
   }
     }
     if (up.equals ("Sand"))
  if (water < 40 && temp > 50)
      return "Sand";

     //Else default initalization
     if (water > 80)
  return "Water";
     if (water > 35)
     {
  if (light > 80)
      return "Short Grass";
  if (light > 50)
      return "Tall Grass";
  if (light > 20)
      return "Young Forest";
  return "Mature Forest";
     }
     if (water < 35 && temp > 55)
  return "Sand";
     return "Dirt";
   }
   
   //Otherwise it will be normal case, row not equal 0, column not equal 0
   left = parent.getParent ().getEcosystem (row, col - 1).getTerrain ().getType ();
     up = parent.getParent ().getEcosystem (row - 1, col).getTerrain ().getType ();
 }


 //Now we can use up and left tile to be more similar
 if (left.equals (up)) //if up and left are the same
 { //higher chance that tile will be the same
     if (left.equals ("Water"))
  if (water > 70)
      return "Water";
     if (water > 25)
     {
  if (left.equals ("Short Grass"))
      if (light > 70)
   return "Short Grass";
      else if (left.equals ("Mature Forest"))
      {
   if (light < 30)
       return "Mature Forest";
      }
      else if (left.equals ("Young Forest"))
   if (light > 14 && light < 56)
       return "Young Forest";
   else if (left.equals ("Tall Grass"))
   {
       if (light > 44 && light < 86)
    return "Tall Grass";
   }
     }
     if (left.equals ("Sand"))
  if (water < 40 && temp > 50)
      return "Sand";
     if (left.equals ("Dirt"))
       if (Math.random() < 0.45) //Dirt is just a default, so there may be a better tile
       return "Dirt"; //Otherwise there would be too much dirt
 }

//Otherwise we give a higher chance for it to be similar to one of the tiles
 //Water is priority, Vegetation second, Sand last
 //doesn't matter if just one is dirt as dirt is default tile
 if (left.equals ("Water") || up.equals ("Water"))
     if (water > 65)
  return "Water";
 if (water > 30)
 {
     if (left.equals ("Short Grass") || up.equals ("Short Grass"))
  if (light > 75)
      return "Short Grass";
  else if (left.equals ("Mature Forest") || up.equals ("Mature Forest"))
  {
      if (light < 25)
   return "Mature Forest";
  }
  else if (left.equals ("Young Forest") || up.equals ("Young Forest"))
      if (light > 17 && light < 53)
   return "Young Forest";
      else if (left.equals ("Tall Grass") || up.equals ("Tall Grass"))
      {
   if (light > 47 && light < 83)
       return "Tall Grass";
      }
 }
 if (left.equals ("Sand") || up.equals ("Sand"))
     if (water < 40 && temp > 50)
  return "Sand";

 //Default
 if (water > 80)
     return "Water";
 if (water > 35)
 {
     if (light > 80)
  return "Short Grass";
     if (light > 50)
  return "Tall Grass";
     if (light > 20)
  return "Young Forest";
     return "Mature Forest";
 }


 if (water < 35 && temp > 55)
     return "Sand";
 return "Dirt";
    }


    //Initialize the Environment with given constructors
    public Terrain (int lite, int h20, int temperature)
    {
 light = lite;
 if (light < 0)
 {
     light = 0;
 }


 else if (light > 100)
 {
     light = 100;
 }


 water = h20;
 if (water < 0)
 {
     water = 0;
 }


 else if (water > 100)
 {
     water = 100;
 }


 temp = temperature;
 if (temp < 0)
 {
     temp = 0;
 }


 else if (temp > 100)
 {
     temp = 100;
 }


 type = type ();
    }

    //if you wish to change light variable
    public void changeLight (int num)
    {
 light = num;
 if (light < 0)
 {
     light = 0;
 }

 else if (light > 100)
 {
     light = 100;
 }
    }

//if you wish to change water variable
    public void changeWater (int num)
    {
 water = num;
 if (water < 0)
 {
     water = 0;
 }

 else if (water > 100)
 {
     water = 100;
 }
    }

    //if you wish to change temperature variable
    public void changeTemp (int num)
    {
 temp = num;
 if (temp < 0)
 {
     temp = 0;
 }
 
 else if (temp > 100)
 {
     temp = 100;
 }
    }
 
  
  public String manifest(){ //for output file
    String out="";
    out+=("Light:"+light+"\n");
    out+=("Water:"+water+"\n");
    out+=("Temp:"+temp+"\n");
    return out;
  }
}



