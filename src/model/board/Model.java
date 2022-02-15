package model.board;

import model.stone.Color;

public interface Model {

  void move(int row, int col);

  boolean isOver();

  Color getWinner();

  void undo();

  void redo();
}
