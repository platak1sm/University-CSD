function setPositions() {
	var positions = [];
	var snakePositions = [13, 20, 28, 44, 58, 59, 65, 72, 78]
	var snakeNewPositions = [11, 10, 7, 34, 48, 39, 25, 52, 69]

	var ladderPositions = [5, 16, 21, 37, 42, 54, 60, 67, 73]
	var ladderNewPositions = [33, 36, 61, 56, 53, 64, 80, 77, 76]


	for (var i = 1; i <= 80; i++) {
		positions[i] = new Object();
		positions[i].from = i;


		if (snakePositions.indexOf(i) != -1) {
			positions[i].to = snakeNewPositions[snakePositions.indexOf(i)];
			positions[i].type = "Snake";
		}
		else if (ladderPositions.indexOf(i) != -1) {
			positions[i].to = ladderNewPositions[ladderPositions.indexOf(i)];
			positions[i].type = "Ladders";
		}
		else if (i === 29 || i === 46) {
			positions[i].to = i;
			positions[i].type = "pythonEffect";
		}
		else {
			positions[i].to = i;
			positions[i].type = "Normal";

		}
	}
	return positions;
}

function randomforturn() {
	var t = Math.floor(Math.random() * 2) + 1;
	var form=document.myForm;
	player1.name=form.p1.value;
	player2.name=form.p2.value;
	if (t === player1.id) {
		document.getElementById("turn").innerHTML = "Turn: "+ player1.name;
		document.getElementById("pytheff").innerHTML += "No";
		player1.turn = true;
	} else {
		document.getElementById("turn").innerHTML = "Turn: "+ player2.name;
		document.getElementById("pytheff").innerHTML += "No";
		player2.turn = true;
	}
	document.getElementById("firstb").hidden = true;
	alert("Press the dice to play!");
	document.getElementById("diceb").disabled = false;
}

function play() {
	var i = rollthedice();
	if (player1.turn) {
		var from = player1.position;
		var to = from + i;
		if (to >= 80)
			changePosition(from, 80);
		else
			changePosition(from, to);

		if (cells[player1.position].type === "Ladders") {
			alert("You climb the ladders! From " + cells[player1.position].from + " To " + cells[player1.position].to);
			changePosition(cells[player1.position].from, cells[player1.position].to);
		} else if (cells[player1.position].type === "Snake") {
			if (!player1.pythoneffect) {
				alert("Unfortunately, you go down with the snake.. From " + cells[player1.position].from + " To " + cells[player1.position].to);
				changePosition(cells[player1.position].from, cells[player1.position].to);
			}

		} else if (cells[player1.position].type === "pythonEffect") {
			player1.pythoneffect = true;
		}
		updateinfobox();
		player1.turn = false;
		player2.turn = true;
	} else {
		var from = player2.position;
		var to = from + i;
		if (to >= 80)
			changePosition(from, 80);
		else
			changePosition(from, to);

		if (cells[player2.position].type === "Ladders") {
			alert("You climb the ladders! From " + cells[player2.position].from + " To " + cells[player2.position].to);
			changePosition(cells[player2.position].from, cells[player2.position].to);
		} else if (cells[player2.position].type === "Snake") {
			if (!player2.pythoneffect) {
				alert("Unfortunately, you go down with the snake.. From " + cells[player2.position].from + " To " + cells[player2.position].to);
				changePosition(cells[player2.position].from, cells[player2.position].to);
			}

		} else if (cells[player2.position].type === "pythonEffect") {
			player2.pythoneffect = true;
			alert("Congrats! Now you have the Python Effect!");
		}
		updateinfobox();
		player2.turn = false;
		player1.turn = true;
	}
	if (player1.position === 80) {
		alert(player1.name +" you won! Congrats!");
		document.getElementById("diceb").disabled = true;
		document.getElementById("win").hidden = false;
		document.getElementById("mainTable").hidden = true;
		document.getElementById("ib").hidden = true;
		document.getElementById("win").innerHTML = player1.name +" won last match. If you want to play again refresh the page!";
	} else if (player2.position === 80) {
		alert(player2.name +" you won! Congrats!");
		document.getElementById("diceb").disabled = true;
		document.getElementById("win").hidden = false;
		document.getElementById("mainTable").hidden = true;
		document.getElementById("ib").hidden = true;
		document.getElementById("pytheff").hidden = true;
		document.getElementById("win").innerHTML =  player2.name +" won last match. If you want to play again refresh the page!";
	}

}

function updateinfobox() {
	if (player1.turn) {
		document.getElementById("turn").innerHTML = "Turn: "+ player1.name;
		if (player1.pythoneffect) {
			document.getElementById("pytheff").innerHTML = "Python Effect: Yes";
		} else {
			document.getElementById("pytheff").innerHTML = "Python Effect: No";
		}
	} else {
		document.getElementById("turn").innerHTML = "Turn: "+ player2.name;
		if (player2.pythoneffect) {
			document.getElementById("pytheff").innerHTML = "Python Effect: Yes";
		} else {
			document.getElementById("pytheff").innerHTML = "Python Effect: No";
		}
	}
}


function rollthedice() {
	var num = Math.floor(Math.random() * 6) + 1;
	var str = "";
	if (num === 1) {
		str = "one";
	} else if (num === 2) {
		str = "two";
	} else if (num === 3) {
		str = "three";
	} else if (num === 4) {
		str = "four";
	} else if (num === 5) {
		str = "five";
	} else if (num === 6) {
		str = "six";
	}
	document.getElementById("diceim").src = "ImagesDice/" + str + ".png";
	return num;
}



function changePosition(from, to) {
	if (player1.turn) {
		if (from !== 0) {
			if (player1.position === player2.position)
				document.getElementById("position" + from).innerHTML = "<img  src='imagesWhite/" + from + ".png'  height=70 width=70></div>"
			else
				document.getElementById("position" + from).innerHTML = "<img  src='images/" + from + ".png'  height=70 width=70></div>"

		}
		if (player2.position === to) {
			document.getElementById("position" + to).innerHTML = "<img  src='imagesBoth/" + to + ".png'  height=70 width=70></div>";
		} else {
			document.getElementById("position" + to).innerHTML = "<img  src='imagesRed/" + to + ".png'  height=70 width=70></div>";
		}
		player1.position = to;
	} else {
		if (from !== 0) {
			if (player1.position === player2.position)
				document.getElementById("position" + from).innerHTML = "<img  src='imagesRed/" + from + ".png'  height=70 width=70></div>"
			else
				document.getElementById("position" + from).innerHTML = "<img  src='images/" + from + ".png'  height=70 width=70></div>"

		}
		if (player2.position === to) {
			document.getElementById("position" + to).innerHTML = "<img  src='imagesBoth/" + to + ".png'  height=70 width=70></div>";
		} else {
			document.getElementById("position" + to).innerHTML = "<img  src='imagesWhite/" + to + ".png'  height=70 width=70></div>";
		}
		player2.position = to;
	}
}


var cells = setPositions();

