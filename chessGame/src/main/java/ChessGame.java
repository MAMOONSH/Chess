import java.util.Objects;

public class ChessGame {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final Board board;
    private boolean isWhiteTurn=true;
    private boolean isDone=false;
    private String winner;
    private final GameRules gameRules;

    public ChessGame(String whitePlayerName,String blackPlayerName){
        whitePlayer=NormalPlayer.createNormalPlayer(false,whitePlayerName);
        blackPlayer=NormalPlayer.createNormalPlayer(true,blackPlayerName);
        board=Board.initializeBoard();
        gameRules=NormalRules.createGameRules(board);
        winner="none";
        System.out.println(board);
    }

    public void playWhite(String move){
        if(move==null)
            throw new IllegalArgumentException();
        if(gameRules.isValidMovePattern(move)){
            String[] moveArray=move.split(" ");
            Move fromCell=Move.translateMove(moveArray[1]);
            Move toCell=Move.translateMove(moveArray[2]);
            playTurn(whitePlayer,fromCell,toCell);
        }
        else{
            System.out.println("please try again -not valid input-");
        }
    }

    public void playBlack(String move){
        if(move==null)
            throw new IllegalArgumentException();
        if(gameRules.isValidMovePattern(move)){
            String[] moveArray=move.split(" ");
            Move fromCell=Move.translateMove(moveArray[1]);
            Move toCell=Move.translateMove(moveArray[2]);
            playTurn(blackPlayer,fromCell,toCell);
        }
        else{

            System.out.println("please try again -not valid input-");
        }
    }

    private void playTurn(Player player,Move moveFromCell,Move moveToCell){
        if(player==null||moveFromCell==null||moveToCell==null)
            throw  new IllegalArgumentException();

        Cell startCell=board.getCell(moveFromCell);
        Cell destinationCell=board.getCell(moveToCell);
        if (gameRules.isValidPieceToMove(player, startCell)) return;
        Piece playerPiece = board.getCell(moveFromCell).getPiece();
        if (gameRules.isValidMove(playerPiece, moveFromCell, moveToCell)) return;
        if(destinationCell.isEmpty()){
           if(playerPiece instanceof King &&Distance.getColumnDistance(moveFromCell, moveToCell) ==2) {
               if(gameRules.isValidCastling(isWhiteTurn, moveFromCell,moveToCell))
                   doCastling(playerPiece, startCell, destinationCell);
               else {
                   System.out.println("invalid castling");
                   return;
               }
           }
           else
               movePlayerPiece(playerPiece, startCell, destinationCell);
       }
       else {
            attackPiece(player, playerPiece, startCell, destinationCell);
        }
        endTurn(player, destinationCell);
        System.out.println(board);
    }

    private void doCastling(Piece playerPiece, Cell startCell, Cell destinationCell) {
        if(playerPiece==null||startCell==null||destinationCell==null) throw  new IllegalArgumentException();
        movePlayerPiece(playerPiece, startCell, destinationCell);
        String rowNumber="";
        rowNumber = getRowNumber(rowNumber);
        Piece rook;
        Cell rookCell;
        Cell rookDestinationCell;
        if(destinationCell.getColumn()> startCell.getColumn()) {
            rookCell = board.getCell(Move.translateMove("H" + rowNumber));
            rookDestinationCell=board.getCell(Move.translateMove("F"+rowNumber));
        }
        else {
            rookCell = board.getCell(Move.translateMove("A" + rowNumber));
            rookDestinationCell=board.getCell(Move.translateMove("D"+rowNumber));
        }
        rook=rookCell.getPiece();
        movePlayerPiece(rook,rookCell,rookDestinationCell);
    }

    private String getRowNumber(String rowNumber) {
        if(rowNumber==null) throw  new IllegalArgumentException();
        if(isWhiteTurn){
            rowNumber ="1";
        }
        else{
            rowNumber ="8";
        }
        return rowNumber;
    }

    private void movePlayerPiece(Piece playerPiece, Cell startCell, Cell destinationCell) {
        destinationCell.setPiece(playerPiece);
        startCell.setPiece(null);
    }

    private void attackPiece(Player player, Piece playerPiece, Cell startCell, Cell destinationCell) {
        if(destinationCell.getPiece() instanceof King)
            setWinner(player);
        destinationCell.getPiece().setAlive(false);
        movePlayerPiece(playerPiece, startCell, destinationCell);
    }

    private void endTurn(Player player, Cell destinationCell) {
        if(gameRules.isValidPromotion(player, destinationCell))
           destinationCell.setPiece(promoteToQueen(player));
        if(gameRules.isCheckMate())
            setWinner(player);
        else if(gameRules.isDraw())
            setDraw();
        isWhiteTurn=!isWhiteTurn;
    }

    private Piece promoteToQueen(Player currentPlayer){
        PieceFactory pieceFactory=new PieceFactory();
        System.out.println("Promote your pawn to a queen");
        return pieceFactory.createPiece("Queen", currentPlayer.isBlackPlayer());
    }

    private void setWinner(Player player) {
        if(player==null) throw  new IllegalArgumentException();
        isDone=true;
        winner= player.getPlayerName();
    }

    private void setDraw(){
        isDone=true;
        winner="Draw";
    }

    public boolean isDone() {
        return isDone;
    }

    public void printWinnerName(){
        System.out.println(winner);
    }

    public boolean isWhiteTurn(){
        return isWhiteTurn;
    }


    @Override
    public String toString() {
        return "ChessGame{" +
                "whitePlayer=" + whitePlayer +
                ", blackPlayer=" + blackPlayer +
                ", board=" + board +
                ", isWhiteTurn=" + isWhiteTurn +
                ", isDone=" + isDone +
                ", winner='" + winner + '\'' +
                ", gameRules=" + gameRules +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return isWhiteTurn == chessGame.isWhiteTurn && isDone == chessGame.isDone && Objects.equals(whitePlayer, chessGame.whitePlayer) && Objects.equals(blackPlayer, chessGame.blackPlayer) && Objects.equals(board, chessGame.board) && Objects.equals(winner, chessGame.winner) && Objects.equals(gameRules, chessGame.gameRules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whitePlayer, blackPlayer, board, isWhiteTurn, isDone, winner, gameRules);
    }
}
