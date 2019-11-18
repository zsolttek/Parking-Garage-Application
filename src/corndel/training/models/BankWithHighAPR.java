package corndel.training.models;

public class BankWithHighAPR implements Banking {

    private static float APR = 35.00F;
    private static float MINIMUM_LOAN = 100.00F;

    @Override
    public void getLoan(Driver driver) {
        driver.setBalance(driver.getBalance() + MINIMUM_LOAN);

        float interest = (MINIMUM_LOAN / 100) * APR;
        driver.setDebt(MINIMUM_LOAN + interest);
    }
}
