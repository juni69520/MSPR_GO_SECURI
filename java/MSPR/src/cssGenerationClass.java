import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

        File fa= new File("/www/style.css");
        try {
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(fa));
            bw2.write(css);
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
