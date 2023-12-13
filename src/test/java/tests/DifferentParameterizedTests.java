package tests;

import data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DifferentParameterizedTests extends TestBase {


    @ParameterizedTest
    @EnumSource(Language.class)
    void wikipediaShouCorrectTextCheckWithEnumTest(Language language) {
        open("https://www.wikipedia.org/");
        $(byAttribute("lang", language.name())).click();
        $("#footer-places-privacy").shouldHave(text(language.description));
    }


    @CsvFileSource(resources = "/test_data/searchTestNotEmptyResult.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должeн быть текст {1}")
    void searchNotEmptyResultFromFileTest(String searchQuery, String expectedText) {
        open("https://www.flip.kz/");
        $("#search_input").setValue(searchQuery).pressEnter();
        $$(withTagAndText("a", expectedText));
    }


    @CsvSource(value = {
            "ноутбук | ноутбук",
            "смартфон | смартфон"
    }, delimiter = '|')
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должeн быть текст {1}")
    void searchNotEmptyResultDataFromCsvSourceTest(String searchQuery, String expectedText) {
        open("https://www.flip.kz/");
        $("#search_input").setValue(searchQuery).pressEnter();
        $$(withTagAndText("a", expectedText));
    }


    @ValueSource(strings = {
            "ноутбук", "смартфон"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    void searchNotEmptyResultTest(String searchQuery) {
        open("https://www.flip.kz/");
        $("#search_input").setValue(searchQuery).pressEnter();
        $$(".p-10").shouldBe(sizeGreaterThan(0));
    }
}