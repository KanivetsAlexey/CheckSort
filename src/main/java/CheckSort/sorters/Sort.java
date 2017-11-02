package CheckSort.sorters;

/**
 * Created by Alexey on 11.10.2017
 */
public abstract class Sort {

    public abstract long sortList(Integer[] listOfElements);

    public Integer[] swapElements(Integer[] listOfElements,int i, int j) {
        int tmp = listOfElements[i];
        listOfElements[i] = listOfElements[j];
        listOfElements[j] = tmp;
        return listOfElements;
    }
}
