function validate() {
	var password1 = document.getElementById("password1").value;
	var password2 = document.getElementById("password2").value;
	
	if(password1 != password2) {
		alert("Passwords do not match");
		return false;
	} else if(password1 == password2 && password2.length < 6) {
		alert("Password should be at least 6 characters");
		return false;
	}
	
	return true;
}

function checkIfTotalHigher() {
	var avail = document.getElementById("availableshares").value;
	var total = document.getElementById("totalshares").value;
	
	if(avail > total) {
		alert("Available Shares cannot be greater than Total Shares");
		return false;
	}
	
	return true;
}