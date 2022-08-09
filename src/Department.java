import java.util.ArrayList;

public class Department {
    //Khai báo các state của class Department
    private int id;                                             //Mã bộ phận
    private String name;                                        //Tên bộ phận
    private int numberOfEmployees;                              //Số lượng nhân viên
    private ArrayList<Staff> staffList = new ArrayList<Staff>();    //Khai báo thêm 1 ArrayList để quản lý các nhân viên

    //Hàm tạo của class Department
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Setter và Getter của Class
    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfEmployees() {
        return staffList.size();
    }

    //Hàm addStaff để  tiến hành thêm nhân viên vào bộ phận
    public void addStaff(Staff staff){
        staffList.add(staff);
        numberOfEmployees++;
    }

    //Hàm toString để in ra trạng thái hiện tại của bộ phận
    public String toString() {
        String partID = "Part ID: " + id;
        String partName = "Name of Part: " + name;
        String number = "Number Of Employee: " + numberOfEmployees;
        return partID + ", " + partName + ", " + number;
    }
}
