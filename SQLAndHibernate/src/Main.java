import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "VjqCRKserver";
        ArrayList<String> nameCourses = new ArrayList<>();

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet nC = statement.executeQuery("select name from courses;");
            while (nC.next()) {
                String name = nC.getString("name");
                nameCourses.add(name);
            }
            System.out.println("Среднее количество покупок в месяц");

            nameCourses.forEach(n -> {

                try {
                    statement.execute("drop view if exists data;");
                    statement.execute("create view data as " +
                            "select " +
                            "c.name, " +
                            "month(sub.subscription_date) month " +
                            "from courses c " +
                            "join subscriptions sub on c.id = sub.course_id " +
                            "where c.name = '" + n + "' " +
                            "order by sub.subscription_date;");

                    statement.execute("select count(*) cnt, max(month) mth from data;");
                    ResultSet avg = statement.executeQuery("select count(*) /  max(month) as avg from data;");
                    while (avg.next()) {
                        if (avg.getString("avg") != null) {
                            System.out.println(n + "\t\t" + avg.getString("avg"));
                        } else {
                            System.out.println(n + "\t\t0");
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
