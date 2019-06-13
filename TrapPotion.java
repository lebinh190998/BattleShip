public class TrapPotion extends Potion {
    private int trapRevealNum;

    public TrapPotion(int x, int y) {
        super(x, y);
        this.trapRevealNum = 1;
    }

    public int getTrapRevealnum() {
        return this.trapRevealNum;
    }

    public void setTrapRevealNum(int newTrapRevealNum) {
        this.trapRevealNum = newTrapRevealNum;
    }
}