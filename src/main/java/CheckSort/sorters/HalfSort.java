package CheckSort.sorters;

/**
 * Created by Alexey on 11.10.2017
 */
public class HalfSort extends Sort {

    private Integer[] listOfElements;

    @Override
    public long sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        long start = System.nanoTime();
        halfRecSort(0, listOfElements.length-1);
        long end = System.nanoTime();
        return (end-start);
    }

    public Integer[] sortListForTest(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        halfRecSort(0, listOfElements.length-1);
        return listOfElements;
    }

    private void halfRecSort(int left, int right){
        int s = listOfElements[(int) Math.floor((left + right)/2)];
        int i = left, j = right;
        while(i <= j){
            while(listOfElements[i] < s) {
                i++;
            }
            while(listOfElements[j] > s) {
                j--;
            }
            if(i <= j) {
                listOfElements = swapElements(listOfElements, i, j);
                i++;
                j--;
            }
        }
        if(left < j) {
            halfRecSort(left, j);
        }
        if(i < right) {
            halfRecSort(i, right);
        }
    }
}
