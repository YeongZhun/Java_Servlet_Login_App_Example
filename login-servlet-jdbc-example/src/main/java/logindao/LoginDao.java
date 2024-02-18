package logindao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import loginbean.LoginBean;

public class LoginDao {

	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;

//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/login_app";
        String username = "YZ1";
        String password = "ILoveInfosys";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where username = ? and password = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            status = true;
	            loginBean.setName(rs.getString("name"));
	            loginBean.setPhoneNumber(rs.getString("phone_number")); // Assuming it's a String
	            System.out.println(loginBean.getPhoneNumber());
	            System.out.println(loginBean.getName());
	            
	        }

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return status;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}