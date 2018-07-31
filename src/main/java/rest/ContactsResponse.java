package rest;

public class ContactsResponse {

    private Contacts[] Contacts;

    private String ProcessID;

    public Contacts[] getContacts ()
    {
        return Contacts;
    }

    public void setContacts (Contacts[] Contacts)
    {
        this.Contacts = Contacts;
    }

    public String getProcessID ()
    {
        return ProcessID;
    }

    public void setProcessID (String ProcessID)
    {
        this.ProcessID = ProcessID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Contacts = "+Contacts+", ProcessID = "+ProcessID+"]";
    }
}
	