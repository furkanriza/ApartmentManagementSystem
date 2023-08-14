import java.util.Date;

public class Manager {
    private String Name;
    private String Surname;
    private int ManagerID;
    private int Salary;
    private String DutyStartDate;
    private String DutyEndDate;
    private Integer ResidentID;

    public Manager(String name, String surname, int salary, Integer residentID) {
        Name =name;
        Surname = surname;
        Salary = salary;
        ResidentID = residentID;
        DutyStartDate = new Date().toString();
    }



    public Manager(String name, String surname, Integer salary, Integer residentID) {
        Name =name;
        Surname = surname;
        Salary = salary;
        ResidentID = residentID;
        DutyStartDate = new Date().toString();
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

    public int getManagerID() {
        return ManagerID;
    }

    public void setManagerID(int managerID) {
        ManagerID = managerID;
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
            case "ManagerID":
                return ManagerID;
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
        return "Manager{" +
                "ManagerID=" + ManagerID +
                ", Salary=" + Salary +
                ", DutyStartDate='" + DutyStartDate + '\'' +
                ", DutyEndDate='" + DutyEndDate + '\'' +
                ", ResidentID='" + ResidentID + '\'' +
                '}';
    }
}
