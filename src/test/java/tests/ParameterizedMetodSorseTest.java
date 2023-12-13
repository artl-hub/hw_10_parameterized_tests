package tests;

import data.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Test popup")
public class ParameterizedMetodSorseTest extends TestBase {


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


}