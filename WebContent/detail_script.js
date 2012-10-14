function getImageDetail(img)
{
        // this will retrieve images from [images] to [images + 10]
        $.getJSON("imgdetail", "img="+img, function(data) 
        {    
            //get the pic
            var pic = data.pic;
            $("<a>").attr({
                id: "img_link", 
                href: "http://jackzilla.com/soup/pics/"+pic.filename
                }).appendTo("#image");
        
            $("<img/>").attr({
                id: "img_main", 
                src: "http://jackzilla.com/soup/pics/"+pic.filename
                }).width("80%").height("80%").prependTo("#img_link");
            
            //loop through and dynamically add pics
                
                $.each(data.comments, function(i,item)
                {
                    $("<li>").text(item.comment + " by " + item.user).appendTo("#comment_list");
                });
        });
        
}

$(document).ready(function() 
{
	var username = getCookieVal("user");
	if(username != null)
	{
		$('#form_post_comment').show();
	}
	else
	{
		console.log("no user logged in");
		$('#form_post_comment').hide();
		
	}
	
	$('#form_post_comment').submit(function(event) 
	{
		event.preventDefault();
		var comment = $('#comment_field').val();
		var pic = $('#pic_url_name').val();
		
		if (comment.length == 0) 
		{
			$('#comment_req').show();
			return;
		} else {
			$('#register_err_footnote').hide();
			$('#register_username_req').hide();
			$('#register_passwd_req').hide();
		}

		var settings = {
			url : "comment",
			type : "POST",
			data : "C=" + comment+"&P="+pic,
			success : function(responseText, statusText, jqXHR) {
				//reload page to show new comment 
				window.location.reload(true);
			},
			error : function(jqXHR, statusText, CodeText) {
				$('#register_err').show();
			}
		};
		jQuery.ajax(settings);
	});
	return;
});
