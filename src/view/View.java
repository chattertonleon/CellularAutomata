package view;

import controlobjects.Settings;
import gameobjects.Automata;
import gameobjects.Board;

//Manages printing of items in the console
public class View {

    //Prints the entire board
    public static void printBoard(Board board) {
        for (int y = 0; y < board.getYLength(); y++){
            for (int x = 0; x < board.getXLength(); x++){
                System.out.print(board.getCell(x,y).toString());
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public static void printRoundInformation(int round, Board board){
        System.out.println("Current round: " + round);
        System.out.println(board.getLogger().toString());

    }

    public static void printNatureInfo(int affectedAutomata) {
        System.out.println("There was a natural disaster killing "+affectedAutomata+" automata");
    }

    public static void printGameSettings(Settings settings){
        System.out.println("\nThe settings you have chosen are as follows:" + settings.toString());
    }

    public static void printFightMessage(Automata winner, Automata loser) {
        if (loser.getLifePoints() > 0) {
            System.out.println("GameObjects.Automata at " + winner.getLocAsString() + " beat automata at " + loser.getLocAsString() + " \n" +
                    "leaving them on " + loser.getLifePoints() + " life points");
        } else {
            System.out.println("GameObjects.Automata at " + winner.getLocAsString() + " killed automata at " + loser.getLocAsString());
        }
    }
}
