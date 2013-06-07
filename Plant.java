class Plant extends Organism
{
  private static int photosynthesisSuccess;
  
  public Plant (Ecosystem eco,String createAs){
    super(eco,createAs);
  }
  
  public void photosynthesize ()
  {
    if (Math.random() * 100 < photosynthesisSuccess)
      addEnergy(1);
  }
}