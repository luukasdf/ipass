window.onload=initpage

function initpage(){
	var d1 = new Date()
	var d2 = new Date()
	agendaInladen(window.sessionStorage.getItem("userID"), d1, d2);
	
}
function agendaInladen(userID, vandaag , vandaag2){	
	$.get("restservices/huis/taken", function(data) {       
	   $.each(data, function(k, v) {
		   var taak = v.naam;
		  var d = new Date(v.datum);

		   if(v.afgetekend == 'Ja'){
			  taak = "<s>" + taak + "</s>"
		   }
		   //var vandaag = new Date();
		   //var vandaag2 = new Date();
		   vandaag2 = getMonday(vandaag2);
		   var firstday = vandaag = getMonday(vandaag)	   
		   var lastday = vandaag2.setDate(vandaag2.getDate() + 6)
		   lastday = new Date(lastday)
		   if(d > firstday.setHours(0,0,0,0) && d.setHours(0,0,0,0) < lastday){
	        if (v.bewonerID == userID){
	        	if (d.getDay() == 1){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("#maandagOchtend").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Middag'){
		        		$("#maandagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#maandagAvond").append(taak+"<br>")
		        	}
	        	}
	        	if (d.getDay() == 2){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("dinsdagOchtend").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Middag'){
		        		$("#dinsdagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#dinsdagAvond").append(taak+"<br>")
		        	}
	        	}
	        	if (d.getDay() == 3){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("woensdagOchtend").append(taak+"<br>")
		        	}
		        	if (data[k].tijdstip == 'Middag'){
		        		$("#woensdagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#woensdagAvond").append(taak+"<br>")
		        	}
	        	}
	        	if (d.getDay() == 4){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("donderdagOchtend").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Middag'){
		        		$("#donderdagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#donderdagAvond").append(taak+"<br>")
		        	}
	        	}
	        	if (d.getDay() == 5){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("vrijdagOchtend").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Middag'){
		        		$("#vrijdagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#vrijdagAvond").append(taak+"<br>")
		        	}
	        	}
	        	if (d.getDay() == 6){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("zaterdagOchtend").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Middag'){
		        		$("#zaterdagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#zaterdagAvond").append(taak+"<br>")
		        	}
	        	}
	        	if (d.getDay() == 0){
	        		if (v.tijdstip == 'Ochtend'){
	        			$("zondagOchtend").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Middag'){
		        		$("#zondagMiddag").append(taak+"<br>")
		        	}
		        	if (v.tijdstip == 'Avond'){
		        		$("#zondagAvond").append(taak+"<br>")
		        	}
	        	}
	         }
	        }
	   });
})	    
	function getMonday(d) {
	  d = new Date(d);
	  var day = d.getDay(),
	      diff = d.getDate() - day + (day == 0 ? -6:1);
	  return new Date(d.setDate(diff));
	}	
}

function clearTable(){
	$("#maandagOchtend").html("")
	$("#maandagMiddag").html("")
	$("#maandagAvond").html("")
	
	$("#dinsdagOchtend").html("")
	$("#dinsdagMiddag").html("")
	$("#dinsdagAvond").html("")

	$("#woensdagOchtend").html("")
	$("#woensdagMiddag").html("")
	$("#woensdagAvond").html("")
	
	$("#donderdagOchtend").html("")
	$("#donderdagMiddag").html("")
	$("#donderdagAvond").html("")
	
	$("#vrijdagOchtend").html("")
	$("#vrijdagMiddag").html("")
	$("#vrijdagAvond").html("")
	
	$("#zaterdagOchtend").html("")
	$("#zaterdagMiddag").html("")
	$("#zaterdagAvond").html("")
		
	$("#zondagOchtend").html("")
	$("#zondagMiddag").html("")
	$("#zondagAvond").html("")
}

$("#weekVeranderen").click(function(){
	if ($("#date").val() != ""){
		$("#welkomsbericht").html("<b>Agenda overzicht, uw taken in de week van: " + $("#date").val() + "</b>")
		clearTable()
		$("#response").html("")
		var da1 = new Date ($("#date").val())
		var da2 = new Date ($("#date").val())
		agendaInladen(window.sessionStorage.getItem("userID"), da1, da2)
	}
	if ($("#date").val() == ""){
		$("#response").html("Er is geen geldige datum ingevuld")
	}
})

