package ControlObjects;

import GameObjects.Board;
import View.View;
import View.InputController;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int roundNumber = 1;
        InputController inputController = new InputController();
        Settings settings = inputController.askForDefaultSettings();
        if (settings == null){
            settings = inputController.setUpSettings();
        }
        View.printGameSettings(settings);
        Board board = new Board(settings);
        Game game = new Game();
        View.printBoard(board);
        board.placeAutomataRandomly();
        while (!board.isFull() && !board.isEmpty()) {
            inputController.promptForPlay();
            View.printBoard(board);
            View.printRoundInformation(roundNumber,board);
            board = game.playRound(board,settings);
            roundNumber++;
        }
        View.printBoard(board);
        System.out.println("Number of rounds taken:" + roundNumber);
    }

}
