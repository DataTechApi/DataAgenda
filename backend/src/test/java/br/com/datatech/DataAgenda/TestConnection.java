package br.com.datatech.DataAgenda;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:oracle:thin:@dataagendadb_high?TNS_ADMIN=/media/jackson/DEV/DEV/ProjetoAPI/DataAgenda/backend/Wallet_dataagendadb";
        Connection conn = DriverManager.getConnection(url, "ADMIN", "Jtmj@@@200276");
        System.out.println("Conexão OK: " + conn);
        conn.close();
    }
}
