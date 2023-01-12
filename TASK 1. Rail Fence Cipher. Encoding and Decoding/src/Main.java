public class Main {

  public static void main(String[] args) {
    char[][] s = encryption("WEAREDISCOVEREDFLEEATONCE", 3);

    for (char[] chars : s) {
      System.out.println(chars);
    }

    System.out.println(decryption(s, 3));
  }

  static char[][] encryption(String text, int rails) {
    boolean check = false;
    int j = 0;
    int col = text.length();
    char[][] encryptedText = new char[rails][col];

    for (int i = 0; i < col; i++) {
      if (j == 0 || j == rails - 1)
        check =! check;
      encryptedText[j][i] = text.charAt(i);
      if (check) {
        j++;
      }
      else {
        j--;
      }
    }

    return encryptedText;
  }

  static String decryption(char[][] encryptedText, int rails) {
    String decryptedText = "";
    boolean check = false;
    int j = 0;

    for (int i = 0; i < encryptedText[0].length; i++) {
      if (j == 0 || j == rails - 1)
        check =! check;

      decryptedText += encryptedText[j][i];
      if (check)
        j++;
      else
        j--;
    }

    return decryptedText;
  }
}