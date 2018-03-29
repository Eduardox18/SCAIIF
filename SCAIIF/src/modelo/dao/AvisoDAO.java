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
     * @return True en caso de que sea guardado correctamente, false en caso de que ocurra algún
     * error a la hora de guardarlo1
     * @throws Exception 
     */
    public static boolean guardarAviso(Aviso aviso) throws Exception{
        boolean resul = false;
        SqlSession conn = null;
        
        if (aviso != null){
            try{
                conn = MyBatisUtils.getSession();
                conn.insert("Aviso.guardarAviso", aviso);
                conn.commit();
                resul = true;
            } finally {
                if(conn != null){
                    conn.close();
                }
            }
        }
        return resul;
    }
    
}
