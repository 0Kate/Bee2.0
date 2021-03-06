var masked = true;
			
$(function() {
	$('#passcheck').on('click', function(event) {
	if ($('#passwordInput').val() == "") return false;
	
	if (masked) {
		$('#passwordInput').attr('type', 'text');
		switchEyeSlash('#showIcon');
	} else {
		$('#passwordInput').attr('type', 'password');
		switchEye('#showIcon');
		}
		
		masked = !masked; 
	});
});

$(function() {
	$('#loginForm').submit(function(event) {
		if ($('#usernameInput').val() == "") {
			$('#usernameInput').addClass('is-invalid');
			alert("Error : Please enter username.");
			return false;
		}
		
		if ($('#passwordInput').val() == "") {
			$('#passwordInput').addClass('is-invalid');
			alert("Error : Please enter password.");
			return false;
		}
	});
});

function switchEye(id) {
	showIcon = $(id);
	showIcon.removeClass('fa-eye-slash');
	showIcon.addClass('fa-eye');
}

function switchEyeSlash(id) {
	showIcon = $(id);
	showIcon.removeClass('fa-eye');
	showIcon.addClass('fa-eye-slash');
}