package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Borrow;
import entities.BorrowDetail;
import entities.Punish;

public class PunishModel {
	public boolean create(Punish punish) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  punish (   id_borrow, id_book  , today ) VALUES (?,?,?)");
			preparedStatement.setInt(1, punish.getId_borrow());
			preparedStatement.setInt(2, punish.getId_book());
		
			preparedStatement.setDate(3, new Date(punish.getToday().getTime()));
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public List<Punish> findAll(){
		List<Punish> punishs = new ArrayList<Punish>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from punish");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Punish punish = new Punish();

				punish.setId_borrow(resultSet.getInt("id_borrow"));
				punish.setId_book(resultSet.getInt("id_book"));
				punish.setToday(resultSet.getDate("today"));
				punishs.add(punish);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			punishs = null;
		}
		return punishs;
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
