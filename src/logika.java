
import java.util.Random;

/**********************************************************************************************************************/
/*Класс с игровой логикой. Здесь собраны все нужные проверки поля*/
/**********************************************************************************************************************/
public class logika {

    /******************************************************************************************************************/
    /*compFirstStep - первый ход компьютера
    Параметры:
        нет
    Возвращаемое значение:
        byte - индекс с ходом компьютера
    Особенности:
        Если по каким-то причинам алгоритм не смог за 100 попыток найти свободное место в таблице, то вернётся -1
    */
    /******************************************************************************************************************/

    public static byte compFirstStep() {

        byte bt_start_index = -1;
        Random random = new Random();
        byte bt_counter_anti_bug = 0;

        while(true) {

            bt_start_index = (byte)random.nextInt(8);

            // Перед нами (возможно) первым ходил человек,
            // поэтому проверяем пуста ли ячейка?
            if (pole.index(bt_start_index) == 0) break;

            //Мера предосторожности
            if (bt_counter_anti_bug == 100) break;
            bt_counter_anti_bug++;
        }

        System.out.println("debug: compFirstStep: " + (1+bt_start_index));

        return bt_start_index;
    }

    /******************************************************************************************************************/
    /*analysisOfTwoCells - поиск двух знаков подряд
    Параметры:
        boolean b_gamer - кого тестируем, юзера(true) или комп(false)?
    Возвращаемое значение:
        byte - индекс ячейки которую важно занять
    Особенности:
        Когда у игрока есть уже две занятые ячейки в строке.
        Анализ проходит как с точки зрения закрыть свою линию, так и помешать противнику закрыть его
        Вначале запускаем анализ для компьютера(атака), а после ещё раз запускам функцию для анализа ходов юзера(защита).  
    */
    /******************************************************************************************************************/

    public static byte analysisOfTwoCells(boolean b_gamer) {

        //Ход, который предстоит сделать, чтобы отреагировать на ситуацию
        byte bt_vajno = -1;

        /*
        true - 1 - юзер(нолик или крестик, неважно)
        false - 2 - компьютер(нолик или крестик, неважно)
        */
        byte bt_gamer = (byte)((b_gamer) ? 1 : 2);

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        // Горизонтали
        if ((pole.index(0) == bt_gamer) && (pole.index(1) == bt_gamer) && (pole.index(2) == 0)) bt_vajno = 2;
        if ((pole.index(0) == 0) && (pole.index(1) == bt_gamer) && (pole.index(2) == bt_gamer)) bt_vajno = 0;
        if ((pole.index(0) == bt_gamer) && (pole.index(1) == 0) && (pole.index(2) == bt_gamer)) bt_vajno = 1;

        if ((pole.index(3) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(5) == 0)) bt_vajno = 5;
        if ((pole.index(3) == 0) && (pole.index(4) == bt_gamer) && (pole.index(5) == bt_gamer)) bt_vajno = 3;
        if ((pole.index(3) == bt_gamer) && (pole.index(4) == 0) && (pole.index(5) == bt_gamer)) bt_vajno = 4;

        if ((pole.index(6) == bt_gamer) && (pole.index(7) == bt_gamer) && (pole.index(8) == 0)) bt_vajno = 8;
        if ((pole.index(6) == 0) && (pole.index(7) == bt_gamer) && (pole.index(8) == bt_gamer)) bt_vajno = 6;
        if ((pole.index(6) == bt_gamer) && (pole.index(7) == 0) && (pole.index(8) == bt_gamer)) bt_vajno = 7;

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        // Вертикали
        if ((pole.index(0) == bt_gamer) && (pole.index(3) == bt_gamer) && (pole.index(6) == 0)) bt_vajno = 6;
        if ((pole.index(0) == 0) && (pole.index(3) == bt_gamer) && (pole.index(6) == bt_gamer)) bt_vajno = 0;
        if ((pole.index(0) == bt_gamer) && (pole.index(3) == 0) && (pole.index(6) == bt_gamer)) bt_vajno = 3;

        if ((pole.index(1) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(7) == 0)) bt_vajno = 7;
        if ((pole.index(1) == 0) && (pole.index(4) == bt_gamer) && (pole.index(7) == bt_gamer)) bt_vajno = 1;
        if ((pole.index(1) == bt_gamer) && (pole.index(4) == 0) && (pole.index(7) == bt_gamer)) bt_vajno = 4;

        if ((pole.index(2) == bt_gamer) && (pole.index(5) == bt_gamer) && (pole.index(8) == 0)) bt_vajno = 8;
        if ((pole.index(2) == 0) && (pole.index(5) == bt_gamer) && (pole.index(8) == bt_gamer)) bt_vajno = 2;
        if ((pole.index(2) == bt_gamer) && (pole.index(5) == 0) && (pole.index(8) == bt_gamer)) bt_vajno = 5;

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        // Диагонали
        if ((pole.index(0) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(8) == 0)) bt_vajno = 8;
        if ((pole.index(0) == 0) && (pole.index(4) == bt_gamer) && (pole.index(8) == bt_gamer)) bt_vajno = 0;
        if ((pole.index(0) == bt_gamer) && (pole.index(4) == 0) && (pole.index(8) == bt_gamer)) bt_vajno = 4;

        if ((pole.index(2) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(6) == 0)) bt_vajno = 6;
        if ((pole.index(2) == 0) && (pole.index(4) == bt_gamer) && (pole.index(6) == bt_gamer)) bt_vajno = 2;
        if ((pole.index(2) == bt_gamer) && (pole.index(4) == 0) && (pole.index(6) == bt_gamer)) bt_vajno = 4;

        System.out.println("debug: analysisOfTwoCells: " + (1+bt_vajno));
        return bt_vajno;
    }

