package model.stone;

/**
 * This class represents a simple stone which has a row number, column number and color.
 */
public class BlackWhiteStone implements Stone {

  private final int row;
  private final int col;
  private final Color color;

  /**
   * Construct a BlackWhiteStone with a row number, column number and color.
   *
   * @param row the row of its position
   * @param col the column of its position
   * @param color the color of this stone
   */
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
