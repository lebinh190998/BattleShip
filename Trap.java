import java.util.*;

public class Trap {
    private int x;
    private int y;
    private boolean isRemove;
    private boolean isReveal;

    public Trap(int x, int y) {
        this.x = x;
        this.y = y;
        this.isRemove = false;
        this.isReveal = false;
    }


    // Getter
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean getIsRemove() {
        return this.isRemove;
    }
    public boolean getIsReveal() {
        return this.isReveal;
    }


    // Setter
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public void setIsRemove(boolean result) {
        this.isRemove= result;
    }
    public void setIsReveal(boolean result) {
        this.isReveal= result;
    }

    public static Trap randomGenerateTrap(Ocean ocean){
        Random randomGenerator = new Random();
        int numRows = ocean.getRows();
        int numCols = ocean.getCols();
        String[][] checkingGrid = ocean.getCheckingGrid();
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