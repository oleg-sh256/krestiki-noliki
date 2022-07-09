
/**********************************************************************************************************************/
/*Класс с игровой логикой. Здесь собраны все нужные проверки*/
/**********************************************************************************************************************/
public class logika {

    /******************************************************************************************************************/
    /*test_gamer_win - проверка выиграл ли кто-то?
    Параметры:
        boolean b_gamer - кого тестируем, юзера(true) или комп(false)?
    Возвращаемое значение:
        boolean - тестируемый игрок(юзер или комп) точно выиграл(истина)/неизвестность
    Особенности:
        0 1 2
        3 4 5
        6 7 8
    */
    /******************************************************************************************************************/

    public static boolean test_gamer_win(boolean b_gamer) {

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

    /******************************************************************************************************************/
    /*analiz_dvuh_znakov - поиск двух знаков подряд
    Параметры:
        boolean b_gamer - кого тестируем, юзера(true) или комп(false)?
    Возвращаемое значение:
        byte
            ячейка которую важно занять
    * */
    /******************************************************************************************************************/

    public static byte analiz_dvuh_znakov(boolean b_gamer) {

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

        System.out.println("debug: analiz_dvuh_znakov: " + (1+bt_vajno));
        return bt_vajno;
    }
}
