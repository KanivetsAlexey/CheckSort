package CheckSort.sorters;

import CheckSort.sorters.util.BoubleIterator;

/**
 * Created by Kanivets on 11.10.2017
 */
public class BoubleSortUp extends BoubleSort {
    /**
     * Iterates from beginning of the array to the last element.
     *
     * @param storage the array to be iterated.
     * @return iterator which will go through array.
     */
    @Override
    protected BoubleIterator iterator(Integer[] storage) {
        return new BoubleIterator(storage, 0) {
            @Override
            public boolean hasNext() {
                return count<storage.length-1;
            }

            @Override
            public Integer next() {
                return storage[count++];
            }
        };
    }
}
