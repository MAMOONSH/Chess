public final class Rook extends Piece{
    public static Rook createRook(boolean isBlack){
        return new Rook(isBlack);
    }

    private Rook(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean validMove(Board board, Move fromCell, Move toCell) {
        if(fromCell==null|| toCell==null ||board==null)
            throw new IllegalArgumentException();
        Piece movingPiece=board.getCell(fromCell).getPiece();
        Piece destinationPiece=board.getCell(toCell).getPiece();
        if(destinationPiece!=null&&isAttackingTheSameColor(movingPiece.isBlack(),destinationPiece.isBlack())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return isBlack()?"Black":"white"+" Rook";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
