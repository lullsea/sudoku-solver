package lullsea.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* 
 * The main GUI for our sudoku game
 */

public class SudokuFrame extends JFrame {
    private Grid grid = new Grid(new GridLayout(9,9,1,2));
    JTextField sudokuTable[][] = new JTextField[9][9];
    Sudoku sudoku = new Sudoku();

    SudokuFrame(){
        super("Sudoku Solver - lull");

        CreateSudokuTable();

        add(grid);
        // Frame attributes
        setLocation(0, 0);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void syncTable(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(!sudokuTable[j][i].getText().equals("")){
					sudoku.getNumber(j,i).answer = Integer.parseInt(sudokuTable[j][i].getText());
					sudokuTable[j][i].setForeground(Color.RED);

                }
				else {
					sudoku.getNumber(j,i).answer = 0;
					sudokuTable[j][i].setForeground(Color.BLACK);
				}

				if (sudoku.getNumber(j,i).isSolved())
					sudokuTable[j][i].setText(String.valueOf(sudoku.getNumber(j,i).answer));			
            }
        }
    }

    private void CreateSudokuTable(){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++){
                sudokuTable[j][i] = new JTextField("i: " + (j+i));
                // TODO: TextField Stylings here
                // Add to grid
                grid.add(sudokuTable[j][i]);
            }
    }
    // Custom grid panel to house all our textfields
    private class Grid extends JPanel{
        private static final long serialVersionUID = -6157041650150998205L;

        Grid(GridLayout layout){
            super(layout);
        }
        
        //draw lines for 3x3 quardrants
        public void paintComponent(Graphics g){
            g.setColor(new Color(13380920));
            g.fillRect(getWidth()/3 - 1,2,3,getHeight() - 4);
            g.fillRect(2*getWidth()/3 - 1,2,3,getHeight() - 4);
            g.fillRect(4,getHeight()/3 - 1,getWidth() - 9,4);
            g.fillRect(4,2*getHeight()/3 - 1,getWidth() - 9,4);
        }
	}
}