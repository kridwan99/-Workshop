# -Workshop

## 🚀 Features

- List all players with detailed stats
- Search players by name or age range
- Add or remove players
- Transfer or loan players to other clubs
- Track yellow/red cards and injury status
- Pay all players monthly salaries
- Display total payroll
- Save/load player data from CSV files
- Record contract details to a contract log file

## 💡 Example Commands

- Add player: Enter player details like ID, name, position, age, goals, salary, etc.
- Transfer/Loan: Specify contract details like fee, duration, destination club.
- Pay Players: Simulates paying each player their monthly salary.
- Show Payroll: Calculates and displays the total salary expense.

## 📝 Data Persistence

- Player data is saved to and loaded from `players.csv`.
- Contract data is saved to `player_contracts.csv`.

## 🛠️ Technologies

- Java 17+
- File I/O
- Object-Oriented Programming

## 📌 How to Run

1. Compile and run `Main.java`.
2. Use the console menu to manage the team.

```bash
javac WorksShopRealMadridTeam/*.java WorksShopRealMadridTeam/contracts/*.java
java WorksShopRealMadridTeam.Main
