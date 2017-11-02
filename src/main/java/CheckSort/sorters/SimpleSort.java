package CheckSort.sorters;

import java.util.Arrays;

/**
 * Created by Alexey on 11.10.2017
 */
public class SimpleSort extends Sort {

    private Integer[] listOfElements;

    @Override
    public long sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        long start = System.nanoTime();
        Arrays.sort(this.listOfElements);
        long end = System.nanoTime();
        return (end-start);
    }

    public Integer[] sortListForTest(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        Arrays.sort(this.listOfElements);
        return listOfElements;
    }
}
