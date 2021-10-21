package HW_3_7;

public class TestClass {
    public TestClass() {
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test 3");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("Test 4");
    }

    @Test(priority = 5)
    public void test5() {
        System.out.println("Test 5");
    }

    @Test(priority = 6)
    public void test6() {
        System.out.println("Test 6");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test(priority = 2)
    public void test2A() {
        System.out.println("Test 2A");
    }

    @Test(priority = 2)
    public void test2B() {
        System.out.println("Test 2B");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }
}
