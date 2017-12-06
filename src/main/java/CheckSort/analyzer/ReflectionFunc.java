package CheckSort.analyzer;

import CheckSort.fillers.FillerGenerator;
import CheckSort.sorters.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kanivets on 02.11.2017
 */
public class ReflectionFunc {
    /**
     * addicted func with reflection
     */
    public void reflectionHelper(GenerateAnalyz generateAnalyz){
        objectCreator();
        getAnnotatedMethodsFromClass(generateAnalyz);
        try {
            getChildClasses(generateAnalyz);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all @Filler methods
     */
    private void getAnnotatedMethodsFromClass(GenerateAnalyz generateAnalyz) {
        Class type = FillerGenerator.class;
        Class annotation = Filler.class;
        Class<?> cls = type;
        while (cls != Object.class) {
            final List<Method> allMethods = new ArrayList<>(Arrays.asList(cls.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    generateAnalyz.setClassWithAnnotMethods(cls);
                    generateAnalyz.setAnnotatedMethods(method);
                    System.out.println("\t" + method.getName());
                }
            }
            cls = cls.getSuperclass();
        }
    }

    /**
     * get childs of Sort class
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void getChildClasses(GenerateAnalyz generateAnalyz) throws NoSuchFieldException, IllegalAccessException {
        ClassLoader myCL = Thread.currentThread().getContextClassLoader();
        while (myCL != null) {
            System.out.println("ClassLoader: " + myCL);
            Iterator iter = list(myCL);
            while (iter.hasNext()) {
                Class cls = (Class)iter.next();
                //Class superClassOfClass = cls;
                //while(superClassOfClass != Object.class){
                    //superClassOfClass = superClassOfClass.getSuperclass();
                    if((cls.getSuperclass() != null) && cls.getSuperclass().equals(Sort.class)){
                        if(cls.equals(BoubleSort.class)){
                            getChildClassesForBoubleSort(generateAnalyz);
                            generateAnalyz.setClassesWithSort(cls);
                        } else {
                            generateAnalyz.setClassesWithSort(cls);
                            generateAnalyz.setClassesWithBoubleSort(cls);
                            System.out.println("\t" + cls.getName());
                        }
                        /*if((superClassOfClass != null) && superClassOfClass.equals(Sort.class)){
                            generateAnalyz.setClassesWithSort(cls);
                            break;
                        }*/
                    }
                }
           // }
            myCL = myCL.getParent();
        }
    }

    private void getChildClassesForBoubleSort(GenerateAnalyz generateAnalyz) throws NoSuchFieldException, IllegalAccessException {
        ClassLoader myCL = Thread.currentThread().getContextClassLoader();
        while (myCL != null) {
            System.out.println("ClassLoader: " + myCL);
            Iterator iter = list(myCL);
            while (iter.hasNext()) {
                Class cls = (Class)iter.next();
                if((cls.getSuperclass() != null) && cls.getSuperclass().equals(BoubleSort.class)){
                    generateAnalyz.setClassesWithBoubleSort(cls);
                    System.out.println("\t" + cls.getName());
                }
            }
            myCL = myCL.getParent();
        }
    }

    /**
     * iterator under ClassLoader
     * @param CL current ClassLoader
     * @return Iterator
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
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

    /**
     * Default init of obj
     */
    private void objectCreator(){
        BoubleSortDown boubleSortDown = new BoubleSortDown();
        BoubleSortUp boubleSortUp = new BoubleSortUp();
        HalfSort halfSort = new HalfSort();
        MargeSort margeSort =  new MargeSort();
        SimpleSort simpleSort = new SimpleSort();
        SwapSort swapSort = new SwapSort();
        FillerGenerator fillerGenerator = new FillerGenerator();
    }


}
