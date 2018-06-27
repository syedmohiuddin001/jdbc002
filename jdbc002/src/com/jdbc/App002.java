package com.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App002 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		BufferedReader br = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "syed");
			st = con.createStatement();
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Table Name :");
			String table_Name = br.readLine();
			String primary_Key_Cols = "";
			String query = "create table "+table_Name+"(";
			int primary_Key_Cols_Count = 0;
			while(true) {
				System.out.print("Column Name :");
				String col_Name = br.readLine();
				query = query + col_Name+" ";
				System.out.print("Column Type :");
				String col_Type = br.readLine();
				query = query + col_Type;
				System.out.print("Is Primary Key?[yes/no] :");
				String primary_Key_Option = br.readLine();
				if(primary_Key_Option.equals("yes")) {
					if(primary_Key_Cols_Count == 0) {
						primary_Key_Cols = primary_Key_Cols + col_Name;
					}else {
						primary_Key_Cols = primary_Key_Cols + "," + col_Name;
					}
					primary_Key_Cols_Count = primary_Key_Cols_Count + 1;
				}
				System.out.print("Onemore Column?[yes/no] :");
				String col_Option = br.readLine();
				if(col_Option.equals("yes")) {
					query = query + ",";
					continue;
				}else {
					query = query + ", primary key(" + primary_Key_Cols + "))";
					break;
				}
			}
			st.executeUpdate(query);
			System.out.println("Table " + table_Name + "Created Successfully" );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}