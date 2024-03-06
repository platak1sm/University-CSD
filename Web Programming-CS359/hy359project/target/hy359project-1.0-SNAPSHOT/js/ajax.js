
$(document).ready(function ()
{
    isLoggedIn();
});

function isLoggedIn() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser();
            $("#op").html("Welcome again " + xhr.responseText);
            $("#ajaxContent").html("");
        } else if (xhr.status !== 200) {
            $("#op").html("Welcome to E-Library!");
            $("#register").load("button.html");
        }
    };
    xhr.open('GET', 'Login');
    xhr.send();
}

function sendAjaxGET() {
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('ajaxContent')
                    .innerHTML = xhr.responseText;
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
            $("#op").html("");
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
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='SeeBooksPOST()'  class='button'>See books</button><br>");
    $("#register").append("<button onclick='getDataRequest()' class='button' >See Your Data</button><br>");
    $("#register").append("<button onclick='seeborrowings()' class='button' >See Your Borrowings</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
}

function getDataRequest() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='edit()' class='button' >Edit Data</button><br>");
    $("#register").append("<button onclick='SeeBooksPOST()'  class='button'>See books</button><br>");
    $("#register").append("<button onclick='seeborrowings()' class='button' >See your borrowings</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $('#ajaxContent').html(xhr.responseText);
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('POST', 'GetInfo');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}

function getDataRequest2() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='edit2()' class='button' >Edit Data</button><br>");
    $("#register").append("<button onclick='back3()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $('#ajaxContent').html(xhr.responseText);
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('POST', 'GetInfoLib');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}


function showLogForm() {
    $("#ajaxContent").load("login.html");
    $("#op").html("");
}

function showLogLibForm() {
    $("#ajaxContent").load("loginlib.html");
    $("#op").html("");
}

function logout() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $('#register').html("");
            $('#register').load("button.html");
            $("#op").html("Welcome to E-Library!");
            $("#ajaxContent").html("Successful Logout");
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'Logout');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}



function showAdmForm() {
    $("#ajaxContent").load("admin.html");
    $("#op").html("");
}

function edit() {
    $("#ajaxContent").load("editt.html");
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back1()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
}

function edit2() {
    $("#ajaxContent").load("edit2.html");
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back3()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
}

function updateSPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser();
            $("#op").html("");
            $("#ajaxContent").html("Successfully updated");
        } else if (xhr.status !== 200) {
            $("#error").html("Cant find username");
        }
    };
    var data = $('#editForm').serialize();
    xhr.open('POST', 'UpdateS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function updateLPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser2();
            $("#op").html("");
            $("#ajaxContent").html("Successfully updated");
        } else if (xhr.status !== 200) {
            $("#error").html("Cant find username");
        }
    };
    var data = $('#edit2Form').serialize();
    xhr.open('POST', 'UpdateL');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function SeeBooksPOST() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back1()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'SeeBooks');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}


function showav(n) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser();
            $("#op").html("");
            $("#ajaxContent").html(xhr.response);
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("There is no availability");
        }
    };
    xhr.open('GET', 'ShowAvailability?isbn=' + n);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();
}

function back1() {
    $("#op").html("Welcome to E-Library!");
    $("#ajaxContent").html("");
    setChoicesForLoggedUser();
}

function showmore(n) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#register").html("");
            $("#register").append("<h1>Choices</h1>");
            $("#register").append("<button onclick='SeeBooksPOST()'  class='button'>Back</button><br>");
            $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
            $("#op").html("");
            $("#ajaxContent").html(xhr.response);
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("There is no info");
        }
    };
    xhr.open('GET', 'ShowMore?isbn=' + n);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();
}

function borrowfrom(x, n) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#register").html("");
            $("#register").append("<h1>Choices</h1>");
            $("#register").append("<button onclick='SeeBooksPOST()'  class='button'>Back</button><br>");
            $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
            $("#op").html("");
            $("#ajaxContent").html(xhr.response);
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("Can't be borrowed");
        }
    };
    xhr.open('GET', 'Borrow?library_id=' + x + '&isbn=' + n);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();
}

function seeborrowings() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back1()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("No borrowings");
        }
    };
    xhr.open('POST', 'SeeBorrowings');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function seeborrowings2() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back3()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("No borrowings");
        }
    };
    xhr.open('POST', 'SeeBorrowingsLib');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function back3() {
    $("#op").html("Welcome to E-Library!");
    $("#ajaxContent").html("");
    setChoicesForLoggedUser2();
}

function back4() {
    $("#op").html("Welcome to E-Library!");
    $("#ajaxContent").html("");
    adminch();
}

function loginlibPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser2();
            $("#op").html("");
            $("#ajaxContent").html("Successful Login");
        } else if (xhr.status !== 200) {
            $("#error").html("Wrong Credentials");
        }
    };
    var data = $('#loginlibForm').serialize();
    xhr.open('POST', 'LoginLib');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function setChoicesForLoggedUser2() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='addbook()'  class='button'>Add a book</button><br>");
    $("#register").append("<button onclick='getDataRequest2()' class='button' >See Your Data</button><br>");
    $("#register").append("<button onclick='seeborrowings2()' class='button' >See Library's Borrowings</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
}

function addbook() {
    $("#op").html("<u>New book info</u>");
    $("#ajaxContent").load("addbook.html");
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back3()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='logout()'  class='button'>Logout</button><br>");
}

function addbookPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            setChoicesForLoggedUser2();
            $("#op").html("");
            $("ajaxContent").html("Successfully entered the database");
        } else if (xhr.status !== 200) {
            $("#error").html("Wrong info");
        }
    };
    var data = $('#bookForm').serialize();
    xhr.open('POST', 'AddBook');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function adminch() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='seeLibrarians()'  class='button'>Show Librarians</button><br>");
    $("#register").append("<button onclick='seeStudents()' class='button' >Show Students</button><br>");
    $("#register").append("<button onclick='exxit()'  class='button'>Exit</button><br>");
}

function loginAdmPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            adminch();
            $("#op").html("");
            $("#ajaxContent").html("Successful Login");
        } else if (xhr.status !== 200) {
            $("#error").html("Wrong Credentials");
        }
    };
    var data = $('#admForm').serialize();
    xhr.open('POST', 'LoginAdmin');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function seeLibrarians() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back4()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='exxit()'  class='button'>Exit</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return librarians");
        }
    };
    xhr.open('POST', 'SeeLibs');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function seeStudents() {
    $("#register").html("");
    $("#register").append("<h1>Choices</h1>");
    $("#register").append("<button onclick='back4()' class='button' >Back</button><br>");
    $("#register").append("<button onclick='exxit()'  class='button'>Exit</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return students");
        }
    };
    xhr.open('POST', 'SeeStudents');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function del(x) {
    adminch();
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully deleted");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't delete user");
        }
    };
    xhr.open('GET', 'DelUser?username='+x);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}