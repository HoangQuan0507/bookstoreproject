package models;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import entities.Book;


public class BookModel {
	public boolean create(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  book ( name, id_category, id_author, quantity, status, price, call_number, isbn, tittle) VALUES (?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, book.getName());
			preparedStatement.setInt(2, book.getId_category());
			preparedStatement.setInt(3, book.getId_author());
			preparedStatement.setInt(4, book.getQuantity());
			preparedStatement.setBoolean(5, true);
			preparedStatement.setDouble(6, book.getPrice());
			preparedStatement.setString(7, book.getCall_number());
			preparedStatement.setString(8, book.getIsbn());
			preparedStatement.setString(9, book.getTittle());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean createCart(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  cart (id, name, id_category, id_author, quantity, status, price, call_number, isbn, tittle) VALUES (?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, book.getId());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setInt(3, book.getId_category());
			preparedStatement.setInt(4, book.getId_author());
			preparedStatement.setInt(5, book.getQuantity());
			preparedStatement.setBoolean(6, true);
			preparedStatement.setDouble(7, book.getPrice());
			preparedStatement.setString(8, book.getCall_number());
			preparedStatement.setString(9, book.getIsbn());
			preparedStatement.setString(10, book.getTittle());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public Book searchBookCart(int id){
		Book book = new Book();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from cart where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				
			}
		} catch (Exception e) {
			book = null;
		}
		return book;
	}
	public List<Book> findAllCart(){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from cart");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			books = null;
		}
		return books;
	}
	public boolean update(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update book set name = ? ,id_category = ? , id_author = ? , quantity = ? ,status = ?, price = ?,call_number =? ,isbn = ?, tittle = ? where id = ?");
			preparedStatement.setString(1, book.getName());
			preparedStatement.setInt(2, book.getId_category());
			preparedStatement.setInt(3, book.getId_author());
			preparedStatement.setInt(4, book.getQuantity());
			preparedStatement.setBoolean(5, book.isStatus());
			preparedStatement.setDouble(6, book.getPrice());
			preparedStatement.setString(7, book.getCall_number());
			preparedStatement.setString(8, book.getIsbn());
			preparedStatement.setString(9, book.getTittle());
			preparedStatement.setInt(10, book.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updateBorrow(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update book set quantity = ? where id = ?");
	
			preparedStatement.setInt(1, book.getQuantity()-1);

			preparedStatement.setInt(2, book.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updatePay(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update book set quantity = ? where id = ?");
	
			preparedStatement.setInt(1, book.getQuantity()+1);

			preparedStatement.setInt(2, book.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updateStatus(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update book set status = ? where id = ?");
	
			preparedStatement.setBoolean(1, true);

			preparedStatement.setInt(2, book.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean updateStatusEmpty(Book book) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update book set status = ? where id = ?");
	
			preparedStatement.setBoolean(1, false);

			preparedStatement.setInt(2, book.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public List<Book> findAll(){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			books = null;
		}
		return books;
	}
	public List<Book> search(String keyword){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where name like ?");
			preparedStatement.setString(1, "%"+keyword +"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			books = null;
		}
		return books;
	}
	
	public Book searchBook(String keyword){
		Book book = new Book();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where name like ?");
			preparedStatement.setString(1, "%"+keyword );
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				
			}
		} catch (Exception e) {
			book = null;
		}
		return book;
	}
	public Book searchBook(int id){
		Book book = new Book();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				
			}
		} catch (Exception e) {
			book = null;
		}
		return book;
	}
	public List<Book> searchCategory(int id){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where id_category = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			books = null;
		}
		return books;
	}
	public List<Book> findAllInventory(){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where status = ?");
			preparedStatement.setBoolean(1, true);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			books = null;
		}
		return books;
	}
	public List<Book> searchAuthor(int id){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where id_author = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			books = null;
		}
		return books;
	}
	public List<Book> searchCallNumber(String call_number){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from book where call_number like ?");
			preparedStatement.setString(1, "%" + call_number +"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setId(resultSet.getInt("id"));
				book.setId_category(resultSet.getInt("id_category"));
				book.setCall_number(resultSet.getString("call_number"));
				book.setId_author(resultSet.getInt("id_author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTittle(resultSet.getString("tittle"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantity(resultSet.getInt("quantity"));
				book.setStatus(resultSet.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			books = null;
		}
		return books;
	}
	public boolean deleteCart(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from cart where id = ?");
			preparedStatement.setInt(1, id);
			
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean deleteAllCart() {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from cart ");
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from book where id = ?");
			preparedStatement.setInt(1, id);
			
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
}
