package erikbissell.com.WebChess;

public class MoveRequest {
    private int sourceRow;
    private int sourceCol;
    private int destRow;
    private int destCol;

    public MoveRequest(int sourceRow, int sourceCol, int destRow, int destCol){
        this.sourceRow = sourceRow;
        this.sourceCol = sourceCol;
        this.destRow = destRow;
        this.destCol = destCol;
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
    
}
