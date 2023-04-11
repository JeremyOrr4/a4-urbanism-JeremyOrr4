package ca.mcmaster.cas.se2aa4.a3.island.City;

public enum STATUS {
    
    PASSED ('X'),
    FAILED ('F'),
    ERRORED('!');
    
    private final char value;

    STATUS(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
