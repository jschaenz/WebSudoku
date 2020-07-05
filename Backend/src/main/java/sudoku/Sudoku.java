package sudoku;

import java.util.Arrays;

import exceptions.MalformedJSONException;

public class Sudoku {
	
	protected int sudoku[][];
	protected int status;
	protected String time;
	// Class Constructor
	public Sudoku() {
		this.sudoku = new int[9][9];
		this.status = 200;
		this.time = "";
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
	 "Sudoku, die benötigte Zeit und den Status"
	 */ 
	public String toJSON() {
		return "{\n\"sudokuJSON\":\n[\n" + Arrays.deepToString(this.sudoku).replace("], ", "],\n").replace("[[","[").replace("]]","]") + "\n],\n\"time\": "+ this.time + ",\n\"status\": " + this.status + "\n}";
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
	public boolean createFromJSON(String sudokuJSON) throws MalformedJSONException {
		String[] splitSudoku = sudokuJSON.split(",");
		int c = 0;
		try {
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 9; y++) {
					if (c == 0) {
						sudoku[x][y] = Character.getNumericValue(splitSudoku[c].charAt(20));
						c++;
					} 
					else if (c % 9 == 0) {
						sudoku[x][y] = Character.getNumericValue(splitSudoku[c].charAt(3));
						c++;
					}
					 else {
						sudoku[x][y] = Character.getNumericValue(splitSudoku[c].charAt(1));
						c++;
					}
				}
			}
		} 
		catch (Error E) {
			System.out.println(E);
			throw new MalformedJSONException("JSON konnte nicht erstellt werden da das Format nicht stimmt");
		}
		return true;
	}

	/**
	 * Set the sudoku from a variety of predefined Sudokus
	 * @param n the index of the sudoku to choose
	 */
	public void getPredefinedSet(int n) {
		//TODO
	}
	
	
	/**
	 * Check if the Sudoko is solved
	 * @return True if the Sudoku is solved; False if not
	 */
	public boolean checkIfSolved() {
		//TODO
		return false;
	}
	
	/**
	 * Solves the Sudoku using Backtracking
	 * @return if successfull true, else false
	 */
	public boolean solveSudoku() {
		//TODO
		return false;
	}
	
}