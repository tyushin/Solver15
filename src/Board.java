public class Board {
    private int[] positions;
    private int scale;
    private int weight;
    private int emptyCellIndex;
    private int depth;
    private int parentId;

    public Board(int[] positions){
        this.positions = positions;
        this.scale = (int) Math.sqrt(positions.length);
        this.emptyCellIndex = findEmptyCellIndex();
        this.parentId = -1;
        this.depth = 0;
        this.weight = findWeight();
    }

    public Board(int[] positions, int parentId, int depth){
        this.positions = positions;
        this.scale = (int) Math.sqrt(positions.length);
        this.emptyCellIndex = findEmptyCellIndex();
        this.parentId = parentId;
        this.depth = depth;
        this.weight = findWeight() + depth;
    }

    public Board move(int from, int to, int parentId){
        int[] newPosition = positions.clone();
        int fromValue = newPosition[from];
        newPosition[from] = newPosition[to];
        newPosition[to] = fromValue;
        return new Board(newPosition, parentId, depth+1);
    }

    private int findWeight(){
        int weight = 0;
        for(int i = 0; i < positions.length; i++){
            if (positions[i] == 0) {
                weight += (scale - 1 - i / scale) + (scale - i % scale);
            }
            else {
                weight += Math.abs(Math.floor(i / scale) - Math.floor((positions[i] - 1) / scale))
                        + Math.abs(i % scale - (positions[i] - 1) % scale);
            }
        }
        return weight;
    }

    private int findEmptyCellIndex(){
        int emptyCellIndex = 0;
        for(int i = 0; i < positions.length; i++){
            if (positions[i] == 0){
                emptyCellIndex = i;
                break;
            }
        }
        return emptyCellIndex;
    }

    public boolean isRight(){
        int[] rightPositions = new int[scale*scale];
        for (int i = 0; i < scale*scale - 1; i++){
            rightPositions[i] = i+1;
        }
        rightPositions[scale*scale - 1] = 0;
        Board rightBoard = new Board(rightPositions);
        return equals(rightBoard);
    }

    public void writeBoard(){
        System.out.print("\t============");
        for (int i = 0; i < scale*scale; i++){
            if (i%scale == 0){
                System.out.println();
            }
            System.out.print("\t" + positions[i]);
        }
        System.out.println();
    }

    public int[] getPositions(){
        return positions;
    }

    public int getParentId(){
        return parentId;
    }

    public int getLength(){
        return positions.length;
    }

    public double getScale(){
        return scale;
    }

    public int getWeight(){
        return weight;
    }

    public int getEmptyCellIndex(){
        return emptyCellIndex;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Board board = (Board) obj;
        for (int i: positions) {
            if (positions[i] != board.positions[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return weight + emptyCellIndex*57;
    }
}
