package mt.grigolo.clash_of_clubs.game;

import mt.grigolo.clash_of_clubs.players.Player;
import mt.grigolo.clash_of_clubs.players.types.AI;
import mt.grigolo.clash_of_clubs.players.types.Human;
import mt.grigolo.clash_of_clubs.utils.Globals;

import java.util.ArrayList;

public class Map {

    private final int width;
    private final int height;
    public int round = 1;

    public static final ArrayList<Player> players = new ArrayList<>();


    public Map(int width, int height, int playerCount, int aiCount) {
        this.width = width;
        this.height = height;

        int x, y;

        for (int i = 0; i < playerCount; i++) {
            do {
                x = (int) (Math.random() * width);
                y = (int) (Math.random() * height);
            } while (isOccupied(x, y));
            players.add(new Human(x, y, i + 1));
        }

        for (int i = 0; i < aiCount; i++) {
            do {
                x = (int) (Math.random() * width);
                y = (int) (Math.random() * height);
            } while (isOccupied(x, y));
            players.add(new AI(x, y, playerCount + i + 1, Globals.defaultAIactionsPerTurn));
        }

        for (Player player : players) {
            if (player instanceof Human) {
                ((Human) player).setMap(this);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("  ");
        for (int i = 0; i < width; i++) {
            s.append(" ").append(i).append(" ");
        }
        s.append("\n");
        for (int i = 0; i < height; i++) {
            s.append(i).append(" ");
            for (int j = 0; j < width; j++) {
                boolean found = false;
                for (Player player : players) {
                    if (player.getVillage().pos.getX() == j && player.getVillage().pos.getY() == i && player.isAlive()) {
                        if (player instanceof Human) {
                            if (!player.getVillage().getArmy().isInHomeVillage()) {
                                s.append(" M ");
                            } else {
                                s.append(" H ");
                            }
                        } else if (player instanceof AI) {
                            if (!player.getVillage().getArmy().isInHomeVillage()) {
                                s.append(" M ");
                            } else {
                                s.append(" A ");
                            }
                        }
                        found = true;
                    }
                }
                if (!found)
                    s.append("[ ]");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    protected boolean isOccupied(int x, int y) {
        for (Player player : players) {
            if (player.getVillage().pos.getX() == x && player.getVillage().pos.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
