/**
 * ---------------------------------------------------------------------------
 * File name: ProblemAnswer.java
 * Project name: Semester Project
 * ---------------------------------------------------------------------------
 * Creator's name and email: Sophia Herrell, herrells@etsu.edu
 * Course:  CSCI 1250
 * Creation Date: November 15, 2021
 * ---------------------------------------------------------------------------
*/

import java.util.Random;

/**
 * Calculator class that 
 * 
 * Date created: November 15, 2021
 * 
 * @author Sophia Herrell
 */

public class ProblemAnswer 
{
    // static Scanner kb = new Scanner(System.in);
    static Random r = new Random();

    private int rangeMax;
    private int rangeMin;
    private int firstNum;
    private int secondNum;
    private String problem;
    private int answer;
    int menuChoice;

    /**
     * Default constructor. 
     * 
     * Date created: November 20, 2021
     */
    public ProblemAnswer()
    {
        menuChoice = 1;
        rangeMax = 10;
        rangeMin = 0;
        firstNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
        secondNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
        answer = firstNum + secondNum;
        problem = "" + firstNum + " + " + secondNum;
    }//end ProblemAnswer()

    /**
     * Parameterized constructor.
     * 
     * Date created: November 20, 2021
     * 
     * @param rangeMax
     * @param rangeMin
     * @param menuChoice
     */
    public ProblemAnswer(int rangeMax, int rangeMin, int menuChoice)
    {
        this.rangeMax = rangeMax;
        this.rangeMin = rangeMin;
        this.menuChoice = menuChoice;

        switch(menuChoice)
        {
            case 1:
                //addition
                firstNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                secondNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                answer = firstNum + secondNum;
                problem = "" + firstNum + " + " + secondNum;
                break;
            case 2:
                //subtraction
                //prevents negative answers
                do{
                    firstNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                    secondNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                }while(firstNum<secondNum); 
                answer = firstNum - secondNum;
                problem = "" + firstNum + " - " + secondNum;
                break;
            case 3:
                //multiplication
                firstNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                secondNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                answer = firstNum * secondNum;
                problem = "" + firstNum + " * " + secondNum;
                break;
            case 4: 
                //division
                //prevents 0 in the denominator, remainders
                do
                {
                    firstNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                    secondNum = r.nextInt (rangeMax - rangeMin + 1)  +  rangeMin;
                }while((secondNum == 0) || (secondNum > firstNum) || (firstNum%secondNum != 0)); 
                answer = firstNum / secondNum;
                problem = "" + firstNum + " / " + secondNum;
                break;
        }//end switch
    }//end ProblemAnswer(param)
    /**
     * Parameterized constructor for use in uploading and manipulating files.
     * 
     * Date created: November 20, 2021
     * 
     * @param firstNum
     * @param secondNum
     * @param answer
     * @param problem
     */
    public ProblemAnswer(int firstNum, int secondNum, int answer, String problem)
    {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.answer = answer;
        this.problem = problem;
    }
    /**
     * Gets the problem.
     * 
     * Date created: November 20, 2021
     * 
     * @return
     */
    public String getProblem() 
    {
        return problem;
    } //end getProblem
    /**
     * Gets the answer.
     * 
     * Date created: November 20, 2021
     * 
     * @return
     */
    public int getAnswer() 
    {
        return answer;
    } //end getAnswer
    /**
     * Gets firstNum.
     * 
     * Date created: November 20, 2021
     * 
     * @return
     */
    public int getFirstNum() 
    {
        return firstNum;
    } //end getFirstNum
    /**
     * Gets secondNum.
     * 
     * Date created: November 20, 2021
     * 
     * @return
     */
    public int getSecondNum() 
    {
        return secondNum;
    } //end getSecondNum
    /**
     * Displays information about a particular object within the ProblemAnswer class.
     * 
     * Date created: November 20, 2021
     * 
     * @return
     */
    public String toString()
    {
        String msg = "";
        msg += "\nProblem:  " + problem;
        msg += "\nAnswer: " + answer;
        msg += "\nFirstNum: " + firstNum;
        msg += "\nSecondNum: " + secondNum;
        return msg;
    } //end toString
}//end class