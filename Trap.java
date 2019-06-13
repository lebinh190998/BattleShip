import java.util.*;

public class Trap {
    private int x;
    private int y;

    public Trap(int x, int y) {
        this.x = x;
        this.y = y;
    }


    // Getter
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }


    // Setter
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }

    public static Trap randomGenerateTrap(String[][] checkingGrid, int numRows, int numCols){
        Random randomGenerator = new Random();
        while (true) {
            int x = randomGenerator.nextInt(numRows);
            int y = randomGenerator.nextInt(numCols);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && checkingGrid[x][y] == "#")
            {
                int type = randomGenerator.nextInt(2);
                if(type == 0){
                    return new HighTrap(x, y);
                }else{
                    return new LowTrap(x, y);
                }
            }
        }
    }
}