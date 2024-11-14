package hu.masterfield.bankproject.datatypes;

public class Confirmation {
    private int newBalance = -1;
    private boolean success = false;
    private String message = null;

    public Confirmation(int newBalance, boolean success, String message) {
        this.newBalance = newBalance;
        this.success = success;
        this.message = message;
    }

    public Confirmation() {
    }


    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Confirmation [newBalance = " + newBalance + ", success=" + success + ", message=" + message + "]";
    }

    
}
