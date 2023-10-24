package com.libraryManagement.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.libraryManagement.exception.ExceptionMessage.INVALID_BOOK_MENU;
import static java.lang.System.exit;

public class BookMenu {

    private final List<String> menuList;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final String NEW_LINE = System.lineSeparator();

    public BookMenu() {
        menuList = new ArrayList<>();

        menuList.add("사용할 기능을 선택해주세요.");
        menuList.add("도서 등록");
        menuList.add("전체 도서 목록 조회");

        menuList.add("제목으로 도서 검색");
        menuList.add("도서 대여");
        menuList.add("도서 반납");

        menuList.add("도서 분실");
        menuList.add("도서 삭제");
    }

    public int startBookMenu() {

        int selectedBookMenuNum;

        while(true){
            for(int i = 0; i < menuList.size(); i++){
                System.out.println(i + ". " + menuList.get(i));
            }
            System.out.print(NEW_LINE);

            try {
                selectedBookMenuNum = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean isRepeat = false;

            switch (selectedBookMenuNum) {
                case 0 :
                    System.out.println("[System] 0번을 입력하여 프로그램을 종료합니다." + NEW_LINE);
                    exit(0);
                    break;
                case 1 :
                    System.out.println("[System] 도서 등록 메뉴로 넘어갑니다." + NEW_LINE);
                    break;
                case 2 :
                    System.out.println("[System] 전체 도서 목록입니다." + NEW_LINE);
                    break;
                case 3 :
                    System.out.println("[System] 제목으로 도서 검색 메뉴로 넘어갑니다." + NEW_LINE);
                    break;
                case 4 :
                    System.out.println("[System] 도서 대여 메뉴로 넘어갑니다." + NEW_LINE);
                    break;
                case 5 :
                    System.out.println("[System] 도서 반납 메뉴로 넘어갑니다." + NEW_LINE);
                    break;
                case 6 :
                    System.out.println("[System] 도서 분실 처리 메뉴로 넘어갑니다." + NEW_LINE);
                    break;
                case 7 :
                    System.out.println("[System] 도서 삭제 처리 메뉴로 넘어갑니다." + NEW_LINE);
                    break;
                default :
                    System.out.println(INVALID_BOOK_MENU.getMessage() + NEW_LINE);
                    isRepeat = true;
            }

            if(!isRepeat) break;
        }

        return selectedBookMenuNum;
    }

}
