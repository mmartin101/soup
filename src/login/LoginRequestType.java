package login;

public class LoginRequestType
{
	public static final LoginRequestType LOGIN 	  = new LoginRequestType("LOGIN", 0);
	public static final LoginRequestType REGISTER = new LoginRequestType("REGISTER", 1);
	
	private String name;
	private Integer id;
	
	public LoginRequestType(String name, Integer id)
	{
		this.name = name;
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public Integer getId()
	{
		return id;
	}
}
