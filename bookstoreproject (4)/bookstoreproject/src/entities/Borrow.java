package entities;

import java.util.Date;

public class Borrow {
	private int id;
	private int id_account;
	private Date date_borrowed;
	private Date pay_day;
	private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_account() {
		return id_account;
	}
	public void setId_account(int id_account) {
		this.id_account = id_account;
	}
	public Date getDate_borrowed() {
		return date_borrowed;
	}
	public void setDate_borrowed(Date date_borrowed) {
		this.date_borrowed = date_borrowed;
	}
	public Date getPay_day() {
		return pay_day;
	}
	public void setPay_day(Date pay_day) {
		this.pay_day = pay_day;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	} 
	
}
