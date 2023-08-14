import java.util.Date;

public class AssistantManager {
    private String Name;
    private String Surname;
    private int AssistantManagerID;
    private int Salary;
    private String DutyStartDate;
    private String DutyEndDate;
    private Integer ResidentID;
    private int ManagerID;

    public AssistantManager(String name, String surname, int assistantManagerID, int salary, Integer residentID, int managerID) {
        Name = name;
        Surname = surname;
        AssistantManagerID = assistantManagerID;
        Salary = salary;
        DutyStartDate = new Date().toString();
        DutyEndDate = null;
        ResidentID = residentID;
        ManagerID = managerID;
    }
    public AssistantManager(String name, String surname,  int salary, Integer residentID, int managerID) {
        Name = name;
        Surname = surname;
        Salary = salary;
        DutyStartDate = new Date().toString();
        DutyEndDate = null;
        ResidentID = residentID;
        ManagerID = managerID;
    }

    public AssistantManager(String name, String surname, int assistantManagerID, int salary,
                            String dutyStartDate, String dutyEndDate, Integer residentID, int managerID) {
        Name = name;
        Surname = surname;
        AssistantManagerID = assistantManagerID;
        Salary = salary;
        DutyStartDate = dutyStartDate;
        DutyEndDate = dutyEndDate;
        ResidentID = residentID;
        ManagerID = managerID;
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

    public int getAssistantManagerID() {
        return AssistantManagerID;
    }

    public void setAssistantManagerID(int assistantManagerID) {
        AssistantManagerID = assistantManagerID;
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

    public int getManagerID() {
        return ManagerID;
    }

    public void setManagerID(int managerID) {
        ManagerID = managerID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "Name":
                return Name;
            case "Surname":
                return Surname;
            case "AssistantManagerID":
                return AssistantManagerID;
            case "Salary":
                return Salary;
            case "DutyStartDate":
                return DutyStartDate;
            case "DutyEndDate":
                return DutyEndDate;
            case "ResidentID":
                return ResidentID;
            case "ManagerID":
                return ManagerID;

            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "AssistantManager{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", AssistantManagerID=" + AssistantManagerID +
                ", Salary=" + Salary +
                ", DutyStartDate='" + DutyStartDate + '\'' +
                ", DutyEndDate='" + DutyEndDate + '\'' +
                ", ResidentID='" + ResidentID + '\'' +
                ", ManagerID=" + ManagerID +
                '}';
    }
}
