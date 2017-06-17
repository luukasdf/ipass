window.onload=initpage

function initpage(){
	agendaInladen(window.sessionStorage.getItem("userID"));
}
function agendaInladen(userID){	
	$.get("restservices/huis/taken", function(data) {       
	   $.each(data, function(k, v) {
		   var taak = v.naam;
		   var d = new Date(v.datum);
		   var curr = new Date;
		   var firstday = new Date(curr.setDate(curr.getDate() - curr.getDay()+1));
		   var lastday = new Date(curr.setDate(curr.getDate() - curr.getDay()+7));
		   if(v.afgetekend == 'Ja'){
			  taak = "<s>" + taak + "</s>"
		   }
		   
		   if(d > firstday && d < lastday){
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

	    
}