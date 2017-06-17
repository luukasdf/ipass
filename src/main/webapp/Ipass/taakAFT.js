window.onload=initpage

function initpage(){
	takenWeergeven(window.sessionStorage.getItem("userID"))
}

function getDag(){
	var d = new Date();
	var n = d.getDay();
	
	if (n == 0){
		return 'Zondag';
	}
	if (n == 1){
		return 'Maandag';
	}
	if (n == 2){
		return 'Dinsdag';
	}
	if (n == 3){
		return 'Woensdag';
	}
	if (n == 4){
		return 'Donderdag';
	}
	if (n == 5){
		return 'Vrijdag';
	}
	if (n == 6){
		return 'Zaterdag';
	}
}

function takenWeergeven(bewID){
	$.get("restservices/huis/taken", function(data) {    
		var vandaag = getDag();
		var aftekenTaken = [];
		$.each(data, function(k, v) {
			var d = new Date(v.datum);
			var curr = new Date;
			if (d.setHours(0,0,0,0) == curr.setHours(0,0,0,0)  && v.afgetekend == 'Nee' && v.bewonerID == bewID){
				aftekenTaken.push(v);
			}
		});
		$.each(aftekenTaken, function(k, v) {
			$("#aftekenTaken").append(" "+v.naam + " Met als ID " + v.taakid);
		});		
		$.each(aftekenTaken, function(k, v) {   
		     $('#taakID')
		         .append($("<option></option>")
		                    .text(v.naam + ", in de " + v.tijdstip)
		                    	.val(v.taakid)); 
		});
		
	});
}