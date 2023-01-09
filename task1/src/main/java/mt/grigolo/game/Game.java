package mt.grigolo.game;

import mt.grigolo.players.Player;
import mt.grigolo.troops.Army;

public class Game {

    private final Map map;

    public Game(int playerCount, int aiCount, int w, int h) {
        this.map = new Map(w, h, playerCount, aiCount);
    }

    public void startGame(){
        doRound();
    }

    public void doRound() {

        System.out.println(map);

        for (Player player : map.getPlayers()) {
            if (player.isAlive()) {
                player.doTurn();
            } else {
                map.getPlayers().remove(player);
            }
        }

        if (map.getPlayers().size() == 1) {
            System.out.println("Player " + map.getPlayers().get(0).getClass().getSimpleName() + " won!");
            return;
        }

        for (Player player : map.getPlayers()) {
            for (Army army : player.getVillage().getEnemyArmies()) {
                army.march();
            }
        }

        doRound();
    }
}
