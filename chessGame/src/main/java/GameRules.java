public interface GameRules {
    boolean isValidMovePattern(String move);
    boolean isValidPieceToMove(Player player, Cell startCell);
    boolean isValidMove(Piece playerPiece, Move fromCell, Move toCell);
    boolean isValidCastling(boolean isWhiteTurn, Move startCell, Move destinationCell);
    boolean isValidPromotion(Player player,Cell destinationCell);
    boolean isDraw();
    boolean isCheckMate();
}
