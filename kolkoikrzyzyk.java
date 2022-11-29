import java.util.Scanner;

public class kolkoikrzyzyk {
    public static void main(String[] args) {
        System.out.println("Witaj w grze, podaj rozmiar planszy (wpisz 3):");
        //przechowuje rozmiar planszy
        int rozmiar = new Scanner(System.in).nextInt();
        //tablica uzupełniona domyślnymi wartościami
        char[][] plansza = new char[rozmiar][rozmiar];
        //licznik ruchów
        int licznik = 0;
        //obecny gracz
        char obecnyGracz = 'X';
        // jeżeli kod w pętli zmieni wartość w tej zmiennej na true, nie wykona się kolejna interakcja
        boolean won = false;
        while (licznik < rozmiar * rozmiar && !won) {
            printBoard(plansza); // drukuje plansze zeby był widoczny rezultat
            boolean moveWasCorrect = performMove(plansza, obecnyGracz);
            if (moveWasCorrect) {
                // zwiększa licznik ruchów o 1
                licznik++;
                // sprawdza czy po tym ruchu ktoś nie wygrał
                won = checkWinner(plansza, obecnyGracz);
                // zmieniam obecnego gracza na przeciwnego
                obecnyGracz = obecnyGracz == 'X' ? 'O' : 'X';
                //jeśli ruch wykonany w to samo miejsce co poprzedni gracz 
            } else {
                System.out.println("Ruch niepoprawny, spróbuj ponownie");
            }
        }
        printBoard(plansza);
        System.out.println("Koniec gry :)");
    }

    private static boolean checkWinner(char[][] plansza, char obecnyGracz) {
        //te dwie metody sprawdzaja wygrana po przekatnej
        return checkFirstDiagonal(plansza, obecnyGracz) ||
                checkSecondDiagonal(plansza, obecnyGracz) ||
                //ta metoda sprawdza wygrana w kolumnie
                checkWinInColumns(plansza, obecnyGracz) ||
                //ta metoda sprawdza wygrana poziomo
                checkWinInRows(plansza, obecnyGracz);
    }

    private static boolean checkFirstDiagonal(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        for (int i = 0; i < rozmiar; i++) {
            if (plansza[i][i] != obecnyGracz) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkSecondDiagonal(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        for (int i = 0; i < rozmiar; i++) {
            if (plansza[i][rozmiar - i - 1] != obecnyGracz) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWinInRows(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        // licznik col będzie sprawdzał w kolejnych interakcjach
        // kolejne kolumny od 0 aż do rozmiar
        for (int row = 0; row < rozmiar; row++) {
            // zakłada optymistycznie, że obecnyGracz wygrał
            boolean win = true;
            // licznik col będzie przesuwał się po kolejnych wierszach dla danej kolumny col
            for (int col = 0; col < rozmiar; col++) {
                /*  jeżeli trafi na pierwszą komórkę w której
                nie ma symbolu obecnyGracz, to wie, że
                w tej kolumnie nie wygrał, więc ustawia
                win na false i przerywam sprawdzanie
                kolejnych komórek */
                if (plansza[row][col] != obecnyGracz) {
                    win = false;
                    break;
                }
            }
            /* jeżeli po sprawdzeniu danej kolumny
            win dalej ma wartość true, to znaczy
            że gracz wygrał w tej kolumnie
            przerywa więc całą metodę zwracając true */
            if (win) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWinInColumns(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        // licznik col będzie sprawdzał w kolejnych interakcjach
        // kolejne kolumny od 0 aż do rozmiar
        for (int col = 0; col < rozmiar; col++) {
            // zakładaa optymistycznie, że obecnyGracz wygrał
            boolean win = true;
            // licznik col będzie przesuwał się po kolejnych wierszach dla danej kolumny col
            for (int row = 0; row < rozmiar; row++) {
                /*  jeżeli trafi na pierwszą komórkę w której
                nie ma symbolu obecnyGracz, to wie, że
                w tej kolumnie nie wygrał, więc ustawia
                win na false i przerywam sprawdzanie
                kolejnych komórek */
                if (plansza[row][col] != obecnyGracz) {
                    win = false;
                    break;
                }
            }
            /* jeżeli po sprawdzeniu danej kolumny
            win dalej ma wartość true, to znaczy
            że gracz wygrał w tej kolumnie
            przerywa więc całą metodę zwracając true */
            if (win) {
                return true;
            }
        }
        return false;
    }

    private static boolean performMove(char[][] plansza, char obecnyGracz) {
        System.out.println(obecnyGracz + ", podaj nr wiersza");
        int row = new Scanner(System.in).nextInt();
        System.out.println(obecnyGracz + ", podaj nr kolumny");
        int col = new Scanner(System.in).nextInt();

        if (plansza[row][col] == 0) { // jeżeli pole jest wolne
            plansza[row][col] = obecnyGracz; // wstaw tam symbol obecnego gracza

            return true; // zwracam true jezeli ruch sie udał
            // jeżeli kod dojdzie do tej linii to znaczy ze się udał
        } else { // ten else jest opcjonalny, wystarczyloby return false 
            return false; // zwroce false, jeżeli miejsce było zajęte
        }

    }

    public static void printBoard(char[][] plansza) {
        int rozmiar = plansza.length;
        // nagłówki kolumn
        System.out.print("\t");
        for (int i = 0; i < rozmiar; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int row = 0; row < rozmiar; row++) {
            System.out.print(row + ":\t");
            for (int column = 0; column < rozmiar; column++) {
                System.out.print(plansza[row][column] + "\t");
            }
            System.out.println();
        }
    }
}
