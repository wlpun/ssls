package com.zkz.domain;

public class MyBorrow {
    private int book_id;
    private String name;
    private String authors;
    private String borrow_time;
    private String deadline;
    private boolean isReturnBook;
    private String return_time;
    private boolean isToContinue;
    private boolean isTimeout;
    private boolean isPay;
    private double fineMoney;
    private int outDays;

    public MyBorrow() {
    }

    public int getOutDays() {
        return outDays;
    }

    public void setOutDays(int outDays) {
        this.outDays = outDays;
    }

    public double getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(double fineMoney) {
        this.fineMoney = fineMoney;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public boolean isReturnBook() {
        return isReturnBook;
    }

    public void setReturnBook(boolean aReturn) {
        isReturnBook = aReturn;
    }

    public boolean isToContinue() {
        return isToContinue;
    }

    public void setToContinue(boolean aContinue) {
        isToContinue = aContinue;
    }

    public boolean isTimeout() {
        return isTimeout;
    }

    public void setTimeout(boolean timeout) {
        isTimeout = timeout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

}
