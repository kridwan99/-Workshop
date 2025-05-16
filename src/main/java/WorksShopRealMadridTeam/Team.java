package WorksShopRealMadridTeam;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String stadium;
    private List<Player> roster;

    public Team(String name, String stadium) {
        this.name = name;
        this.stadium = stadium;
        this.roster = PlayerFileManager.loadPlayers();
    }

    public void addPlayer(Player player) {
        roster.add(player);
        PlayerFileManager.savePlayers(roster);
    }

    public void removePlayer(int id) {
        roster.removeIf(player -> player.getId() == id);
        PlayerFileManager.savePlayers(roster);
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(roster);
    }

    public List<Player> searchByName(String name) {
        List<Player> result = new ArrayList<>();
        for (Player player : roster) {
            if (player.getName().equalsIgnoreCase(name)) {
                result.add(player);
            }
        }
        return result;
    }

    public List<Player> searchByAgeRange(int minAge, int maxAge) {
        List<Player> result = new ArrayList<>();
        for (Player player : roster) {
            if (player.getAge() >= minAge && player.getAge() <= maxAge) {
                result.add(player);
            }
        }
        return result;
    }

    // Calculate total monthly payroll cost
    public double getTotalPayroll() {
        return roster.stream().mapToDouble(Player::getMonthlySalary).sum();
    }

    public void displayPlayers() {
        if (roster.isEmpty()) {
            System.out.println("No players in the team.");
        } else {
            roster.forEach(System.out::println);
        }
    }

    public void displayPlayers(List<Player> players) {
        if (players.isEmpty()) {
            System.out.println("No players found.");
        } else {
            players.forEach(System.out::println);
        }
    }
}
