package CheckSort.analyzer;

import CheckSort.excel.LineChart;
import CheckSort.sorters.BoubleSort;
import CheckSort.sorters.BoubleSortDown;
import CheckSort.sorters.BoubleSortUp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kanivets on 22.10.2017
 */
public class GenerateAnalyz {

    private ArrayList<Class> classesWithSort = new ArrayList<>();
    private ArrayList<Class> classesWithBoubleSort = new ArrayList<>();
    private ArrayList<Method> annotatedMethods = new ArrayList<>();
    private Class classWithAnnotMethods;
    private ArrayList<Long> listNanoTime = new ArrayList<>();
    private final int SIZE = 100;

    /**
     * generates data for excel used reflection API
     */
    public void startAnalyzis() {
        ReflectionFunc rf = new ReflectionFunc();
        rf.reflectionHelper(this);
        Timer timer = new Timer();

        for(Method mth : annotatedMethods){
            try{
                for(Class clazz : classesWithSort){
                    for(int i = 10; i < SIZE + 10; i++){
                        Class[] paramInt = new Class[1];
                        paramInt[0] = Integer.TYPE;
                        Object obj = classWithAnnotMethods.newInstance();
                        timeFinder(mth, clazz, i, timer, obj);
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        LineChart lineChart = new LineChart();
        try {
            lineChart.startDrowing(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void timeFinder(Method mth, Class clazz, int i, Timer timer, Object obj) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if(clazz != BoubleSort.class){
            timeFinderAll(mth, clazz, i, timer, obj);
        }else{
            timeFinderForBoubleUp(mth, clazz, i, timer, obj);
            timeFinderForBoubleDown(mth, clazz, i, timer, obj);
        }
    }

    public void timeFinderAll(Method mth, Class clazz, int i, Timer timer, Object obj) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object currObj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sortList", Integer[].class);
        timer.start();
        method.invoke(currObj, mth.invoke(obj, i));
        listNanoTime.add(timeNormalyzing(timer.stop()));
    }

    public void timeFinderForBoubleUp(Method mth, Class clazz, int i, Timer timer, Object obj) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object currObj = BoubleSortUp.class.newInstance();
        Method method = clazz.getDeclaredMethod("sortList", Integer[].class);
        timer.start();
        method.invoke(currObj, mth.invoke(obj, i));
        listNanoTime.add(timeNormalyzing(timer.stop()));
    }

    public void timeFinderForBoubleDown(Method mth, Class clazz, int i, Timer timer, Object obj) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object currObj = BoubleSortDown.class.newInstance();
        Method method = clazz.getDeclaredMethod("sortList", Integer[].class);
        timer.start();
        method.invoke(currObj, mth.invoke(obj, i));
        listNanoTime.add(timeNormalyzing(timer.stop()));
    }
    /**
     * Normalize time
     * @param time time that must be checked for normalization
     * @return normalized/not time
     */
    private long timeNormalyzing(long time){
        if(time >= 300000){
            time = time / 10;
        }
        return time;
    }


    public ArrayList<Class> getClassesWithSort() {
        return classesWithSort;
    }

    public ArrayList<Method> getAnnotatedMethods() {
        return annotatedMethods;
    }

    public ArrayList<Long> getListNanoTime() {
        return listNanoTime;
    }

    public Class getClassWithAnnotMethods() {
        return classWithAnnotMethods;
    }

    public int getSIZE() {
        return SIZE;
    }

    public ArrayList<Class> getClassesWithBoubleSort() {
        return classesWithBoubleSort;
    }

    public void setAnnotatedMethods(Method annotatedMethods) {
        this.annotatedMethods.add(annotatedMethods);
    }

    public void setClassesWithSort(Class classesWithSort) {
        this.classesWithSort.add(classesWithSort);
    }

    public void setClassWithAnnotMethods(Class classWithAnnotMethods) {
        this.classWithAnnotMethods = classWithAnnotMethods;
    }

    public void setListNanoTime(ArrayList<Long> listNanoTime) {
        this.listNanoTime = listNanoTime;
    }

    public void setClassesWithBoubleSort(Class classesWithBoubleSort) {
        this.classesWithBoubleSort.add(classesWithBoubleSort);
    }
}
