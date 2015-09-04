package kamene2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kamene2.GameState2;

/**
 * Field represents playing field and game logic.
 */
@SuppressWarnings("serial")
public class Fieldk2 implements Serializable {
	/**
	 * Time of playing from saved game
	 */
	private int startTime;
	/**
	 * Playing field tiles.
	 */
	private Number2[][] numbers;
	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;
	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;
	/**
	 * Game state.
	 */
	private GameState2 state = GameState2.PLAYING;

	/**
	 * Constructor.
	 * 
	 * @param rowCount
	 *            - row count
	 * @param columnCount
	 *            - column count
	 */
	public Fieldk2(int rowCount, int columnCount) {
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		numbers = new Number2[rowCount][columnCount];
		generate();
	}


	/**
	 * generate playing field, generate random numbers which represent game -
	 * first fill arrayList with numbers from 1 to n(row*column-1) and " " than
	 * it shuffle
	 */
	private void generate() {

		List<Integer> createRandomNumber = new ArrayList<>();
		for (int i = 1; i <= getColumnCount() * getRowCount(); i++) {
			if (i != getColumnCount() * getRowCount()) {
				createRandomNumber.add(i);
			} else {
				createRandomNumber.add(0);
			}
		}
		Collections.shuffle(createRandomNumber);
		Integer[] randomNumbers = createRandomNumber.toArray(new Integer[createRandomNumber.size()]);
		int count = 0;
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				numbers[row][column] = new Number2(randomNumbers[count]);
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
	public void move(int row,int column) {

		int emptyRow = 0;
		int emptyColumn = 0;
		Number2 number = null;
		out: for (emptyRow = 0; emptyRow < getRowCount(); emptyRow++) {
			for (emptyColumn = 0; emptyColumn < getColumnCount(); emptyColumn++) {
				number = (Number2) numbers[emptyRow][emptyColumn];
				if (number.getValue() == 0) {
					break out;
				}
			}
		}
		if((((emptyRow==row+1)||(emptyRow==row-1))&&emptyColumn==column)||(((emptyColumn==column+1)||(emptyColumn==column-1))&&emptyRow==row)){
			numbers[emptyRow][emptyColumn].setValue(numbers[row][column].getValue());
			Number2 newEmpty=(Number2) numbers[row][column];
			newEmpty.setValue(0);
		}
		if (isSolved()) {
			state = GameState2.SOLVED;
		} else {
			state = GameState2.PLAYING;
		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 * 
	 * @return true if game is solved, false otherwise
	 */
	private boolean isSolved() {
		int count = 1;
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				if (count != getColumnCount() * getRowCount()) {
					Number2 number = (Number2) numbers[row][column];
					if (number.getValue() != count) {
						return false;
					}
					count++;
				}
			}
		}
		return true;
	}

	public Number2 getNumber(int row, int column) {
		return numbers[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState2 getState() {
		return state;
	}
}
