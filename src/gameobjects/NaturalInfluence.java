package GameObjects;

import GameObjects.Board;

import java.util.Random;

//Simulates natural event that can kill off automata no matter what
public class NaturalInfluence {

    //Severity is number of cells affected prior to being affected by multiplier
    private int severity;
    private Random rand;
    private Board board;
    //Affected automata is the number of automata affected by existing in an affected cell
    private int affectedAutomata;

    public NaturalInfluence(Board board, int chanceOfNaturalInfluence, int maxMultiplierValue){
        rand = new Random();
        severity = 0;
        //simulates a 1 in chanceOfNaturalInfluence chance that natural influence occurs in a round
        if (rand.nextInt(chanceOfNaturalInfluence)+1 == 1) {
            //Random multiplier value to possibly increase severity of natural disaster
            int multiplier = rand.nextInt(maxMultiplierValue)+1;
            severity = rand.nextInt(board.getXLength() * board.getYLength());
            severity = severity * multiplier;
        }
        this.board = board;
    }

    public Board influence(){
        for (int i = 0; i < severity; i++){
            //at a random location on the board, if there is an automata, remove it and increase affected counter
            int xLoc = rand.nextInt(board.getXLength());
            int yLoc = rand.nextInt(board.getYLength());
            if (board.getCell(xLoc,yLoc).getAutomata() != null){
                affectedAutomata++;
            }
            board.clearCell(xLoc,yLoc);
        }
        return board;
    }

    public int getSeverity(){
        return severity;
    }

    public int getAffectedAutomata(){
        return affectedAutomata;
    }
}
