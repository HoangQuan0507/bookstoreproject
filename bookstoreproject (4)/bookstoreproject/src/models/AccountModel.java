package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import entities.Account;

public class AccountModel {
	public boolean create(Account account) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"INSERT INTO  account (   name ,  password ,  fullname ,  birthday ,  status ,  phone ,  email ,address,  position_id ) VALUES (?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, account.getName());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getFullname());
			preparedStatement.setDate(4, new Date(account.getBirthday().getTime()));
			preparedStatement.setBoolean(5, false);
			preparedStatement.setString(6, account.getPhone());
			preparedStatement.setString(7, account.getEmail());
			preparedStatement.setString(8, account.getAddress());
			preparedStatement.setInt(9, account.getPosition_id());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean update(Account account) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"update account set name = ? ,password = ? , fullname = ? , birthday = ? ,status = ?, phone = ?,email =? ,address = ?, position_id = ? where id = ?");
			preparedStatement.setString(1, account.getName());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getFullname());
			preparedStatement.setDate(4, new Date(account.getBirthday().getTime()));
			preparedStatement.setBoolean(5, account.isStatus());
			preparedStatement.setString(6, account.getPhone());
			preparedStatement.setString(7, account.getEmail());
			preparedStatement.setString(8, account.getAddress());
			preparedStatement.setInt(9, account.getPosition_id());
			preparedStatement.setInt(10, account.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean activePass(String username, String password) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from account where name = ? and password = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updatePass(Account account, String password) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("update account set password = ?  where id = ?");

			preparedStatement.setString(1, password);

			preparedStatement.setInt(2, account.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from account");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setName(resultSet.getString("name"));
				account.setId(resultSet.getInt("id"));
				account.setFullname(resultSet.getString("fullname"));
				account.setEmail(resultSet.getString("email"));
				account.setAddress(resultSet.getString("address"));
				account.setPosition_id(resultSet.getInt("position_id"));
				account.setPhone(resultSet.getString("phone"));
				account.setPassword(resultSet.getString("password"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setStatus(resultSet.getBoolean("status"));
				accounts.add(account);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			accounts = null;
		}
		return accounts;
	}

	public Account findPhone(String phone) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from account where phone = ?");
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				account = new Account();
				account.setName(resultSet.getString("name"));
				account.setId(resultSet.getInt("id"));
				account.setFullname(resultSet.getString("fullname"));
				account.setEmail(resultSet.getString("email"));
				account.setPosition_id(resultSet.getInt("position_id"));
				account.setPhone(resultSet.getString("phone"));
				account.setAddress(resultSet.getString("address"));
				account.setPassword(resultSet.getString("password"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setStatus(resultSet.getBoolean("status"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			account = null;
		}
		return account;
	}

	public Account find(String name) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from account where name = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				account = new Account();
				account.setName(resultSet.getString("name"));
				account.setId(resultSet.getInt("id"));
				account.setFullname(resultSet.getString("fullname"));
				account.setEmail(resultSet.getString("email"));
				account.setPosition_id(resultSet.getInt("position_id"));
				account.setPhone(resultSet.getString("phone"));
				account.setAddress(resultSet.getString("address"));
				account.setPassword(resultSet.getString("password"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setStatus(resultSet.getBoolean("status"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			account = null;
		}
		return account;
	}

	public Account findPass(String email, String phone) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from account where email = ? and phone = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				account = new Account();
				account.setName(resultSet.getString("name"));
				account.setId(resultSet.getInt("id"));
				account.setFullname(resultSet.getString("fullname"));
				account.setEmail(resultSet.getString("email"));
				account.setPosition_id(resultSet.getInt("position_id"));
				account.setPhone(resultSet.getString("phone"));
				account.setAddress(resultSet.getString("address"));
				account.setPassword(resultSet.getString("password"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setStatus(resultSet.getBoolean("status"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			account = null;
		}
		return account;
	}

	public Account find(int id) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from account where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				account = new Account();
				account.setName(resultSet.getString("name"));
				account.setId(resultSet.getInt("id"));
				account.setFullname(resultSet.getString("fullname"));
				account.setEmail(resultSet.getString("email"));
				account.setPosition_id(resultSet.getInt("position_id"));
				account.setPhone(resultSet.getString("phone"));
				account.setAddress(resultSet.getString("address"));
				account.setPassword(resultSet.getString("password"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setStatus(resultSet.getBoolean("status"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			account = null;
		}
		return account;
	}

	public List<Account> search(String keyword) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from account where fullname like ?");
			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("id"));
				account.setName(resultSet.getString("name"));
				account.setPassword(resultSet.getString("password"));
				account.setFullname(resultSet.getString("fullname"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setStatus(resultSet.getBoolean("status"));
				account.setAddress(resultSet.getString("address"));
				account.setPosition_id(resultSet.getInt("position_id"));
				account.setEmail(resultSet.getString("email"));
				account.setPhone(resultSet.getString("phone"));
				accounts.add(account);
			}
		} catch (Exception e) {
			accounts = null;
		}
		return accounts;
	}

	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("delete from account where id = ?");
			preparedStatement.setInt(1, id);

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public Account login(String username, String password) {
		Account account = find(username);
		if (account != null) {
			if (BCrypt.checkpw(password, account.getPassword())) {
				return account;
			}
		}
		return null;
	}
}
