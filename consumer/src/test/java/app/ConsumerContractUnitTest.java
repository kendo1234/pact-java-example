package app;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;

import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsumerContractUnitTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("provider", "localhost", 8082, this);

    @Pact(consumer = "consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder.given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/validate/even-or-odd")
                .query("number=2")
                .method("GET")
                .willRespondWith().status(200)
                .toPact();
    }

    @Test
    @PactVerification()
    public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {
        // when
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/validate/even-or-odd?number=2", String.class);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

}
