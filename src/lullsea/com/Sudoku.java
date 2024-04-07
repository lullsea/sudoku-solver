package lullsea.com;
import java.util.ArrayList;

/*
 * Handles the logic for our sudoku game
 */
public class Sudoku {
    Number[][] table = new Number[9][9];

    Sudoku(){
        // Populate the table
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j ++)
                table[i][j] = new Number(i, j);
    }

    Sudoku(int[][] tab){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                table[i][j] = new Number(i, j);
                table[i][j].answer = tab[i][j];
            }
        }
    }

    public Number getNumber(int x, int y){
        return table[x][y]; 
    }

    boolean checkSolved(Number num){
        if(!num.isSolved())
            return true;
        
        for (int i = 0; i < 9; i++){
            // Row
            if (num.answer == table[i][num.y].answer && num.x != i)
                return false;
            // Column
            if (num.answer == table[num.x][i].answer && num.y != i)
                return false;
        }

        for(int y = num.y; y < num.y + 3; y++)
            for(int x = num.x; x < num.x + 3; x++){
                // No reason to check for the same position
                if(num.x != x && num.y != y && y < 9 && x < 9)
                    if(num.answer == table[x][y].answer)
                        return false;

            }

        // If it passes all of the checks returns true
        return true;
    }

    /*
    * Needs to keep track of:
    * 1. It's position on the board
    * 2. Calculate potential values of its position
    */

    // Crude implementation
    Tuple<Integer, Integer> incrementPosition(int x, int y){
        if(x + 1 > 8){
            y+=1;
            x = 0;
        }else x+=1;

        return new Tuple<Integer,Integer>(x,y);
    }

    // Recursively go through and check every position
    boolean solve(int x, int y){
        if(x == 0 && y == 9)
            return true;

        Tuple<Integer, Integer> newPosition = incrementPosition(x, y);

        if (table[x][y].isSolved())
	    	return solve(newPosition.x, newPosition.y);

	    for (int possibleValue : table[x][y].possibleValues) {
	    	Number tmp = new Number(table[x][y]);
            tmp.answer = possibleValue;

		    if (checkSolved(tmp)) {
		        table[x][y] = tmp; 
		       	if (solve(newPosition.x, newPosition.y)) 
		       		return true;
		    }
	    }
        // Reset
	    table[x][y].answer = 0;

        return false;
    }

    class Number{
        int x;
        int y;
        ArrayList<Integer> possibleValues = new ArrayList<Integer>(); 
        int answer;

        Number(int x, int y){
            this.x = x;
            this.y = y;
            this.answer = 0;
            for(int i = 1; i <= 9; i++)
                possibleValues.add(i);
        }

        Number(Number num){
            this.x = num.x;
            this.y = num.y;
            for(int i = 1; i <= 9; i++)
                possibleValues.add(i);
            this.answer = 0;
        }

        boolean isSolved(){
            return answer != 0;
        }
    }
}
