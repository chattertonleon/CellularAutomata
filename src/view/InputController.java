package view;

import controlobjects.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Manages inputs and processing them for the game to work as required
public class InputController {

    private BufferedReader reader;

    public InputController(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Settings askForDefaultSettings() throws IOException {
        System.out.println("Would you like to use your own settings or the default settings? These are:");
        Settings defaultSettings = new Settings();
        System.out.println(defaultSettings);
        System.out.print("Press Y to use defualt settings, press N to use your own settings:");
        String decision = reader.readLine();
        decision = InputSanitiser.sanitiseForYorN(decision);
        while(decision == null){
            System.out.print("Invalid input, press Y to use defualt settings, press N to use your own settings:");
            decision = reader.readLine();
            decision = InputSanitiser.sanitiseForYorN(decision);
        }
        if (decision.equals("n")) {
            return null;
        }
        else{
            return defaultSettings;
        }
    }

    public Settings setUpSettings() throws IOException {
        Settings settings = null;
        try{
            System.out.print("Enter the board width: ");
            int boardWidth = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter the board height: ");
            int boardHeight = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter life points for each automata: ");
            int automataLifePoints = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter the aggression factor, the higher the number the less likely a automata will be born \naggressive: ");
            int automataAggressionUpperBound = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter the chance of natural disaster, where there is a 1 in n chance of a natural disaster \nand you are entering n: ");
            int naturalInfluence = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter chance of an automata being randomly placed on the board, where there is a 1 in n \nchance of this occurring and you are entering n: ");
            int randomAutomataPlacement = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter chance of mutation where there is a 1 in n chance of this occurring and you are \nentering n: ");
            int mutationChance = InputSanitiser.sanitiseForNumbers(reader.readLine());
            System.out.print("Enter the maximum multiplier for natural disaster, the higher, the greater the chance of \n" +
                    "a more powerful natural disaster");
            int maxMultiplierValue = InputSanitiser.sanitiseForNumbers(reader.readLine());
            settings = new Settings(boardWidth,boardHeight,automataLifePoints, automataAggressionUpperBound, naturalInfluence, randomAutomataPlacement, mutationChance, maxMultiplierValue);
        } catch (NumberFormatException e){
            System.out.println("The settings you have entered are of an invalid format, please try again");
            setUpSettings();
        }
        return settings;
    }

    public void promptForPlay() throws IOException {
        System.out.println("Press enter for next play or hold for continuous play");
        System.in.read();
    }
}
