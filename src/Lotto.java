import java.util.*;
import java.io.*;

public class Lotto{
    public static void main(String[] args) throws FileNotFoundException {

        // Deklarerer et array med 6 ints til lottotallene
        int[] lottoNumbers = new int[6];

        // Kalder metode med array'et som gemmer 6 unikke lottoTal i det
        getRandomNumbers(lottoNumbers);

        // Kalder metode som gemmer array'en i en tekstfil (fordi vi skal i opgaven)
        writeToFile(lottoNumbers);

        // Kalder metode som indlæser data fra tekstfilen ind i et nyt array (fordi vi skal i opgaven)
        int[] numbersFromFile = readFromFile();

        // Deklarerer et array med 6 ints til brugerens lottoseddel
        int[] userNumbers = new int[6];

        // Laver et scanner objekt til at tage imod brugerinput
        Scanner scan = new Scanner(System.in);
        System.out.print("\nVil du have en lottokupon? Tast 'j' eller 'n' og ENTER: ");
        char userChoice = Character.toLowerCase((scan.next().charAt(0)));

        int moneySpent = 0;

        // Loop kører indtil brugeren ikke vil spille mere
        while (userChoice == 'j'){
            moneySpent += 7;
            // Kalder metode med brugerarray'et som gemmer brugerens 6 tal i array'et
            getRandomNumbers(userNumbers);

            // Tjekker hvor mange rigtige brugeren har
            int correctNumbers = 0;
            for (int i = 0; i < lottoNumbers.length; i++) {
                for (int j = 0; j < userNumbers.length; j++) {
                    if (lottoNumbers[i] == userNumbers[j]) {
                        correctNumbers++;
                    }
                }
            }

            // Kalder metode som printer lottotallene pænt ud på skærmen
            System.out.print("\nLottotallene er:    ");
            printLottoNumbers(lottoNumbers);

            // Kalder metode som printer brugerens tal pænt ud på skærmen
            System.out.print("Din kupons tal er:  ");
            printLottoNumbers(userNumbers);

            // Printer hvor mange rigtige brugeren har
            if (correctNumbers == 1) {
                System.out.print("Du har " + correctNumbers + " rigtig!    ");
            }
            else {
                System.out.print("Du har " + correctNumbers + " rigtige!   ");
            }

            // Nyt array til at gemme hvilke tal man har som er rigtige
            int[] correctNumberArray = new int[correctNumbers];

            // Sammenholder lotto-array og bruger-array og gemmer de rigtige tal i det nye array
            int indexNumber = 0;
            for (int i = 0; i < lottoNumbers.length; i++) {
                for (int j = 0; j < userNumbers.length; j++) {
                    if (lottoNumbers[i] == userNumbers[j]) {
                        correctNumberArray[indexNumber] = lottoNumbers[i];
                        indexNumber++;
                    }
                }
            }

            // Printer de rigtige tal ud
            printLottoNumbers(correctNumberArray);
            System.out.print("\n");

            // Spørger brugeren om de vil spille igen og opdaterer userChoice
            System.out.print("Vil du spille igen? Tast 'j' eller 'n' og ENTER: ");
            userChoice = Character.toLowerCase((scan.next().charAt(0)));
        }

        // Når spillet ikke kører længere, afsluttes det med en besked
        System.out.println("Tak for spillet, du har spildt " + moneySpent + " kroner!");
    }

    // Metode som tager et int-array og fylder det med unikke random tal
    public static void getRandomNumbers(int[] randomNumbers){

        // Opretter et random objekt
        Random rand = new Random();
        int maxTal = 35;

        // Kører indtil Array'et er fyldt med tal mellem 1 og 35
        int myIndex = 0;
        while (myIndex < 6){
            // Finder et random tal
            int newNum = rand.nextInt(maxTal) + 1;

            // Boolean starter med at være falsk og bliver sand hvis tallet allerede findes i array'et
            boolean redundant = false;

            // Looper over array'et - hvis det allerede indeholder det nye tal, ændres redundant til true
            for (int i = 0; i < randomNumbers.length; i++){
                if (randomNumbers[i] == newNum) {
                    redundant = true;
                }
            }

            // Kun hvis redundant stadig er false (tal er ikke allerede i array), skrives tallet til vores numArray og index opdateres
            if (redundant == false){
                randomNumbers[myIndex] = newNum;
                myIndex++;
            }
        }
    }

    // Metode som tager en int-array og skriver den til en tekstfil
    public static void writeToFile(int[] inputArray) throws FileNotFoundException {

        // Opretter et PrintStream objekt og en tekstfil
        PrintStream output = new PrintStream(new File("textfile.txt"));

        // Skriver array'et ind i tekstfilen, men kun integers og uden brackets
        for (int i = 0; i < inputArray.length; i++) {
            output.print(inputArray[i] + " ");
        }
    }

    // Metode som læser fra en bestemt tekstfil og returnerer en int-array
    public static int[] readFromFile() throws FileNotFoundException {
        // Opretter et scannerobjekt og et fil-objekt til at læse vores tekstfil
        Scanner scan = new Scanner(new File("textfile.txt"));

        // Deklarerer et nyt tomt array med plads til 6 ints
        int[] savedArray = new int[6];

        // Looper over tekstfilen og skriver dens integers ind i det nye array
        int arrayIndex = 0;
        while (scan.hasNext()) {
            savedArray[arrayIndex] = scan.nextInt();
            arrayIndex++;
        }
        return savedArray;
    }

    // Metode som tager et int-array og printer det pænt ud på skærmen
    public static void printLottoNumbers(int[] lottoNumbers){
        for (int i = 0; i < lottoNumbers.length; i++) {
            System.out.printf("%5d", lottoNumbers[i]);
        }
        System.out.print("\n");
    }
}  