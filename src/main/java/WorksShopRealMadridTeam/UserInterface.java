package WorksShopRealMadridTeam;

import WorksShopRealMadridTeam.contracts.*;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    private Team team;
    private Scanner scanner;

    public UserInterface() {
        this.team = new Team("Real Madrid", "Santiago Bernabéu Stadium");
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("        Real Madrid Team Management System     ");
            System.out.println("==============================================");
            System.out.println("1 - List All Players");
            System.out.println("2 - Search Players by Name");
            System.out.println("3 - Search Players by Age Range");
            System.out.println("4 - Add a Player");
            System.out.println("5 - Remove a Player");
            System.out.println("6 - Transfer or Loan a Player");
            System.out.println("7 - Pay Players");
            System.out.println("8 - Update Player Cards/Injury");
            System.out.println("9 - Show Total Payroll");
            System.out.println("99 - Quit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println();

            switch (choice) {
                case 1 -> team.displayPlayers();
                case 2 -> processSearchByName();
                case 3 -> processSearchByAgeRange();
                case 4 -> processAddPlayer();
                case 5 -> processRemovePlayer();
                case 6 -> processContractTransaction();
                case 7 -> processPayPlayers();
                case 8 -> processUpdateCardsAndInjury();
                case 9 -> System.out.printf("Total monthly payroll: %.2f\n", team.getTotalPayroll());
                case 99 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private void processSearchByName() {
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();
        team.displayPlayers(team.searchByName(name));
    }

    private void processSearchByAgeRange() {
        System.out.print("Enter min age: ");
        int minAge = scanner.nextInt();
        System.out.print("Enter max age: ");
        int maxAge = scanner.nextInt();
        scanner.nextLine();
        team.displayPlayers(team.searchByAgeRange(minAge, maxAge));
    }

    private void processAddPlayer() {
        System.out.print("Enter player ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter player name: ");
        String name = scanner.nextLine();

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Enter goals scored: ");
        int goals = scanner.nextInt();

        System.out.print("Enter assists: ");
        int assists = scanner.nextInt();

        System.out.print("Enter contract expiry date (yyyy-mm-dd): ");
        scanner.nextLine();
        LocalDate contractExpiry = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter yellow cards: ");
        int yellowCards = scanner.nextInt();

        System.out.print("Enter red cards: ");
        int redCards = scanner.nextInt();

        System.out.print("Is the player injured? (true/false): ");
        boolean injured = scanner.nextBoolean();

        System.out.print("Enter monthly salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Player player = new Player(id, name, position, age, goals, assists, contractExpiry,
                yellowCards, redCards, injured, salary);
        team.addPlayer(player);

        System.out.println("Player added successfully!");
    }

    private void processRemovePlayer() {
        System.out.print("Enter player ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        team.removePlayer(id);
        System.out.println("Player removed successfully!");
    }

    private void processContractTransaction() {
        System.out.print("Enter player ID: ");
        int playerId = scanner.nextInt();
        scanner.nextLine();

        Player player = null;
        for (Player p : team.getAllPlayers()) {
            if (p.getId() == playerId) {
                player = p;
                break;
            }
        }

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.print("Enter destination club: ");
        String destinationClub = scanner.nextLine();

        System.out.print("Is this a Transfer or Loan? (T/L): ");
        String type = scanner.nextLine().trim().toUpperCase();

        PlayerContract contract = null;

        if (type.equals("T")) {
            System.out.print("Enter transfer fee: ");
            double fee = scanner.nextDouble();
            scanner.nextLine();
            contract = new TransferContract(LocalDate.now(), player, destinationClub, fee);
        } else if (type.equals("L")) {
            System.out.print("Enter loan duration (months): ");
            int months = scanner.nextInt();
            System.out.print("Enter loan fee: ");
            double loanFee = scanner.nextDouble();
            System.out.print("Enter optional buy fee: ");
            double buyFee = scanner.nextDouble();
            scanner.nextLine();
            contract = new LoanContract(LocalDate.now(), player, destinationClub, months, loanFee, buyFee);
        } else {
            System.out.println("Invalid type.");
            return;
        }

        new ContractFileManager().saveContract(contract);
        team.removePlayer(playerId);
        System.out.println("Contract saved and player removed from roster.");
    }

    private void processPayPlayers() {
        System.out.println("Paying all players their monthly salary...");
        double totalPaid = 0;
        for (Player player : team.getAllPlayers()) {
            System.out.printf("Paid %.2f to %s\n", player.getMonthlySalary(), player.getName());
            totalPaid += player.getMonthlySalary();
        }
        System.out.printf("Total payroll this month: %.2f\n", totalPaid);
    }

    private void processUpdateCardsAndInjury() {
        System.out.print("Enter player ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Player player = null;
        for (Player p : team.getAllPlayers()) {
            if (p.getId() == id) {
                player = p;
                break;
            }
        }

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.print("Enter new yellow cards: ");
        int yellowCards = scanner.nextInt();

        System.out.print("Enter new red cards: ");
        int redCards = scanner.nextInt();

        System.out.print("Is player injured? (true/false): ");
        boolean injured = scanner.nextBoolean();
        scanner.nextLine();

        // Update the player's disciplinary record and injury status
        player.setYellowCards(yellowCards);
        player.setRedCards(redCards);
        player.setInjured(injured);

        // Save updated players list
        PlayerFileManager.savePlayers(team.getAllPlayers());

        System.out.println("Player record updated successfully.");
    }
}
