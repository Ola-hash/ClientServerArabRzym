package rzymarab;

public class RzymArab {
    private static String[] rzym = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public static final String REGEX = "^[1-9][0-9]*$";

    public static boolean isNumber(String expression) {
        return expression.matches(REGEX);
    }

    public static String arab2rzym(int liczba) throws RzymArabException {
        StringBuilder wynik = new StringBuilder();
        int i = 0;
        if (liczba > 3999 || liczba < 1) {
            throw new RzymArabException("Liczba spoza zakresu.");
        }
        while (liczba > 0) {
            if (liczba >= arab[i]) {
                liczba -= arab[i];
                wynik.append(rzym[i]);
            } else {
                i++;
            }
        }
        return wynik.toString();
    }

    public static int rzym2arab(String liczba) throws RzymArabException {
        int i = 0;
        int j = 0;
        int max = 0;
        int suma = 0;
        while (j < liczba.length() && i < arab.length) {
            if ((i % 2 == 0) && rzym[i].charAt(0) == liczba.charAt(j)) {
                suma = suma + arab[i];
                j++;
                if (i == 2 || i == 10 || i == 6) {
                    i += 2;
                    continue;
                }
                max++;
                if (max == 3) {
                    i++;
                }
            } else if ((i % 2 != 0) && (j < liczba.length() - 1) && (rzym[i].charAt(0) == liczba.charAt(j)) && (rzym[i].charAt(1) == liczba.charAt(j + 1))) {
                suma = suma + arab[i];
                if (i == 1 || i == 5) {
                    i += 4;
                } else if (i == 3 || i == 7) {
                    i += 2;
                } else {
                    i--;
                }
                j += 2;
            } else {
                i++;
                max = 0;
            }
        }

        if (j != liczba.length()) {
            throw new RzymArabException("Podano błędną liczbę rzymską");
        }

        return suma;
    }

}

