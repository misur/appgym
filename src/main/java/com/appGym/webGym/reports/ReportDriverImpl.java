package com.appGym.webGym.reports;

import java.sql.*;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

public class ReportDriverImpl {

	private static Connection connection;

	public static Conn readParm() {

		Conn c = new Conn();

		try {

			BufferedReader bf = new BufferedReader(new FileReader(new File(

			"src/main/resources/config.txt")));

			String dbUrl = bf.readLine();

			System.out.println("URL " + dbUrl);

			// dbUrl = dbUrl.substring(dbUrl.indexOf('=') + 1).replace(" ", "");

			c.setDbUrl(dbUrl);

			String dbClass = bf.readLine();

			// dbClass = dbClass.substring(dbUrl.indexOf('=') + 1)

			// .replace(" ", "");

			c.setDbClass(dbClass);

			String dbUser = bf.readLine();

			// dbUser = dbUser.substring(dbUrl.indexOf('=') + 1).replace(" ",

			// "");

			c.setDbUser(dbUser);

			String dbPass = bf.readLine();

			if (dbPass.equals("null")) {

				c.setDbPass("");

			} else {

				// dbPass = dbPass.substring(dbUrl.indexOf('=') +

				// 1).replace(" ",

				// "");

				c.setDbPass(dbPass);

			}

			return c;

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;

	}

	public static void disconectDB() {

		try {

			connection.close();

			connection = null;

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public static Connection connectDB() {

		try {

			Conn c = readParm();

			Class.forName(c.getDbClass());

			connection = DriverManager.getConnection(c.getDbUrl());

			// ,

			// c.getDbUser(), c.getDbPass());

			return connection;

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return null;

	}

}