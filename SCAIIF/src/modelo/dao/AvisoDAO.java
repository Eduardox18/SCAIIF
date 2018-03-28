package modelo.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.util.converter.LocalDateTimeStringConverter;
import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Aviso;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lalo
 */
public class AvisoDAO {
    
    public static boolean guardarAviso(Aviso aviso) throws Exception{
        boolean resultado = false;
        SqlSession conn = null;
        
        try{
            conn = MyBatisUtils.getSession();
            conn.insert("Aviso.guardarAviso", aviso);
            conn.commit();
            resultado = true;
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return resultado;
    }
    
}
