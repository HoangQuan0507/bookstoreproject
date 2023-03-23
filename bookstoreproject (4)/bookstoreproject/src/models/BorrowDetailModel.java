package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.Borrow;
import entities.BorrowDetail;

public class BorrowDetailModel {
	public boolean create(BorrowDetail borrowDetail) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  details_borrow (   id_borrow, id_book  , deposit ) VALUES (?,?,?)");
			preparedStatement.setInt(1, borrowDetail.getId_borrow());
			preparedStatement.setInt(2, borrowDetail.getId_book());
		
			preparedStatement.setDouble(3, borrowDetail.getDeposit());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean update(BorrowDetail borrowDetail) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update details_borrow set   deposit = ?  where id_book = ?");

			preparedStatement.setDouble(1, borrowDetail.getDeposit());
			preparedStatement.setInt(2, borrowDetail.getId_book());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public BorrowDetail find(int id_borrow , int id_book) {
		BorrowDetail borrowDetail = new BorrowDetail();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from details_borrow  where id_borrow = ? and id_book= ?");
			preparedStatement.setInt(1,id_borrow );
		
			preparedStatement.setInt(2, id_book);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				borrowDetail = new BorrowDetail();
				borrowDetail.setId_borrow(id_borrow);
				borrowDetail.setId_book(id_book);
			
				borrowDetail.setDeposit(resultSet.getDouble("deposit"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return borrowDetail = null;
		}
		return borrowDetail;
	}
	
	public List<BorrowDetail> find(int id){
		List<BorrowDetail> borrowDetails = new ArrayList<BorrowDetail>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from details_borrow where id_borrow = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BorrowDetail borrowDetail = new BorrowDetail();
				borrowDetail.setId_borrow(resultSet.getInt("id_borrow"));
				borrowDetail.setId_book(resultSet.getInt("id_book"));
		
				borrowDetail.setDeposit(resultSet.getDouble("deposit"));
				borrowDetails.add(borrowDetail);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			borrowDetails = null;
		}
		return borrowDetails;
	}
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from details_borrow where id_borrow = ?");
			preparedStatement.setInt(1, id);
			
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
