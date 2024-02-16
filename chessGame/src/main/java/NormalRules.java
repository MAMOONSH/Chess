import java.util.*;
import java.util.regex.*;

public class NormalRules implements GameRules{
    private final Board board;
    private static final Pattern MOVE_REGEX =
            Pattern.compile("move [A-H][1-8] [A-H][1-8]");

    public static NormalRules createGameRules(Board board){
        return new NormalRules(board);
    }

    private NormalRules(Board board){
        this.board=board;
    }

    @Override
    public boolean isValidCastling(boolean isWhiteTurn, Move startCell, Move destinationCell) {
        if(startCell==null||destinationCell==null)
            throw  new IllegalArgumentException();
        String rowNumber="";
        rowNumber = getRowNumber(rowNumber,isWhiteTurn);
        if (!isRookAndKingAtStartPlace(rowNumber, startCell, destinationCell)) return false;
        int startCheckingFromCell=destinationCell.getColumn()>startCell.getColumn()?1:-1;
        for(int i=startCell.getColumn()+startCheckingFromCell;i<destinationCell.getColumn();){
            Move checkNextCell = getNextCell(startCell, i);
            if(destinationCell.getColumn()>=startCell.getColumn()) {
                if (!isCellUnderAttack(checkNextCell) && !isCheckMate()&&board.getCell(checkNextCell).isEmpty()) {
                    i++;
                }
                else
                    return false;
            }
            else {
                if (!isCellUnderAttack(checkNextCell) && !isCheckMate()&&board.getCell(checkNextCell).isEmpty()) {
                    i--;
                }
                else
                    return false;
            }
        }
        if(destinationCell.getColumn()<startCell.getColumn()){
            Move checkNextCell = getNextCell(startCell, destinationCell.getColumn()-1);
            return board.getCell(checkNextCell).isEmpty();
        }
        return true;
    }

    private String getRowNumber(String rowNumber, boolean isWhiteTurn) {
        if(rowNumber==null) throw  new IllegalArgumentException();
        if(isWhiteTurn){
            rowNumber ="1";
        }
        else{
            rowNumber ="8";
        }
        return rowNumber;
    }

    private boolean isRookAndKingAtStartPlace(String rowNumber, Move startCell, Move destinationCell) {
        if(rowNumber==null||startCell==null||destinationCell==null) throw  new IllegalArgumentException();
        if(board.getCell(Move.translateMove("E"+ rowNumber)).isEmpty()||!(board.getCell(Move.translateMove("E"+ rowNumber)).getPiece() instanceof King))
            return false;
        if(destinationCell.getColumn()> startCell.getColumn()){
            return !board.getCell(Move.translateMove("H" + rowNumber)).isEmpty() && board.getCell(Move.translateMove("H" + rowNumber)).getPiece() instanceof Rook;
        }
        else return !board.getCell(Move.translateMove("A" + rowNumber)).isEmpty() && board.getCell(Move.translateMove("A" + rowNumber)).getPiece() instanceof Rook;
    }

    private boolean isCellUnderAttack(Move cell){
        if(cell==null) throw  new IllegalArgumentException();
        return false;
    }

    private Move getNextCell(Move startCell, int destinationCell) {
        if(startCell==null) throw  new IllegalArgumentException();
        Move checkNextCell= startCell;
        checkNextCell.setColumn(destinationCell);
        return checkNextCell;
    }

    @Override
    public boolean isCheckMate(){
        return false;
    }

    @Override
    public boolean isValidPromotion(Player player,Cell destinationCell) {
        if(player==null||destinationCell==null) throw  new IllegalArgumentException();
        int row=destinationCell.getRow();
        if(player.isBlackPlayer()){
            return row == 0 && destinationCell.getPiece() instanceof Pawn;
        }
        else {
            return row == 7 && destinationCell.getPiece() instanceof Pawn;
        }
    }

    @Override
    public boolean isValidPieceToMove(Player player, Cell startCell) {
        if(player==null||startCell==null) throw  new IllegalArgumentException();
        if(startCell.isEmpty()) {
            System.out.println("there is no piece to move");
            return true;
        }
        Piece playerPiece=startCell.getPiece();
        if(playerPiece.isBlack()!= player.isBlackPlayer()){
            System.out.println("the color of the moved piece\n" +
                    "does not match the player color");
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidMove(Piece playerPiece, Move fromCell, Move toCell) {
        if(playerPiece==null||fromCell==null||toCell==null) throw  new IllegalArgumentException();
        if(!playerPiece.validMove(board, fromCell, toCell)){
            System.out.println("not valid move for your piece " +
                    "or cant kill your pieces");
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidMovePattern(String move) {
        return MOVE_REGEX.matcher(move).matches();
    }

    @Override
    public boolean isDraw() {
        if(isStaleMate())
            return true;
        return isNotEnoughMaterials();
    }

    private boolean isStaleMate(){
        return false;
    }

    private boolean isNotEnoughMaterials() {
        List<Piece> blackPlayerPieces= new ArrayList<>();
        List<Piece> whitePlayerPieces= new ArrayList<>();
        getBoardRemainPieces(blackPlayerPieces, whitePlayerPieces);
        return blackPlayerPieces.size() == 1 && whitePlayerPieces.size() == 1;
    }

    private void getBoardRemainPieces(List<Piece> blackPlayerPieces, List<Piece> whitePlayerPieces) {
        for(int row=0;row<8;row++)
            for (int column=0;column<8;column++){
                String columnName=""+(char)('A'+row);
                String rowName=""+(char)('1'+column);
                Move indexCell=Move.translateMove(columnName+rowName);
                Cell currentCell=board.getCell(indexCell);
                if(!currentCell.isEmpty()){
                    if(currentCell.getPiece().isBlack())
                        blackPlayerPieces.add(currentCell.getPiece());
                    else
                        whitePlayerPieces.add(currentCell.getPiece());
                }
            }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalRules that = (NormalRules) o;
        return Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    @Override
    public String toString() {
        return "NormalRules{" +
                "board=" + board +
                '}';
    }
}
