
# 🚗 Car Rental System – Java Swing + MySQL

## 📌 Mô tả dự án

Ứng dụng **Car Rental System** được xây dựng bằng **Java Swing** để quản lý hoạt động cho thuê xe ô tô, sử dụng **MySQL** làm cơ sở dữ liệu.

### 👤 Người dùng có thể:
- Đăng ký / Đăng nhập
- Xem và cập nhật thông tin tài khoản
- Xem danh sách xe có sẵn
- Chọn xe và thời gian thuê
- Xem lịch sử thuê xe cá nhân

### 🛠️ Quản trị viên có thể:
- Quản lý danh sách xe (thêm, sửa, xóa)
- Cập nhật trạng thái xe (đang cho thuê, hết xe)
- Xem thống kê lượt thuê, tổng thời gian thuê
- Tra cứu số lượng xe đã thuê và thời gian thuê của từng người

---

## 💻 Công nghệ sử dụng

- Java 8+
- IntelliJ IDEA
- Java Swing
- JDBC (Java Database Connectivity)
- MySQL
- Git + GitHub

---

## ⚙️ Yêu cầu hệ thống

- Java JDK 8 trở lên
- MySQL Server (5.7+ hoặc 8.0+)
- IntelliJ IDEA (khuyến nghị dùng phiên bản có hỗ trợ GUI Designer)

## 📁 Cấu trúc thư mục
```
CarRentalSystem/
│
├── README.md                      # Mô tả dự án
├── .gitignore                     # Bỏ qua các tệp không cần thiết khi push lên Git
├── pom.xml / build.gradle         # (Nếu dùng Maven hoặc Gradle)
│
├── src/
│   └── main/
│       ├── java/
│       │       | 
│       │       ├── Main.java                        # Entry point
│       │
│       │       ├── model/                           # Các class dữ liệu
│       │       │   ├── User.java
│       │       │   ├── Car.java
│       │       │   ├── Rental.java
│       │       │   └── Enums.java                   # Trạng thái xe, loại xe, v.v.
│       │
│       │       ├── dao/                             # Tầng truy cập dữ liệu (giả lập hoặc kết nối DB)
│       │       │   ├── UserDAO.java
│       │       │   ├── CarDAO.java
│       │       │   └── RentalDAO.java
│       │
│       │       ├── service/                         # Tầng xử lý nghiệp vụ
│       │       │   ├── UserService.java
│       │       │   ├── CarService.java
│       │       │   └── RentalService.java
│       │
│       │       ├── ui/                              # Giao diện người dùng (Swing)
│       │       │   ├── common/                      # Các thành phần dùng chung
│       │       │   │   └── CustomTableModel.java
│       │       │   │
│       │       │   ├── user/                        # Giao diện cho người dùng
│       │       │   │   ├── UserDashboard.java
│       │       │   │   ├── CarSelectionPanel.java
│       │       │   │   └── MyRentalInfoPanel.java
│       │       │   │
│       │       │   ├── admin/                       # Giao diện cho admin
│       │       │   │   ├── AdminDashboard.java
│       │       │   │   ├── CarManagementPanel.java
│       │       │   │   └── RentalStatsPanel.java
│       │       │   │
│       │       │   └── login/                       # Giao diện đăng nhập, đăng ký
│       │       │       ├── LoginForm.java
│       │       │       └── RegisterForm.java
│       │
│       │       └── util/                            # Các class tiện ích
│       │           ├── Validator.java
│       │           └── DataFormatter.java
│       │
│       └── resources/
│           └── data/                                # Lưu trữ file JSON giả lập DB (nếu không dùng SQL, hiện không cần nên bỏ)
│               ├── users.json
│               ├── cars.json
│               └── rentals.json
│
└── docs/                                            # Tài liệu thiết kế, báo cáo, sơ đồ lớp
├── class-diagram.png
└── database-schema.pdf
```

## 🧪 Cách chạy chương trình

1. **Tạo cơ sở dữ liệu MySQL**:
  - Tạo database `car_rental_db`
  - Chạy script `database/init.sql` để tạo bảng

2. **Cấu hình kết nối trong code** (ví dụ file `DBConnection.java`):
   ```java
   String url = "jdbc:mysql://localhost:3306/car_rental_db";
   String username = "root";
   String password = "your_password";