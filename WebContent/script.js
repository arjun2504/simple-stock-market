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
	var avail = parseInt(document.getElementById("availableshares").value);
	var total = parseInt(document.getElementById("totalshares").value);
	var price = parseInt(document.getElementById("price").value);
	
	if(avail > total) {
		alert("Available Shares cannot be greater than Total Shares");
		return false;
	}
	
	if(price > 100000) {
		alert("Price cannot be more than Rs. 1,00,000");
		return false;
	}
	
	return true;
}