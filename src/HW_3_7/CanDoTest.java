package HW_3_7;

import ru.gb.current.Container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class CanDoTest {
    static void start(Class testClass) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Method beforeMethod = null;
        Method afterMethod = null;
        ArrayList<Method> testMethods = new ArrayList<>();

        Class<TestClass> testClassClass = TestClass.class;
        System.out.println(testClassClass);
        TestClass testClassInst = new TestClass();

        Constructor<TestClass> constructor = testClassClass.getDeclaredConstructor();
        System.out.println(constructor);


        Object object = testClass.getDeclaredConstructor().newInstance();


        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            } else if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) {
                    beforeMethod = m;
                } else {
                    throw new RuntimeException("Должно быть не более одного метода с аннотацией @BeforeSuite");
                }
            } if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) {
                    afterMethod = m;
                } else {
                    throw new RuntimeException("Должно быть не более одного метода с аннотацией @AfterSuite");
                }
            }
        }
        System.out.println("Object created in \"getDeclaredConstructor().newInstance()\"");

        if (beforeMethod != null) {
            beforeMethod.invoke(object);
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        for (Method m : testMethods) {
            m.invoke(object);
        }

        if (afterMethod != null) {
            afterMethod.invoke(object);
        }

        System.out.println("Объект создан через \"new\", Object created in \"new\"");


        if (beforeMethod != null) {
            beforeMethod.invoke(testClassInst);
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        for (Method m : testMethods) {
            m.invoke(testClassInst);
        }

        if (afterMethod != null) {
            afterMethod.invoke(testClassInst);
        }
    }
}


