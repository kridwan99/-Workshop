package WorksShopRealMadridTeam.contracts;

import WorksShopRealMadridTeam.Player;
import java.time.LocalDate;

/**
 * Contract for player loans.
 */
public class LoanContract extends PlayerContract {
    private int durationMonths;
    private double loanFee;
    private double optionalBuyFee;

    public LoanContract(LocalDate date, Player player, String destinationClub,
                        int durationMonths, double loanFee, double optionalBuyFee) {
        super(date, player, destinationClub);
        this.durationMonths = durationMonths;
        this.loanFee = loanFee;
        this.optionalBuyFee = optionalBuyFee;
    }

    public int getDurationMonths() { return durationMonths; }
    public double getLoanFee() { return loanFee; }
    public double getOptionalBuyFee() { return optionalBuyFee; }

    @Override
    public double getTotalFee() {
        return loanFee + optionalBuyFee;
    }
}
