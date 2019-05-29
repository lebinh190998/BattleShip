import java.util.*;

public class BattleShips {
    public static int numRows = 20;
    public static int numCols = 60;
    public static int playerShips;
    public static int computerShips;
    public static int playerDestroyedShips = 0;
    public static int computerDestroyedShips = 0;
    public static int playerLife = 3;
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
        setDifficulty();

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
        }while((BattleShips.playerDestroyedShips < 5 && BattleShips.computerDestroyedShips < 5) && (BattleShips.playerLife > 0 && BattleShips.computerLife > 0));

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
                BattleShips.playerShips = 80;
                BattleShips.computerShips = 80;
                BattleShips.traps = 10;
                BattleShips.potions = 18;
            }
            else if(level == 2){
                BattleShips.playerShips = 50;
                BattleShips.computerShips = 50;
                BattleShips.traps = 20;
                BattleShips.potions = 18;
            }
            else if(level == 3){
                BattleShips.playerShips = 20;
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


    public static void deployPlayerShips(){
        Scanner input = new Scanner(System.in);
        Random randomGenerator = new Random();
        System.out.println("\nDeploy your ships:");

        //Deploying five ships for player
        int i = 1;
        while (i <= BattleShips.computerShips) {
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            int length = randomGenerator.nextInt(3) + 3;

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (grid[x][y] == "#") && (computerShipPosition[x][y] == 0) && (trapsPosition[x][y] == 0) && (potionsPosition[x][y] == 0))
            {
                for(int l = 0; l < length; l++){
                    playerShipPosition[x][y+l] = i;
                    grid[x][y+l] = "0";
                }
                Integer obj = new Integer(i);
                BattleShips.remainingPlayerShips.add(obj.toString());
                i++;
            }
        }
        printOceanMap();
    }


    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");

        //Deploying five ships for computer
        int i = 1;
        while (i <= BattleShips.computerShips) {
            Random randomGenerator = new Random();
            int x = randomGenerator.nextInt(20);
            int y = randomGenerator.nextInt(60);
            int length = randomGenerator.nextInt(3) + 3;
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (grid[x][y] == "#") && (playerShipPosition[x][y] == 0) && (trapsPosition[x][y] == 0) && (potionsPosition[x][y] == 0))
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
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#") && (playerShipPosition[x][y] == 0) && (computerShipPosition[x][y] == 0) && (potionsPosition[x][y] == 0))
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
            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == "#") && (playerShipPosition[x][y] == 0) && (trapsPosition[x][y] == 0) && (computerShipPosition[x][y] == 0))
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
        computerTurn();
        printOceanMap();
        System.out.println();
        System.out.println("Your destroyed ships: " + BattleShips.playerDestroyedShips + " | Computer's destroyed ships: " + BattleShips.computerDestroyedShips);
        System.out.println("Your life: " + BattleShips.playerLife + "            | Computer's life: " + BattleShips.computerLife);
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
                           removeShip(i, "computer");
                        }  
                    }
                    System.out.println("Boom! You sunk the ship!");
                    ++BattleShips.playerDestroyedShips;
                }
                else if (grid[x][y] == "0" || playerShipPosition[x][y] != 0)
                {
                    for(int i = 1; i <= BattleShips.playerShips; i++){
                        if(playerShipPosition[x][y] == i){
                           removeShip(i, "player");
                        }  
                    }
                    System.out.println("Oh no, you sunk your own ship :(");
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
                    int type = 1;
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
                            revealShip(Integer.parseInt(BattleShips.remainingComputerShips.get(0)), "computer");
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
                if (grid[x][y] == "0" || playerShipPosition[x][y] != 0) //if player ship is already there; player loses ship
                {
                    for(int i = 1; i <= BattleShips.playerShips; i++){
                        if(playerShipPosition[x][y] == i){
                            removeShip(i, "player");
                        }  
                    }
                    System.out.println("The Computer sunk one of your ships!");
                    ++BattleShips.computerDestroyedShips;
                }
                else if (grid[x][y] == "C" || computerShipPosition[x][y] != 0) 
                {
                    for(int i = 1; i <= BattleShips.computerShips; i++){
                        if(computerShipPosition[x][y] == i){
                            removeShip(i, "computer");
                        }  
                    }
                    System.out.println("The Computer sunk one of its own ships");
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
                        System.out.println("Computer hit in a Low Danger Trap :(");
                        --BattleShips.computerLife;
                    }else{
                        for(int i = 1; i <= BattleShips.traps; i++){
                            if(trapsPosition[x][y] == i){
                               removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a High Danger Trap :(");
                        BattleShips.computerLife -= 2;
                    }
                }
                else if (grid[x][y] == "P" || potionsPosition[x][y] != 0) 
                {
                    int type = randomGenerator.nextInt(3);
                    if(type == 0)
                    {
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }  
                        }
                        System.out.println("Computer got Life Saver Potion");
                        ++BattleShips.computerLife;
                    }
                    else if(type == 1)
                    {
                        for(int i = 1; i <= BattleShips.potions; i++){
                            if(potionsPosition[x][y] == i){
                               revealPotion(i);
                            }
                        }
                        System.out.println("Computer got Trap Reveal Potion");
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
                        if(BattleShips.remainingComputerShips.size() > 0){
                            revealShip(Integer.parseInt(BattleShips.remainingComputerShips.get(0)), "computer");
                        }
                        else
                        {
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
        if(BattleShips.playerDestroyedShips == 5 || BattleShips.computerLife < 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
    }

    public static void removeShip(int shipNo, String type){
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

    public static void revealShip(int shipNo, String type){
        Integer obj = new Integer(shipNo);
        if(type == "player")
        {
            for(int i = 0; i < playerShipPosition.length; i++) {
                for (int j = 0; j < playerShipPosition[i].length; j++) {
                    if (playerShipPosition[i][j] == shipNo){
                        grid[i][j] = "0";
                    }
                    else{
                    }
                }
                System.out.println();
            }
            System.out.println("remaining Player Ships"+ BattleShips.remainingPlayerShips);
        }
        else 
        {
            for(int i = 0; i < computerShipPosition.length; i++) {
                for (int j = 0; j < computerShipPosition[i].length; j++) {
                    if (computerShipPosition[i][j] == shipNo){
                        grid[i][j] = "C";
                    }
                    else{
                    }
                }
                System.out.println();
            }
            System.out.println("remaining Computer Ships"+ BattleShips.remainingComputerShips);
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
            System.out.println();
        }
        BattleShips.remainingTraps.remove(obj.toString());
        System.out.println("remaining traps"+ BattleShips.remainingTraps);
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
            System.out.println();
        }
        System.out.println("remaining traps"+ BattleShips.remainingTraps);
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
            System.out.println();
        }
        BattleShips.remainingPotions.remove(obj.toString());
        System.out.println("remaining potions"+ BattleShips.remainingTraps);
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