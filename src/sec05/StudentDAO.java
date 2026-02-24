package sec05;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO implements IStudentDAO {
    //인터페이스 구현 클래스
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //DBConnect dbCon = null;
    StudentDTO std = null;
    ArrayList<StudentDTO> stdList = null;

    // 생성자에서 DB 연결
    public StudentDAO() {
        //dbCon = new DBConnect();
        //con = dbCon.getConnection();
        con = DBConnect.getConnection();
    }

    @Override
    public void insertStudent(StudentDTO dto) {
        //학생 정보 입력
        try {
            String sql = "insert into student values(?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);

            //바인딩 변수 설정
            pstmt.setString(1, dto.getStdNo());
            pstmt.setString(2, dto.getStdName());
            pstmt.setInt(3, dto.getStdYear());
            pstmt.setString(4, dto.getStdAddress());
            //pstmt.setDate(5, dto.getStdBirth()); //dto.getStdBirth() util.date 반환, pstmt.setDate() sql.date 요구
            pstmt.setDate(5, new java.sql.Date(dto.getStdBirth().getTime()));
            //pstmt.setString(5, dto.getStdBirth());
            pstmt.setString(6, dto.getDptNo());
            // 쿼리문 실행(영향을 받은 행의 수 받아와서 변수에 저장)
            int result = pstmt.executeUpdate();

            if(result > 0) {
                System.out.println("학생 등록 성공!");
            }

        } catch(SQLException e){
            System.out.println("학생 등록 실패!");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);

        }

    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() {
        //전체 학생 정보 조회
        stdList = new ArrayList<StudentDTO>();

        try {
            String sql = "SELECT * FROM student ORDER BY stdNo";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String stdNo = rs.getString(1);
                String stdName = rs.getString(2);
                int stdYear = rs.getInt(3);
                String stdAddress = rs.getString(4);
                Date stdBirthday = rs.getDate(5);
                String dptNo = rs.getString(6);

                std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBirthday, dptNo);
                stdList.add(std);

            }

        } catch(SQLException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return stdList;
    }

    @Override
    public StudentDTO detailStudent(String stdNo) {
        //한명 학생정보 조회
        try {
            String sql = "SELECT * FROM student WHERE stdNo = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stdNo);

            // 쿼리 실행 후 결과 반환
            rs = pstmt.executeQuery(); //sql 전달하면 오류 발생

            // 받은 결과를 dto에 추가해서 반환(기본키 기준으로 검색 진행 = 반환결과 레코드는 0이거나 1)
            if(rs.next()) {
                stdNo = rs.getString(1); //매개 변수 전달받은 값이나 rs 1번 컬럼 값이나 동일해야함
                String stdName = rs.getString(2);
                int stdYear = rs.getInt(3);
                String stdAddress = rs.getString(4);
                Date stdBirthday = rs.getDate(5);
                String dptNo = rs.getString(6);

                std = new StudentDTO(stdNo, stdName, stdYear, stdAddress, stdBirthday, dptNo);

            } else {
                System.out.println("학번에 해당하는 정보가 없습니다.");
                std = null;
            }

        } catch (Exception e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }

        return std;
    }

    @Override
    public void deleteStudent(String stdNo) {
        // 한 명의 학생 정보 삭제
        String sql = "DELETE FROM student WHERE stdNo = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stdNo);

            int result = pstmt.executeUpdate();

            if(result > 0) {
                System.out.println("학번 " + stdNo + " 학생 정보 삭제 성공");
            } else {
                System.out.println("학번 " + stdNo + " 학생 정보가 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public void updateStudent(StudentDTO dto) {
        // 모든 컬럼 업데이트 처리
        String sql = "UPDATE student SET stdName = ?, stdYear = ?,"
                    + "stdAddress = ?, stdBirth = ?, dptNo = ? WHERE stdNo = ?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, dto.getStdName());
            pstmt.setInt(2, dto.getStdYear());
            pstmt.setString(3, dto.getStdAddress());
            pstmt.setDate(4, new java.sql.Date(dto.getStdBirth().getTime()));
            pstmt.setString(5, dto.getDptNo());
            pstmt.setString(6, dto.getStdNo());

            int result = pstmt.executeUpdate();
            if(result > 0) {
               System.out.println("학번 " + dto.getStdNo() + " 학생의 정보 수정 완료");
            } else {
                System.out.println("학번 "+ dto.getStdNo() + " 학생의 정보가 없음");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }




    @Override
    public ArrayList<StudentDTO> searchStudent(String dptNo) {
        //같은 과 학생 검색
        stdList = new ArrayList<StudentDTO>();
        String sql = "SELECT * FROM student WHERE dptNo = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dptNo);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                do {
                    std = new StudentDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getString(6));
                    stdList.add(std);
                } while (rs.next());
            } else {
                System.out.println(dptNo + " 학과의 학생정보는 없습니다.");
            }

        } catch (Exception e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return stdList;
    }


    @Override
    public ArrayList<StudentDTO> searchStudentBydptName(String dptName) {
        //같은 과 학생 검색
        stdList = new ArrayList<StudentDTO>();
        String sql = "SELECT * FROM student WHERE dptNo = (SELECT dptNo FROM department WHERE dptName = ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dptName);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                do {
                    std = new StudentDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getString(6));
                    stdList.add(std);
                } while (rs.next());
            } else {
                System.out.println(dptName + " 학과의 학생정보는 없습니다.");
            }

        } catch (Exception e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return stdList;
    }

    @Override
    public boolean searchStudentNO(String stdNo) {
        // 정보 입력 시 중복 확인용
        String sql = "SELECT * FROM student WHERE stdNo = ?";
        boolean res = true;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stdNo);

            rs = pstmt.executeQuery();

            if(rs.next()) { //전달된 학번으로 검색된 레코드가 있다는 의미
                res = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return res;
    }
    // 인터페이스 구현 클래스
}
