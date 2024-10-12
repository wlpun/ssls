package com.zkz.domain;

public class Borrow {
    private int id;
    private int book_id;
    private int user_id;
    private String borrow_time;
    private String deadline;
    private boolean isReturn;
    private boolean isContinue;
    private String return_time;

    public Borrow() {
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setIsReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public void setIsContinue(boolean aContinue) {
        isContinue = aContinue;
    }


    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", user_id=" + user_id +
                ", borrow_time='" + borrow_time + '\'' +
                ", deadline='" + deadline + '\'' +
                ", iSReturn=" + isReturn +
                ", return_time='" + return_time + '\'' +
                ", isContinue=" + isContinue +
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

