public class ShipPotion extends Potion {
    private int shipRevealNum;

    public ShipPotion(int x, int y) {
        super(x, y);
        this.shipRevealNum = 1;
    }

    public int getShipRevealnum() {
        return this.shipRevealNum;
    }

    public void setShipRevealNum(int newShipRevealNum) {
        this.shipRevealNum = newShipRevealNum;
    }
}