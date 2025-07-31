package automation;


import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.BaseTest;
import utilities.Logs;

public class PrimerosTests extends BaseTest{

    @Test(groups = regression)
    public void primerTest(){
        Logs.info("Mi primer test");
        sleep(3000);
    }
    
    @Test(groups = regression)
    public void fallidoTest(){
        Logs.info("Esperando 2 segundos antes de fallar");
        sleep(2000);
        
        Logs.info("Este test fallará");
        Assert.assertEquals(3, 2);
    }
}
