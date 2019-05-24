import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class BattleShips {
    public static int numRows = 20;
    public static int numCols = 60;
    public static int playerShips;
    public static int computerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];

    public static void main(String[] args){

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");
        System.out.println(ThreadLocalRandom.current().nextInt(1, 20 + 1));


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
        BattleShips.playerShips = 5;
        for (int i = 1; i <= BattleShips.playerShips; i++) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt() -1;
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt() -1;

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#"))
            {
                for(int l = 0; l < 5; l++){
                    grid[x][y+l] = "@";
                }
            }
            else if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && grid[x][y] == "@")
                System.out.println("You can't place two or more ships on the same location");

            else if((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }
        printOceanMap();
    }


    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");

        //Deploying five ships for computer
        BattleShips.computerShips = 5;
        for (int i = 1; i <= BattleShips.computerShips; i++) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
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
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "x") //if computer ship is already there; computer loses ship
                {
                    System.out.println("Boom! You sunk the ship!");
                    grid[x][y] = "!"; //Hit mark
                    --BattleShips.computerShips;
                }
                else if (grid[x][y] == "@") {
                    System.out.println("Oh no, you sunk your own ship :(");
                    grid[x][y] = "x";
                    --BattleShips.playerShips;
                }
                else if (grid[x][y] == "#") {
                    System.out.println("Sorry, you missed");
                    grid[x][y] = " ";
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }


    public static void computerTurn(){
        System.out.println("\nCOMPUTER'S TURN");

        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "@") //if player ship is already there; player loses ship
                {
                    System.out.println("The Computer sunk one of your ships!");
                    grid[x][y] = "x";
                    --BattleShips.playerShips;
                    ++BattleShips.computerShips;
                }
                else if (grid[x][y] == "x") {
                    System.out.println("The Computer sunk one of its own ships");
                    grid[x][y] = "!";
                }
                else if (grid[x][y] == " ") {
                    System.out.println("Computer missed");

                    //Saving missed guesses for computer
                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
            }
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
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