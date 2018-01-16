import java.io.*;

class Test {
	
	static final int WHITEKING = 75;
	static final int BLACKKING = 107;
	static final int WHITEPAWN = 80;
	static final int BLACKPAWN = 112;
	static final int WHITEROOK = 82;
	static final int BLACKROOK = 114;
	static final int WHITEBISHOP = 66;
	static final int BLACKBISHOP = 98;
	static final int WHITEQUEEN = 81;
	static final int BLACKQUEEN = 113;
	static final int WHITEKNIGHT = 78;	
	static final int BLACKKNIGHT = 110;
	
	
	static class Point {
		int x;
		int y;
		Point(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static class Chess {
		int[] x;
		int[] y;
		int steps;
		int directions;
		Chess() {
			x = new int[10];
			y = new int[10];
			steps = 0;
			directions = 0;
		}
	}
	
	
	

	public static void main(String[] args) throws IOException {
		
		// Use File Stream Reader
		 BufferedReader br = new BufferedReader (new FileReader ("C:\\Users\\yy957\\Desktop\\ACM\\Main\\inputs\\p10196.txt"));
		
		// Use Input Steam Reader
//		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		Chess[] pieces = new Chess[6];
		
		for (int i = 0; i < pieces.length; i++)
			pieces[i] = new Chess();
		
		// White Pawn
		pieces[0].x[0] = -1;
		pieces[0].y[0] = 1;
		pieces[0].x[1] = -1;
		pieces[0].y[1] = -1;
		
		pieces[0].steps = 1;
		pieces[0].directions = 2;
		
		// Rook
		pieces[1].x[0] = 1;
		pieces[1].y[0] = 0;
		pieces[1].x[1] = -1;
		pieces[1].y[1] = 0;
		pieces[1].x[2] = 0;
		pieces[1].y[2] = 1;
		pieces[1].x[3] = 0;
		pieces[1].y[3] = -1;
		
		pieces[1].steps = 8;
		pieces[1].directions = 4;
		
		// Bishop
		pieces[2].x[0] = 1;
		pieces[2].y[0] = 1;
		pieces[2].x[1] = 1;
		pieces[2].y[1] = -1;
		pieces[2].x[2] = -1;
		pieces[2].y[2] = 1;
		pieces[2].x[3] = -1;
		pieces[2].y[3] = -1;
		
		pieces[2].steps = 8;
		pieces[2].directions = 4;
		
		// Queen
		pieces[3].x[0] = 1;
		pieces[3].y[0] = 0;
		pieces[3].x[1] = -1;
		pieces[3].y[1] = 0;
		pieces[3].x[2] = 0;
		pieces[3].y[2] = 1;
		pieces[3].x[3] = 0;
		pieces[3].y[3] = -1;
		pieces[3].x[4] = 1;
		pieces[3].y[4] = 1;
		pieces[3].x[5] = 1;
		pieces[3].y[5] = -1;
		pieces[3].x[6] = -1;
		pieces[3].y[6] = 1;
		pieces[3].x[7] = -1;
		pieces[3].y[7] = -1;
		
		pieces[3].steps = 8;
		pieces[3].directions = 8;
		
		
		// Knight
		pieces[4].x[0] = -1;
		pieces[4].y[0] = 2;
		pieces[4].x[1] = 1;
		pieces[4].y[1] = 2;
		pieces[4].x[2] = 1;
		pieces[4].y[2] = -2;
		pieces[4].x[3] = -1;
		pieces[4].y[3] = -2;
		pieces[4].x[4] = -2;
		pieces[4].y[4] = 1;
		pieces[4].x[5] = 2;
		pieces[4].y[5] = 1;
		pieces[4].x[6] = 2;
		pieces[4].y[6] = -1;
		pieces[4].x[7] = -2;
		pieces[4].y[7] = -1;
		
		pieces[4].steps = 1;
		pieces[4].directions = 8;
		
		// Black Pawn
		pieces[5].x[0] = 1;
		pieces[5].y[0] = -1;
		pieces[5].x[1] = 1;
		pieces[5].y[1] = 1;
		
		pieces[5].steps = 1;
		pieces[5].directions = 2;
				
		int gameNum = 0;
		do {						
			boolean isEmpty = true;
			int[][] board = new int[8][8];			
			for (int i = 0; i < 8; i++) {
				String s = br.readLine();
				if (s.equals("")) 
					s = br.readLine();
				char[] line = s.toCharArray();

				for (int j = 0; j < 8; j++) {
					char c = line[j];
					if (c != '.')
						isEmpty = false;
					board[i][j] = line[j];
				}
			}			
			
			if(isEmpty) break;												
			
			 checkTheCheck(board, pieces, ++gameNum);
		}
		while(true); 	
		return;
	}	
	
	// > 96 lower case, < 96 upper case
	static void checkTheCheck(int[][] curtBoard, Chess[] pieces, int GameNum) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int curtPiece = curtBoard[i][j];
				if (curtPiece < 96) {				
					// Handle white pieces
					switch (curtPiece) {
					case WHITEPAWN:				
						if (movePiece(curtBoard, i, j, pieces[0], false, BLACKKING, GameNum))
							return;
						break;
					case WHITEROOK:
						if (movePiece(curtBoard, i, j, pieces[1], false, BLACKKING, GameNum))
							return;
						break;
					case WHITEBISHOP:
						if (movePiece(curtBoard, i, j, pieces[2], false, BLACKKING, GameNum))
							return;
						break;
					case WHITEQUEEN:
						if (movePiece(curtBoard, i, j, pieces[3], false, BLACKKING, GameNum))
							return;
						break;
					case WHITEKNIGHT:
						if (movePiece(curtBoard, i, j, pieces[4], true, BLACKKING, GameNum))
							return;
					default:
						break;
					}														
				}
				
				else {
					// Handle black pieces
					switch (curtPiece) {
					case BLACKPAWN:				
						if (movePiece(curtBoard, i, j, pieces[5], false, WHITEKING, GameNum))
							return;
						break;
					case BLACKROOK:
						if (movePiece(curtBoard, i, j, pieces[1], false, WHITEKING, GameNum))
							return;
						break;
					case BLACKBISHOP:
						if (movePiece(curtBoard, i, j, pieces[2], false, WHITEKING, GameNum))
							return;
						break;
					case BLACKQUEEN:
						if (movePiece(curtBoard, i, j, pieces[3], false, WHITEKING, GameNum))
							return;
						break;
					case BLACKKNIGHT:
						if (movePiece(curtBoard, i, j, pieces[4], true, WHITEKING, GameNum))
							return;
					default:
						break;
					}
					
				}
			}
		}				
		String res = "Game #" + GameNum + ": no king is in check.";
		System.out.println(res);
	}	
	
	static boolean movePiece(int[][] Board, int x, int y, Chess piece, boolean isKnight, int king, int gameNum) {
		for (int i = 0; i < piece.directions; i++) {
			int newX = x, newY = y;
			for (int j = 0; j < piece.steps; j++) {
				newX += piece.x[i];
				newY += piece.y[i];
				if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
					break;
				}
				int nextPiece = Board[newX][newY];
				
				if (nextPiece != 46 && nextPiece != king)
					break;
				
				else if (nextPiece == king) {
					// print info
					printWinMessage(king, gameNum);
					return true;
				}				
			}
		}
		return false;
	}
	
	static void printWinMessage(int king, int gameNum) {
		String res = "Game #" + gameNum + ": ";
		if (king == BLACKKING) {
			res += "black king is in check.";
		}
		else {
			res += "white king is in check.";
		}
		
		System.out.println(res);
	}
}