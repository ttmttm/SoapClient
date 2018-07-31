package rest;

public class Contacts {


    private Peoples[] Peoples;

    public Peoples[] getPeoples ()
    {
        return Peoples;
    }

    public void setPeoples (Peoples[] Peoples)
    {
        this.Peoples = Peoples;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Peoples = "+Peoples+"]";
    }
}
	