package gameobjects;

//Simulates location in the board
public class BoardLocation {

    private Automata automata;
    private int x;
    private int y;
    private int boardXLength;
    private int boardYLength;

    public BoardLocation(int x, int y, int boardXLength, int boardYLength){
        automata = null;
        this.x = x;
        this.y = y;
        this.boardXLength = boardXLength;
        this.boardYLength = boardYLength;
    }

    public Automata getAutomata() {
        return automata;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Works out if a location is free
    public boolean freeToPlace() {
        if (x < 0 || x > boardXLength-1 || y < 0 || y > boardYLength-1 || automata != null) {
            return false;
        }
        return true;
    }

    public void placeAutomata(Automata newAutomata) {
        automata = newAutomata;
    }

    public String toString(){
        if (automata == null){
            return ".";
        }
        return Character.toString(automata.getFamily());
    }

    public void removeAutomata() {
        automata = null;
    }
}
