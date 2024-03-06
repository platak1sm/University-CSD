function confpw() {
    var form = document.forms.myForm;
    var str1 = form.pw.value;
    var str2 = form.pwc.value;
    var result = str1.localeCompare(str2);
    if (result != 0) {
        document.getElementById("message").innerHTML = "<u>Wrong! Not the same password!</u>";
        form.sub.disabled = true;
    } else {
        document.getElementById("message").innerHTML = "";
        form.sub.disabled = false;
    }
}
function confpw2() {
    var form = document.forms.editForm;
    var str1 = form.pw.value;
    var str2 = form.pwc.value;
    var result = str1.localeCompare(str2);
    if (result != 0) {
        document.getElementById("message").innerHTML = "<u>Wrong! Not the same password!</u>";
        form.sub.disabled = true;
    } else {
        document.getElementById("message").innerHTML = "";
        form.sub.disabled = false;
    }
}


function showPass() {
    if (document.getElementById("pw").type === "text") {
        document.getElementById("pw").type = "password";
        document.getElementById("myBut").value = "Show Password";
    } else {
        document.getElementById("pw").type = "text";
        document.getElementById("myBut").value = "Hide Password";
    }
}

function showPassConf() {
    if (document.getElementById("pwc").type === "text") {
        document.getElementById("pwc").type = "password";
        document.getElementById("myBut2").value = "Show Password";
    } else {
        document.getElementById("pwc").type = "text";
        document.getElementById("myBut2").value = "Hide Password";
    }
}

function wrpw() {
    var form = document.forms.myForm;
    var str1 = "helmepa";
    var str2 = "uoc";
    var str3 = "tuc";
    var str = form.pw.value;
    var count = 0;
    var flagU = 0;
    var flagL = 0;
    var s1 = 0, s2 = 0, s3 = 0, s4 = 0, s5 = 0, s6 = 0, s7 = 0, s = 0;
    if (str.includes(str1) || str.includes(str2) || str.includes(str3)) {
        alert("Contains wrong sequence!");
        form.sub.disabled = true;
    } else {
        form.sub.disabled = false;
    }

    var half = Math.trunc(str.length / 2 + 0.5);
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) === '0' || str.charAt(i) === '1' || str.charAt(i) === '2' || str.charAt(i) === '3' || str.charAt(i) === '4' || str.charAt(i) === '5'
                || str.charAt(i) === '6' || str.charAt(i) === '7' || str.charAt(i) === '8' || str.charAt(i) === '9') {
            count++;
            console.log("true")
        } else if (str.charAt(i).toUpperCase() === str.charAt(i)) {
            flagU = 1;
        } else if (str.charAt(i).toLowerCase() === str.charAt(i)) {
            flagL = 1;
        } else if (str.charAt(i) === "#") {
            s1 = 1;
            console.log("true");
        } else if (str.charAt(i) === '@') {
            s2 = 1;
            console.log("truea");
        } else if (str.charAt(i) === '$') {
            s3 = 1;
        } else if (str.charAt(i) === '!') {
            s4 = 1;
        } else if (str.charAt(i) === '%') {
            s5 = 1;
        } else if (str.charAt(i) === '&') {
            s6 = 1;
        } else if (str.charAt(i) === '*') {
            s7 = 1;
        }
    }
    s = s1 + s2 + s3 + s4 + s5 + s6 + s7;
    if (count >= half) {
        document.getElementById("m2").innerHTML = "<b><u>WEAK PASSWORD, CHANGE IT!</u></b>";
        form.sub.disabled = true;
    } else {
        form.sub.disabled = false;
        if (flagL === 1 && flagU === 1 && s >= 2) {
            document.getElementById("m2").innerHTML = "<b><u>STRONG PASSWORD, GOOD JOB!</u></b>";
        } else {
            document.getElementById("m2").innerHTML = "<b><u>MEDIUM PASSWORD!</u></b>";
        }
    }


}

