package sudoku;

import java.util.Arrays;
import java.util.HashMap;

import exceptions.MalformedJSONException;

public class Sudoku {
	
	private double time;
	private int feld[][];
	private int status;

	// Class Constructor
	public Sudoku() {
		this.feld = new int[9][9];
		this.status = 0;
		this.time = 0;
	}
	
	
	/**
	 * @return sudoku grid in json String representation seen below:<br>
	 * {
	 *		"sudokuJSON": 
	 *			[ 
	 *				[ 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
	 *				[ 4, 5, 6, 7, 8, 9, 1, 2, 3 ],
	 *				[ 7, 8, 9, 1, 2, 3, 4, 5, 6 ], 
	 *				[ 2, 1, 4, 3, 6, 5, 8, 9, 7 ], 
	 *				[ 3, 6, 5, 8, 9, 7, 2, 1, 4 ], 
	 *				[ 8, 9, 7, 2, 1, 4, 3, 6, 5 ], 
	 *				[ 5, 3, 1, 6, 4, 2, 9, 7, 8 ], 
	 *				[ 6, 4, 2, 9, 7, 8, 5, 3, 1 ], 
	 *				[ 9, 7, 8, 5, 3, 1, 6, 4, 2 ] 
	 *			],
	 *		"time": 123.0,
	 *		"status": 200
	 *	}
	 */ 
	public String toJSON() {
		return "{\n\"sudokuJSON\":\n[\n" + Arrays.deepToString(this.feld).replace("], ", "],\n").replace("[[","[").replace("]]","]") + "\n],\n\"time\": "+ this.time%.1f + ",\n\"status\": " + this.status + "\n}";
	}
	
	/**
	 * Creates the sudoku grid from given JSON
	 * @param sudokuJSON Sudoku in JSON syntax of following form:<br>
	 * {
	 *	"sudokuJSON": 
	 *		[ 
	 *			[ 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
	 *			[ 4, 5, 6, 7, 8, 9, 1, 2, 3 ],
	 *			[ 7, 8, 9, 1, 2, 3, 4, 5, 6 ], 
	 *			[ 2, 1, 4, 3, 6, 5, 8, 9, 7 ], 
	 *			[ 3, 6, 5, 8, 9, 7, 2, 1, 4 ], 
	 *			[ 8, 9, 7, 2, 1, 4, 3, 6, 5 ], 
	 *			[ 5, 3, 1, 6, 4, 2, 9, 7, 8 ], 
	 *			[ 6, 4, 2, 9, 7, 8, 5, 3, 1 ], 
	 *			[ 9, 7, 8, 5, 3, 1, 6, 4, 2 ] 
	 *		]
	 * }
	 * @return if creation was successful true, else false
	 */

	public boolean createFromJSON(String sudokuJSON) throws MalformedJSONException{
		//{"sudokuJSON":[["1","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""]]}
		String[] splitSudoku = sudokuJSON.split(",");
		int counter = 0;
		char cachedChar;
		int cachedNumericValue;
		try {
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 9; y++) {

					if (counter == 0) {
						cachedChar = splitSudoku[counter].charAt(17);
						cachedNumericValue = Character.getNumericValue(cachedChar);
					} 
					else if (counter % 9 == 0) {
						cachedChar = splitSudoku[counter].charAt(2);
						cachedNumericValue = Character.getNumericValue(cachedChar);
					}
					else {
						cachedChar = splitSudoku[counter].charAt(1);
						cachedNumericValue = Character.getNumericValue(cachedChar);
					}


					if(cachedChar == '"'){
						feld[x][y] = 0;
					}
					else if(cachedNumericValue > 9 ||cachedNumericValue < 1){
						throw new MalformedJSONException("Falsche Zahlen im Sudoku");
					}
					else{
						feld[x][y] = cachedNumericValue;
					}
					counter++;
				}
			}
		} 
		catch (Exception E) {
			//System.out.println(E);
			throw new MalformedJSONException("JSON konnte nicht erstellt werden da das Format nicht stimmt");
		}
		return true;
	}

	/**
	 * Set the sudoku from a variety of predefined Sudokus
	 * @param n the index of the sudoku to choose
	 */
	public void getPredefinedSet(int n) {
		HashMap<Integer, int[][]> PredefinedSudokus = new HashMap<Integer,int[][]>();

		int[][] PredefinedSudoku1 = { // Easy
			{0,4,8,  0,0,0,  0,7,0},
			{2,7,0,  6,9,0,  0,3,0},
			{0,3,0,  0,7,2,  0,4,0},

			{3,0,0,  0,0,0,  4,1,0},
			{0,9,0,  1,0,8,  7,0,3},
			{5,1,6,  0,4,0,  0,0,8},

			{0,2,0,  0,0,9,  1,0,0},
			{7,5,4,  2,0,1,  3,0,6},
			{1,0,0,  7,0,5,  0,0,0}
		};

		int[][] PredefinedSudoku2 = { // Medium
			{0,2,0,  0,0,1,  9,4,8},
			{8,1,0,  6,0,0,  0,0,0},
			{0,0,4,  0,2,7,  6,0,0},

			{1,7,0,  0,9,0,  0,0,3},
			{3,0,0,  0,0,0,  0,0,0},
			{0,4,8,  0,5,3,  0,0,0},

			{0,0,6,  1,0,0,  0,0,2},
			{0,0,0,  0,7,4,  0,0,6},
			{0,5,0,  9,0,0,  0,0,7}
		};

		int[][] PredefinedSudoku3 = { 
			{0,0,2,  0,0,0,  1,0,7},
			{7,4,5,  2,0,0,  0,0,0},
			{0,0,6,  0,0,5,  0,0,0},

			{0,5,7,  6,0,4,  0,3,1},
			{9,0,4,  6,0,3,  6,0,0},
			{6,1,0,  0,0,0,  0,0,9},

			{4,0,0,  0,0,0,  0,6,3},
			{0,0,0,  0,0,0,  9,0,8},
			{0,7,8,  0,0,0,  0,0,0}
		};

		PredefinedSudokus.put(1, PredefinedSudoku1);
		PredefinedSudokus.put(2, PredefinedSudoku2);
		PredefinedSudokus.put(3, PredefinedSudoku3);

		this.feld = PredefinedSudokus.get(n);
	}
	
	
	/**
	 * Check if the Sudoko is solved
	 * @return True if the Sudoku is solved; False if not
	 */
	public boolean checkIfSolved() { //->solve Funktion
		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++) {
				if(feld[x][y] < 1 && feld[x][y] > 9){
					return false;
				}
			}
		}
		return false;
	}
	
	
	public boolean solveSudoku() {
		long start = System.nanoTime();
		boolean solved = solve();
		long end = System.nanoTime();
		long total = (end - start) / 1000000;
		this.time = (double)total;
		return solved;
	}

	/**
	 * Solves the Sudoku using Backtracking
	 * @return if successfull true, else false
	 */
	private boolean solve() {
		// TODO
		return false;
	}
	
}
