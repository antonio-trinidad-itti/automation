import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    private By emailBox = By.id(":R9najttqpcq:");
    private By passBox = By.id("outlined-adornment-password");
    private By loginButton = By.xpath("//*[@data-testid='login-button']");
    private By homeTitle = By.xpath("//*[@data-testid='homepage-title']");

    public LoginPage(WebDriver driver, WebDriverWait wait){
        super(driver,null);
    }

    public void ingresarUser(String email, String pass){
        this.sendText(email,emailBox);
        this.sendText(pass,passBox);
    }

    public void clickLogin(){
        this.click(loginButton);
        System.out.println("Login Exitoso "+this.getText(homeTitle));
    }
}
