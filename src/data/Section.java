package data;

public class Section {
    private int mRow;
    private int mColumn;
    private boolean isBlocked = false;

    public Section(int row, int column, boolean blocked) {
        this.mRow = row;
        this.mColumn = column;
        this.isBlocked = blocked;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        this.mRow = row;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setColumn(int column) {
        this.mColumn = column;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public Section clone() {
        return new Section(this.mRow, this.mColumn, this.isBlocked);
    }
}
