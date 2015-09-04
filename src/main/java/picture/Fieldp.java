package picture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * Field represents playing field and game logic.
 */
@SuppressWarnings("serial")
public class Fieldp implements Serializable {
	/**
	 * Playing field tiles.
	 */
	private int[][] images;
	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;
	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	private int firstColumn= -1;
	private int firstRow= -1;
	private int  secondColumn= -1;
	private int  secondRow = -1;

	public int getFirstColumn() {
		return firstColumn;
	}

	public void setFirstColumn(int firstColumn) {
		this.firstColumn = firstColumn;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(int secondColumn) {
		this.secondColumn = secondColumn;
	}

	public int getSecondRow() {
		return secondRow;
	}

	public void setSecondRow(int secondRow) {
		this.secondRow = secondRow;
	}

	public void selectSection(int row, int column) {
		System.out.printf("firstcolum:%s",firstColumn);
		System.out.printf("firstrow:%s",firstRow);
		if (firstColumn ==column && firstRow == row) {
			firstColumn = -1;
			firstRow = -1;
		} else if (secondColumn == column && secondRow == row) {
			secondColumn = -1;
			secondRow = -1;
		} else if (firstColumn<0) {
			setFirstRow(row);
			setFirstColumn(column);
		} else if (secondColumn<0) {
			setSecondColumn(column);
			setSecondRow(row);
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param rowCount
	 *            - row count
	 * @param columnCount
	 *            - column count
	 */
	public Fieldp(int rowCount, int columnCount) {
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		images = new int[rowCount][columnCount];
		generate();
	}

	/**
	 * generate playing field, generate random numbers which represent game -
	 * first fill arrayList with numbers from 1 to n(row*column-1) and " " than
	 * it shuffle
	 */
	private void generate() {

		// BufferedImage image = null;
		// try {
		// image = ImageIO.read(new File("resources/images2/picture.png"));
		// } catch (IOException e) {
		// }
		// int width = image.getWidth();
		// int height = image.getHeight();
		// int widthOfSection=width/getColumnCount();
		// int heightOfSection=height/getRowCount();

		List<Integer> createRandomNumber = new ArrayList<>();
		for (int i = 0; i < getColumnCount() * getRowCount(); i++) {
			createRandomNumber.add(i);
		}
		Collections.shuffle(createRandomNumber);
		Integer[] randomNumbers = createRandomNumber.toArray(new Integer[createRandomNumber.size()]);
		//randomNumbers[1]=0;
		//randomNumbers[0]=1;
		int count = 0;
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				images[row][column] = randomNumbers[count];
				count++;
			}
		}

	}

	/**
	 * action with tiles or throws exception when wrong input
	 * 
	 * @param command
	 *            read input
	 * @throws WrongInput
	 */
	public void move() {
		int imaginarSection=getValue(firstRow, firstColumn);
		images[firstRow][firstColumn]=getValue(secondRow, secondColumn);
		images[secondRow][secondColumn]=imaginarSection;
		setFirstColumn(-1);
		setFirstRow(-1);
		setSecondColumn(-1);
		setSecondRow(-1);
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 * 
	 * @return true if game is solved, false otherwise
	 */
	public boolean isSolved() {
		int count = 0;
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				if (count != getColumnCount() * getRowCount()) {
					int number = images[row][column];
					if (number != count) {
						return false;
					}
					count++;
				}
			}
		}
		return true;
	}

	public Integer getValue(int row, int column) {
		return images[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}
}
