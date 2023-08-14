import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ResidentView implements ViewInterface {
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
                int residentID = resultSet.getInt("ResidentID");
                String doorNumber = resultSet.getString("DoorNumber");
                int dept = resultSet.getInt("Dept");
                String residencyStartDate = resultSet.getString("ResidencyStartDate");
                // ^^ get string colum label (from string) keep in mind
                String residencyEndDate = resultSet.getString("ResidencyEndDate");

                // Display values
                System.out.print(name + "\t");
                System.out.print(surName + "\t");
                System.out.print(residentID + "\t");
                System.out.print(doorNumber+ "\t");
                System.out.print(dept+ "\t");
                System.out.print(residencyStartDate+ "\t");
                System.out.println(residencyEndDate);
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
        String name = getString("Name: ",true);
        String surName = getString("SurName: ",true);
        Integer residentID = getInteger("ResidentID: ",true);
        String doorNumber = getString("DoorNumber: ",true);
        Integer dept = getInteger("Dept: ",true);
        String residencyStartDate = getString("ResidencyStartDate: ",true);
        String residencyEndDate = getString("ResidencyEndDate: ",true);

        //Arranged According to Specifications of the Class

        Map<String, Object> whereParameters = new HashMap<>();
        if (name != null) whereParameters.put("Name", name);
        if (surName != null) whereParameters.put("SurName", surName);
        if (residentID != null) whereParameters.put("ResidentID", residentID);
        if (doorNumber != null) whereParameters.put("DoorNumber", doorNumber);
        if (dept != null) whereParameters.put("Dept", dept);
        if (residencyStartDate != null) whereParameters.put("ResidencyStartDate", residencyStartDate);
        if (residencyEndDate != null) whereParameters.put("ResidencyEndDate", residencyEndDate);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "Name, Surname, DoorNumber, ResidentID, Dept, ResidencyStartDate");
        List<Object> rows = new ArrayList<>();

        String name, surName, doorNumber;
        do
        {
            System.out.println("Fields to insert:");
            name = getString("Name : ", true);
            surName = getString("SurName : ", true);
            doorNumber = getString("DoorNumber : ", true);

            System.out.println();

            if (name != null && surName != null && doorNumber != null) {
                rows.add(new Resident(name, surName, doorNumber));
            }
        }
        while (name != null && surName != null && doorNumber != null );

        parameters.put("rows", rows);

        return new ViewData("Resident", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String name = getString("Name: ",true);
        String surName = getString("SurName: ",true);
        String doorNumber = getString("DoorNumber: ",true);
        Integer dept = getInteger("Dept: ",true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (name != null) updateParameters.put("Name", name);
        if (surName != null) updateParameters.put("SurName", surName);
        if (doorNumber != null) updateParameters.put("DoorNumber", doorNumber);
        if (dept != null) updateParameters.put("Dept", dept);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "delete", parameters);
    }


    @Override
    public String toString() {
        return "Resident View";
    }
}
