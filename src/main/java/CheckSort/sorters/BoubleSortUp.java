package CheckSort.sorters;

/**
 * Created by Alexey on 11.10.2017
 */
public class BoubleSortUp extends Sort {

    private Integer[] listOfElements;

    @Override
    public long sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        long start = System.nanoTime();
        for (int i = 0; i < listOfElements.length-1; i++) {
            for (int j = 0; j < listOfElements.length-1; j++) {
                if (listOfElements[j] > listOfElements[j+1]) {
                    listOfElements = swapElements(listOfElements, j, j+1);
                }
            }
        }
        long end = System.nanoTime();
        return (end-start);
    }

    public Integer[] sortListForTest(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        for (int i = 0; i < listOfElements.length-1; i++) {
            for (int j = 0; j < listOfElements.length-1; j++) {
                if (listOfElements[j] > listOfElements[j+1]) {
                    listOfElements = swapElements(listOfElements, j, j+1);
                }
            }
        }
        return listOfElements;
    }
}
