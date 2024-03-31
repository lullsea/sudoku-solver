import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame {
    SudokuFrame(){
        super("Sudoku Solver - lull");
        setLocation(0, 0);
        setSize(500, 500);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.black);
        setVisible(true);
    }
}