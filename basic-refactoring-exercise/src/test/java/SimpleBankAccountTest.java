import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final String NAME = "Mario";
    private static final String SURNAME = "Rossi";
    private static final int USER_ID = 1;
    private static final int INITIAL_BALANCE = 0;
    private static final int DEPOSIT_AMOUNT = 100;
    private static final int WITHDRAW_AMOUNT = 70;
    private static final int WRONG_USER_ID = 2;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder(NAME, SURNAME, USER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        int wrongAmount = 50;
        bankAccount.deposit(WRONG_USER_ID, wrongAmount);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        var depositId = accountHolder.id();
        var withdrawId = accountHolder.id();
        depositAndWithdraw(depositId, DEPOSIT_AMOUNT, withdrawId, WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        var depositId = accountHolder.id();
        depositAndWithdraw(depositId, DEPOSIT_AMOUNT, WRONG_USER_ID, WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    private void depositAndWithdraw(int depositId, int depositAmount, int withdrawId, int withdrawAmount) {
        bankAccount.deposit(depositId, depositAmount);
        bankAccount.withdraw(withdrawId, withdrawAmount);
    }
}
