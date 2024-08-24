package windowsactivationcodegenerator.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Scanner;

@SpringBootApplication
public class WindowsActivationCodeGeneratorApplication {

	static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	static final String jdbcUrl = "jdbc:mysql://localhost:3306/windows_activation";
	static final String username = "root";
	static final String password = "gacorkangx999";

	static Connection connection;
	static Statement statement;
	static ResultSet resultSet;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(WindowsActivationCodeGeneratorApplication.class, args);

		try{
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			statement = connection.createStatement();

			boolean running = true;
			while (running) {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				printBanner();
				System.out.println();
				System.out.println("Windows 7/8/10/11 Activation Code Generator");
				System.out.println("Menu : ");
				System.out.println("1. Generate Windows 7 Activation Code");
				System.out.println("2. Generate Windows 8 Activation Code");
				System.out.println("3. Generate Windows 10 Activation Code");
				System.out.println("4. Generate Windows 11 Activation Code");
				System.out.println("5. Exit");
				System.out.print("Please enter your choice : ");
				int menu = scanner.nextInt();
				scanner.nextLine();

				switch (menu) {
					case 1:
						ListWindows7();
						break;
					case 2:
						ListWindows8();
						break;
					case 3:
						ListWindows10();
						break;
					case 4:
						ListWindows11();
						break;
					case 5:
						running = false;
						break;
					default:
						System.out.println("Menu Invalid");
				}
			}

			statement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException exception){
			exception.printStackTrace();
		}
	}
	private static void ListWindows7() throws SQLException {
		String sql = "SELECT * FROM windows_7";
		resultSet = statement.executeQuery(sql);

		// Adjust the format to add more space (equivalent to two tabulations)
		System.out.printf("%-12s\t\t%-30s\t\t%-20s\t\t%-30s%n", "Id", "Activation Code", "Version", "Created At");
		System.out.println("=========================================================================================================");

		while (resultSet.next()) {
			System.out.printf("%-12d\t\t%-30s\t\t%-20s\t\t%-30s%n",
					resultSet.getInt("id"),
					resultSet.getString("activation_code"),
					resultSet.getString("version"),
					resultSet.getString("created_at"));
		}
	}

	private static void ListWindows8() throws SQLException {
		String sql = "SELECT * FROM windows_8";
		resultSet = statement.executeQuery(sql);

		System.out.printf("%-12s\t\t%-30s\t\t%-20s\t\t%-30s%n", "Id", "Activation Code", "Version", "Created At");
		System.out.println("=========================================================================================================");

		while (resultSet.next()) {
			System.out.printf("%-12d\t\t%-30s\t\t%-20s\t\t%-30s%n",
					resultSet.getInt("id"),
					resultSet.getString("activation_code"),
					resultSet.getString("version"),
					resultSet.getString("created_at"));
		}
	}

	private static void ListWindows10() throws SQLException {
		String sql = "SELECT * FROM windows_10";
		resultSet = statement.executeQuery(sql);

		System.out.printf("%-12s\t\t%-30s\t\t%-20s\t\t%-30s%n", "Id", "Activation Code", "Version", "Created At");
		System.out.println("=========================================================================================================");

		while (resultSet.next()) {
			System.out.printf("%-12d\t\t%-30s\t\t%-20s\t\t%-30s%n",
					resultSet.getInt("id"),
					resultSet.getString("activation_code"),
					resultSet.getString("version"),
					resultSet.getString("created_at"));
		}
	}

	private static void ListWindows11() throws SQLException {
		String sql = "SELECT * FROM windows_11";
		resultSet = statement.executeQuery(sql);

		System.out.printf("%-12s\t\t%-30s\t\t%-20s\t\t%-30s%n", "Id", "Activation Code", "Version", "Created At");
		System.out.println("=========================================================================================================");

		while (resultSet.next()) {
			System.out.printf("%-12d\t\t%-30s\t\t%-20s\t\t%-30s%n",
					resultSet.getInt("id"),
					resultSet.getString("activation_code"),
					resultSet.getString("version"),
					resultSet.getString("created_at"));
		}
	}
	private static void printBanner() {
		System.out.println(":'######::::::'###:::::'######:::'#######::'########::'##::::'##::'#######:::'#######:::'#######::");
		System.out.println("'##... ##::::'## ##:::'##... ##:'##.... ##: ##.... ##:. ##::'##::'##.... ##:'##.... ##:'##.... ##:");
		System.out.println(" ##:::..::::'##:. ##:: ##:::..:: ##:::: ##: ##:::: ##::. ##'##::: ##:::: ##: ##:::: ##: ##:::: ##:");
		System.out.println(" ##::'####:'##:::. ##: ##::::::: ##:::: ##: ########::::. ###::::: ########:: ########:: ########:");
		System.out.println(" ##::: ##:: #########: ##::::::: ##:::: ##: ##.. ##::::: ## ##::::...... ##::...... ##::...... ##:");
		System.out.println(" ##::: ##:: ##.... ##: ##::: ##: ##:::: ##: ##::. ##::: ##:. ##::'##:::: ##:'##:::: ##:'##:::: ##:");
		System.out.println(". ######::: ##:::: ##:. ######::. #######:: ##:::. ##: ##:::. ##:. #######::. #######::. #######::");
		System.out.println(":......::::..:::::..:::......::::.......:::..:::::..::..:::::..:::.......::::.......::::.......:::");
		System.out.println();
	}


}
