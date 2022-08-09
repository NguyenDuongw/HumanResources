public class Employee extends Staff implements ICalculator {
    //Khai báo thêm state overTime để chỉ số giờ làm thêm
    private int overTime;
    
    //Hàm tạo của class Employee
    public Employee(int id,
                    String name,
                    int age,
                    double coefficientSalary,
                    String dayStart,
                    String workingPart,
                    int numberOfDayOff,
                    int overTime) {
        super(id, name, age, coefficientSalary, dayStart, workingPart, numberOfDayOff);
        this.overTime = overTime;
        calSalary();                    //Khi khởi tạo gọi luôn để tính được salary cho đối tượng
    }

    //setter và getter
    public int getoverTime() {
        return overTime;
    }

    public void setoverTime(int overTime) {
        this.overTime = overTime;
    }

    //Kế thừa và định nghĩa hàm displayInformation từ Staff
    @Override
    void displayInformation() {
        System.out.println("Mã nhân viên: " + getId());
        System.out.println("Tên nhân viên: " + getName());
        System.out.println("Tuổi nhân viên: " + getAge());
        System.out.println("Hệ số lương: " + getCoefficientSalary());
        System.out.println("Ngày vào làm: " + getDayStart());
        System.out.println("Bộ phận làm việc: " + getWorkingPart());
        System.out.println("Số ngày nghỉ phép: " + getNumberOfDayOff());
        System.out.println("Số giờ làm thêm: " + getoverTime());
        System.out.println("Tiền lương: " + getSalary());
        System.out.println();
    }

    //Hàm claSalary được kế thừa từ Interface
    @Override
    public void calSalary() {
        //Sử dụng setter từ Staff để set lương cho Employee
        setSalary(getCoefficientSalary() * 3000000 + getoverTime() * 200000);
    }
}
