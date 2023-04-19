package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Initializing variables and objects
        Scanner scanner = new Scanner(System.in);
        String pathToFile = ("C:\\Users\\mikec\\IdeaProjects\\Rock-Paper-Scissors (Java)\\Rock-Paper-Scissors (Java)\\task\\src\\rockpaperscissors\\rating.txt");
        File file = new File(pathToFile);
        Random randomizer = new Random();
        int score = 0;
        int randomNumber = 0;
        int counterConditionsArray = 0;
        int totalObjects = 0;
        int halfObjects = 0;
        int indexOfUserOption = 0;
        int counterOutOfBounds = 0;
        String userInput = "";
        String userName = "";
        String computerOption = "";
        String[] winCondition;
        String[] loseCondition;
        String objectListString;
        String rockPaperScissors = "rock,paper,scissors";

        //Reading the file and assigning its values to an Array type String
        String[] userInfoArray = readFile(file);

        //User inputting the name
        System.out.println("Enter your name:");
        userInput = scanner.nextLine();

        //Verifying if the username is present on the file "rating.txt"
        for (String name : userInfoArray) {
            if (name.contains(userInput)) {
                String[] splitter = name.split(" ");
                userName = splitter[0];
                score = Integer.parseInt(splitter[1]);
                System.out.println("Hello, " + userName);
                System.out.println("Your rating: " + score);
                break;
            }
        }

        //If username doesn't exist on the file then it's going to assign the name that was inputted by the user, and it
        //will have a score of 0.
        if (userName.equals("")) {
            userName = userInput;
            System.out.println("Hello, " + userName);
            score = 350;
        }

        //Asking the user for the objects to play with (Options to be chosen)
        userInput = scanner.nextLine();
        String optionsToPlayWith = userInput;
        String[] optionsArray = optionsToPlayWith.split(",");
        objectListString = String.join(",", optionsArray);
        System.out.println("Okay, let's start");

        //Giving values to these variables if there's items on the input
        if (!(optionsToPlayWith.equals(""))) {
            totalObjects = optionsArray.length;
            halfObjects = (totalObjects - 1) / 2;
        }

        //Loop for the user to play, !exit equals ending the program.
        while (true) {

            userInput = scanner.nextLine();

            if (userInput.equals("!exit")) {
                System.out.println("Bye!");
                break;
            } else if (rockPaperScissors.contains(userInput) && rockPaperScissors.contains(computerOption)) {
                //Generating a new number each time it enters this part of the loop
                randomNumber = randomizer.nextInt(1 + 2);

                //Assigning a value to the computer input depending on the number generated
                switch (randomNumber) {
                    case 0 -> computerOption = "rock";
                    case 1 -> computerOption = "paper";
                    case 2 -> computerOption = "scissors";
                }
                //Determining an outcome and printing it

                if (userInput.equals(computerOption)) {
                    System.out.println("There is a draw " + "(" + userInput + ")");
                    score += 50;
                } else if (userInput.equals("rock") && computerOption.equals("paper")) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                } else if (userInput.equals("paper") && computerOption.equals("rock")) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                } else if (userInput.equals("paper") && computerOption.equals("scissors")) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                } else if (userInput.equals("scissors") && computerOption.equals("paper")) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                } else if (userInput.equals("scissors") && computerOption.equals("rock")) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                } else if (userInput.equals("rock") && computerOption.equals("scissors")) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                }

            } else if (userInput.equals("!rating")) {
                System.out.println("Your rating: " + score);
            } else if (objectListString.contains(userInput)) {
               /* Generating a number between 0 and the indexes found on the 'Objects Array' and the computer is going
                To choose an option randomly between those objects. */
                randomNumber = randomizer.nextInt(totalObjects - 1);
                computerOption = optionsArray[randomNumber];


                if (userInput.equals("rock") && computerOption.equals("paper")) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                } else if (userInput.equals("paper") && computerOption.equals("rock")) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                } else if (userInput.equals("paper") && computerOption.equals("scissors")) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                } else if (userInput.equals("scissors") && computerOption.equals("paper")) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                } else if (userInput.equals("scissors") && computerOption.equals("rock")) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                } else if (userInput.equals("rock") && computerOption.equals("scissors")) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                }

                //Locating the index of the userInput object
                for (int i = 0; i < optionsArray.length; i++) {
                    if (optionsArray[i].equals(userInput)) {
                        indexOfUserOption = i;
                        break;
                    }
                }

                //Creating the Array Size for Win and determining the win condition objects
                winCondition = new String[halfObjects];
                for (int i = 1; i <= halfObjects; i++) {
                    // If the win conditions were out of bounds at the start of index, then we're going to start
                    // from the back of the array
                    if (optionsArray[indexOfUserOption].equals(optionsArray[0]) || optionsArray[indexOfUserOption - i].equals(optionsArray[0])) {
                        winCondition[counterConditionsArray] = optionsArray[0];
                        counterConditionsArray++;
                        break;
                    } else {
                        winCondition[counterConditionsArray] = optionsArray[indexOfUserOption - i];
                        counterConditionsArray++;
                    }
                }

                //This loop is going to work if the condition above is true the 'if' part, if not then is ignored
                for (int i = 1; counterConditionsArray < halfObjects; i++) {
                    winCondition[counterConditionsArray] = optionsArray[totalObjects - i];
                    counterConditionsArray++;
                }

                //Resetting the counter for the index
                counterConditionsArray = 0;

                //Creating the array size for the Loss condition objects and determining the loss condition objects
                loseCondition = new String[halfObjects];
                for (int i = 1; i <= halfObjects; i++) {
                    // If the loss conditions were out of bounds at the end of index, then we're going to start
                    // from the start of the array
                    if (optionsArray[indexOfUserOption].equals(optionsArray[totalObjects - 1]) || optionsArray[indexOfUserOption + i].equals(optionsArray[totalObjects - 1])) {
                        loseCondition[counterConditionsArray] = optionsArray[0];
                        counterConditionsArray++;
                        counterOutOfBounds++;
                        break;
                    } else {
                        loseCondition[counterConditionsArray] = optionsArray[indexOfUserOption + i];
                        counterConditionsArray++;
                    }
                }

                //This loop is going to work if the condition above is true the 'if' part, if not then is ignored
                for (int i = 1; counterConditionsArray < halfObjects; i++) {
                    loseCondition[counterConditionsArray] = optionsArray[counterOutOfBounds];
                    counterConditionsArray++;
                    counterOutOfBounds++;
                }

                //Converting both arrays to String to determine Win/Loss condition
                String winConditionString = String.join(",", winCondition);
                String loseConditionString = String.join(",", loseCondition);

                //Determining the outcome Win/Lose.
                if (userInput.equals(computerOption)) {
                    System.out.println("There is a draw " + "(" + userInput + ")");
                    score += 50;
                } else if (winConditionString.contains(computerOption)) {
                    System.out.println("Well done. The computer chose " + computerOption + " and failed");
                    score += 100;
                } else if (loseConditionString.contains(computerOption)) {
                    System.out.println("Sorry, but the computer chose " + computerOption);
                }

                //Resetting the variables used
                indexOfUserOption = 0;
                counterConditionsArray = 0;
                counterOutOfBounds = 0;
            } else {
                System.out.println("Invalid input");
            }
        }

    }

    public static String[] readFile(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        String[] usersRating = new String[3];

        while (fileReader.hasNext()) {
            for (int i = 0; i < usersRating.length; i++) {
                String usersOnFile = fileReader.nextLine();
                usersRating[i] = usersOnFile;
            }
        }

        return usersRating;
    }
}
