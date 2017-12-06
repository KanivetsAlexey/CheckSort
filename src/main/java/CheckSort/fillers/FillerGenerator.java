package CheckSort.fillers;

import CheckSort.analyzer.Filler;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Kanivets on 22.10.2017
 */
public class FillerGenerator {

    @Filler
    public static Integer[] generateSortedFiller(Integer size){
        size = chackerOfSize(size);
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size);
        }
        Arrays.sort(fill);
        return fill;
    }

    @Filler
    public static Integer[] generateSortedWithRElemFiller(Integer size){
        size = chackerOfSize(size);
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
    public static Integer[] generateRevSortedFiller(Integer size){
        size = chackerOfSize(size);
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size-1);
        }
        Arrays.sort(fill, Collections.reverseOrder());
        return fill;
    }

    @Filler
    public static Integer[] generateRandomFiller(Integer size){
        size = chackerOfSize(size);
        Integer[] fill = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            fill[i] = random.nextInt(size-1);
        }
        return fill;
    }

    private static int chackerOfSize(Integer size){
        if(size == null){
            return Integer.parseInt(JOptionPane.showInputDialog("Insert valid size(not null, >0)", size));
        }else if(size < 0){
            return Math.abs(size);
        }
        return size;
    }
}
