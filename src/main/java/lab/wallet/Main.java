package lab.wallet;

public class Main {

    static void demonstrateLostAcknowledgment(){
        Wallet wallet = new Wallet();

        String coordinatorStatus = "UNKNOWN";
        wallet.transfer("TX-001", 100);
        System.out.printf("Coordinator status: %s\n", coordinatorStatus);
        coordinatorStatus = wallet.getTransferStatus("TX-001");
        System.out.printf("Recovered status: %s\n", coordinatorStatus);
        System.out.printf("Final balance: %d\n", wallet.getBalance());
    }

    static void demonstrateUnsafeRetry(){
        Wallet wallet = new Wallet();

        System.out.println(wallet.transfer("TX-001", 100));
        System.out.println(wallet.transfer("TX-002", 100));
        System.out.printf("Final balance: %d\n", wallet.getBalance());

    }

    static void demonstrateSafeRetry(){
        Wallet wallet = new Wallet();

        System.out.println(wallet.transfer("TX-001", 100));
        System.out.println(wallet.transfer("TX-001", 100));
        System.out.printf("Final balance: %d%n", wallet.getBalance());   
    }

    static void demonstrateDelayedRequest(){
        Wallet wallet = new Wallet();

        System.out.println(wallet.getTransferStatus("TX-001"));
        wallet.transfer("TX-001", 100);
        System.out.println(wallet.getTransferStatus("TX-001"));
        System.out.printf("Final balance: %d%n", wallet.getBalance());
    }
   public static void main(String[] args) {
    System.out.println("\n=== Lost Acknowledgment ===");
    demonstrateLostAcknowledgment();

    System.out.println("\n=== Unsafe Retry ===");
    demonstrateUnsafeRetry();

    System.out.println("\n=== Safe Retry ===");
    demonstrateSafeRetry();

    System.out.println("\n=== Delayed Request ===");
    demonstrateDelayedRequest();
}
}
