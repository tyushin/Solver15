import java.util.ArrayList;

public class Fifteen {
    private static int[] rightBoard = {1,2,3,4,5,6,7,8,0};

    private int[] board = new int[9];

    public ArrayList<int[]> solverBoards;

    public Fifteen(int[] board){
        this.board = board;
    }

    public void Solve() {
        //set initial conditions
        solverBoards = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        weights.add(compare(board, rightBoard));
        ArrayList<int[]> unverified = new ArrayList<>();
        unverified.add(board);
        ArrayList<Integer> parentUnverifiedIndexes = new ArrayList<>();
        parentUnverifiedIndexes.add(-1);
        ArrayList<int[]> history = new ArrayList<>();
        ArrayList<Integer> parentHistoryIndexes = new ArrayList<>();

        while (true) {
            //find the next board to check
            int currentBoardIndex = 0;
            for (int i = 0; i < weights.size(); i++) {
                if (weights.get(i) < weights.get(currentBoardIndex)) {
                    currentBoardIndex = i;
                }
            }
            int[] currentBoard = unverified.get(currentBoardIndex);
            int parentNumber = parentUnverifiedIndexes.get(currentBoardIndex);
            int depth = getDepth(parentHistoryIndexes,parentNumber);

            //remove from unverified
            weights.remove(currentBoardIndex);
            unverified.remove(currentBoardIndex);
            parentUnverifiedIndexes.remove(currentBoardIndex);

            //add to history
            history.add(currentBoard);
            parentHistoryIndexes.add(parentNumber);

            //check for solution
            if (compare(currentBoard, rightBoard) == 0) {
                solverBoards.add(currentBoard);
                while (parentNumber != -1) {
                    solverBoards.add(history.get(parentNumber));
                    parentNumber = parentHistoryIndexes.get(parentNumber);
                }
           //output correct moves
                for (int i = solverBoards.size() - 1; i >= 0; i--){
                    writeBoard(solverBoards.get(i));
                }
                return;
            }
            // find empty cell
            int Zero = 0;
            for (int i = 0; i < 9; i++) {
                if (currentBoard[i] == 0) {
                    Zero = i;
                    break;
                }
            }
            //up
            if (Zero / 3 > 0) {
                int[] nextBoard = currentBoard.clone();
                nextBoard[Zero] = nextBoard[Zero - 3];
                nextBoard[Zero - 3] = 0;
                addToUnverified(unverified, history, weights, depth, parentUnverifiedIndexes, nextBoard);
            }
            //down
            if (Zero / 3 < 2) {
                int[] nextBoard = currentBoard.clone();
                nextBoard[Zero] = nextBoard[Zero + 3];
                nextBoard[Zero + 3] = 0;
                addToUnverified(unverified, history, weights, depth, parentUnverifiedIndexes, nextBoard);
            }
            //left
            if (Zero % 3 > 0) {
                int[] nextBoard = currentBoard.clone();
                nextBoard[Zero] = nextBoard[Zero - 1];
                nextBoard[Zero - 1] = 0;
                addToUnverified(unverified, history, weights, depth, parentUnverifiedIndexes, nextBoard);
            }
            //right
            if (Zero % 3 < 2) {
                int[] nextBoard = currentBoard.clone();
                nextBoard[Zero] = nextBoard[Zero + 1];
                nextBoard[Zero + 1] = 0;
                addToUnverified(unverified, history, weights, depth, parentUnverifiedIndexes, nextBoard);
            }
        }
    }

    private int getDepth(ArrayList<Integer> parentHistoryNumbers, int parentNumber) {
        int depth = 0;
            while (parentNumber != -1) {
                parentNumber = parentHistoryNumbers.get(parentNumber);
                depth++;
            }
        return depth;
    }

    private int compare(int[] board1, int [] board2){
        int wrong = 0;
        for (int i = 0; i < 9; i++){
            if (board1[i] != board2[i]){
                wrong++;
            }
        }
        return wrong;
    }

    private void addToUnverified(ArrayList<int[]> unverified, ArrayList<int[]> history, ArrayList<Integer> weight,
                                 int depth, ArrayList<Integer> parentUnverifiedNumbers, int[] nextBoard) {
        for (int[] historyBoard : history) {
            if (compare(historyBoard, nextBoard) == 0 ) {
                return;
            }
        }
        int nextWeight = compare(nextBoard, rightBoard) + depth + 1;
        weight.add(nextWeight);
        unverified.add(nextBoard);
        parentUnverifiedNumbers.add(history.size() - 1);
    }

    private void writeBoard(int[] board){
        System.out.print("\t=========");
        for (int i = 0; i < 9; i++){
            if (i%3 == 0){
                System.out.println();
            }
            System.out.print("\t" + board[i]);
        }
        System.out.println();
    }
}