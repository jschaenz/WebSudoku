package sudoku;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SudokuController {
	
	@CrossOrigin(origins = "*")
	@PostMapping("/sudoku/solve")
	public String solveSudoku(@RequestParam String jsonStringData){		
		Sudoku s = new Sudoku();
	
		if (s.createFromJSON(jsonStringData)) {
			s.solveSudoku();
			
			if (s.checkIfSolved()) {
				return s.toJSON(); 
			} else {
				System.out.println("Could not solve Sudoku!");
				return "{\"status\": 500, \"info\": \"Could not solve Sudoku!\"}";
			}
			
		} else {
			System.out.println("Could not create sudoku from JSON!");
			return "{\"status\": 500, \"info\": \"Could not create Sudoku from JSON! Check your JSON!\"}";
		}
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/sudoku/generate")
	public String generateSudoku() {
		Random r = new Random();
		Sudoku s = new Sudoku();
		s.getPredefinedSet(r.nextInt(3) + 1);
		return s.toJSON();
	}
}
