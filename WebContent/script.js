function getCookieVal(cookieName) 
{
	var allCookies = document.cookie;
	if (allCookies == null) 
	{
		console.log("is null");
	}
	var pos = allCookies.indexOf(cookieName + '=');
	if (pos == -1)
	{
		return null;
	}
	var valueStart = pos + (cookieName.length + 1);
	var valueEnd = allCookies.indexOf(";", valueStart);
	if (valueEnd == -1)
	{
		valueEnd = allCookies.length;
	}
	var value = allCookies.substring(valueStart, valueEnd);
	value = unescape(value);
	return (value == "") || (value == "\"\"") ? null : value;
}
 
function logout()
{
	var settings = 
		{
			url : "login",
			type : "POST",
			data : "REQ_TYPE=LOGOUT",
			success : function(responseText, statusText, jqXHR) 
			{
				//server will send redirect to foo.html
			},
			error : function(jqXHR, statusText, CodeText) 
			{
				//not sure how you can have an error logging out (O_o)
			}
		};
		jQuery.ajax(settings);
}	
	
	
	
$(document).ready(function() {
	// im thinking we can use this so that on the client side we 
	// can know whether or not the user is logged in
	var username = getCookieVal("user");
	if(username != null)
	{
		$('#nav_bar_options_user').show();
		$('#form_login').hide();
		$('#form_upload').hide();
	}
	else
	{
		console.log("no user logged in");
		$('#nav_bar_options_user').hide();
		$('#nav_bar_options_no_user').show();
		
	}
	
	// show login/register
	$('#link_login').click(function() {
		$('#form_login').show();
		$('#form_register').hide();
		$('#form_upload').hide();
	});

	$('#link_register').click(function() {
		$('#form_login').hide();
		$('#form_upload').hide();
		$('#form_register').show();
	});
	
	$('#link_login_register').click(function() {
		$('#form_login').show();
		$('#form_upload').hide();
		$('#form_register').hide();
	});
	
	$('#link_upload').click(function() {
		$('#form_upload').show();
		$('#form_login').hide();
		$('#form_register').hide();
	});
	
	$('#link_upload2').click(function() {
		$('#form_upload').show();
		$('#form_login').hide();
		$('#form_register').hide();
	});
	
	$('#link_logout').click(function(){
		logout();
	});

	// submit button action
	$('#form_login').submit(function(event) 
	{
		event.preventDefault();
		var user = $('#user_login').val();
		var passwd = $('#passwd_login').val();
		console.log(user);
		console.log(passwd);
		if (user.length == 0 || passwd.length == 0) 
		{
			$('#login_err_footnote').show();
			if (user.length == 0) {
				$('#login_username_req').show();
			}
			if (passwd.length == 0) {
				$('#login_passwd_req').show();
			}

			return;
		} 
		else 
		{
			$('#login_err_footnote').hide();
			$('#login_username_req').hide();
			$('#login_passwd_req').hide();
		}

		var settings = 
		{
			url : "login",
			type : "POST",
			data : "REQ_TYPE=LOGIN&U=" + user + "&P=" + passwd,
			success : function(responseText, statusText, jqXHR) 
			{
				//reload page, should show "hello user_name" 
				window.location.reload(true);
			},
			error : function(jqXHR, statusText, CodeText) 
			{
				$('#login_err').show();
			}
		};
		jQuery.ajax(settings);
	});

	$('#form_register').submit(function(event) 
	{
		event.preventDefault();
		var user = $('#register_user_login').val();
		var passwd = $('#register_passwd_login').val();
		console.log(user);
		console.log(passwd);
		if (user.length == 0 || passwd.length == 0) {
			$('#register_err_footnote').show();
			if (user.length == 0) {
				$('#register_username_req').show();
			}
			if (passwd.length == 0) {
				$('#register_passwd_req').show();
			}

			return;
		} else {
			$('#register_err_footnote').hide();
			$('#register_username_req').hide();
			$('#register_passwd_req').hide();
		}

		var settings = {
			url : "login",
			type : "POST",
			data : "REQ_TYPE=REGISTER&U=" + user + "&P=" + passwd,
			success : function(responseText, statusText, jqXHR) {
				//should redirect to 
				window.location.pathname = "soup/foo.html";
			},
			error : function(jqXHR, statusText, CodeText) {
				$('#register_err').show();
			}
		};
		jQuery.ajax(settings);
	});
	
	$('#form_upload').submit(function(event) 
	{
		var filename = $('#file_chooser').val();
		console.log(filename);
		if(filename.length == 0)
		{
			$('#no_file_err').show();
			return;
		}
		else
		{
			$('#no_file_err').hide();
		}
		
		
		});
	return;
});