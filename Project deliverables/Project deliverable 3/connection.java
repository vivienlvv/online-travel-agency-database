package sqlProject;

import java.sql.* ;

public class DatabaseConnection {
	
	private static final String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
	private static final String user = "cs421g55";
	private static final String password = "cs421h4H4";
	public static Connection con;
	public static Statement statement;
	
	//constructor registers a database manager
	public DatabaseConnection() throws SQLException {
		try {
			DriverManager.registerDriver ( new org.postgresql.Driver() ) ;
		} catch (Exception cnfe){
			System.out.println("Class not found");
		}
	}
	
	//creates a connection
	public static Connection createConnection() throws SQLException {
		Connection connection = null;
		try {
			con = DriverManager.getConnection (url,user, password) ;
			statement = con.createStatement ( ) ;
			return con;
		} catch (SQLException e) {
			System.out.println("Error: trouble forming the connection and statement");
		}
		return null;	
	}
	//helper function for selecting multiple attributes. 
	private static int parseInput(String attribute) {
		int count=1;
		for (int i=0;i<attribute.length();i++) {
			if (attribute.charAt(i)==',') {
				count++;
			}
		}
		return count;
	}
	
	private static String selectAll(String attribute, String tableName) {
		String output="";
		try { String querySql = "SELECT " + attribute +" from " + tableName;
		System.out.println(querySql);
		ResultSet rs=statement.executeQuery(querySql);
		int colCount=rs.getMetaData().getColumnCount();
		
		while (rs.next()) {
			for (int i=1;i<=colCount;i++) {
				output+=rs.getString(i)+"   ";
			}
			output+="\n";
		}
	}
	catch (SQLException e) {
		int sqlCode = e.getErrorCode(); // Get SQLCODE
		String sqlState = e.getSQLState(); // Get SQLSTATE
                
		// Your code to handle errors comes here;
		// something more meaningful than a print would be good
		System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
	    }
		return output;
	}
	
	//does a select query for choice 1
	public static String choiceOne(String attribute, String tableName) throws SQLException {
		String output = attribute+"\n";
		try {
			//Trying to do SELECT * FROM table
			if (attribute.equals("*")) {
				output=selectAll(attribute,tableName);
			}
			
			else { String querySQL = "SELECT " + attribute + " from " + tableName;
		   int count= parseInput(attribute);
		    System.out.println (querySQL) ;
		    ResultSet rs = statement.executeQuery(querySQL);
		    while (rs.next() ) {
		    	for (int i=1;i<=count;i++) {
		    	output += rs.getString(i)+"  ";
		    	}
		    	output+="\n";
		    }
		    rs.close();
		}
			
		    System.out.println ("DONE");
		   
		} catch (SQLException e)
		    {
			int sqlCode = e.getErrorCode(); // Get SQLCODE
			String sqlState = e.getSQLState(); // Get SQLSTATE
	                
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		    }
		System.out.println(output);
		 return output;
	}
	
	//choice 2
	public static String choiceTwo() throws SQLException {
		String output = "=";
		try {
		    String querySQL = "SELECT COUNT ('custid') from Customer";
		    System.out.println (querySQL) ;
		    ResultSet rs = statement.executeQuery(querySQL);
		    while (rs.next()) {
		    	output += rs.getString(1)+"\n";
		    }
		    System.out.println ("DONE");
		    rs.close();
		} catch (SQLException e)
		    {
			int sqlCode = e.getErrorCode(); // Get SQLCODE
			String sqlState = e.getSQLState(); // Get SQLSTATE
	                
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		    }
		System.out.println(output);
		
		return output;
	}
	
	//choice 3
		public static String choiceThree(String tableName, String attribute,String parameter) throws SQLException {
			String output = "";
			try {
				//if we dont have any input in the columns 
				if (attribute.equals("Columns (optional)")) {
					Statement temp=con.createStatement();
					//do an insert, then display the altered table 
					String querySQL = "INSERT INTO "+tableName+ " VALUES "+"(" +parameter+ ")";
					String querySQL2="SELECT * FROM "+tableName;
					temp.addBatch(querySQL);
					temp.addBatch(querySQL2);
					System.out.println(querySQL);
					temp.executeBatch();
					output=selectAll("*", tableName);
					System.out.println(output);
					temp.close();
					}
				else {
					//creating data for certain columns
				Statement temp=con.createStatement();
			    String querySQL = "INSERT INTO "+ tableName +" (" + attribute + ") VALUES " +"(" +parameter+ ") ";
			    String querySQL2="SELECT * FROM "+tableName;
				temp.addBatch(querySQL);
				temp.addBatch(querySQL2);
				System.out.println(querySQL);
				temp.executeBatch();

				output=selectAll("*", tableName);
				temp.close();
				System.out.println(output);
				
				}
			} catch (SQLException e)
			    {
				int sqlCode = e.getErrorCode(); // Get SQLCODE
				String sqlState = e.getSQLState(); // Get SQLSTATE
		                
				// Your code to handle errors comes here;
				// something more meaningful than a print would be good
				System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			    }
			System.out.println(output);
			 return output;
		}
		
		//choice 3
				public static String choiceThree1(String tableName, String attribute,String parameter) throws SQLException {
					String output = "";
					try {
							//creating data for certain columns
						Statement temp=con.createStatement();
						String querySQL = "DELETE FROM "+tableName+ " WHERE "+attribute+" IN "+"("+parameter+")";
					    String querySQL2="SELECT * FROM "+tableName;
						temp.addBatch(querySQL);
						temp.addBatch(querySQL2);
						System.out.println(querySQL);
						temp.executeBatch();

						output=selectAll("*", tableName);
						temp.close();
						System.out.println(output);
						
					} catch (SQLException e)
					    {
						int sqlCode = e.getErrorCode(); // Get SQLCODE
						String sqlState = e.getSQLState(); // Get SQLSTATE
				                
						// Your code to handle errors comes here;
						// something more meaningful than a print would be good
						System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
					    }
					System.out.println(output);
					 return output;
				}
				
		public static String choiceFour() throws SQLException{
			String output="custid\n";
			try {
				String querySQL="SELECT customer.custid FROM customer EXCEPT SELECT associates.custid FROM associates";
				ResultSet rs=statement.executeQuery(querySQL);
				while (rs.next()) {
			    	output += rs.getString(1)+"\n";
			    }
				System.out.println(output);
				
			}catch (SQLException e)
		    {
			int sqlCode = e.getErrorCode(); // Get SQLCODE
			String sqlState = e.getSQLState(); // Get SQLSTATE
	                
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		    }
			return output;

		}
	
		public static String choiceFive() throws SQLException{
			String output="eid     count(sessionid)\n";
			try {
				String querySQL="SELECT eid, COUNT(sessionid) FROM (SELECT employee.eid,consults.sessionid FROM employee LEFT JOIN consults on employee.eid=consults.eid) as A GROUP BY A.eid";
				ResultSet rs=statement.executeQuery(querySQL);
				while (rs.next()) {
			    	output += rs.getString(1)+"  "+rs.getString(2)+"\n";
			    }
				System.out.println(output);
			}catch (SQLException e)
		    {
			int sqlCode = e.getErrorCode(); // Get SQLCODE
			String sqlState = e.getSQLState(); // Get SQLSTATE
	                
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		    }
			return output;

		}
	//close the connection
	public void closeConnection() throws SQLException {
		statement.close ( ) ;
		con.close ( ) ;
	}
		
	
}
