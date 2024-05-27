import java.util.ArrayList;

public class SpellingCheck {
    public ArrayList<String> CheckMistakes(String input , ArrayList<String> dictionary) {
        ArrayList<String> Mistakes = new ArrayList<>();
        Mistakes.addAll(checkMessingChar(input , dictionary));
        Mistakes.addAll(CheckExcessCharacter(input , dictionary));
        Mistakes.addAll(CheckSwappedChar(input , dictionary));
        return Mistakes;
    }

    public ArrayList<String> checkMessingChar(String input , ArrayList<String> dictionary) {
        ArrayList<String> correction = new ArrayList<>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char c : alphabet) {
            String atFront = c + input;
            String atBack = input + c;
            if (dictionary.contains(atFront)) {
                correction.add(atFront);
            }
            if (dictionary.contains(atBack)) {
                correction.add(atBack);
            }
        }
        return correction;
    }

    public ArrayList<String> CheckExcessCharacter(String input, ArrayList<String> dictionary) {
        ArrayList<String> correction = new ArrayList<>();

        int len = input.length() - 1;

        if (dictionary.contains(input.substring(1))) {
            correction.add(input.substring(1));
        }
        for (int i = 1; i < len; i++) {

            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            if (dictionary.contains(working)) {
                correction.add(working);
            }
        }
        if (dictionary.contains(input.substring(0, len))) {
            correction.add(input.substring(0, len));
        }
        return correction;
    }

    public ArrayList<String> CheckSwappedChar(String input , ArrayList<String> dictionary) {
        ArrayList<String> correction = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);
            working = working + input.charAt(i + 1);
            working = working + input.charAt(i);
            working = working.concat(input.substring((i + 2)));
            if (dictionary.contains(working)) {
                correction.add(working);
            }
        }
        return correction;
    }
}
