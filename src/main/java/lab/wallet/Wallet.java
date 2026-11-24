package lab.wallet;

import java.util.HashMap;
import java.util.Map;

/**
 * Your first task:
 * - Start with 500 credits.
 * - Accept a transaction ID and a positive transfer amount.
 * - Return FAILED when funds are insufficient.
 * - Otherwise deduct the amount, record SUCCESS, and return SUCCESS.
 *
 * Assumptions for this first attempt: one caller, positive amounts, no crashes.
 * Choose your own data structure for transaction records.
 */
public class Wallet {
    
    private int balance = 500;
    
    public record TransferData(String status, int amount){}
    private Map<String,TransferData> transactionRecord = new HashMap<>();

    
    public String transfer(String transactionId, int amount){

        if (transactionRecord.containsKey(transactionId)) {
            if(transactionRecord.get(transactionId).amount == amount) {
                return transactionRecord.get(transactionId).status;
            } else {
                return "CONFLICT";
            }
        }

        if (amount < 1) {
            transactionRecord.put(transactionId, new TransferData("FAILED", amount));
            return "FAILED";
        }
        if (amount > balance){
            transactionRecord.put(transactionId, new TransferData("FAILED", amount));
            return "FAILED";
        }

        balance -= amount;
        
        transactionRecord.put(transactionId, new TransferData("SUCCESS", amount));
        return "SUCCESS";
    }

    public int getBalance() {
        return balance;
    }

    public String getTransferStatus(String transactionId){
        TransferData transaction = transactionRecord.get(transactionId);
        
        if (transaction == null) return "NOT_FOUND";

        return transaction.status;
    }
    
}
