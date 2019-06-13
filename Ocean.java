import java.util.*;

public class Ocean {
    private int numRows;
    private int numCols;
    private String[][] displayGrid;
    private String[][] checkingGrid;    

    public Ocean(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.displayGrid = new String[numRows][numCols];
        this.checkingGrid = new String[numRows][numCols];
    }

    // Getter
    public int getRows() {
        return this.numRows;
    }
    public int getCols() {
        return this.numCols;
    }
    public String[][] getDisplayGrid() {
        return this.displayGrid;
    }
    public String[][] getCheckingGrid() {
        return this.checkingGrid;
    }

    // Setter
    public void setRows(int newRows) {
        this.numRows = newRows;
    }
    public void setCols(int newCols) {
        this.numCols = newCols;
    }
    public void setDisplayGrid(String[][] newGrid) {
        this.displayGrid = newGrid;
    }
    public void setheckingGrid(String[][] newGrid) {
        this.checkingGrid = newGrid;
    }

    public static Ocean createOceanMap(int rows, int cols){
        //Header section of Ocean Map
        Ocean ocean = new Ocean(rows, cols);
        int numRows = ocean.getRows();
        int numCols = ocean.getCols();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getCheckingGrid();

        //Create display Map
        for(int i = 0; i < displayGrid.length; i++) {
            for (int j = 0; j < displayGrid[i].length; j++) {
                displayGrid[i][j] = "#";
            }
        }
        //Create checking Map
        for(int i = 0; i < checkingGrid.length; i++) {
            for (int j = 0; j < checkingGrid[i].length; j++) {
                checkingGrid[i][j] = "#";
            }
        }

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid); 

        return ocean;
    }

    public static void printOceanMap(Ocean ocean){
        int numRows = ocean.getRows();
        int numCols = ocean.getCols();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getCheckingGrid();

        System.out.println();

        //First section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < displayGrid.length; i++) {
            for (int j = 0; j < displayGrid[i].length; j++) {
                if (j == 0){
                    if((i+1) < 10){

                        System.out.print(" " + (i+1) + "|" + displayGrid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + displayGrid[i][j]);
                    }
                }
                else if (j == displayGrid[i].length - 1){
                    System.out.print(displayGrid[i][j] + "|");
                }
                else{
                    System.out.print(displayGrid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }
}