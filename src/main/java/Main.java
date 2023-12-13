import java.util.Scanner;

public class Main {

    public static char[][] createEmptyGrid(char[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = ' ';
            }
        }
        return state;
    }

    public static void printGrid(char[][] state) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean checkNumbers(int n, int m) {
        if (!(n > 0 && n <= 3 && m > 0 && m <= 3)){
            System.out.println("Coordinates should be from 1 to 3!");
        }
        return n > 0 && n <= 3 && m > 0 && m <= 3;
    }

    public static boolean isPlaceIsEmpty(char[][] state, int n, int m) {
        if (!(state[n - 1][m - 1] == ' ')) {
            System.out.println("This cell is occupied! Choose another one!");
        }
        return state[n - 1][m - 1] == ' ';
    }

    public static char checkXOrO(int count) {
        if (count % 2 != 0) {
            return 'X';
        } else {
            return 'O';
        }
    }

    public static void updateGrid(char[][] state, int n, int m, char symbol) {
        state[n - 1][m - 1] = symbol;
    }

    public static boolean checkGrid(char[][] state) {
        boolean checkX = false;
        boolean checkO = false;
        boolean check_ = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == ' ') {
                    check_ = true;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            int row = state[i][0] + state[i][1] + state[i][2];
            if (row == 264) {
                checkX = true;
            }
            if (row == 237) {
                checkO = true;
            }
        }
        for (int j = 0; j < 3; j++) {
            int column = state[0][j] + state[1][j] + state[2][j];
            if (column == 264) {
                checkX = true;
            }
            if (column == 237) {
                checkO = true;
            }
        }
        int d1 = state[0][0] + state[1][1] + state[2][2];
        int d2 = state[0][2] + state[1][1] + state[2][0];
        if (d1 == 264 || d2 == 264) {
            checkX = true;
        }
        if (d1 == 237 || d2 == 237) {
            checkO = true;
        }

        if (!checkX && !checkO && !check_) {
            System.out.println("Draw");
        } else if (checkX && !checkO) {
            System.out.println("X wins");
        } else if (!checkX && checkO) {
            System.out.println("O wins");
        }

        if (!checkX && !checkO && check_) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] state = new char[3][3];

        createEmptyGrid(state);
        printGrid(state);

        boolean checkPlaces = true;
        boolean checkNum = false;
        int count = 1;



        while (checkPlaces) {

            while (!checkNum) {
                System.out.print("Enter the coordinates: ");
                String numbers = scanner.nextLine();
                int n;
                int m;
                try {
                    String number1 = numbers.split(" ")[0];
                    String number2 = numbers.split(" ")[1];
                    n = Integer.parseInt(number1);
                    m = Integer.parseInt(number2);
                    checkNum = true;
                    if (!checkNumbers(n, m)) {
                        checkNum = false;
                    } else if (isPlaceIsEmpty(state, n, m)) {
                        char symbol = checkXOrO(count);
                        count++;
                        updateGrid(state, n, m, symbol);
                    } else {
                        checkNum = false;
                    }
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                }
            }
            printGrid(state);
            checkPlaces = checkGrid(state);
            checkNum = false;
        }
    }
}
