
import java.util.Scanner;

/**********************************************************************************************************************/
/*Класс с основным игровым циклом*/
/**********************************************************************************************************************/
public class Game {

    /******************************************************************************************************************/
    /*gamerStep - Ход игрока
    Параметры:
        нет
    Возвращаемое значение:
        нет
    Особенности:
        Функция отправляет принятые данные в класс с игровым полем
    */
    /******************************************************************************************************************/

    public static void gamerStep() {

        Scanner scanner_in = new Scanner(System.in);

        pole.printField();
        System.out.print("Ваш ход. \nВведите номер ячейки для своего хода(1-9): ");

        //ввод данных
        while (true) {

            byte bt_num_hod = scanner_in.nextByte();

            if ((bt_num_hod < 1) || (bt_num_hod > 9)) {
                System.out.println("Некорректный ввод данных, введите цифру от 1 до 9");
                continue;
            }

            //Для работы с полем
            bt_num_hod--;

            //Проверка: Занята ли ячейка или нет?
            if (pole.index(bt_num_hod) != 0) {
                System.out.println("Ячейка уже занята, повторите ввод");
                continue;

            } else {
                pole.setIndex(bt_num_hod, 1);
                break;

            }

        }

        scanner_in.close();

    }

    /******************************************************************************************************************/
    /*compStep - Ход компьютера
    Параметры:
        нет
    Возвращаемое значение:
        нет
    Особенности:
        Функция отправляет обработанные данные в класс с игровым полем
    */
    /******************************************************************************************************************/

    public static void compStep() {

        byte bt_step = -1;

        //Проверяем не является ли данный ход первым?
        if (pole.compLength() == 0) {
            //Первый ход делаем по рандому
            bt_step = logika.compFirstStep();
        }

        if (bt_step == -1) {
            //Атака. Есть возможность выиграть одним действием
            bt_step = logika.analysisOfTwoCells(false);
        }

        if (bt_step == -1) {
            //Защита. Закрываем возможность выиграть противнику
            bt_step = logika.analysisOfTwoCells(true);
        }

        if (bt_step == -1) {
            //Выбор направления. Стремимся занять места с двумя ячейками, когда уже есть одна в ряду
            bt_step = logika.compProfitSearch();
        }

        //Если ничего из вышеперечисленного не помогло, то просто выбираем первую попавшуюся пустую ячейку
        //Плюс подстраховка, на случай, если ячейка уже занята и мы где-то ошиблись в логике
        if ((bt_step == -1) || (pole.index(bt_step) != 0)) {
            for(byte i = 0; i < pole.lengthAll(); i++){
                if (pole.index(bt_step) == 0) {
                    bt_step = i;
                    break;
                }
            }
        }

        if (bt_step != -1) {
            String str_index = "";
            switch (bt_step) {
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

    }

    /******************************************************************************************************************/
    /*Игровой цикл*/

    /*gameLoop - игровой цикл
    Параметры:
        нет
    Возвращаемое значение:
        byte - чем закончилась игра
            0 - поражение юзера
            1 - победа юзера
            2 - ничья
    */
    /******************************************************************************************************************/

    public static byte gameLoop() {

        //Отслеживаем не пора ли заканчивать игру
        byte bt_finish_game = -1;
        //Последний сделанный ход
        //byte bt_step = -1;

        //Игровой цикл ходов
        while(true) {

            //Игрок, интерфейс + занесение данных
            //bt_step = gamerStep();
            gamerStep();

            //Отслеживание ситуации на поле.
            //Проверяем как дела у пользователя
            if (logika.testGamerWin(true)) {
                bt_finish_game = 1;
                break;
            }

            //Ничья
            if (pole.lengthAll() >= 9) {
                bt_finish_game = 2;
                break;
            }

            //Ход компьютера + вывод пользователю
            //bt_step = compStep();
            compStep();

            //Отслеживание ситуации на поле.
            //Смотрим какая ситуация у компьютера
            if (logika.testGamerWin(false)) {
                bt_finish_game = 0;
                break;
            }

            //Ничья
            if (pole.lengthAll() >= 9) {
                bt_finish_game = 2;
                break;
            }

        }

        //Завершающее слово будет сказано уже в функции main
        return bt_finish_game;
    }

}