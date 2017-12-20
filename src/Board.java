
public class Board {

    private int[] positions;
    private double scale;
    private int weight;
    private int emptyCellIndex;

    public Board(int[] positions){
        this.positions = positions;
        scale = Math.sqrt(positions.length);
        weight = findWeight();
        emptyCellIndex = findEmptyCellIndex();
    }

    public int[] getPositions(){
        return positions;
    }

    public int getLength(){
        return positions.length;
    }

    public double getScale(){
        return scale;
    }

    public Board move(int from, int to){
        int[] newPosition = positions.clone();
        int fromValue = newPosition[from];
        newPosition[from] = newPosition[to];
        newPosition[to] = fromValue;
        return new Board(newPosition);
    }

    private int findWeight(){
        int weight = 0;
        for(int i = 0; i < positions.length; i++){
            if (positions[i] == 0) {
                weight += (scale - 1 - i / scale) + (scale - i % scale);
            }
            else {
                weight += Math.abs(Math.floor(i / scale) - Math.floor((positions[i] - 1) / scale)) + Math.abs(i % scale - (positions[i] - 1) % scale);
            }
        }
        return weight;
    }

    public int getWeight(){
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

    public int getEmptyCellIndex(){
        return emptyCellIndex;
    }






}
