import java.util.Random;
import java.util.Scanner;




public class Main {

    static Scanner scanner_in;
    static boolean k_or_n = true; //чем играет пользователь: крестиком(true) или ноликом(false)
    static byte[] pole = {2,2,2, 2,2,2, 2,2,2};
    final static byte bt_empty = 2;

    /******************************************************************************************************************/
    /*Ход игрока*/
    /******************************************************************************************************************/

    public static byte hod_igroka() {

        print_table();
        System.out.print("Ваш ход. \nВведите номер ячейки для своего хода(1-9): ");

        byte bt_num = -1;

        //ввод данных
        while (true) {

            bt_num = scanner_in.nextByte();

            if ((bt_num < 1) || (bt_num > 9)) {
                System.out.println("Некорректный ввод данных, введите цифру от 1 до 9");
                continue;
            }

            //Для работы с массивом
            bt_num--;

            if (pole[bt_num] != 2) {
                System.out.println("Ячейка уже занята, повторите ввод");
                continue;
            }

            break;

        }

        return bt_num;
    }

    /******************************************************************************************************************/
    /*Искусственный интеллект - стратегия*/
    /******************************************************************************************************************/

    public static byte comp_ai_strategiya(byte bt_user_znak, byte bt_comp_znak, byte bt_empty) {

        System.out.println("debug: comp_ai_strategiya( " + bt_user_znak + ", " + bt_comp_znak + ", " + bt_empty + " )");

        byte bt_hod = -1;

        // Проверяем не является ли данный ход первым?
        byte bt_counter = 0;
        for(byte i = 0; i < pole.length; i++){
            if (pole[i] == bt_comp_znak) {
                bt_counter++;
            }
        }

        //Первый ход делаем по рандому
        if (bt_counter == 0) {
            bt_hod = comp_ai_pervyi_hod(bt_empty);
            return bt_hod;
        }

        //Атака. Есть возможность выиграть одним действием
        bt_hod = comp_ai_analiz(bt_comp_znak, bt_empty);
        if ((bt_hod != -1) && (pole[bt_hod] == bt_empty)) {
            return bt_hod;
        }

        //Защита. Закрываем возможность выиграть противнику
        bt_hod = comp_ai_analiz(bt_user_znak, bt_empty);
        if ((bt_hod != -1) && (pole[bt_hod] == bt_empty)) {
            return bt_hod;
        }

        //Выбор направления. Стремимся занять места с двумя ячейками, когда уже есть одна в ряду
        bt_hod = comp_ai_vyigoda(bt_comp_znak, bt_empty);
        if ((bt_hod != -1) && (pole[bt_hod] == bt_empty)) {
            return bt_hod;
        }

        return bt_hod;

    }

    /******************************************************************************************************************/
    /*Ход компьютера*/
    /******************************************************************************************************************/

    public static byte comp_hod(boolean b_user_znak) {

        byte bt_rez = -1;

        byte bt_user_znak = (byte)((b_user_znak) ? 1 : 0);

        //Подбираем знак для компа
        //Здесь обратная логика, на истину ноль и наоборот единица на ложь
        byte bt_comp_znak = (byte)((b_user_znak) ? 0 : 1);

        bt_rez = comp_ai_strategiya(bt_user_znak, bt_comp_znak, bt_empty);

        //Если ничего из вышеперечисленного не помогло, то просто выбираем первую попавшуюся пустую ячейку
        if ((bt_rez == -1) || (pole[bt_rez] != bt_empty)) {
            for(byte i = 0; i < pole.length; i++){
                if (pole[i] == 2) {
                    bt_rez = i;
                    break;
                }
            }
        }



        if (bt_rez != -1) {
            String str_index = "";
            switch (bt_rez) {
                case 0:
                    str_index = "первая";
                    break;
                case 1:
                    str_index = "вторая";
                    break;
                case 2:
                    str_index = "третья";
                    break;
                case 3:
                    str_index = "четвёртая";
                    break;
                case 4:
                    str_index = "пятая";
                    break;
                case 5:
                    str_index = "шестая";
                    break;
                case 6:
                    str_index = "седьмая";
                    break;
                case 7:
                    str_index = "восьмая";
                    break;
                case 8:
                    str_index = "девятая";
                    break;
            }
            System.out.println("\nХодит компьютер. \nХод: " + str_index + " ячейка");
        }

        return bt_rez;
    }



