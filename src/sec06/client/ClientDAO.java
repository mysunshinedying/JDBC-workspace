package sec06.client;
import sec06.DBConnect;

import java.sql.*;
import java.util.ArrayList;

public class ClientDAO implements IClientDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ClientDTO client = null;
    ArrayList<ClientDTO> clientList = null;

    public ClientDAO() {
        con = DBConnect.getConnection();
    }

    @Override
    public void insertClient(ClientDTO dto) { //회원 정보 등록
        try {
            String sql = "INSERT INTO client VALUES(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getClientNo());
            pstmt.setString(2, dto.getClientName());
            pstmt.setString(3, dto.getClientPhone());
            pstmt.setString(4, dto.getClientAddress());
            pstmt.setDate(5, dto.getClientBirth() != null ? new java.sql.Date(dto.getClientBirth().getTime()) : null);
            pstmt.setString(6, dto.getClientHobby());
            pstmt.setString(7, dto.getClientGender());
            pstmt.setString(8, dto.getClientPassword());
            int result = pstmt.executeUpdate();
            if (result > 0) System.out.println("회원 등록 성공!");
        } catch (SQLException e) {
            System.out.println("회원 등록 실패!");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public ArrayList<ClientDTO> getAllClients() { //회원 전체 조회
        clientList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM client ORDER BY clientNo";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String clientNo = rs.getString(1);
                String clientName = rs.getString(2);
                String clientPhone = rs.getString(3);
                String clientAddress = rs.getString(4);
                Date clientBirth = rs.getDate(5);
                String clientHobby = rs.getString(6);
                String clientGender = rs.getString(7);
                String clientPassword = rs.getString(8);
                client = new ClientDTO(clientNo, clientName, clientPhone, clientAddress, clientBirth, clientHobby, clientGender, clientPassword);
                clientList.add(client);
            }
        } catch (SQLException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return clientList;
    }

    @Override
    public ClientDTO detailClient(String clientNo) { //회원 한명 조회
        client = null;
        try {
            String sql = "SELECT * FROM client WHERE clientNo = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clientNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                clientNo = rs.getString(1);
                String clientName = rs.getString(2);
                String clientPhone = rs.getString(3);
                String clientAddress = rs.getString(4);
                Date clientBirth = rs.getDate(5);
                String clientHobby = rs.getString(6);
                String clientGender = rs.getString(7);
                String clientPassword = rs.getString(8);
                client = new ClientDTO(clientNo, clientName, clientPhone, clientAddress, clientBirth, clientHobby, clientGender, clientPassword);
            } else {
                System.out.println("도서번호에 해당하는 정보가 없습니다.");
                client = null;
            }
        } catch (Exception e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return client;
    }

    @Override
    public void deleteClient(String clientNo) { //회원 정보 삭제
        String sql = "DELETE FROM client WHERE clientNo = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clientNo);
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("회원번호 " + clientNo + " 회원 정보 삭제 성공");
            } else {
                System.out.println("회원번호 " + clientNo + " 회원 정보가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public void updateClient(ClientDTO dto) { //회원 정보 수정
        String sql = "UPDATE client SET clientName = ?, clientPhone = ?, clientAddress = ?, clientBirth = ?, clientHobby = ?, clientGender = ?, clientPassword = ? WHERE clientNo = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getClientName());
            pstmt.setString(2, dto.getClientPhone());
            pstmt.setString(3, dto.getClientAddress());
            pstmt.setDate(4, dto.getClientBirth() != null ? new java.sql.Date(dto.getClientBirth().getTime()) : null);
            pstmt.setString(5, dto.getClientHobby());
            pstmt.setString(6, dto.getClientGender());
            pstmt.setString(7, dto.getClientPassword());
            pstmt.setString(8, dto.getClientNo());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("회원번호 " + dto.getClientNo() + " 회원의 정보 수정 완료");
            } else {
                System.out.println("회원번호 " + dto.getClientNo() + " 회원의 정보가 없음");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt);
        }
    }

    @Override
    public boolean searchClientNo(String clientNo) { //회원 번호 존재 여부 확인
        String sql = "SELECT * FROM client WHERE clientNo = ?";
        boolean res = true;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clientNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                res = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(pstmt, rs);
        }
        return res;
    }

    @Override
    public boolean login(String clientNo, String clientPassword) {
        // client를 입력받는다. 로그인 여부 확인
        try {
            String sql = "SELECT 1 FROM client WHERE clientNo = ? AND clientPassword = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clientNo);
            pstmt.setString(2, clientPassword);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.close(pstmt, rs);
        }
    }
}
