/*
 * package my.day06.a.multifor;
 * 
 * import java.util.Scanner;
 * 
 * 
 * >> 몇단볼래? => 8엔터
 * 
 * === 8단 === 8*1=8 8*2=16 8*3=24 8*4=32 8*5=40 8*6=48 8*7=56 8*8=64 8*9=72
 * 
 * >> 또 하시겠습니까?[Y/N] => y엔터 또는 Y엔터
 * 
 * >> 몇단볼래? => 1.34엔터 또는 똘똘이엔터 >>> 2단부터 9단까지만 가능합니다 <<<
 * 
 * >> 몇단볼래? => 345엔터 >>> 2단부터 9단까지만 가능합니다 <<<
 * 
 * >> 몇단볼래? => 3엔터
 * 
 * === 3단 === 3*1=3 3*2=6 3*3=9 3*4=12 3*5=15 3*6=18 3*7=21 3*8=24 3*9=27
 * 
 * >> 또 하시겠습니까?[Y/N] => s엔터 또는 S엔터 >>> Y 또는 N 만 가능합니다!! <<<
 * 
 * >> 또 하시겠습니까?[Y/N] => n엔터 또는 N엔터
 * 
 * == 프로그램종료 ==
 * 
 * public class Main_gugudan_2 {
 * 
 * public static void main(String[] args) {
 * 
 * Scanner sc = new Scanner(System.in);
 * 
 * 
 * === 레이블을 사용하여 break; 하기 === 레이블명 뒤에는 :을 붙이며 반드시 반복문 앞에 써야 한다!!
 * 
 * outer: //outer: 을 레이블이라고 부르는데 그 레이블명이 지금은 outer 일 뿐이다 // 무한 반복, n 값을 하면 빠져 나올
 * 것 for(;;) {
 * 
 * System.out.print("몇단 볼래? =>");
 * 
 * // sc.nextLine(); // 단은 정수로 본다
 * 
 * 
 * 
 * try {
 * 
 * int dan = Integer.parseInt(sc.nextLine());
 * 
 * System.out.println("====="+ dan +"단 ====" ); for(int i = 0; i<9; i++) {
 * System.out.println(dan+"*"+(i+1)+"="+ dan*(i+1));
 * 
 * } // end of for
 * 
 * for(;;) { System.out.print(">> 또 하시겠습니까?[Y/N]=>"); String yn = sc.nextLine();
 * 
 * // 해당하는 단을 출력하기// // if("n".equals(yn) || "N".equals(yn)){ // 또는 for(;;) {
 * if("n".equalsIgnoreCase(yn)) { // yn 값이 "n" 또는 "N"이라면
 * 
 * break outer; //레이블이 없으면 바깥 for을 나갈 방법이 없는데 for문 앞에 lable명을 주면 outer되어진 반복문이
 * for 전체를 빠져 나간다 // 반복문 중에 레이블명이 outer라고 선언되어진 반복문을 빠져나가는 것이다 // 즉, 가장 바깥에서
 * 선언되어진 for문을 빠져나가는 것이다
 * 
 * } else if("y".equalsIgnoreCase(yn)) { break; // yn 값이 "y" 또는 "Y"이라면 // 그냥
 * break;는 break; 와 가장 가까운 반복문을 빠져나가는 것이다
 * 
 * } else { /// yn 값이 "n" 또는 "N" 또는 "y" 또는 "Y"를 제외한 다른 글자인 경우
 * System.out.println(">>> Y 또는 N 만 가능합니다!! <<<"); continue; } } } // for문
 * break에서 빠져 나감
 * 
 * }
 * 
 * 
 * 
 * else { System.out.println(">>> 2단부터 9단까지만 가능합니다 <<<"); } break; // 정상일 때만 빠져
 * 나가야 함 } catch(NumberFormatException e) {
 * System.out.println(">>> 2단부터 9단까지만 가능합니다 <<<"); }
 * 
 * 
 * }//end of for
 * 
 * sc.close(); System.out.println("==프로그램 종료 =="); } }// end of main() ------
 * 
 * 
 */
package my.day06.a.multifor;

import java.util.Scanner;

public class Main_gugudan_2 {

   public static void main(String[] args) {
      
      Scanner sc = new Scanner(System.in);
      
      /*
       * == 레이블을 사용하여 break; 하기 
       *      레이블명 뒤에는 : 을 붙이며 반드시 반복문 앞에 써야 한다
       * */
      
      outer: //outer:을 레이블 이라고 부르는데 레이블이 있으면 반복문을 빠져나갈수 있는 key가 된다..
      for(;;) {
            
         System.out.println("몇단 볼래?");
         try {
            
            int dan = Integer.parseInt(sc.nextLine());
            
            System.out.println("===" + dan + "단 ===");
            for(int i = 0; i<9; i++) {
               System.out.println(dan + "*" + (i+1) + "=" + dan*(i+1));
            }

            
            for(;;) {
               System.out.print(" 또 하시겠습니까? [Y/N =>");
               String yn = sc.nextLine();
               
               if("n".equalsIgnoreCase(yn)) {
                  // yn값이 y또는 n이라면
                  //전체 for문을 빠져나가야함.
                  sc.close();
                  break outer; 
                  //반복문중에 레이블명이 outer라고 선언되어진 반복문을
                  //즉,가장 바깥에서 선언되어진 for문을 빠져나가는 것이다.
               } else if("y".equalsIgnoreCase(yn)) {
                  break;
               } else {
                  System.out.println("2단부터 9단까지만 가능합니다.");
                  continue;
               }
            }
         } catch(NumberFormatException e) {
            System.out.println(">>2단부터 9단까지만 가능합니다.<<");
         }
      }
      System.out.println("==프로그램종료==");
   }

}