window.onload=initpage

function initpage(){
	takenWeergeven(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
}
//geeft de te ruilen en de te ontvangen taken weer in twee verschillende dropdown select inputs.
function takenWeergeven(userID, afdelingID){
	$.get("restservices/huis/taken", function(data) {    
		var vandaag = new Date;
		vandaag.setHours(0,0,0,0);
		var ruilTaken = [];
		$.each(data, function(k, v) {
			if (new Date(v.datum) > vandaag && v.afgetekend == 'Nee' && v.bewonerID == userID){
				ruilTaken.push(v);
			}
		});
		$.each(ruilTaken, function(k, v) {   
		    $('#taakID').append($("<option></option>").text(v.naam +" op "+ v.datum +", in de " + v.tijdstip).val(v.taakid)); 	     
		});
	});
	//haalt mogelijke ruilpartners op met bijbehorende taak en geeft deze weer in een dropdown.
	$.get("restservices/huis/ruilopties", function(data) {    
		var vandaag = new Date;
		vandaag.setDate(vandaag.getDate()-1);
		var ruilOpties = [];
		$.each(data, function(k, v) {
			if (new Date(v.datum) > vandaag && v.bewonerid != userID && v.afdelingid == afdelingID){				
				ruilOpties.push(v);
			}		
		});
		$.each(ruilOpties, function(k, v) {   
		     $('#taakID2')
		         .append($("<option></option>")
		                    .text(v.taaknaam + " van "+v.naam + " op "+ v.datum +", in de " + v.tijdstip)
		                    	.val(v.bewonerid+","+ v.taakid)); 
		});
	});

	
}

$("#verzoekBTN").click(function(){
	window.location.href = "taakrui.html";			
});
$("#verderBTN").click(function(){
	window.location.href = "taakver.html";			
});

//stelt een ruilverzoek op.
$("#verzoekOpstellen").click(function(){	
	if (($("#taakID").val()) != null && $("#taakID2").val() != null){
		$.get("restservices/huis/ruilverzoeken", function(data) {    
			var taakIDs = [];
			$.each(data, function(k, v) {
				taakIDs.push(v.ruilverzoekId);				
			});
			taakIDs.sort();
			var verzID = window.sessionStorage.getItem("userID");
			var verzTaak = ($("#taakID").val());
			var ontvTaak = $("#taakID2").val().split(",")[1];
			var ontvID = $("#taakID2").val().split(",")[0];
			//de reden functionaliteit heb ik niet naar behoren in beeld kunnen brengen bij het
			//keuren van een ruilverzoek, dus heb ik deze geschrapt.
			//echter is er nog wel code aanwezig voor deze functionaliteit dus het kan achteraf
			//vrij makkelijk geimplementeerd worden.
			var reden = "Deze functionaliteit is tijdelijk uitgeschakeld";
			var uri = "restservices/huis/ruilverzoeken/"+reden+"/"+verzTaak+"/"+ontvTaak+"/"+verzID+"/"+ontvID;
			$.post(uri, function(response) {
				$("#response").html("Er is een verzoek verzonden!")
			}); 	
		});
	}

	if (($("#taakID").val()) == null){
		$("#response").html("Er is geen verzend taak ingevoerd")
	}
	if ($("#taakID2").val() == null){
		$("#response").html("Er is geen ontvang taak ingevoerd")
	}
});




