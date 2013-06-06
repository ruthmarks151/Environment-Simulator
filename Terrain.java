import java.util.Random;
class Terrain
{
  int light; //sd 20, mean 50, min 0, max 100
  int water; //same as light
  int temp; //
  
  public Terrain ()
  {
    Random r = new Random();
    do
    {
    light = (int)Math.round(r.nextGaussian()*20)+50;
    }
    while (light > 100 && light < 0);
    do
    {
    water = (int)Math.round(r.nextGaussian()*20)+50;
    }
    while (water > 100 && water < 0);
    
  }
}