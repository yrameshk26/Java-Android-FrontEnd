function changeStyle(){
	var text = document.getElementById("paragraph1").style.color = "red";
	//var text = document.getElementById('paragraph1').innerHTML = "red";
}

function multiPara(){
	var change = document.getElementsByTagName("p");
	//var changeTextStyle = change[1].style.fontStyle = "italic"
	for (var i = 0; i <change.length; i++) {
		i+=1;
		var changeTextStyle = change[i].innerHTML = i;
	};

}