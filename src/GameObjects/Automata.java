package GameObjects;

import ControlObjects.Settings;

import java.util.ArrayList;
import java.util.Random;

//Class to simulate an automata
public class Automata {

    private int lifePoints;
    private int aggression;
    private int xLoc;
    private int yLoc;
    private Neighbourhood neighbourhood;
    private Settings settings;
    private char family;
    private Random rand;
    private int mutation;
    private int fightWins;

    public Automata(int xLoc, int yLoc, Neighbourhood neighbourhood, Settings settings, char family){
        rand = new Random();
        this.family = family;
        this.settings = settings;
        rand = new Random();
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.lifePoints = settings.getLifePoints();
        aggression = rand.nextInt(settings.getAggressionUpperBound())+1;
        this.neighbourhood = neighbourhood;
        this.mutation = settings.getChanceOfMutation();
        if (rand.nextInt(mutation)+1 == 1){
            this.family = this.generateFamilyLetter();
        }
    }

    public void decreaseLifePoints(){
        lifePoints--;
    }

    public int getLifePoints(){
        return lifePoints;
    }

    public void updateNeighbourhood(BoardLocation[][] board){
        neighbourhood = new Neighbourhood(xLoc, yLoc, board);
    }

    //method for automata to fight
    public void fight(){
        int fightChoice = rand.nextInt(aggression);
        if (fightChoice+1 == 1) {
            ArrayList<Automata> rivals;
            //gets automata of other family in neighbourhood
            ArrayList<Automata> rivalsFromOtherFamily = neighbourhood.getAutomataOfOtherFamily(family);
            //if no automata of other family exists they will fight own family member
            if (rivalsFromOtherFamily.size() != 0) {
                rivals = rivalsFromOtherFamily;
            }
            else {
                rivals = neighbourhood.getAutomataOfSameFamily(family);
            }
            //If the rival has more life points, this automata looses a life point and vice versa
            if (rivals.size() > 0){
                Automata rival = rivals.get(rand.nextInt(rivals.size()));
                if (rival.getLifePoints() > lifePoints) {
                    lifePoints--;
                    //rival.incrementFightWins();
                    //View.View.printFightMessage(rival,this);
                } else if (rival.getLifePoints() < lifePoints) {
                    rival.decreaseLifePoints();
                    //incrementFightWins();
                    //View.View.printFightMessage(this, rival);
                }
            }
        }
    }

    private void incrementFightWins() {
        fightWins++;
    }

    //Method to reproduce and place child within neighbourhood
    public ArrayList<Automata> reproduce(){
        ArrayList<Automata> children = new ArrayList<Automata>();
        int numberOfChildren = rand.nextInt(lifePoints);
        for (int i = 0; i < numberOfChildren; i++){
            ArrayList<Location> neighbourhoodLocations = neighbourhood.spaceInNeighboorhood();
            if (neighbourhoodLocations.size() > 0) {
                Location childLocation = neighbourhoodLocations.get(rand.nextInt(neighbourhoodLocations.size()));
                Automata child = new Automata(childLocation.getX(), childLocation.getY(), neighbourhood, settings,this.family);
                children.add(child);
            }
        }
        return children;
    }

    public int getXLoc(){
        return xLoc;
    }

    public int getYLoc(){
        return yLoc;
    }

    public char getFamily(){
        return family;
    }

    //Generates a letter for the family if the automata mutates
    public static char generateFamilyLetter(){
        Random rand = new Random();
        return (char)(rand.nextInt(26)+'a');
    }

    //returns its location as a string
    public String getLocAsString() {
        return "xLoc: " + xLoc + " yLoc: " + yLoc;
    }
}
