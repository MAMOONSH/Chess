import java.util.Objects;

public abstract class Piece {
    private final boolean isBlack;
    private boolean isAlive=true;

    protected Piece(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    abstract public boolean validMove(Board board, Move fromCell, Move toCell);

    protected boolean isAttackingTheSameColor(boolean movingPieceColor,boolean destinationPieceColor){
        return movingPieceColor==destinationPieceColor;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "isBlack=" + isBlack +
                ", isAlive=" + isAlive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return isBlack == piece.isBlack && isAlive == piece.isAlive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlack, isAlive);
    }
}
