import java.util.Date;

public class ManagerController {

    private String Name;
    private String Surname;
    private int ManagerControllerID;
    private int Salary;
    private String DutyStartDate;
    private String DutyEndDate;
    private Integer ResidentID;

    public ManagerController(String name, String surname, int managerControllerID, int salary, Integer residentID) {
        Name =name;
        Surname = surname;
        ManagerControllerID = managerControllerID;
        Salary = salary;
        ResidentID = residentID;
        DutyStartDate = new Date().toString();
    }
    public ManagerController(String name, String surname, int managerControllerID, int salary, String dutyStartDate, Integer residentID) {
        Name =name;
        Surname = surname;
        ManagerControllerID = managerControllerID;
        Salary = salary;
        DutyStartDate = dutyStartDate;
        ResidentID = residentID;
    }

    public ManagerController(String name, String surname, Integer salary, Integer residentID) {
        Name =name;
        Surname = surname;
        Salary = salary;
        DutyStartDate = new Date().toString();
        ResidentID = residentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getManagerControllerID() {
        return ManagerControllerID;
    }

    public void setManagerControllerID(int ManagerManagerID) {
        ManagerControllerID = ManagerManagerID;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public String getDutyStartDate() {
        return DutyStartDate;
    }

    public void setDutyStartDate(String dutyStartDate) {
        DutyStartDate = dutyStartDate;
    }

    public String getDutyEndDate() {
        return DutyEndDate;
    }

    public void setDutyEndDate(String dutyEndDate) {
        DutyEndDate = dutyEndDate;
    }

    public Integer getResidentID() {
        return ResidentID;
    }

    public void setResidentID(Integer residentID) {
        ResidentID = residentID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "Name":
                return Name;
            case "Surname":
                return Surname;
            case "ManagerControllerID":
                return ManagerControllerID;
            case "Salary":
                return Salary;
            case "DutyStartDate":
                return DutyStartDate;
            case "DutyEndDate":
                return DutyEndDate;
            case "ResidentID":
                return ResidentID;

            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "ManagerController{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", ManagerControllerID=" + ManagerControllerID +
                ", Salary=" + Salary +
                ", DutyStartDate='" + DutyStartDate + '\'' +
                ", DutyEndDate='" + DutyEndDate + '\'' +
                ", ResidentID='" + ResidentID + '\'' +
                '}';
    }
}
