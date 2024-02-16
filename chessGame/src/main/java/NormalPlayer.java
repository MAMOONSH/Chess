import java.util.Objects;

public final class NormalPlayer implements Player{
    private final boolean isBlackPlayer;
    private final String playerName;

    public static NormalPlayer createNormalPlayer(boolean isBlackPLayer,String playerName){
        return new NormalPlayer(isBlackPLayer, playerName);
    }

    private NormalPlayer(boolean isBlackPlayer, String playerName) {
        if(playerName==null)
            throw new NullPointerException();

        this.isBlackPlayer = isBlackPlayer;
        this.playerName = playerName;
    }

    @Override
    public boolean isBlackPlayer() {
        return isBlackPlayer;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "NormalPlayer{" +
                "isBlackPlayer=" + isBlackPlayer +
                ", playerName='" + playerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalPlayer that = (NormalPlayer) o;
        return isBlackPlayer == that.isBlackPlayer && Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlackPlayer, playerName);
    }
}
