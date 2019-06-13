import java.util.*;

public class Ship {
    private int x;
    private ArrayList<Integer> y = new ArrayList<Integer>();
    private int length;
    private boolean isSunk;

    public Ship(int x, ArrayList<Integer> y , int length) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.isSunk = false;
    }


    // Getter
    public int getX() {
        return this.x;
    }
    public ArrayList<Integer> getY() {
        return this.y;
    }
    public int getLength() {
        return this.length;
    }
    public boolean getIsSunk() {
        return this.isSunk;
    }


    // Setter
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(ArrayList<Integer> newY) {
        this.y = newY;
    }
    public void setLength(int newLength) {
        this.length= newLength;
    }
    public void setIsSunk(boolean result) {
        this.isSunk= result;
    }

    public static Ship randomGenerateShip(String[][] checkingGrid, int numRows, int numCols){
        Random randomGenerator = new Random();
        while (true) {
            int x = randomGenerator.nextInt(numRows);
            int y = randomGenerator.nextInt(numCols);
            int length = randomGenerator.nextInt(3) + 3;
            ArrayList<Integer> ys = new ArrayList<Integer>();
            for(int i=0; i < length; i++){
                ys.add(y+i);
            }
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length))
            {
                boolean isTaken = false;
                for(int i : ys){
                    if(checkingGrid[x][i] != "#"){
                        isTaken = true;
                    }
                }
                if(isTaken == false){
                    return new Ship(x, ys, length);
                }
            }
        }
    }
}