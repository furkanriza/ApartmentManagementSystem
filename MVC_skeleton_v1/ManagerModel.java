import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerModel implements ModelInterface{
    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	Name, SurName, ManagerID, Salary, DutyStartDate, DutyEndDate, ResidentID ");
        sql.append(" FROM Manager ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY ManagerID");
        //System.out.println(sql.toString() + "\n");


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Manager (" + fieldNames + ") " );
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i=0; i<rows.size(); i++) {
            if (rows.get(i) instanceof Manager) {
                rowCount++;

                Manager manager = (Manager) rows.get(i);

                sql.append("(");
                for (int j=0; j<fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    if(!fieldName.equals("ManagerID")){
                    sql.append(DatabaseUtilities.formatField(manager.getByName(fieldName)));
                    }else {
                        sql.append(ConstructID());
                    }

                    if (j < fieldList.length - 1 ) {
                        sql.append(", ");
                    }
//                    if (j==4){
//                EndSomeOne(manager.getByName(fieldName).toString());
//                    }
                }// for end
                sql.append(")");

                if (i < rows.size() - 1) {
                    sql.append(", ");
                }

            }


        }
        //System.out.println(sql.toString());


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
        sql.append(" UPDATE Manager SET ");
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
        sql.append(" DELETE FROM Manager ");

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

    public int ConstructID()throws Exception{

        // construct SQL statement
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" SELECT TOP 1 ");
        sql1.append("	 ManagerID");
        sql1.append(" FROM Manager ");
        sql1.append("ORDER BY ManagerID DESC");


        //This is where Magic Happens
        //Retrives Last Primary Key and generates new one

//        int rowCount = 0;
        Connection connection1 = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1.toString());
        ResultSet result1 = preparedStatement1.executeQuery();

        int x =1;
        while(result1.next()){
            x += result1.getInt("ManagerID");
        }
        return x;
    }

//    public void EndSomeOne(String date)throws Exception{
//        // construct SQL statement
//        StringBuilder sql1 = new StringBuilder();
//        sql1.append(" SELECT TOP 1 ");
//        sql1.append("	 ManagerID");
//        sql1.append(" FROM Manager ");
//        sql1.append("ORDER BY ManagerID DESC");
// :(
//
////        int rowCount = 0;
//        Connection connection1 = DatabaseUtilities.getConnection();
//        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1.toString());
//        ResultSet result1 = preparedStatement1.executeQuery();
//
//        int managerID=0;
//        while(result1.next()){
//            managerID= result1.getInt("ManagerID");
//        }
//
//        Map<String, Object> updateParameters = new HashMap<>();
//        updateParameters.put("DutyEndDate", date);
//
//        Map<String, Object> whereParameters = new HashMap<>();
//        whereParameters.put("ManagerID", managerID);
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("updateParameters", updateParameters);
//        parameters.put("whereParameters", whereParameters);
//        System.out.println(updateParameters);
//        System.out.println(managerID);
//        ViewData Try = new ViewData("Manager", "update", parameters);
//        System.out.println(Try);
//
//    }
}
