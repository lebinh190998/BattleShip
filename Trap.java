import java.util.*;

public class Trap {
    public int trapNo;
    public int x;
    public int y;

    public Trap(int trapNo, int x, int y) {
        this.trapNo = trapNo;
        this.x = x;
        this.y = y;
    }

    public Trap() {
        this.trapNo = 0;
        this.x = 0;
        this.y = 0;
    }


    // Getter
    public int getTrapNo() {
        return this.trapNo;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }


    // Setter
    public void setTrapNo(int newTrapNo) {
        this.trapNo = newTrapNo;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
}