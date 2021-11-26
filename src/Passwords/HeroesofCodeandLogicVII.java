package Passwords;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeroesofCodeandLogicVII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Hero> heroList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            //{hero name} {HP} {MP}

            String name = input[0];
            int HP = Integer.parseInt(input[1]);
            int MP = Integer.parseInt(input[2]);

            Hero hero = new Hero(name,HP,MP);
            heroList.add(hero);
        }

        String command = scanner.nextLine();
        while(!command.equals("End")){
            String[] tokens = command.split(" \\- ");
            String action = tokens[0];
            String name = tokens[1];
            switch (action){
                case "CastSpell":
                    int MPNeeded = Integer.parseInt(tokens[2]);
                    String spellName = tokens[3];
                    castSpell(heroList,name,spellName,MPNeeded);
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    setDamage(heroList,name,damage,attacker);
                    break;
                case "Recharge":
                    int rechargeAmount = Integer.parseInt(tokens[2]);
                    rechargeMP(heroList,name,rechargeAmount);
                    break;
                case "Heal":
                    int healAmount = Integer.parseInt(tokens[2]);
                    healHP(heroList,name,healAmount);
                    break;
            }
            command = scanner.nextLine();
        }

        heroList.stream()
                .sorted((p1,p2)-> {
                    int result = Integer.compare(p2.getHP(),p1.getHP());
                    if (result == 0) {
                        result = p1.getName().compareTo(p2.getName());
                    }
                    return result;
                }).forEach(el-> {
                    System.out.println(el.getName());
                    System.out.println("  HP: " + el.getHP());
                    System.out.println("  MP: " + el.getMP());
                });

    }

    private static void healHP(List<Hero> heroList, String name, int healAmount) {
        for (Hero hero:heroList) {
            if (hero.getName().equals(name)) {
                if (hero.getHP() + healAmount < 100) {
                    System.out.printf("%s healed for %d HP!%n",name,healAmount);
                    hero.setHP(hero.getHP() + healAmount);
                }else{
                    System.out.printf("%s healed for %d HP!%n",name,100 - hero.getHP());
                    hero.setHP(100);
                }
                break;
            }
        }
    }

    private static void rechargeMP(List<Hero> heroList, String name, int rechargeAmount) {
        for (Hero hero:heroList) {
            if (hero.getName().equals(name)) {
                if (hero.getMP() + rechargeAmount <= 200) {
                    System.out.printf("%s recharged for %d MP!%n",name,rechargeAmount);
                    hero.setMP(hero.getMP() + rechargeAmount);
                }else{
                    System.out.printf("%s recharged for %d MP!%n",name,200 - hero.getMP());
                    hero.setMP(200);
                }
                break;
            }
        }
    }

    private static void setDamage(List<Hero> heroList, String name, int damage, String attacker) {
        for (Hero hero:heroList) {
            if (hero.getName().equals(name)) {
                if (hero.getHP() > damage) {
                    hero.setHP(hero.getHP() - damage);
                    System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n",name,damage,attacker,hero.getHP());
                }else{
                    System.out.printf("%s has been killed by %s!%n",name,attacker);
                    heroList.remove(hero);
                }
                break;
            }
        }
    }

    private static void castSpell(List<Hero> heroList, String name, String spellName, int mpNeeded) {
        for (Hero hero:heroList) {
            if (hero.getName().equals(name)) {
                if (hero.getMP() >= mpNeeded) {
                    hero.setMP(hero.getMP() - mpNeeded);
                    System.out.printf("%s has successfully cast %s and now has %d MP!%n",name, spellName,hero.getMP());
                }else{
                    System.out.printf("%s does not have enough MP to cast %s!%n",name,spellName);
                }
                break;
            }
        }
    }


    public static class Hero{
        private String name;
        private int HP;
        private int MP;

        public Hero(String name, int HP, int MP) {
            this.name = name;
            this.HP = HP;
            this.MP = MP;
        }

        public String getName() {
            return name;
        }

        public int getHP() {
            return HP;
        }

        public void setHP(int HP) {
            this.HP = HP;
        }

        public int getMP() {
            return MP;
        }

        public void setMP(int MP) {
            this.MP = MP;
        }
    }
}
