package entities;

import java.util.Date;

public class PayDetail {
	private int id_pay;
	private int id_book;
	private double total;
	public int getId_pay() {
		return id_pay;
	}
	public void setId_pay(int id_pay) {
		this.id_pay = id_pay;
	}
	public int getId_book() {
		return id_book;
	}
	public void setId_book(int id_book) {
		this.id_book = id_book;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
