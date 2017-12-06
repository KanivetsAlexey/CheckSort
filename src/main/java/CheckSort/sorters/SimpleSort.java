package CheckSort.sorters;

import java.util.Arrays;

/**
 * Created by Kanivets on 11.10.2017
 */
public class SimpleSort extends Sort {

    private Integer[] listOfElements;

    @Override
    public Integer[] sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        Arrays.sort(this.listOfElements);
        return listOfElements;
    }
}
