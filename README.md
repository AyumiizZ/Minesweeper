# Minesweeper game
A medium difficult minesweeper game (16x16 grid with 40 mines)

(01204215 Object-Oriented Programming Laboratory class)

## Table of contents
* [How to play](#how-to-play)
   * [Grid Meaning](#grid-meaning)
   * [Tips](#tips)
* [Git tutorial](#tutorial-for-git-beginner)
    * [Windows user](#windows-user)
    * [Linux or macOS user](#linux-or-macos-user)

## How to play

__1. Run and get this window.__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/GamePic/1.png)

__2. Click any square on the grid.__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/GamePic/3.png)

__3. Review the numbers.__

Any number on the board refers to the number of mines currently touching that number's square.

__4.Right-click any squares that you think contain mines.__

This will put a flag on the square.
__5.Double-right-click any squares that are uncertain.__

Doing so will place a question mark over the square
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/GamePic/2.png)

__6.Click any squares that don't contain mines.__

Don't click mine grid...
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/GamePic/4.png)

__7.Clear the board.__

To win a round of Minesweeper, you must click on the board every square that doesn't have a mine under it.
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/GamePic/6.png)

[Back to top](#minesweeper-game)

### Grid meaning
__1. Blank grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/0.png)

This grid means not have mine around this grid.

__2. Number grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/1.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/2.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/3.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/4.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/5.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/6.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/7.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/8.png)

This grid means have 1-8 mine(s) around this grid.

__3. Mines grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/clicked_bomb.png)
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/unclicked_bomb.png)

The red one is mine that you clicked and black one with blue grid is mine that you unclicked it. (It will reveal after you lose.)

__4. Wrong flag grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/wrong_flag.png)

This will append when you lose with wrong flag pinned.

__5. Question flad grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/ques.png)

This is marked grid. When double-left-click some grid the question will count as flag.

__6. Not reveal grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/not_reveal.png)

This grid is not reveal yet. It waiting for you click. (It can be blank grid, number grid or Mine grid)

__7. Flag grid__
![alt text](https://github.com/AyumiizZ/Minesweeper/blob/master/core/assets/flag.png)

This grid is the grid that you mark as mine.

[Back to top](#minesweeper-game)

### Tips

__1.Double-left-click__

It help you to reveal around grid.

__2.Question until sure__

It make you more safe to win this game.

[Back to top](#minesweeper-game)

## Tutorial for Git beginner

### Windows user

__First way (beginner's way: Not recommend)__
1. Click "Clone or download" (Green button).
2. Click "Download ZIP".
3. Unzip and enjoy my game :)

__Second way__
1. Install [git bash](https://git-scm.com/downloads).
2. Right click on your destination folder.
3. Click git bash here.
4. paste this command `git clone https://github.com/AyumiizZ/Minesweeper.git` in git bash.
5. Enjoy my game :P

[Back to top](#minesweeper-game)

### Linux or macOS user
1. Open your terminal.
2. `mkdir <your directory name>`.
3. `cd <your directory name>`.
4. `git clone https://github.com/AyumiizZ/Minesweeper.git`.
5. Enjoy my game :3

[Back to top](#minesweeper-game)
