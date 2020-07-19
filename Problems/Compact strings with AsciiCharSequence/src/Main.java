import java.util.Arrays;


class AsciiCharSequence /* extends*/implements java.lang.CharSequence {
    byte[] line;

    public AsciiCharSequence(byte[] line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return new String(line);
    }

    @Override
    public int length() {
        return line.length;
    }

    @Override
    public char charAt(int index) {
        return (char)line[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence( Arrays.copyOfRange(line, start, end));
    }
    // implementation
}