import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        char[][] playground = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };





        int numberOfRounds = 0;

        newOpponentTip(playground);
        while (numberOfRounds < 4) {

            // user
            printMap(playground);
            newUserTip(playground);

            String massage = isWin(playground);
            if (!(massage.equals(""))) {
                System.out.println(massage);
                break;
            }

            //Opponent
            printMap(playground);
            newOpponentTip(playground);

            massage = isWin(playground);
            if (!(massage.equals(""))) {
                System.out.println(massage);
                break;
            }




            numberOfRounds += 1;


        }

        printMap(playground);

    }


    static void printMap(char[][] mapArr) {

        int count = 0;
        for (int i = 0; i < mapArr.length; i++) {

            for (int j = 0; j < mapArr[i].length; j++) {
                count += 1;
                if (mapArr[i][j] == ' ') {
                    System.out.print(count + "|");
                } else {
                    System.out.print(mapArr[i][j] + "|");
                }

            }
            if (i < mapArr.length -1){
                System.out.println("\n------");
            }

        }

        System.out.println("\n-------------------");

    }


    static void newUserTip(char[][] map) {

        boolean positionFree = false;
        while (!positionFree) {
            int usertip = askForTip();
            int[] coordinates = findCoordinates(usertip, map);
            positionFree = checkCoordinates(map, coordinates);

            if (positionFree) {
                map = insertInputInMap(coordinates, map, true);
            }
        }



    }



    static void newOpponentTip(char[][] map) {

        boolean positionFree = false;
        while (!positionFree) {
            int opponentTip = getRandomTip(map);
            int[] coordinates = findCoordinates(opponentTip, map);
            positionFree = checkCoordinates(map, coordinates);

            if (positionFree) {
                map = insertInputInMap(coordinates, map, false);
            }
        }

    }


    static int getRandomTip(char[][] map) {

        int maxNumber = map.length * map.length;

        Random rand = new Random();
        int randomTip = (rand.nextInt(maxNumber)) + 1;

        return randomTip;
    }


    static int askForTip() {
        int userInput = -1;
        while (userInput >= 10 || userInput <= 0) {
            try {
                System.out.print("your Tip (1-9):");
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("pleas enter a number between 1 and 9");
                userInput =-1;
            }catch (Exception e){
                userInput =-1;
            }

        }
        return userInput;

    }


    static int[] findCoordinates(int userNumber, char[][] map) {

        final int playGroundLength = map.length;

        // find row
        int row = (userNumber - 1) / playGroundLength;

        // find column
        int column = (userNumber - 1) % playGroundLength;

        int[] coordinatesArr = {row, column};
        return coordinatesArr;

    }


    static boolean checkCoordinates(char[][] map, int[] coordinates) {

        int row = coordinates[0];
        int column = coordinates[1];
        boolean positionFree = false;

        if (map[row][column] == ' ') {
            positionFree = true;
        }

        return positionFree;
    }


    static char[][] insertInputInMap(int[] coordinates, char[][] map, boolean fromUser) {

        char symbol = 'O';
        if (fromUser) {
            symbol = 'X';
        }

        map[coordinates[0]][coordinates[1]] = symbol;

        return map;

    }


    static String isWin(char[][] map) {

        String massage = "";

        //  vertical
        for (int i = 0; i < map.length; i++) {

            int counter = 0;
            for (int j = 0; j < map[i].length; j++) {
                char currentChar = map[i][j];

                if (currentChar == 'X') {
                    counter += 1;

                } else if (currentChar == 'O') {
                    counter -= 1;

                }

            }
            //  if user win
            if (counter == 3) {
                massage = "you win the game";

            } else if (counter == -3) {
                massage = "you lose the game";
            }

        }

        // horizontal (i and j change )
        for (int i = 0; i < map.length; i++) {

            int counter = 0;
            for (int j = 0; j < map[i].length; j++) {
                char currentChar = map[j][i];

                if (currentChar == 'X') {
                    counter += 1;

                } else if (currentChar == 'O') {
                    counter -= 1;

                }

            }
            //  if user win
            if (counter == 3) {
                massage = "you win the game";

            } else if (counter == -3) {
                massage = "you lose the game";
            }

        }


        // diagonal right

        int counter = 0;
        for (int i = 0; i < map.length; i++) {

            for (int j = map[i].length - 1; j >= 0; j--) {

                if (i + j == map[i].length - 1) {

                    char currentChar = map[i][j];

                    if (currentChar == 'X') {
                        counter += 1;

                    } else if (currentChar == 'O') {
                        counter -= 1;

                    }

                }
                //  if user win
                if (counter == 3) {
                    massage = "you win the game";

                } else if (counter == -3) {
                    massage = "you lose the game";
                }

            }
        }


        // diagonal left
        counter = 0;
        for (int i = 0; i < map.length; i++) {

            char currentChar = map[i][i];

            if (currentChar == 'X') {
                counter += 1;

            } else if (currentChar == 'O') {
                counter -= 1;

            }

        }
        if (counter == 3) {
            massage = "you win the game";

        } else if (counter == -3) {
            massage = "you lose the game";
        }

        return massage;
    }


}