    /******************************************************************************************************************/
    /*Отслеживание игрового процесса*/
    /*Параметры: boolean b_user_znak - чем играет игрок крестики(истина) или нолики(ложь)*/
    /*Возвращаемое значение: boolean - возвращаем в главный цикл закончилась ли игра или нет*/
    /******************************************************************************************************************/

    public static boolean logika_igri(boolean b_user_znak) {

        //Пора ли заканчивать?
        boolean b_state_finish = false;

        /*0 - юзер проиграл
        * 1 - юзер выиграл
        * 2 - ничья*/
        byte b_user_win = 2;

        //Не наметилась ли у нас победа кого-то над кем-то?
        if (test_znak_win(b_user_znak)) {
            b_user_win = 1;
            b_state_finish = true;
        } else if (test_znak_win(!b_user_znak)) {
            b_user_win = 0;
            b_state_finish = true;
        }

        //Ну раз никто не выиграл то может, тут ничья?
        if (!b_state_finish) {

            //Пока есть хоть одна не занятая ячейка игра не закончится
            for(byte i = 0; i < pole.length; i++){

                //Нашли пустую ячейку, продолжаем игру
                if (pole[i] == 2) {
                    break;
                }

                //Дошли до конца массива, а пустых ячеек нет, значит ничья
                if (i == (pole.length-1)) {
                    b_user_win = 2;
                    b_state_finish = true;
                    break;
                }

            }

        }

        //Выводим пользователям сообщение о том, чем закончилась игра
        if (b_state_finish) {
            print_table();

            System.out.println("============================\n");

            if (b_user_win == 1) {
                System.out.println("Вы выиграли!!!");
            } else if (b_user_win == 0) {
                System.out.println("Вы проиграли=(");
            } else {
                System.out.println("Ничья...");
            }
        }

        return b_state_finish;
    }

    /******************************************************************************************************************/
    /*Основной игровой цикл*/
    /******************************************************************************************************************/

    public static void main(String[] args) {

        System.out.println("\n****************************");
        System.out.println("*** Игра крестики-нолики ***");
        System.out.println("****************************");
        System.out.print("\nВыберете символ, которым вы будете играть,\nкрестик(1) или нолик(0): ");

        //ввод данных
        scanner_in = new Scanner(System.in);
        byte bt_num = scanner_in.nextByte();

        if (bt_num == 1) {
            k_or_n = true;
            System.out.println("\nВы выбрали игру крестиками(X)");
        } else {
            k_or_n = false;
            System.out.println("\nВы выбрали игру ноликами(0)");
        }
        System.out.println("\nНажмите Enter для начала игры...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}

        boolean finish_game = false;
        byte bt_hod = -1;

        //Игровой цикл ходов
        while(true) {

            //Игрок, интерфейс
            bt_hod = hod_igroka();

            //Добавляем последний ход игрока
            pole[bt_hod] = (byte)((k_or_n) ? 1 : 0);

            //Смотрим на результаты на поле
            finish_game = logika_igri(k_or_n);

            if (finish_game) break;

            //Ход компа(только логика)
            bt_hod = comp_hod(k_or_n);

            //Добавляем ход компа(обратный знак пользовательскому)
            pole[bt_hod] = (byte)((k_or_n) ? 0 : 1);

            //Смотрим на результаты на поле
            finish_game = logika_igri(k_or_n);

            //Завершающее слово было сказано в функции logika_igri
            if (finish_game) break;

        }

        System.out.println("Игра закончена");

        scanner_in.close();
    }
}
