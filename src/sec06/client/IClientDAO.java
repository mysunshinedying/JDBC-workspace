package sec06.client;

import java.util.ArrayList;

public interface IClientDAO {


    public void insertClient(ClientDTO dto);

    public ArrayList<ClientDTO> getAllClients();

    public ClientDTO detailClient(String clientNo);

    public void deleteClient(String clientNo);

    public void updateClient(ClientDTO dto);

    public boolean searchClientNo(String clientNo);

    public boolean login(String clientNo, String clientPassword);
}
