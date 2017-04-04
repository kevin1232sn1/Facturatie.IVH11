package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.Builders.UserBuilder;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by kevin on 3-4-2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean(name = "UserAdministrationService")
    UserAdministrationService userAdministrationService;
    @MockBean
    UserService userService;
    @Autowired
    private WebDriver webDriver;

    @Test
    public void getEditPage() throws Exception {
        given(this.userService.findOne(1))
                .willReturn(UserBuilder.builder().build());
        given(this.userAdministrationService.getCurrentUser())
                .willReturn(UserBuilder.builder().build());

        this.webDriver.get("/user/edit/1");
        WebElement element = this.webDriver.findElement(By.id("email"));
        assertThat(element.getAttribute("value")).isEqualTo("test@test.com");
    }
}
