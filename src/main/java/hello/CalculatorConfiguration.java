package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class CalculatorConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("xagent.wsdl");
		return marshaller;
	}

	@Bean
	public CalculatorClient calculatorClient(Jaxb2Marshaller marshaller,WebServiceMessageSender webServiceMessageSender) {
		CalculatorClient client = new CalculatorClient();
		client.setDefaultUri("https://ps1w2.rt.informaticacloud.com/active-bpel/public/soap/0018IZ/XAgentContactDemoProcess");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.setMessageSender(webServiceMessageSender);
		return client;
	}
	
	@Bean
	  public WebServiceMessageSender webServiceMessageSender() {
	    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
	    // timeout for creating a connection
	    httpComponentsMessageSender.setConnectionTimeout(120000);
	    // when you have a connection, timeout the read blocks for
	    httpComponentsMessageSender.setReadTimeout(120000);

	    return httpComponentsMessageSender;
	  }

}