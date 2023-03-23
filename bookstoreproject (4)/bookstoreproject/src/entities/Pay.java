package entities;

import java.util.Date;

public class Pay {
	private int id;
	private int id_borrow;
	private Date pay_day;
	private double late_day;
	
	public double getLate_day() {
		return late_day;
	}
	public void setLate_day(double late_day) {
		this.late_day = late_day;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_borrow() {
		return id_borrow;
	}
	public void setId_borrow(int id_borrow) {
		this.id_borrow = id_borrow;
	}
	public Date getPay_day() {
		return pay_day;
	}
	public void setPay_day(Date pay_day) {
		this.pay_day = pay_day;
	}
}
