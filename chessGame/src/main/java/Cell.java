import java.util.Objects;

public final class Cell {
    private final int row;
    private final int column;
    private Piece piece=null;

    public static Cell createCell(int row,int column){
        return new Cell(row,column);
    }

    private Cell(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty(){
        return piece == null;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", piece=" + piece +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column && Objects.equals(piece, cell.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, piece);
    }
}
