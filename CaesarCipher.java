/** Project: Lab 4
 * Purpose Details: Create the code to encrypt packages
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/12
 * Last Date Changed:
 * Rev:

 */






public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                result.append((char) ((character - base + shift) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    /**
     *
     * @param text
     * @param shift
     * @return
     */
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }
}

