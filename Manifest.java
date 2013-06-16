class Manifest {
  String species;
  int population;
  
  // basic constructor
  public Manifest (String type, int num) {
    species = type;
    population = num;
  }
  
  // getters
  public String getSpecies () {return species;}  
  public int getPop () {return population;}
}