package sec06;

/*
 * db 연결 및 종료 관리
 * getConnection() : db 연결(session) 관리
 */

import java.sql.*;

public class DBConnect {
    // 1. CONNECTION 객체만 필요 시 사용
    public static Connection getConnection(){
        Connection con = null;
        //예외처리 필요
        try {

            String url = "jdbc:oracle:thin:@localhost:29889:xe";
            String user = "C##SQL_USER";
            String pwd = "1234";

            con = DriverManager.getConnection(url, user, pwd);

            if(con != null) {
                System.out.println("db 연결 성공");
            } else {
                System.out.println("db 연결 실패");
            }
            return con;

        } catch (Exception e){
            e.printStackTrace();
            return null; //연결 실패 시 오류 출력 후 null 리턴
        }
    }
    // 자원 close 메서드
    // 외부 자원 사용 메서드: 반드시 예외 처리 필요
    // 2. 3개 자원 close

    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
                rs = null;
            }
            if(pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if(con != null) {
                con.close();
                con = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. 2개 자원 close
    public static void close(Connection con, PreparedStatement pstmt) {
        try {
            if(pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if(con != null) {
                con.close();
                con = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // 3-1. 2개 자원 close
    public static void close(PreparedStatement pstmt, ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
                rs = null;
            }
            if(pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. 1개 자원 close
    public static void close(Connection con) {
        try {
            if(con != null) {
                con.close();
                con = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. 1개 자원 close
    public static void close(PreparedStatement pstmt) {
        try {
            if(pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
