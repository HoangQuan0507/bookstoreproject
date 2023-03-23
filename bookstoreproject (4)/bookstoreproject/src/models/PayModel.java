package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Borrow;
import entities.Pay;

public class PayModel {
	public int create(Pay pay) {
		int id = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  pay (   id_borrow,pay_day ,late_day) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
		
			
			preparedStatement.setInt(1, pay.getId_borrow());
			preparedStatement.setDate(2, new Date(pay.getPay_day().getTime()));
			preparedStatement.setDouble(3, pay.getLate_day());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			return id = resultSet.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return id;
		}
	}
	public List<Pay> findAll(){
		List<Pay> pays = new ArrayList<Pay>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from pay");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pay pay = new Pay();

				pay.setId(resultSet.getInt("id"));
				pay.setId_borrow(resultSet.getInt("id_borrow"));

				pay.setPay_day(resultSet.getDate("pay_day"));
				pay.setLate_day(resultSet.getDouble("late_day"));
				pays.add(pay);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pays = null;
		}
		return pays;
	}
	public Pay searchPay(int id){
		Pay pay = new Pay();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from pay where id_borrow = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pay = new Pay();

				pay.setId(resultSet.getInt("id"));
				pay.setId_borrow(resultSet.getInt("id_borrow"));

				pay.setPay_day(resultSet.getDate("pay_day"));
				pay.setLate_day(resultSet.getDouble("late_day"));
			
			}
		} catch (Exception e) {
			pay = null;
		}
		return pay;
	}
}
