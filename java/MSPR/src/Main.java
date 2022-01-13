package com.company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static  String filename = "agents.txt";
    public static  String pathFiche = "./fiche_agent/";
    public static  String pathCni = "../cni_agent/";
    public static void main(String[] args) throws IOException {

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
                    html += "<tr style=''><td style='border: 1px solid black;background-color:"+backgroundColor+";text-align: center;vertical-align: middle;'><a href='./fiche_agent/"+line+".html' style='text-decoration:none; color:white;'>"+line+"</a><td><tr>";
                    cpt+=1;
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
        html = "";

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(reader);
            boolean read = true;

            while(read) {
                String line = buffer.readLine();
                if (line == null) {
                    read = false;
                } else {
                    try {
                        System.out.println(line);
                        String pathFile = pathFiche+line+".txt";
                        String imageFile = pathCni+line+".png";
                        System.out.println(pathFile);

                        int cptLine = 0;
                        String agentNom = "";
                        String agentPrenom = "";
                        List materiel =  new ArrayList();

                        FileReader readerFiche = new FileReader(pathFile);
                        BufferedReader bufferFiche = new BufferedReader(readerFiche);
                        boolean read2 = true;

                        while(read2) {
                            String line2 = bufferFiche.readLine();
                            if (line == null) {
                                read = false;
                            } else {
                                System.out.println(line2+" test");
                                if(cptLine == 0){
                                    agentNom = line2;
                                }else if(cptLine == 1){
                                    agentPrenom = line2;
                                }else if(cptLine == 8){
                                    read2 = false;
                                } else if (cptLine >= 5){
                                    materiel.add(line2);
                                }
                                cptLine++;
                            }
                        }
                        fa= new File("./fiche_agent/"+line+".html");
                        bw = new BufferedWriter(new FileWriter(fa));

                        html = "<head></head><body><div>"+agentNom+" "+agentPrenom;
                        html+= "<img src='"+imageFile+"'>";
                        for (int i = 0; i < materiel.size(); i++) {
                            html+="<br>"+materiel.get(i);
                        }

                        html+="</body><footer></footer>";

                        bw.write(html);
                        bw.close();
                        bufferFiche.close();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
            buffer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
