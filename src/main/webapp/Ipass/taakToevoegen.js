window.onload=initpage

function initpage(){
	bewonersLaden(window.sessionStorage.getItem("userID"), window.sessionStorage.getItem("afdelingID"))	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 

	today = yyyy+'-'+mm+'-'+dd;
	document.getElementById("date").setAttribute("min", today);
	
	
}



function bewonersLaden(userID, afdelingID){
	
	var bewoners=[];
	$.get("restservices/huis/bewoners", function(data) {    
		$.each(data, function(k, v) {
			if (v.afdelingID == afdelingID){
				bewoners.push(v);
				
			}
		});		
		$.each(bewoners, function(k, v) {  
		     $('#userId')
		         .append($("<option></option>")
		                    .text(v.naam)
		                    	.val(v.persoonsnummer)); 
		});
	});	
}

$("#toevBTN").click(function(){
	var data = $("#form").serialize();
	console.log(data)
	var d = new Date($("#date").val())
	
	var newD = new Date()
	newD.setHours(0,0,0,0)
	console.log(d > newD)
	
	var uri = "restservices/huis/taken/"+window.sessionStorage.getItem("afdelingID");
	
	console.log($("#date").val() != "" && $("#duur").val() != "");
	if ($("#naam").val() != "" && $("#date").val() != "" && $("#duur").val() != "" && d > newD){
		console.log("Post request")
	$.post(uri, data, function(response) {
		$("#response").text("Taak added!");
    }); 
	}
	if ($("#naam").val() == ""){
		$("#response").text("Er is geen naam ingevoerd!");
	}
	if ($("#date").val() == ""){
		$("#response").text("Er is geen correcte datum ingevoerd!");
	}
	if ($("#duur").val() == ""){
		$("#response").text("Er is geen correcte duur ingevoerd!");
	}
	if (d < newD){
		$("#response").text("De datum ligt in het verleden!");
	}
	
});

