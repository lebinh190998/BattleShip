import java.util.*;

public class Ship {
    public int shipNo;
    public int x;
    public int y;
    public int length;

    public Ship(int shipNo, int x, int y, int length) {
        this.shipNo = shipNo;
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public Ship() {
        this.shipNo = 0;
        this.x = 0;
        this.y = 0;
        this.length = 0;
    }


    // Getter
    public int getShipNo() {
        return this.shipNo;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getLength() {
        return this.length;
    }


    // Setter
    public void setShipNo(int newShipNo) {
        this.shipNo = newShipNo;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public void setLength(int newLength) {
        this.length= newLength;
    }
}