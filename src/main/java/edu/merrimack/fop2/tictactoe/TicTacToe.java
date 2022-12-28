/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package edu.merrimack.fop2.tictactoe;

import java.util.Scanner;

/**
 *
 * @author kmacr
 */
public class TicTacToe {

    public static void main(String[] args) {

        Board newBoard = new Board();
        newBoard.draw();

        Scanner myScanner = new Scanner(System.in);
        int menuSelection;

        do {
            do {
                System.out.println("");
                System.out.println("Tic Tac Toe");
                System.out.println("1. Play against Human");
                System.out.println("2. Play against Computer (Human: x, Computer: 0");
                System.out.println("3. Play against Computer (Computer: x, Human: 0");
                System.out.println("4. End Program");

                System.out.println("Enter a menu option");
                menuSelection = myScanner.nextInt();
                if (menuSelection < 1 || menuSelection > 4) {
                    System.out.println("Invalid Choice");
                    System.out.println("");
                    System.out.println("");

                }
            } while (menuSelection < 1 || menuSelection > 4);

            if (menuSelection == 1) {
                //play against human

                //Why does a or statement not work? 
                while (newBoard.determineBoardStatus() != BoardStatus.O_WINS
                        && newBoard.determineBoardStatus() != BoardStatus.X_WINS ) {
                    //Player x moves
                    newBoard.draw();
                    System.out.println("Player X enter a column (0-2)");
                    int col = myScanner.nextInt();
                    System.out.println("Player X enter a row (0-2)");
                    int row = myScanner.nextInt();
                    while (col>2 || row >2 || newBoard.getSquare(col, row) == 'X' || newBoard.getSquare(col, row) == 'O') {
                        System.out.println("Row/column already occupied or invalid");
                        System.out.println("Player X enter a column");
                        col = myScanner.nextInt();
                        System.out.println("Player X enter a row");
                        row = myScanner.nextInt();
                    }
                    newBoard.setSquare(col, row, 'X');
                    newBoard.determineBoardStatus();
                    System.out.println(newBoard.determineBoardStatus());
                    if (newBoard.determineBoardStatus() == BoardStatus.X_WINS) {
                        //System.out.println("X Wins");
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    } else if (newBoard.determineBoardStatus() == BoardStatus.DRAW) {
                        newBoard.draw();
                        break;
                    }

                    //Player O moves
                    newBoard.draw();
                    System.out.println("Player O enter a column (0-2)");
                    int col2 = myScanner.nextInt();
                    System.out.println("Player O enter a row (0-2)");
                    int row2 = myScanner.nextInt();
                    while (col>2 || row >2 || newBoard.getSquare(col2, row2) == 'X' || newBoard.getSquare(col2, row2) == 'O') {
                        System.out.println("Row/column already occupied or invalid");
                        System.out.println("Player O enter a column (0-2)");
                        col2 = myScanner.nextInt();
                        System.out.println("Player O enter a row (0-2)");
                        row2 = myScanner.nextInt();
                    }
                    newBoard.setSquare(col2, row2, 'O');
                    newBoard.determineBoardStatus();
                    System.out.println(newBoard.determineBoardStatus());
                    if (newBoard.determineBoardStatus() == BoardStatus.O_WINS) {
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    } else if (newBoard.determineBoardStatus() == BoardStatus.DRAW) {
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    }
                }

            } else if (menuSelection == 2) {

                while (newBoard.determineBoardStatus() == BoardStatus.UNFINISHED
                        ) {

                    newBoard.draw();

                    System.out.println("Player X enter a column (0-2)");
                    int col = myScanner.nextInt();
                    System.out.println("Player X enter a row (0-2)");
                    int row = myScanner.nextInt();
                    while (col>2 || row >2 || newBoard.getSquare(col, row) == 'X' || newBoard.getSquare(col, row) == 'O') {
                        System.out.println("Row/column already occupied");
                        System.out.println("Player X enter a column (0-2)");
                        col = myScanner.nextInt();
                        System.out.println("Player X enter a row (0-2)");
                        row = myScanner.nextInt();
                    }
                    newBoard.setSquare(col, row, 'X');
                    newBoard.determineBoardStatus();
                    System.out.println(newBoard.determineBoardStatus());
                    if (newBoard.determineBoardStatus() == BoardStatus.X_WINS) {
                        //System.out.println("X Wins");
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    } else if (newBoard.determineBoardStatus() == BoardStatus.DRAW) {
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    }

                        //End user X move ^^
                        
                        Move oMove = newBoard.makeMove('O');
                        int compCol = oMove.getColumn();
                        int compRow = oMove.getRow();
                        System.out.println(compCol);
                        System.out.println(compRow);
                        newBoard.setSquare(compCol, compRow, 'O');
                }
                    if (newBoard.determineBoardStatus() == BoardStatus.O_WINS){
                        System.out.println(" ");
                        System.out.println(newBoard.determineBoardStatus());
                        newBoard.draw();
                        newBoard = new Board();
                    }
                    if(newBoard.determineBoardStatus() == BoardStatus.DRAW){
                        System.out.println(" ");
                        System.out.println(newBoard.determineBoardStatus());
                        newBoard.draw();
                        newBoard = new Board();
                        
                    }
                    
            } else if (menuSelection == 3) {
                
                while (newBoard.determineBoardStatus() == BoardStatus.UNFINISHED) 
                {
                    
                    
                    Move oMove = newBoard.makeMove('X');
                        int compCol = oMove.getColumn();
                        int compRow = oMove.getRow();
                        System.out.println(compCol);
                        System.out.println(compRow);
                        newBoard.setSquare(compCol, compRow, 'X');
                        
                        
                    if (newBoard.determineBoardStatus() == BoardStatus.X_WINS) {
                        newBoard.draw();
                        System.out.println(newBoard.determineBoardStatus());
                        newBoard = new Board();
                        break;
                    }
                    if (newBoard.determineBoardStatus() == BoardStatus.DRAW) {
                        newBoard.draw();
                        System.out.println(newBoard.determineBoardStatus());
                        newBoard = new Board();
                        break;
                    }
                    else if (newBoard.determineBoardStatus() == BoardStatus.UNFINISHED){
                        newBoard.draw();
                    }

                    

                    System.out.println("Player O enter a column (0-2)");
                    int col = myScanner.nextInt();
                    System.out.println("Player O enter a row");
                    int row = myScanner.nextInt();
                    while (col>2 || row >2 || newBoard.getSquare(col, row) == 'X' || newBoard.getSquare(col, row) == 'O') {
                        System.out.println("Row/column already occupied (0-2)");
                        System.out.println("Player O enter a column (0-2)");
                        col = myScanner.nextInt();
                        System.out.println("Player O enter a row (0-2)");
                        row = myScanner.nextInt();
                    }
                    newBoard.setSquare(col, row, 'O');
                    newBoard.determineBoardStatus();
                    System.out.println(newBoard.determineBoardStatus());
                    if (newBoard.determineBoardStatus() == BoardStatus.O_WINS) {
                        //System.out.println("X Wins");
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    } else if (newBoard.determineBoardStatus() == BoardStatus.DRAW) {
                        newBoard.draw();
                        newBoard = new Board();
                        break;
                    }
                }
                    //newBoard.draw();
                    //System.out.println(newBoard.determineBoardStatus());

            }

        } while (menuSelection != 4);

    }

}
