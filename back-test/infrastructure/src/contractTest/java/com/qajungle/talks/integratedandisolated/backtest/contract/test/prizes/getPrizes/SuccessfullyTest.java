package com.qajungle.talks.integratedandisolated.backtest.contract.test.prizes.getPrizes;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.qajungle.talks.integratedandisolated.backtest.contract.base.PrizesSuccessfullyBase;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class SuccessfullyTest extends PrizesSuccessfullyBase {

	@Test
	public void validate_shouldGetPrizesSuccess() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.queryParam("category","physics")
					.queryParam("from","2018")
					.queryParam("to","2019")
					.get("/nobel_prizes/v1/");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
			assertThat(response.header("Access-Control-Allow-Origin")).isEqualTo("*");
			assertThat(response.header("Access-Control-Allow-Headers")).isEqualTo("*");
			assertThat(response.header("Access-Control-Allow-Methods")).isEqualTo("*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).array("['prizes']").contains("['year']").matches("([1-9]\\d*)");
			assertThatJson(parsedJson).array("['prizes']").contains("['firstname']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).array("['prizes']").contains("['surname']").matches("^\\s*\\S[\\S\\s]*");
		// and:
			assertThat(parsedJson.read("$.from", String.class)).matches("[\\S\\s]+");
			assertThat(parsedJson.read("$.to", String.class)).matches("[\\S\\s]+");
			assertThat(parsedJson.read("$.category", String.class)).matches("[\\S\\s]+");
			assertThat(parsedJson.read("$.prizes[0].firstname", String.class)).matches("[\\S\\s]+");
			assertThat(parsedJson.read("$.prizes[0].surname", String.class)).matches("[\\S\\s]+");
			assertThat(parsedJson.read("$.prizes[0].year", String.class)).matches("[\\S\\s]+");
	}

}
