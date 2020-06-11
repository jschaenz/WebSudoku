function generate_button() {
    removeloesbar();
    let sudoku = generateSudoku()
    let table = document.getElementById('sudoku');

    for (let r = 0, n = table.rows.length; r < n; r++) {
        for (let c = 0, m = table.rows[r].cells.length; c < m; c++) {
            if (sudoku.sudokuJSON[r][c] === 0) { //fix damit keine 0 eingetragen wird -> sieht schöner aus
                table.rows[r].cells[c].firstChild.value = "";
            }
            else {
                table.rows[r].cells[c].firstChild.value = sudoku.sudokuJSON[r][c];
                table.rows[r].cells[c].firstChild.readOnly = true;
            }
        }
    }
    //->readonly
}





function solve_button() {
    removeloesbar();
    let sudoku = { "sudokuJSON": GetCellValues() }
    let solvedSudoku = solveSudoku(sudoku);
    let table = document.getElementById('sudoku');

    console.log(solvedSudoku);

    if (solvedSudoku.status === 500) {
        unloesbar();
    }
    else {
        for (let r = 0, n = table.rows.length; r < n; r++) {
            for (let c = 0, m = table.rows[r].cells.length; c < m; c++) {
                table.rows[r].cells[c].firstChild.value = solvedSudoku.sudokuJSON[r][c];
            }
        }
        loesbar();
    }

    let zeit = solvedSudoku.time;
    document.getElementById("sekunden").innerText = zeit + " Sekunden";
}





function check_button() {
    removeloesbar();
    let sudoku = { "sudokuJSON": GetCellValues() }
    let solvedSudoku = solveSudoku(sudoku);
    if (solvedSudoku.status === 500) {
        unloesbar();
    }
    else{
        loesbar();
    }
}




//setzt alle Werte in den Zellen auf ""
function clearfeld() {
    removeloesbar();
    let table = document.getElementById('sudoku');
    for (let r = 0, n = table.rows.length; r < n; r++) {
        for (let c = 0, m = table.rows[r].cells.length; c < m; c++) {
            table.rows[r].cells[c].firstChild.value = "";
        }
    }
    return null;
}




//holt alle Werte in den Zellen, schiebt sie dann in die beiden Arrays
function GetCellValues() {
    let table = document.getElementById('sudoku');
    let JObjectArray = [];
    let JObjectRow = [];
    for (let r = 0, n = table.rows.length; r < n; r++) {
        for (let c = 0; c < 9; c++) {
            JObjectRow.push(table.rows[r].cells[c].firstChild.value);
        }
        JObjectArray.push(JObjectRow);
        JObjectRow = [];
    }
    return (JObjectArray);
}



function unloesbar() {
    let box = document.getElementById("loesbarAnzeige");
    box.style.backgroundColor="red";
    return null;
}

function loesbar(){
    let box = document.getElementById("loesbarAnzeige");
    box.style.backgroundColor="green";
    return null;
}

function removeloesbar(){
    let box = document.getElementById("loesbarAnzeige");
    box.style.backgroundColor="lightgray";
    document.getElementById("sekunden").innerText = ""; 
    let table = document.getElementById('sudoku'); //entfernt readonly tag
    for (let r = 0, n = table.rows.length; r < n; r++) {
        for (let c = 0, m = table.rows[r].cells.length; c < m; c++) {
            table.rows[r].cells[c].firstChild.readOnly = false;
        }
    }
    return null;
}