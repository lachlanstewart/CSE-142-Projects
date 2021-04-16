//Lachlan Stewart
//Student ID 2032212
//10/23/2020
//CSE 142 BD 
//TA: Millen Katyal
//Assessment 4 Budgeter Project
//This program calculates your net monthly income.

import java.util.*;
public class Budgeter {
    public static final int DAYS_IN_MONTH = 31;
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        printWelcomeMessage();
        double incomeTotal = getAmountTotal(console, "income");
        double expenseTotal = getAmountTotal(console, "expense");
        printTotals(incomeTotal, expenseTotal);
        printEndMessages(incomeTotal, expenseTotal);
    }

    //This outputs the message explaining the program
    public static void printWelcomeMessage(){
        System.out.println("This program asks for your monthly income and");
        System.out.println("expenses, then tells you your net monthly income.");
        System.out.println();
    }

    //This calculates the user's monthly income/expenses
    //Returns the total of the user's monthly expenses/income
    //Scanner console - Takes user input
    //String type - Determines if it calculates expense or income
    public static double getAmountTotal(Scanner console, String type){
        int expenseType = 0;
        if(type.equals("expense")){
            System.out.print("Enter 1) monthly or 2) daily expenses? ");
            expenseType = console.nextInt();
        }
        System.out.print("How many categories of " + type + "? ");
        int numCategories = console.nextInt();
        double amountTotal = 0;

        for(int i = 0; i < numCategories; i++){
            System.out.print("    Next " + type + " amount? $");
            double runningTotal = console.nextDouble();
            amountTotal += runningTotal;
        }
        if(expenseType == 2){
            amountTotal *= DAYS_IN_MONTH;
        }
        System.out.println();
        return amountTotal;
    }

    //This outputs the total income and expenses, 
    //as well as their "per day" equivalence
    //double incomeTotal - The sum of the monthly income
    //double expenseTotal - The sum of the monthly expenses
    public static void printTotals(double incomeTotal, double expenseTotal){
        double incomePerDay = round2(incomeTotal / DAYS_IN_MONTH);
        double expensePerDay = round2(expenseTotal / DAYS_IN_MONTH);
        System.out.print("Total income = $" + round2(incomeTotal));
        System.out.println(" ($" + incomePerDay + "/day)");
        System.out.print("Total expenses = $" + round2(expenseTotal));
        System.out.println(" ($" + expensePerDay + "/day)");
        System.out.println();
    }

    //This outputs an end message that varies depending on the monthly net income
    //double incomeTotal - The sum of the monthly income
    //double expenseTotal - The sum of the monthly expenses
    public static void printEndMessages(double incomeTotal, double expenseTotal){
        double netIncome = incomeTotal - expenseTotal;
        double absI = Math.abs(netIncome);
        String category;
        String verb1;
        String verb2;
        String customMsg;

        if (0 < netIncome){
            verb1 = "earned";
            verb2 = "spent";
            customMsg = "Keep skipping those drive-thrus!";
            category = "saver.";
            if (250.0 < netIncome){
                category = "big saver.";
                customMsg = "Keep it up and that Ferrari will be yours!";
            }
        } else  {
            category = "big spender.";
            verb1 = "spent";
            verb2 = "earned";
            customMsg = "Bankruptcy imminent!";
            if (-250 < netIncome){
                customMsg = "Do you want a Ferrari or not?";
                category = "spender.";
            }
        }
        System.out.print("You " + verb1);
        System.out.print(" $" + round2(absI) + " more than you ");
        System.out.println(verb2 + " this month.");
        System.out.println("You're a " + category);
        System.out.print(customMsg);
    }

    //Returns the given number, rounded to 2 decimals
    //double num - the number to round
    public static double round2(double num){
        return Math.round(num * 100.0) / 100.0;
    }
}