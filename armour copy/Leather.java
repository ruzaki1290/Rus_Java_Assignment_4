package armour;

public class Leather extends Armour {
  private int armourAmount = 50;
  private int dexterityCost = 50;

  public Leather(String armourType) {
    super(armourType);
    super.setArmourAmount(armourAmount);
    super.setDexterityCost(dexterityCost);
  } // contructor

  public int getFinalDamage(int damage) {
    return damage - (this.armourAmount / 10);
  }
  
} // class
