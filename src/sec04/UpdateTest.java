package sec04;

import sec03.DBConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateTest {
    public static void main(String[] args) {
        // 필요 참조변수 선언 및 생성
        DBConnect dbCon = new DBConnect();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);

        String sql = null;
        String bookNo, bookTitle, bookAuthor, bookDate, pubNo;
        int bookPrice, bookStock;
        try {
            con = dbCon.getConnection();
            sql = "UPDATE book SET bookName = ?, bookAuthor = ?," +
                    "bookPrice = ?, bookDate = ?, bookStock = ?, pubNo = ? WHERE bookNo = ?";
            pstmt = con.prepareStatement(sql);

            //사용자로부터 데이터 입력을 받는다
            System.out.print("수정할 도서 번호 입력 : ");
            bookNo = sc.nextLine();

            System.out.print("도서 제목 입력 : ");
            bookTitle = sc.nextLine();

            System.out.print("도서 저자 입력 : ");
            bookAuthor = sc.nextLine();

            System.out.print("도서 가격 입력 : ");
            bookPrice = Integer.parseInt(sc.nextLine()); //사용자가 enter를 넣었을 때 enter 바로 앞 입력까지만 처리
            // 다음 입력으로 enter가 넘어감 -> nextLine()을 사용

            System.out.print("발행일 입력 : ");
            bookDate = sc.nextLine();

            System.out.print("도서 재고 입력 : ");
            bookStock = Integer.parseInt(sc.nextLine());

            System.out.print("출판사 번호 입력 : ");
            pubNo = sc.nextLine();

            pstmt.setString(1, bookTitle);
            pstmt.setString(2, bookAuthor);
            pstmt.setInt(3, bookPrice);
            pstmt.setString(4, bookDate);
            pstmt.setInt(5, bookStock);
            pstmt.setString(6, pubNo);
            pstmt.setString(7, bookNo);

            //질의 전달 및 실행
            int tmpResult = pstmt.executeUpdate();
            if(tmpResult == 1)
                System.out.println("도서정보 수정 성공");

            //등록된 값 확인 : select 절
            sql = "SELECT * FROM book WHERE bookNo = ?";
            PreparedStatement pstmtSel = con.prepareStatement(sql);
            pstmtSel.setString(1, bookNo);
            rs = pstmtSel.executeQuery();

            while(rs.next()) {
                // 현재 rs 참조하는 레코드의 각 컬럼의 값을 반환받아서 변수에 저장
                bookNo = rs.getString(1);
                bookTitle = rs.getString(2);
                bookAuthor = rs.getString(3);
                bookPrice = rs.getInt(4);
                Date bkDate = rs.getDate(5);
                bookStock = rs.getInt(6);
                pubNo = rs.getString(7);
                System.out.format("%-10s\t %-20s\t %-10s %6d %13s \t%3d %10s\n",
                        bookNo, bookTitle, bookAuthor, bookPrice, bkDate, bookStock, pubNo);
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("도서 정보 수정 실패");
            e.printStackTrace();
        }
    }
}
