package CheckSort.sorters;

import CheckSort.sorters.util.BoubleIterator;

/**
 * Created by Kanivets on 19.10.2017
 */
public class BoubleSortDown extends BoubleSort{

    /**
     * Iterates from end of the array to the first element.
     *
     * @param storage the array to be iterated.
     * @return iterator which will go through array.
     */
    @Override
    protected BoubleIterator iterator(Integer[] storage) {
        return new BoubleIterator(storage, storage.length-1) {
            @Override
            public boolean hasNext() {
                return count>0;
            }

            @Override
            public Integer next() {
                return storage[count--];
            }
        };
    }
}
