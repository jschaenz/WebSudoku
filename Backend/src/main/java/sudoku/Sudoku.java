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

	public String toJSON() {
		return "{\"sudokuJSON\":"
				+ Arrays.deepToString(this.feld).replace("], ", "],").replace('"',' ').replace("[","[ ").replace("]"," ]")
				+ ",\"time\": " + this.time+ ",\"status\": " + this.status + "}";
	}

	public boolean createFromJSON(String sudokuJSON) throws MalformedJSONException {
		// {"sudokuJSON":[["X","X","X","X","X","X","X","X","X"],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""],["","","","","","","","",""]]}
		System.out.println("Input String: "+sudokuJSON);
		String[] splitSudoku = sudokuJSON.split(",");
		int counter = 0;
		char cachedChar;
		int cachedNumericValue;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (counter == 0) { // 1. Durchlauf 0|0, da sudokuJSON:"" noch davor steht
					if (splitSudoku[counter].length() > 19) {
						return false;
					}
					cachedChar = splitSudoku[counter].charAt(17);
					cachedNumericValue = Character.getNumericValue(cachedChar);
				}

				else if (counter == 80) { // 80, ist länger als der Rest			
					if (splitSudoku[counter].length() > 6) {
						return false;
					}
					cachedChar = splitSudoku[counter].charAt(1);
					cachedNumericValue = Character.getNumericValue(cachedChar);
				}

				else if (counter % 9 == 8) { // 8 17 26 35 44 53 62 71, jede 9. Zahl um -1 versetzt, da noch ] dabei ist				
					if (splitSudoku[counter].length() > 4) {
						return false;
					}
					cachedChar = splitSudoku[counter].charAt(1);
					cachedNumericValue = Character.getNumericValue(cachedChar);
				}

				else if (counter % 9 == 0) { // jede 9. Zahl, da noch [ dabei ist
					if (splitSudoku[counter].length() > 4) {
						return false;
					}
					cachedChar = splitSudoku[counter].charAt(2);
					cachedNumericValue = Character.getNumericValue(cachedChar);
				}

				else {
					if (splitSudoku[counter].length() > 3) { // jede andere Zahl
						return false;
					}
					cachedChar = splitSudoku[counter].charAt(1);
					cachedNumericValue = Character.getNumericValue(cachedChar);
				}


				if (cachedChar == '"') {
					this.feld[x][y] = 0;
				} else {
					this.feld[x][y] = cachedNumericValue;
				}
				counter++;
			}
		}
		return true;
	}


	public void getPredefinedSet(int n) {
		HashMap<Integer, int[][]> PredefinedSudokus = new HashMap<Integer, int[][]>();

		int[][] PredefinedSudoku1 = {
				{ 0, 4, 8, 0, 0, 0, 0, 7, 0 }, 
				{ 2, 7, 0, 6, 9, 0, 0, 3, 0 }, 
				{ 0, 3, 0, 0, 7, 2, 0, 4, 0 },
				{ 3, 0, 0, 0, 0, 0, 4, 1, 0 }, 
				{ 0, 9, 0, 1, 0, 8, 7, 0, 3 }, 
				{ 5, 1, 6, 0, 4, 0, 0, 0, 8 },
				{ 0, 2, 0, 0, 0, 9, 1, 0, 0 }, 
				{ 7, 5, 4, 2, 0, 1, 3, 0, 6 }, 
				{ 1, 0, 0, 7, 0, 5, 0, 0, 0 } }; 

		int[][] PredefinedSudoku2 = {
				{ 0, 2, 0, 0, 0, 1, 9, 4, 8 }, 
				{ 8, 1, 0, 6, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 4, 0, 2, 7, 6, 0, 0 },
				{ 1, 7, 0, 0, 9, 0, 0, 0, 3 }, 
				{ 3, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 4, 8, 0, 5, 3, 0, 0, 0 },
				{ 0, 0, 6, 1, 0, 0, 0, 0, 2 }, 
				{ 0, 0, 0, 0, 7, 4, 0, 0, 6 }, 
				{ 0, 5, 0, 9, 0, 0, 0, 0, 7 } };

		int[][] PredefinedSudoku3 = { 
				{ 0, 2, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 6, 0, 0, 0, 0, 3 },
				{ 0, 7, 4, 0, 8, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 3, 0, 0, 2 }, 
				{ 0, 8, 0, 0, 4, 0, 0, 1, 0 }, 
				{ 6, 0, 0, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 7, 8, 0 }, 
				{ 5, 0, 0, 0, 0, 9, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 4, 0 } };

		PredefinedSudokus.put(1, PredefinedSudoku1);
		PredefinedSudokus.put(2, PredefinedSudoku2);
		PredefinedSudokus.put(3, PredefinedSudoku3);

		this.feld = PredefinedSudokus.get(n);
	}

	private boolean checkIfAllowed(int y, int x, int value) {
		// Prüft ob Zahl value an die Stelle eingefügt werden darf
		int xrange = 0;
		int yrange = 0;
		
		//Prüfen ob Zahl zwischen 1 und 9
		if(value > 9 || value < 1) {
			return false;
		}
		
		// Prüfen ob Zahl bereits in Zeile
		for (int i = 0; i < 9; i++) {
			if (feld[y][i] == value) {
				return false;
			}
		}

		// Prüfen ob Zahl bereits in Spalte
		for (int i = 0; i < 9; i++) {
			if (feld[i][x] == value) {
				return false;
			}
		}

		// 3x3 Feld bestimmen
		// für x
		if (x <= 2) {
			xrange = 0;
		} else if (x <= 5) {
			xrange = 3;
		} else {
			xrange = 6;
		}
		// für y
		if (y <= 2) {
			yrange = 0;
		} else if (y <= 5) {
			yrange = 3;
		} else {
			yrange = 6;
		}

		// durch 3x3 Felder iterieren und nach Duplikat schauen
		for (int i = yrange; i < yrange + 3; i++) {
			for (int j = xrange; j < xrange + 3; j++) {
				if (feld[i][j] == value) {
					return false;
				}
			}
		}
		// wenn die Zahl nicht bereits in Zeile Spalte oder 3x3 Feld, ist das Einsetzen
		// erlaubt
		return true;

	}


	private boolean checkIfValid() {
		/* Prüft alle Felder mit einer eingtragenen Zahl, ob diese überhaupt gültig ist.
		*/
		int tmp;
		for(int y=0; y<9; y++){
			for(int x=0; x<9; x++){
				if(feld[y][x]!=0) {
					tmp = feld[y][x];
					feld[y][x]=0;
					if(checkIfAllowed(y,x,tmp)== false) {
						feld[y][x] = tmp;
						return false;
					}
					feld[y][x] = tmp;
					
				}
			}	
		}
		return true;
	}

	public boolean checkIfSolved() {
		for (int i = 0; i < 9; i++) { // i ist Zeile
			for (int j = 0; j < 9; j++) { // j ist Spalte

				int value = feld[i][j];
				feld[i][j] = 0; // Feld wird temporÃ¤r auf 0 gesetzt, damit checkrules funktioniert
				if (checkIfAllowed(i, j, value) == false) {
					feld[i][j] = value; // Feld wird wieder auf seinen alten Wert gesetzt
					return false;
				}
				feld[i][j] = value; // Feld wird wieder auf seinen alten Wert gesetzt
			}
		}
		this.status = 200; // "gelÃster Status"
		return true;
	}

	public boolean solveSudoku() {
		long start = System.nanoTime();
		boolean solved = solve(true);
		long end = System.nanoTime();
		long total = (end - start) / 1000000;
		this.time = (double) total;
		return solved;
	}


	private boolean solve(boolean start){
		//Lösen des sudokus
		//Iterieren über alle Felder und prüfen ob eines davon eine 0 enthält
		
		if(start) {
			if(checkIfValid() == false) {return false;}
		}
		
		for(int y=0; y<9; y++){
			for(int x=0; x<9; x++){
		
				if(feld[y][x]==0) { 
					// Zahlen von 0-9 durchgehen und wenn Zahl erlaubt ist diese ins Feld setzten
					for(int n=1; n<=9; n++){ 
						if(checkIfAllowed(y,x,n)){ 
							feld[y][x] = n;
							//solve erneut ausführen um nächstes Feld mit 0 zu berarbeiten
							if (solve(false)) { //solveable soll bei rekursivem Aufruf nicht ausgeführt werden
								return true; 
							}
							else {
							//Feld wird wieder auf 0 gesetzt wenn kein Eintrag möglich und wieder ein Feld zurückgesprungen werden muss
							feld[y][x] = 0; 
							}
						}
						
					}
					
					return false;
				}
			}
		}
		// wenn alle Felder belegt sind, wird nicht mehr ins if statement gesprungen und true, also gelöst zurückgegeben
		return true; 
		
		
	}
}
