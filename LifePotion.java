public class LifePotion extends Potion {
    private int life;

    public LifePotion(int x, int y) {
        super(x, y);
        this.life = 1;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int newLife) {
        this.life = newLife;
    }
}