package com.example.exampletest.reflection;

import java.util.Objects;

public class Book {

    public static String A = "A";

    private String B = "B";

    public Book() {
    }

    public Book(String b) {
        B = b;
    }

    public void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        return Objects.equals(B, book.B);
    }

    @Override
    public int hashCode() {
        return B != null ? B.hashCode() : 0;
    }
}
