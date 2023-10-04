package day01.consoleMvc;

import java.time.LocalDate;


public class ConsoleDto { // todo list 클래스

    private int tno;              // todo list 번호
    private String title;         // todo list 내용
    private LocalDate date;       // todo list 작성일
    private boolean finished;     // todo list 실행여부

    public ConsoleDto(){}

    public ConsoleDto(int tno, String title, LocalDate date, boolean finished) {
        this.tno = tno;
        this.title = title;
        this.date = date;
        this.finished = finished;
    }

    public int getTno() {
        return tno;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "ConsoleDto{" +
                "tno=" + tno +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", finished=" + finished +
                '}'+"\n";
    }
}