    /******************************************************************************************************************/
    /*compProfitSearch - поиск выгоды
    Параметры:
        нет
    Возвращаемое значение:
        byte - индекс ячейки которую можно занять
    Особенности:
        Когда у игроков уже что-то есть и компьютеру следует найти выгодную позицию для следующего хода.
        Анализ проходит только с точки зрения компьютера, игрока никак не тестируем.  
    */
    /******************************************************************************************************************/

    public static byte compProfitSearch() {

        //Потенциально выгодные ячейки
        byte bt_interesno1 = -1;
        byte bt_interesno2 = -1;

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        // Горизонтали
        if ((pole.index(0) == 2) && (pole.index(1) == 0) && (pole.index(2) == 0)) bt_interesno1 = 2;
        if ((pole.index(0) == 0) && (pole.index(1) == 0) && (pole.index(2) == 2)) bt_interesno1 = 0;
        if ((pole.index(0) == 0) && (pole.index(1) == 2) && (pole.index(2) == 0)) {
            bt_interesno1 = 0;
            bt_interesno2 = 2;
        }

        if ((pole.index(3) == 2) && (pole.index(4) == 0) && (pole.index(5) == 0)) bt_interesno1 = 5;
        if ((pole.index(3) == 0) && (pole.index(4) == 0) && (pole.index(5) == 2)) bt_interesno1 = 3;
        if ((pole.index(3) == 0) && (pole.index(4) == 2) && (pole.index(5) == 0)) {
            bt_interesno1 = 3;
            bt_interesno2 = 5;
        }

        if ((pole.index(6) == 2) && (pole.index(7) == 0) && (pole.index(8) == 0)) bt_interesno1 = 8;
        if ((pole.index(6) == 0) && (pole.index(7) == 0) && (pole.index(8) == 2)) bt_interesno1 = 6;
        if ((pole.index(6) == 0) && (pole.index(7) == 2) && (pole.index(8) == 0)) {
            bt_interesno1 = 6;
            bt_interesno2 = 8;
        }

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        // Вертикали
        if ((pole.index(0) == 2) && (pole.index(3) == 0) && (pole.index(6) == 0)) bt_interesno1 = 6;
        if ((pole.index(0) == 0) && (pole.index(3) == 0) && (pole.index(6) == 2)) bt_interesno1 = 0;
        if ((pole.index(0) == 0) && (pole.index(3) == 2) && (pole.index(6) == 0)) {
            bt_interesno1 = 0;
            bt_interesno2 = 6;
        }

        if ((pole.index(1) == 2) && (pole.index(4) == 0) && (pole.index(7) == 0)) bt_interesno1 = 7;
        if ((pole.index(1) == 0) && (pole.index(4) == 0) && (pole.index(7) == 2)) bt_interesno1 = 1;
        if ((pole.index(1) == 0) && (pole.index(4) == 2) && (pole.index(7) == 0)) {
            bt_interesno1 = 1;
            bt_interesno2 = 7;
        }

        if ((pole.index(2) == 2) && (pole.index(5) == 0) && (pole.index(8) == 0)) bt_interesno1 = 8;
        if ((pole.index(2) == 0) && (pole.index(5) == 0) && (pole.index(8) == 2)) bt_interesno1 = 2;
        if ((pole.index(2) == 0) && (pole.index(5) == 2) && (pole.index(8) == 0)) {
            bt_interesno1 = 2;
            bt_interesno2 = 8;
        }

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        // Диагонали
        if ((pole.index(0) == 2) && (pole.index(4) == 0) && (pole.index(8) == 0)) bt_interesno1 = 8;
        if ((pole.index(0) == 0) && (pole.index(4) == 0) && (pole.index(8) == 2)) bt_interesno1 = 0;
        if ((pole.index(0) == 0) && (pole.index(4) == 2) && (pole.index(8) == 0)) {
            bt_interesno1 = 0;
            bt_interesno2 = 8;
        }

        if ((pole.index(2) == 2) && (pole.index(4) == 0) && (pole.index(6) == 0)) bt_interesno1 = 6;
        if ((pole.index(2) == 0) && (pole.index(4) == 0) && (pole.index(6) == 2)) bt_interesno1 = 2;
        if ((pole.index(2) == 0) && (pole.index(4) == 2) && (pole.index(6) == 0)) {
            bt_interesno1 = 2;
            bt_interesno2 = 8;
        }

        // Присутствуют потенциально две интересных ячейки, выбираем одну
        if (bt_interesno2 != -1) {

            // Если процент занятых клеток большой, то выбираем меньшую переменную,
            // но если игра только начинается, то большую.
            if (pole.compLength() >= 4) {
                System.out.println("debug: compProfitSearch: " + (1+bt_interesno1));
                return bt_interesno1;
            } else {
                System.out.println("debug: compProfitSearch: " + (1+bt_interesno2));
                return bt_interesno2;
            }

        }

        System.out.println("debug: compProfitSearch: " + (1+bt_interesno1));
        return bt_interesno1;

    }

