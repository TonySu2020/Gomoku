package model.stone;

/**
 * Interface for stone type classes.
 */
public interface Stone {

  /**
   * Get the row number of the stone.
   *
   * @return  the row
   */
  int getRow();

  /**
   * Get the column number of the stone.
   *
   * @return  the column
   */
  int getCol();

  /**
   * Get the color of the stone.
   *
   * @return  the color
   */
  Color getColor();
}
