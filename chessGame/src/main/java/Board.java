import java.util.Arrays;

public final class Board {
    private final Cell[][] board=new Cell[8][8];

    public static Board initializeBoard(){
        return new Board();
    }

    private Board(){
        createBoard();
    }

    private void createBoard(){
        PieceFactory pieceCreator=new PieceFactory();
        for(int row=0;row<8;row++)
            for(int column=0;column<8;column++){
                fillingTheCell(pieceCreator, row, column);
            }

    }

    private void fillingTheCell(PieceFactory pieceCreator, int row, int column) {
        Cell cell=Cell.createCell(row, column);
        cell.setPiece(null);
        boolean isBlack= row == 6 || row == 7;
        if(checkIfRook(row, column))
            cell.setPiece(pieceCreator.createPiece("Rook", isBlack));
        else if(checkIfKnight(row, column))
            cell.setPiece(pieceCreator.createPiece("Knight", isBlack));
        else if(checkIfBishop(row, column))
            cell.setPiece(pieceCreator.createPiece("Bishop", isBlack));
        else if(checkIfQueen(row, column))
            cell.setPiece(pieceCreator.createPiece("Queen", isBlack));
        else if(checkIfKing(row,column))
            cell.setPiece(pieceCreator.createPiece("King", isBlack));
        else if(checkIfPawn(row))
            cell.setPiece(pieceCreator.createPiece("Pawn", isBlack));
        board[row][column] = cell;
    }

    private boolean checkIfRook(int row, int column) {
        return (row == 0 && (column == 0 || column == 7)) || (row == 7 && (column == 0 || column == 7));
    }

    private boolean checkIfKnight(int row, int column) {
        return (row == 0 && (column == 1 || column == 6)) || (row == 7 && (column == 1 || column == 6));
    }

    private boolean checkIfBishop(int row, int column) {
        return (row == 0 && (column == 2 || column == 5)) || (row == 7 && (column == 2 || column == 5));
    }

    private boolean checkIfQueen(int row, int column) {
        return (row == 0 && column == 3) || (row == 7 && column == 3);
    }

    private boolean checkIfKing(int row, int column) {
        return (row == 0 && column == 4) || (row == 7 && column == 4);
    }

    private boolean checkIfPawn(int row) {
        return row == 1 || row == 6;
    }

    public Cell getCell(Move move){
        if(move==null) throw  new IllegalArgumentException();
        return board[move.getRow()][move.getColumn()];
    }

    @Override
    public String toString() {
        String boardShape= "";
        boardShape = getBoardAsString(boardShape);
        return boardShape;
    }

    private String getBoardAsString(String boardShape) {
        StringBuilder boardShapeBuilder = new StringBuilder(boardShape);
        for(int row = 7; row>=0; row--) {
            for (int column = 0; column <= 7; column++) {
                Piece pieceAtCell = board[row][column].getPiece();
                if (pieceAtCell == null)
                    boardShapeBuilder.append("    0    ");
                else if(pieceAtCell.isBlack())
                    boardShapeBuilder.append("B ");
                else
                    boardShapeBuilder.append("W ");
                if (pieceAtCell instanceof Pawn)
                    boardShapeBuilder.append("Pawn   ");
                else if (pieceAtCell instanceof Rook)
                    boardShapeBuilder.append("Rook   ");
                else if (pieceAtCell instanceof Knight)
                    boardShapeBuilder.append("Knight ");
                else if (pieceAtCell instanceof Bishop)
                    boardShapeBuilder.append("Bishop ");
                else if (pieceAtCell instanceof Queen)
                    boardShapeBuilder.append("Queen  ");
                else if (pieceAtCell instanceof King)
                    boardShapeBuilder.append("King   ");
            }
            boardShapeBuilder.append("\n");
        }
        boardShape = boardShapeBuilder.toString();
        return boardShape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
