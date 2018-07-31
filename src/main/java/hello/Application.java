package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import rest.LogRequestInterceptor;
import rest.RestTemplateResponseErrorHandler;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	
	
	@Bean
	public xagent.wsdl.ObjectFactory ObjectFactory() {
        return new xagent.wsdl.ObjectFactory();
    }
	
/*	@Bean
	CommandLineRunner lookup(CalculatorClient calculatorClient) {
		return args -> {
			int a = 2;
			int b = 5;


			AddResponse response = calculatorClient.getAdd(a, b);
			System.err.println(response.getAddResult());
		};
	}*/
	
	@Bean(name = "simpleRestTemplate")
	@Primary
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) { 

		RestTemplate template = restTemplateBuilder
				//.requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
	//			.interceptors(new LogRequestInterceptor()) 
	//			.errorHandler(new RestTemplateResponseErrorHandler())
					/*{				    protected boolean hasError(HttpStatus statusCode) {
				    	log.info("Http Response-> "+statusCode.name()+ " Status Code-> "+statusCode.toString());
				        return false;
				    }})*/
				.build();
		return template;

	}
	
}
