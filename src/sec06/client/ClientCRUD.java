package sec06.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ClientCRUD {

    public static ClientDTO getClientInfo(Scanner sc) { //회원 정보 등록
        ClientDTO dto = null;
        IClientDAO idao = null;
        String clientNo = "";
        try {
            idao = new ClientDAO();
            System.out.println("회원 정보 등록");

            while (true) {
                System.out.print("회원번호 입력 : ");
                clientNo = sc.nextLine();
                boolean res = idao.searchClientNo(clientNo);
                if (!res) {
                    System.out.println("기존에 있는 회원번호입니다. 다시 입력하세요.");
                } else {
                    break;
                }
            }

            System.out.print("회원명 입력 : ");
            String clientName = sc.nextLine();

            System.out.print("전화번호 입력 : ");
            String clientPhone = sc.nextLine();

            System.out.print("주소 입력 : ");
            String clientAddress = sc.nextLine();

            System.out.print("생년월일 입력 : ");
            String clientBirthday = sc.nextLine();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fm.parse(clientBirthday);

            System.out.print("취미 입력 : ");
            String clientHobby = sc.nextLine();

            System.out.print("성별 입력 : ");
            String clientGender = sc.nextLine();

            System.out.print("비밀번호 입력 : ");
            String clientPassword = sc.nextLine();

            dto = new ClientDTO(clientNo, clientName, clientPhone, clientAddress, date, clientHobby, clientGender, clientPassword);
        } catch (Exception e) {
            System.out.println("입력 오류");
            e.printStackTrace();
        }
        return dto;
    }

    public static ClientDTO getClientInfo(Scanner sc, String clientNo) { //회원 정보 수정
        ClientDTO dto = null;
        try {
            System.out.println(clientNo + " 회원 정보 수정");
            System.out.print("회원명 입력 : ");
            String clientName = sc.nextLine();

            System.out.print("전화번호 입력 : ");
            String clientPhone = sc.nextLine();

            System.out.print("주소 입력 : ");
            String clientAddress = sc.nextLine();

            System.out.print("생년월일 입력 : ");
            String clientBirthday = sc.nextLine();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fm.parse(clientBirthday);

            System.out.print("취미 입력 : ");
            String clientHobby = sc.nextLine();

            System.out.print("성별 입력 : ");
            String clientGender = sc.nextLine();

            System.out.print("비밀번호 입력 : ");
            String clientPassword = sc.nextLine();

            dto = new ClientDTO(clientNo, clientName, clientPhone, clientAddress, date, clientHobby, clientGender, clientPassword);
        } catch (Exception e) {
            System.out.println("입력 오류");
            e.printStackTrace();
        }
        return dto;
    }

    public static void writeClientInfo(ClientDTO dto) { //회원 정보 조회
        System.out.println("----- 회원 정보 조회 ------");
        System.out.format("%-10s\t %-10s\t %-14s %-20s \t%12s %-10s %-6s %-10s\n",
                "회원번호", "회원명", "전화번호", "주소", "생년월일", "취미", "성별", "비밀번호");

        String clientNo = dto.getClientNo();
        String clientName = dto.getClientName();
        String clientPhone = dto.getClientPhone();
        String clientAddress = dto.getClientAddress();
        Date clientBirthday = dto.getClientBirth();
        String clientHobby = dto.getClientHobby();
        String clientGender = dto.getClientGender();

        System.out.format("%-10s\t %-10s\t %-14s %-20s \t%12s %-10s %-6s %s\n",
                clientNo, clientName, clientPhone, clientAddress, clientBirthday, clientHobby, clientGender, "****");
    }

    public static void writeClientInfo(ArrayList<ClientDTO> clientList) { //회원 전체 조회
        System.out.println("----- 전체 회원 정보 조회 ------");
        System.out.format("%-10s\t %-10s\t %-14s %-20s \t%12s %-10s %-6s %-10s\n",
                "회원번호", "회원명", "전화번호", "주소", "생년월일", "취미", "성별", "비밀번호");

        for (ClientDTO dto : clientList) {
            String clientNo = dto.getClientNo();
            String clientName = dto.getClientName();
            String clientPhone = dto.getClientPhone();
            String clientAddress = dto.getClientAddress();
            Date clientBirthday = dto.getClientBirth();
            String clientHobby = dto.getClientHobby();
            String clientGender = dto.getClientGender();

            System.out.format("%-10s\t %-10s\t %-14s %-20s \t%12s %-10s %-6s %s\n",
                    clientNo, clientName, clientPhone, clientAddress, clientBirthday, clientHobby, clientGender, "****");
        }
    }
}