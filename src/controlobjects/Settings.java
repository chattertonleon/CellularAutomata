package ControlObjects;

//Class to define settings for game, can be user defined or default, hence two constructors
public class Settings {

    private int boardWidth;
    private int boardHeight;
    private int lifePoints;
    private int aggressionUpperBound;
    private int chanceOfNaturalInfluence;
    private int chanceOfRandomAutomataGeneration;
    private int chanceOfMutation;
    private int maxMultiplierValue;

    public Settings(){
        boardWidth = 40;
        boardHeight = 10;
        lifePoints = 3;
        aggressionUpperBound = 2;
        chanceOfNaturalInfluence = 10;
        chanceOfRandomAutomataGeneration = 100;
        chanceOfMutation = 5;
        maxMultiplierValue = 3;
    }

    public Settings(int boardWidth, int boardHeight, int lifePoints, int aggressionUpperBound, int chanceOfNaturalInfluence, int chanceOfRandomAutomataGeneration, int chanceOfMutation, int maxMultiplierValue){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.lifePoints = lifePoints;
        this.aggressionUpperBound = aggressionUpperBound;
        this.chanceOfNaturalInfluence = chanceOfNaturalInfluence;
        this.chanceOfRandomAutomataGeneration = chanceOfRandomAutomataGeneration;
        this.chanceOfMutation = chanceOfMutation;
        this.maxMultiplierValue = maxMultiplierValue;
    }

    public int getAggressionUpperBound() {
        return aggressionUpperBound;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getChanceOfNaturalInfluence() {
        return chanceOfNaturalInfluence;
    }

    public int getChanceOfRandomAutomataGeneration() {
        return chanceOfRandomAutomataGeneration;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getChanceOfMutation() {
        return chanceOfMutation;
    }

    public int getMaxMultiplierValue() {
        return maxMultiplierValue;
    }

    public String toString(){
        return "\nBoard Width: " + boardWidth +
                "\nBoard Height: " + boardHeight +
                "\nLife Points: " + lifePoints +
                "\nChance of aggression: 1/" + aggressionUpperBound +
                "\nChance of natural influence: 1/" + chanceOfNaturalInfluence +
                "\nMaximum natural disaster multiplier value:" + maxMultiplierValue +
                "\nChance of random automata generation: 1/" + chanceOfRandomAutomataGeneration +
                "\nChance of mutation: 1/" + chanceOfMutation;
    }
}
