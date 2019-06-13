import java.util.*;

public class Potion {
    private int x;
    private int y;

    public Potion(int x, int y) {
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

    public static Potion randomGeneratePotion(Ocean ocean){
        Random randomGenerator = new Random();
        int numRows = ocean.getRows();
        int numCols = ocean.getCols();
        String[][] checkingGrid = ocean.getCheckingGrid();
        while (true) {
            int x = randomGenerator.nextInt(numRows);
            int y = randomGenerator.nextInt(numCols);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && checkingGrid[x][y] == "#")
            {
                int type = randomGenerator.nextInt(3);
                if(type == 0){
                    return new LifePotion(x, y);
                }else if(type == 1){
                    return new ShipPotion(x, y);
                }else{
                    return new TrapPotion(x, y);
                }
            }
        }
    }
}