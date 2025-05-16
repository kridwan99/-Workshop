package WorksShopRealMadridTeam;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerFileManager {
    private static final String FILE_PATH = "players.csv";

    public static List<Player> loadPlayers() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 11) {
                    players.add(new Player(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]),
                            LocalDate.parse(parts[6]),
                            Integer.parseInt(parts[7]),
                            Integer.parseInt(parts[8]),
                            Boolean.parseBoolean(parts[9]),
                            Double.parseDouble(parts[10])
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading players file: " + e.getMessage());
        }
        return players;
    }

    public static void savePlayers(List<Player> players) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Player player : players) {
                writer.println(player.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error writing players file: " + e.getMessage());
        }
    }
}
