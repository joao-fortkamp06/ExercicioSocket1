/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Principal {
    public static void main(String[] args) {
        try {
           
            String termo = JOptionPane.showInputDialog("Digite o que deseja pesquisar no Google:");

            if (termo == null || termo.trim().isEmpty()) {
                System.out.println("Pesquisa cancelada.");
                return;
            }
         
            String query = URLEncoder.encode(termo, "UTF-8");

            Socket sock = new Socket("www.google.com", 80);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            out.println("GET /search?q=" + query + " HTTP/1.1");
            out.println("Host: www.google.com");
            out.println("Connection: close");
            out.println();
            out.flush();

            String linha;
            while ((linha = in.readLine()) != null) {
                System.out.println("echo: " + linha);
            }

            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            System.err.println("Problemas de IO: " + e.getMessage());
        }
    }
}
