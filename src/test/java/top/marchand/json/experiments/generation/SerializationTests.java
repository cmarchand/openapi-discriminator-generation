package top.marchand.json.experiments.generation;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import top.marchand.json.openapi.model.Info;
import top.marchand.json.openapi.model.Iso31662;
import top.marchand.json.openapi.model.Iso31663;

public class SerializationTests {
  private final ObjectMapper mapper = new ObjectMapper();

  @DisplayName("Given a ISO-3166-3 country, all infos should be marshalled")
  @Test
  public void test_10() {
    // Given
    Info info = new Iso31663("ISO-3166-3", Iso31663.CodeEnum.FRA);
    // When
    String actual = mapper.writeValueAsString(info);
    // Then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).containsIgnoringWhitespaces("\"norme\":\"ISO-3166-3\"");
    softly.assertThat(actual).containsIgnoringWhitespaces("\"code\":\"FRA\"");
    softly.assertAll();
  }

  @DisplayName("Given a ISO-3166-2 country, all infos should be marshalled")
  @Test
  public void test_20() {
    // Given
    Info info = new Iso31662("ISO-3166-2", Iso31662.CodeEnum.FR);
    // When
    String actual = mapper.writeValueAsString(info);
    // Then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).containsIgnoringWhitespaces("\"norme\":\"ISO-3166-2\"");
    softly.assertThat(actual).containsIgnoringWhitespaces("\"code\":\"FR\"");
    softly.assertAll();
  }

  @DisplayName("Unmarshall a Iso3166-3")
  @Test
  public void test_30() {
    // Given
    String json = """
        {
          "code": "FRA",
          "norme": "ISO-3166-3"
        }
        """;
    // When
    Info actual = mapper.readValue(json, Info.class);
    // Then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).isInstanceOf(Iso31663.class);
    softly.assertThat(actual.getNorme()).isEqualTo("ISO-3166-3");
    softly.assertThat(((Iso31663)actual).getCode()).isEqualTo(Iso31663.CodeEnum.FRA);
    softly.assertAll();
  }

  @DisplayName("Unmarshall a Iso3166-2")
  @Test
  public void test_40() {
    // Given
    String json = """
        {
          "code": "FR",
          "norme": "ISO-3166-2"
        }
        """;
    // When
    Info actual = mapper.readValue(json, Info.class);
    // Then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).isInstanceOf(Iso31662.class);
    softly.assertThat(actual.getNorme()).isEqualTo("ISO-3166-2");
    softly.assertThat(((Iso31662)actual).getCode()).isEqualTo(Iso31662.CodeEnum.FR);
    softly.assertAll();
  }
}
