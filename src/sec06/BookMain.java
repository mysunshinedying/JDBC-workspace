package sec06;

import sec06.client.ClientDAO;
import sec06.client.IClientDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class BookMain {
    public static void main(String[] args) {
        IBookDAO idao = new BookDAO();
        BookDAO dao = new BookDAO();
        BookDTO dto = null;
        Scanner sc = new Scanner(System.in);
        ArrayList<BookDTO> bookList = null;
        int menuSelected;
        String bookNo = "";

        IClientDAO clientDao = new ClientDAO();
        boolean login = false;
        //로그인 여부만 알면 되기에 boolean 타입으로 반환받도록 함
        //login 처리부분은 clientDAO에 있습니다.

        try {
            System.out.println("****** 회원 로그인 ******");
            do {
                System.out.print("회원번호 입력 : ");
                String clientNo = sc.nextLine();
                System.out.print("비밀번호 입력 : ");
                String clientPassword = sc.nextLine();

                login = clientDao.login(clientNo, clientPassword);
                if (login) {
                    System.out.println("로그인되었습니다.");
                    break;
                } else {
                    System.out.println("회원번호 또는 비밀번호가 올바르지 않습니다. 다시 입력하세요.");
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            System.out.println("*************************************");
            System.out.println("    도서 관리 프로그램");
            System.out.println("*************************************");
            System.out.println("    다음 메뉴에서 선택");
            System.out.println("*************************************");
            do {
                System.out.println("1. 도서 등록\t2. 전체 도서 조회\t3. 도서 정보 조회\t4. 도서 삭제\t"
                        + "5. 도서 정보 수정\t6. 종료");
                System.out.print("메뉴 선택(숫자입력) : ");
                menuSelected = sc.nextInt();
                //메뉴는 다섯 개인데 전체 도서 조회일까 단권일까 싶어서 그냥 둘 다 넣었습니다!!

                sc.nextLine();

                switch (menuSelected) {

                    // 1. 도서 정보 입력 - BookCRUD 활용
                    case 1 :
                        idao.insertBook(BookCRUD.getBookInfo(sc));
                        break;

                    // 2. 전체 도서 정보 조회
                    case 2 :
                        bookList = dao.getAllBooks();
                        BookCRUD.writeBookInfo(bookList);
                        break;

                    // 3. 한 권 도서 정보 조회
                    case 3 :
                        System.out.println("도서번호를 입력하세요");
                        bookNo = sc.nextLine();

                        dto = dao.detailBook(bookNo);

                        if(dto != null)
                            BookCRUD.writeBookInfo(dto);

                        break;

                    // 4. 한 권 도서 정보 삭제
                    case 4 :
                        System.out.println("도서번호를 입력하세요");
                        bookNo = sc.nextLine();
                        idao.deleteBook(bookNo);

                        break;

                    // 5. 도서 정보 수정
                    case 5 :

                        System.out.println("도서번호를 입력하세요");
                        bookNo = sc.nextLine();

                        dto = dao.detailBook(bookNo);

                        if(dto != null) {
                            BookCRUD.writeBookInfo(dto);
                            idao.updateBook(BookCRUD.getBookInfo(sc, bookNo));
                        }

                        break;

                    case 6 : System.out.println("프로그램을 종료합니다..."); break;
                    default : System.out.println("다시 입력하세요.");
                }
            } while(menuSelected != 6);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}