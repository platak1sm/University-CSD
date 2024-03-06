
function sendAjaxGET() {
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('ajaxContent')
                    .innerHTML = xhr.responseText;
            // $("#myForm").hide();
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    var params = "";
    for (let [name, value] of formData) {
        params += name + "=" + value + "&";
    }
    params = params.substring(0, params.length - 1);
    xhr.open('GET', 'Echo?' + params);
    xhr.send();
}

function loginPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser();
            $("#ajaxContent").html("Successful Login");
        } else if (xhr.status !== 200) {
            $("#error").html("Wrong Credentials");
        }
    };
    var data = $('#loginForm').serialize();
    xhr.open('POST', 'Login');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function setChoicesForLoggedUser() {
    $("#login").html("");
    $("#login").append("<h1>Choices</h1>");
    $("#login").append("<button onclick='getDataRequest()' class='button' >See Your Data</button><br>");
    $("#login").append("<button onclick='logout()'  class='button'>Logout</button><br>");
}

function getDataRequest() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $('#ajaxContent').html("<h1>Your Data</h1>");
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('GET', 'Login');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}


function showLogForm() {
    $("#ajaxContent").load("login.html");
    $("#op").html("");
}


function logout() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $('#login').load("button.html");
            $("#ajaxContent").html("Successful Logout");
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'Logout');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

