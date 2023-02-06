public class Hero extends GameActor {
    public Hero(String name, int health, int gold, int agility, int experience, int power) {
        super(name, health, gold, agility, experience, power);
    }

    public void deal(Dealer dealer, int offer) {
        try {dealer.sale(this, offer);}
        catch (NullPointerException ex) {
            System.out.println("Торговец еще не вышел на работу!");
        }
    }
}

