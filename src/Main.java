import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.*;
public class Main {
    static String text = "" ;
    static int spellingMistakes = 0 ;
    public static void main(String[] args) {

        try{
            BufferedReader reader = new BufferedReader(new FileReader("D:\\workspace\\java\\text-Editor\\TextEditor\\src\\text.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                text += " " + line ;
            }
        }
        catch (IOException e){
            System.out.println("error Reading file");
        }
        System.out.println(text);

        Thread RemoveSpaces = new Thread(new Runnable() {
            @Override
            public void run() {
                text = RemoveSpaces(text);
            }
        }
        );

        Thread checkSpelling = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> dictionary = new ArrayList<String>();
                try{
                    BufferedReader reader = new BufferedReader(new FileReader("D:\\workspace\\java\\text-Editor\\TextEditor\\src\\Dictionary.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        dictionary.add(line);
                    }
                }
                catch (IOException e){
                    System.out.println("error Reading file");
                }

                SpellingCheck checker = new SpellingCheck();

                String TextSplitSpaces[] = text.split(" ");

                for (int i = 0 ; i < TextSplitSpaces.length ; i++){
                    String word = TextSplitSpaces[i];
                    if (!(dictionary.contains(word))) {
                        ArrayList<String> suggestions = checker.CheckMistakes(word , dictionary);
                        spellingMistakes += suggestions.size();
                        TextSplitSpaces[i] = suggestions.get(0);
                    }
                }

                text = "" ;
                for( String Word : TextSplitSpaces){
                    text += Word + " " ;
                }
            }
        });

        RemoveSpaces.start();
        checkSpelling.start();

        try {
            RemoveSpaces.join();
            checkSpelling.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("spellingMistakes found = " + spellingMistakes );
        System.out.println("Text After Correction :");
        System.out.println(text);

        try {
            Files.writeString(Path.of("D:\\workspace\\java\\text-Editor\\TextEditor\\src\\correctedText.txt"), text, StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            // Print messqage exception occurred as
            // invalid. directory local path is passed
            System.out.print("Invalid Path");
        }
    }

    public static String RemoveSpaces (String text){
        String TextSplitSpaces [] = text.split(" ");

        text = "" ;
        for (String x : TextSplitSpaces){
            if (!(x.isEmpty())) {
                text += x + " ";
            }
        }
        return text ;
    }

}