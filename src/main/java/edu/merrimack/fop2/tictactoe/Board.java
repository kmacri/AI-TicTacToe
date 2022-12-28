/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.merrimack.fop2.tictactoe;

/**
 *
 * @author kmacr
 */
public class Board {

    //board
    private char[][] square;

    //constructor, initializes a 3x3 aray
    public Board() {
        this.square = new char[3][3];
    }

    public char getSquare(int column, int row) {
        //returns the item at col/row which is a char
        return square[column][row];
    }

    /////////************Ask************
    public void setSquare(int column, int row, char c) {
        //sets item at Square[index col][index row] to the char passed in 
        this.square[column][row] = c;
    }
    
    /**
     * determines the board status
     * @return 
     */

    public BoardStatus determineBoardStatus() {
        int count = 0;

        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                //***************0 means null for char array?************
                if (square[i][j] == 0) {
                    count += 1;
                }
            }
        }

        //[col][row]
        //Verticals 
        if (square[0][0] == 'X' && square[0][1] == 'X' && square[0][2] == 'X') {
            return BoardStatus.X_WINS;
        } else if (square[1][0] == 'X' && square[1][1] == 'X' && square[1][2] == 'X') {
            return BoardStatus.X_WINS;
        } else if (square[2][0] == 'X' && square[2][1] == 'X' && square[2][2] == 'X') {
            return BoardStatus.X_WINS;
        } //diagonal 1
        else if (square[0][0] == 'X' && square[1][1] == 'X' && square[2][2] == 'X') {
            return BoardStatus.X_WINS;
        } //diagonal 2
        else if (square[0][2] == 'X' && square[1][1] == 'X' && square[2][0] == 'X') {
            return BoardStatus.X_WINS;
        } //Horizontals
        else if (square[0][0] == 'X' && square[1][0] == 'X' && square[2][0] == 'X') {
            return BoardStatus.X_WINS;
        } else if (square[0][1] == 'X' && square[1][1] == 'X' && square[2][1] == 'X') {
            return BoardStatus.X_WINS;
        } else if (square[0][2] == 'X' && square[1][2] == 'X' && square[2][2] == 'X') {
            return BoardStatus.X_WINS;
        } //Do that same thing for 0??
        else if (square[0][0] == 'O' && square[0][1] == 'O' && square[0][2] == 'O') {
            return BoardStatus.O_WINS;
        } else if (square[1][0] == 'O' && square[1][1] == 'O' && square[1][2] == 'O') {
            return BoardStatus.O_WINS;
        } else if (square[2][0] == 'O' && square[2][1] == 'O' && square[2][2] == 'O') {
            return BoardStatus.O_WINS;
        } //diagonal 1
        else if (square[0][0] == 'O' && square[1][1] == 'O' && square[2][2] == 'O') {
            return BoardStatus.O_WINS;
        } //diagonal 2
        else if (square[0][2] == 'O' && square[1][1] == 'O' && square[2][0] == 'O') {
            return BoardStatus.O_WINS;
        } //verticals
        else if (square[0][0] == 'O' && square[1][0] == 'O' && square[2][0] == 'O') {
            return BoardStatus.O_WINS;
        } else if (square[0][1] == 'O' && square[1][1] == 'O' && square[2][1] == 'O') {
            return BoardStatus.O_WINS;
        } else if (square[0][2] == 'O' && square[1][2] == 'O' && square[2][2] == 'O') {
            return BoardStatus.O_WINS;
        } //Not all elements are filled 
        else if (count == 0) {
            return BoardStatus.DRAW;
        } else if (count < 9) {
            return BoardStatus.UNFINISHED;
        }

        return BoardStatus.UNFINISHED;

    }

    /**
     * draws the board 
     */
    public void draw() {

        //[col][row]
        String horizontal = "---+---+---";
        String firstRow = square[0][0] + "   | " + square[1][0] + "  | " + square[2][0];
        String secondRow = square[0][1] + "   | " + square[1][1] + "  | " + square[2][1];
        String thirdRow = square[0][2] + "   | " + square[1][2] + "  | " + square[2][2];

        System.out.println(firstRow);
        System.out.println(horizontal);
        System.out.println(secondRow);
        System.out.println(horizontal);
        System.out.println(thirdRow);

    }
    
    /**
     * Returns a move for the computer based on the current state of the board
     * @param currentPlayer
     * @return 
     */

    //char current player is the curent player... x or 0
    public Move makeMove(char currentPlayer) {
        
        //Initialize move obj
        Move bestMove = new Move();
        BoardStatus status;
        Move tempMove = new Move();
        Move move;
        
        //switch between players
        char opponent = ' ';
        if (currentPlayer == 'X') {
            opponent = 'O';
        }
        if (currentPlayer == 'O') {
            opponent = 'X';
        }
        
        
        //Iterate through all elements of the array
        //place currentPlayer at first available square
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++){
                if (getSquare(i, j) == 0) {
                    square[i][j] = currentPlayer;
                    status = determineBoardStatus();
                    
                    //move returns unfinihsed, recursively call makeMove
                    if (status == BoardStatus.UNFINISHED){
                        move = makeMove(opponent);
                        status = move.getBoardStatus();
                        square[i][j] = 0;
                    }
                    
                    //if current player is x and move results in x wins, make that move. 
                    if (status == BoardStatus.X_WINS && currentPlayer == 'X'){
                            
                        bestMove.setColumn(i);
                        bestMove.setRow(j);
                        bestMove.setBoardStatus(status);
                        square[i][j] = 0;
                        return bestMove;
                        
                    }
                    
                    
                    //if current player is 0 and move results in 0 wins, make that move.
                    if (status == BoardStatus.O_WINS && currentPlayer == 'O'){
                        
                        bestMove.setColumn(i);
                        bestMove.setRow(j);
                        bestMove.setBoardStatus(status);
                        square[i][j] = 0;
                        return bestMove;
                    }
                    
                    
                    //if status is Draw
                    else if (status == BoardStatus.DRAW){
                        tempMove.setColumn(i);
                        tempMove.setRow(j);
                        tempMove.setBoardStatus(status);
                        square[i][j] = 0;
                    
                    }
                }
                
            }
        }
        
        return tempMove;

    }

}
