# [CREATIVE GAME NAME]  
### APCSA Final Project 2025  
**By Josh Felder, Billy Center, Tyler Engola**

## Description  
A simple terminal-based game written in Java for our 2025 AP Computer Science A final project. The game features a player-controlled character who moves left and right to collect falling items. It runs in the console and uses `Scanner` for user input.

## How to Play  
- When the game asks for a username, enter the name you want to show up on the leaderboard  
- Use the **[A]** key to move left  
- Use the **[D]** key to move right  
- Press **[ENTER]** after each input (input is handled via `Scanner`)  
- Items fall from the sky — your goal is to collect or avoid them before they hit the ground
- If you have wrenches in your inventory, the shop will open randomly
- In the shop, you can buy health packs to heal and repair kits to repair integrity
- If health reaches 0%, you lose
- If integrity reaches 100%, you win

## Item List  
Items that can fall from the sky (customize as needed):  
- 🔧 Wrench – spend these in the shop to heal or repair - if it hits the ground, you take damage  
- 🕒 Clock – makes items fall slower  
- 🧲 Magnet – increases pickup range  
- 🕒 Clock – adds extra time  
- 💣 Bomb – deals massive damage  
- 🕸 Web – disables your movement  
- 👾 Glitch - obscures your vision  
- 💻 Laptop - spawns a wall of bombs  
- 🎰 Slot Machine - has a random effect   

## ▶️ How to Run  
### Native Java  
1. Download the latest release  
2. Run java -jar `jarfile.jar` in a console
**Note: some consoles may not support the clear escape code (`\033[H\033[2J`) or emojis used for the display.**  
### CodeHS (recommended)  
Since the game was made for CodeHS, it is the recommended environment. However, the leaderboard may not work as intended.  
1. Download the source code  
2. Upload each `.java` file and `leaderboard.txt` to the CodeHS repository
