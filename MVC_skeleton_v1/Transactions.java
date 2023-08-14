import java.util.Date;

public class Transactions {

    private int TransactionsID;
    private String SpendOrCollect;
    private int Amount;
    private Integer ResidentID;
    private int ManagerID;
    private int PreviousBalance;
    private int NewBalance;
    private String TransactionsDate;

    public Transactions(int transactionsID, String spendOrCollect, int amount, int residentID,
                        int managerID, int previousBalance, int newBalance, String transactionsDate) {
        TransactionsID = transactionsID;
        SpendOrCollect = spendOrCollect;
        Amount = amount;
        ResidentID = residentID;
        ManagerID = managerID;
        PreviousBalance = previousBalance;
        NewBalance = newBalance;
        TransactionsDate = transactionsDate;
    }
//    constructor for collect

    public Transactions(int transactionsID, String spendOrCollect, int amount, int residentID, int managerID) {
        TransactionsID = transactionsID;
        SpendOrCollect = spendOrCollect;
        Amount = amount;
        ResidentID = residentID;
        ManagerID = managerID;

        TransactionsDate = new Date().toString();
    }


//    constructor for spend
    public Transactions(int transactionsID, String spendOrCollect, int amount, int managerID) {
        TransactionsID = transactionsID;
        SpendOrCollect = spendOrCollect;
        Amount = amount;
        ResidentID = null;
        ManagerID = managerID;
        TransactionsDate = new Date().toString();
    }


    public int getTransactionsID() {
        return TransactionsID;
    }

    public void setTransactionsID(int transactionsID) {
        TransactionsID = transactionsID;
    }

    public String getSpendOrCollect() {
        return SpendOrCollect;
    }

    public void setSpendOrCollect(String spendOrCollect) {
        SpendOrCollect = spendOrCollect;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getResidentID() {
        return ResidentID;
    }

    public void setResidentID(int residentID) {
        ResidentID = residentID;
    }

    public int getManagerID() {
        return ManagerID;
    }

    public void setManagerID(int managerID) {
        ManagerID = managerID;
    }

    public int getPreviousBalance() {
        return PreviousBalance;
    }

    public void setPreviousBalance(int previousBalance) {
        PreviousBalance = previousBalance;
    }

    public int getNewBalance() {
        return NewBalance;
    }

    public void setNewBalance(int newBalance) {
        NewBalance = newBalance;
    }

    public String getTransactionsDate() {
        return TransactionsDate;
    }

    public void setTransactionsDate(String transactionsDate) {
        TransactionsDate = transactionsDate;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "TransactionsID":
                return TransactionsID;
            case "SpendOrCollect":
                return SpendOrCollect;
            case "Amount":
                return Amount;
            case "ResidentID":
                return ResidentID;
            case "ManagerID":
                return ManagerID;
            case "PreviousBalance":
                return PreviousBalance;
            case "NewBalance":
                return NewBalance;
            case "TransactionsDate":
                return TransactionsDate;
            default:
                return null;
        }
    }
}
