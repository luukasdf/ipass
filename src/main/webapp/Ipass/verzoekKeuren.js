window.onload=initpage

function initpage(){
	verzoekenWeergeven(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
}

function getRuilverzoeken(userID, afdelingID){
	var ruilverzoeken = []
	$.get("restservices/huis/ruilverzoeken", function(data) {    
		$.each(data, function(k, v) {
			if (v.ontvangerId == userID){
				ruilverzoeken.push(v);
			}
		});		
	});	
	return ruilverzoeken;
}

function getBewoners(userID, afdelingID){
	var bewoners = []
	$.get("restservices/huis/bewoners", function(data) {    
		$.each(data, function(k, v) {
			if (v.afdelingID == afdelingID){
				bewoners.push(v);
			}
		});	
	});	
	return bewoners;
}

function getTaken(userID, afdelingID){
	var taken = []
	$.get("restservices/huis/taken", function(data) {  
		$.each(data, function(k, v) {
			if (v.afdelingID == afdelingID){
				taken.push(v);
			}
		});		
	});
	return taken
}

function verzoekenWeergeven(userID, afdelingID){
	var ruilverzoeken = []
	$.get("restservices/huis/ruilverzoekenjoined", function(data) {    
		$.each(data, function(k, v) {
			if (v.ontvangerId == userID){
				ruilverzoeken.push(v);
			}
		});	
		//console.log(ruilverzoeken)
		
		$.each(ruilverzoeken, function(k, v){
			//console.log(v.naam +" wil "+ v.taaknaam + " in de " + v.tijdstip + " ruilen voor " + v.taaknaam2)
			$("#ruilverzoekSelect")
				.append($("<option></option>")
					.text(v.naam +" wil "+ v.taaknaam + " in de " + v.tijdstip + " ruilen voor " + v.taaknaam2)
						.val(v.ruilverzoekId+","+v.verzendId+","+v.verzendTaakId+","+v.ontvangerId+","+v.ontvangTaakId)); console.log(v)
		});
		
	});

	
}

$("#accepterenBTN").click(function(){
	//console.log("Clicked");
	//console.log($("#ruilverzoekSelect").val());
	var ruilverzoekId = $("#ruilverzoekSelect").val().split(",")[0];
	var verzId = $("#ruilverzoekSelect").val().split(",")[1];
	var verzTaakId = $("#ruilverzoekSelect").val().split(",")[2];
	var ontvId = $("#ruilverzoekSelect").val().split(",")[3];
	var ontvTaakId = $("#ruilverzoekSelect").val().split(",")[4];
	
	/*console.log(ruilverzoekId)
	console.log(verzId)
	console.log(verzTaakId)
	console.log(ontvId)
	console.log(ontvTaakId)*/
	
	var uri = "restservices/huis/takenruilen/" + ruilverzoekId +"/"+ verzId +"/"+ verzTaakId +"/"+ ontvId +"/"+ ontvTaakId;
	//console.log(uri)
    $.ajax(uri, { 
        type: "put",  
        success: function(response) {
            $("#response").text("geruild!");
        },
        error: function(response) {
            $("#response").text("geruild!");
        }
        
    });
});
