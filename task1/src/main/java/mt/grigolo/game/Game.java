package mt.grigolo.game;

import mt.grigolo.players.Player;
import mt.grigolo.players.types.AI;
import mt.grigolo.utils.Input;

public class Game {

    private final Map map;


    public Game(int playerCount, int aiCount, int w, int h) {
        this.map = new Map(w, h, playerCount, aiCount);
    }

    public void startGame() {
        doRound();
    }

    public void doRound() {

        map.getPlayers().removeIf(player -> !player.isAlive());

        map.getPlayers().stream().filter(Player::isAlive).forEach(Player::doTurn);

        // Win condition
        if (map.getPlayers().size() == 1) {
            System.out.println("Player " + map.getPlayers().get(0).getId() + " won!");
            Input.closeScanner();
            return;
        }

        // Make AI slowly die in agony, and self-inflicted pain.
        map.getPlayers().stream().filter(player -> player instanceof AI)
                .forEach(player -> player.getVillage().damage((int) (Math.random() * 10)));

        // March phase
        map.getPlayers().forEach(player -> player.getVillage().getArmy().march());

        map.round++;

        doRound();
    }
}
