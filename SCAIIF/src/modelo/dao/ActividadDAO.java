package modelo.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Actividad;

/**
 *
 * @author lalo
 */
public class ActividadDAO {

    public static List<Actividad> recuperarHistorial(int noPersonal) throws Exception {
        SqlSession conn = null;
        List<Actividad> historialReservaciones = new ArrayList<>();
        try {
            conn = MyBatisUtils.getSession();
            historialReservaciones = conn.selectList("Actividad.getActividades", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return historialReservaciones;
    }

    public static List<Actividad> recuperarActividadesAsesor(int noPersonal, Date fecha) 
            throws Exception {
        List<Actividad> actividadesProximas = new ArrayList<>();
        Actividad actividad = new Actividad();
        actividad.setNoPersonal(noPersonal);
        actividad.setFecha(fecha);
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            actividadesProximas = conn.selectList("Actividad.recuperarActividadesAsesor",
                    actividad);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return actividadesProximas;
    }
}
