//Lachlan Stewart
//11/22/2020
//CSE 142 BD 
//Assessment 7 Personality Test
//This program converts Keirnsey test answers into personality types 

import java.util.*;
import java.io.*;

public class Personality {
    public static final int DIMENSIONS = 4;
    public static void main(String[] args) throws FileNotFoundException {
        //Intro message
        describeProgram();
        //Get input file
        Scanner console = new Scanner(System.in);
        System.out.print("input file name? ");
        File input = new File(console.nextLine());
        //Get Output file (& Create PrintStream)
        System.out.print("output file name? ");
        PrintStream output = new PrintStream(new File(console.nextLine()));

        //Process each pair of input file lines --> each respondants data
        Scanner inputScan = new Scanner(input);
        while(inputScan.hasNextLine()){
            int[] countsA = new int[DIMENSIONS];
            int[] countsB = new int[DIMENSIONS];
            //Get the name
            String name = inputScan.nextLine();
            //Get the counts of A and B answers per dimension
            String answers = inputScan.nextLine();
            getCounts(countsA, countsB, answers);
            //Convert totals to B percents 
            int[] percentsB = new int[DIMENSIONS];
            getPercent(countsA, countsB, percentsB);
            //Determine personality type
            String personality = getPersonality(percentsB);
            //Print the results (To output file)
            printResults(output, name, percentsB, personality);
        }
        System.out.println("Done!");
    }

    //Determines the personality type from the B percentages
    //Returns a 4 letter string that labels the personality type
    //int[] percentsB - the B percentages
    public static String getPersonality(int[] percentsB){
        //Format : [E/I, S/N, T/F, J/P]
        String[] underHalf = {"E", "S", "T", "J"};
        String[] overHalf = {"I", "N", "F", "P"};
        
        String personality = "";
        for (int i = 0; i < DIMENSIONS; i++){
            if(percentsB[i] > 50){
                personality += overHalf[i];
            } else if (percentsB[i] < 50){
                personality += underHalf[i];
            } else {
                personality += "X";
            }
        }
        return personality;
    }

    //Prints the calculated results to the chosen output file
    //PrintStream output - the output file to which the results are printed
    //String name - The name of the respondant whose results are being printed
    //int[] pctB - The array of B percentages for the given respondant
    //String pers - The determined personality type
    public static void printResults(PrintStream output, String name, int[] pctB, String pers){
        output.print(name + ": ");
        output.print(Arrays.toString(pctB));
        output.println(" = " + pers);
    }

    //Calculates the percentages of B answers from the number of A and B answers 
    //int[] countsA - The A answers
    //int[] countsB - The B answers
    //int[] percentsB - The percentages of B answers
    public static void getPercent(int[] countsA, int[] countsB, int[] percentsB){
        for(int i = 0; i < DIMENSIONS; i++){
            double percentage = 100 * ((double) countsB[i] / (countsB[i] + countsA[i]));
            percentsB[i] = (int)Math.round(percentage);
        }
    }

    //Prints the program description to the console
    public static void describeProgram(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    //Counts the A and B answers for each personality aspect, ignoring the blank answers
    //int[] countsA - The counts of all the A answers
    //int[] countsB - The counts of all the B answers
    //String answers - The sequence of answers to count
    public static void getCounts( int[] countsA, int[] countsB, String answers){
        //Format : [E/I, S/N, T/F, J/P]
        answers = answers.toLowerCase();
        for (int i = 0; i < 70; i++){
            if (answers.charAt(i) == 'a'){
                addCount(countsA, i);
            } else if (answers.charAt(i) == 'b'){
                addCount(countsB, i);
            }
        }
    }

    //Counts the A or B answer to the corresponding array
    //int[] countArray - The array of answer totals (A or B)
    //int i - The answer's index in the individual's test data 
    public static void addCount(int[] countArray, int i){
        if (i % 7 == 0){
            countArray[0]++;
        } else if (i % 7 == 1 || i % 7 == 2){
            countArray[1]++;
        } else if (i % 7 == 3 || i % 7 == 4){
            countArray[2]++;
        } else if (i % 7 == 5 || i % 7 == 6){
            countArray[3]++;
        } 
    }
}