    /******************************************************************************************************************/
    /*testGamerWin - проверка выиграл ли кто-то?
    Параметры:
        boolean b_gamer - кого тестируем, юзера(true) или комп(false)?
    Возвращаемое значение:
        boolean - тестируемый игрок(юзер или комп) точно выиграл(true)/неизвестность(false)
    Особенности:
        Функция не работает по принципу выиграл/проиграл. Цель только в определение выигрыша и всё.
        0 1 2
        3 4 5
        6 7 8
    */
    /******************************************************************************************************************/

    public static boolean testGamerWin(boolean b_gamer) {

        /*
        true - 1 - юзер(нолик или крестик, неважно)
        false - 2 - компьютер(нолик или крестик, неважно)
        */
        byte bt_gamer = (byte)((b_gamer) ? 1 : 2);

        /*
        0 1 2
        3 4 5
        6 7 8
        */

        //Горизонтали + вертикали + диагонали
        if (
                ((pole.index(0) == bt_gamer) && (pole.index(1) == bt_gamer) && (pole.index(2) == bt_gamer)) ||
                        ((pole.index(3) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(5) == bt_gamer)) ||
                        ((pole.index(6) == bt_gamer) && (pole.index(7) == bt_gamer) && (pole.index(8) == bt_gamer)) ||
                        ((pole.index(0) == bt_gamer) && (pole.index(3) == bt_gamer) && (pole.index(6) == bt_gamer)) ||
                        ((pole.index(1) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(7) == bt_gamer)) ||
                        ((pole.index(2) == bt_gamer) && (pole.index(5) == bt_gamer) && (pole.index(8) == bt_gamer)) ||
                        ((pole.index(0) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(8) == bt_gamer)) ||
                        ((pole.index(2) == bt_gamer) && (pole.index(4) == bt_gamer) && (pole.index(6) == bt_gamer))
        ) {
            return true;
        }

        return false;
    }

}