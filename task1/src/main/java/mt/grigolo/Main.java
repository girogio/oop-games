package mt.grigolo;

import mt.grigolo.game.Game;
import mt.grigolo.utils.Globals;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

       int playerCount;
       int aiCount;

       do {
           System.out.print("Enter number of players (1 or more): ");
           playerCount = s.nextInt();
       } while (playerCount < 1);


       do {
           System.out.print("Enter number of AIs (0 or more): ");
           aiCount = s.nextInt();
       } while (aiCount < 0);


        Game game = new Game(playerCount, aiCount, Globals.defaultMapWidth, Globals.defaultMapHeight);

        game.startGame();

        s.close();

    }
}