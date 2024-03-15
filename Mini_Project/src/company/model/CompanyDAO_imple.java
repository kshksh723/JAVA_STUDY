package company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbconnection.MyDBConnection;

public class CompanyDAO_imple implements CompanyDAO {

	private Connection conn = MyDBConnection.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs= null;
	
	
}
