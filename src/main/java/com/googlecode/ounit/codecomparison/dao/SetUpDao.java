package com.googlecode.ounit.codecomparison.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.googlecode.ounit.codecomparison.util.FileUtil;
import com.googlecode.ounit.codecomparison.util.PropertyLoader;

public class SetUpDao {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void createSchema() {
		String contents = FileUtil.readFileFromClasspath("schema.sql");

		for (String statement : contents.split(";")) {
			if (statement.matches("\\s*")) {
				continue;
			}

			executeUpdate(statement);
		}
	}

	public void executeUpdate(String sql) {
		System.out.println("executeupdate");
		String url = new PropertyLoader().getProperty("db.url");
		try (Connection conn = DriverManager.getConnection(url, "hibuser", "root"); Statement stmt = conn.createStatement()) {

			System.out.println("executing: " + sql);

			// stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
