// This say that this functionloads first when page loads
window.addEventListener("load", dofirst);

// This is the definition of function which is supposed to load first
function dofirst() {
				//BEGIN LIBRARY CODE

				// Declares all the variables
				var c = document.getElementById("canvas");
				var speed = 1;
				var difficulty = 1;
				var x = 25;
				var y = 250;
				var r = 15;
				var dx = 1.5;
				var dy = -4;
				var ctx;
				var WIDTH;
				var HEIGHT;
				var paddlex;
				var bricks;
				var score = 0;
				var NROWS = 6;
				var NCOLS = 6;
				var BRICKWIDTH;
				var BRICKHEIGHT = 15;
				var PADDING = 10;
				var paddleh = 30;
				var paddlew = 700;
				var rightDown = false;
				var leftDown = false;
				var canvasMinX = 0;
				var canvasMaxX = 0;
				var intervalId = 0;
				var rowcolors = ["#306EFF"];
				var paddlecolor = "black";
				var ballcolor = "black";
				var backcolor = "black";
				var background = new Image();
				var breakBeep = new Audio("audio/break.mp3");	//gets audio input
				var bgm = new Audio("audio/bgm.mp3");

				background.src = "images/white.png"; //gets Backgorund Image


				// Resizing the window to fit the whole screen
				window.addEventListener('resize', resizeCanvas, false);


				// Draws the ball
				function circle(x,y,r) {
							  ctx.beginPath();
							  ctx.arc(x, y, r, 0, Math.PI*2, true);
							  ctx.closePath();
							  ctx.fill();
				}

				// Draws the moving brick
				function rect(x,y,w,h) {
							  ctx.beginPath();
							  ctx.rect(x,y,w,h);
							  ctx.closePath();
							  ctx.fill();
				}

				// Clears the background to give a moving animation look for the objects
				function clear() {
							ctx.clearRect(0, 0, WIDTH, HEIGHT);
				  			rect(0,0,WIDTH,HEIGHT);
				  			ctx.drawImage(background,0,0,1600,700);
				}

				//set rightDown or leftDown if the right or left keys are down
				function onKeyDown(evt) {
							if (evt.keyCode == 39) rightDown = true;
							else if (evt.keyCode == 37) leftDown = true;
				}

				//unset them when the right or left key is released
				function onKeyUp(evt) {
				  if (evt.keyCode == 39) rightDown = false;
				  else if (evt.keyCode == 37) leftDown = false;
				}

				//Detects the down and up key press
				document.addEventListener("keydown", onKeyDown);
				document.addEventListener("keyup", onKeyUp);

				//Moves the brick according to the mouse move direction
				function onMouseMove(evt) {
				  if (evt.pageX > canvasMinX-10 && evt.pageX < canvasMaxX) {
				    paddlex = evt.pageX - canvasMinX;
				  }
				}

				//Detects the mouse move
				document.getElementById("canvas").addEventListener("mousemove", onMouseMove);

				//Defines the bricks settlement in an array in a table format
				function initbricks() {
							  bricks = new Array(NROWS);
							  for (i=0; i < NROWS; i++) {
							    bricks[i] = new Array(NCOLS);
							    for (j=0; j < NCOLS; j++) {
							      bricks[i][j] = 1;
							    }
							  }
				}

				//this draws the bricks and selects the different colors to bricks
				function drawbricks() {
							  for (i=0; i < NROWS; i++) {
							    ctx.fillStyle = rowcolors[i];
							    for (j=0; j < NCOLS; j++) {
							      if (bricks[i][j] == 1) {
							        rect((j * (BRICKWIDTH + PADDING)) - PADDING,
							             (i * (BRICKHEIGHT + PADDING)) + PADDING,
							             BRICKWIDTH, BRICKHEIGHT);
							        //ctx.strokeRect(20);
							      }
							    }
							  }
							}

				// This pauses the game when pause button is clicked
				function pauseGame() {
					clearInterval(intervalId);
                    bgm.pause();
				}

				// This stops the game when stop button is pressed and asks the user to exit the game or stay in the game
				function stopGame() {
                        bgm.pause();
					clearInterval(intervalId);
    					if (confirm("Are you sure? Do you want to quit the Game?") == true) {
					       // alert("Thank you for playing our game!!!");
    						window.close();
					    } else {
					        alert("Enjoy The Game!!!");
					        startGame();
					    }
				}

				//This displays score in background
				function drawscore() {
					ctx.font = "16px Arial";
					ctx.fillStyle = "white";
					document.getElementById("scorePoint").innerHTML = score * 100;
				}

				// This restarts the game when restart button is clicked
				function restartGame() {
					drawbricks();
					initbricks();
					draw();
					var x = 25;
					var y = 250;
					var r = 10;
					var dx = 1.5;
					var dy = -4;
					ctx.fillStyle = backcolor;
					clear();
					ctx.fillStyle = ballcolor;
					circle(x, y, r);
					ctx.fillStyle = paddlecolor;
					rect(paddlex, HEIGHT-paddleh, paddlew, paddleh);
					//clearInterval(intervalId);
				}
				//This gets difficulty and speed input while the play now button pressed
				function getInput() {
					    speed = prompt("Please enter the speed (1-100)");
					    difficulty = prompt("Please enter the difficulty (1-10)");
					    document.getElementById('playNowBtn').style.display = 'block';
        				this.style.display = 'none';
                        startGame();
				}


				// This has the initial fucntions when the game is started
				function startGame() {
								var x = 25;
								var y = 250;
								var r = 10;
								var dx = 1.5;
								var dy = -4;
							    bgm.play(); 				//plays Background Music
							  ctx = c.getContext("2d");
							  WIDTH = c.width;
							  HEIGHT = c.height;
							  paddlex = WIDTH / 2;
							  BRICKWIDTH = (WIDTH/NCOLS) - 10;
							  canvasMinX = c.offsetLeft;
							  canvasMaxX = canvasMinX + WIDTH;
							  intervalId = setInterval(draw, 100/speed);

				}
				//END LIBRARY CODE


				// This has the complete draw function which draws the ball, paddle, and bricks
				function draw() {
							ctx.fillStyle = backcolor;
							clear();
							ctx.fillStyle = ballcolor;
							circle(x, y, r);
                            var paddlew = 700 - (difficulty * 50);
							//move the paddle if left or right is currently pressed
							if (rightDown) paddlex += 15;
							else if (leftDown) paddlex -= 15;
							ctx.fillStyle = paddlecolor;
							rect(paddlex, HEIGHT-paddleh, paddlew, paddleh);

							drawbricks();
							drawscore();

							//checks when ball hits a brick?
							  rowheight = BRICKHEIGHT + PADDING;
							  colwidth = BRICKWIDTH + PADDING;
							  row = Math.floor(y/rowheight);
							  col = Math.floor(x/colwidth);
							  //if so, reverse the ball and mark the brick as broken
							  if (y < NROWS * rowheight && row >= 0 && col >= 0 && bricks[row][col] == 1) {
							    dy = -dy;
							    score++;											//Adds score each time brick breaks
							    breakBeep.play();									//Plays break music while brick breaks.
							    if (score == NROWS * NCOLS){
							    	bgm.pause();
                                    ctx.font = "70px Georgia";						//Displays Canvas Text with the points and game over
						      		ctx.fillStyle = "black";
							  		ctx.fillText("You WON....!!!!!",WIDTH/3.25,HEIGHT/2.20);
							  		ctx.fillStyle = "black";
							  		ctx.font = "40px Georgia";
							  		ctx.fillText("You got :" + score * 100 + " points",WIDTH/2.78,HEIGHT/1.80);
							  		ctx.font = "25px Georgia";
							  		ctx.strokeText("(Refresh page to play again.)",WIDTH/2.6,HEIGHT/1.62);
						      		score = 0;
							    	clearInterval(intervalId);
							    }
							    bricks[row][col] = 0;
							  }

							// makes sure the ball jumps back when it hits the paddle
							if (x + dx > WIDTH-r || x + dx <r)
    							dx = (0.99 +(difficulty/100)) * -dx;	//speeds up the ball in every hit

  							if (y + dy < r)
    							dy = (0.99 +(difficulty/100)) * -dy;

    						else if (y + dy > HEIGHT-r-paddleh) {
	    						if (x > paddlex && x < paddlex + paddlew){
	    							//move the ball differently based on where it hit the paddle
	      							dx = 8 * ((x-(paddlex+paddlew/2))/paddlew);
	      							dy = (0.99 +(difficulty/100)) * -dy;
      							}
						    else{
						      //game over, so stop the animation
						      clearInterval(intervalId);
						      bgm.pause();
                                ctx.font = "70px Georgia";				//Displays Canvas Results with points and Game Over.
						      ctx.fillStyle = "black";
							  ctx.fillText("Game Over....!!!",WIDTH/3.25,HEIGHT/2.20);
							  ctx.fillStyle = "black";
							  ctx.font = "40px Georgia";
							  ctx.fillText("You got :" + score * 100 + " points",WIDTH/2.78,HEIGHT/1.80);
							  ctx.font = "25px Georgia";
							  ctx.strokeText("(Refresh page to play again.)",WIDTH/2.6,HEIGHT/1.62);
						      score = 0;
							  }
	 						}

  							x += dx;
  							y += dy;
				}

				// Resizes the window
				function resizeCanvas() {
                canvas.width = window.innerWidth;
                canvas.height = 0.8 * window.innerHeight;

            	// Detects the start,stop,restart,pause button cliks
        document.getElementById("playNowBtn").addEventListener("click", getInput);
				document.getElementById("startBtn").addEventListener("click", startGame);
				document.getElementById("pauseBtn").addEventListener("click", pauseGame);
				document.getElementById("restartBtn").addEventListener("click", restartGame);
				document.getElementById("stopBtn").addEventListener("click", stopGame);

				// Calls the initbricks function
				initbricks();
				}
				resizeCanvas();
}
