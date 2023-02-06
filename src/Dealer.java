public class Dealer {
    private String name;
    private int drugs;
    private int gold;

    public Dealer(String name, int drugs, int gold) {
        this.name = name;
        this.drugs = drugs;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getDrugs() {
        return drugs;
    }

    public int getGold() {
        return gold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrugs(int drugs) {
        this.drugs = drugs;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean sale(GameActor buyer, int offer) {
        if (offer <= buyer.getGold() && offer <= this.drugs) {
            this.setDrugs(this.getDrugs() - offer);
            this.setGold(this.getGold() + offer);
            buyer.setGold(buyer.getGold() - offer);
            buyer.setHealth(buyer.getHealth() + offer);
            System.out.println(String.format("Сделка состоялась. У покупателя уровень здоровья поднялся до %d," +
                            " осталось средств %d.", buyer.getHealth(), buyer.getGold()));
            System.out.println(String.format("У продавца остало %d единиц товара.", this.getDrugs()));
            return true;
        } else if (offer > buyer.getGold() && offer <= drugs) {
            System.out.println("У героя недостаточно средств для покупки.");
            return false;
        } else if (offer <= buyer.getGold() && offer > drugs) {
            System.out.println("У продавца нет в наличии столько товара.");
            return false;
        } else {
            System.out.println("Сделка невозможна.");
            return false;
        }
    }
}
