package model.stone;

public class BlackWhiteStone implements Stone {

  private final int row;
  private final int col;
  private final Color color;

  public BlackWhiteStone(int row, int col, Color color) {
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  @Override
  public Color getColor() {
    return color;
  }
}
