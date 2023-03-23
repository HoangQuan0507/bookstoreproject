package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.Author;

public class AuthorModel {
	public boolean create(Author author) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  author (   name, story ) VALUES (?,?)");
			preparedStatement.setString(1, author.getName());
			
			preparedStatement.setString(2, author.getStory());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean update(Author author) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update author set name=?,story=? where id = ?");
			preparedStatement.setString(1, author.getName());
		
			preparedStatement.setString(2, author.getStory());
		
			preparedStatement.setInt(3, author.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from author where id = ?");
			preparedStatement.setInt(1, id);
			
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public List<Author> findAll(){
		List<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from author");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Author author = new Author();
				author.setName(resultSet.getString("name"));
				author.setId(resultSet.getInt("id"));
				author.setStory(resultSet.getString("story"));
			
				authors.add(author);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			authors = null;
		}
		return authors;
	}
	public Author find(int id){
		Author author = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from author where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				author = new Author();
				author.setName(resultSet.getString("name"));
				author.setId(resultSet.getInt("id"));
				author.setStory(resultSet.getString("story"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			author = null;
		}
		return author;
	}
	public Author find(String name){
		Author author = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from author where name like ?");
			preparedStatement.setString(1, "%" + name +"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				author = new Author();
				author.setName(resultSet.getString("name"));
				author.setId(resultSet.getInt("id"));
				author.setStory(resultSet.getString("story"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			author = null;
		}
		return author;
	}
}
