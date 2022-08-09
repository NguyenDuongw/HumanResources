import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class HumanResources {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Load thông tin Staff từ file csv và cho vào staffList sử dụng hàm loadStaffList
        ArrayList<Staff> staffList = loadStaffList("Employer List.csv");    //add all employee vào list
        staffList.addAll(loadStaffList("Manager List.csv"));                //thêm list manager vào sau list staff

        //Tạo 2 đối tượng từ class Department và thêm vào departmentList
        Department it = new Department(1, "IT");
        Department sale = new Department(1, "Sale");
        ArrayList<Department> departmentList = new ArrayList<>();
        departmentList.add(it);
        departmentList.add(sale);

        //Lặp qua staffList và thêm các nhân viên vào bộ phận tương ứng
        for (int i = 0; i < staffList.size(); i++) {
            addStaffToDepartment(staffList.get(i), departmentList);         //Hàm thêm một nhân viên vào bộ phận
        }

        System.out.println("Chào mừng đến với chương trình quản lý nhân viên của công ty ABC");
        int option;                         //Biến option để lưu lựa chọn từ người dùng
        while (true) {
            option = 0;
            System.out.println("Bạn muốn làm gì: (Hãy nhấn phím ứng với lựa chọn của bạn)");
            System.out.println("1 - Hiển thị danh sách nhân viên hiện có trong công ty");
            System.out.println("2 - Hiển thị các bộ phận trong công ty");
            System.out.println("3 - Hiển thị các nhân viên theo từng bộ phận");
            System.out.println("4 - Thêm nhân viên mới vào công ty ");
            System.out.println("5 - Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên");
            System.out.println("6 - Hiển thị bảng lương của nhân viên toàn công ty");

            //Sử dụng switch-case để chạy hàm tương ứng khi người dùng gọi
            System.out.print("Lựa chọn của bạn là: ");
            option = checkInput(1, 6, sc.nextInt());               //Hàm checkInput sẽ kiểm tra xem người dùng có đang nhập vào từ 1-6
            System.out.println();
            switch (option) {
                case 1 -> displayStaff(staffList);                      //Hàm hiển thị staff trong cty
                case 2 -> displayPartName(departmentList);              //Hàm hiển thị tên các bộ phận
                case 3 -> displayStaffPart(departmentList);             //Hàm hiển thị nhân viên theo bộ phận
                case 4 -> addNewStaff(staffList, departmentList);       //Hàm thêm đối tượng mới vào staffList
                case 5 -> findStaff(staffList);                         //Tìm đối tượng trong staffList
                case 6 -> displaySalary(staffList);                     //Hàm hiển thị lương
            }

            //Kiểm tra người dùng muốn thoát chương trình chưa
            System.out.println("Bạn có muốn tra cứu thêm thông tin nào không");
            System.out.println("1 - Có");
            System.out.println("2 - Không");

            //Nếu không sẽ chạy hàm if, nếu có thì sẽ quay lại vòng lặp
            System.out.print("Lựa chọn của bạn là: ");
            int input = checkInput(1, 2, sc.nextInt());
            System.out.println();
            if (input != 1) {
                System.out.println("Cám ơn bạn đã sử dụng phần mềm tra cứu của công ty ABC");
                break;
            }
        }
    }

    //Hàm in ra các staff trong staffList
    public static void displayStaff(ArrayList<Staff> staffList) {
        for (int i = 0; i < staffList.size(); i++) {
            staffList.get(i).displayInformation();              //Sử dụng phương thức được kế thừa displayInformation
        }
    }

    //Hàm hiển thị tên các bộ phận có trong cty
    public static void displayPartName(ArrayList<Department> departmentList) {
        //Duyệt qua mảng departmentList để đưa ra thông tin
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.println(departmentList.get(i).toString());
        }
    }

    //Hàm hiển thị nhân viên trong một bộ phận
    public static void displayStaffPart(ArrayList<Department> departmentList) {
        //Vòng lặp thứ nhất duyệt qua các bộ phân của công ty
        for (int i = 0; i < departmentList.size(); i++) {
            //Ở trong bộ phận đó, lại duyệt lần lượt qua danh sách nhân viên
            for (int j = 0; j < departmentList.get(i).getStaffList().size(); j++) {
                //Mỗi nhân viên được gọi đến thì chạy hàm displayInformation để hiển thị thông tin
                departmentList.get(i).getStaffList().get(j).displayInformation();
            }
            System.out.println("------------------------------------");
        }
    }

    //Hàm thêm nhân viên mới vào staffList và departmentList
    public static void addNewStaff(ArrayList<Staff> staffList, ArrayList<Department> departmentList) {
        //Hỏi người dùng muốn thêm một manager hay employer
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn thêm quản lý hay nhân viên");
        System.out.println("1 - Quản lý");
        System.out.println("2 - Nhân viên");
        System.out.print("Lựa chọn của bạn là: ");
        int option = checkInput(1, 2, sc.nextInt());        //Kiểm tra nhập có nằm trong 1-2

        //Khai báo các biến liên quan đến staff state
        int id;
        String name;
        int age;
        double coefficientSalary;
        String dayStart;
        String workingPart;
        int numberOfDayOff;

        //Lưu các giá trị người dùng nhập vào các biến trên
        System.out.print("Hệ số lương: ");
        coefficientSalary = sc.nextDouble();
        System.out.print("Ngày vào làm: ");
        dayStart = sc.next();
        System.out.print("Bộ phận làm việc: ");
        workingPart = sc.next();
        System.out.print("Số ngày nghỉ phép: ");
        numberOfDayOff = sc.nextInt();
        if (option == 1) {        //Trường hợp người dùng thêm vào một quản lý
            String title;
            System.out.print("Mã quản lý: ");
            id = sc.nextInt();
            System.out.print("Tên quản lý: ");
            name = sc.next();
            System.out.print("Chức danh: ");
            title = sc.next();
            System.out.print("Tuổi quản lý: ");
            age = sc.nextInt();

            //Lần lượt tạo một đối tượng manager và thêm vào staffList và departmentList
            Manager newManager = new Manager(id, name, age, coefficientSalary, dayStart, workingPart, numberOfDayOff, title);
            staffList.add(newManager);
            addStaffToDepartment(newManager, departmentList);
        } else {                 //Trường hợp người dùng thêm vào một nhân viên
            int overtime;
            System.out.print("Mã nhân viên: ");
            id = sc.nextInt();
            System.out.print("Tên nhân viên: ");
            name = sc.next();
            System.out.print("Tuổi nhân viên: ");
            age = sc.nextInt();
            System.out.print("Số giờ làm thêm: ");
            overtime = sc.nextInt();

            //Lần lượt tạo một đối tượng employer và thêm vào staffList và departmentList
            Employee newEmployee = new Employee(id, name, age, coefficientSalary, dayStart, workingPart, numberOfDayOff, overtime);
            staffList.add(newEmployee);
            addStaffToDepartment(newEmployee, departmentList);
        }
    }

    //Hàm tìm nhân viên với mã hoặc tên
    public static void findStaff(ArrayList<Staff> staffList) {
        //Hỏi người dùng muốn tìm theo mã nhân viên hay tên nhân viên
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn tìm theo tên nhân viên hay mã nhân viên?");
        System.out.println("1 - Tên nhân viên");
        System.out.println("2 - Mã nhân viên");
        System.out.print("Lựa chọn của bạn là: ");
        int option = checkInput(1, 2, sc.nextInt());        //Kiểm tra nhập

        ArrayList<Staff> staffFound = new ArrayList<>();          //Tạo mảng để lưu các nhân viên được tìm thấy phù hợp với id or tên đó (trùng id or tên)
        if (option == 1) {                                        //Trường hợp người dùng tìm theo id
            //Tạo biến lưu giá trị nhập vào
            String nameToFind;
            System.out.print("Xin mời nhập tên của nhân viên bạn muốn tìm: ");
            nameToFind = sc.next();

            //Duyệt qua staffList, nếu thấy id trùng với id cần tìm thì thêm object đó vào staffFound
            for (int i = 0; i < staffList.size(); i++) {
                if (staffList.get(i).getName().equals(nameToFind)) {
                    staffFound.add(staffList.get(i));
                }
            }
        } else {                        //Trường hợp người dùng tìm theo tên
            //Tạo biến lưu giá trị nhập vào
            int idToFind;
            System.out.print("Xin mời nhập mã nhân viên bạn muốn tìm: ");
            idToFind = sc.nextInt();

            //Duyệt qua staffList, nếu thấy tên trùng với tên cần tìm thì thêm object đó vào staffFound
            for (int i = 0; i < staffList.size(); i++) {
                if (staffList.get(i).getId() == idToFind) {
                    staffFound.add(staffList.get(i));
                }
            }
        }

        //Duyệt qua list staffFound để lần lượt in thông tin các nhân viên được tìm thấy ra
        if (staffFound.size() != 0) {
            System.out.println("Có " + staffFound.size() + " kết quả phù hợp với tìm kiếm của bạn");
            displayStaff(staffFound);           //Hàm in thông tin nhân viên
        } else {           //Trường hợp không tìm thấy nhân viên phù hợp
            System.out.println("Không có kết quả phù hợp với tìm kiếm");
        }
    }

    //Hàm hiển thị lương các nhân viên theo thứ tự
    public static void displaySalary(ArrayList<Staff> staffList) {
        Scanner sc = new Scanner(System.in);

        //Hỏi người dùng muốn hiển thị lương theo thứ tự nào
        System.out.println("Bạn muốn hiển thị bảng lương theo thứ tự nào");
        System.out.println("1 - Tăng dần");
        System.out.println("2 - Giảm dần");
        System.out.print("Lựa chọn của bạn là: ");

        //Tạo một mảng để copy lại staffList để khi dùng hàm sort ko làm xáo trộn staffList
        ArrayList<Staff> staffListCopy = new ArrayList<>(staffList);

        int option = checkInput(1, 2, sc.nextInt());
        if (option == 1) {          //Xếp theo thứ tự lương tăng dần
            //Tạo comparator mới để dùng hàm sort theo quy ước của mình
            staffListCopy.sort(new Comparator<Staff>() {
                @Override
                public int compare(Staff o1, Staff o2) {
                    return o1.getSalary() > o2.getSalary() ? 1 : -1;
                }
            });
        } else {                    //Xếp theo thứ tự lương giảm dần
            //Tạo comparator mới để dùng hàm sort theo quy ước của mình
            staffListCopy.sort(new Comparator<Staff>() {
                @Override
                public int compare(Staff o1, Staff o2) {
                    return o1.getSalary() < o2.getSalary() ? 1 : -1;
                }
            });
        }

        //Sau khi sort xong ta sẽ có staffListCopy theo thứ tự lương mong muốn,
        //việc còn lại chỉ là lần lượt hiển thị thông tin các nhân viên theo thứ tự mới
        displayStaff(staffListCopy);
    }

    //Hàm kiểm tra xem option do người dùng nhập có nằm trong khoảng a-b
    public static int checkInput(int a, int b, int option) {
        boolean wrongInput = true;                  //Điều kiện dừng cho vòng lặp khi nhập sai
        Scanner sc = new Scanner(System.in);

        do {
            if (option < a || option > b) {
                System.out.println("Nhập không hợp lệ vui lòng nhập lại");
                System.out.print("Lựa chọn của bạn là: ");
                option = sc.nextInt();              //Cho người dùng nhập lại biến option
            } else {
                wrongInput = false;
            }
        } while (wrongInput);

        return option;                              //Trả về giá trị người dùng nhập vào cuối cùng
    }

    //Hàm đọc thông tin nhân viên từ file csv bằng buffer và đưa vào ArrayList
    public static ArrayList<Staff> loadStaffList(String path) {
        ArrayList<Staff> listStaff = new ArrayList<>();                             //Tạo một ArrayList để chứa các đối tượng

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));       //tạo buffer mới
            reader.readLine();                                                      // đọc dòng đầu tiên

            String line1 = null;                                                    //set dòng 1 = null để skip hàng tên các cột
            //Băt đầu đọc qua từng hàng một
            while ((line1 = reader.readLine()) != null) {
                //chia hàng đang đọc ra thành 1 mảng với các phần tử ngăn nhau bởi dấu ;
                String[] values = line1.split(";");

                //gán các phần tử của mảng trên vào biến tương ứng
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                double coefficientSalary = Double.parseDouble(values[3]);
                String dayStart = values[4];
                String workingPart = values[5];
                int numberOfDayOff = Integer.parseInt(values[6]);

                //Kiểm tra tên file được load là của Employer hay Manager
                if (path.equals("Employer List.csv")) {
                    int overtime = Integer.parseInt(values[7]);

                    //Tạo đối tượng Employee và add đối tượng vào mảng ban đầu
                    listStaff.add(new Employee(id, name, age, coefficientSalary, dayStart, workingPart, numberOfDayOff, overtime));
                } else {
                    String title = values[7];

                    //Tạo đối tượng Manager và add đối tượng vào mảng ban đầu
                    listStaff.add(new Manager(id, name, age, coefficientSalary, dayStart, workingPart, numberOfDayOff, title));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listStaff;
    }

    //Hàm thêm một nhân viên vào bộ phân tương ứng
    public static void addStaffToDepartment(Staff staff, ArrayList<Department> departmentList) {
        //Lặp qua mảng chứa tên các bộ phận, nếu nơi làm việc của nhân viên trùng với
        //tên của bộ phân thì sẽ sử dụng method addStaff trong class Department
        //để thêm nhân viên vào bộ phận

        for (int i = 0; i < departmentList.size(); i++) {
            if (staff.getWorkingPart().equals(departmentList.get(i).getName())) {
                departmentList.get(i).addStaff(staff);
            }
        }
    }
}
