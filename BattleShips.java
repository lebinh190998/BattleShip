import java.util.*;
import java.util.Random;

public class BattleShips {
    public static int numRows = 20;
    public static int numCols = 60;
    public static int playerShips;
    public static int computerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
    public static int[][] shipPosition = new int[numRows][numCols];

    public static void main(String[] args){

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");


        //Step 1 – Create the ocean map
        createOceanMap();

        //Step 2 – Deploy player’s ships
        deployPlayerShips();

        //Step 3 - Deploy computer's ships
        deployComputerShips();

        //Step 4 Battle
        do {
            Battle();
        }while(BattleShips.playerShips != 0 && BattleShips.computerShips != 0);

        //Step 5 - Game over
        gameOver();
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


    public static void deployPlayerShips(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nDeploy your ships:");

        //Deploying five ships for player
        BattleShips.playerShips = 1;
        for (int i = 1; i <= BattleShips.playerShips; i++) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt() -1;
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt() -1;
            Random randomGenerator = new Random();
            int length = randomGenerator.nextInt(3) + 3;

            if((x >= 0 && x < numCols-length) && (y >= 0 && y < numRows) && (grid[y][x] == "#"))
            {
                for(int l = 0; l < length; l++){
                    shipPosition[y][x+l] = 1;
                    grid[y][x+l] = "@";
                }
            }
            else if((x >= 0 && x < numCols) && (y >= 0 && y < numRows) && grid[y][x] == "@")
                System.out.println("You can't place two or more ships on the same location");

            else if((x < 0 || x >= numCols-length) || (y < 0 || y >= numRows))
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }
        printOceanMap();
    }


    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");

        //Deploying five ships for computer
        BattleShips.computerShips = 1;
        for (int i = 1; i <= BattleShips.computerShips; i++) {
            Random randomGenerator = new Random();
            //int x = randomGenerator.nextInt(60);
            //int y = randomGenerator.nextInt(20);
            int x = 1;
            int y = 1;
            int length = randomGenerator.nextInt(3) + 3;
            if((x >= 0 && x < numCols-length) && (y >= 0 && y < numRows) && (grid[y][x] == "#"))
            {
                for(int l = 0; l < length; l++){
                    shipPosition[y][x+l] = 2;
                    grid[y][x+l] = "x";
                }
            }
        }
        printOceanMap();
    }


    public static void Battle(){
        playerTurn();
        computerTurn();
        printOceanMap();
        System.out.println();
        System.out.println("Your ships: " + BattleShips.playerShips + " | Computer ships: " + BattleShips.computerShips);
        System.out.println();
    }


    public static void playerTurn(){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < numCols) && (y >= 0 && y < numRows)) //valid guess
            {
                if (grid[y][x] == "x") //if computer ship is already there; computer loses ship
                {
                    System.out.println("Boom! You sunk the ship!");
                    grid[y][x] = "!"; //Hit mark
                    --BattleShips.computerShips;
                }
                else if (grid[y][x] == "@") {
                    System.out.println("Oh no, you sunk your own ship :(");
                    grid[y][x] = "x";
                    --BattleShips.playerShips;
                }
                else if (grid[y][x] == "#") {
                    System.out.println("Sorry, you missed");
                    grid[y][x] = " ";
                }
            }
            else if ((x < 0 || x >= numCols) || (y < 0 || y >= numRows))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numCols) || (y < 0 || y >= numRows));  //keep re-prompting till valid guess
    }


    public static void computerTurn(){
        System.out.println("\nCOMPUTER'S TURN");

        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            Random randomGenerator = new Random();
            x = randomGenerator.nextInt(60);
            y = randomGenerator.nextInt(20);
            if ((x >= 0 && x < numCols) && (y >= 0 && y < numRows)) //valid guess
            {
                if (grid[y][x] == "@") //if player ship is already there; player loses ship
                {
                    System.out.println("The Computer sunk one of your ships!");
                    grid[y][x] = "x";
                    --BattleShips.playerShips;
                }
                else if (grid[y][x] == "x") {
                    System.out.println("The Computer sunk one of its own ships");
                    grid[y][x] = "!";
                    --BattleShips.computerShips;
                }
                else if (grid[y][x] == "#") {
                    System.out.println("Computer missed");

                    //Saving missed guesses for computer
                    if(missedGuesses[y][x] != 3)
                        missedGuesses[y][x] = 3;
                }
            }
        }while((x < 0 || x >= numCols) || (y < 0 || y >= numRows));  //keep re-prompting till valid guess
    }


    public static void gameOver(){
        System.out.println("Your ships: " + BattleShips.playerShips + " | Computer ships: " + BattleShips.computerShips);
        if(BattleShips.playerShips > 0 && BattleShips.computerShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
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
                    System.out.print(grid[i][j]=="x"?"#":grid[i][j] + "|");
                }
                else{
                    System.out.print(grid[i][j]=="x"?"#":grid[i][j]);
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