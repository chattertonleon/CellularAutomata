package GameObjects;

import ControlObjects.Logger;
import ControlObjects.Settings;
import java.util.ArrayList;
import java.util.Random;

//Class to control board functions and simulate 2D plane that the automata live on

public class Board {

    private BoardLocation[][] board;
    private Logger logger;
    private ArrayList<Automata> automataInBoard;
    private final int xLength;
    private final int yLength;
    private Settings settings;

    public Board(Settings settings){
        this.settings = settings;
        logger = new Logger();
        xLength = settings.getBoardWidth();
        yLength = settings.getBoardHeight();
        board = new BoardLocation[xLength][yLength];
        automataInBoard = new ArrayList<Automata>();
        for (int y = 0; y < board[0].length; y++){
            for (int x = 0; x < board.length; x++){
                board[x][y] = new BoardLocation(x,y,xLength,yLength);
            }
        }
    }

    public BoardLocation getCell(int x, int y) {
        return board[x][y];
    }

    public int getYLength() {
        return board[0].length;
    }

    public int getXLength() {
        return board.length;
    }

    //Places automata randomly on board in any place
    public void placeAutomataRandomly() {
        Random rand = new Random();
        boolean isPlaced = false;
        while (!isPlaced) {
            int xPos = rand.nextInt(xLength);
            int yPos = rand.nextInt(yLength);
            if (board[xPos][yPos].freeToPlace()) {
                Automata newAutomata = new Automata(xPos, yPos, new Neighbourhood(xPos, yPos, board), settings, Automata.generateFamilyLetter());
                board[xPos][yPos].placeAutomata(newAutomata);
                automataInBoard.add(newAutomata);
                isPlaced = true;
            }
        }
    }

    //Updates all automata on the board and increments logging as required
    public void updateAutomata() {
        logger.resetAutomataDeadInRound();
        logger.resetAutomataCreatedInRound();
        for (Automata auto: automataInBoard) {
            if (auto.getLifePoints() > 0) {
                ArrayList<Automata> children = auto.reproduce();
                for (Automata automata : children) {
                    board[automata.getXLoc()][automata.getYLoc()].placeAutomata(automata);
                    logger.incrementAutomataCreated();
                    logger.incrementAutomataCreatedInRound();
                }
                auto.updateNeighbourhood(board);
                auto.fight();
                auto.decreaseLifePoints();
            }
            else {
                board[auto.getXLoc()][auto.getYLoc()].removeAutomata();
                logger.incrementAutomataDeadInRound();
                logger.incrementAutomatedDead();
            }
        }
    }

    public void getAllAutomata(){
        ArrayList<Automata> automataInBoardNew = new ArrayList<Automata>();
        for (int y = 0; y < board[0].length; y++){
            for (int x = 0; x < board.length; x++){
                if (board[x][y].getAutomata() != null){
                    automataInBoardNew.add(board[x][y].getAutomata());
                }
            }
        }
        automataInBoard = automataInBoardNew;
    }

    public boolean isFull() {
        for (int y = 0; y < board[0].length; y++){
            for (int x = 0; x < board.length; x++){
                if (board[x][y].getAutomata() == null){
                    return false;
                }
            }
        }
        return true;
    }

    public void clearCell(int xLoc, int yLoc) {
        if (board[xLoc][yLoc] != null) {
            automataInBoard.remove(board[xLoc][yLoc].getAutomata());
            board[xLoc][yLoc].removeAutomata();
        }
    }

    public boolean isEmpty() {
        for (int y = 0; y < board[0].length; y++){
            for (int x = 0; x < board.length; x++){
                if (board[x][y].getAutomata() != null){
                    return false;
                }
            }
        }
        return true;
    }

    public Logger getLogger() {
        return logger;
    }
}
