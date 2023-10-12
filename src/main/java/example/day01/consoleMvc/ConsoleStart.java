package example.day01.consoleMvc;

import java.util.List;
import java.util.Scanner;

public class ConsoleStart {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while(true){
            doGet();
            doPost();

        } // while e

    } // main e

    public static void doGet(){
        ConsoleController consoleController = new ConsoleController();
        List<ConsoleDto> result =  consoleController.doGet();
        System.out.println(result);

    } // doGet e


    public static void doPost(){
        System.out.print("title : "); String title = scanner.next();
        ConsoleController consoleController = new ConsoleController();
        boolean result = consoleController.doPost(title);
        System.out.print(result);

    } // doPost e

}








/*

// syso : 이클립스 자동완성
// sout : 인텔리제이 자동완성
        System.out.println("sout -> println");
                System.out.printf("souf -> printf");
                System.out.println("consoleStart.main"); // soutm : 현재 실행중인 함수명
                System.out.println("args = " + Arrays.toString(args)); // soutp : 현재 실행중인 함수의 매개변수 출력
                System.out.println("args = " + args); // soutv : 변수 출력

*/
