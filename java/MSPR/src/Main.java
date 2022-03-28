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

    public class main {
        public static  String filename = "./www/agents.txt";
        public static  String pathFiche = "./www/fiche_agent/";
        public static  String pathCni = "../cni_agent/";

        public static void main(String[] args) throws IOException {
            Files.createDirectories(Paths.get("./www/staff/"));

            Runnable r2 = new cssGenerationClass();
            Thread th2 = new Thread(r2, "My second thread");
            th2.start();

            Runnable r1 = new contentGenerationThread();
            Thread th1 = new Thread(r1, "My first thread");
            th1.start();
        }
    }
}

public class cssGenerationClass extends Thread{
    public void run()
    {
        String css = "@font-face {\n" +
                "    font-family: 'Roboto Bk';\n" +
                "    src: url('fonts/Roboto-Black.woff2') format('woff2'),\n" +
                "    url('fonts/Roboto-Black.woff') format('woff');\n" +
                "    font-weight: 900;\n" +
                "    font-style: normal;\n" +
                "    font-display: swap;\n" +
                "}\n";
        css+= "body{\n" +
                "    font-family: 'Roboto Bk'\n" +
                "}\n";
        css+= "table {\n" +
                "    margin-left:auto;\n" +
                "    margin-right:auto;\n" +
                "    margin-top:12%;\n" +
                "    border: 1px solid black;\n" +
                "}\n";
        css+= "th {\n" +
                "    border: 1px solid black;\n" +
                "    width:180px;\n" +
                "    text-align: center;\n" +
                "    vertical-align: middle;\n" +
                "}\n";
        css+= "a {\n" +
                "    text-decoration:none;\n" +
                "    color:white;\n" +
                "}\n";
        css+= "section {\n" +
                "    background-color:#379EC1;\n" +
                "    margin-left:12%;\n" +
                "    display:block;\n" +
                "    border: 2px solid black;\n" +
                "    height: 400px;\n" +
                "    width:1000px;\n" +
                "    padding-top:20px;\n" +
                "}\n";
        css+= ".div1 {\n" +
                "    position: fixed;\n" +
                "    left: 15%;\n" +
                "}\n";
        css+= ".div2 {\n" +
                "    position: fixed;\n" +
                "    right: 40%;\n" +
                "    top: 3%;\n" +
                "}\n";
        css+= ".div3 {\n" +
                "    position: fixed;\n" +
                "    left: 15%;\n" +
                "    top: 10%;\n" +
                "    border-radius: 20px;\n" +
                "    background: #659224;\n" +
                "    padding: 15px;\n" +
                "    width: 200px;\n" +
                "    height: 20px;\n" +
                "    text-align:center;\n" +
                "}\n";
        css+= ".div4 {\n" +
                "    margin-top:25%;\n" +
                "}\n";

        File fa= new File("www/style.css");
        try {
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(fa));
            bw2.write(css);
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

public class contentGenerationThread extends Thread{
    public static  String filename = "./www/agents.txt";
    public static  String pathFiche = "./www/fiche_agent/";
    public static  String pathCni = "../cni_agent/";

    public void run()
    {


        String htpasswd = "";
        String html =
                "<head>\n<link rel=\"stylesheet\" href=\"style.css\">\n<link rel=\"preload\" href=\"Roboto-Black.woff2\" as=\"font\" type=\"font/woff2\" crossorigin>\n</head>\n" +
                        "\n<body>" +
                        "\n<div>" +
                        "\n<table>" +
                        "\n<tr'>" +
                        "\n<th>List des agents</th>" +
                        "\n<tr>";

        File fa= new File("www/index.html");

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
                    html += "\n<tr style=''>\n<td style='background-color:"+backgroundColor+"'><a href='./staff/"+line+".html'>"+line+"</a><td>\n<tr>";
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
                    File fa2= new File("./www/staff/"+line+".html");
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter(fa2));

                    html2 = "<head>\n<link rel=\"stylesheet\" href=\"../style.css\">\n</head>\n" +
                            "<body>\n" +
                            "<section>\n" +
                            "<div class=\"div1\">\n" +
                            "<a href=\"../index.html\"><img style=\"width:2%; height\" src='"+pathReturn+"'></a>\n" +
                            "</div>\n" +
                            "<div class=\"div2\">\n" +
                            "<img style=\"background-color: rgba(255, 255, 128, 0);display:block;margin:auto;\" src='"+imageFile+"'>\n" +
                            "</div>\n" +
                            "<div class=\"div3\">\n" +agentNom+" "+agentPrenom;

                    html2 += "<div class=\"div4\"><table>\n";
                    for (int i = 0; i < materiel.size(); i++) {

                        html2+="\n<tr>\n<td><input style=\"background: #00bf00;\"type=\"checkbox\" id='"+materiel.get(i)+"' name='"+materiel.get(i)+"' checked> <label for='"+materiel.get(i)+"' style=\"color:white;\">"+materiel.get(i)+"</label></td>\n</tr>";
                    }
                    html2 += "\n</table>\n</div></div>\n</section>";
                    html2+="\n</body>\n<footer></footer>";

                    bw2.write(html2);
                    bw2.close();
                    bufferFiche.close();
                }
            }
            buffer.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        html += "</table>\n</div>\n<body>\n<footer></footer>";

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fa));
            bw.write(html);
            bw.close();

            File fa3= new File("./www/staff/.htpasswd");
            BufferedWriter bw3 = new BufferedWriter(new FileWriter(fa3));
            bw3.write(htpasswd.substring(0, htpasswd.length() - 1));
            bw3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
