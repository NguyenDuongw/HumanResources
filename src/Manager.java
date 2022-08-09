public class Manager extends Staff implements ICalculator {
    //Khai báo các state của class Manager
    private String title;                   //Chức vụ quản lý
    private int responseSalary;             //Lương trách nhiệm

    //Hàm tạo của class Manager
    public Manager(int id,
                   String name,
                   int age,
                   double coefficientSalary,
                   String dayStart,
                   String workingPart,
                   int numberOfDayOff,
                   String title) {
        super(id, name, age, coefficientSalary, dayStart, workingPart, numberOfDayOff);
        this.title = title;
        calSalary();                           //Khi khởi tạo gọi luôn để tính được salary cho đối tượng
    }

    //setter và geter
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    //Kế thừa và định nghĩa hàm displayInformation từ Staff
    @Override
    void displayInformation() {
        System.out.println("Mã quản lý: " + getId());
        System.out.println("Tên quản lý: " + getName());
        System.out.println("Chức danh: " + getTitle());
        System.out.println("Tuổi quản lý: " + getAge());
        System.out.println("Hệ số lương: " + getCoefficientSalary());
        System.out.println("Ngày vào làm: " + getDayStart());
        System.out.println("Bộ phận làm việc: " + getWorkingPart());
        System.out.println("Số ngày nghỉ phép: " + getNumberOfDayOff());
        System.out.println("Tiền lương: " + getSalary());
        System.out.println();
    }

    //Hàm calSalary được kế thừa từ Interface
    @Override
    public void calSalary() {
        //Kiểm tra title của quản lý
        if (getTitle().equals("Business Leader")) {
            responseSalary = 8000000;
        } else if (getTitle().equals("Project Leader")) {
            responseSalary = 5000000;
        } else if (getTitle().equals("Technical Leader")) {
            responseSalary = 6000000;
        }

        //Sử dụng setter để set lương cho Manger
        setSalary(getCoefficientSalary() * 5000000 + responseSalary);
    }
}
