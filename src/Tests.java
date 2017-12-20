import org.junit.Assert;
import org.junit.Test;


public class Tests {

    @Test
    public void Test1() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        Fifteen test = new Fifteen(testBoard);
        System.out.println("Solution for Test1 :");
        test.Solve();
        Assert.assertEquals(1, test.solverBoards.size());
    }

    @Test
    public void Test2() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 0, 7, 8};
        Fifteen test = new Fifteen(testBoard);
        System.out.println("Solution for Test2 :");
        test.Solve();
        Assert.assertEquals(3, test.solverBoards.size());
    }

    @Test
    public void Test3() {
        int[] testBoard = {2, 5, 0, 7, 8, 4, 1, 3, 6};
        Fifteen test = new Fifteen(testBoard);
        System.out.println("Solution for Test3 :");
        test.Solve();
        Assert.assertEquals(23, test.solverBoards.size());
    }

    @Test
    public void Test4() {
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Fifteen test = new Fifteen(testBoard);
        System.out.println("Solution for Test4 :");
        test.Solve();
        Assert.assertEquals(19, test.solverBoards.size());
    }

    @Test
    public void TestGetWeight1() {
        int[] testBoard = {1, 2, 3, 4, 0, 6, 7, 8, 5};
        Board test = new Board(testBoard);
        Assert.assertEquals(4, test.getWeight());
    }

    @Test
    public void TestGetWeight2() {
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        Assert.assertEquals(14, test.getWeight());
    }

    @Test
    public void TestGetEmptyCellIndex() {
        int[] testBoard = {1, 6, 2, 8, 0, 3, 5, 7, 4};
        Board test = new Board(testBoard);
        Assert.assertEquals(4, test.getEmptyCellIndex());
    }

}