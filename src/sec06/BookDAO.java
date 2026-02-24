package sec06;


import java.sql.*;
import java.util.ArrayList;

public class BookDAO implements IBookDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    BookDTO book = null;
    ArrayList<BookDTO> bookList = null;

    public BookDAO() {
        con = DBConnect.getConnection();
    }

    @Override
    public void insertBook(BookDTO dto) { //도서 정보 등록
        try {
            String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getBookNo());
            pstmt.setString(2, dto.getBookName());
            pstmt.setString(3, dto.getBookAuthor());
            pstmt.setInt(4, dto.getBookPrice());
            pstmt.setDate(5, new java.sql.Date(dto.getBookDate().getTime()));
            pstmt.setInt(6, dto.getBookStock());
            pstmt.setString(7, dto.getPubNo());
            int result = pstmt.executeUpdate();
            if (result > 0) System.out.println("성공: 도서 정보가 등록 되었습니다.");
        } catch (SQLException e) {
            System.out.println("실패: 도서 정보 등록에 실패했습니다.");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public ArrayList<BookDTO> getAllBooks() { //전체 조회
        bookList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book ORDER BY bookNo";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String bookNo = rs.getString(1);
                String bookName = rs.getString(2);
                String bookAuthor = rs.getString(3);
                int bookPrice = rs.getInt(4);
                Date bookDate = rs.getDate(5);
                int bookStock = rs.getInt(6);
                String pubNo = rs.getString(7);

                book = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return bookList;
    }

    @Override
    public BookDTO detailBook(String bookNo) { //한 권 조회
        try {
        String sql = "SELECT * FROM book WHERE bookNo = ?";

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, bookNo);

        rs = pstmt.executeQuery();


        if(rs.next()) {
            bookNo = rs.getString(1);
            String bookName = rs.getString(2);
            String bookAuthor = rs.getString(3);
            int bookPrice = rs.getInt(4);
            Date bookDate = rs.getDate(5);
            int bookStock = rs.getInt(6);
            String pubNo = rs.getString(7);

            book = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);

        } else {
            System.out.println("도서번호에 해당하는 정보가 없습니다.");
            book = null;
        }

    } catch (Exception e) {
        System.out.println("오류 발생");
        e.printStackTrace();
    } finally {
        DBConnect.close(pstmt, rs);
    }

        return book;

    }

    @Override
    public void deleteBook(String bookNo) {
        String sql = "DELETE FROM book WHERE bookNo = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookNo);

            int result = pstmt.executeUpdate();

            if(result > 0) {
                System.out.println("도서번호 " + bookNo + " 도서 정보 삭제 성공");
            } else {
                System.out.println("도서번호 " + bookNo + " 도서 정보가 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public void updateBook(BookDTO dto) { //도서 정보 업데이트
        String sql = "UPDATE book SET bookName = ?, bookAuthor = ?, bookPrice = ?, bookDate = ?, bookStock = ?, pubNo = ? WHERE bookNo = ?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, dto.getBookName());
            pstmt.setString(2, dto.getBookAuthor());
            pstmt.setInt(3, dto.getBookPrice());
            pstmt.setDate(4, new java.sql.Date(dto.getBookDate().getTime()));
            pstmt.setInt(5, dto.getBookStock());
            pstmt.setString(6, dto.getPubNo());
            pstmt.setString(7, dto.getBookNo());

            int result = pstmt.executeUpdate();
            if(result > 0) {
                System.out.println("도서번호 " + dto.getBookNo() + " 도서의 정보 수정 완료");
            } else {
                System.out.println("도서번호 " + dto.getBookNo() + " 도서의 정보가 없음");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public boolean searchBookNO(String bookNo) { //이미 있는지 확인
        String sql = "SELECT * FROM book WHERE bookNo = ?";
        boolean res = true;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookNo);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                res = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return res;
    }
}
