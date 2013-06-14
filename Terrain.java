/**********************************************************************************************************************/
import java.util.Random;
class Terrain
{
  protected int light; //sd 25, mean 50, min 0, max 100
  protected int water; //same as light
  protected int temp; //same
  protected Ecosystem parent;
  protected String type;
  public Terrain (Ecosystem creator)
  {
    parent=creator;
    
    Random r = new Random();
    do
    {
    light = (int)Math.round(r.nextDouble()*25)+50;
    }
    while (light > 100 && light < 0);
    do
    {
    water = (int)Math.round(r.nextDouble()*100);
    }
    while (water > 100 && water < 0);
    do
    {
      temp = (int)Math.round(r.nextDouble()*100);
    }
    while (temp > 100 && temp < 0);
    
    type = type();
  }
  
  //This needs to get tuned Right now it spews random junk
  public String type(){
    int row = parent.getRow();
    int col = parent.getCol();
    String up, left;
    if (row == 0 && col == 0){
    if (water>80)
      return "Water";
    if(water>35){
      if(light>80)
        return "Short Grass";
      if (light>50)
        return "Tall Grass";
      if (light>20)
        return "Young Forest";
      return "Mature Forest";
    }
    if (water<30 && temp > 60)
      return "Sand";
    return "Dirt";
    }
    
    else if (row == 0)
    {
      left = parent.getParent().getEcosystem(row,col-1).getTerrain().getType();
      if (left.equals("Water"))
        if (water > 75)
            return "Water;
    }
    else
    {
    up = parent.getParent().getEcosystem(row-1,col).getTerrain().getType();
    }
    if (water>75)
      return "Water";
    if(water>35){
      if(light>80)
        return "Short Grass";
      if (light>50)
        return "Tall Grass";
      if (light>20)
        return "Young Forest";
      return "Mature Forest";
    }
    if (water<30 && temp > 60)
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