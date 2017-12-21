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
        solverBoards = new ArrayList<>();
        Queue<Board> unverified = new PriorityQueue<>(boardComparator);
        unverified.add(board);
        Set<Board> history  = new HashSet<>();

        while (true){
            //find the next board to check
            Board currentBoard = unverified.poll();
            history.add(currentBoard);
            //check for solution
            if (currentBoard.isRight()){
                solverBoards.add(currentBoard);
                while (currentBoard.getParentBoard() != null) {
                    currentBoard = currentBoard.getParentBoard();
                    solverBoards.add(currentBoard);
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
                unverified.add(currentBoard.move(emptyCell,emptyCell - scale));
            }
            //down
            if (emptyCell / scale < scale - 1){
                unverified.add(currentBoard.move(emptyCell,emptyCell + scale));
            }
            //left
            if (emptyCell % scale > 0){
                unverified.add(currentBoard.move(emptyCell,emptyCell - 1));
            }
            //right
            if (emptyCell % scale < scale - 1){
                unverified.add(currentBoard.move(emptyCell,emptyCell + 1));
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
