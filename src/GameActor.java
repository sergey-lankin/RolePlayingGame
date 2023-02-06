import java.util.Random;

abstract class GameActor implements Attacking {

    private String name;
    private int health;
    private int gold;
    private int agility;
    private int experience;
    private int power;

    public GameActor(String name, int health, int gold, int agility, int experience, int power) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.agility = agility;
        this.experience = experience;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getAgility() {
        return agility;
    }

    public int getExperience() {
        return experience;
    }

    public int getPower() {
        return power;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setPower(int power) {
        this.power = power;
    }
    @Override
    public int attack() {
        int setKick = (int)(Math.random());
        if (this.power * 3 > setKick * 100 && this.power < setKick * 60) {
            return power;
        } else if (this.power > setKick * 60) {
            return power * 2;
        } else return 0;
    }

    public boolean checkState() {
        if (health > 0) {
            return true;
        }
        else return false;
    }
}
