package data;

public class Map {
    private Section[] mBoard;
    private int mRowLength;
    private int mColumnLength;

    public Map(Section[] data, int rowLength, int columnLength) {
        this.mBoard = data;
        this.mRowLength = rowLength;
        this.mColumnLength = columnLength;
    }

    public Section[] getBoard() {
        return mBoard;
    }

    public void setBoard(Section[] board) {
        this.mBoard = board;
    }

    public int getRowLength() {
        return mRowLength;
    }

    public void setRowLength(int rowLength) {
        this.mRowLength = rowLength;
    }

    public int getColumnLength() {
        return mColumnLength;
    }

    public void setColumnLength(int columnLength) {
        this.mColumnLength = columnLength;
    }
}
