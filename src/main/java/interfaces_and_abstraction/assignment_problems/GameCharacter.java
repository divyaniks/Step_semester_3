package interfaces_and_abstraction.assignment_problems;

public abstract class GameCharacter {

    private static int characterCount = 1000;
    private final String characterId;

    public GameCharacter() {
        characterCount++;
        characterId = "CHAR-" + characterCount;
    }

    public String getCharacterId() {
        return characterId;
    }

    public abstract String getSpecialMove();
}