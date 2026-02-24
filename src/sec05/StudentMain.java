package sec05;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMain {
    public static void main(String[] args) {
        // 클래스 테스트용(controller)
        IStudentDAO idao = new StudentDAO();
        StudentDAO dao = new StudentDAO();
        StudentDTO dto = null;
        Scanner sc = new Scanner(System.in);
        ArrayList<StudentDTO> stdList = null;
        int menuSelected;
        String stdNo = "";

        try {

            System.out.println("학생 조회 프로그램");
            System.out.println("******** 메뉴를 선택하세요 *********");
            do {
                System.out.println("--------------------------------------------");
                System.out.println("1. 학생 등록\t2. 전체 학생 조회\t3. 학생 정보 조회\t4.학생 삭제\t"
                + "5. 학생 정보 수정\t6. 학과 등록 학생 조회\t7. 학과 등록 학생 조회\t8.종료");
                System.out.print("메뉴 선택(숫자입력) : ");
                menuSelected = sc.nextInt();

                sc.nextLine();

                switch (menuSelected) {

                    // 1. 학생 정보 입력 - 사용자로부터 입력받은 ReadWrite class 활용
                    case 1 :
                    idao.insertStudent(ReadWrite.getStdInfo(sc));
                    break;

                    // 2. 전체 학생 정보 조회
                    case 2 :
                    stdList = dao.getAllStudent();
                    ReadWrite.writeStdInfo(stdList);
                    break;

                    //ReadWrite.writeStdInfo(dao.getAllStudent());


                    // 3. 한 명 학생 정보 조회
                    case 3 :
                    System.out.println("학번을 입력하세요");
                    stdNo = sc.nextLine();

                    dto = dao.detailStudent(stdNo);

                    if(dto != null)
                        ReadWrite.writeStdInfo(dto);

                    break;

                    // 4. 한명 학생 정보 삭제
                    case 4 :
                    System.out.println("학번을 입력하세요");
                    stdNo = sc.nextLine();
                    idao.deleteStudent(stdNo);

                    break;

                    // 5. 학생 정보 수정
                    // 수정할 학생 학번 입력받아 개별 학생 정보 출력
                    case 5 :

                    System.out.println("학번을 입력하세요");
                    stdNo = sc.nextLine();

                    dto = dao.detailStudent(stdNo);

                    if(dto != null) {
                        ReadWrite.writeStdInfo(dto);
                        idao.updateStudent(ReadWrite.getStdInfo(sc, stdNo)); // 학번 수정 방지

                    }

                    break;

                    // 6. 학과 번호 검색
                    case 6:
                        System.out.println("학과 번호를 입력하세요");
                        String dptNo = sc.nextLine();

                        stdList = idao.searchStudent(dptNo);

                        if (!stdList.isEmpty()) {
                            //혹은 stdList.size() > 0으로도 사용 가능
                            ReadWrite.writeStdInfo(stdList);

                        }
                        break;

                    // 7. 학과 명 검색
                    case 7:
                        System.out.println("학과 명을 입력하세요");
                        //dptNo = sc.nextLine();
                        String dptName = sc.nextLine();

                        // stdList = idao.searchStudent(dptNo);
                        stdList = idao.searchStudentBydptName(dptName);

                        if (!stdList.isEmpty()) {
                            //혹은 stdList.size() > 0으로도 사용 가능
                            ReadWrite.writeStdInfo(stdList);

                        }
                        break;
                    case 8 : System.out.println("프로그램을 종료합니다..."); break;
                    default : System.out.println("다시 입력하세요.");
                }
            } while(menuSelected != 8);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
