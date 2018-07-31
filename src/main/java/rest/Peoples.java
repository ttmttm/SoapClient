package rest;

public class Peoples {


    private String ID;

    private String FirstName;

    private String LastName;

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getFirstName ()
    {
        return FirstName;
    }

    public void setFirstName (String FirstName)
    {
        this.FirstName = FirstName;
    }

    public String getLastName ()
    {
        return LastName;
    }

    public void setLastName (String LastName)
    {
        this.LastName = LastName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ID = "+ID+", FirstName = "+FirstName+", LastName = "+LastName+"]";
    }
}
	