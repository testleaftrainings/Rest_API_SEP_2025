package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class PostCallMocking {
	
	static String request_payload = """
			                        {
			                          "firstName": "Ram",
			                          "lastName": "kumar",
			                          "username": "ram.kumar",
			                          "password": "Pa$$word@123"
			                        }
			                        """;
	
	static String response_payload = """
			                         {
			                           "message": "User created successfully!"
			                         }
			                         """;

	public static void main(String[] args) {
		// Request Mocking
		MappingBuilder requestMocking = WireMock.post("/user/registration")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(request_payload));
		
		// Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withStatus(201)
		        .withStatusMessage("Created")
		        .withHeader("Content-Type", "application/json")
		        .withBody(response_payload);
		
		WireMock.stubFor(requestMocking.willReturn(responseMocking));

	}

}