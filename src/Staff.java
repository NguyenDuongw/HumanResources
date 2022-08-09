public abstract class Staff {
    //Khai báo các state của Staff
    private int id;                     //Mã nhân viên
    private String name;                //Tên nhân viên
    private int age;                    //Tuổi nhân viên
    private double coefficientSalary;   //Hệ số lương
    private String dayStart;            //Ngày vào làm
    private String workingPart;         //Bộ phận làm việc
    private int numberOfDayOff;         //Số ngày nghỉ
    private double salary;              //Lương

    //Hàm tạo cơ bản  của 1 đối tượng
    public Staff(int id,
                 String name,
                 int age,
                 double coefficientSalary,
                 String dayStart,
                 String workingPart,
                 int numberOfDayOff) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.coefficientSalary = coefficientSalary;
        this.dayStart = dayStart;
        this.workingPart = workingPart;
        this.numberOfDayOff = numberOfDayOff;
    }

    //Các setter và getter cho từng state
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setCoefficientSalary(double coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public double getCoefficientSalary() {
        return coefficientSalary;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setNumberOfDayOff(int numberOfDayOff) {
        this.numberOfDayOff = numberOfDayOff;
    }

    public int getNumberOfDayOff() {
        return numberOfDayOff;
    }

    public void setWorkingPart(String workingPart) {
        this.workingPart = workingPart;
    }

    public String getWorkingPart() {
        return workingPart;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    //Hàm abstract được gọi ra để các class con kế thừa
    abstract void displayInformation();
}
