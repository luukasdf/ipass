window.onload=initpage

function initpage(){
	takenWeergeven(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))
}

function takenWeergeven(userID, afdelingID){
	$.get("restservices/huis/taken", function(data) {    
		var vandaag = new Date;
		vandaag.setDate(vandaag.getDate()-1);
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
$("#verzoekOpstellen").click(function(){	
	if ($("#Reden").val() != "" && ($("#taakID").val()) != null && $("#taakID2").val() != null){
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
			//var reden = $("#Reden").val();
			
			var b = $("#Reden").val().split("/")
			var reden = "";
			$.each(b, function(ka, ve){
				reden += ve;
			})
			var uri = "restservices/huis/ruilverzoeken/"+reden+"/"+verzTaak+"/"+ontvTaak+"/"+verzID+"/"+ontvID;
			var form = 'reden='+$("#Reden").val();
			$.post(uri, form, function(response) {
				$("#response").html("Er is een verzoek verzonden!")
			}); 	
		});
	}
	if ($("#Reden").val() == ""){
		$("#response").html("Er is geen reden ingevoerd")
	}
	if (($("#taakID").val()) == null){
		$("#response").html("Er is geen verzend taak ingevoerd")
	}
	if ($("#taakID2").val() == null){
		$("#response").html("Er is geen ontvang taak ingevoerd")
	}
});




