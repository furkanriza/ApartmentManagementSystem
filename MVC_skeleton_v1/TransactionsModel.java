import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class TransactionsModel implements ModelInterface{
    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {

        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	TransactionsID, SpendOrCollect, Amount, ResidentID, ManagerID, PreviousBalance, NewBalance, TransactionsDate");
        sql.append(" FROM Transactions ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY TransactionsID");
        //System.out.println(sql.toString() + "\n");


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();
        System.out.println(result);

        return result;
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {

        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Transactions (" + fieldNames + ") " );
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i=0; i<rows.size(); i++) {
            if (rows.get(i) instanceof Transactions) {
                rowCount++;

                Transactions transactions = (Transactions) rows.get(i);

                sql.append("(");
                int newB = getPreviousBalance();
                int amount =0;
                char ch =' ';
                for (int j=0; j<fieldList.length; j++) {

                    String fieldName = fieldList[j].trim();

                    if(fieldName.equals("Amount")){
                        amount = Integer.parseInt(DatabaseUtilities.formatField(transactions.getByName(fieldName)));
                    }
                    if (fieldName.equals("SpendOrCollect")){
                        ch = DatabaseUtilities.formatField(transactions.getByName(fieldName)).charAt(1);
                    }
                    if(fieldName.equals("PreviousBalance")){
                        sql.append(newB);
                    } else if (fieldName.equals("NewBalance")) {
                            if (ch =='c'|| ch == 'C'){
                            newB += amount;
                            sql.append(newB);
                            }else{
                                System.out.println(ch);
                                newB -= amount;
                                sql.append(newB);
                            }
                    }else {
                        //This is the main place that we do the job
                    sql.append(DatabaseUtilities.formatField(transactions.getByName(fieldName)));
                    }//
//
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");

                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }

        }
        System.out.println(sql.toString());


        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        return rowCount;
    }

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {

        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE Transactions SET ");
        int appendCount = 0;
        for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
            sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
            if (++appendCount < updateParameters.size()) {
                sql.append(", ");
            }
        }
        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    @Override
    public int delete(Map<String, Object> whereParameters) throws Exception {

        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM Transactions ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }
    public int getPreviousBalance() throws Exception {
        // construct SQL statement
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" SELECT TOP 1 ");
        sql1.append("	 NewBalance");
        sql1.append(" FROM Transactions ");
        sql1.append("ORDER BY TransactionsID DESC");

//        int rowCount = 0;
        Connection connection1 = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1.toString());
        ResultSet result1 = preparedStatement1.executeQuery();
        int x =0;
        while(result1.next()){
            x = result1.getInt("NewBalance");
        }
        return x;
    }
//    public int ConstructID()throws Exception{
//
//        // construct SQL statement
//        StringBuilder sql1 = new StringBuilder();
//        sql1.append(" SELECT TOP 1 ");
//        sql1.append("	 TransactionsID");
//        sql1.append(" FROM Transaction ");
//        sql1.append("ORDER BY TransactionsID DESC");
//
//
//        //This is where Magic Happens
//        //Retrives Last Primary Key and generates new one
//
////        int rowCount = 0;
//        Connection connection1 = DatabaseUtilities.getConnection();
//        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1.toString());
//        ResultSet result1 = preparedStatement1.executeQuery();
//        int x =1;
//        while(result1.next()){
//            x += result1.getInt("AssistantManagerID");
//        }
//        return x;
//    }

}
