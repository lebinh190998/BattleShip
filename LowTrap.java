public class LowTrap extends Trap {
    private int damage;

    public LowTrap(int x, int y) {
        super(x, y);
        this.damage = 1;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }
}