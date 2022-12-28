/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.merrimack.fop2.tictactoe;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Some tests for Tic Tac Toe
 * 
 * 
 * @author Ed Grzyb
 */
public class BoardTest {
    
    public BoardTest() {
    }
    @Test
    public void testGetSquareEmptyBoard() {
        int column = 0;
        int row = 0;
        Board board = new Board();
        char expResult = 0;
        char result = board.getSquare(column, row);
        assertEquals(expResult, result);
    }
    @Test
    public void testSetSquare() {
        int column = 0;
        int row = 0;
        char c = 'X';
        Board board = new Board();
        board.setSquare(column, row, c);
        assertEquals('X', board.getSquare(column, row));
    }
    @Test
    public void testDetermineBoardStatusEmptyBoard() {
        Board board = new Board();
        BoardStatus expResult = BoardStatus.UNFINISHED;
        BoardStatus result = board.determineBoardStatus();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDetermineBoardStatusDraw() {
        Board board = new Board();
        board.setSquare(0, 0, 'X');
        board.setSquare(1, 0, 'O');
        board.setSquare(2, 0, 'X');
        board.setSquare(0, 1, 'O');
        board.setSquare(1, 1, 'X');
        board.setSquare(2, 1, 'X');
        board.setSquare(0, 2, 'O');
        board.setSquare(1, 2, 'X');
        board.setSquare(2, 2, 'O');
        board.draw();        
        BoardStatus expResult = BoardStatus.DRAW;
        BoardStatus result = board.determineBoardStatus();
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testDetermineBoardStatusXWins() {
        Board board = new Board();
        board.setSquare(0, 0, 'X');
        board.setSquare(1, 0, 'X');
        board.setSquare(2, 0, 'X');
        board.setSquare(0, 1, 'O');
        board.setSquare(1, 1, 'O');
        board.setSquare(2, 1, 'X');
        board.setSquare(0, 2, 'O');
        board.setSquare(1, 2, 'X');
        board.setSquare(2, 2, 'O');
        board.draw();
        BoardStatus expResult = BoardStatus.X_WINS;
        BoardStatus result = board.determineBoardStatus();
        assertEquals(expResult, result);
    }   
    
    @Test
    public void testDetermineBoardStatusOWins() {
        Board board = new Board();
        board.setSquare(0, 0, 'O');
        board.setSquare(1, 0, 'O');
        board.setSquare(2, 0, 'X');
        board.setSquare(0, 1, 'X');
        board.setSquare(1, 1, 'O');
        board.setSquare(2, 1, 'X');
        board.setSquare(0, 2, 'O');
        board.setSquare(1, 2, 'X');
        board.setSquare(2, 2, 'O');
        board.draw();        
        BoardStatus expResult = BoardStatus.O_WINS;
        BoardStatus result = board.determineBoardStatus();
        assertEquals(expResult, result);
    }    
    @Test
    public void testMakeAMoveOWins() {
        Board board = new Board();
        board.setSquare(0, 0, 'O');
        board.setSquare(1, 0, 'X');
        board.setSquare(2, 0, 'X');
        board.setSquare(0, 1, 'X');
        board.setSquare(1, 1, 'O');
        board.setSquare(2, 1, 'X');
        board.setSquare(0, 2, 'O');
        board.setSquare(1, 2, ' ');
        board.setSquare(2, 2, ' ');
        board.draw();
        
        // O | X | X
        //---+---+---
        // X | O | X
        //---+---+---
        // O |   |  
        char currentPlayer = 'O';        
        Move expectedMove = new Move();
        expectedMove.setColumn(2);
        expectedMove.setRow(2);
        expectedMove.setBoardStatus(BoardStatus.O_WINS);
        
        Move result = board.makeMove(currentPlayer);
        assertEquals(expectedMove.getColumn(), result.getColumn());
        assertEquals(expectedMove.getRow(), result.getRow());
        assertEquals(expectedMove.getBoardStatus(), result.getBoardStatus());
    }
    
    @Test
    public void testMakeAMoveDraw() {
        Board board = new Board();
        board.setSquare(0, 0, 'X');
        board.setSquare(1, 0, ' ');
        board.setSquare(2, 0, 'O');
        board.setSquare(0, 1, 'O');
        board.setSquare(1, 1, 'X');
        board.setSquare(2, 1, 'X');
        board.setSquare(0, 2, ' ');
        board.setSquare(1, 2, 'X');
        board.setSquare(2, 2, 'O');
        board.draw();
        
        // X |   | O
        //---+---+---
        // O | X | X
        //---+---+---
        //   | X | O 
        char currentPlayer = 'O';        
        Move expectedMove = new Move();
        expectedMove.setColumn(1);
        expectedMove.setRow(0);
        expectedMove.setBoardStatus(BoardStatus.DRAW);
        
        Move result = board.makeMove(currentPlayer);
        assertEquals(expectedMove.getColumn(), result.getColumn());
        assertEquals(expectedMove.getRow(), result.getRow());
        assertEquals(expectedMove.getBoardStatus(), result.getBoardStatus());
        // need to actually update the board to make the next move
        board.setSquare(result.getColumn(), result.getRow(), currentPlayer);
        board.draw();        
        
        currentPlayer = 'X';        
        expectedMove = new Move();
        expectedMove.setColumn(0);
        expectedMove.setRow(2);
        expectedMove.setBoardStatus(BoardStatus.DRAW);
        
        result = board.makeMove(currentPlayer);
        assertEquals(expectedMove.getColumn(), result.getColumn());
        assertEquals(expectedMove.getRow(), result.getRow());
        assertEquals(expectedMove.getBoardStatus(), result.getBoardStatus());      
    }    
    
    @Test
    public void testMakeAMoveXWins() {
        Board board = new Board();
        board.setSquare(0, 0, 'O');
        board.setSquare(1, 0, ' ');
        board.setSquare(2, 0, 'X');
        board.setSquare(0, 1, 'O');
        board.setSquare(1, 1, ' ');
        board.setSquare(2, 1, ' ');
        board.setSquare(0, 2, 'X');
        board.setSquare(1, 2, ' ');
        board.setSquare(2, 2, ' ');
        board.draw();        
        
        // O |   | X
        //---+---+---
        // O |   | 
        //---+---+---
        // X |   |  
        char currentPlayer = 'X';        
        Move expectedMove = new Move();
        expectedMove.setColumn(1);
        expectedMove.setRow(1);
        expectedMove.setBoardStatus(BoardStatus.X_WINS);
        
        Move result = board.makeMove(currentPlayer);
        assertEquals(expectedMove.getColumn(), result.getColumn());
        assertEquals(expectedMove.getRow(), result.getRow());
        assertEquals(expectedMove.getBoardStatus(), result.getBoardStatus());
    }    
    
    
    
}