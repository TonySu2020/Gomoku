package model.board;

import model.stone.Color;

/**
 * Interface for the board.
 */
public interface Model {

  /**
   * Make a move by placing a stone at the (row, col) position on the board, recording the move for
   * undo.
   *
   * @param row the row-th row of the board
   * @param col the col-th col of the board
   */
  void move(int row, int col);

  /**
   * Return true if the game is over, false otherwise.
   *
   * @return if the game is over or not
   */
  boolean isOver();

  /**
   * Return the color of the winner.
   *
   * @return the color of the winner
   */
  Color getWinner();

  /**
   * Undo the latest move.
   */
  void undo();

  /**
   * Redo the last undo move.
   */
  void redo();
}
