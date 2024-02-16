public final class Knight extends Piece{

    public static Knight createKnight(boolean isBlack){
        return new Knight(isBlack);
    }

    private Knight(boolean isBlack) {
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
        return true;
    }

    @Override
    public String toString() {
        return isBlack()?"Black":"white"+" Knight";
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
