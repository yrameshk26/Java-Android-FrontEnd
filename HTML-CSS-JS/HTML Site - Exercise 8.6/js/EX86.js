// This say that this functionloads first when page loads
window.addEventListener("load", dofirst);

// This is the definition of function which is supposed to load first
function dofirst() {
					// signUpFunc function loads when signUpBtn button clicked
					document.getElementById("signUpBtn").addEventListener("click", signUpFunc);
					// logInFunc function loads when logInBtn button clicked
					document.getElementById("logInBtn").addEventListener("click", logInFunc);
}

// This defines the singup function
function signUpFunc() {
					var nameFunc = document.getElementById("name").value;		//declares the input element name variable 
					var newPW = document.getElementById("nwPW").value;			//declares the input element new password variable 
					var retypPW = document.getElementById("chkPW").value;		//declares the input element retype password variable
					var newEmail = document.getElementById("newEmail").value;	//declares the input element new email variable
					var atpos = newEmail.indexOf("@");							//declares the position of '@' sign in new Email input
    				var dotpos = newEmail.lastIndexOf(".");						//declares the position of '.'' period in new Email input
					var todayMs =  Date.now();									//finds and declares todays date
					var today = new Date (todayMs);								//change today's date to date format
					var todayYear = today.getFullYear();						//finds and declares today's date's year
					var BdayMs = document.getElementById("dob").value;			//stores date of birth
					var Bday = new Date (BdayMs);								//change it to date format
					var BdayYear = Bday.getFullYear();							//finds year of birth
					var ageY = todayYear - BdayYear;							//calculates and declares age

					//checks for any null or blank values in input fields
					if (nameFunc == null || nameFunc == ""|| newPW == null || newPW == ""|| retypPW == null || retypPW == ""|| newEmail == null || newEmail == ""|| BdayMs == null || BdayMs == "") {
						//Gives alert if no values found
						alert ("Please Fill In The Information Before Clicking Singup Button.");
						return false;
					}
					//check if email is valid
					else if (atpos<1 || dotpos<atpos+2 || dotpos+2>=newEmail.length) {
						//Gives alert if email is invalid
						alert ("Please Enter A Valid Email Address");
						return false;
					}
					else {
						//checks age of user
						if (ageY>=18) {
							//check whether both password matches
							if (newPW == retypPW) {
								//Output welcome text
								document.getElementById("Output").innerHTML = 'Welcome &nbsp;' + nameFunc + '&nbsp;!!!!! <br>' + 'You are &nbsp;' + ageY + '&nbsp;years old, and you are <br> GOOD TO GO!!!' ;
								//Changes background Color
								document.getElementsByTagName("body")[0].style.backgroundColor = "#85BB65";
								return false;
							} 
							else{
								//Gives warning Message in output field
								document.getElementById("Output").innerHTML = 'Passwords Doesnt Match. Please Retype' ;
								//Gives alert of passwords doesnt match
								alert("Passwords Doesnt Match. Please Retype");
								return false;
							}
							
						}
						else {
							//Gives warning in output field if age is less than 18.
							document.getElementById("Output").innerHTML = 'You are &nbsp;' + ageY + "&nbsp;years old, and you are NOT ALLOWED to be here!!!" ;
							// changes background color to red to warn
							document.getElementsByTagName("body")[0].style.backgroundColor = "red";
							// gives warning alert of age restriction
							alert("Get Out");
							return false;

						}
					}
}
	
//This defines the login function				
function logInFunc() {
					var extEmail = document.getElementById("extEmail").value;					//declares the input element Existing Email variable 
					var extPW = document.getElementById("extPW").value;							//declares the input element Existing Password variable 
					var atpos = extEmail.indexOf("@");											//declares the position of '@' sign in new Email input
    				var dotpos = extEmail.lastIndexOf(".");										//declares the position of '.'' period in new Email input
					//checks for any null or blank values in input fields
					if (extEmail == null || extEmail == ""|| extPW == null || extPW == "") {
						//Gives alert if no values found
						alert (" Please Fill In The Information Before Clicking Login Button.");
					}
					//check if email is valid
					else if (atpos<1 || dotpos<atpos+2 || dotpos+2>=extEmail.length) {
						//Gives alert if email is invalid
						alert ("Please Enter A Valid Email Address")
					}
					else { 
							//check matching user name and password for login credentials
							if(extEmail == "yrameshk26@gmail.com" && extPW == "blabla2016"){
								//changes output text to welcome user and changes background color to green
								document.getElementById("Output").innerHTML = 'Welcome Back &nbsp; Ramesh' ;
								document.getElementsByTagName("body")[0].style.backgroundColor = "#85BB65";
							}
							//check matching user name and password for login credentials
							else if(extEmail == "batman@superman.com" && extPW == "ironman99"){
								//changes output text to welcome user and changes background color to green
								document.getElementById("Output").innerHTML = 'Welcome Back &nbsp; Bruce' ;
								document.getElementsByTagName("body")[0].style.backgroundColor = "#85BB65";
							}
							else {
								//Gives alert if no matching username and password found and changes background color to yellow
								alert ("The Email Address and Password Doesnt Match.");
								document.getElementById("Output").innerHTML = 'The Email Address and Password Doesnt Match.' ;
								document.getElementsByTagName("body")[0].style.backgroundColor = "yellow";
							}
					}
}











