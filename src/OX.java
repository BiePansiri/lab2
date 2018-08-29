import com.sun.xml.internal.bind.v2.runtime.unmarshaller.SAXConnector;

public class OX {

    private String[][] table = {{" ", "0", "1", "2"},
            {"0", "-", "-", "-"},
            {"1", "-", "-", "-"},
            {"2", "-", "-", "-"}
    };
    private String Player;
    private int countX;
    private int countO;
    private int countDraw;
    private int turnCount;
    private int scoreDraw;

    public OX() {
        Player = "X";
        countX = 0;
        countO = 0;
        countDraw = 0;
        scoreDraw=0;
        turnCount=0;
    }

    public String getTableString() {
        String result = "";
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                result = result + table[row][col];
            }
            result = result + "\n";

        }
        return result;
    }

    public String getCurrentPlayer() {
        return Player;
    }

    public int getCountO() {
        return countO;
    }

    public int getCountX() {
        return countX;
    }

    public int getCountDraw() {
        return countDraw;
    }

    public boolean put(int col, int row) {

        try{
            if (!table[row + 1][col + 1].equals("-")) {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }

        table[row + 1][col + 1] = getCurrentPlayer();

        turnCount++;
        if(checkWin(col, row)){
            if(Player.equals("X")){
                countX++;
            }else if(Player.equals("O")){
                countO++;
            }
        }

        if(isDraw()){
            scoreDraw++;
        }
        return true;

    }

    public void swicthPlayer() {
        if (Player == "X") {
            Player = "O";
        } else {
            Player = "X";
        }

    }

    public boolean checkWin(int col, int row) {
        boolean colWin = true;
        /*checkcolwin*/
        for(int i=0;i<3;i++){
            if(!table[i+1][col+1].equals(getCurrentPlayer())){
                colWin=false;

            }
        }
        if(colWin){
            return true;
        }

        /*checkrowwin*/
        boolean rowWin = true;
        for(int i=0;i<3;i++){
            if(!table[row+1][i+1].equals(getCurrentPlayer())){
                rowWin=false;

            }
        }
        if(rowWin){
            return true;
        }
        /*checkeswin*/
        boolean WinES = true;
        for(int i=0;i<3;i++){
            if(!table[i+1][i+1].equals(getCurrentPlayer())){
                WinES = false;

            }
        }
        if(WinES ){
            return true;
        }

        /*checksswin*/
        boolean WinSS = true;
        for(int i=0;i<3;i++){
            if(!table[i+1][3-i].equals(getCurrentPlayer())){
                WinSS = false;

            }
        }
        if(WinSS ){
            return true;
        }

        return false;
    }

    public String get(int col,int row){

      if(col>2||col<0||row>2||row<0){
          return null;
      }
        return table[row+1][col+1];
    }


    public void reset() {


        for(int i =0 ;i<3;i++){
            for(int j=0;j<3;j++){
                table[i+1][j+1]="-";
            }
        }

       Player="X";
        turnCount=0;
    }

    public int getTurnCount() {

        return turnCount;
    }

    public boolean isDraw() {
        if(turnCount<9){
            return false;
        }
        return true;
    }

    public int getScoreX() {
        return countX;
    }

    public int getScoreO() {
        return countO;
    }

    public int getScoreDraw() {
        return scoreDraw;
    }
}
