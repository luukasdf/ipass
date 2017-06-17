$("#toevBTN").click(function(){
	console.log($("#form").serialize())
	//var bewonerId;
	
	$.get("restservices/huis/minstetaken/1", function(data) {    	
		$.each(data, function(k, v) {
			console.log("test")
		});
	});
	
	
});