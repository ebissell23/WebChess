package erikbissell.com.WebChess;

public class MoveRequest {
    private int sourceRow;
    private int sourceCol;
    private int destRow;
    private int destCol;
    private boolean capturedKing;

    public MoveRequest(int sourceRow, int sourceCol, int destRow, int destCol){
        this.sourceRow = sourceRow;
        this.sourceCol = sourceCol;
        this.destRow = destRow;
        this.destCol = destCol;
        this.capturedKing = false;
    }

    public int getSourceRow(){
        return sourceRow;
    }
    public int getSourceCol(){
        return sourceCol;
    }
    public int getDestRow(){
        return destRow;
    }
    public int getDestCol(){
        return destCol;
    }
    public void printMove(){
        System.out.println("sourceRow: " + sourceRow + " sourceCol: " + sourceCol + " destRow: " + destRow + " destCol: " + destCol);
    }
    public void setCaptureKing(boolean capturedKing){
        this.capturedKing = capturedKing;
    }
    public boolean checkCapturedKing(){
        System.out.println("returning: " + capturedKing);
        return capturedKing;
    }
    
}
