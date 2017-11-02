package CheckSort.sorters;

/**
 * Created by Alexey on 11.10.2017
 */
public class MargeSort extends Sort {

    private Integer[] listOfElements;

    @Override
    public long sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        long start = System.nanoTime();
        mergeSort(0, listOfElements.length);
        long end = System.nanoTime();
        return (end-start);
    }

    public Integer[] sortListForTest(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        mergeSort(0, listOfElements.length);
        return listOfElements;
    }

    private void mergeSort(int low, int high) {
        if (low + 1 < high) {
            int mid = (low + high) >>> 1;
            mergeSort(low, mid);
            mergeSort(mid, high);

            int size = high - low;
            Integer[] b = new Integer[size];
            int i = low;
            int j = mid;

            for (int k = 0; k < size; k++) {
                if ((j >= high) || (i < mid) && (listOfElements[i] < listOfElements[j])) {
                    b[k] = listOfElements[i++];
                } else {
                    b[k] = listOfElements[j++];
                }
            }
            System.arraycopy(b, 0, listOfElements, low, size);
        }
    }
}
