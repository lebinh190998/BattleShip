import java.util.*;

public class Potion {
    public int potionNo;
    public int x;
    public int y;

    public Potion(int potionNo, int x, int y) {
        this.potionNo = potionNo;
        this.x = x;
        this.y = y;
    }

    public Potion() {
        this.potionNo = 0;
        this.x = 0;
        this.y = 0;
    }


    // Getter
    public int getPotionNo() {
        return this.potionNo;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }


    // Setter
    public void setPotionNo(int newPotionNo) {
        this.potionNo = newPotionNo;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
}