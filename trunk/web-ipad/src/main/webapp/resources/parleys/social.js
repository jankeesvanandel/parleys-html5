/**
 * Created by IntelliJ IDEA.
 * User: dobler
 * Date: 14.09.2010
 * Time: 13:00:14
 * To change this template use File | Settings | File Templates.
 */




        var DIGG = "digg";
		var FACEBOOK = "faceBook";
		var GOOGLE_BOOKMARK = "googleBookmark";
		var MYSPACE = "myspace";
		var STUMBLE_UPON = "stumbleUpon";
		var TECHNORATI = "technorati";
		var TWITTER = "twitter";
		var YAHOO_BOOKMARK = "yahooBookmark";


        function post(to, link, title) {
            var targetURL = "";
			switch (to) {
				case DIGG :
					targetURL = "http://digg.com/submit?phase=2&url=" + link + "&title=" + title;
					break;
				case FACEBOOK :
					targetURL = "http://www.facebook.com/sharer.php?u=" + link+"&t="+title;
					break;
				case GOOGLE_BOOKMARK :
					targetURL = "http://www.google.com/bookmarks/mark?op=add&bkmk=" + link + "&title=" + title;
					break;
				case MYSPACE :
					targetURL = "http://www.myspace.com/Modules/PostTo/Pages/?u=" + link + "&t=" + title;
					break;
				case STUMBLE_UPON :
					targetURL = "http://www.stumbleupon.com/submit?url=" + link;
					break;
				case TECHNORATI :
					targetURL = "http://technorati.com/faves/?add=" + link;
					break;
				case TWITTER :
					targetURL = "http://twitter.com/home?status=" + link +" "+title;
					break;
				case YAHOO_BOOKMARK :
					targetURL = "http://myweb2.search.yahoo.com/myresults/bookmarklet?u=" + link + "&t=" + title;
					break;
				default :
					break;
			}

            var load = window.open(targetURL,'','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no')
		}


var presentationId;
var presentationTitle;




$(document).bind('ready', function() {


    $("#tabs").tabs({ fx: {opacity: 'toggle' } });


    $('#tabs').bind('tabsselect', function(event, ui) {

        if(ui.index == 2){
          $("#tabs").css("left", "0px");
          $("#tabs").css("width", "750px");
        } else{
            $("#tabs").css("left", "0px");
            $("#tabs").css("width", "550px");
        }

    });



    presentationId =   $("#mainContent").attr("presentationId");
    presentationTitle =  $("#mainContent").attr("presentationTitle");

    $("#shareButton").click(function() {
        toggleShare();
    });


     $("#tabs-1 a").bind("click", function() {
        var type = $(this).attr("type");
        var link = "http://www.parleys.com/d/"+presentationId;

        post(type,link,presentationTitle);
        return false;
    });


} );


var isShareOpen = false;
function toggleShare(){
  if(isShareOpen){
      $("#tabs").css("left", "-710px");
      isShareOpen = false;
  }else{
      $("#tabs").css("left", "0px");
      $("#tabs").css("width", "550px"); 
      isShareOpen = true;
  }
}

