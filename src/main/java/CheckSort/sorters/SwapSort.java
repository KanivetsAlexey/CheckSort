package CheckSort.sorters;

/**
 * Created by Alexey on 11.10.2017
 */
public class SwapSort extends Sort {

    private Integer[] listOfElements;

    @Override
    public long sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        long start = System.nanoTime();
        for (int i = 0; i < listOfElements.length-1; i++) {
            int minElement = listOfElements[i];
            int indexOfmin = i;
            for (int j = i+1; j < listOfElements.length; j++) {
                if (minElement > listOfElements[j]) {
                    minElement = listOfElements[j];
                    indexOfmin = j;
                }
            }
            listOfElements = swapElements(listOfElements, i, indexOfmin);
        }
        long end = System.nanoTime();
        return (end-start);
    }

    public Integer[] sortListForTest(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        for (int i = 0; i < listOfElements.length-1; i++) {
            int minElement = listOfElements[i];
            int indexOfmin = i;
            for (int j = i+1; j < listOfElements.length; j++) {
                if (minElement > listOfElements[j]) {
                    minElement = listOfElements[j];
                    indexOfmin = j;
                }
            }
            listOfElements = swapElements(listOfElements, i, indexOfmin);
        }
        return listOfElements;
    }
}
