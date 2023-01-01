package com.example.exampletest.reflection;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Class<Book> bookClass = Book.class;

        new Main().reflectionFieldTest();

        new Main().reflectionMethodTest();

        new Main().reflectionInterfaceTest();

        System.out.println(MyBook.class.getSuperclass());
    }

    private void reflectionInterfaceTest() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }

    private void reflectionMethodTest() {
        Class<Book> bookClass = Book.class;

        System.out.println("모든 메서드 출력");
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
    }

    private void reflectionFieldTest() {
        Class<Book> bookClass = Book.class;
        // public 한 맴버 필드만 가져옴
        System.out.println("public 한 맴버 필드만 가져옴");
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        // 모든 필드를 다 가져옴
        System.out.println("모든 필드를 다 가져옴");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        // 모든 필드의 값까지 출력
        System.out.println("모든 필드의 값까지 출력");
        Book book = new Book();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s %s", f, f.get(book));
                System.out.println();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private void reflectionTest() throws ClassNotFoundException {
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // 만약 경로에 해당 클래스 정보가 없으면 ClassNotFoundException 발생
        Class<?> aClass1 = Class.forName("com.example.exampletest.reflection.Book");
    }

}
