package lullsea.com;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/* 
 * The main GUI for our sudoku game
 */

public class SudokuFrame extends JFrame {
    private Grid grid = new Grid(new GridLayout(10,9,1,2));
    JTextField sudokuTable[][] = new JTextField[9][9];
    Sudoku sudoku = new Sudoku();

    SudokuFrame(){
        super("Sudoku Solver - lull");

        CreateSudokuTable();

        add(grid);
        // Frame attributes
        setLocation(0, 0);
        setSize(800, 650);
        // setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void syncTable(){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                if(!sudokuTable[x][y].getText().equals("")){
					sudoku.getNumber(x,y).answer = Integer.parseInt(sudokuTable[x][y].getText());
					sudokuTable[x][y].setForeground(Color.RED);

                }
				else {
					sudoku.getNumber(x,y).answer = 0;
					sudokuTable[x][y].setForeground(Color.BLACK);
				}

            }
        }
    }
    public void syncGUI(){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++){
                if (sudoku.getNumber(j,i).isSolved()){
                    sudokuTable[j][i].setText(String.valueOf(sudoku.getNumber(j,i).answer));			
                }
            }
    }

    private void performSolve(){
        syncTable();
        if(!sudoku.solve(0, 0))
            JOptionPane.showMessageDialog(null, "This puzzle cannot be solved.");
        else syncGUI();
    }

    private void performClear(){
        for(int i = 0; i < 9; i ++)
            for(int j = 0; j < 9; j++)
                sudokuTable[j][i].setText("");
        syncTable();
    }

    private void performRandom(){
        int[][][] arr = {
            {
                {0, 0, 6, 0, 5, 0, 9, 0, 0},
                {1, 0, 0, 0, 6, 0, 0, 4, 2},
                {7, 0, 0, 0, 8, 0, 2, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 4, 0},
                {0, 0, 8, 0, 0, 0, 2, 0, 0},
                {0, 3, 0, 0, 6, 0, 0, 0, 0},
                {9, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0, 7, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0}
            },
            {
                {0, 8, 0, 4, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 6, 0},
                {4, 0, 0, 0, 5, 0, 0, 0, 9},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 0, 0, 3, 0, 0}
            },
            {
                {0, 3, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 7, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 0, 0, 0, 0, 6, 0},
                {0, 0, 0, 0, 0, 9, 0, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 0},
                {9, 0, 0, 0, 0, 0, 0, 1, 0}
            }
        };
        Random rand = new Random();
        int x = rand.nextInt(arr.length);

        sudoku = new Sudoku(arr[x]);
        syncGUI();
    }

    private void CreateSudokuTable(){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++){
                sudokuTable[j][i] = new JTextField();
                // TODO: TextField Stylings here
                sudokuTable[j][i].setHorizontalAlignment(JTextField.CENTER);
                // Add to grid
                grid.add(sudokuTable[j][i]);
            }
        JButton randomBtn = new JButton("Random");
        JButton solveBtn = new JButton("Solve");
        JButton clearBtn = new JButton("Clear");

        solveBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                performSolve();
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                performClear();
            }
        });
        randomBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                performClear();
                performRandom();
                syncTable();
            }
        });


        grid.add(new JLabel(""));
        grid.add(new JLabel(""));
        grid.add(randomBtn);
        grid.add(new JLabel(""));
        grid.add(solveBtn);
        grid.add(new JLabel(""));
        grid.add(clearBtn);
        grid.add(new JLabel(""));
        grid.add(new JLabel(""));
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
            g.fillRect(getWidth()/3 - 2,2,3,getHeight() - 65);
            g.fillRect(2*getWidth()/3 - 2,2,3,getHeight() - 65);
            g.fillRect(4,getHeight()/3 - 23,getWidth() - 9,4);
            g.fillRect(4,2*getHeight()/3 - 43,getWidth() - 9,4);
        }
	}
}