    package connect4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Dallal, Z
 */
public class Controller {

    char computer = 'o';
    char human = 'x';
    Connect4Game board = new Connect4Game(3, 3, 3);

    public void play() {
        System.out.println(board);
        while (true) {
            humanPlay();
            System.out.println(board);

            if (board.isWin(human)) {
                System.out.println("Human wins");
                break;
            }
            if (board.isWithdraw()) {
                System.out.println("Draw");
                break;
            }
            computerPlay();
            System.out.println("_____Computer Turn______");
            System.out.println(board);
            if (board.isWin(computer)) {
                System.out.println("Computer wins!");
                break;
            }
            if (board.isWithdraw()) {
                System.out.println("Draw");
                break;
            }
        }

    }

    //         ************** YOUR CODE HERE ************            \\
    private void computerPlay() {
        // this is a random move, you should change this code to run you own code
       // board=maxMove(board).getvalue();
        List<Object> result = maxMove(board);
        Connect4Game nextMove = (Connect4Game) result.get(1);
        board = nextMove;
    }


    /**
     * Human plays
     *
     * @return the column the human played in
     */
    private void humanPlay() {
        Scanner s = new Scanner(System.in);
        int col;
        while (true) {
            System.out.print("Enter column: ");
            col = s.nextInt();
            System.out.println();
            if ((col > 0) && (col - 1 < board.getWidth())) {
                if (board.play(human, col - 1)) {
                    return;
                }
                System.out.println("Invalid Column: Column " + col + " is full!, try agine");
            }
            System.out.println("Invalid Column: out of range " + board.getWidth() + ", try agine");
        }
    }

//    private List<Object> maxMove(Connect4Game b) {
//        // the fuction returns list of object the first object is the evaluation (type Integer), the second is the state with the max evaluation
////         ************** YOUR CODE HERE ************            \\
//        return null;
//
//
//    }
private List<Object> maxMove(Connect4Game b) {
    if (b.isFinished()) {
        List<Object> result = new ArrayList<>();
        result.add(b.evaluate(computer));
        result.add(b);
        return result;
    }

    int maxEval = Integer.MIN_VALUE;
    Connect4Game bestMove = null;

    for (Connect4Game next : b.allNextMoves(computer)) {
        List<Object> result = minMove(next);
        int eval = (int) result.get(0);

        if (eval > maxEval) {
            maxEval = eval;
            bestMove = next;
        }
    }

    List<Object> finalResult = new ArrayList<>();
    finalResult.add(maxEval);
    finalResult.add(bestMove);
    return finalResult;
}


private List<Object> minMove(Connect4Game b) {
    if (b.isFinished()) {
        List<Object> result = new ArrayList<>();
        result.add(b.evaluate(human));
        result.add(b);
        return result;
    }

    int minEval = Integer.MAX_VALUE;
    Connect4Game bestMove = null;

    for (Connect4Game next : b.allNextMoves(human)) {
        List<Object> result = maxMove(next);
        int eval = (int) result.get(0);

        if (eval < minEval) {
            minEval = eval;
            bestMove = next;
        }
    }

    List<Object> finalResult = new ArrayList<>();
    finalResult.add(minEval);
    finalResult.add(bestMove);
    return finalResult;
}


    public static void main(String[] args) {
        Controller g = new Controller();
        g.play();
    }

}
