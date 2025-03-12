package entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private double importPrice;
    private double exportPrice;
    private String title;
    private String author;
    private double interest;
    private int year;

    public Book() {}

    public Book(String bookId, String bookName, double importPrice, double exportPrice, String title, String author, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(Scanner scanner) {
        // Nhập bookId
        while (true) {
            System.out.print("Nhập mã sách (BXXXX): ");
            String id = scanner.nextLine();
            if (id.matches("B\\w{4}")) {
                this.bookId = id;
                break;
            } else {
                System.out.println("Mã sách không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập bookName
        while (true) {
            System.out.print("Nhập tên sách (6-100 ký tự): ");
            String name = scanner.nextLine();
            if (name.length() >= 6 && name.length() <= 100) {
                this.bookName = name;
                break;
            } else {
                System.out.println("Tên sách không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập importPrice
        while (true) {
            System.out.print("Nhập giá nhập: ");
            try {
                double price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    this.importPrice = price;
                    break;
                } else {
                    System.out.println("Giá nhập phải lớn hơn 0. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá nhập không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập exportPrice
        while (true) {
            System.out.print("Nhập giá bán (lớn hơn 10% giá nhập): ");
            try {
                double price = Double.parseDouble(scanner.nextLine());
                if (price > this.importPrice * 1.1) {
                    this.exportPrice = price;
                    break;
                } else {
                    System.out.println("Giá bán phải lớn hơn 10% giá nhập. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá bán không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập title
        System.out.print("Nhập tiêu đề sách: ");
        this.title = scanner.nextLine();

        // Nhập author
        System.out.print("Nhập tác giả: ");
        this.author = scanner.nextLine();

        // Nhập year
        while (true) {
            System.out.print("Nhập năm xuất bản (sau năm 1970): ");
            try {
                int year = Integer.parseInt(scanner.nextLine());
                if (year > 1970) {
                    this.year = year;
                    break;
                } else {
                    System.out.println("Năm xuất bản phải sau năm 1970. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Năm xuất bản không hợp lệ. Vui lòng nhập lại.");
            }
        }

    }

    public void displayData() {
        System.out.printf("%-10s%-20s%-15.2f%-15.2f%-20s%-20s%-15.2f%-10d\n",
                bookId, bookName, importPrice, exportPrice, title, author, interest, year);
    }

    public void calInterest() {
        this.interest = exportPrice - importPrice;
    }
}
