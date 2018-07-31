package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import rest.Contacts;
import rest.ContactsResponse;
import rest.CreateContacts;
import rest.CreateContactsResponse;
import rest.Peoples;
import xagent.wsdl.XAgentContactDemoProcessMessageEvent1Request;
import xagent.wsdl.XAgentContactDemoProcessResponse;
import xagent.wsdl.XAgentPeopleType;
import xagent.wsdl.XAgentPeoplesType;

@Controller
public class HelloController {
	
	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private CalculatorClient xagent;
	
	XAgentContactDemoProcessResponse response;
	
	@Autowired
	@Qualifier("simpleRestTemplate")
	private RestTemplate simpleRestTemplate;

	@GetMapping("/home")
	@ResponseBody
	public String hello() {

		response = xagent.getList();
		List <XAgentPeoplesType> peoples=response.getContacts();
		//XAgentPeoplesType people= new ArrayList<XAgentPeoplesType>(); 
				
	String page="<html><body><table style=width:100%;text-align:left;border-collapse:collapse;background-color:gold;>"
			+ "<tr style=background-color:yellowgreen;color:white;><th>ID</th><th>First Name</th><th>Last Name </th></tr>" ;
			for(int i=0;i<peoples.size();i++)
			{
				//people=peoples.get(i);
				page+="<tr><td>"+peoples.get(i).getPeoples().get(0).getID()+"</td><td>"+peoples.get(i).getPeoples().get(0).getFirstName()+"</td><td>"+peoples.get(i).getPeoples().get(0).getLastName()+"</td></tr>";
			}
	page+="</table><a href=/add>Add New Record</a></body></html>";
	xagent.process_id=response.getProcessID();
	
	
	System.out.println(xagent.process_id);
	
	return page;
	}

	@PostMapping("/insert")
	@ResponseBody
	public String insert(@RequestParam("firstname") String fname, @RequestParam("lastname") String lname) {
		
		
		//return xagent.getAdd(fname, lname).getStatus();
		rest.Error error = null;
		ResponseEntity<CreateContactsResponse> resp = null;
		CreateContacts request = new CreateContacts();
		request.FirstName_mid=fname;
		request.LastName_mid=lname;
		request.Action_mid="Insert";
		//request.setID_mid(0);
		request.ProcessID_mid=xagent.process_id;
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<CreateContacts> requestEntity = new HttpEntity<CreateContacts>(request, headers);
		
		try {
			//resp = simpleRestTemplate.postForObject("https://ps1w2.rt.informaticacloud.com/active-bpel/public/rt/0018IZ/XAgentContactDemoProcess/event/MessageEvent1", request, CreateContactsResponse.class);
			resp = simpleRestTemplate.exchange("https://ps1w2.rt.informaticacloud.com/active-bpel/public/rt/0018IZ/XAgentContactDemoProcess/event/MessageEvent1", HttpMethod.POST, requestEntity, CreateContactsResponse.class);
		} catch (RestClientException e) {
			
			System.out.println("SERVER ERROR RESPONSE EXCEPTION "+e.getMessage());
			log.error(e.getMessage());
			error = new rest.Error(resp.getHeaders().toString(), e.getMessage());
			
	

		}
		
		if (resp.getStatusCode().equals(HttpStatus.OK))
		return "Contact Created -"+ resp.getBody().toString();
		else return "Creation Failed - "+error.getCode()+" Check Server logs for Service Exception- "+ error.getMessage();
		
	}
	
	@GetMapping("/add")
	@ResponseBody
	public String add() {

		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<body>\r\n" + "\r\n" + "<h2>Create User</h2>\r\n" + "\r\n"
				+ "<form action=\"/insert\" method=\"post\">\r\n" + "  First name:<br>\r\n"
				+ "  <input type=\"text\" name=\"firstname\" >\r\n" + "  <br>\r\n" + "  Last name:<br>\r\n"
				+ "  <input type=\"text\" name=\"lastname\" >\r\n" + "  <br><br>\r\n"
				+ "  <input type=\"submit\" value=\"Submit\">\r\n" + "</form> \r\n" + "\r\n"
				+ "<p>If you click the \"Submit\" button, the Contact will be created </p>\r\n" + "\r\n"
				+ "</body>\r\n" + "</html>\r\n";
	}
	
	/*
	 * @PostMapping("/add")
	 * 
	 * @ResponseBody public String add(@RequestParam("firstname") String
	 * fname,@RequestParam("lastname") String lname ) {
	 * 
	 * 
	 * 
	 * return "result " +calc.getAdd(Integer.parseInt(fname),
	 * Integer.parseInt(lname)).getAddResult(); }
	 */
	/*@PostMapping("/insert")
	@ResponseBody
	public String[] insert(@RequestParam("firstname") String fname, @RequestParam("lastname") String lname,
			@RequestParam("processID") String processID, @RequestParam("actionID") String actionID) {
		String values[]=new String[4];
		values[0]=fname;
		values[1]=lname;
		values[2]=processID;
		values[3]=actionID;
		return values;
	}*/
	
	@GetMapping("/rest")
	public String rest() {

		return "rest.html";
	}

	@GetMapping("/contacts")
	@ResponseBody
	public String contacts() {
		
		
		ContactsResponse resp = null;
		
		try {
			resp = simpleRestTemplate.getForObject("https://ps1w2.rt.informaticacloud.com/active-bpel/public/rt/0018IZ/XAgentContactDemoProcess", ContactsResponse.class);

		} catch (RestClientResponseException e) {
			log.error(e.getResponseBodyAsString());

		}
		
		
		Contacts[] contacts=resp.getContacts();
		String page="<html><body><table><tr><th>ID</th><th>First Name</th><th>Last Name </th></tr>" ;
		for(int i=0;i<contacts.length;i++)
		{
			
			page+="<tr><td>"+contacts[i].getPeoples()[0].getID()+"</td><td>"+contacts[i].getPeoples()[0].getFirstName()+"</td><td>"+contacts[i].getPeoples()[0].getLastName()+"</td></tr>";
		}
        page+="</table><a href=/add>Add New Record</a>"
        		+ "</p>"+resp.getProcessID()
        		+ "</body></html>";


		return page;
	}

}