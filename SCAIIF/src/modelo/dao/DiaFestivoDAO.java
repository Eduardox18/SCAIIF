package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import modelo.pojos.DiasFestivos;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DiaFestivoDAO {

    /**
     * Recupera los días festivos de un calendario
     * @param idCalendario identificador del calendario
     * @return Lista de días festivos
     * @throws Exception
     */
    public static List<DiasFestivos> recuperarDiasFestivos (int idCalendario) throws Exception {
        List<DiasFestivos> diasFestivos;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            diasFestivos = conn.selectList("DiaFestivo.recuperarDiasFestivos");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return diasFestivos;
    }

    /**
     * Agrega un nuevo día festivo a un calendario
     * @param diaFestivo Día festivo a agregar
     * @return éxito o fracaso de la operación
     * @throws Exception
     */
    public static boolean agregarDiaFestivo (DiasFestivos diaFestivo) throws Exception {
        SqlSession conn = null;
        boolean resultado = false;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("DiaFestivo.agregarDiaFestivo", diaFestivo);
            conn.commit();
            resultado = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }

    /**
     * Borra un día festivo de un calendario
     * @param idDiasFestivos identificador del día festivo
     * @return éxito o fracaso de la operación
     * @throws Exception
     */
    public static boolean borrarDiaFestivo (int idDiasFestivos) throws Exception {
        boolean resultado = false;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.delete("DiaFestivo.borrarDiaFestivo", idDiasFestivos);
            conn.commit();
            resultado = true;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
}
