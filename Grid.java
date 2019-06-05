public class Grid {
    public static int numRows;
    public static int numCols;
    public static String[][] grid;

    public Grid(int numRows, int numCols) {
        Grid.numRows = numRows;
        Grid.numCols = numCols;
        Grid.grid = new String[numRows][numCols];
    }

    // Getter
    public static int getRows() {
        return numRows;
    }
    public static int getCols() {
        return numCols;
    }
    public static String[][] getGrid() {
        return grid;
    }

    // Setter
    public static void setRows(int newRows) {
        Grid.numRows = newRows;
    }
    public static void setCols(int newCols) {
        Grid.numCols = newCols;
    }
    public static void setGrid(String[][] newGrid) {
        Grid.grid = newGrid;
    }

    public static void createOceanMap(){
        //Header section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < Grid.grid.length; i++) {
            for (int j = 0; j < Grid.grid[i].length; j++) {
                Grid.grid[i][j] = "#";
                if (j == 0){
                    if((i+1) < 10){
                        System.out.print(" " + (i+1) + "|" + Grid.grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + Grid.grid[i][j]);
                    }
                }
                else if (j == Grid.grid[i].length - 1){
                    System.out.print(Grid.grid[i][j] + "|");
                }
                else{
                    System.out.print(Grid.grid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= Grid.numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printOceanMap(){
        System.out.println();

        //First section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < Grid.grid.length; i++) {
            for (int j = 0; j < Grid.grid[i].length; j++) {
                if (j == 0){
                    if((i+1) < 10){

                        System.out.print(" " + (i+1) + "|" + Grid.grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + Grid.grid[i][j]);
                    }
                }
                else if (j == Grid.grid[i].length - 1){
                    System.out.print(Grid.grid[i][j] + "|");
                }
                else{
                    System.out.print(Grid.grid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= Grid.numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }
}