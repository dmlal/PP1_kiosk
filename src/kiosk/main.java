package kiosk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class main extends Menu{
    public static void main(String[] args) {
        ArrayList<MainMenu> food = new ArrayList<>();
        ArrayList<SideMenu> sideFood = new ArrayList<>();
        ArrayList<Beverage> drink = new ArrayList<>();


        food.add(new MainMenu("엽기떡볶이", 15000, "일반적인 떡볶이"));
        food.add(new MainMenu("마라떡볶이", 15000, "마라맛 떡볶이"));
        food.add(new MainMenu("로제떡볶이", 15000, "원픽 떡볶이"));
        
        
        sideFood.add(new SideMenu("참치마요밥", 3500, "참치마요 맛있어!"));
        sideFood.add(new SideMenu("셀프주먹밥", 2000, "떡볶이랑 최고의 궁합"));
        sideFood.add(new SideMenu("계란찜   ", 2000, "매울때 한숟가락 크.."));

        drink.add(new Beverage("쿨피스", 1000, "엽떡에 필수!"));
        drink.add(new Beverage("콜라  ", 2500, ""));
        drink.add(new Beverage("사이다", 2000, ""));

        topping.add(new PlusMenu("떡추가", 1000, "밀떡 추가"));
        topping.add(new PlusMenu("오뎅추가", 1000, "오뎅 추가"));
        topping.add(new PlusMenu("계란추가", 1500, "계란 2개 추가"));

        Scanner sc = new Scanner(System.in);
        int select = 0, num= 0;

        boolean isMenuAlreadyPrint = false; //메뉴판을 처음 한번만 보여주기 위한 변수

        Order order = new Order();


        while(true){

            if(!isMenuAlreadyPrint) {
                Menu.printMenu();
                isMenuAlreadyPrint = true;
            }
            String tmp = sc.nextLine();
            try {
                select = Integer.parseInt(tmp);
            } catch (Exception e) {
                System.out.println("입력 오류! 메뉴 목록을 입력해주세요.");
                continue;
            }
            if(select != 1 || select != 2 || select != 3 || select != 5 || select != 6 || select != 0 ){
                System.out.println("메뉴에 있는 숫자를 입력해주세요");
            }
            if (select == 6) {
                System.out.println("진행하던 주문을 취소하시겠습니까?");
                System.out.println("  1. 확인    2. 취소");
                int cancel = sc.nextInt();
                sc.nextLine();
                if (cancel == 1) {
                    order.cancelOrder();
                    System.out.println("\n주문이 취소되었습니다.");
                    isMenuAlreadyPrint = false;
                }else if (cancel==2){
                    isMenuAlreadyPrint = false;
                }
            }
            if (select == 1) {
                System.out.println("\n\n\n");
                System.out.println("[  "+ "M A I N  M E N U"+ "  ]");
                choiceMenu(food);
                order.pickMainMenu(food);
                isMenuAlreadyPrint = false;
            } else if (select == 2) {
                System.out.println("\n\n\n");
                System.out.println("[  "+ "S I D E  M E N U"+ "  ]");
                choiceMenu(sideFood);
                order.pickMenu(sideFood);
                isMenuAlreadyPrint = false;
            }else if (select == 3) {
                System.out.println("\n\n\n");
                System.out.println("[  "+ "BEVERAGE"+ "  ]");
                choiceMenu(drink);
                order.pickMenu(drink);
                isMenuAlreadyPrint = false;
            }
            if (select == 5) {
                order.printOrder();
                printMenu();
            }
            if (select == 0) {
                System.out.println("현재까지 판매된 메뉴는 아래와 같습니다.");
                Iterator it = OrderResult.getSelledMenu().iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
                System.out.println("\n총 판매액  " +OrderResult.getTotalSales() +"원");
                System.out.println("\n고객이 사용하는 기능이 아니기 때문에 메뉴판은 자동으로 출력되지 않습니다.");
                System.out.println("1 2 3 5 6을 눌러 이동해주세요.");
            }



        }
    }

    //인텔리제이가 자동으로 추가함.  이유는 모르겠음.  나중에 질문할것
//    private static void pickMenu(ArrayList<MainMenu> food) {
//    }
    public static ArrayList<PlusMenu> topping = new ArrayList<>();

    public static void choiceMenu(ArrayList<? extends Menu> menu){
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + "번 메뉴    " + menu.get(i));
        }
    }



}
