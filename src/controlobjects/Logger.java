package controlobjects;

//Responsible for logging key facts within the game
public class Logger {

    private int automataCreated;
    private int automataDead;
    private int automataDeadInRound;
    private int automataCreatedInRound;

    public Logger(){
        automataCreated = 0;
        automataDead = 0;
        automataDeadInRound = 0;
        automataCreatedInRound = 0;
    }

    public void incrementAutomataCreated(){
        automataCreated++;
    }

    public void incrementAutomatedDead(){
        automataDead++;
    }

    public void incrementAutomataCreatedInRound(){
        automataCreatedInRound++;
    }

    public void incrementAutomataDeadInRound(){
        automataDeadInRound++;
    }

    public void resetAutomataCreatedInRound(){
        automataCreatedInRound = 0;
    }

    public void resetAutomataDeadInRound(){
        automataDeadInRound = 0;
    }

    public String toString(){
        return
                "Total Automata created:" + automataCreated + "\n" +
                        "Total Automata dead: " + automataDead + "\n" +
                        "Automata dead in round: " + automataDeadInRound + "\n" +
                        "Automata created in round: " + automataCreatedInRound + "\n";
    }
}
