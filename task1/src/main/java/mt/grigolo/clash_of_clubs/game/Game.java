package mt.grigolo.clash_of_clubs.game;

import mt.grigolo.clash_of_clubs.players.Player;
import mt.grigolo.clash_of_clubs.utils.Input;

public class Game {

    private final Map map;

    public Game(int playerCount, int aiCount, int w, int h) {
        this.map = new Map(w, h, playerCount, aiCount);
    }

    public void startGame() {
        doRound();
    }

    public void doRound() {



        // Clean up dead players
        Map.players.removeIf(player -> !player.isAlive());

        Map.players.stream().filter(Player::isAlive).forEach(Player::doTurn);

        // Win condition
        if (Map.players.size() == 1) {
            Input.clearScreen();
            System.out.println("""
                    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                    ░░░░█▀▀▀░█▀▀▀░░█▀▀░▀▀█░░█░░░░
                    ░░░░█░▀█░█░▀█░░█▀▀░▄▀░░░▀░░░░
                    ░░░░▀▀▀▀░▀▀▀▀░░▀▀▀░▀▀▀░░▀░░░░
                    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                    """);
            System.out.println("Player " + Map.players.get(0).getId() + " won!\n");
            Input.closeScanner();
            return;
        }

        // March phase
        Map.players.forEach(player -> player.getVillage().getArmy().march());

        map.round++;

        doRound();
    }
}
