package app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/calculate")
    public String checkOddAndEven(@RequestParam("number") Integer number) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/validate/even-or-odd?number=" + number,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }
}
