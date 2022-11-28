import java.util.Scanner;

public class kolkoikrzyzyk {
    public static void main(String[] args) {
        System.out.println("Witaj w grze, podaj rozmiar planszy (wpisz 3):");
        //zmienna przechowująca rozmiar planszy
        int rozmiar = new Scanner(System.in).nextInt();
        char[][] plansza = new char[rozmiar][rozmiar];
        int movesCounter = 0;
        char obecnyGracz = 'X';
        // jeżeli kod w pętli zmieni wartość w tej zmiennej na true, nie wykona się kolejna iteracja
        boolean won = false;
        while (movesCounter < rozmiar * rozmiar && !won) {
            printBoard(plansza); // drukuje plansze zeby był widoczny rezultat
            boolean moveWasCorrect = performMove(plansza, obecnyGracz);
            if (moveWasCorrect) {
                // zwiększam licznik ruchów o 1
                movesCounter++;
                // sprawdzam czy po tym ruchu ktoś nie wygrał
                won = checkWinner(plansza, obecnyGracz);
                // zmieniam aktywnego gracza na przeciwnego
                obecnyGracz = obecnyGracz == 'X' ? 'O' : 'X';
            } else {
                System.out.println("Ruch niepoprawny, spróbuj ponownie");
            }
        }
        printBoard(plansza);
        System.out.println("Koniec gry :)");
    }

    private static boolean checkWinner(char[][] plansza, char obecnyGracz) {
        return checkFirstDiagonal(plansza, obecnyGracz) ||
                checkSecondDiagonal(plansza, obecnyGracz) ||
                checkWinInColumns(plansza, obecnyGracz) ||
                checkWinInRows(plansza, obecnyGracz);
    }

    private static boolean checkFirstDiagonal(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        for (int i = 0; i < dim; i++) {
            if (plansza[i][i] != obecnyGracz) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkSecondDiagonal(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        for (int i = 0; i < dim; i++) {
            if (plansza[i][dim - i - 1] != obecnyGracz) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWinInRows(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        // licznik col będzie sprawdzał w kolejnych iteracjach
        // kolejne kolumny od 0 aż do dim
        for (int row = 0; row < rozmiar; row++) {
            // zakładam optymistycznie, że activePlayer wygrał
            boolean win = true;
            // licznik col będzie przesuwał się po kolejnych
            // wierszach dla danej kolumny col
            for (int col = 0; col < rozmiar; col++) {
                /* jeżeli trafię na pierwszą komórkę w której
                nie ma symbolu obecnyGracz, to wiem, że
                w tej kolumnie nie wygrał, więc ustawiam
                flagę win na false i przerywam sprawdzanie
                kolejnych komórek */
                if (plansza[row][col] != obecnyGracz) {
                    win = false;
                    break;
                }
            }
            /* jeżeli po sprawdzeniu danej kolumny
            flaga win dalej ma wartość true, to znaczy
            że gracz wygrał w tej kolumnie
            przerywam więc całą metodę zwracając true */
            if (win) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWinInColumns(char[][] plansza, char obecnyGracz) {
        int rozmiar = plansza.length;
        // licznik col będzie sprawdzał w kolejnych interakcjach
        // kolejne kolumny od 0 aż do dim
        for (int col = 0; col < rozmiar; col++) {
            // zakładam optymistycznie, że obecnyGracz wygrał
            boolean win = true;
            // licznik col będzie przesuwał się po kolejnych
            // wierszach dla danej kolumny col
            for (int row = 0; row < rozmiar; row++) {
                /*  jeżeli trafię na pierwszą komórkę w której
                nie ma symbolu obecnyGracz, to wiem, że
                w tej kolumnie nie wygrał, więc ustawiam
                flagę win na false i przerywam sprawdzanie
                kolejnych komórek */
                if (plansza[row][col] != obecnyGracz) {
                    win = false;
                    break;
                }
            }
            /* jeżeli po sprawdzeniu danej kolumny
            flaga win dalej ma wartość true, to znaczy
            że gracz wygrał w tej kolumnie
            przerywam więc całą metodę zwracając true */
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

            // jako że przeniosłem tę metodę to nie będę już tutaj
            // modyfikował licznika ruchów ani zmieniał gracza
            // zrobię to metodzie main

            return true; // zwracam true jezeli ruch sie udał
            // jeżeli kod dojdzie do tej linii to znaczy ze się udał
        } else { // ten else jest opcjonalny, wystarczyloby return false (dlaczego?)
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
