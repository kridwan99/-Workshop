package WorksShopRealMadridTeam.contracts;

import WorksShopRealMadridTeam.Player;
import java.time.LocalDate;

/**
 * Contract for player transfers.
 */
public class TransferContract extends PlayerContract {
    private double transferFee;

    public TransferContract(LocalDate date, Player player, String destinationClub, double transferFee) {
        super(date, player, destinationClub);
        this.transferFee = transferFee;
    }

    public double getTransferFee() {
        return transferFee;
    }

    @Override
    public double getTotalFee() {
        return transferFee;
    }
}
