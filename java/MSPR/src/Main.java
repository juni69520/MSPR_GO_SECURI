package com.company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static  String filename = "./agents.txt";
    public static  String pathFiche = "./fiche_agent/";
    public static  String pathCni = "../cni_agent/";
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get("./staff/"));

        String htpasswd = "";
        String html =
                "<head>" +
                        "<style>" +
                            "@font-face {" +
                                "font-family: 'Roboto-Light';" +
                                "src: url('Roboto-Light.eot');" +
                                "src: url('Roboto-Light.woff') format('woff')," +
                                "url('Roboto-Light.otf') format('opentype')," +
                                "url('Roboto-Light.svg#filename') format('svg');" +
                            "}" +
                        "</style>" +
                "</head>" +
                "<body style='font-family: \"CustomFont\"'>" +
                "   <div>" +
                "       <table style='margin-left:auto;margin-right:auto;margin-top:12%; border: 1px solid black;'>" +
                "           <tr'>" +
                "               <th style='border: 1px solid black; width:180px;text-align: center;vertical-align: middle;'>List des agents</th>" +
                "           <tr>";

        File fa= new File("index.html");

        //Path filePath = Paths.get(filename);
        String backgroundColor = "";
        int cpt = 0;
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(reader);
            boolean read = true;

            while(read) {
                String line = buffer.readLine();
                if (line == null) {
                    read = false;
                } else {
                    backgroundColor = "#659224";
                    if (Math.floorMod(cpt, 2) == 0){
                        backgroundColor = "#379EC1";
                    }
                    html += "<tr style=''><td style='border: 1px solid black;background-color:"+backgroundColor+";text-align: center;vertical-align: middle;'><a href='./staff/"+line+".html' style='text-decoration:none; color:white;'>"+line+"</a><td><tr>";
                    cpt+=1;

                    FileReader reader2 = new FileReader(filename);
                    BufferedReader buffer2 = new BufferedReader(reader);
                    boolean read2 = true;
                    String html2;

                    String pathFile = pathFiche+line+".txt";
                    String imageFile = pathCni+line+".png";
                    String pathReturn = "../image/return.png";

                    int cptLine = 0;
                    String agentNom = "";
                    String agentPrenom = "";
                    List materiel =  new ArrayList();

                    FileReader readerFiche = new FileReader(pathFile);
                    BufferedReader bufferFiche = new BufferedReader(readerFiche);

                    while(read2) {
                        String line2 = bufferFiche.readLine();
                        if (line2 == null) {
                            read2 = false;
                        } else {
                            if(cptLine == 0){
                                agentNom = line2;
                            }else if(cptLine == 1){
                                agentPrenom = line2;
                            }else if(cptLine == 2){
                                htpasswd += line+":"+ hash.getMd5(line2)+"\n";
                            } else if (cptLine >= 5){
                                materiel.add(line2);
                            }
                            cptLine++;
                        }
                    }
                    File fa2= new File("./staff/"+line+".html");
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter(fa2));

                    html2 = "<head></head><body><section style=\"background-color:#379EC1; margin-left:12%;display:block; border: 2px solid black; height: 400px; width:1000px;padding-top:20px;\">" +
                            "<div style=\"position: fixed; left: 15%;\"><a href=\"../index.html\"><img style=\"width:2%; height\" src='"+pathReturn+"'></a></div>" +
                            "<div style=\"position: fixed; right: 40%; top: 3%;\"><img style=\"background-color: rgba(255, 255, 128, 0);display:block;margin:auto;\" src='"+imageFile+"'></div>" +
                            "<div style=\"position: fixed; left: 15%; top: 10%; border-radius: 20px; background: #659224; padding: 15px; width: 200px; height: 20px; text-align:center;\">" +agentNom+" "+agentPrenom;

                    html2 += "<div style=\"margin-top:25%;\"><table>";
                    for (int i = 0; i < materiel.size(); i++) {

                        html2+="<tr><td><input style=\"background: #00bf00;\"type=\"checkbox\" id='"+materiel.get(i)+"' name='"+materiel.get(i)+"' checked> <label for='"+materiel.get(i)+"' style=\"color:white;\">"+materiel.get(i)+"</label></td></tr>";
                    }
                    html2 += "</table></div></div></section>";
                    html2+="</body><footer></footer>";

                    bw2.write(html2);
                    bw2.close();
                    bufferFiche.close();
                }
            }
            buffer.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        html += "</table></div><body><footer></footer>";

        BufferedWriter bw = new BufferedWriter(new FileWriter(fa));
        bw.write(html);
        bw.close();

        File fa3= new File("./staff/.htpasswd");
        BufferedWriter bw3 = new BufferedWriter(new FileWriter(fa3));
        bw3.write(htpasswd.substring(0, htpasswd.length() - 1));
        bw3.close();
    }
}

public class hash {
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest &= md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}