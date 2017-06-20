window.onload=initpage

function initpage(){
	verzoekenWeergeven(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
}
//geeft de verzoeken weer in een dropdown menu
function verzoekenWeergeven(userID, afdelingID){
	var ruilverzoeken = []
	$.get("restservices/huis/ruilverzoekenjoined", function(data) {    
		$.each(data, function(k, v) {
			if (v.ontvangerId == userID){
				ruilverzoeken.push(v);
			}
		});	
		$.each(ruilverzoeken, function(k, v){
			$("#ruilverzoekSelect")
				.append($("<option></option>")
					.text(v.naam +" wil "+ v.taaknaam +" op "+ v.datum +" in de " + v.tijdstip + " ruilen voor " + v.taaknaam2 + " op " + v.datum2 + " in de " + v.tijdstip2)
						.val(v.ruilverzoekId+","+v.verzendId+","+v.verzendTaakId+","+v.ontvangerId+","+v.ontvangTaakId)); console.log(v)
		});
		
	});

	
}

$("#accepterenBTN").click(function(){
	if ($("#ruilverzoekSelect").val() != null){	
		//haalt de informatie op uit de dropdown
		var ruilverzoekId = $("#ruilverzoekSelect").val().split(",")[0];
		var verzId = $("#ruilverzoekSelect").val().split(",")[1];
		var verzTaakId = $("#ruilverzoekSelect").val().split(",")[2];
		var ontvId = $("#ruilverzoekSelect").val().split(",")[3];
		var ontvTaakId = $("#ruilverzoekSelect").val().split(",")[4];		
		var uri = "restservices/huis/takenruilen/" + ruilverzoekId +"/"+ verzId +"/"+ verzTaakId +"/"+ ontvId +"/"+ ontvTaakId;
	
	    $.ajax(uri, { 
	        type: "put",  
	        success: function(response) {
	            $("#response").text("De taken zijn geruild!");
	        },
	    });	    
	    $('#ruilverzoekSelect')
	    .find('option')
	    .remove()
	    .end()
	    .append('<option value="" disabled selected>Maak een keuze</option>')
	    .val("");
	    setTimeout(verzoekenWeergeven(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID")), 1000);
	    alert("De taken zijn geruild!")
	    location.reload();

	}
	if ($("#ruilverzoekSelect").val() == null){		
		$("#response").text("Er is geen taak geselecteerd!")
	}
});
//taak verwijderen
$("#declineBTN").click(function(){
	if ($("#ruilverzoekSelect").val() != null){	
	var ruilverzoekId = $("#ruilverzoekSelect").val().split(",")[0];
	var uri = "restservices/huis/ruilverzoeken/" + ruilverzoekId 
	console.log(uri)
	$.ajax(uri, {
		type: 'delete',
		success: function(response) {
	       $("#response").text("Verzoek afgekeurd!");
	       $("#ruilverzoekSelect").find('option:selected', this).remove();
	       
	    },
	    error: function(response) {
	    	$("#response").text("verzoek kan niet afgekeurd worden!");    	
	    }
	    });
	}
	if ($("#ruilverzoekSelect").val() == null){		
		$("#response").text("Er is geen taak geselecteerd!")
	}
	
});


