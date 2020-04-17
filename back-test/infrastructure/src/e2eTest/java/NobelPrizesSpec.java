import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.lookiero.tkiero.core.util.extension.TKieroExtension;
import com.lookiero.tkiero.core.util.extension.TKieroProperties;
import com.lookiero.tkiero.dbvalidation.DBValidator;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TKieroExtension.class)
public class NobelPrizesSpec {

  private static final String NOBEL_PRIZES_SCHEMA = "schemas/nobel_prizes_schema.json";

  private static String nobelPrizesApiUrl;
  private static DBValidator dbValidator;

  @BeforeAll
  static void setupTest(final TKieroProperties tKieroProperties) {
    nobelPrizesApiUrl = tKieroProperties.serverProperties.serverUrl + "/nobel_prizes/v1";
    dbValidator = DBValidator.given(tKieroProperties.dbProperties.postgreSqlUrl);
  }

  @Test void
  should_get_nobel_prizes_db_is_updated_only_the_first_time() {
    //Verify db is clean
    dbValidator
        .when("select count(id) as rows from nobel_prize")
        .then("rows", 0);

    //Call to api first time
    given()
        .when()
        .contentType(ContentType.JSON)
        .get(nobelPrizesApiUrl + "?category=medicine&from=2018&to=2020")
        .then()
        .body(matchesJsonSchemaInClasspath(NOBEL_PRIZES_SCHEMA))
        .statusCode(200);

    //Verify data is in db
    dbValidator
        .when("select count(id) as rows from nobel_prize")
        .then("rows", 2);

    //Call to api second time
    given()
        .when()
        .contentType(ContentType.JSON)
        .get(nobelPrizesApiUrl + "?category=medicine&from=2018&to=2020")
        .then()
        .statusCode(200);

    //Verify the data is not again added to db
    dbValidator
        .when("select count(id) as rows from nobel_prize")
        .then("rows", 2);
  }
}
