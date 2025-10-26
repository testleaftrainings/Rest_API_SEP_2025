package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class GetCallMockWithJsonResponse {
	
	static String response_json = """
			               { 
			                 "message": "Hi! Welcome to the Wiremock mocking"
			               }			
			               """;

	public static void main(String[] args) {
		//Request Mocking
		MappingBuilder requestMocking = WireMock.get("/json/welcome");
				
		//Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
						                            .withStatus(200)
				                                    .withStatusMessage("OK")
				                                    .withHeader("Content-Type", "application/json")
				                                    .withBody(response_json);
		// Stub Mapping (Mock Server)
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
	}

}
