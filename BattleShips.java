import java.util.*;

public class BattleShips {
    public static int numRows = 20;
    public static int numCols = 60;
    public static int computerShips;
    public static int playerDestroyedShips = 0;
    public static int playerLife = 3;
    public static int traps;
    public static int potions;
    public static ArrayList<String> remainingComputerShips = new ArrayList<String>();
    public static ArrayList<String> remainingTraps = new ArrayList<String>();
    public static ArrayList<Integer> remainingPotions = new ArrayList<Integer>();
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] computerShipPosition = new int[numRows][numCols];
    public static int[][] trapsPosition = new int[numRows][numCols];
    public static int[][] potionsPosition = new int[numRows][numCols];

    public static void main(String[] args){

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");


        //Step 1 – Create the ocean map
        setDifficulty();

        //Step 1 – Create the ocean map
        createOceanMap();

        //Step 3 - Deploy computer's ships
        deployComputerShips();

        //Step 3 - Deploy computer's ships
        deployTraps();

        //Step 3 - Deploy computer's ships
        deployPotions();

        //Step 4 Battle
        do {
            Battle();
        }while(BattleShips.playerDestroyedShips < 5 && BattleShips.playerLife > 0);

        //Step 5 - Game over
        gameOver();
    }


    public static void setDifficulty(){
        int level = -1;
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("Choose your level of difficulty: \n1.Beginner \n2.Intermediate \n3.Advance");
            level = input.nextInt();
    
            if(level == 1){
                BattleShips.computerShips = 80;
                BattleShips.traps = 10;
                BattleShips.potions = 18;
            }
            else if(level == 2){
                BattleShips.computerShips = 50;
                BattleShips.traps = 20;
                BattleShips.potions = 18;
            }
            else if(level == 3){
                BattleShips.computerShips = 20;
                BattleShips.traps = 30;
                BattleShips.potions = 18;
            }
            else{
                System.out.println("Please input a number from 1 to 3");
            }
        }while(level != 1 && level != 2 && level != 3);
    }

    public static void createOceanMap(){
        //Header section of Ocean Map
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
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "#";
                if (j == 0){
                    if((i+1) < 10){
                        System.out.print(" " + (i+1) + "|" + grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + grid[i][j]);
                    }
                }
                else if (j == grid[i].length - 1){
                    System.out.print(grid[i][j] + "|");
                }
                else{
                    System.out.print(grid[i][j]);
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

    public static void deployComputerShips(){
        System.out.println("\nDeploying ships");

        //Deploying five ships for computer
        int i = 1;
        while (i <= BattleShips.computerShips) {
            Random randomGenerator = new Random();
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            int length = randomGenerator.nextInt(3) + 3;
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (grid[x][y] == "#") && (trapsPosition[x][y] == 0) && (potionsPosition[x][y] == 0))
            {
                for(int l = 0; l < length; l++){
                    computerShipPosition[x][y+l] = i;
                    grid[x][y+l] = "c";
                }
                Integer obj = new Integer(i);
                BattleShips.remainingComputerShips.add(obj.toString());
                i++;
            }
        }
        printOceanMap();
    }

    public static void deployTraps(){
        Random randomGenerator = new Random();
        System.out.println("\nDeploying traps");

        //Deploying five traps
        int i = 1;
        while (i <= BattleShips.traps) {
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#") && (computerShipPosition[x][y] == 0) && (potionsPosition[x][y] == 0))
            {
                Integer obj = new Integer(i);
                BattleShips.remainingTraps.add(obj.toString());
                trapsPosition[x][y] = i;
                grid[x][y] = "t";
                i++;
            }
        }
        printOceanMap();
    }

    public static void deployPotions(){
        System.out.println("\nDeploying potions");

        //Deploying five traps
        int i = 1;
        while (i <= BattleShips.potions) {
            Random randomGenerator = new Random();
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#") && (trapsPosition[x][y] == 0) && (computerShipPosition[x][y] == 0))
            {
                BattleShips.remainingPotions.add(i);
                potionsPosition[x][y] = i;
                grid[x][y] = "p";
                i++;
            }
        }
        printOceanMap();
    }

    public static void Battle(){
        playerTurn();
        printOceanMap();
        System.out.println();
        System.out.println("Destroyed ships: " + BattleShips.playerDestroyedShips);
        System.out.println("Your life: " + BattleShips.playerLife);
        System.out.println();
    }


    public static void playerTurn(){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        do {
            Random randomGenerator = new Random();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "C" || computerShipPosition[x][y] != 0) //if computer ship is already there; computer loses ship
                {
                    for(int i = 1; i <= BattleShips.computerShips; i++){
                        if(computerShipPosition[x][y] == i){
                           removeShip(i);
                        }  
                    }
                    System.out.println("Boom! You sunk the ship!");
                    ++BattleShips.playerDestroyedShips;
                }
                else if (grid[x][y] == "T" || trapsPosition[x][y] != 0)
                {
                    int type = randomGenerator.nextInt(2);
                    if(type == 0){
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a Low Danger Trap :(");
                        --BattleShips.playerLife;
                    }else{
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a High Danger Trap :(");
                        BattleShips.playerLife -= 2;
                    }
                }
                else if (grid[x][y] == "P" || potionsPosition[x][y] != 0) 
                {
                    //int type = randomGenerator.nextInt(3);
                    int type = 2;
                    if(type == 0)
                    {
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Life Saver Potion, your health increase by 1");
                        ++BattleShips.playerLife;
                    }
                    else if(type == 1)
                    {
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }
                        }
                        System.out.println("Trap Reveal Potion");
                        if(BattleShips.remainingTraps.size() > 0)
                        {
                            revealTrap(Integer.parseInt(BattleShips.remainingTraps.get(0)));
                        }
                        else
                        {
                            System.out.println("No more trap to reveal");
                        }
                    }
                    else
                    {
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Ship Reveal Potion");
                        if(BattleShips.remainingComputerShips.size() > 0)
                        {
                            revealShip(Integer.parseInt(BattleShips.remainingComputerShips.get(0)));
                        }
                        else
                        {
                            System.out.println("No more ship to reveal");
                        }
                    }
                }
                else if (grid[x][y] == "#") 
                {
                    System.out.println("Sorry, you missed");
                    grid[x][y] = " ";
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }

    public static void gameOver(){
        if(BattleShips.playerDestroyedShips == 5)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry! You lost the battle :(");
        System.out.println();
    }

    public static void removeShip(int shipNo){
        Integer obj = new Integer(shipNo);
        for(int i = 0; i < computerShipPosition.length; i++) {
            for (int j = 0; j < computerShipPosition[i].length; j++) {
                if (computerShipPosition[i][j] == shipNo){
                    grid[i][j] = "!";
                    computerShipPosition[i][j] = 0;
                }
                else{
                }
            }
        }
        BattleShips.remainingComputerShips.remove(obj.toString());
    }

    public static void revealShip(int shipNo){
        Integer obj = new Integer(shipNo);
        for(int i = 0; i < computerShipPosition.length; i++) {
            for (int j = 0; j < computerShipPosition[i].length; j++) {
                if (computerShipPosition[i][j] == shipNo){
                    grid[i][j] = "C";
                }
                else{
                }
            }
        }
    }

    public static void removeTrap(int trapNo){
        Integer obj = new Integer(trapNo);
        for(int i = 0; i < trapsPosition.length; i++) {
            for (int j = 0; j < trapsPosition[i].length; j++) {
                if (trapsPosition[i][j] == trapNo){
                    grid[i][j] = " ";
                }
                else{
                }
            }
        }
        BattleShips.remainingTraps.remove(obj.toString());
    }

    public static void revealTrap(int trapNo){
        Integer obj = new Integer(trapNo);
        for(int i = 0; i < trapsPosition.length; i++) {
            for (int j = 0; j < trapsPosition[i].length; j++) {
                if (trapsPosition[i][j] == trapNo){
                    grid[i][j] = "T";
                }
                else{
                }
            }
        }
    }

    public static void revealPotion(int potionNo){
        Integer obj = new Integer(potionNo);
        for(int i = 0; i < potionsPosition.length; i++) {
            for (int j = 0; j < potionsPosition[i].length; j++) {
                if (potionsPosition[i][j] == potionNo){
                    grid[i][j] = " ";
                    potionsPosition[i][j] = 0;
                }
                else{
                }
            }
        }
        BattleShips.remainingPotions.remove(obj.toString());
    }

    public static void printOceanMap(){
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
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0){
                    if((i+1) < 10){

                        System.out.print(" " + (i+1) + "|" + grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + grid[i][j]);
                    }
                }
                else if (j == grid[i].length - 1){
                    System.out.print(grid[i][j] + "|");
                }
                else{
                    System.out.print(grid[i][j]);
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