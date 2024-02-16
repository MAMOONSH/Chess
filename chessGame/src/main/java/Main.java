import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ChessGame game=new ChessGame("fahed","ahmad");
        while(!game.isDone()){
            String move =sc.nextLine();
            if(game.isWhiteTurn())
                game.playWhite(move);
            else
                game.playBlack(move);
        }
        game.printWinnerName();
        sc.close();
    }
}
