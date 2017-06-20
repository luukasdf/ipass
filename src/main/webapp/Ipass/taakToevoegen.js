window.onload=initpage

function initpage(){	
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
//voegt een taak toe aan de hand van de verstrekte informatie
$("#toevBTN").click(function(){
	var data = $("#form").serialize();
	var d = new Date($("#date").val())
	var newD = new Date()
	newD.setHours(0,0,0,0)
	//taak wordt automatisch aan de persoon gegeven met de minste taken (minste duur) 
	var uri = "restservices/huis/taken/"+window.sessionStorage.getItem("afdelingID");
	if ($("#naam").val() != "" && $("#date").val() != "" && $("#duur").val() != "" && d > newD){
		$.post(uri, data, function(response) {
			$("#response").text("Taak added!");
	    }); 
	}
	//checken of de verstrekte informatie correct is
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

