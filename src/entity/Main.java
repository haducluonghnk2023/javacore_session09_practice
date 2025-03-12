package entity;

import java.util.*;

public class Main {
    private static final int MAX_BOOKS = 100;
    private static Book[] books = new Book[MAX_BOOKS];
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n**************************** MENU *************************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Tính lợi nhuận của các sách");
            System.out.println("4. Cập nhật sách");
            System.out.println("5. Xóa sách");
            System.out.println("6. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("7. Tìm kiếm sách theo tác giả");
            System.out.println("8. Tìm kiếm sách theo khoảng giá bán");
            System.out.println("9. Thống kê số lượng sách theo mỗi tác giả");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    addNewBook(sc);
                    break;
                case 3:
                    calculateInterest();
                    break;
                case 4:
                    updateBook(sc);
                    break;
                case 5:
                    deleteBook(sc);
                    break;
                case 6:
                    sortByInterest();
                    break;
                case 7:
                    searchByAuthor(sc);
                    break;
                case 8:
                    searchByPriceRange(sc);
                    break;
                case 9:
                    statisticByAuthor();
                    break;
                case 10:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void displayBooks() {
        if (count == 0) {
            System.out.println("Danh sách sách trống.");
            return;
        }
        System.out.printf("%-10s%-20s%-15s%-15s%-20s%-20s%-15s%-10s\n",
                "Mã sách", "Tên sách", "Giá nhập", "Giá bán", "Tiêu đề", "Tác giả", "Lợi nhuận", "Năm");
        for (int i = 0; i < count; i++) {
            books[i].displayData();
        }
    }
    private static void addNewBook(Scanner scanner) {
        if (count >= MAX_BOOKS) {
            System.out.println("Đã đạt số lượng sách tối đa.");
            return;
        }
        Book book = new Book();
        book.inputData(scanner);
        books[count++] = book;
        System.out.println("Đã thêm sách thành công.");
    }
    private static void calculateInterest() {
        for (int i = 0; i < count; i++) {
            books[i].calInterest();
        }
        System.out.println("Đã tính lợi nhuận cho tất cả các sách.");
    }
    private static void updateBook(Scanner scanner) {
        System.out.print("Nhập mã sách cần cập nhật: ");
        String bookId = scanner.nextLine();
        for (int i = 0; i < count; i++) {
            if (books[i].getBookId().equals(bookId)) {
                Book book = books[i]; // Lấy đối tượng sách cần cập nhật
                while (true) {
                    System.out.println("Chọn thuộc tính cần cập nhật:");
                    System.out.println("1. Tên sách");
                    System.out.println("2. Giá nhập");
                    System.out.println("3. Giá bán");
                    System.out.println("4. Tiêu đề");
                    System.out.println("5. Tác giả");
                    System.out.println("6. Năm xuất bản");
                    System.out.println("0. Hoàn tất cập nhật");
                    System.out.print("Lựa chọn: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Đọc dòng new line

                    switch (choice) {
                        case 1:
                            System.out.print("Nhập tên sách mới: ");
                            book.setBookName(scanner.nextLine());
                            break;
                        case 2:
                            System.out.print("Nhập giá nhập mới: ");
                            book.setImportPrice(Double.parseDouble(scanner.nextLine()));
                            break;
                        case 3:
                            System.out.print("Nhập giá bán mới: ");
                            book.setExportPrice(Double.parseDouble(scanner.nextLine()));
                            break;
                        case 4:
                            System.out.print("Nhập tiêu đề mới: ");
                            book.setTitle(scanner.nextLine());
                            break;
                        case 5:
                            System.out.print("Nhập tác giả mới: ");
                            book.setAuthor(scanner.nextLine());
                            break;
                        case 6:
                            System.out.print("Nhập năm xuất bản mới: ");
                            book.setYear(Integer.parseInt(scanner.nextLine()));
                            break;
                        case 0:
                            System.out.println("Đã cập nhật sách thành công.");
                            return;
                        default:
                            System.out.println("Lựa chọn không hợp lệ.");
                    }
                }
            }
        }
        System.out.println("Không tìm thấy sách với mã " + bookId);
    }
    private static void deleteBook(Scanner scanner) {
        System.out.print("Nhập mã sách cần xóa: ");
        String bookId = scanner.nextLine();
        for (int i = 0; i < count; i++) {
            if (books[i].getBookId().equals(bookId)) {
                // Dịch chuyển các phần tử phía sau lên 1 vị trí
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[count - 1] = null; // Gán null cho phần tử cuối
                count--;
                System.out.println("Đã xóa sách thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy sách với mã " + bookId);
    }
    private static void sortByInterest() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (books[j].getInterest() > books[j + 1].getInterest()) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp sách theo lợi nhuận tăng dần.");
    }
    private static void searchByAuthor(Scanner scanner) {
        System.out.print("Nhập tên tác giả cần tìm (hoặc một phần tên): ");
        String author = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().toLowerCase().contains(author.toLowerCase())) {
                books[i].displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sách của tác giả có tên chứa '" + author + "'");
        }
    }
    private static void searchByPriceRange(Scanner scanner) {
        System.out.print("Nhập giá bán tối thiểu: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Nhập giá bán tối đa: ");
        double maxPrice = scanner.nextDouble();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].getExportPrice() >= minPrice && books[i].getExportPrice() <= maxPrice) {
                books[i].displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sách trong khoảng giá này.");
        }
    }
    private static void statisticByAuthor() {
        String[] authors = new String[count]; // Mảng để lưu trữ tên tác giả
        int[] counts = new int[count]; // Mảng để lưu trữ số lượng sách của mỗi tác giả
        int authorCount = 0; // Số lượng tác giả duy nhất

        for (int i = 0; i < count; i++) {
            String currentAuthor = books[i].getAuthor();
            boolean found = false;
            for (int j = 0; j < authorCount; j++) {
                if (authors[j].equals(currentAuthor)) {
                    counts[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                authors[authorCount] = currentAuthor;
                counts[authorCount] = 1;
                authorCount++;
            }
        }

        for (int i = 0; i < authorCount; i++) {
            System.out.println("Tác giả " + authors[i] + ": " + counts[i] + " sách");
        }
    }
}

