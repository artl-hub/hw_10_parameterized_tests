package tests;

import org.junit.jupiter.api.*;

public class TagTests {

    @Test
    @Tag("SMOKE")
    @DisplayName("SMOKE  TEST")
    void testWithTagSmoke() {
        System.out.println("==============SMOKE TEST=================");
    }

    @Test
    @Tag("WEB")
    @DisplayName("WEB  TEST")
    void testWithTagWeb() {
        System.out.println("==============WEB  TEST=================");
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE"),
    })
    @DisplayName("WEB  TEST")
    void testWithTagsWebAndSmoke() {
        System.out.println("==============WEB and  SMOKE=================");
    }

}
