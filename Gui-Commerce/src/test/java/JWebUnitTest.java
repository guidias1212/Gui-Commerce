import static net.sourceforge.jwebunit.junit.JWebUnit.*;
 
import org.junit.Before;
import org.junit.Test;
 
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
 
 
public class JWebUnitTest {
    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT); 
        setBaseUrl("http://127.0.0.1:8080/Gui-Commerce/");
    }

    
    @Test
    public void fullTest() {
        beginAt("index.jsp"); 
        assertTitleEquals("Gui-Commerce: Home");
        clickButton("register");
        assertTitleEquals("Gui-Commerce: Register");
        setTextField("name","TEST");
        setTextField("username","TEST");
        setTextField("password_1","TEST123");
        setTextField("password_2","TEST123");
        clickButton("create");
        assertTitleEquals("Gui-Commerce: Success");
        clickButton("home");
        assertTitleEquals("Gui-Commerce: Home");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Login");
        setTextField("username","TEST");
        setTextField("password","TEST123");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Settings");
        setTextField("product","TEST product 1");
        setTextField("price","123,45");
        setTextField("url","https://www.google.com/");
        clickButton("add");
        assertTitleEquals("Gui-Commerce: Success");
        clickButton("home");
        assertTitleEquals("Gui-Commerce: Home");
        assertTextInTable("table","TEST product 1");
        clickButton("login");
        clickButton("edit");
        assertTitleEquals("Gui-Commerce: Edit");
        setTextField("product","TEST product 11");
        setTextField("price","112233,44");
        setTextField("url","https://www.google.com/");
        clickButton("update");
        assertTitleEquals("Gui-Commerce: Settings");
        assertTextInTable("table","TEST product 11");
        clickButton("home");
        assertTitleEquals("Gui-Commerce: Home");
        assertTextInTable("table","TEST product 11");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Settings");
        clickButton("edit");
        assertTitleEquals("Gui-Commerce: Edit");
        clickButton("cancel");
        assertTitleEquals("Gui-Commerce: Settings");
        setTextField("product","TEST product 2");
        setTextField("price","678.90");
        setTextField("url","https://www.google.com/");
        clickButton("add");
        assertTitleEquals("Gui-Commerce: Success");
        clickButton("home");
        assertTitleEquals("Gui-Commerce: Home");
        assertTextInTable("table","TEST product 11");
        assertTextInTable("table","TEST product 2");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Settings");
        assertTextInTable("table","TEST product 11");
        assertTextInTable("table","TEST product 2");
        clickButton("edit");
        assertTitleEquals("Gui-Commerce: Edit");
        clickButton("delete");
        assertTitleEquals("Gui-Commerce: Settings");
        assertNoMatchInTable("table","TEST product 11");
        assertTextInTable("table","TEST product 2");
        clickButton("home");
        assertTitleEquals("Gui-Commerce: Home");
        assertNoMatchInTable("table","TEST product 11");
        assertTextInTable("table","TEST product 2");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Settings");
        clickButton("logout");
        assertTitleEquals("Gui-Commerce: Home");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Login");
        setTextField("username","TEST");
        setTextField("password","TEST123");
        clickButton("login");
        assertTitleEquals("Gui-Commerce: Settings");
        clickButton("delete");
        assertTitleEquals("Gui-Commerce: Home");
        assertNoMatchInTable("table","TEST product 2");
    }
    
    
}
