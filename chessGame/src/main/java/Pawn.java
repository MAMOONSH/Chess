import java.util.Objects;

public final class Pawn extends Piece{
    private boolean isFirstMove=true;
    public static Pawn createPawn(boolean isBlack){
        return new Pawn(isBlack);
    }

    private Pawn(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean validMove(Board board, Move fromCell, Move toCell) {
        if(fromCell==null|| toCell==null||board==null)
            throw new IllegalArgumentException();
        Piece movingPiece=board.getCell(fromCell).getPiece();
        Piece destinationPiece=board.getCell(toCell).getPiece();
        if(destinationPiece!=null&&isAttackingTheSameColor(movingPiece.isBlack(),destinationPiece.isBlack())) {
            return false;
        }
        if(isFirstMove) {
            isFirstMove=false;
            return true;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pawn pawn = (Pawn) o;
        return isFirstMove == pawn.isFirstMove;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isFirstMove);
    }

    @Override
    public String toString() {
        return isBlack() ? "Black" : "white" + " Pawn";
    }

}
