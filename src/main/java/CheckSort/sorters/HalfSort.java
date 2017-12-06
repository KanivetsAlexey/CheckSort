package CheckSort.sorters;

/**
 * Created by Kanivets on 11.10.2017
 */
public class HalfSort extends Sort {

    private Integer[] listOfElements;

    @Override
    public Integer[] sortList(Integer[] listOfElements) {
        this.listOfElements = listOfElements;
        halfRecSort(0, listOfElements.length-1);
        return listOfElements;
    }

    /**
     * recursive func for sort
     * @param left left side of zone for sort
     * @param right right side of zone for sort
     */
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
