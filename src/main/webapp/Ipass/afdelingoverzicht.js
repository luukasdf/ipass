window.onload=initpage

function initpage(){
	alleJongens(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
	stouteJongens(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
}

//laat alle bewoners zien in een overzicht die een taak in het verleden niet hebben afgetekend
function stouteJongens(userID, afdelingID){
	$.get("restservices/huis/bewonersnietaf", function(data){
		$.each(data, function(k,v){
			var tabelRij;
			if (v.afdelingID == afdelingID){
				tabelRij = "<tr> <td>"+ v.naam +"</td> <td>"+v.woonadres+"</td> <td>"+v.duur+"</td> </tr>";
				$("#stouteJongens").append(tabelRij)
			}
		})
	})
}
//laat alle bewoners zien, en hun geplande duur.
function alleJongens(userID, afdelingID){
	$.get("restservices/huis/bewonersduur", function(data){
		$.each(data, function(k,v){
			var tabelRij;
			if (v.afdelingID == afdelingID){
				tabelRij = "<tr> <td>"+ v.naam +"</td> <td>"+v.woonadres+"</td> <td>"+v.duur+"</th> </tr>";
				$("#lieveJongens").append(tabelRij)
			}
		})
	})
}