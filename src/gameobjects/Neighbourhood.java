package gameobjects;

import java.util.ArrayList;

//Simulates a neighbourhood, adjacent cells, around an automata, considered the range they can "see"
public class Neighbourhood {

    private BoardLocation[][] neighbourhood;
    //Global x and y pos is the same as the location of the automata
    private int globalXPos;
    private int globalYPos;

    public Neighbourhood(int xPos, int yPos, BoardLocation[][] board) {
        this.globalXPos = xPos;
        this.globalYPos = yPos;
        neighbourhood = new BoardLocation[3][3];
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++) {
                /* equation of n2-1+n transposes co-ordinates from game board of of size x*y to a neighbourhood size
                   3*3 where n2 is the global co-ordinate on the game board and n is the local co-ordinate on the
                   neighbourhood*/
                if (xPos-1+x > -1 && xPos-1+x < board.length && yPos-1+y > -1 && yPos-1+y < board[0].length) {
                    neighbourhood[x][y] = board[xPos - 1 + x][yPos - 1 + y];
                }
            }
        }
    }

    //Converts local co-ordinate in neighbourhood to global co-ordinate on game board
    public Location localToGlobal(Location local){
        return new Location(globalXPos+local.getX()-1,globalYPos+local.getY()-1);
    }

    public BoardLocation[][] getNeighbourhood(){
        return neighbourhood;
    }

    //Scans to find a space in a neighbourhood
    public ArrayList<Location> spaceInNeighboorhood() {
        ArrayList<Location> freeLocations = new ArrayList<Location>();
        for (int y = 0; y < neighbourhood[0].length; y++){
            for (int x = 0; x < neighbourhood.length; x++){
                if (neighbourhood[x][y] != null && neighbourhood[x][y].freeToPlace()){
                    freeLocations.add(this.localToGlobal(new Location(x,y)));
                }
            }
        }
        return freeLocations;
    }

    //Gets all automata in neighbourhood
    public ArrayList<Automata> getAutomata() {
        ArrayList<Automata> automata = new ArrayList<Automata>();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (neighbourhood[x][y] != null && neighbourhood[x][y].getAutomata() != null) {
                    automata.add(neighbourhood[x][y].getAutomata());
                }
            }
        }
        return automata;
    }

    //Cycles through and returns an automata of a different family
    public ArrayList<Automata> getAutomataOfOtherFamily(char family) {
        ArrayList<Automata> automata = new ArrayList<Automata>();
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++){
                if (neighbourhood[x][y] != null && neighbourhood[x][y].getAutomata() != null && neighbourhood[x][y].getAutomata().getFamily() != family){
                    automata.add(neighbourhood[x][y].getAutomata());
                }
            }
        }
        return automata;
    }

    //Cycles through and returns an automata of the same family
    public ArrayList<Automata> getAutomataOfSameFamily(char family) {
        ArrayList<Automata> automata = new ArrayList<Automata>();
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++){
                if (neighbourhood[x][y] != null && neighbourhood[x][y].getAutomata() != null && neighbourhood[x][y].getAutomata().getFamily() == family){
                    automata.add(neighbourhood[x][y].getAutomata());
                }
            }
        }
        return automata;
    }
}
