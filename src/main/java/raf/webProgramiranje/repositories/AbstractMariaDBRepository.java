package raf.webProgramiranje.repositories;

import java.sql.*;
import java.util.Optional;

public class AbstractMariaDBRepository {



        public AbstractMariaDBRepository() {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); //TODO
            }
        }

        protected Connection newConnection() throws SQLException {


            return DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/webprojekat?user=root&password="   );

        }

        protected String getHost() {
            return "localhost";
        }

        protected int getPort() {
            return 3306;
        }

        protected String getDatabaseName() {
            return "root";
        }

        protected String getUsername() {
            return "root";
        }

        protected String getPassword() {
            return "";
        }

        protected void closeStatement(Statement statement) {
            try {
                Optional.of(statement).get().close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                System.out.println("OVDE");
            }
        }

        protected void closeResultSet(ResultSet resultSet) {
            try {
                Optional.of(resultSet).get().close();
            } catch (SQLException throwable) {
               throwable.printStackTrace();
                System.out.println("OVDE");
            }
        }

        protected void closeConnection(Connection connection) {
            try {

                Optional.of(connection).get().close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                System.out.println("OVDE");
            }
        }
    }


