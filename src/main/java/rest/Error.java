package rest;

public class Error {
	
	public Error(String code, String message) {
		super();
		this.message = message;
		this.code = code;
	}

	private String message;

    private String code;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", code = "+code+"]";
    }
}
