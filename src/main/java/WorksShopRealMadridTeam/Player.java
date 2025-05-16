package WorksShopRealMadridTeam;

import java.time.LocalDate;

public class Player {
    private int id;
    private String name;
    private String position;
    private int age;
    private int goals;
    private int assists;
    private LocalDate contractExpiry;
    private int yellowCards;
    private int redCards;
    private boolean injured;
    private double monthlySalary;

    public Player(int id, String name, String position, int age, int goals, int assists,
                  LocalDate contractExpiry, int yellowCards, int redCards, boolean injured,
                  double monthlySalary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
        this.goals = goals;
        this.assists = assists;
        this.contractExpiry = contractExpiry;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.injured = injured;
        this.monthlySalary = monthlySalary;
    }

    // --- Getters ---
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public LocalDate getContractExpiry() { return contractExpiry; }
    public double getMonthlySalary() { return monthlySalary; }
    public void setYellowCards(int yellowCards) { this.yellowCards = yellowCards; }
    public void setRedCards(int redCards) { this.redCards = redCards; }
    public void setInjured(boolean injured) { this.injured = injured; }

    // --- CSV Support ---
    public String toCSV() {
        return String.format("%d,%s,%s,%d,%d,%d,%s,%d,%d,%b,%.2f",
                id, name, position, age, goals, assists,
                contractExpiry.toString(), yellowCards, redCards, injured, monthlySalary);
    }

    public static Player fromCSV(String csvLine) {
        String[] tokens = csvLine.split(",");
        return new Player(
                Integer.parseInt(tokens[0]),      // id
                tokens[1],                        // name
                tokens[2],                        // position
                Integer.parseInt(tokens[3]),      // age
                Integer.parseInt(tokens[4]),      // goals
                Integer.parseInt(tokens[5]),      // assists
                LocalDate.parse(tokens[6]),       // contractExpiry
                Integer.parseInt(tokens[7]),      // yellowCards
                Integer.parseInt(tokens[8]),      // redCards
                Boolean.parseBoolean(tokens[9]),  // injured
                Double.parseDouble(tokens[10])    // monthlySalary
        );
    }

    // --- Format salary as 300K, 2.5M, etc. ---
    public static String formatSalary(double salary) {
        if (salary >= 1_000_000_000) {
            return String.format("%.1fB", salary / 1_000_000_000);
        } else if (salary >= 1_000_000) {
            return String.format("%.1fM", salary / 1_000_000);
        } else if (salary >= 1_000) {
            return String.format("%.0fK", salary / 1_000);
        } else {
            return String.format("%.2f", salary);
        }
    }

    // --- Clean, aligned output for list display ---
    @Override
    public String toString() {
        return String.format(
                "ID: %-3d | Name: %-15s | Pos: %-10s | Age: %2d | Goals: %3d | Assists: %3d | Expiry: %-10s | Yellow: %2d | Red: %2d | Injured: %-3s | Salary: %6s",
                id, name, position, age, goals, assists,
                contractExpiry.toString(), yellowCards, redCards,
                injured ? "Yes" : "No", formatSalary(monthlySalary)
        );
    }
}
