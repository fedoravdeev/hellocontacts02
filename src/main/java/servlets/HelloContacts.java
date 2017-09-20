package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/s2")
public class HelloContacts extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Sorry I didn't mean that!!!!! Contacts Get\n");

        try {
            Class.forName("org.h2.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        out.println("Class_for_Name(org.h2.Driver)");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("Connection");

        Statement st = null;
        try {
            out.println("createSQL");
            st = conn.createStatement();
            out.println("createStatement");
            st.execute("DROP TABLE STUDENTS IF EXISTS");
            out.println("drop");
            st.execute("CREATE TABLE STUDENTS(ID INT PRIMARY KEY, NAME VARCHAR(255),  HOBBY VARCHAR(255));");
            out.println("create");
            st.execute("INSERT INTO STUDENTS VALUES(1, 'Ivanov', '');");
            st.execute("INSERT INTO STUDENTS VALUES(2, 'Petrov', 'Swim');");
            st.execute("INSERT INTO STUDENTS VALUES(3, 'Sidorov', '');");
            st.execute("INSERT INTO STUDENTS VALUES(4, 'Andreev', 'Wrestling');");
            out.println("insert");
        } catch (SQLException e) {
            out.println("printStackTrace");
            e.printStackTrace();
        }
        out.println("Statement");

        ResultSet result = null;
        try {
            result = st.executeQuery("SELECT * FROM STUDENTS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("ResultSet");

        String id = "", name = "", hobby = "";
        out.println("Database h2");

        try {
            while (result.next()) {
                id = result.getString("ID");
                name = result.getString("NAME");
                hobby = result.getString("HOBBY");


                if (hobby.equals("")) {
                    hobby = "Нет хобби";
                }
                System.out.printf("%3s | %-10s | %-10s \n", id, name, hobby);
                out.println("id="+id+";name="+name+";hobby="+hobby);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPut(req, resp);
        PrintWriter out = response.getWriter();
        out.println("Sorry I didn't mean that!!!!! Contacts Put");
    }
}
