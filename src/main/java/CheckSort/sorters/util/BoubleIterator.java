package CheckSort.sorters.util;

import java.util.Iterator;

/**
 * Created by Alexey on 09.11.2017
 */
public abstract class BoubleIterator implements Iterator<Integer> {
    protected Integer[] storage;
    private final int startIndex;
    protected int count;

    /**
     * @param storage the array to be iterated.
     * @param startIndex iteration starts from the element with this index.
     */
    protected BoubleIterator(Integer[] storage, int startIndex){
        this.startIndex = startIndex;
        this.storage = storage;
        count = startIndex;
    }

    public int getCount() {
        return count;
    }

    /**
     * Assigns counter to start index.
     */
    public void restart(){
        count = startIndex;
    }
}
