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


function RegisterPOST() {
    var myForm = document.getElementById('MyForm');
    var formData = new FormData(myForm);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const responseData = JSON.parse(xhr.responseText);
            $('#ajaxContent').html("Successful Registration.");
        } else if (xhr.status !== 200) {
            $('#ajaxContent').append('Request failed. Returned status of ' + xhr.status + "<br>");
            const responseData = JSON.parse(xhr.responseText);
            for (const x in responseData) {
                $('#ajaxContent').append("<p style='color:red'>" + x + "=" + responseData[x] + "</p>");
            }
        }
    };
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    xhr.open('POST', 'Register');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(data));
}


function showRegForm() {
    $("#ajaxContent").load("registration.html");
    $("#op").html("");
}


function guestPOST() {
    $("#register").load("button2.html");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'Guest');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showAllG() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'Guest');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showFG() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'FantasyG');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showRG() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'RomanceG');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showSG() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'SportsG');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showAG() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'AdventureG');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showNG() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'NovelG');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showAllS() {
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

function showFS() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'FantasyS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showRS() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'RomanceS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showSS() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'SportsS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showAS() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'AdventureS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showNS() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'NovelS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function writerev(x){
    /*de douleuei h seeborrowings tha eksigisw giati sthn eksetash*/
}