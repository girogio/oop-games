package mt.grigolo.clash_of_clubs.game;

import mt.grigolo.clash_of_clubs.players.Player;
import mt.grigolo.clash_of_clubs.utils.Input;

public class Game {

    private final Map map;

    public Game(int playerCount, int aiCount, int w, int h) {
        this.map = new mt.grigolo.clash_of_clubs.game.Map(w, h, playerCount, aiCount);
    }

    public void startGame() {
        doRound();
    }

    public void doRound() {



        // Clean up dead players
        map.getPlayers().removeIf(player -> !player.isAlive());

        map.getPlayers().stream().filter(Player::isAlive).forEach(Player::doTurn);

        // Win condition
        if (map.getPlayers().size() == 1) {
            Input.clearScreen();
            System.out.println("""
                    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                    ░░░░█▀▀▀░█▀▀▀░░█▀▀░▀▀█░░█░░░░
                    ░░░░█░▀█░█░▀█░░█▀▀░▄▀░░░▀░░░░
                    ░░░░▀▀▀▀░▀▀▀▀░░▀▀▀░▀▀▀░░▀░░░░
                    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                    """);
            System.out.println("Player " + map.getPlayers().get(0).getId() + " won!\n");
            Input.closeScanner();
            return;
        }

        // March phase
        map.getPlayers().forEach(player -> player.getVillage().getArmy().march());

        map.round++;

        doRound();
    }
}
