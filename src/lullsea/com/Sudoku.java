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

    boolean checkSolved(Number num){
        if(num.getAnswer() != 0)
            return true;
        
        for (int i = 0; i < 9; i++){
            // Row
            if (num.getAnswer() == table[i][num.y].getAnswer() && num.x != i)
                return false;
            // Column
            if (num.getAnswer() == table[num.x][i].getAnswer() && num.y != i)
                return false;
        }
        // TODO: check each quadrant

        return false;
    }

    /*
    * Needs to keep track of:
    * 1. It's position on the board
    * 2. Calculate potential values of its position
    */

    class Number{
        int x;
        int y;
        ArrayList<Integer> posssibleValues; //make a shallow copy of the possible values at x,y

        Number(int x, int y){
            this.x = x;
            this.y = y;
            for(int i = 1; i <= 9; i++)
                posssibleValues.add(i);
        }

        Number(Number num){
            this.x = num.x;
            this.y = num.y;
        }
        int getAnswer(){
            if(posssibleValues.size() != 1)
                return 0;
            else return posssibleValues.get(0);
        }
    }
}
