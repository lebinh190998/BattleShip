import java.util.*;

public class Player {
    private int playerDestroyedShips;
    private int playerLife;

    public Player(int playerLife) {
        this.playerLife = playerLife;
        this.playerDestroyedShips = 0;
    }


    // Getter
    public int getPlayerLife() {
        return this.playerLife;
    }
    public int getPlayerDestroyedShips() {
        return this.playerDestroyedShips;
    }


    // Setter
    public void setPlayerLife(int life) {
        this.playerLife = life;
    }
    public void setPlayerDestroyedShips(int destroyedShips) {
        this.playerDestroyedShips = destroyedShips;
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