
import java.util.Scanner;

/**********************************************************************************************************************/
/*Главный файл программы. Интерфейсная часть вне игрового цикла*/
/**********************************************************************************************************************/

public class Main {

    /******************************************************************************************************************/
    /*Начало программы*/
    /******************************************************************************************************************/

    public static void main(String[] args) {

        System.out.println("\n****************************");
        System.out.println("*** Игра крестики-нолики ***");
        System.out.println("****************************");
        System.out.print("\nВыберете символ, которым вы будете играть,\nкрестик(1) или нолик(0): ");

        //ввод данных
        Scanner scanner_in = new Scanner(System.in);
        byte bt_num = scanner_in.nextByte();

        //чем играет пользователь: крестиком(true) или ноликом(false)
        boolean k_or_n = true;

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

        //Инициализация игрового поля
        pole.pole(k_or_n);

        //Запуск игрового цикла
        byte bt_result = Game.gameLoop();

        pole.printField();

        System.out.println("============================\n");

        if (bt_result == 1) {
            System.out.println("Вы выиграли!!!");
        } else if (bt_result == 0) {
            System.out.println("Вы проиграли=(");
        } else {
            System.out.println("Ничья...");
        }

        System.out.println("\nИгра закончена");

        scanner_in.close();
    }
}
