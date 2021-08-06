package View;

//manages rules for input validation
public class InputSanitiser {

    //Checks if input is a y or n
    public static String sanitiseForYorN(String input){
        if (!(input.equals("Y") || input.equals("y") || input.equals("n") || input.equals("N"))){
            return null;
        }
        else if (input.equals("Y")){
            return "y";
        }
        else if (input.equals("N")){
            return "n";
        }
        return input;
    }

    //Checks if input is a number
    public static int sanitiseForNumbers(String input) throws NumberFormatException {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }

}
