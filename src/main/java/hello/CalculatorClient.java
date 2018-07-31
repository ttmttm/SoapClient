package hello;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import xagent.wsdl.ObjectFactory;
import xagent.wsdl.XAgentContactDemoProcessMessageEvent1Request;
import xagent.wsdl.XAgentContactDemoProcessMessageEvent1Response;
import xagent.wsdl.XAgentContactDemoProcessRequest;
import xagent.wsdl.XAgentContactDemoProcessResponse;

public class CalculatorClient extends WebServiceGatewaySupport{
	
	@Autowired
	private ObjectFactory soap;



	public String process_id;
	

	
	private static final Logger log = LoggerFactory.getLogger(CalculatorClient.class);

	public XAgentContactDemoProcessMessageEvent1Response getAdd(String firstName,String lastName) {

		XAgentContactDemoProcessMessageEvent1Request request = soap.createXAgentContactDemoProcessMessageEvent1Request();
		request.setFirstNameMid(firstName);
		request.setLastNameMid(lastName);
		//request.setIDMid(123);
		request.setActionMid("Insert");
		request.setProcessIDMid(this.process_id);
		
				XAgentContactDemoProcessMessageEvent1Response response = (XAgentContactDemoProcessMessageEvent1Response) getWebServiceTemplate()
				.marshalSendAndReceive("https://ps1w2.rt.informaticacloud.com/active-bpel/public/soap/0018IZ/XAgentContactDemoProcess",
						request);

		return response;
	}
	public XAgentContactDemoProcessResponse getList() {

		XAgentContactDemoProcessRequest request = soap.createXAgentContactDemoProcessRequest();
		
		XAgentContactDemoProcessResponse response = (XAgentContactDemoProcessResponse) getWebServiceTemplate()
				
				.marshalSendAndReceive("https://ps1w2.rt.informaticacloud.com/active-bpel/public/soap/0018IZ/XAgentContactDemoProcess",
						request);

		return response;
	}
}
