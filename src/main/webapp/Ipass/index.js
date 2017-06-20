window.onload=initpage;

function initpage(){
	var datum = loadDate();
	$("#welkomsbericht").html(datum);
}

//voor de home pagina, geeft de datum in de banner weer.
function loadDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	
	if(dd<10) {
	    dd='0'+dd
	} 
	
	if(mm<10) {
	    mm='0'+mm
	} 
	var date = "<b>Welkom bij Wie doet wat, het is vandaag: " + dd + "-" + mm + "-" + yyyy+"</b>";
	return date;
}