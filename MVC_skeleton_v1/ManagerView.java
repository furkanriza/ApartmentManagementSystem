import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerView implements ViewInterface{
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
                String name = resultSet.getString("Name");
                String surName = resultSet.getString("SurName");
                int managerID = resultSet.getInt("ManagerID");
                int salary = resultSet.getInt("Salary");
                String dutyStartDate = resultSet.getString("DutyStartDate");
                String DutyEndDate = resultSet.getString("DutyEndDate");
                Integer residentID = resultSet.getInt("ResidentID");

                // Display values
                System.out.print(name + "\t");
                System.out.print(surName + "\t");
                System.out.print(managerID + "\t");
                System.out.print(salary+ "\t");
                System.out.print(dutyStartDate+ "\t");
                System.out.print(DutyEndDate + "\t");
                System.out.println(residentID);
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
        String name = getString("Name : ",true);
        String surName = getString("SurName : ",true);
        Integer managerID = getInteger("ManagerID : ",true);
        Integer salary = getInteger("Salary : ",true);
        String dutyStartDate = getString("DutyStartDate : ",true);
        String dutyEndDate = getString("DutyEndDate : ",true);
        Integer residentID = getInteger("ResidentID : ",true);

        //Arranged According to Specifications of the Class

        Map<String, Object> whereParameters = new HashMap<>();
        if (name != null) whereParameters.put("Name", name);
        if (surName != null) whereParameters.put("SurName", surName);
        if (managerID != null) whereParameters.put("ManagerID", managerID);
        if (salary != null) whereParameters.put("Salary", salary);
        if (dutyStartDate != null) whereParameters.put("DutyStartDate", dutyStartDate);
        if (dutyEndDate != null) whereParameters.put("DutyEndDate", dutyEndDate);
        if (residentID != null) whereParameters.put("ResidentID", residentID);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Manager", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "Name, Surname, ManagerID, Salary, DutyStartDate, ResidentID");
        List<Object> rows = new ArrayList<>();

        String name, surName;
        Integer  salary, residentID;
        do
        {
            System.out.println("Fields to insert:");
            name = getString("Name : ", true);
            surName = getString("SurName : ", true);
            salary = getInteger("Salary : ",true);
            residentID = getInteger("ResidentID : ", true);


            System.out.println();

            if (name != null && surName != null  &&  salary!=null) {
                rows.add(new Manager(name, surName,  salary, residentID));
            }
        }
        while (name != null && surName != null  &&  salary!=null);

        parameters.put("rows", rows);

        return new ViewData("Manager", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String name = getString("Name: ",true);
        String surName = getString("SurName: ",true);
        Integer salary = getInteger("Salary: ",true);
        Integer residentID = getInteger("ResidentID: ",true);

        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (name != null) updateParameters.put("Name", name);
        if (surName != null) updateParameters.put("SurName", surName);
        if (salary != null) updateParameters.put("Salary", salary);
        if (residentID != null) updateParameters.put("ResidentID", residentID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Manager", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Manager", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Manager View";
    }
}
