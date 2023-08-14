import java.sql.ResultSet;
import java.util.*;

public class TransactionsView implements ViewInterface {


@Override
public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch(operationName) {
        case "select": return selectOperation(modelData);
        case "insert": return insertOperation(modelData);
        case "update": return updateOperation(modelData);
        case "delete": return deleteOperation(modelData);
        case "select.gui": return selectGUI(modelData);
        case "insert.gui": return insertGUI(modelData);
        case "update.gui": return updateGUI(modelData);
        case "delete.gui": return deleteGUI(modelData);
        }

        return new ViewData("MainMenu", "");
        }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int transactionsID = resultSet.getInt("TransactionsID");
                String spendOrCollect = resultSet.getString("SpendOrCollect");
                int amount = resultSet.getInt("Amount");
                int residentID = resultSet.getInt("ResidentID");
                int managerID = resultSet.getInt("ManagerID");
                int previousBalance = resultSet.getInt("PreviousBalance");
                int newBalance = resultSet.getInt("NewBalance");
                String transactionsDate = resultSet.getString("TransactionsDate");

                // Display values
                System.out.print(transactionsID + "\t");
                System.out.print(spendOrCollect + "\t");
                System.out.print(amount + "\t");
                System.out.print(residentID + "\t");
                System.out.print(managerID + "\t");
                System.out.print(previousBalance + "\t");
                System.out.print(newBalance + "\t");
                System.out.println(transactionsDate);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
        }

        ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
        }

        ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
        }

        Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter condition;");
            Integer transactionsID = getInteger("TransactionsID : ",true);
            String spendOrCollect = getString("SpendOrCollect : ",true);
            Integer amount = getInteger("Amount : ",true);
            Integer residentID = getInteger("ResidentID : ",true);
            Integer managerID = getInteger("ManagerID : ",true);
            Integer previousBalance = getInteger("PreviousBalance : ",true);
            Integer newBalance = getInteger("NewBalance : ",true);
            String transactionsDate = getString("TransactionsDate : ",true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (transactionsID != null) whereParameters.put("TransactionsID", transactionsID);
        if (spendOrCollect != null) whereParameters.put("SpendOrCollect", spendOrCollect);
        if (amount != null) whereParameters.put("Amount", amount);
        if (residentID != null) whereParameters.put("ResidentID", residentID);
        if (managerID != null) whereParameters.put("ManagerID", managerID);
        if (previousBalance != null) whereParameters.put("PreviousBalance", previousBalance);
        if (newBalance != null) whereParameters.put("NewBalance", newBalance);
        if (transactionsDate != null) whereParameters.put("TransactionsDate", transactionsDate);


        return whereParameters;
        }

        ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Transactions", "select", parameters);
        }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "TransactionsID, SpendOrCollect, Amount, ResidentID, ManagerID, PreviousBalance, NewBalance, TransactionsDate");
        List<Object> rows = new ArrayList<>();


        String  spendOrCollect;
        Integer transactionsID, amount, residentID, managerID;
        do {
            System.out.println("Fields to insert:");
            transactionsID = getInteger("TransactionsID : ", true);
            do{
            spendOrCollect = getString("SpendOrCollect(in Format of 'c' or 's') : ", false);
            }while(!spendOrCollect.equals("c") &&!spendOrCollect.equals("C") &&!spendOrCollect.equals("s") &&!spendOrCollect.equals("S")  );
            amount = getInteger("Amount : ", true);
            if (spendOrCollect.equals("c") || spendOrCollect.equals("C")) {
                residentID = getInteger("ResidentID : ", true);
            } else {
                residentID = null;
            }
            managerID = getInteger("ManagerID : ", true);

            System.out.println();


            if (transactionsID != null && amount != null && managerID != null && residentID!=null) {

                // constructor for collect which means resident id is not null
                rows.add(new Transactions(transactionsID, spendOrCollect, amount, residentID, managerID));
            } else if (transactionsID != null && amount != null && managerID != null) {
                //// constructor for spend which means resident id is null
                rows.add(new Transactions(transactionsID, spendOrCollect, amount, managerID));

            }
        }
        while (transactionsID != null && amount != null &&  managerID != null);

        parameters.put("rows", rows);

        return new ViewData("Transactions", "insert", parameters);
    }

        ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer transactionsID = getInteger("TransactionsID : ",true);
        String spendOrCollect = getString("SpendOrCollect : ",true);
        Integer amount = getInteger("Amount : ",true);
        Integer residentID = getInteger("ResidentID : ",true);
        Integer managerID = getInteger("ManagerID : ",true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (transactionsID != null) updateParameters.put("TransactionsID", transactionsID);
        if (spendOrCollect != null) updateParameters.put("SpendOrCollect", spendOrCollect);
        if (amount != null) updateParameters.put("Amount", amount);
        if (residentID != null) updateParameters.put("ResidentID", residentID);
        if (managerID != null) updateParameters.put("ManagerID", managerID);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Transactions", "update", parameters);
        }

        ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Transactions", "delete", parameters);
        }

@Override
public String toString() {
        return "Transactions View";
        }


}