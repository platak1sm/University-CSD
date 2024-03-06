

$(document).ready(function ()
{
    connect();
});

function connect() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html("database ready");
        } else if (xhr.status !== 200) {
            $("#ajaxContent").html("database failed");
        }
    };
    xhr.open('POST', 'Connect');
    xhr.send();
}
function showEmployees() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<h2>Select action</h2>");
    $("#buttons").append("<button class=\"button\" onclick=\"addPermanent();\">Add Permanent Employee</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"addContract();\">Add Contract Employee</button>");
}

function addPermanent() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("permform.html");
}


function addPermanentPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't add");
        }
    };
    var data = $('#permForm').serialize();
    xhr.open('POST', 'AddPermanent');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function addContract() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("contform.html");
}

function addContractPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't add");
        }
    };
    var data = $('#contForm').serialize();
    xhr.open('POST', 'AddContract');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function showChildForm() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("childform.html");
}

function addChildPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("eid doesn't exist.");
        }
    };
    var data = $('#childForm').serialize();
    xhr.open('POST', 'AddChild');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}


function editPOST(eid) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully edited");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't edit employee");
        }
    };
    var data = $('#editForm').serialize();
    xhr.open('POST', 'EditEmp');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);

}

function editformm() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("editform.html");
}

function bsform() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("editbs.html");
}

function alform() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("edital.html");
}

function editBSPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully edited");
        } else if (xhr.status !== 200) {
            $("#error").html("Nothing to edit about basic salaries");
        }
    };
    var data = $('#bsForm').serialize();
    xhr.open('POST', 'EditBS');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function editAlPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully edited");
        } else if (xhr.status !== 200) {
            $("#error").html("Nothing to edit about allowances");
        }
    };
    var data = $('#alForm').serialize();
    xhr.open('POST', 'EditAl');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function renewform() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("renewf.html");
}

function renewPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully renewed");
        } else if (xhr.status !== 200) {
            $("#error").html("Renewal failed");
        }
    };
    var data = $('#rForm').serialize();
    xhr.open('POST', 'Renew');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function sqlinput() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("sql.html");
}

function sqlPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("SQL failed");
        }
    };
    var data = $('#sqlForm').serialize();
    xhr.open('POST', 'SQL');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function quest() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button><br>");
    $("#ajaxContent").html("");
    $("#buttons").append("<button class=\"button\" onclick=\"plogPOST();\">PaymenLogInfo</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"mmaPOST();\">Max/Min/Average Salary</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"incform();\">Average increase of Salary/Allowances</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"einfoform();\">Employee info</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"ptotalsPOST();\">Payment totals</button><br>");
}

function firef() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showchoices();\">Back</button>");
    $("#ajaxContent").load("fire.html");
}

function firePOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully fired/retired");
        } else if (xhr.status !== 200) {
            $("#error").html("Firement/Retirement failed");
        }
    };
    var data = $('#fForm').serialize();
    xhr.open('POST', 'Fire');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function plogPOST() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("PaymentLog failed");
        }
    };
    xhr.open('POST', 'PaymentLog');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function mmaPOST() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("MaxMinAvg salary failed");
        }
    };
    xhr.open('POST', 'MaxMinAvg');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function incform() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    $("#ajaxContent").load("incform.html");
}

function incPOST() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Avg salary/allowances incr. failed");
        }
    };
    var data = $('#incForm').serialize();
    xhr.open('POST', 'AvgSAI');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function einfoform() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    $("#ajaxContent").load("einfo.html");
}

function einfoPOST() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Employee Info failed");
        }
    };
    var data = $('#eiForm').serialize();
    xhr.open('POST', 'EInfo');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function ptotalsPOST() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"quest();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Payment Totals failed");
        }
    };
    xhr.open('POST', 'Ptotals');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}



function showchoices() {
    $("#buttons").html("");
    $("#op").html("Output");
    $("#ajaxContent").html("");
    $("#buttons").append("<h2>Select action</h2>");
    $("#buttons").append("<button class=\"button\" onclick=\"showEmployees();\">Add an employee</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"editformm();\">Edit an employee</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"showChildForm();\">Add a child</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"bsform();\">Edit Basic Salaries</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"alform();\">Edit Allowances</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"renewform();\">Renew contract</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"sqlinput();\">Submit SQL query</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"firef();\">Fire/Retire</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"quest();\">Questions</button><br>");
}

