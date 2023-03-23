package entities;

import java.util.Date;

public class Punish {
	private int id_borrow;
	private int id_book;
	private Date today;
	public int getId_borrow() {
		return id_borrow;
	}
	public void setId_borrow(int id_borrow) {
		this.id_borrow = id_borrow;
	}
	public int getId_book() {
		return id_book;
	}
	public void setId_book(int id_book) {
		this.id_book = id_book;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
}
