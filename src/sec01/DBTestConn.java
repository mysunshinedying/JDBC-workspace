package sec01;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTestConn {
    public static void main(String[] args) {
        // 접속 객체 선언
        Connection con = null;
        //DB 연결은 외부 자원을 사용 - 외부 시스템과 통신
        // 예외처리 필요
        try {
            //1.JDBC DRIVER 코드 : 런타임시 로드(자동 로드 : 생략가능)
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            //2.Connection 객체 인스턴스 생성 - db위치(주소:url), 계정명, 비밀번호
            //DriverManager를 통해 접속 시도 DriverManager.getConnection(url,user,pwd)
            // URL : jdbc 종류:@ip주소:포트:sid
            // "jdbc:oracle:thin:@localhost:29889:orcl" 예전버전

            String url = "jdbc:oracle:thin:@localhost:29889:xe";
            String user = "C##SQL_USER";
            String pwd = "1234";

            con = DriverManager.getConnection(url, user, pwd);

            if(con != null) {
                System.out.println("db 연결 성공");
            } else {
                System.out.println("db 연결 실패");
            }
        } catch (Exception e){
            e.printStackTrace(); //오류 발생할때까지의 과정을
        }
    }
}
