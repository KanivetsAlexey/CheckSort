package CheckSort.analyzer;

import CheckSort.fillers.FillerGenerator;
import CheckSort.sorters.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexey on 22.10.2017
 */
public class GenerateAnalyz {

    private ArrayList<Class> classesWithSort = new ArrayList<>();
    private ArrayList<Method> annotatedMethods = new ArrayList<>();
    private Class classWithAnnotMethods;
    private ArrayList<Long> listNanoTime = new ArrayList<>();
    private final int SIZE = 100;

    public void generateData() {
        reflectionHelper();

        for(Method mth : annotatedMethods){
            try{
                for(Class clazz : classesWithSort){
                    for(int i = 10; i < SIZE + 10; i++){
                        Class[] paramInt = new Class[1];
                        paramInt[0] = Integer.TYPE;
                        Object obj = classWithAnnotMethods.newInstance();
                        Object currObj = clazz.newInstance();
                        Method method = clazz.getDeclaredMethod("sortList", Integer[].class);
                        listNanoTime.add(timeNormalyzer(
                                (long)method.invoke(currObj, mth.invoke(obj, i))));
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private long timeNormalyzer(long time){
        if(time >= 10000000){
            time = time / 10;
        }
        return time;
    }

    private void getAnnotatedMethodsFromClass() {
        Class type = FillerGenerator.class;
        Class annotation = Filler.class;
        Class<?> cls = type;
        while (cls != Object.class) {
            final List<Method> allMethods = new ArrayList<>(Arrays.asList(cls.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    classWithAnnotMethods = cls;
                    annotatedMethods.add(method);
                    System.out.println("\t" + method.getName());
                }
            }
            cls = cls.getSuperclass();
        }
    }

    private void getChildClasses() throws NoSuchFieldException, IllegalAccessException {
        ClassLoader myCL = Thread.currentThread().getContextClassLoader();
        while (myCL != null) {
            System.out.println("ClassLoader: " + myCL);
            Iterator iter = list(myCL);
            while (iter.hasNext()) {
                Class cls = (Class)iter.next();
                if((cls.getSuperclass() != null) && cls.getSuperclass().equals(Sort.class)){
                    classesWithSort.add(cls);
                    System.out.println("\t" + cls.getName());
                }
            }
            myCL = myCL.getParent();
        }
    }

    private Iterator list(ClassLoader CL) throws NoSuchFieldException, IllegalAccessException {
        Class CL_class = CL.getClass();
        while (CL_class != java.lang.ClassLoader.class) {
            CL_class = CL_class.getSuperclass();
        }
        java.lang.reflect.Field ClassLoader_classes_field = CL_class
                .getDeclaredField("classes");
        ClassLoader_classes_field.setAccessible(true);
        List classes = (List) ClassLoader_classes_field.get(CL);
        return classes.iterator();
    }

    private void objectCreator(){
        BoubleSortDown boubleSortDown = new BoubleSortDown();
        BoubleSortUp boubleSortUp = new BoubleSortUp();
        HalfSort halfSort = new HalfSort();
        MargeSort margeSort =  new MargeSort();
        SimpleSort simpleSort = new SimpleSort();
        SwapSort swapSort = new SwapSort();
    }

    private void reflectionHelper(){
        objectCreator();
        FillerGenerator fillerGenerator = new FillerGenerator();
        getAnnotatedMethodsFromClass();
        try {
            getChildClasses();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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

}
