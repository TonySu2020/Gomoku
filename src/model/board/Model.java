package model.board;

import model.Color;

public interface Model {

  void move(int row, int col);

  boolean isOver();

  Color getWinner();

  void undo();

  void redo();
}
