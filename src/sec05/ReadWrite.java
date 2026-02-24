package sec05;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ReadWrite {
    //개발 코드(메인, 컨트롤러)에서 필요한 입출력 기능을 구현하는 클래스
    //static으로 구성
    // 1. 학생 한명 정보 입력(insert/update) 받아 DTO에 저장 후 반환
    public static StudentDTO getStdInfo(Scanner sc) {
        StudentDTO dto = null;
        IStudentDAO idao = null;
        String stdNo = "";
        try {
            idao = new StudentDAO();
            System.out.println("학생 정보 등록");

            // 학생 데이터 입력
            while(true) {
                System.out.print("학번 입력 : ");
                stdNo = sc.nextLine();
                boolean res = idao.searchStudentNO(stdNo);
                if(!res) { System.out.println("기존에 있는 학번입니다. 다시 입력하세요."); }
                else { break; }
            }


                System.out.print("성명 입력 : ");
                String stdName = sc.nextLine();

                System.out.print("학년 입력 : ");
                int stdYear = sc.nextInt(); // 마지막 문자인 enter가 처리되지 않아 다음 입력으로 연결

                sc.nextLine(); //위에서 처리되지 않은 enter처리

                System.out.print("주소 입력 : ");
                String stdAddress = sc.nextLine();

                System.out.print("생년월일 입력 : ");
                String stdBirthday = sc.nextLine();
                // 입력된 문자열을 Date Type으로 변환 후 사용(생성자 전달 시 오류 발생 가능)
                SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
                Date date = fm.parse(stdBirthday); //String -> date 타입 변경 메서드

                System.out.print("학과번호 입력 : ");
                String dptNo = sc.nextLine();

                dto = new StudentDTO(stdNo, stdName, stdYear, stdAddress, date, dptNo);

        } catch(Exception e) {
            System.out.println("입력 오류");
            e.printStackTrace();
        }
        return dto;
    }

    // update용, 학번은 입력받지 않음
    public static StudentDTO getStdInfo(Scanner sc, String stdNo) {
        StudentDTO dto = null;
        try {
            // 학생 데이터 입력
            System.out.println(stdNo + " 학생 정보 수정");
            System.out.print("성명 입력 : ");
            String stdName = sc.nextLine();

            System.out.print("학년 입력 : ");
            int stdYear = sc.nextInt(); // 마지막 문자인 enter가 처리되지 않아 다음 입력으로 연결

            sc.nextLine(); //위에서 처리되지 않은 enter처리

            System.out.print("주소 입력 : ");
            String stdAddress = sc.nextLine();

            System.out.print("생년월일 입력 : ");
            String stdBirthday = sc.nextLine();
            // 입력된 문자열을 Date Type으로 변환 후 사용(생성자 전달 시 오류 발생 가능)
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fm.parse(stdBirthday); //String -> date 타입 변경 메서드

            System.out.print("학과번호 입력 : ");
            String dptNo = sc.nextLine();

            dto = new StudentDTO(stdNo, stdName, stdYear, stdAddress, date, dptNo);
        } catch(Exception e) {
            System.out.println("입력 오류");
            e.printStackTrace();
        }
        return dto;
    }

    // 2. 학생 한명 정보를 출력
    public static void writeStdInfo(StudentDTO dto) {
        //제목 출력
        System.out.println("----- 학생 정보 조회 ------");
        System.out.format("%-10s\t %-10s\t %-4s %-20s \t%13s %5s\n",
                "학번", "성명", "학년", "주소", "출생일", "학과번호");

        //정보 추출
        String stdNo = dto.getStdNo();
        String stdName = dto.getStdName();
        int stdYear = dto.getStdYear();
        String stdAddress = dto.getStdAddress();
        Date stdBirthday = dto.getStdBirth();
        String dptNo = dto.getDptNo();

        //출력
        System.out.format("%-10s\t %-10s\t %-4d %-20s \t%13s %5s\n",
                stdNo, stdName, stdYear, stdAddress, stdBirthday, dptNo);

    }

    // 3. 학생 여러 명 정보 출력
    public static void writeStdInfo(ArrayList<StudentDTO> stdList) {
        //제목 출력
        System.out.println("----- 전체 학생 정보 조회 ------");
        System.out.format("%-10s\t %-10s\t %-4s %-20s \t%13s %5s\n",
                "학번", "성명", "학년", "주소", "출생일", "학과번호");

        for(StudentDTO dto : stdList) {
            //정보 추출
            String stdNo = dto.getStdNo();
            String stdName = dto.getStdName();
            int stdYear = dto.getStdYear();
            String stdAddress = dto.getStdAddress();
            Date stdBirthday = dto.getStdBirth();
            String dptNo = dto.getDptNo();

            //출력
            System.out.format("%-10s\t %-10s\t %-4d %-20s \t%13s %5s\n",
                    stdNo, stdName, stdYear, stdAddress, stdBirthday, dptNo);
        }
    }
}
