import java.util.*;

public class Player {
    private String name;
    private int stepsTaken;
    private int playerDestroyedShips;
    private int playerLife;

    public Player(String name, int playerLife) {
        this.name = name;
        this.playerLife = playerLife;
        this.playerDestroyedShips = 0;
        this.stepsTaken = 0;
    }


    // Getter
    public String getName() {
        return this.name;
    }
    public int getStepsTaken() {
        return this.stepsTaken;
    }
    public int getPlayerLife() {
        return this.playerLife;
    }
    public int getPlayerDestroyedShips() {
        return this.playerDestroyedShips;
    }


    // Setter
    public void setName(String newName) {
        this.name = newName;
    }
    public void setStepsTaken(int steps) {
        this.stepsTaken = steps;
    }
    public void setPlayerLife(int life) {
        this.playerLife = life;
    }
    public void setPlayerDestroyedShips(int destroyedShips) {
        this.playerDestroyedShips = destroyedShips;
    }

    public void increaseSteps() {
        this.stepsTaken += 1;
    }
    public void increaseLife(int life) {
        this.playerLife += life;
    }
    public void decreaseLife(int life) {
        this.playerLife -= life;
    }
    public void increaseShipDestroyed() {
        this.playerDestroyedShips += 1;
    }
}