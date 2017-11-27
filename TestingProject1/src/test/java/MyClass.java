import org.junit.*;

public class MyClass {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Before Class");
    }

    @Before
    public void setUp(){
        System.out.println("Set Up");
    }

    @Test
    public void test1(){
        System.out.println("Hello My Friends!");
    }

    @After
    public void tearDown(){
        System.out.println("Tear Down");
    }

    @AfterClass
    public static void destroy(){
        System.out.println("Destroy");
    }

}
