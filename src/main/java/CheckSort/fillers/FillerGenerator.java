package CheckSort.fillers;

import CheckSort.analyzer.Filler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Alexey on 22.10.2017
 */
public class FillerGenerator {

    @Filler
    public static Integer[] generateSortedFiller(int size){
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size);
        }
        Arrays.sort(fill);
        return fill;
    }

    @Filler
    public static Integer[] generateSortedWithRElemFiller(int size){
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size-1);
        }
        Arrays.sort(fill);
        fill[size-1] = random.nextInt(size-1);
        return fill;
    }

    @Filler
    public static Integer[] generateRevSortedFiller(int size){
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size-1);
        }
        Arrays.sort(fill, Collections.reverseOrder());
        return fill;
    }

    @Filler
    public static Integer[] generateRandomFiller(int size){
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size-1);
        }
        return fill;
    }
}
