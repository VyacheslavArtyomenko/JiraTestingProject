import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.xml.bind.SchemaOutputResolver;

public class TestWithParameter {

    @Parameters({"waitForTimeOut"})
    @Test
    public void waitTimeOutInSeconds(String browserName) {
        System.out.println(browserName);
    }

}
