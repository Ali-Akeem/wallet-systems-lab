package lab.wallet;

public class WalletTest {

    void  testTransfer(){
        Wallet wallet = new Wallet();
        String firstResult = wallet.transfer("TX-001", 100);
        String secondResult = wallet.transfer("TX-001", 100);
        assert "SUCCESS".equals(firstResult);
        assert "SUCCESS".equals(secondResult);
        assert wallet.getBalance() == 400;
    }

    void testConflictingAmount(){
        Wallet wallet = new Wallet();
        String first = wallet.transfer("TX-002", 100);
        String second = wallet.transfer("TX-002", 450);

        assert "SUCCESS".equals(first);
        assert "CONFLICT".equals(second);
        assert wallet.getBalance() == 400; 
    }

    void testRepeatedFailure(){
        Wallet wallet = new Wallet();
        String first = wallet.transfer("TX-003", 600);
        String second = wallet.transfer("TX-003", 600);

        assert "FAILED".equals(first);
        assert "FAILED".equals(second);
        assert wallet.getBalance() == 500; 
    }

    void testConflictAfterFailure(){
        Wallet wallet = new Wallet();
        String first = wallet.transfer("TX-004", 600);
        String second = wallet.transfer("TX-004", 100);

        assert "FAILED".equals(first);
        assert "CONFLICT".equals(second);
        assert wallet.getBalance() == 500; 
    }

    void testTransferStatus(){
        Wallet wallet = new Wallet();
        wallet.transfer("TX-005", 100);
        
        assert "SUCCESS".equals(wallet.getTransferStatus("TX-005"));
        assert "NOT_FOUND".equals(wallet.getTransferStatus("TX-999"));
        assert wallet.getBalance() == 400; 
    }

    void testInvalidAmount(){
        Wallet wallet = new Wallet();
        String first = wallet.transfer("TX-006", -100);
        
        assert "FAILED".equals(first);
        assert wallet.getBalance() == 500; 
    }

    void testUnsafeRetry(){
        Wallet wallet = new Wallet();
        String first = wallet.transfer("TX-007", 100);
        String second = wallet.transfer("TX-008", 100);

        assert "SUCCESS".equals(first);
        assert "SUCCESS".equals(second);

        assert wallet.getBalance() == 300; 
    }

    void testSafeRetry(){
        Wallet wallet = new Wallet();
        String first = wallet.transfer("TX-009", 100);
        String second = wallet.transfer("TX-009", 100);

        assert "SUCCESS".equals(first);
        assert "SUCCESS".equals(second);
        assert wallet.getBalance() == 400; 
    }
    public static void main(String[] args) {
        WalletTest test = new WalletTest();
        test.testTransfer();
        test.testConflictingAmount();
        test.testRepeatedFailure();
        test.testConflictAfterFailure();
        test.testTransferStatus();
        test.testInvalidAmount();
        test.testUnsafeRetry();
        test.testSafeRetry();
        System.out.println("All Test passed");
    }
}
