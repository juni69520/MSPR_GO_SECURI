import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class contentGenerationThread extends Thread{
    public static  String filename = "www/agents.txt";
    public static  String pathFiche = "www/fiche_agent/";
    public static  String pathCni = "www/cni_agent/";

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
                    String pathReturn = "www/image/return.png";

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
                    File fa2= new File("www/staff/"+line+".html");
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

            File fa3= new File("www/staff/.htpasswd");
            BufferedWriter bw3 = new BufferedWriter(new FileWriter(fa3));
            bw3.write(htpasswd.substring(0, htpasswd.length() - 1));
            bw3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
