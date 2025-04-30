import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateCompanyTest {
    public WebDriver driver;
    public WebDriverWait wait;
    
    @BeforeTest
    public void precoditions(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        LoginPage loginPage = new LoginPage(driver,wait);
        loginPage.setup();
        loginPage.url("https://frontend-humanitti-core-front-development-dxtxa.humanitti-sdlc.itti-platform.digital/sign-in");
        loginPage.ingresarUser("bismarck.berrios@itti.digital","Admin123");
        loginPage.clickLogin();
    }

    @Test
    public void crearEmpresaUnitaria() {
        CreateCompanyPage createCompanyPage = new CreateCompanyPage(driver, wait);
        createCompanyPage.clickCrearEmpresa();
        createCompanyPage.llenarCamposUsuario("Antonio","Trinidad","333333","123456789","antonio@itti.digital");
        createCompanyPage.llenarCamposEmpresa("Empresa1","empresaPrueba1@itti.digital","80090123","Buenos Aires","3010");
        createCompanyPage.clickCrear();
    }
    @Test
    public void crearEmpresaMasiva() throws InterruptedException {
        CreateCompanyPage createCompanyPage = new CreateCompanyPage(driver, wait);

        for (int i = 11; i <= 26; i++) {
            String nombre = "Jesus" + i;
            String apellido = "Martinez" + i;
            String cedula = "4562132" + i;
            String telefono = "098234132" + i;
            String correo = "jdmshop" + i + "@hotmail.com";

            String nombreEmpresa = "jdmShop" + i;
            String correoEmpresa = "jdmShop" + i + "@hotmail.com";
            String ruc = "780090" + i;
            String direccion = "Buenos Aires " + i;
            String numPatronal = "301" + i;

            createCompanyPage.clickCrearEmpresa();
            createCompanyPage.llenarCamposUsuario(nombre, apellido, cedula, telefono, correo);
            createCompanyPage.llenarCamposEmpresa(nombreEmpresa, correoEmpresa, ruc, direccion, numPatronal);
            createCompanyPage.clickCrear();


            Thread.sleep(2000);
        }
    }

    @AfterTest
    public void close(){
        LoginPage loginPage = new LoginPage(driver,wait);
        loginPage.close();
    }
}
