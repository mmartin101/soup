package login;

public class User
{
	private Integer id;
	private String user_name;
	private String password;
	private String email_address;
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getUser_name()
	{
		return user_name;
	}
	
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getEmail_address()
	{
		return email_address;
	}
	
	public void setEmail_address(String email_address)
	{
		this.email_address = email_address;
	}
}
