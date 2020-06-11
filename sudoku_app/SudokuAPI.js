function queryAPI(queryUrl, dataBody = "") {
	
	//Variable for returning
	var result;

    $.ajax({
     type: "POST",
	 url: queryUrl,
	 async: false,
	 data: {jsonStringData : JSON.stringify(dataBody)}
	}).done(function (data) {
		
		//Parsing from string to JsonObject
		data = JSON.parse(data)

		result = data;
	});	

	//return JsonObject
	return result;
}

//Calls a POST-Request to the Backend-Server
//Solves and returns the "sudoku"-JsonObject as JsonObject
function solveSudoku(sudoku) {
	var result = queryAPI('http://localhost:8080/sudoku/solve', sudoku);
	return result;
}

//Calls a POST-Request to the Backend-Server
//Returns a new solveable Sudoku as JsonObject
function generateSudoku() {
	var result = queryAPI('http://localhost:8080/sudoku/generate', "");
	return result;
}