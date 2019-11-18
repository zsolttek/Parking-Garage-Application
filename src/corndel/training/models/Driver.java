package corndel.training.models;

import corndel.training.corndel.training.services.ParkingBillingService;

public class Driver {

    private float balance = 5.00F;
    private float debt = 00.00F;
    
    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }
    public float getDebt() {
        return debt;
    }

    public boolean canPayParkingCost(float cost) {
        return (balance >= cost);
    }

    public void payParkingCost(float cost) {
            balance -= cost;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public void payParkingCostWithPenalty(float cost) {
        ParkingBillingService.getBank().getLoan(this);
        balance -= cost;
    }
}
