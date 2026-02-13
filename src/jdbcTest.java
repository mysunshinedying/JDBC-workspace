import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcTest {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("설치 완료");
    }
}
