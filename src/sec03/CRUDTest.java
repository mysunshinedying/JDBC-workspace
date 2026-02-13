package sec03;

import sec02.DBConnect;

import java.sql.Connection;
import java.sql.Statement;

public class CRUDTest {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        DBConnect dbCon = new DBConnect();

        try {
            con = dbCon.getConnection();
            stmt = con.createStatement();

            // insert 구문 : 정해진 값을 이용해서 질의어 작성
            //String sql = "INSERT INTO book VALUES('100', '자바', '김바로', 10000, '2021-03-01', 5, '2')";
            // insert 등의 query는 데이터 삽입 후 실행 종료
            // insert/update/delete 질의 : executeUpdate(), select : executeQuery()
            // statement 객체는 매번 형식검사(전처리)를 진행
            //int tmpResult = stmt.executeUpdate(sql);
            //System.out.println(tmpResult);

            /*
            if(tmpResult == 1)
                System.out.println("인서트 성공");
            */

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
