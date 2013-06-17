/**********************************************************************************************************************/
import java.util.Random;
class Terrain
{
    protected int light;
    protected int water;
    protected int temp;
    protected Ecosystem parent;
    protected String type;
    public Terrain (Ecosystem creator)
    {
 parent = creator;

 Random r = new Random ();
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


    //This needs to get tuned Right now it spews random junk
    public String type ()
    {
 int row = parent.getRow ();
 int col = parent.getCol ();
 String up = "Dirt", left = "Dirt";
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
     if (water < 30 && temp > 60)
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
  if (water < 35 && temp > 55)
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
     if (water < 30 && temp > 60)
  return "Sand";
     return "Dirt";
 }


 else
 { //Just import up tile too
     up = parent.getParent ().getEcosystem (row - 1, col).getTerrain ().getType ();
 }


 //Now we can use up and left tile to be more similar
 if (left.equals (up)) //if up and left are the same
 {
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
       if (Math.random() < 0.5)
       return "Dirt";
 }


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
     if (water < 35 && temp > 55)
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


 if (water < 30 && temp > 60)
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


    public String getType ()
    {
 return type;
    }


    public int getLight ()
    {
 return light;
    }


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


    public int getWater ()
    {
 return water;
    }


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


    public int getTemp ()
    {
 return temp;
    }


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
}


