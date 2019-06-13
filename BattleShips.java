import java.util.*;

public class BattleShips {
    public static void main(String[] args){
        Game game = new Game();
        Player player = new Player(15);

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Set game difficulty
        setDifficulty(game);

        //Step 2 – Create the ocean map
        Ocean ocean = Ocean.createOceanMap(20, 60);
        Ocean.printOceanMap(ocean);

        //Step 3 - Deploy ships
        game.deployShips(game, ocean);

        //Step 4 - Deploy traps
        game.deployTraps(game, ocean);

        //Step 5 - Deploy potions
        game.deployPotions(game, ocean);

        //Step 4 Battle
        do {
            Battle(ocean, game, player);
        }while(player.getPlayerDestroyedShips() < 5 && player.getPlayerLife() > 0);

        //Step 5 - Game over
        gameOver(player);
    }


    public static void setDifficulty(Game game){
        int level = -1;
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("Choose your level of difficulty: \n1.Beginner \n2.Intermediate \n3.Advance");
            level = input.nextInt();
    
            if(level == 1){
                game.setShips(80);
                game.setTraps(10);
                game.setPotions(18);
            }
            else if(level == 2){
                game.setShips(50);
                game.setTraps(20);
                game.setPotions(18);
            }
            else if(level == 3){
                game.setShips(20);
                game.setTraps(30);
                game.setPotions(18);
            }
            else{
                System.out.println("Please input a number from 1 to 3");
            }
        }while(level != 1 && level != 2 && level != 3);
    }
    
    public static void Battle(Ocean ocean, Game game, Player player){
        playGame(ocean, game, player);
        Ocean.printOceanMap(ocean);
        System.out.println();
        System.out.println("Ships found: " + player.getPlayerDestroyedShips());
        System.out.println("Life left: " + player.getPlayerLife());
        System.out.println();
    }
    


    public static void playGame(Ocean ocean, Game game, Player player){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        int numRows = ocean.getRows();
        int numCols = ocean.getCols();

        do {
            Scanner input = new Scanner(System.in);
            String[][] checkingGrid = ocean.getCheckingGrid();
            String[][] displayGrid = ocean.getDisplayGrid();
            
            System.out.print("Enter row coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter column coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols))
            {
                if (displayGrid[x][y] != " " && checkingGrid[x][y] == "c")
                {
                    BattleShips.hitShipAction(x, y, ocean, game, player);
                }
                else if (displayGrid[x][y] != " " && checkingGrid[x][y] == "t")
                {
                    BattleShips.hitTrapAction(x, y, ocean, game, player);
                }
                else if (displayGrid[x][y] != " " && checkingGrid[x][y] == "p") 
                {
                    BattleShips.hitPotionAction(x, y, ocean, game, player);
                }
                else if (displayGrid[x][y] == "#") 
                {
                    System.out.println("Sorry, you missed");
                    displayGrid[x][y] = " ";
                }
                else if (displayGrid[x][y] == " " || displayGrid[x][y] == "!") 
                {
                    System.out.println("You have chosen that position");
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                System.out.println("You can't chose position outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));
    }

    public static void hitShipAction(int x, int y, Ocean ocean, Game game, Player player){
        ArrayList<Ship> allShips = game.getAllShips();
        for(Ship ship : allShips){
            int shipX = ship.getX();
            ArrayList<Integer> shipY = ship.getY();
            if(x == shipX && shipY.contains(y)){
                game.removeShip(ship, ocean);
            }
        }
        System.out.println("Boom! You sunk the ship!");
        player.increaseShipDestroyed();
    }

    public static void hitTrapAction(int x, int y, Ocean ocean, Game game, Player player){
        ArrayList<Trap> allTraps = game.getAllTraps();
        for(Trap trap : allTraps){
            int trapX = trap.getX();
            int trapY = trap.getY();
            if(x == trapX && y == trapY){
                if(trap instanceof HighTrap){
                    System.out.println("Oh no, you hit in a High Danger Trap :(");
                    HighTrap highTrap = (HighTrap)trap;
                    player.decreaseLife(highTrap.getDamage());
                }else if (trap instanceof LowTrap){
                    System.out.println("Oh no, you hit in a Low Danger Trap :(");
                    LowTrap lowTrap = (LowTrap)trap;
                    player.decreaseLife(lowTrap.getDamage());
                }
                game.removeTrap(trap,ocean);
            }
        }
    }

    public static void hitPotionAction(int x, int y, Ocean ocean, Game game, Player player){
        ArrayList<Potion> allPotions = game.getAllPotions();

        for(Potion potion : allPotions){
            int potionX = potion.getX();
            int potionY = potion.getY();
            if(x == potionX && y == potionY){
                if(potion instanceof LifePotion){
                    System.out.println("Life Saver Potion, your health increase by 1");
                    LifePotion lifePotion = (LifePotion)potion;
                    player.increaseLife(lifePotion.getLife());
                }else if (potion instanceof ShipPotion){
                    System.out.println("Ship Reveal Potion");
                    game.revealShip(ocean, game);
                }else if (potion instanceof TrapPotion){
                    System.out.println("Trap Reveal Potion");
                    game.revealTrap(ocean, game);
                }
                game.removePotion(potion, ocean);
            }
        }
    }
    

    public static void gameOver(Player player){
        if(player.getPlayerDestroyedShips() == 5)
        {
            System.out.println("///////");
            System.out.println("|0   0|");
            System.out.println("|  U  |");
            System.out.println("|_____|");
            System.out.println("Hooray! You won the battle!");
        }           
        else
        {
            System.out.println("///////");
            System.out.println("|0   0|");
            System.out.println("|  ^  |");
            System.out.println("|_____|");
            System.out.println("Sorry! You lost the battle ");
        }
        System.out.println();
    }
}