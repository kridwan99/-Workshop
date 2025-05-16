package WorksShopRealMadridTeam.contracts;

import WorksShopRealMadridTeam.Player;
import java.time.LocalDate;

/**
 * Abstract base class for player contracts.
 */
public abstract class PlayerContract {
    private LocalDate date;
    private Player player;
    private String destinationClub;

    public PlayerContract(LocalDate date, Player player, String destinationClub) {
        this.date = date;
        this.player = player;
        this.destinationClub = destinationClub;
    }

    public LocalDate getDate() { return date; }
    public Player getPlayer() { return player; }
    public String getDestinationClub() { return destinationClub; }

    // Each contract type must implement this to return total fee.
    public abstract double getTotalFee();
}
