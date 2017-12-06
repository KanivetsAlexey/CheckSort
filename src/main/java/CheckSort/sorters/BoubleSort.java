package CheckSort.sorters;

import CheckSort.sorters.util.BoubleIterator;

/**
 * Created by Alexey on 09.11.2017
 */
public abstract class BoubleSort extends Sort {
    /**
     * Sorts array using bubble sort.
     * @param listOfElements the array to be sorted.
     */
    @Override
    public Integer[] sortList(Integer[] listOfElements) {
        int n = listOfElements.length;
        for (int i = 0; i < n - 1; i++) {
            boolean isBeenSwaps = false;
            BoubleIterator iterator = iterator(listOfElements);
            int firstIndex= iterator.getCount();
            iterator.next();
            int direction = iterator.getCount() - firstIndex;
            iterator.restart();
            while (iterator.hasNext()) {
                int lowerIndex = iterator.getCount() + (direction-1)/2;
                if (listOfElements[lowerIndex] > listOfElements[lowerIndex +1]){
                    swapElements(listOfElements, lowerIndex , lowerIndex+1);
                    isBeenSwaps = true;
                }
                iterator.next();
            }
            if (!isBeenSwaps) break;
        }
        return listOfElements;
    }

    /**
     * @param storage the array to be iterated.
     * @return iterator, which will go through array.
     */
    abstract protected BoubleIterator iterator(Integer[] storage);

}
