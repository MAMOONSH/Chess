import java.util.Objects;

public class Move{
    private int row;

    private int column;

    public static Move translateMove(String move){
        return new Move(move);
    }

    private Move(String move){
        if(move==null)
            throw new IllegalArgumentException();
        this.column =translateMoveColumn(move.charAt(0));
        this.row =translateMoveRow(move.charAt(1));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    private int translateMoveColumn(char column){
        return column-'A';
    }

    private int translateMoveRow(char row){
        return row-'1';
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return row == move.row && column == move.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

}
