package mt.grigolo.players.types;

import mt.grigolo.players.Player;

import java.util.Scanner;

public class Human extends Player {


    public Human(int x, int y, int id) {
        super(x, y, id);
    }

    @Override
    public void playerInput() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(getVillage());

        System.out.println("1. Build");
        System.out.println("2. Upgrade");
        System.out.println("3. Train");
        System.out.println("4. Upgrade");
        System.out.println("5. Surrender");
        System.out.println("6. Pass");
        System.out.println("> ");


        int choice = 0;

        do {
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 6);

        switch (choice) {
            case 1 -> {
                System.out.println("1. Resource Generator");
                System.out.println("2. Troop Generator");
                int building = scanner.nextInt();
                System.out.println("1. Gem Generator");
                System.out.println("2. Gold Generator");
                System.out.println("3. Elixir Generator");
                int type = scanner.nextInt();
                build(building, type);
            }
        }

        scanner.close();

    }
}
