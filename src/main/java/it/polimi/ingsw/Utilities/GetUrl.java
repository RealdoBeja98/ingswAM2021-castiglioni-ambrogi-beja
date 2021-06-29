package it.polimi.ingsw.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetUrl {

    /**
     * Helper method for getting an URL
     * @param: filename the json file
     * @return an URL, of type String
     */
    public static String UrlFromText(String filename){
        InputStream is = null;
        is = GetUrl.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = null;
        try {
            line = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            try {
                line = buf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fileAsString = sb.toString();
        return fileAsString;
    }
}
