public final class King extends Piece{
    public static King createKing(boolean isBlack){
        return new King(isBlack);
    }

    private King(boolean isBlack) {
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

        if(Distance.getColumnDistance(fromCell, toCell) ==2&&Distance.getRowDistance(fromCell,toCell)==0) {
            System.out.println("castling");
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return isBlack()?"Black":"white"+" King";
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
