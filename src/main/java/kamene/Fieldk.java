package kamene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kamene.GameState;

/**
 * Field represents playing field and game logic.
 */
@SuppressWarnings("serial")
public class Fieldk implements Serializable {
	/**
	 * Time of playing from saved game
	 */
	private int startTime;
	/**
	 * Playing field tiles.
	 */
	private Number[][] numbers;
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
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 * 
	 * @param rowCount
	 *            - row count
	 * @param columnCount
	 *            - column count
	 */
	public Fieldk(int rowCount, int columnCount) {
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		numbers = new Number[rowCount][columnCount];
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
				numbers[row][column] = new Number(randomNumbers[count]);
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
	public void move(String command) {
		int row = 0;
		int column = 0;
		Number number = null;
		out: for (row = 0; row < getRowCount(); row++) {
			for (column = 0; column < getColumnCount(); column++) {
				number = (Number) numbers[row][column];
				if (number.getValue() == 0) {
					break out;
				}
			}
		}
		Number up = null;
		switch (command) {
		case "W":
		case "UP":
			if (row != getRowCount() - 1) {
				up = (Number) numbers[row + 1][column];
				number.setValue(up.getValue());
				up.setValue(0);
			}
			break;
		case "S":
		case "DOWN":
			if (row != 0) {
				up = (Number) numbers[row - 1][column];
				number.setValue(up.getValue());
				up.setValue(0);
			}
			break;
		case "A":
		case "LEFT":
			if (column != (getColumnCount() - 1)) {
				up = (Number) numbers[row][column + 1];
				number.setValue(up.getValue());
				up.setValue(0);
			}
			break;
		case "D":
		case "RIGHT":
			if (column != 0) {
				up = (Number) numbers[row][column - 1];
				number.setValue(up.getValue());
				up.setValue(0);
			}
			break;
		default:
			System.out.println("Wrong Input: w|up - s|down - a|left - d|right - save - e|exit ");
		}
		if (isSolved()) {
			state = GameState.SOLVED;
			return;
		} else {
			state = GameState.PLAYING;
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
					Number number = (Number) numbers[row][column];
					if (number.getValue() != count) {
						return false;
					}
					count++;
				}
			}
		}
		return true;
	}

	public Number getNumber(int row, int column) {
		return numbers[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState getState() {
		return state;
	}
}
