package com.zkz.domain;

public class Fine {
    private int id;
    private int book_id;
    private int user_id;
    private double amount_of_penalty;
    private boolean isPay;
    private String borrow_time;
    public Fine() {
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", user_id=" + user_id +
                ", amount_of_penalty=" + amount_of_penalty +
                ", isPay=" + isPay +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getAmount_of_penalty() {
        return amount_of_penalty;
    }

    public void setAmount_of_penalty(double amount_of_penalty) {
        this.amount_of_penalty = amount_of_penalty;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setIsPay(boolean pay) {
        isPay = pay;
    }
}
