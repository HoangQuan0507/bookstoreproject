package models;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import entities.Borrow;
import entities.Category;
import entities.Pay;
import entities.Total;

public class TotalModel {
	public Boolean create(Total total) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  total (   date ,total) VALUES (?,?)");
		
			
			preparedStatement.setDate(1, (java.sql.Date) new Date(total.getDate().getTime()));
			preparedStatement.setDouble(2, total.getTotal());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public Total find(Date date){
		Total total = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from total where date = ?");
			preparedStatement.setDate(1, (java.sql.Date) date);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				total = new Total();
				total.setTotal(resultSet.getDouble("total"));
				total.setId(resultSet.getInt("id"));
				total.setDate(resultSet.getDate("date"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			total = null;
		}
		return total;
	}
	public boolean update(Total total) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update total set total = ? where id = ?");
			preparedStatement.setDouble(1, total.getTotal());
	
			preparedStatement.setInt(2, total.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
