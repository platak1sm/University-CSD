function initBoard() {
	var table = document.getElementById('mainTable');
	var tr = document.createElement('tr');

	for (var i = 8; i >= 1; i--) {
		var tr = document.createElement('tr');
		for (var j = 9; j >= 0; j--) {
			var td1 = document.createElement('td');
			var num = i * 10 - j;
			td1.innerHTML = "<div id='position" + num + "'><img  src='images/" + num + ".png'  height=70 width=70></div>";

			tr.appendChild(td1);

		}
		table.appendChild(tr);
	}
}



const player1 = {
	name: '',
	position: 0,
	pythoneffect: false,
	id: 1,
	turn: false,
	color: 'Red'
}

const player2 = {
	name: '',
	position: 0,
	pythoneffect: false,
	id: 2,
	turn: false,
	color: 'White'
}

function start() {
	document.getElementById("wel").hidden = true;
	document.getElementById("forms").hidden = true;
	document.getElementById("mainTable").hidden = false;
	document.getElementById("ib").hidden = false;
	document.getElementById("firstb").hidden = false;
}