package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.Book;
import entities.Borrow;

public class BorrowModel {
	public int create(Borrow borrow) {
		int id = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  borrow (   id_account, date_borrowed,pay_day,status ) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, borrow.getId_account());
			
			preparedStatement.setDate(2, new Date(borrow.getDate_borrowed().getTime()));
			preparedStatement.setDate(3, new Date(borrow.getPay_day().getTime()));
			preparedStatement.setBoolean(4, true);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			return id = resultSet.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return id;
		}
	}
	public boolean create2(Borrow borrow) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  borrow (   id_account, date_borrowed,pay_day,status ) VALUES (?,?,?,?)");
			preparedStatement.setInt(1, borrow.getId_account());
			
			preparedStatement.setDate(2, new Date(borrow.getDate_borrowed().getTime()));
			preparedStatement.setDate(3, new Date(borrow.getPay_day().getTime()));
			preparedStatement.setBoolean(4, true);
			
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean update(Borrow borrow) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update borrow set status = ? where id = ?");
			preparedStatement.setBoolean(1, false);
	
			preparedStatement.setInt(2, borrow.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public Borrow searchBorrow(int id){
		Borrow borrow = new Borrow();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from borrow where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				borrow = new Borrow();

				borrow.setId(resultSet.getInt("id"));
				borrow.setId_account(resultSet.getInt("id_account"));
				borrow.setDate_borrowed(resultSet.getDate("date_borrowed"));
				borrow.setPay_day(resultSet.getDate("pay_day"));
				borrow.setStatus(resultSet.getBoolean("status"));
			
			}
		} catch (Exception e) {
			borrow = null;
		}
		return borrow;
	}
	public List<Borrow> findAll(){
		List<Borrow> borrows = new ArrayList<Borrow>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from borrow ");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Borrow borrow = new Borrow();

				borrow.setId(resultSet.getInt("id"));
				borrow.setId_account(resultSet.getInt("id_account"));
				borrow.setDate_borrowed(resultSet.getDate("date_borrowed"));
				borrow.setPay_day(resultSet.getDate("pay_day"));
				borrow.setStatus(resultSet.getBoolean("status"));
				borrows.add(borrow);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			borrows = null;
		}
		return borrows;
	}
	public List<Borrow> find(int id){
		List<Borrow> borrows = new ArrayList<Borrow>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from borrow where id_account = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Borrow borrow = new Borrow();

				borrow.setId(resultSet.getInt("id"));
				borrow.setId_account(resultSet.getInt("id_account"));
				borrow.setDate_borrowed(resultSet.getDate("date_borrowed"));
				borrow.setPay_day(resultSet.getDate("pay_day"));
				borrow.setStatus(resultSet.getBoolean("status"));
				borrows.add(borrow);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			borrows = null;
		}
		return borrows;
	}
}
