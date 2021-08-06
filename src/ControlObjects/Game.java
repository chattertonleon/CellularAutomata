package ControlObjects;

import GameObjects.Board;
import GameObjects.NaturalInfluence;
import View.View;

import java.util.Random;

//Controls game per each round running appropriate methods
public class Game {

    //method to play a round
    public static Board playRound(Board board, Settings settings) {
        Random rand = new Random();
        //create new natural influence
        NaturalInfluence nature = new NaturalInfluence(board, settings.getChanceOfNaturalInfluence(), settings.getMaxMultiplierValue());
        board = nature.influence();
        //Check if natural influence should occur
        if (nature.getSeverity() > 0){
            View.printNatureInfo(nature.getAffectedAutomata());
        }
        if (rand.nextInt(settings.getChanceOfRandomAutomataGeneration())+1 == 1) {
            board.placeAutomataRandomly();
        }
        board.updateAutomata();
        board.getAllAutomata();
        return board;
    }
}
