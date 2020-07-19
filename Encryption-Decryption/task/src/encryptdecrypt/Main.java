package encryptdecrypt;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

abstract class Cipher{
    protected String data;
    protected int key;
    protected Algorithm alg;

    public Cipher(String data, int key, Algorithm alg) {
        this.data = data;
        this.key = key;
        this.alg = alg;
    }

    abstract String perform();
}
class EncryptDataCipher extends Cipher{

    public EncryptDataCipher(String data, int key, Algorithm alg) {
        super(data, key, alg);
    }

    @Override
    String perform() {
        StringBuilder result = new StringBuilder();
        if (alg == Algorithm.SHIFT) {
            for (char c : data.toCharArray()) {
                if (!Character.isLetter(c)) {
                    result.append(c);
                } else if (Character.isUpperCase(c)) {
                    result.append(getEncryptUpperChar(c));
                } else if (Character.isLowerCase(c)) {
                    result.append(getEncryptLowerChar(c));
                }
            }
        } else if (alg == Algorithm.UNICODE) {
            for (char c : data.toCharArray()) {
                result.append((char) (c + key));
            }
        } else return null;
        return result.toString();
    }

    private char getEncryptUpperChar(char ch) {
        char root_character = 'A';
        int expectAlphabetPosition = ch - root_character + key;
        if (expectAlphabetPosition > 26)
            return (char) ('A' + expectAlphabetPosition % 26);
        return (char) (root_character + expectAlphabetPosition);
    }
    private char getEncryptLowerChar(char ch) {
        char root_character = 'a';
        int expectAlphabetPosition = ch - root_character + key;
        if (expectAlphabetPosition > 26)
            return (char) ('a' + expectAlphabetPosition % 26);
        return (char) (root_character + expectAlphabetPosition);
    }
}

class DecryptDataCipher extends Cipher{

    public DecryptDataCipher(String data, int key, Algorithm alg) {
        super(data, key, alg);
    }

    @Override
    String perform() {
        StringBuilder result = new StringBuilder();
        if (alg == Algorithm.SHIFT) {
            for (char c : data.toCharArray()) {
                if (!Character.isLetter(c)) {
                    result.append(c);
                } else if (Character.isUpperCase(c)) {
                    result.append(getDecryptUpperChar(c));
                } else if (Character.isLowerCase(c)) {
                    result.append(getDecryptLowerChar(c));
                }
            }
        } else if (alg == Algorithm.UNICODE) {
            for (char c : data.toCharArray()) {
                result.append((char) (c - key));
            }
        }
        return result.toString();
    }
    private char getDecryptUpperChar(char ch) {
        char root_character = 'A';
        int expectAlphabetPosition = ch - root_character - key;
        if (expectAlphabetPosition < 0)
            return (char) ('Z' + expectAlphabetPosition + 1);
        return (char) (root_character + expectAlphabetPosition);
    }

    private char getDecryptLowerChar(char ch) {
        char root_character = 'a';
        int expectAlphabetPosition = ch - root_character - key;
        if (expectAlphabetPosition < 0)
            return (char) ('z' + expectAlphabetPosition + 1);
        return (char) (root_character + expectAlphabetPosition);
    }
}

class Factory {

    public Cipher getCipher(Mode mode, String data, int key, Algorithm alg) {
        if (mode == Mode.DEC) {
            return new DecryptDataCipher(data, key, alg);
        } else if (mode == Mode.ENC) {
            return new EncryptDataCipher(data, key, alg);
        } else return null;
    }
}

enum Mode{
    ENC,
    DEC
}
enum Algorithm {
    SHIFT,
    UNICODE
}

public class Main{

    public static void main(String[] args) throws IOException {
        Mode mode = Mode.ENC;
        Algorithm alg = Algorithm.SHIFT;
        int key = 0;
        String data = "";
        String in = null;
        String out = null;

        //Process the Command-Line Statement
        for(int i = 0; i < args.length; i+=2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1].equals("enc") ? Mode.ENC : Mode.DEC;
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    key %= 26;
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1].equals("shift") ? Algorithm.SHIFT : Algorithm.UNICODE;
                    break;
            }
        }
        if (key < 0) {
            key = -key;
            mode = mode == Mode.ENC ? Mode.DEC : Mode.ENC;
        }
        //Process message
        if (data.equals("") && in != null) {
            data = Files.readString(Path.of(in));
        }
        //Process FileWriter
        PrintStream printStream = out == null ? System.out : new PrintStream(out);

        Factory ft = new Factory();
        String result = ft.getCipher(mode, data, key, alg).perform();

        for (char c : result.toCharArray()) {
            printStream.print(c);
        }

    }
}
