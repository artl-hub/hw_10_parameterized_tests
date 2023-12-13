package tests;

import data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DifferentPframeterixedTests extends TestBase {

//    @BeforeAll
//    static void setup() {
//        Configuration.browserSize = "1920x1080";
//        Configuration.pageLoadStrategy = "eager";
//        Configuration.timeout = 5000;
//        Configuration.holdBrowserOpen = true;
//    }



    static Stream<Arguments> wikipediaShoudCorrectFuterButtonCheckWithEnamAndList() {
        return Stream.of(
                Arguments.of(Language.EN, List.of(
                        "Privacy policy", "About Wikipedia", "Disclaimers", "Contact Wikipedia", "Code of Conduct",
                        "Developers", "Statistics", "Cookie statement", "Mobile view")),
                Arguments.of(Language.ES, List.of(
                        "Política de privacidad", "Acerca de Wikipedia", "Limitación de responsabilidad",
                        "Código de conducta", "Desarrolladores", "Estadísticas", "Declaración de cookies", "Versión para móviles"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void wikipediaShoudCorrectFuterButtonCheckWithEnamAndList(Language language, List<String> expectedButtons) {
        open("https://www.wikipedia.org/");
        $(byAttribute("lang", language.name())).click();
        $$("#footer-places a").filter(visible).shouldHave((texts(expectedButtons)));
    }



//    @Disabled("Временно отключен")
    @ParameterizedTest
    @EnumSource(Language.class)
    void wikipediaShouCorrectTextCheckWithEnam(Language language) {
        open("https://www.wikipedia.org/");
        $(byAttribute("lang", language.name())).click();
        $("#footer-places-privacy").shouldHave(text(language.description));
    }



//    @Disabled("Временно отключен")
    @CsvFileSource(resources = "/test_data/searchTestNotEmptyResult.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должeн быть текст {1}")
//    @Tag("SMOKE")
    void searchTestNotEmptyResultFromFile(String searchQuery, String expectedText) {
        open("https://www.flip.kz/");
        $("#search_input").setValue(searchQuery).pressEnter();
        $$(withTagAndText("a", expectedText));
    }



//    @Disabled("Временно отключен")
    @CsvSource(value = {
            "ноутбук | ноутбук",
            "смартфон | смартфон"
    }, delimiter = '|')
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должeн быть текст {1}")
//    @Tag("SMOKE")
    void searchTestNotEmptyResultDataFromCsvSource(String searchQuery, String expectedText) {
        open("https://www.flip.kz/");
        $("#search_input").setValue(searchQuery).pressEnter();
        $$(withTagAndText("a", expectedText));
    }



//    @Disabled("Временно отключен")
    @ValueSource(strings = {
            "ноутбук", "смартфон"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
//    @Tag("SMOKE")
    void searchTestNotEmptyResult(String searchQuery) {
        open("https://www.flip.kz/");
        $("#search_input").setValue(searchQuery).pressEnter();
        $$(".p-10").shouldBe(sizeGreaterThan(0));
    }
}