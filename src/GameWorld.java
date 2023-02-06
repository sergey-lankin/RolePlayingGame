import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class GameWorld {
    private static BufferedReader consoleTyping;
    private static GameActor hero = null;
    private static Dealer dealer = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        InputStreamReader stream = new InputStreamReader(System.in);
        consoleTyping = new BufferedReader(stream);
        battle = new Battle();
        System.out.println("Введите имя героя");
        try {
            action(consoleTyping.readLine());
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public static void action(String string) throws IOException {
        if (hero == null) {
            hero = new Hero(string, 100, 30, 20, 0, 5);
            int healthCurr = hero.getHealth();
            System.out.println("Добро пожаловать в Игру.");
            printMenu();
        }

        switch (string) {
            case "1": {
                if (dealer == null) {
                    dealer = new Dealer("doctor", 100, 0);
                }
                try {
                    System.out.println("На какую сумму Вы хотите приобрести товар?");
                    int offer = Integer.parseInt(consoleTyping.readLine());
                    dealer.sale(hero, offer);
                    printMenu();
                } catch (IOException e) {
                    e.getMessage();
                }
                break;
            }
            case "2": {
                fight();
            }
                break;
            case "3":
            {System.exit(1);}
                break;
            case "да": {
                action("2");
            }
            case "нет": {
                printMenu();
                action(consoleTyping.readLine());
            }
        }
        action(consoleTyping.readLine());
    }
    public static void printMenu() {
        System.out.println("Выберите тип похода:");
        System.out.println("Нажмите 1, чтобы пойти к Торговцу");
        System.out.println("Нажмите 2, чтобы пойти в Темный лес");
        System.out.println("Нажмите 3, чтобы выйти из Игры");
    }

    public static void fight() {
        battle.startBattle(hero, createMonster(), new BattleState() {
            @Override
            public void winState() {
                System.out.println(String.format("%s победил. Поздравляем!!!", hero.getName()));
                System.out.println("Наберите да для продолжения, нет для вызова меню");
                try {
                    action(consoleTyping.readLine());
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }

            @Override
            public void lossState() {
                System.out.println("К сожалению, герой повержен");
            }
        });
    }
    public static GameActor createMonster() {
        Random chooseMonster = new Random();
        if ((chooseMonster.nextInt(10)) > 5) {
            System.out.println("Goblin");
            return new Goblin("Goblin", 10, 20, 10, 5, 3);

        } else {
            System.out.println("Skeleton");
            return new Skeleton(" Skeleton", 15, 30, 15, 6, 4);
        }
    }
}
