package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.Aviso;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lalo
 */
public class AvisoDAO {
    
    /**
     * Guarda un aviso en la base de datos
     * @param aviso El objeto aviso que contiene los datos que se desean guardar
     * @throws Exception 
     */
    public static void guardarAviso(Aviso aviso) throws Exception{
        SqlSession conn = null;
        
        if (aviso != null){
            try{
                conn = MyBatisUtils.getSession();
                conn.insert("Aviso.guardarAviso", aviso);
                conn.commit();
            } finally {
                if(conn != null){
                    conn.close();
                }
            }
        }
    }
    
}
