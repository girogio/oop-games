package mt.grigolo.players.types;

import mt.grigolo.exceptions.InsufficientResourceException;
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

        int choice;

        do {
            System.out.print("> ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    int building, type;
                    boolean purchaseSuccesful = false;

                    do { // until purchase successful

                        // do until valid
                        do {
                            System.out.println("1. Resource Generator");
                            System.out.println("2. Troop Generator");
                            System.out.println("3. Back");
                            System.out.print("> ");
                            building = scanner.nextInt();
                        } while (building < 1 || building > 3);

                        if (building == 3) {
                            break;
                        }

                        //do until valid
                        do {
                            System.out.println("1. Gem Generator");
                            System.out.println("2. Gold Generator");
                            System.out.println("3. Elixir Generator");
                            System.out.println("4. Back");
                            System.out.print("> ");
                            type = scanner.nextInt();

                        } while (type < 1 || type > 4);

                        if (type != 4) {
                            try {
                                purchaseSuccesful = build(building, type);
                            } catch (InsufficientResourceException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } while (!purchaseSuccesful);
                }

                case 6 -> {
                    return;
                }
            }

        } while (choice < 1 || choice > 6);
    }
}
