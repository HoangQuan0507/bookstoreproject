package models;

import java.sql.PreparedStatement;

import entities.BorrowDetail;
import entities.PayDetail;

public class PayDetailModel {
	public boolean create(PayDetail payDetail) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  details_pay (   id_pay, id_book  , total ) VALUES (?,?,?)");
			preparedStatement.setInt(1, payDetail.getId_pay());
			preparedStatement.setInt(2, payDetail.getId_book());
		
			preparedStatement.setDouble(3, payDetail.getTotal());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
