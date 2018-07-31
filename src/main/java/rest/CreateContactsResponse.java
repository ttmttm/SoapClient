package rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateContactsResponse {

	@JsonProperty("Status")
    private String Status;

	@Override
	public String toString() {
		return "CreateContactsResponse [Status=" + Status + "]";
	}


}