import java.util.*;

public class Solver {
    private Board board;
    private int scale;
    List<Board> solverBoards;
    public Solver(Board board){
        this.board = board;
        this.scale = (int) board.getScale();
        Solve();
    }
    private void Solve(){
        //set initial conditions
        solverBoards = new LinkedList<>();
        Queue<Board> unverified = new PriorityQueue<>(boardComparator);
        unverified.add(board);
        List<Board> history  = new LinkedList<>();

        while (true){
            //find the next board to check
            Board currentBoard = unverified.poll();
            history.add(currentBoard);
            //check for solution
            if (currentBoard.isRight()){
                solverBoards.add(currentBoard);
                int nextIndex = currentBoard.getParentId();
                while (nextIndex != -1) {
                    currentBoard = history.get(nextIndex);
                    solverBoards.add(currentBoard);
                    nextIndex = currentBoard.getParentId();
                }
                //output correct moves
                for (int i = solverBoards.size() - 1; i >= 0; i--){
                    solverBoards.get(i).writeBoard();
                }
                return;
            }
            int emptyCell = currentBoard.getEmptyCellIndex();
            //up
            if (emptyCell / scale > 0){
                unverified.add(currentBoard.move(emptyCell,emptyCell - scale, history.size() - 1));
            }
            //down
            if (emptyCell / scale < scale - 1){
                unverified.add(currentBoard.move(emptyCell,emptyCell + scale, history.size() - 1));
            }
            //left
            if (emptyCell % scale > 0){
                unverified.add(currentBoard.move(emptyCell,emptyCell - 1, history.size() - 1));
            }
            //right
            if (emptyCell % scale < scale - 1){
                unverified.add(currentBoard.move(emptyCell,emptyCell + 1, history.size() - 1));
            }
        }
    }

    public static Comparator<Board> boardComparator = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.getWeight() - o2.getWeight();
        }
    };
}
