import java.util.Date;

public class Resident {
        private String Name;
        private String Surname;
        private Integer ResidentID;
        private String DoorNumber;
        private Integer Dept;
        private String ResidencyStartDate;
        private String ResidencyEndDate;



    // Resident constructor without nullables
    public Resident(String name, String surname, int residentID, String doorNumber, int dept, String residencyStartDate) {
        Name = name;
        Surname = surname;
        ResidentID = residentID;
        DoorNumber = doorNumber;
        Dept = dept;
        ResidencyStartDate = residencyStartDate;

    }

    // Resident constructor with all fields
    public Resident(String name, String surname, int residentID, String doorNumber, int dept, String residencyStartDate, String residencyEndDate) {
        Name = name;
        Surname = surname;
        ResidentID = residentID;
        DoorNumber = doorNumber;
        Dept = dept;
        ResidencyStartDate = residencyStartDate;
        ResidencyEndDate = residencyEndDate;

    }


    public Resident(String name, String surname, String doorNumber, int residentID) {
        Name = name;
        Surname = surname;
        DoorNumber = doorNumber;
        ResidentID = residentID;
        Dept = 0;
        ResidencyStartDate= new Date().toString();


    }

    public Resident(String name, String surname, String doorNumber) {
        Name = name;
        Surname = surname;
        DoorNumber = doorNumber;
        Dept = 0;
        ResidencyStartDate= new Date().toString();
    }

    public int getResidentID() {
        return ResidentID;
    }

    public String getDoorNumber() {
        return DoorNumber;
    }

    public int getDept() {
        return Dept;
    }

    public String getResidencyStartDate() {
        return ResidencyStartDate;
    }

    public String getResidencyEndDate() {
        return ResidencyEndDate;
    }


    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setResidentID(int residentID) {
        ResidentID = residentID;
    }

    public void setDoorNumber(String doorNumber) {
        DoorNumber = doorNumber;
    }

    public void setDept(int dept) {
        Dept = dept;
    }

    public void setResidencyStartDate(String residencyStartDate) {
        ResidencyStartDate = residencyStartDate;
    }

    public void setResidencyEndDate(String residencyEndDate) {
        ResidencyEndDate = residencyEndDate;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ResidentID": return ResidentID;
            case "DoorNumber": return DoorNumber;
            case "Dept": return Dept;
            case "ResidencyStartDate": return ResidencyStartDate;
            case "ResidencyEndDate": return ResidencyEndDate;
            case "Name": return Name;
            case "Surname": return Surname;
            default: return null;
        }
    }

    @Override
    public String toString() {
        return "Resident{" +
                "ResidentID=" + ResidentID +
                ", DoorNumber='" + DoorNumber + '\'' +
                ", Dept=" + Dept +
                ", ResidencyStartDate=" + ResidencyStartDate +
                ", ResidencyEndDate=" + ResidencyEndDate +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                '}';
    }
}
