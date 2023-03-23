package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.Position;

public class PositionModel {
	public List<Position> findAll(){
		List<Position> positions = new ArrayList<Position>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from position");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Position position = new Position();
				position.setId(resultSet.getInt("id"));
				position.setName(resultSet.getString("name"));
				positions.add(position);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			positions = null;
		}
		return positions;
	}
	public Position find(int id) {
		Position position = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("select * from position where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				position = new Position();
				position.setName(resultSet.getString("name"));
				position.setId(resultSet.getInt("id"));
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			position = null;
		}
		return position;
	}
}
