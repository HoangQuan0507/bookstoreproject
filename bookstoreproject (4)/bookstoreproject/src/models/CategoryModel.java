package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Author;
import entities.Category;


public class CategoryModel {
	public List<Category> findAll(){		
		List<Category> categories = new ArrayList<Category>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from category");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			categories = null;
		}
		return categories;
	}
	public boolean create(Category category) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("INSERT INTO  category (   name ) VALUES (?)");
			preparedStatement.setString(1, category.getName());
			
	
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean update(Category category) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("update category set name=? where id = ?");
			preparedStatement.setString(1, category.getName());
		
		
		
			preparedStatement.setInt(2, category.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("delete from category where id = ?");
			preparedStatement.setInt(1, id);
			
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public Category find(int id){
		Category category = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from category where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				category = new Category();
				category.setName(resultSet.getString("name"));
				category.setId(resultSet.getInt("id"));
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			category = null;
		}
		return category;
	}
}
