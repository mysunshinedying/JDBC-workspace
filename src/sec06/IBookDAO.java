package sec06;

import java.util.ArrayList;

public interface IBookDAO {

    public void insertBook(BookDTO dto);

    public ArrayList<BookDTO> getAllBooks();

    public BookDTO detailBook(String bookNo);

    public void deleteBook(String bookNo);

    public void updateBook(BookDTO dto);

    //로그인 체크 여부
    public boolean searchBookNO(String bookNo);

}
