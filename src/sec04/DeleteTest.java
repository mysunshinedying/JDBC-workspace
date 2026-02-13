package sec04;

import sec03.DBConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DeleteTest {
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

        try{
           con = dbCon.getConnection();
           sql = "DELETE book WHERE bookNo = ?";
           pstmt = con.prepareStatement(sql);

           System.out.print("삭제할 도서 번호 입력");
           bookNo = sc.nextLine();

           pstmt.setString(1, bookNo);

           pstmt.executeUpdate();
           System.out.println("도서 정보 삭제 성공");

            //수정된 도서 확인 : select 절
            sql = "SELECT * FROM book WHERE bookNo = ?";
            PreparedStatement pstmtSel = con.prepareStatement(sql);
            pstmtSel.setString(1, bookNo);
            rs = pstmtSel.executeQuery();

            System.out.println("-------------- 전체 도서 정보 조회 --------------");
            System.out.println("도서번호 \t\t 도서명 \t\t\t\t\t 저자 \t\t 가격 \t 발행일 \t\t재고 \t출판사번호");

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
            System.out.println("도서 정보 삭제 실패");
            e.printStackTrace();
        }
    }
}
