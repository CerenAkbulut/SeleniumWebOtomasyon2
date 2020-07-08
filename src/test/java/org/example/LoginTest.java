package org.example;
import org.junit.Test;
import sun.rmi.runtime.Log;

public class LoginTest extends ChrmTest {
    @Test
    public void login() throws InterruptedException {
        new LoginPage(driver).login("akbltcrn@outlook.com","112233crN.");
    }

}
