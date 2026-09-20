package interfaces_and_abstraction.assignment_problems;

public class ArenaBattleMain {

    static void resolveDefense(Defendable[] combatants) {
        for (Defendable combatant : combatants) {
            System.out.println(combatant.defend());
        }
    }

    public static void main(String[] args) {

        Warrior w = new Warrior("Kael");
        Trap t = new Trap("Spike Pit");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());
        System.out.println(w.getCharacterId());

        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}