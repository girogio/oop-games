package mt.grigolo.game;

import mt.grigolo.players.Player;
import mt.grigolo.players.types.Human;
import mt.grigolo.utils.Input;

public class Game {

    private final Map map;

    private int round;

    public Game(int playerCount, int aiCount, int w, int h) {
        this.map = new Map(w, h, playerCount, aiCount);
        this.round = 1;
    }

    public void startGame() {
        doRound();
    }

    public void doRound() {

        for (Player player : map.getPlayers()) {
            if (player.isAlive()) {
                if (player instanceof Human) {
                    Input.clearScreen();
                    System.out.println("Round " + round + ", player " + player.getId() + "'s turn.");
                    System.out.println("Map: ");
                    System.out.println(map);
                }
                player.doTurn();
            } else {
                map.getPlayers().remove(player);
            }
        }
        // Win condition
        if (map.getPlayers().size() == 1) {
            System.out.println("Player " + map.getPlayers().get(0).getId() + " won!");
            return;
        }

        // March phase
        for (Player player : map.getPlayers()) {
            player.getVillage().getArmy().march();
        }

        round++;
        doRound();
    }
}
