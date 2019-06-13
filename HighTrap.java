public class HighTrap extends Trap {
    private int damage;

    public HighTrap(int x, int y) {
        super(x, y);
        this.damage = 2;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }
}