package CheckSort.sorters;

/**
 * Created by Alexey on 19.10.2017
 */
public class BoubleSortDown extends Sort {

    private Integer[] listOfElements;

    @Override
    public long sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        long start = System.nanoTime();
        for (int i = 0; i < listOfElements.length-1; i++) {
            for (int j = listOfElements.length-1; j > 0; j--) {
                if (listOfElements[j] < listOfElements[j-1]) {
                    listOfElements = swapElements(listOfElements, j, j-1);
                }
            }
        }
        long end = System.nanoTime();
        return (end-start);
    }

    public Integer[] sortListForTest(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        for (int i = 0; i < listOfElements.length-1; i++) {
            for (int j = listOfElements.length-1; j > 0; j--) {
                if (listOfElements[j] < listOfElements[j-1]) {
                    listOfElements = swapElements(listOfElements, j, j-1);
                }
            }
        }
        return listOfElements;
    }
}
