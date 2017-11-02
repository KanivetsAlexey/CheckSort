package CheckSort;

import CheckSort.sorters.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexey on 26.10.2017
 */
public class SortTest {

    private Integer[] elements = {2,5,3,1,4};
    private Integer[] sortedElements = {1,2,3,4,5};

    @Test
    public void boubleDownTest(){
        BoubleSortDown boubleSortDown = new BoubleSortDown();
        Integer[] sorted = boubleSortDown.sortListForTest(elements);
        Assert.assertArrayEquals(sortedElements, sorted);
    }

    @Test
    public void boubleUpTest(){
        BoubleSortUp boubleSortUp = new BoubleSortUp();
        Integer[] sorted = boubleSortUp.sortListForTest(elements);
        Assert.assertArrayEquals(sortedElements, sorted);
    }

    @Test
    public void halfSortTest(){
        HalfSort halfSort = new HalfSort();
        Integer[] sorted = halfSort.sortListForTest(elements);
        Assert.assertArrayEquals(sortedElements, sorted);
    }

    @Test
    public void margeSortTest(){
        MargeSort margeSort = new MargeSort();
        Integer[] sorted = margeSort.sortListForTest(elements);
        Assert.assertArrayEquals(sortedElements, sorted);
    }

    @Test
    public void simpleSortTest(){
        SimpleSort simpleSort = new SimpleSort();
        Integer[] sorted = simpleSort.sortListForTest(elements);
        Assert.assertArrayEquals(sortedElements, sorted);
    }

    @Test
    public void swapSortTest(){
        SwapSort swapSort = new SwapSort();
        Integer[] sorted = swapSort.sortListForTest(elements);
        Assert.assertArrayEquals(sortedElements, sorted);
    }
}
