package CheckSort.sorters;

/**
 * Created by Kanivets on 11.10.2017
 */
public abstract class Sort {
    /**
     *
     * @param listOfElements list for sort
     * @return time of sort
     */
    public abstract Integer[] sortList(Integer[] listOfElements);

    /**
     *
     * @param listOfElements list from what we swap
     * @param i first elem
     * @param j second elem
     * @return list with swapped elems
     */
    protected Integer[] swapElements(Integer[] listOfElements,int i, int j) {
        int tmp = listOfElements[i];
        listOfElements[i] = listOfElements[j];
        listOfElements[j] = tmp;
        return listOfElements;
    }
}
