public class Distance {
    public static int getColumnDistance(Move startCell,Move endCell) {
         return calculateDistance(startCell.getColumn(),endCell.getColumn());
    }
    public static int getRowDistance(Move startCell,Move endCell) {
        return calculateDistance(startCell.getRow(),endCell.getRow());
    }
    private static int calculateDistance(int from,int to){
        return Math.abs(from-to);
    }
}
