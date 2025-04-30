import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCompanyPage extends BasePage {

    private By createCompanyButton = By.xpath("//*[@data-testid='create-company-button']");

    //User locators

    private By firstName = By.id(":R356lqfnkumlbjd5cq:");
    private By lastName = By.id(":R556lqfnkumlbjd5cq:");
    private By idNumber = By.id(":R376lqfnkumlbjd5cq:");
    private By phoneNumber = By.id(":R576lqfnkumlbjd5cq:");
    private By email = By.id(":R96lqfnkumlbjd5cq:");

    //Company locators

    private By companyName = By.id(":R5alqfnkumlbjd5cq:");
    private By companyEmail = By.id(":R67alqfnkumlbjd5cq:");
    private By companyId = By.id(":Ra7alqfnkumlbjd5cq:");
    private By companyType = By.id("mui-component-select-companyType");
    private By unipersonalCompany = By.xpath("//li[normalize-space()='Unipersonal']");
    private By employeeRange = By.id("mui-component-select-employeeRange");
    private By range = By.xpath("//li[normalize-space()='0-50']");
    private By companyCountry = By.id(":R16balqfnkumlbjd5cq:");
    private By argentina = By.xpath("//li[@id=':R16balqfnkumlbjd5cq:-option-9']");
    private By companyCity = By.id("mui-component-select-city");
    private By buenosAires= By.xpath("//li[normalize-space()='Buenos Aires']");
    private By companyAddress = By.id(":Rdalqfnkumlbjd5cq:");
    private By patronalCount = By.id("mui-component-select-patronalCount");
    private By patronal1 = By.xpath("//li[normalize-space()='1']");
    private By patronalNumber = By.xpath("/html[1]/body[1]/div[1]/div[3]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[7]/div[1]/input[1]");

    private By createButton = By.xpath("//button[normalize-space()='CREAR EMPRESA']");

    public CreateCompanyPage(WebDriver driver, WebDriverWait wait){
        super(driver,null);
    }

    public void clickCrearEmpresa(){
        this.click(createCompanyButton);
    }

    public void llenarCamposUsuario(String nombre, String apellido, String cedula, String telefono, String correo){
        this.sendText(nombre,firstName);
        this.sendText(apellido,lastName);
        this.sendText(cedula,idNumber);
        this.sendText(telefono,phoneNumber);
        this.sendText(correo,email);
    }

    public void llenarCamposEmpresa(String nombreEmpresa, String correoEmpresa,
                                    String ruc, String direccion, String numPatronal) {
        this.sendText(nombreEmpresa,companyName);
        this.sendText(correoEmpresa,companyEmail);
        this.sendText(ruc,companyId);
        this.click(companyType);
        this.click(unipersonalCompany);
        this.click(employeeRange);
        this.click(range);
        this.click(companyCountry);
        this.click(argentina);
        this.click(companyCity);
        this.click(buenosAires);
        this.sendText(direccion,companyAddress);
        this.click(patronalCount);
        this.click(patronal1);
        this.sendText(numPatronal,patronalNumber);

    }

    public void clickCrear(){
        this.click(createButton);
    }

}
