public class PieceFactory {
    public Piece createPiece(String type,boolean isBlack){
        if(type==null||type.isEmpty())
            throw new IllegalArgumentException();
        if(type.equalsIgnoreCase("Rook"))
            return Rook.createRook(isBlack);
        else if(type.equalsIgnoreCase("Knight"))
            return Knight.createKnight(isBlack);
        else if(type.equalsIgnoreCase("Bishop"))
            return Bishop.createBishop(isBlack);
        else if(type.equalsIgnoreCase("Queen"))
            return Queen.createQueen(isBlack);
        else if(type.equalsIgnoreCase("King"))
            return King.createKing(isBlack);
        else if(type.equalsIgnoreCase("Pawn"))
            return Pawn.createPawn(isBlack);
        else
            throw new IllegalArgumentException();
    }
}
