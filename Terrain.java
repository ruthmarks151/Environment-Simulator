import java.util.Random;
class Terrain
{
  int light;
  int water;
  int temp;
  
  public Terrain ()
  {
    Random r = new Random();
    do
    {
    light = (int)Math.round(r.nextGaussian()*20)+50;
    }
    while (light > 100 && light < 0)
      
  }
}