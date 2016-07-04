function changeStyle(){
	var valueA =4;
	var valueB =3;

	var clickEvent = document.getElementById("demo").addEventListener("click",function() {secondFunction(valueA,valueB);};);

}


function secondFunction (a,b){
	var newValue;
	newValue = a * b;
	document.getElementById.('demo').innerHTML = newValue;
}