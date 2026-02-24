package sec06;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BookCRUD {
    public static BookDTO getBookInfo(Scanner sc) {
        // 책 정보 입력받아 DTO 저장 후 반환
        BookDTO dto = null;
        IBookDAO idao = null;
        String bookNo = "";

        try {
            idao = new BookDAO();
            System.out.println("**********************");
            System.out.println("    도서 정보 등록");
            System.out.println("**********************");

            while(true) {
                System.out.print("도서 번호 입력 : ");
                bookNo = sc.nextLine();
                boolean res = idao.searchBookNO(bookNo);
                if(!res) { System.out.println("기존에 있는 책입니다. 다시 입력하세요."); }
                else { break; }
            }

            System.out.print("도서명 입력 : ");
            String bookName = sc.nextLine();

            System.out.print("저자 입력 : ");
            String bookAuthor = sc.nextLine();

            System.out.print("가격 입력 : ");
            int bookPrice = sc.nextInt();

            sc.nextLine();

            System.out.print("발행일 입력 : ");
            String bookDate = sc.nextLine();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fm.parse(bookDate);

            System.out.print("재고 입력 : ");
            int bookStock = sc.nextInt();

            sc.nextLine();

            System.out.print("출판사 번호 입력 : ");
            String pubNo = sc.nextLine();

            dto = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, date, bookStock, pubNo);
        } catch (Exception e) {
            System.out.println("입력 오류");
            e.printStackTrace();
        }

        return dto;
    }

    public static BookDTO getBookInfo(Scanner sc, String bookNo) {
        BookDTO dto = null;
        try {
            System.out.println("**********************");
            System.out.println("    " + bookNo + "도서 정보 수정");
            System.out.println("**********************");
            System.out.print("도서 명 입력 : ");
            String bookName = sc.nextLine();

            System.out.print("저자 입력 : ");
            String bookAuthor = sc.nextLine();

            System.out.print("가격 입력 : ");
            int bookPrice = sc.nextInt();
            sc.nextLine();

            System.out.print("발행일 입력 : ");
            String bookDateStr = sc.nextLine();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fm.parse(bookDateStr);

            System.out.print("재고 입력 : ");
            int bookStock = sc.nextInt();
            sc.nextLine();

            System.out.print("출판사 번호 입력 : ");
            String pubNo = sc.nextLine();

            dto = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, date, bookStock, pubNo);
        } catch(Exception e) {
            System.out.println("입력 오류");
            e.printStackTrace();
        }
        return dto;
    }

    public static void writeBookInfo(BookDTO dto){
        //도서 정보 조회
        System.out.println("******** 도서 정보 조회 ********");
        System.out.format("%-10s\t %-20s\t %-10s %6s %13s \t%3s %10s\n",
                "번호", "제목", "저자", "가격", "발행연도", "재고", "출판사 번호");

        String bookNo = dto.getBookNo();
        String bookName = dto.getBookName();
        String bookAuthor = dto.getBookAuthor();
        int bookPrice = dto.getBookPrice();
        Date bookDate = dto.getBookDate();
        int bookStock = dto.getBookStock();
        String pubNo = dto.getPubNo();

        System.out.format("%-10s\t %-20s\t %-10s %6d %13s \t%3d %10s\n",
                bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
    }

    public static void writeBookInfo(ArrayList<BookDTO> bookList) {
        //전체 도서 정보 조회
        System.out.println("******** 전체 도서 정보 조회 ********");
        System.out.format("%-10s\t %-20s\t %-10s %6s %13s \t%3s %10s\n",
                "번호", "제목", "저자", "가격", "발행연도", "재고", "출판사 번호");
        for(BookDTO dto : bookList) {
            String bookNo = dto.getBookNo();
            String bookName = dto.getBookName();
            String bookAuthor = dto.getBookAuthor();
            int bookPrice = dto.getBookPrice();
            Date bookDate = dto.getBookDate();
            int bookStock = dto.getBookStock();
            String pubNo = dto.getPubNo();

            System.out.format("%-10s\t %-20s\t %-10s %6d %13s \t%3d %10s\n",
                    bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
        }
    }
}
