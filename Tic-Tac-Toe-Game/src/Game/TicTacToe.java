package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static ArrayList<Integer> playerPositions;
	static ArrayList<Integer> cpuPositions;

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		do {
			playerPositions = new ArrayList<>();
			cpuPositions = new ArrayList<>();
			playTicTacToe();
			System.out.println("Do you wanna play again? [Y/N]");
			String playAgain = scn.next();
			if(playAgain.equals("N")) {
				break;
			}
		}while(true);

	}

	public static void playTicTacToe() {
		// printing the Game Board
		
		char[][] board = { { ' ', '|', ' ', '|', ' ' }, 
						   { '-', '+', '-', '+', '-' }, 
						   { ' ', '|', ' ', '|', ' ' },
						   { '-', '+', '-', '+', '-' }, 
						   { ' ', '|', ' ', '|', ' ' } };
		printBoard(board);
		
		while (true) {

			// *user plays*
			Scanner scn = new Scanner(System.in);
			
			System.out.println("Where do you wanna place 'X'? Enter positon [1-9]");
			int userPos = scn.nextInt();
			
			while (!validPos(userPos)) {
				System.out.println("Position taken, try another position");
				userPos = scn.nextInt();
			}
			placePiece(board, userPos, "player");
			// check
			String res = checkWin();
			if (res.length() > 0) {
				printBoard(board);
				System.out.println(res);
				break;
			}

			// *cpu plays*
			Random rand = new Random(); // using Randomizer here to generate random cpu moves
			int cpuPos = rand.nextInt(9) + 1; // [1 - 9]
			while (!validPos(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(board, cpuPos, "cpu");
			printBoard(board);

			// check
			res = checkWin();
			if (res.length() > 0) {
				System.out.println(res);
				break;
			}

		}
	}

	public static boolean validPos(int pos) {
		if (playerPositions.contains(pos) || cpuPositions.contains(pos)) {
			return false;
		}
		return true;
	}

	public static void printBoard(char[][] board) {
		for (char[] row : board) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placePiece(char[][] board, int pos, String user) {
		char piece = ' ';
		if (user.equals("player")) {
			piece = 'X';
			playerPositions.add(pos);
//			System.out.println(playerPositions.toString());
		} else if (user.equals("cpu")) {
			piece = 'O';
			cpuPositions.add(pos);
		}

		switch (pos) {
		case 1:
			board[0][0] = piece;
			break;
		case 2:
			board[0][2] = piece;
			break;
		case 3:
			board[0][4] = piece;
			break;
		case 4:
			board[2][0] = piece;
			break;
		case 5:
			board[2][2] = piece;
			break;
		case 6:
			board[2][4] = piece;
			break;
		case 7:
			board[4][0] = piece;
			break;
		case 8:
			board[4][2] = piece;
			break;
		case 9:
			board[4][4] = piece;
			break;
		default:
			System.out.print("Enter between 1 and 9");
			break;
		}
	}

	public static String checkWin() {
		
		// Can also use a hashset by generating a string code
		List<Integer> row1 = Arrays.asList(1, 2, 3);
		List<Integer> row2 = Arrays.asList(4, 5, 6);
		List<Integer> row3 = Arrays.asList(7, 8, 9);
		List<Integer> col1 = Arrays.asList(1, 4, 7);
		List<Integer> col2 = Arrays.asList(2, 5, 8);
		List<Integer> col3 = Arrays.asList(3, 6, 9);
		List<Integer> diag1 = Arrays.asList(1, 5, 9);
		List<Integer> diag2 = Arrays.asList(7, 5, 3);
		/*
		 1 2 3
		 4 5 6
		 7 8 9
		 */		
		
		List<List> wins = new ArrayList<List>();
		wins.add(row1);
		wins.add(row2);
		wins.add(row3);
		wins.add(col1);
		wins.add(col2);
		wins.add(col3);
		wins.add(diag1);
		wins.add(diag2);
		
		String res = "";
		for (List l : wins) {
			if (playerPositions.containsAll(l)) {
				return "Congratulations, you won!";
			} else if (cpuPositions.containsAll(l)) {
				return "Better luck next time:( ";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				res = "It's a tie";
			}
		}
		return res;
	}
}
