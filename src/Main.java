import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int sandwichesmade = 0;
        int totalDecibel = 0;
        boolean sandwiching = true;
        Scanner scan = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        map.put("Ham", 10);
        map.put("Chicken Salad", 50);
        map.put("coal:", 20);
        map.put("American cheese", 10);
        map.put("Watermelon ", 30);
        map.put("turkey ", 10);
        map.put("toast ", 30);
        map.put("ketchup ", 4);
        map.put("mustard", 4);
        map.put("crunchy peanut butter",100);
        map.put("Waterballoon", 30);

        System.out.println("Welcome to make a sandwich at 3 am without waking up your dad");
        System.out.println("How to play:\n1. Your goal is to make a delicious sandwich\n2. Each sandwich option has a decibel level on it, the higher the level the more points you get, but if your decibel level adds up to the limit your dad wakes up, and you lose all points\n3. The decibel limit is unknown to you (Between 200-500). You have to risk it for higher points. Good luck and have fun!");
        int decibelLimit = generateRandomDecibelLimit();

        while (sandwiching) {
            // Print the available sandwich options and their decibel levels
            System.out.println("Sandwich Options:");
            List<String> randomOptions = getRandomSandwichOptions(map, 3);
            for (int i = 0; i < randomOptions.size(); i++) {
                System.out.println((i + 1) + ". " + randomOptions.get(i) + " - Decibel Level: " + map.get(randomOptions.get(i)));
            }

            int userChoice = getUserChoice(scan, randomOptions.size());

            if (userChoice == 0) {
                sandwiching = false;
                System.out.println("Stopping the game. You made " + sandwichesmade + " sandwiches. Goodbye! Your score was: " + totalDecibel);
            } else {
                String option = randomOptions.get(userChoice - 1);
                int optionDecibel = map.get(option);
                totalDecibel += optionDecibel;

                if (totalDecibel > decibelLimit) {
                    System.out.println("Uh-oh! You surpassed the unknown decibel limit. Your dad woke up, and you lose all points. The limit was: " + decibelLimit);
                    totalDecibel = 0; // Reset total decibel level
                    break;
                } else {
                    sandwichesmade++;
                    System.out.println("Added " + option + ". Your score was:  " + totalDecibel);
                }

                System.out.println("Do you want to make another sandwich? (yes/no): ");
                String continueChoice = scan.nextLine().toLowerCase();
                if (continueChoice.equals("no")) {
                    sandwiching = false;
                    System.out.println("Stopping the game. You made " + sandwichesmade + " sandwiches. Goodbye!");
                }
            }
        }
    }

    private static List<String> getRandomSandwichOptions(Map<String, Integer> map, int count) {
        List<String> options = new ArrayList<>(map.keySet());
        Collections.shuffle(options);
        return options.subList(0, Math.min(count, options.size()));
    }

    private static int getUserChoice(Scanner scan, int maxChoice) {
        int userChoice = -1;
        do {
            System.out.println("Enter the number of your choice (or 0 to stop): ");
            try {
                userChoice = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            if (userChoice < 0 || userChoice > maxChoice) {
                System.out.println("Invalid choice. Please enter a number between 0 and " + maxChoice + ".");
            }
        } while (userChoice < 0 || userChoice > maxChoice);

        return userChoice;
    }

    public static int generateRandomDecibelLimit() {
        return (int) (Math.random() * 301) + 200; // Random limit between 200 and 500
    }
}