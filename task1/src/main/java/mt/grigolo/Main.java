package mt.grigolo;

import mt.grigolo.game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int playerCount;
        int aiCount;
        int w;
        int h;

        do {
            System.out.print("Enter number of players (1 or more): ");
            playerCount = s.nextInt();
        } while (playerCount < 1);


        do {
            System.out.print("Enter number of AIs (0 or more): ");
            aiCount = s.nextInt();
        } while (aiCount < 0);

        do {
            System.out.print("Enter map width: ");
            w = s.nextInt();
        } while (w < 0);

        do {
            System.out.print("Enter map height: ");
            h = s.nextInt();
        } while (h < 0);


        Game game = new Game(playerCount, aiCount, w, h);

        game.startGame();

        s.close();

    }
}