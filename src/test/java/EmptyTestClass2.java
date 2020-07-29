import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmptyTestClass2 {

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test Class2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method Class2");
    }

    @Test
    public void emptyTest1() {
        System.out.println("Empty Test Class2");
        assert 1 == 1;
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method Class2");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test Class2");
    }
}
