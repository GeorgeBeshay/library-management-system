package maids.cc.src.DMOs;

import java.util.Objects;

public class Borrowing {

    private int patronId;
    private int bookId;
    private String startDate;
    private String endDate;

    public Borrowing() {}

    public Borrowing(int patronId, int bookId, String startDate, String endDate) {
        this.patronId = patronId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return getPatronId() == borrowing.getPatronId() && getBookId() == borrowing.getBookId() && Objects.equals(getStartDate(), borrowing.getStartDate()) && Objects.equals(getEndDate(), borrowing.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatronId(), getBookId(), getStartDate(), getEndDate());
    }
}
