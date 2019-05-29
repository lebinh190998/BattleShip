import java.util.*;
import java.util.Random;

public class BattleShips {
    public static int numRows = 20;
    public static int numCols = 60;
    public static int playerShips;
    public static int computerShips;
    public static int playerLife = 15;
    public static int computerLife = 15;
    public static int traps;
    public static int potions;
    public static ArrayList<String> remainingTraps = new ArrayList<String>();
    public static ArrayList<Integer> remainingPotions = new ArrayList<Integer>();
    public static ArrayList<String> remainingPlayerShips = new ArrayList<String>();
    public static ArrayList<String> remainingComputerShips = new ArrayList<String>();
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
    public static int[][] playerShipPosition = new int[numRows][numCols];
    public static int[][] computerShipPosition = new int[numRows][numCols];
    public static int[][] trapsPosition = new int[numRows][numCols];
    public static int[][] potionsPosition = new int[numRows][numCols];

    public static void main(String[] args){

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");


        //Step 1 – Create the ocean map
        createOceanMap();

        //Step 2 – Deploy player’s ships
        deployPlayerShips();

        //Step 3 - Deploy computer's ships
        deployComputerShips();

        //Step 3 - Deploy computer's ships
        deployTraps();

        //Step 3 - Deploy computer's ships
        deployPotions();

        //Step 4 Battle
        do {
            Battle();
        }while((BattleShips.playerShips != 0 && BattleShips.computerShips != 0) && (BattleShips.playerLife != 0 && BattleShips.computerLife != 0));

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
        BattleShips.playerShips = 3;
        for (int i = 1; i <= BattleShips.playerShips; i++) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt() -1;
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt() -1;
            Random randomGenerator = new Random();
            int length = randomGenerator.nextInt(3) + 3;

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (grid[x][y] == "#"))
            {
                for(int l = 0; l < length; l++){
                    playerShipPosition[x][y+l] = i;
                    grid[x][y+l] = "@";
                }
                Integer obj = new Integer(i);
                BattleShips.remainingPlayerShips.add(obj.toString());
            }
            else if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && grid[x][y] == "@")
                System.out.println("You can't place two or more ships on the same location");

            else if((x < 0 || x >= numRows) || (y < 0 || y >= numCols-length))
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }
        printOceanMap();
        System.out.println("Remaining Play Ships" + BattleShips.remainingPlayerShips);
    }


    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");

        //Deploying five ships for computer
        BattleShips.computerShips = 3;
        int i = 1;
        while (i <= BattleShips.computerShips) {
            Random randomGenerator = new Random();
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            int length = randomGenerator.nextInt(3) + 3;
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (grid[x][y] == "#"))
            {
                for(int l = 0; l < length; l++){
                    computerShipPosition[x][y+l] = i;
                    grid[x][y+l] = "x";
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
        BattleShips.traps = 3;
        int i = 1;
        while (i <= BattleShips.traps) {
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#"))
            {
                Integer obj = new Integer(i);
                BattleShips.remainingTraps.add(obj.toString());
                trapsPosition[x][y] = i;
                grid[x][y] = "T";
                i++;
            }
        }
        printOceanMap();
    }

    public static void deployPotions(){
        System.out.println("\nDeploying potions");

        //Deploying five traps
        BattleShips.potions = 4;
        int i = 1;
        while (i <= BattleShips.potions) {
            Random randomGenerator = new Random();
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#"))
            {
                BattleShips.remainingPotions.add(i);
                potionsPosition[x][y] = i;
                grid[x][y] = "P";
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
        System.out.println("Your life: " + BattleShips.playerLife + " | Computer life: " + BattleShips.computerLife);
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
                if (grid[x][y] == "x") //if computer ship is already there; computer loses ship
                {
                    for(int i = 1; i <= BattleShips.playerShips; i++){
                        if(computerShipPosition[x][y] == i){
                           revealShip(i, "computer");
                        }  
                    }
                    System.out.println("Boom! You sunk the ship!");
                    --BattleShips.computerShips;
                }
                else if (grid[x][y] == "@") 
                {
                    for(int i = 1; i <= BattleShips.playerShips; i++){
                        if(playerShipPosition[x][y] == i){
                           revealShip(i, "player");
                        }  
                    }
                    System.out.println("Oh no, you sunk your own ship :(");
                    --BattleShips.playerShips;
                }
                else if (grid[x][y] == "T") 
                {
                    int type = randomGenerator.nextInt(2) + 1;
                    if(type == 1){
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               revealTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a Low Danger Trap :(");
                        --BattleShips.playerLife;
                    }else{
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               revealTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a High Danger Trap :(");
                        BattleShips.playerLife -= 2;
                    }
                }
                else if (grid[x][y] == "P") 
                {
                    //int type = randomGenerator.nextInt(3) + 1;
                    int type = 3;
                    if(type == 1){
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Life Saver Potion, your health increase by 1");
                        ++BattleShips.playerLife;
                    }else if(type == 2){
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }
                        }
                        System.out.println("Trap Reveal Potion");
                        if(BattleShips.remainingTraps.size() > 0){
                            revealTrap(Integer.parseInt(BattleShips.remainingTraps.get(0)));
                        }else{
                            System.out.println("No more trap to reveal");
                        }
                    }else{
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Ship Reveal Potion");
                        if(BattleShips.remainingComputerShips.size() > 0){
                            revealShip(Integer.parseInt(BattleShips.remainingComputerShips.get(0)), "computer");
                        }else{
                            System.out.println("No more ship to reveal");
                        }
                    }
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
            Random randomGenerator = new Random();
            x = randomGenerator.nextInt(60);
            y = randomGenerator.nextInt(20);
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "@") //if player ship is already there; player loses ship
                {
                    for(int i = 1; i <= BattleShips.playerShips; i++){
                        if(playerShipPosition[x][y] == i){
                           revealShip(i, "player");
                        }  
                    }
                    System.out.println("The Computer sunk one of your ships!");
                    --BattleShips.playerShips;
                }
                else if (grid[x][y] == "x") 
                {
                    for(int i = 1; i <= BattleShips.playerShips; i++){
                        if(computerShipPosition[x][y] == i){
                           revealShip(i, "computer");
                        }  
                    }
                    System.out.println("The Computer sunk one of its own ships");
                    --BattleShips.computerShips;
                }
                else if (grid[x][y] == "T") 
                {
                    int type = randomGenerator.nextInt(2) + 1;
                    if(type == 1){
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               revealTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a Low Danger Trap :(");
                        --BattleShips.playerLife;
                    }else{
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               revealTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a High Danger Trap :(");
                        BattleShips.playerLife -= 2;
                    }
                }
                else if (grid[x][y] == "P") 
                {
                    int type = randomGenerator.nextInt(3) + 1;
                    if(type == 1){
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Life Saver Potion, your health increase by 1");
                        ++BattleShips.playerLife;
                    }else if(type == 2){
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }
                        }
                        System.out.println("Trap Reveal Potion");
                        if(BattleShips.remainingTraps.size() > 0){
                            revealTrap(Integer.parseInt(BattleShips.remainingTraps.get(0)));
                        }else{
                            System.out.println("No more trap to reveal");
                        }
                    }else{
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Ship Reveal Potion");
                        if(BattleShips.remainingComputerShips.size() > 0){
                            revealShip(Integer.parseInt(BattleShips.remainingComputerShips.get(0)), "computer");
                        }else{
                            System.out.println("No more ship to reveal");
                        }
                    }
                }
                else if (grid[x][y] == "#") 
                {
                    System.out.println("Computer missed");
                    grid[x][y] = " ";
                }
            }
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }


    public static void gameOver(){
        System.out.println("Your ships: " + BattleShips.playerShips + " | Computer ships: " + BattleShips.computerShips);
        System.out.println("Your life: " + BattleShips.playerLife + " | Computer life: " + BattleShips.computerLife);
        if(BattleShips.playerShips > 0 && BattleShips.computerShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
    }

    public static void revealShip(int shipNo, String type){
        Integer obj = new Integer(shipNo);
        if(type == "player"){
            for(int i = 0; i < playerShipPosition.length; i++) {
                for (int j = 0; j < playerShipPosition[i].length; j++) {
                    if (playerShipPosition[i][j] == shipNo){
                        grid[i][j] = "!";
                        playerShipPosition[i][j] = 0;
                    }
                    else{
                    }
                }
                System.out.println();
            }
            BattleShips.remainingPlayerShips.remove(obj.toString());
            System.out.println("remaining Player Ships"+ BattleShips.remainingPlayerShips);
        }
        else {
            for(int i = 0; i < computerShipPosition.length; i++) {
                for (int j = 0; j < computerShipPosition[i].length; j++) {
                    if (computerShipPosition[i][j] == shipNo){
                        grid[i][j] = "!";
                        computerShipPosition[i][j] = 0;
                    }
                    else{
                    }
                }
                System.out.println();
            }
            BattleShips.remainingComputerShips.remove(obj.toString());
            System.out.println("remaining Computer Ships"+ BattleShips.remainingComputerShips);
        }
    }

    public static void revealTrap(int trapNo){
        Integer obj = new Integer(trapNo);
        for(int i = 0; i < trapsPosition.length; i++) {
            for (int j = 0; j < trapsPosition[i].length; j++) {
                if (trapsPosition[i][j] == trapNo){
                    grid[i][j] = " ";
                }
                else{
                }
            }
            System.out.println();
        }
        BattleShips.remainingTraps.remove(obj.toString());
        System.out.println("remaining traps"+ BattleShips.remainingTraps);
    }

    public static void revealPotion(int potionNo){
        for(int i = 0; i < potionsPosition.length; i++) {
            for (int j = 0; j < potionsPosition[i].length; j++) {
                if (potionsPosition[i][j] == potionNo){
                    grid[i][j] = " ";
                    potionsPosition[i][j] = 0;
                }
                else{
                }
            }
            System.out.println();
        }
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