function wrpw2() {
    var form = document.forms.editForm;
    var str1 = "helmepa";
    var str2 = "uoc";
    var str3 = "tuc";
    var str = form.pw.value;
    var count = 0;
    var flagU = 0;
    var flagL = 0;
    var s1 = 0, s2 = 0, s3 = 0, s4 = 0, s5 = 0, s6 = 0, s7 = 0, s = 0;
    if (str.includes(str1) || str.includes(str2) || str.includes(str3)) {
        alert("Contains wrong sequence!");
        form.sub.disabled = true;
    } else {
        form.sub.disabled = false;
    }

    var half = Math.trunc(str.length / 2 + 0.5);
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) === '0' || str.charAt(i) === '1' || str.charAt(i) === '2' || str.charAt(i) === '3' || str.charAt(i) === '4' || str.charAt(i) === '5'
                || str.charAt(i) === '6' || str.charAt(i) === '7' || str.charAt(i) === '8' || str.charAt(i) === '9') {
            count++;
            console.log("true")
        } else if (str.charAt(i).toUpperCase() === str.charAt(i)) {
            flagU = 1;
        } else if (str.charAt(i).toLowerCase() === str.charAt(i)) {
            flagL = 1;
        } else if (str.charAt(i) === "#") {
            s1 = 1;
            console.log("true");
        } else if (str.charAt(i) === '@') {
            s2 = 1;
            console.log("truea");
        } else if (str.charAt(i) === '$') {
            s3 = 1;
        } else if (str.charAt(i) === '!') {
            s4 = 1;
        } else if (str.charAt(i) === '%') {
            s5 = 1;
        } else if (str.charAt(i) === '&') {
            s6 = 1;
        } else if (str.charAt(i) === '*') {
            s7 = 1;
        }
    }
    s = s1 + s2 + s3 + s4 + s5 + s6 + s7;
    if (count >= half) {
        document.getElementById("m2").innerHTML = "<b><u>WEAK PASSWORD, CHANGE IT!</u></b>";
        form.sub.disabled = true;
    } else {
        form.sub.disabled = false;
        if (flagL === 1 && flagU === 1 && s >= 2) {
            document.getElementById("m2").innerHTML = "<b><u>STRONG PASSWORD, GOOD JOB!</u></b>";
        } else {
            document.getElementById("m2").innerHTML = "<b><u>MEDIUM PASSWORD!</u></b>";
        }
    }


}

function typeofperson() {
    var val = document.getElementsByName("Role");
    if (val[0].checked) {
        document.getElementById("student").style.display = "block";
        document.getElementById("library").style.display = "none";
        document.getElementById("libn").required = false;
        document.getElementById("info").required = false;
        document.getElementById("bach").required = true;
        document.getElementById("mas").required = true;
        document.getElementById("phd").required = true;
        document.getElementById("acid").required = true;
        document.getElementById("idst").required = true;
        document.getElementById("idexp").required = true;
        document.getElementById("university").required = true;
        document.getElementById("dep").required = true;
        document.getElementById("adstt").innerHTML = "Street Address:";
    } else {
        document.getElementById("student").style.display = "none";
        document.getElementById("library").style.display = "block";
        document.getElementById("libn").required = true;
        document.getElementById("info").required = true;
        document.getElementById("bach").required = false;
        document.getElementById("mas").required = false;
        document.getElementById("phd").required = false;
        document.getElementById("acid").required = false;
        document.getElementById("idst").required = false;
        document.getElementById("idexp").required = false;
        document.getElementById("university").required = false;
        document.getElementById("dep").required = false;
        document.getElementById("adstt").innerHTML = "Library's Address:";
    }
}


function check() {
    var form = document.forms.myForm;
    var email = form.email.value;
    var length = email.length;
    var str = '';
    if (document.querySelector("#university").value === "UOC") {
        for (var i = length - 6; i < length; i++) {
            str += email[i];
        }
        if (str.localeCompare("uoc.gr") !== 0) {
            alert("email validation failed");
            window.history.back();
            return false;
        }
    } else if (document.querySelector("#university").value === "HELMEPA") {
        for (var i = length - 10; i < length; i++) {
            str += email[i];
        }
        if (str.localeCompare("helmepa.gr") !== 0) {
            alert("email validation failed");
            window.history.back();
            return false;
        }
    } else if (document.querySelector("#university").value === "TUC") {
        for (var i = length - 6; i < length; i++) {
            str += email[i];
        }
        if (str.localeCompare("tuc.gr") !== 0) {
            alert("email validation failed");
            window.history.back();
            return false;
        }

    }

    var date1 = document.getElementById("idst").value;
    var d1 = new Date(date1);
    var date2 = document.getElementById("idexp").value;
    var d2 = new Date(date2);
    if (d1.getTime() > d2.getTime()) {
        alert("id starting or expiring date validation failed");
        window.history.back();
        return false;
    }

    var val = document.getElementsByName("student_type");
    var yeardiff = Math.trunc((d2.getTime() - d1.getTime()) / (1000 * 3600 * 24 * 365));
    if (val[0].checked) {
        if (yeardiff > 6) {
            alert("id starting or expiring date validation failed");
            window.history.back();
            return false;
        }
    } else if (val[1].checked) {
        if (yeardiff > 2) {
            alert("id starting or expiring date validation failed");
            window.history.back();
            return false;
        }
    } else {
        if (yeardiff > 5) {
            alert("id starting or expiring date validation failed");
            window.history.back();
            return false;
        }
    }
    var cb = document.querySelector('#yes');
    if (!cb.checked) {
        alert("In order to continue please accept the terms of use.");
        window.history.back();
        return false;
    }
    return true;
}


function exxit() {
    location.reload();
}