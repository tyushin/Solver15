import org.junit.Assert;
import org.junit.Test;

public class Tests {

    @Test
    public void Test11() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        System.out.println("Solution for Test11 :");
        Solver test = new Solver(new Board(testBoard));
        Assert.assertEquals(1, test.solverBoards.size());
    }

    @Test
    public void Test12() {
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        System.out.println("Solution for Test12 :");
        Solver test = new Solver(new Board(testBoard));
        Assert.assertEquals(19, test.solverBoards.size());
    }

    @Test
    public void Test13() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ,15, 0};
        System.out.println("Solution for Test13 :");
        Solver test = new Solver(new Board(testBoard));
        Assert.assertEquals(1, test.solverBoards.size());
    }

    @Test
    public void Test14() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 13 ,14, 15};
        System.out.println("Solution for Test14 :");
        Solver test = new Solver(new Board(testBoard));
        Assert.assertEquals(4, test.solverBoards.size());
    }

    @Test
    public void Test15() {
        int[] testBoard = {2, 8, 0, 4, 3, 13, 10, 11, 1, 14, 9, 7, 6, 15, 5, 12};
        System.out.println("Solution for Test15 :");
        Solver test = new Solver(new Board(testBoard));
        Assert.assertEquals(37, test.solverBoards.size());
    }

    @Test
    public void TestGetWeight1() {
        int[] testBoard = {1, 2, 3, 4, 0, 6, 7, 8, 5};
        Board test = new Board(testBoard);

        Assert.assertEquals(5, test.getWeight());
        Assert.assertEquals(2, test.move(4,8,-1).getWeight());
    }

    @Test
    public void TestGetWeight2() {
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        Assert.assertEquals(15, test.getWeight());
    }

    @Test
    public void TestGetEmptyCellIndex() {
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        Assert.assertEquals(4, test.getEmptyCellIndex());
    }
    @Test
    public void TestMove(){
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        int[] resultPositions = {1, 0, 2, 8, 6, 3, 5, 7, 4};
        Board result = new Board(resultPositions);
        Assert.assertEquals(result, test.move(1,4,-1));
    }

    @Test
    public void TestEquals(){
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        int[] unequalBoard = {1, 0, 2, 8, 6, 3, 5, 7, 4};
        Board unequal = new Board(unequalBoard);
        int[] equalBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board equal = new Board(equalBoard);
        Assert.assertEquals(false, test.equals(unequal));
        Assert.assertEquals(true, test.equals(equal));
    }

    @Test
    public void TestIsRight(){
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        int[] rightBoard1 = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        Board rightl = new Board(rightBoard1);
        int[] rightBoard2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        Board right2 = new Board(rightBoard2);
        Assert.assertEquals(false, test.isRight());
        Assert.assertEquals(true, rightl.isRight());
        Assert.assertEquals(true, right2.isRight());

    }

}