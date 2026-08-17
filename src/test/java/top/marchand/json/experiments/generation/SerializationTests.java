package top.marchand.json.experiments.generation;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import top.marchand.json.openapi.model.Info;
import top.marchand.json.openapi.model.Iso31663;

public class SerializationTests {
  private final ObjectMapper mapper = new ObjectMapper();

  @DisplayName("Given a ISO-3166-3 country, all infos should be marshalled")
  @Test
  public void test_10() {
    // Given
    Info info = new Iso31663("ISO-3166-3", Iso31663.CodeEnum.FRA);
    String expected = """
        {
          "code": "FRA",
          "norme": "ISO-3166-3"
        }
        """;
    // When
    String actual = mapper.writeValueAsString(info);
    // Then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actual).containsIgnoringWhitespaces("\"norme\":\"ISO-3166-3\"");
    softly.assertThat(actual).containsIgnoringWhitespaces("\"code\":\"FRA\"");
    softly.assertAll();
  }
}
