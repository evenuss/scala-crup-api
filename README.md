# Scala CRUD API dengan Akka HTTP dan SQLite

## 📌 Deskripsi
Proyek ini adalah REST API sederhana yang dibangun menggunakan **Scala**, **Akka HTTP**, dan **SQLite**. API ini memungkinkan operasi CRUD (Create, Read, Update, Delete) untuk mengelola data karyawan.

## 🛠 Teknologi yang Digunakan
- **Scala** (Versi 2.13+ / 3)
- **Akka HTTP** untuk REST API
- **Slick** sebagai ORM untuk database SQLite
- **Spray JSON** untuk JSON parsing
- **SBT** sebagai build tool
- **SQLite** sebagai database

## 📂 Struktur Proyek
```
├── src
│   ├── main
│   │   ├── scala
│   │   │   ├── Main.scala       # Entry point aplikasi
│   │   │   ├── models
│   │   │   │   ├── Database.scala  # Koneksi database & query
│   │   │   │   ├── Employee.scala  # Model data karyawan
│   │   │   │   ├── ApiResponse.scala # Model respons API
│   │   │   ├── routes
│   │   │   │   ├── EmployeeRoutes.scala # Route untuk employee CRUD
│   ├── test
│   │   ├── scala (Unit test)
├── build.sbt
├── README.md
```

## 🚀 Cara Menjalankan
### 1️⃣ Persiapan
Pastikan **Scala**, **SBT**, dan **SQLite** telah terinstall:

```sh
brew install scala sbt sqlite
```

### 2️⃣ Clone Repository & Install Dependensi
```sh
git clone <repo-url>
cd scala-crud-api
sbt update
```

### 3️⃣ Jalankan Server
```sh
sbt run
```
Server akan berjalan di: **http://localhost:8080**

## 📌 Endpoint API
| Method | Endpoint           | Deskripsi                  |
|--------|-------------------|----------------------------|
| GET    | `/employees`       | Mendapatkan semua karyawan |
| GET    | `/employees/{id}`  | Mendapatkan karyawan by ID |
| POST   | `/employees`       | Menambahkan karyawan baru  |
| PUT    | `/employees/{id}`  | Memperbarui data karyawan  |
| DELETE | `/employees/{id}`  | Menghapus karyawan         |

## 📦 Contoh Payload
### 🔹 **POST /employees**
```json
{
  "name": "John Doe",
  "age": 30,
  "department": "IT"
}
```

### 🔹 **Response (Success)**
```json
{
  "status": "success",
  "message": "User added with ID: 1",
  "data": 1
}
```

## 🔥 Fitur Mendatang
- Middleware untuk logging
- Integrasi dengan database lain (PostgreSQL, MySQL)
- Unit testing dengan ScalaTest

## 📜 Lisensi
MIT License - Feel free to use and modify! 🚀

