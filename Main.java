package com.company;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static char[][] board = new char[3][3];
    private static int currentPlayer = 0;

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", enter the row (1-3): ");
            int row;

            try {
                row = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); // Clear the input buffer
                continue;
            }

            if (row < 0 || row >= 3) {
                System.out.println("Invalid row number, try again.");
                continue;
            }

            System.out.print("Enter the column (1-3): ");
            int col;

            try {
                col = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); // Clear the input buffer
                continue;
            }

            if (col < 0 || col >= 3 || board[row][col] != '\u0000') {
                System.out.println("Invalid move, try again.");
                continue;
            }

            board[row][col] = (char) ('0' + currentPlayer);

            if (checkForWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 0) ? 1 : 0;
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, '\u0000');
        }
    }

    private static void printBoard() {
        System.out.println("--------------");
        for (char[] row : board) {
            System.out.print("| ");
            for (char c : row) {
                System.out.print(c + " | ");
            }
            System.out.println();
            System.out.println("--------------");
        }
    }

    private static boolean checkForWin(int player) {
        char symbol = (char) ('0' + player);

        // Check rows
        for (char[] row : board) {
            if (row[0] == symbol && row[1] == symbol && row[2] == symbol) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }
}
