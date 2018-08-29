

import java.util.Scanner;


public class Game {


    private static OX ox;
    private static Scanner kb = new Scanner(System.in);
    private static int col,row;

    private static void input(){

        boolean canPut=true;

        do {
            System.out.print(ox.getCurrentPlayer() + " (Col): ");
            col = kb.nextInt();
            System.out.print(ox.getCurrentPlayer() + " (Row): ");
            row = kb.nextInt();
            canPut = ox.put(col, row);
            if (!canPut) {
                System.out.println("Please input number between 0-2");
            }
        }while (!canPut);


    }

    private static void printTable() {

        System.out.print(ox.getTableString());

    }



    public static void main(String[] args) {

        ox = new OX();
        while(true) {
            printTable();
            input();
            if(ox.checkWin(col,row)){
                printTable();
                printWin();
                printScore();
                ox.reset();
                continue;
            }

            if(ox.isDraw()){
                printTable();
                System.out.println("Draw");
                printScore();
                ox.reset();
                continue;
            }

           ox.swicthPlayer();

        }
    }

    private static void printWin() {
        System.out.println(ox.getCurrentPlayer()+" "+"Win");
    }

    private static void printScore() {
        System.out.println("score X : "+ox.getScoreX());
        System.out.println("score O : "+ox.getScoreO());
        System.out.println("Draw : "+ox.getScoreDraw());
    }


}
