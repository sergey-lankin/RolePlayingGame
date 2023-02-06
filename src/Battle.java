public class Battle {

    public void startBattle(GameActor hero, GameActor monster, BattleState battleState) {
        Runnable runnable = () -> {
            int order = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("Наносится удар № " + order);
                if (order++ % 2 == 0) {
                    isFightEnded = kick(hero, monster, battleState);
                } else {
                    isFightEnded = kick(monster, hero, battleState);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException iex) {
                    iex.getMessage();
                }
            }
        };
        new Thread(runnable).start();
    }

    public boolean kick(GameActor attacker, GameActor defender, BattleState battleState) {
        int kickPower = attacker.attack();
        int defHealth = defender.getHealth() - kickPower;
        if (kickPower == 0) {
            System.out.println(String.format("%s промахнулся", attacker.getName()));
        }
        else {
            System.out.println(String.format("%s нанес удар силой в %d единиц", attacker.getName(), kickPower));
            System.out.println(String.format("%s понес потери, осталось %d единиц здоровья", defender.getName(), defHealth));
        }

        if (defHealth <= 0 && defender instanceof Hero) {
            System.out.println("Вы проиграли!");
            battleState.lossState();
            return true;
        } else if (defHealth <= 0) {
            System.out.println(String.format("Вы уничтожили %s, заработали %d опыта и %d золота", defender.getName(),
                    defender.getExperience(), defender.getGold()));
            attacker.setGold(attacker.getGold() + defender.getGold());
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            battleState.winState();
            return true;
        } else {
            defender.setHealth(defHealth);
            return false;
        }
    }
}
