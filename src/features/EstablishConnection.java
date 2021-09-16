package features;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

public class EstablishConnection {
    static Logger logger=Logger.getLogger(EstablishConnection.class);
    public static  Connection setConnection(){
        Connection connection=null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "admin");
        }catch (Exception e){
            logger.info(e);
        }
        return connection;
    }
}
