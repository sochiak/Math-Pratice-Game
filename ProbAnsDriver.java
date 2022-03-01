/**
 * ---------------------------------------------------------------------------
 * File name: ProbAnsDriver.java
 * Project name: Semester Project
 * ---------------------------------------------------------------------------
 * Creator's name and email: Sophia Herrell, herrells@etsu.edu
 * Course:  CSCI 1250
 * Creation Date: November 15, 2021
 * ---------------------------------------------------------------------------
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

/**
 * Allows the user to practice their addition, subtraction, multiplication,
 * and division skills. 
 * 
 * Date created: November 15, 2021
 * 
 * @author Sophia Herrell
 */

public class ProbAnsDriver 
{
    static ArrayList<ProblemAnswer> questionBank = new ArrayList<ProblemAnswer>();
    static Scanner kb = new Scanner(System.in);
    private static ArrayList<String> questionBankList;
    /**
     * Main method which collects the user's specifications and then calls
     * the appropriate methods based on the user's preferences. 
     * 
     * Date created: November 15, 2021
     * 
     * @param args
     */
    public static void main(String[] args) 
    {
        //declare variables
        int probNum = 0; //the number of problems
        int rangeMax = 0; //the maximum value of the numbers used in the problems
        int rangeMin = 0; //the minimum value of the numbers used in the problems
        int menuChoice = 10;
        boolean validChoice = false;
        questionBankList = new ArrayList<>();

        //create array of encouraging messages
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Good job!"); 
        messages.add("Amazing!"); 
        messages.add("Great work!"); 
        messages.add("Good progress!"); 
        messages.add("Way to go!"); 
        messages.add("Keep it up!");

        do
        {
            //clears lists
            questionBank.clear();

            menu();
            menuChoice = getMenuOption();

            if((menuChoice != 0) && (menuChoice != 5)) //does not collect additional info if user chooses to terminate program or upload their own file
            {
                validChoice = false;
                while(!validChoice)
                {
                    try 
                    {
                        System.out.print("Enter the number of problems you would like to practice: ");
                        probNum = kb.nextInt();  
                        if(probNum > 0)
                        {
                            validChoice = true;
                        }
                        else
                        {
                            System.out.println("Error: integer must be greater than 0");
                            validChoice = false;
                        }
                    } 
                    catch(Exception e) //ensures input is an integer
                    {
                        System.out.println("Error: please input an integer");
                        kb.next(); //clears scanner to prevent infinite loop
                        validChoice = false;
                    } //end catch
                }//end while
                validChoice = false;
                while(!validChoice)
                {
                    try 
                    {
                        System.out.print("Enter the minimum of the range: ");
                        rangeMin = kb.nextInt();
                        if(rangeMin >= 0)
                        {
                            validChoice = true;
                        } //end if
                        else
                        {
                            System.out.println("Error: please enter a positive integer");
                            validChoice = false; 
                        } //end else
                    } 
                    catch(Exception e) //ensures input is an integer
                    {
                        System.out.println("Error: please input an integer");
                        validChoice = false;
                        kb.next(); //clears scanner to prevent infinite loop
                    } //end catch
                }//end while
                validChoice = false;
                while(!validChoice)
                {
                    try 
                    {
                        System.out.print("Enter the maximum of the range: ");
                        rangeMax = kb.nextInt();
                        if(rangeMax > rangeMin)
                        {
                            validChoice = true;
                        }
                        else
                        {
                            System.out.println("Error: the maximum must be greater than the minimum");
                            validChoice = false;
                        }  
                    } 
                    catch(Exception e) //ensures input is an integer
                    {
                        System.out.println("Error: please input an integer");
                        kb.next(); //clears scanner to prevent infinite loop
                        validChoice = false;
                    } //end catch
                }//end while
                kb.nextLine(); //clear buffer
                routeOptions(messages, menuChoice, probNum, rangeMax, rangeMin);
                interact(menuChoice, probNum, messages);
            }//end if
            else if(menuChoice == 5)
            {
                routeOptions(messages, menuChoice, probNum, rangeMax, rangeMin);
            }
        }while(menuChoice != 0);

    } //end main
    /**
     * Prints the menu.
     * 
     * Date created: November 15, 2021
     */
    public static void menu()
    { 
        System.out.println("\nMenu:");
        System.out.println("1. Generate Addition \n2. Generate Subtraction\n3. Generate Multiplication\n4. Generate Division\n5. Upload Problems to Practice\n0. Exit");
        /**
        * 
        * ------Menu------
        * 1. Addition
        * 2. Subtraction
        * 3. Multiplication
        * 4. Division
        * 0. Exit
        */
    }
    /**
     * Gets the user's menu selection.
     * 
     * Date created: November 15, 2021
     * 
     * @return
     */
    public static int getMenuOption()
    {
        int menuChoice = 1;
        boolean validChoice = false;

        //executes as long as the input is invalid
        while(!validChoice)
        {
            try
            {
                System.out.print("\nSelect menu option: ");
                menuChoice = kb.nextInt();
                kb.nextLine(); //clear buffer

                //ensures that menu choice is either 0, 1, 2, 3, 4, or 5
                if(menuChoice < 6 && menuChoice >= 0)
                {
                    validChoice = true;
                }
                else
                {
                    System.out.println("Error: selected index is not included in the menu\n");
                    validChoice = false;
                }
            } //end try
            catch(Exception e) //ensures input is an integer
            {
                System.out.println("Error: please enter the numerical index of your menu choice");
                kb.next(); //clears scanner to prevent infinite loop
            } //end catch
        }//end while
        return menuChoice;
    } //end getMenuOption()
    /**
     * Runs the user's menu option through a switch to call the
     * appropriate method.
     * 
     * Date created: November 15, 2021
     * 
     * @param messages
     * @param menuChoice
     * @param probNum
     * @param rangeMax
     * @param rangeMin
     */
    public static void routeOptions(ArrayList<String> messages, int menuChoice, int probNum, int rangeMax, int rangeMin)
    {
        switch(menuChoice)
            {
                case 1:
                    //addition
                    generateAddition(probNum, rangeMax, rangeMin);
                    break;
                case 2: 
                    //subtraction
                    generateSubtraction(probNum, rangeMax, rangeMin);
                    break;
                case 3:
                    //multiplication
                    generateMultiplication(probNum, rangeMax, rangeMin);
                    break;
                case 4: 
                    //division
                    generateDivision(probNum, rangeMax, rangeMin);
                    break;
                case 5:
                    //file upload
                    practiceFile(messages);
                case 0:
                    //program terminated
                    break;
                default: 
                    System.out.println("default triggered"); //REMOVE
                    break;
            } //end switch 
    }
    /**
     * Generates random addition problems according to the user's
     * specifications and then creates an object containing the
     * problem and answer. 
     * 
     * Date created: November 15, 2021
     * 
     * @param probNum
     * @param rangeMax
     * @param rangeMin
     */
    public static void generateAddition(int probNum, int rangeMax, int rangeMin)
    {
        int menuChoice = 1;
        //generates probNum number of addition problem objects and adds them to questionBank array
        for(int i = 0; i < probNum; i++)
        {
            ProblemAnswer question = new ProblemAnswer(rangeMax, rangeMin, menuChoice);
            questionBank.add(question);
        }
    } //end generateAddition
    /**
     * Generates random subtraction problems according to the user's
     * specifications and then creates an object containing the
     * problem and answer. 
     * 
     * Date created: November 15, 2021
     * 
     * @param probNum
     * @param rangeMax
     * @param rangeMin
     */
    public static void generateSubtraction(int probNum, int rangeMax, int rangeMin)
    {
        int menuChoice = 2;
        //generates probNum number of subtraction problem objects and adds them to questionBank array
        for(int i = 0; i < probNum; i++)
        {
            ProblemAnswer question = new ProblemAnswer(rangeMax, rangeMin, menuChoice);
            questionBank.add(question);
        }
    } //end generateSubtraction
    /**
     * Generates random multiplication problems according to the user's
     * specifications and then creates an object containing the
     * problem and answer. 
     * 
     * Date created: November 15, 2021
     */
    public static void generateMultiplication(int probNum, int rangeMax, int rangeMin)
    {
        int menuChoice = 3;
        //generates probNum number of multiplication problem objects and adds them to questionBank array
        for(int i = 0; i < probNum; i++)
        {
            ProblemAnswer question = new ProblemAnswer(rangeMax, rangeMin, menuChoice);
            questionBank.add(question);
        }
    } //end generateMultiplication
    /**
     * Generates random division problems according to the user's
     * specifications and then creates an object containing the
     * problem and answer. 
     * 
     * Date created: November 15, 2021
     * 
     * @param probNum
     * @param rangeMax
     * @param rangeMin
     */
    public static void generateDivision(int probNum, int rangeMax, int rangeMin)
    {
        int menuChoice = 4;
        //generates probNum number of division problem objects and adds them to questionBank array
        for(int i = 0; i < probNum; i++)
        {
            ProblemAnswer question = new ProblemAnswer(rangeMax, rangeMin, menuChoice);
            questionBank.add(question);
        }
    } //end generateDivision
    /**
     * Asks the user the previously randomly generated questions
     * and allows them to answer.
     * 
     * Date created: November 20, 2021
     * 
     * @param menuChoice
     * @param probNum
     * @param messages
     */
    public static void interact(int menuChoice, int probNum, ArrayList<String> messages)
    {
        //explain rules
        System.out.println("\nGet ready to practice! I will give you a problem and you will type in your answer as an integer.");
        System.out.println("If it seems like you are having trouble, I will create a diagram to help you visualize the problem.");

        //declare variables
        int userAns = 0;
        int score = probNum;
        int index = 0;

        //iterates through list of problems in questionBank
        for(ProblemAnswer probAns : questionBank)
        {
            int w = 0;
            
            do
            {
                boolean okay = false;
                while(!okay)
                {
                    try
                    {
                        System.out.println("\n" + probAns.getProblem()); //prints problem
                        userAns = kb.nextInt(); //takes user answer
                        kb.nextLine(); //clear buffer
                        okay = true;
                    }
                    catch(Exception e) //ensures input is an integer
                    {
                        System.out.println("\nError: please enter an integer");
                        kb.next(); //clears scanner to prevent infinite loop
                        okay = false;
                    } //end catch
                } //end while

                if(userAns == probAns.getAnswer()) //if answers correctly
                {
                    int ind = (int)(Math.random() * messages.size());
                    System.out.println(messages.get(ind));
                }//end else
                if(userAns != probAns.getAnswer()) //if user answers incorrectly
                {
                    //tell user they're wrong and decrement score
                    System.out.println("Sorry, that is incorrect.");
                    score--; //FIX score so that it only detracts 1 point per problem 
                    w++; //keeps track of incorrect answers for 1 problem
                }//end if
                //when the user answers incorrectly twice, they get a hint
                if(w>1)
                {
                    score++; //prevents detracting points twice for an incorrect answer to the same problem
                    assist(index, menuChoice);
                    w = 0; //prevents assist from running twice
                }//end if
            }while(userAns != probAns.getAnswer());
            index++;
        }//end for
        System.out.println("Your score is: " + score + "/" + probNum);
    } //end interact
    /**
     * Generates grids of squares matching the problem to
     * help the user find the correct answer.
     * 
     * Date created: November 15, 2021
     * 
     * @param index
     * @param menuChoice
     */
    public static void assist(int index, int menuChoice)
    {
        String operator = "";
        //addition or subtraction
        if(menuChoice == 1 || menuChoice == 2)
        {
            if(menuChoice == 1)
                operator = "+";
            else if(menuChoice == 2)
                operator = "-"; 
            System.out.println("\nCount the squares to help: ");
            System.out.print("  ");
            for(int i = 0; i < questionBank.get(index).getFirstNum(); i++)
            {
                System.out.print("[]"); //prints [] firstNum times
            } //end for
            System.out.print("\n" + operator + " ");
            for(int i = 0; i < questionBank.get(index).getSecondNum(); i++)
            {
                System.out.print("[]"); //prints [] secondNum times
            } //end for
        } //end if
        else if(menuChoice == 3) //multiplication
        {
            System.out.println("\nCount the squares to help: ");
            for(int j = 0; j < questionBank.get(index).getFirstNum(); j++) //row
            {
                for(int i = 0; i < questionBank.get(index).getAnswer(); i++) //column
                {
                    System.out.print("[]");
                }
                System.out.println("");
            } //end for
        } //end elif mult
        else if(menuChoice == 4) //division
        {
            for(int j = 0; j < questionBank.get(index).getSecondNum(); j++) //row
            {
                for(int i = 0; i < questionBank.get(index).getAnswer(); i++) //column
                {
                    System.out.print("[]");
                } //end for 
                System.out.println("");
            } //end for
            System.out.println(questionBank.get(index).getFirstNum() + " divided into " + questionBank.get(index).getSecondNum() + " groups has a result of " + questionBank.get(index).getAnswer() + " per group.");
        } //end elif div
        else
        {
            System.out.println("Error: assist() else triggered");
        } //end else
    }//end assist
    /**
     * Get the file from the user, opens it, and reads contents.
     * 
     * Date created: November 21, 2021
     */
    private static void inputFile()
	{
		Scanner inputFile = null; 

		//chooses file
		try 
		{
			JFileChooser fc = new JFileChooser("Problems");
			int button = fc.showOpenDialog(null); // makes window

			if(button == JFileChooser.CANCEL_OPTION) //ends program if user presses cancel button
				return;
			
			//where file lives
			File file = fc.getSelectedFile();
			inputFile = new Scanner(file); //instantiates scanner specific to particular file; reads file
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error: file not found. Program terminated. " + e.getMessage()); //outputs error message
			System.exit(-8); //closes program 
		} //end catch

		//reads file
		while(inputFile.hasNext()) //runs file has unread contents
		{
			try
			{
				String prob = inputFile.nextLine(); //uses scanner to read the first line of the file
				questionBankList.add(prob); //adds string to list
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			catch(Exception e) //e is within scope of {} so you can use it twice
			{
				System.out.println("Unable to add problem to question bank: " + e.getMessage());
			} //end try catch
		} //end while
		inputFile.close(); //closes file
	} //end inputFile 
    /**
     * Parses the file input to create an object containing
     * questions and answers and then calls interact using
     * the questions from the file.
     * 
     * Date created: November 20, 2021
     * 
     * @param messages
     */
    public static void practiceFile(ArrayList<String> messages)
    {
        inputFile();

        String prob;
        char firstNumCh;
        char secondNumCh;
        int firstNum;
        int secondNum;
        int answer;
        int count = 0;
        int problemType = 1;

        for(String f : questionBankList) //read each line
        {
            count++;
            prob = f; //create string to hold problem
            String intValues = prob.replaceAll("[^0-9]", ""); // only the numbers in the string
            //extracts numbers from string
            firstNumCh = intValues.charAt(0);
            secondNumCh = intValues.charAt(1);
            firstNum = Character.getNumericValue(firstNumCh);
            secondNum = Character.getNumericValue(secondNumCh);
            answer = firstNum + secondNum;
            ProblemAnswer questionFromFile = new ProblemAnswer(firstNum, secondNum, answer, prob);
            questionBank.add(questionFromFile);
        }

        interact(problemType, count, messages);
        
    } //end practice()
} //end class