//Lachlan Stewart
//11/13/2020
//CSE 142 BD 
//Assessment 6 Yaz Interpreter
//This program allows the user to interpret and view programs written in YazLang.

import java.util.*;
import java.io.*;

public class YazInterpreter {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        //Welcome Message
        welcomeUser();
        //Prompt Menu
        String promptEntry = promptUser(console);
        while(!promptEntry.equalsIgnoreCase("q")){
            
            //If I, interpret YazLang program
            if(promptEntry.equalsIgnoreCase("i")){
                Scanner fileScan = new Scanner(determineFile(console));
                //Prompt for output file name
                System.out.print("Output file name: ");
                PrintStream output = new PrintStream(new File(console.nextLine()));
                interpretYazFile(fileScan, output);            
            }

            //If V, view output
            else if(promptEntry.equalsIgnoreCase("v")){
                Scanner fileScan = new Scanner(determineFile(console));
                viewYazFile(fileScan);
            }
            promptEntry = promptUser(console);
        }
    }

    //This takes user input to select an input file and checks that it exists
    //Returns the file
    //Scanner console - The scanner object that accepts user input
    public static File determineFile(Scanner console){
        System.out.print("Input file name: ");
        File selectedFile = new File(console.nextLine());
        while(!selectedFile.exists()){
            System.out.print("File not found. Try again: ");
            selectedFile = new File(console.nextLine());
        }
        return selectedFile;
    }


    //This interprets the chosen input file by executing the YazLang commands 
    //Scanner fileScan - The scanner object that scans each line of the file
    //PrintStream output - The PrintStream that outputs to the chosen output file
    public static void interpretYazFile(Scanner fileScan, PrintStream output){
        while(fileScan.hasNextLine()){
            String fileLine = fileScan.nextLine();
            Scanner lineScan = new Scanner(fileLine);
            
            //Yaz convert command
            if (fileLine.startsWith("CONVERT")){
                yazConvert(lineScan, output);

            //Yaz range command
            } else if (fileLine.startsWith("RANGE")){
                yazRange(lineScan, output);

            //Yaz repeat command    
            } else if (fileLine.startsWith("REPEAT")){
                yazRepeat(lineScan, output);
            }    
        }
        System.out.println("YazLang interpreted and output to a file!");
        System.out.println();
    }

    public static void yazRepeat(Scanner lineScan, PrintStream output){
        String command = lineScan.next();
        while(lineScan.hasNext()){
        //Grab string argument (gets repeated)
        String arg = lineScan.next();
        //Grab number of repetitions
            int rep = lineScan.nextInt();
            //Compile repeated string
            arg = arg.replace("_"," ");
            arg = arg.substring(1,arg.length() - 1);
            for(int i = 0; i < rep; i++){
                output.print(arg);                    
            }
        }
        output.println();
    }

    public static void yazConvert(Scanner lineScan, PrintStream output){
        String command = lineScan.next();
        //Grab temperature arg
        int temp = lineScan.nextInt();
        //Grab temperature type arg (C or F)
        String tempType = lineScan.next();
        //Convert, depending on tempType 
        if(tempType.equals("C")){
            temp = (int)(temp * 1.8 + 32);
            tempType = tempType.replace("C", "F");
        } else if (tempType.equals("F")){
            temp = (int)((temp - 32) / 1.8);
            tempType = tempType.replace("F","C");
        }
        //Print the result
        output.println(temp + tempType);
    }

    public static void yazRange(Scanner lineScan, PrintStream output){
        String command = lineScan.next();
        //grab first int
        int start = lineScan.nextInt();
        //grab second int
        int end = lineScan.nextInt();
        //grab third int
        int increment = lineScan.nextInt();
        //Calculation
        while(start < end){
            output.print(start + " ");
            start += increment;
        }
        output.println();
    }

    //This allows the user to view the contents of a file
    //Scanner fileScan - the scanner object that scans the chosen file
    public static void viewYazFile(Scanner fileScan){
        System.out.println();
        while(fileScan.hasNextLine()){
            String fileLine = fileScan.nextLine();
            System.out.println(fileLine);
        }
        System.out.println();
    }

    //This prompts the user for the action they'd like to run
    //Returns the string that the user enters
    //Scanner console - The scanner object that takes user input
    public static String promptUser(Scanner console){
        System.out.print("(I)nterpret YazLang program, (V)iew output, (Q)uit? ");
        String promptEntry = console.nextLine();
        return promptEntry;
    }

    //This prints the welcome message at the beginning of the program
    public static void welcomeUser(){
        System.out.println("Welcome to YazInterpreter!");
        System.out.println("You may interpret a YazLang program and output");
        System.out.println("the results to a file or view a previously");
        System.out.println("interpreted YazLang program.");
        System.out.println();
    }
}
