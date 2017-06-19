window.onload=initpage

function initpage(){
	alleJongens(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
	stouteJongens(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
	//overzichtWeergeven(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
}
	
function overzichtWeergeven(userID, afdelingID){
	var bruikbareTaken = []
	var bewonerIds=  []
	$.get("restservices/huis/takenjoin", function(data) {
		$.each(data, function(k, v){
			if (v.afdelingID == afdelingID){						
				if (bewonerIds.indexOf(v.bewonerID) == -1){
					bewonerIds.push(v.bewonerID);
				}
				
			}
		});
		
		
		console.log(bewonerIds)
				
	});    
}

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