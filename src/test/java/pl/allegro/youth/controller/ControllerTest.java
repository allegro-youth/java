package pl.allegro.youth.controller;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ControllerTest {

    @Test
    public void testShouldHello() throws Exception {
        Controller controller = new Controller();
        String hello = controller.hello();

        assertThat(hello).isEqualTo("hello");
    }
}