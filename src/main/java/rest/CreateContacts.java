package rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateContacts {

	@JsonProperty("ProcessID_mid")
    public String ProcessID_mid;

	@JsonProperty("FirstName_mid")
	public String FirstName_mid;

	@JsonProperty("Action_mid")
    public String Action_mid;

	@JsonProperty("ID_mid")
    public int ID_mid;

	@JsonProperty("LastName_mid")
    public String LastName_mid;

   
}