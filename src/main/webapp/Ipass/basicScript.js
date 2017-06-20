
//haalt op welke bewoner momenteel is ingelogd en toont het rechtsboven in het scherm.
function getUser(userID){
	$.get("restservices/huis/bewoners", function(data){
		$.each(data, function(k, v){
			if(v.persoonsnummer == userID){
				$("#userinfoLAB").html("<b>"+v.naam+"</b>");
			}
		});
	});
	
}
getUser(window.sessionStorage.getItem("userID"));