package model.board;

import java.util.Stack;
import model.stone.Color;
import model.stone.BlackWhiteStone;
import model.stone.Stone;

/**
 * This class represents a standard 15 * 15 Gomoku board. The first move is made by black. Players
 * take turns to make a move. Each move is tracked for undo and redo. Once the game is over for
 * either having a winner or the board is full, none of the players can make anther move.
 */
public class StandardBoard implements Model {

  private final int height;
  private final int width;
  private final Stone[][] board;
  private boolean isBlacksTurn;

  private int moveCount;
  private final int maxMoveCount;
  private Color winner;

  private final Stack<Stone> previousMoves;
  private final Stack<Stone> futureMoves;


  public StandardBoard(Stone[][] board) {
    height = board.length;
    width = board[0].length;
    this.board = new Stone[height][width];
    int blackCount = 0;
    int whileCount = 0;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (board[row].length != this.board[row].length) {
          throw new IllegalArgumentException("Invalid input board array.");
        }
        this.board[row][col] = board[row][col];
        if(board[row][col] != null) {
          if(board[row][col].getColor() == Color.BLACK) {
            blackCount++;
          } else {
            whileCount++;
          }
        }
      }
    }
    isBlacksTurn = blackCount == whileCount;
    moveCount = 0;
    maxMoveCount = width * height;
    winner = null;
    previousMoves = new Stack<>();
    futureMoves = new Stack<>();
  }

  public StandardBoard() {
    this(new Stone[15][15]);
  }

  @Override
  public void move(int row, int col) {
    if (row < 0 || col < 0 || row >= height || col >= width) {
      throw new IllegalArgumentException("Invalid coordinate.");
    }
    if (isOver()) {
      throw new IllegalStateException("The game is over already.");
    }
    if (board[row][col] != null) {
      throw new IllegalArgumentException("This position has been taken.");
    }
    Color color = isBlacksTurn ? Color.BLACK : Color.WHITE;
    Stone stone = new BlackWhiteStone(row, col, color);
    board[row][col] = stone;
    moveCount++;
    isBlacksTurn = !isBlacksTurn;

    previousMoves.push(stone);
    futureMoves.clear();

    determineWinner(stone);
  }

  private void determineWinner(Stone stone) {
    int row = stone.getRow();
    int col = stone.getCol();
    Color color = stone.getColor();
    if (checkVertical(row, col, color) || checkHorizontal(row, col, color)
      || checkDiagonal(row, col, color) || checkAntiDiagonal(row, col, color)) {
      winner = stone.getColor();
    }
  }

  private boolean checkVertical(int row, int col, Color color) {
    int r = row;
    int count = 1;
    r--;
    while (r >= 0 && board[r][col] != null && color == board[r][col].getColor()) {
      count++;
    }
    r = row;
    r++;
    while (r < height && board[r][col] != null && color == board[r][col].getColor()) {
      count++;
    }
    return count >= 5;
  }

  private boolean checkHorizontal(int row, int col, Color color) {
    int c = col;
    int count = 1;
    c--;
    while (c >= 0 && board[row][c] != null && color == board[row][c].getColor()) {
      count++;
    }
    c = col;
    c++;
    while (c < width && board[row][c] != null && color == board[row][c].getColor()) {
      count++;
    }
    return count >= 5;
  }

  private boolean checkDiagonal(int row, int col, Color color) {
    int r = row;
    int c = col;
    int count = 1;
    c--;
    r--;
    while (c >= 0 && r >= 0 && board[r][c] != null && color == board[r][c].getColor()) {
      count++;
    }
    r = row;
    c = col;
    c++;
    r++;
    while (c < width && r < height && board[r][c] != null && color == board[r][c].getColor()) {
      count++;
    }
    return count >= 5;
  }

  private boolean checkAntiDiagonal(int row, int col, Color color) {
    int r = row;
    int c = col;
    int count = 1;
    c--;
    r++;
    while (c >= 0 && r < height && board[r][c] != null && color == board[r][c].getColor()) {
      count++;
    }
    r = row;
    c = col;
    c++;
    r--;
    while (c < width && r >= 0 && board[r][c] != null && color == board[r][c].getColor()) {
      count++;
    }
    return count >= 5;
  }

  @Override
  public boolean isOver() {
    if (winner != null) {
      return true;
    }
    return maxMoveCount == moveCount;
  }

  @Override
  public Color getWinner() {
    return winner;
  }

  @Override
  public void undo() {
    if (previousMoves.isEmpty()) {
      throw new IllegalStateException("No more moves to undo.");
    }
    Stone last = previousMoves.pop();
    futureMoves.push(last);
  }

  @Override
  public void redo() {
    if (futureMoves.isEmpty()) {
      throw new IllegalStateException("No more moves to redo.");
    }
    Stone next = previousMoves.pop();
    previousMoves.push(next);
  }
}
