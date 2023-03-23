package entities;

public class BorrowDetail {
	private int id_borrow;
	private int id_book;
	private int quantity;
	private double deposit;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	
}
