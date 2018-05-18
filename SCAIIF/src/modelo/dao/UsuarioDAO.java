package modelo.dao;

import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Usuario;

import java.util.List;

/**
 *
 * @author lalo
 */
public class UsuarioDAO {

    /**
     * Recupera el nombre completo del asesor del que el Coordinador 
     * requiere consultar las actividades.
     * @param noPersonal identificador del asesor.
     * @return objeto usuario con el nombre completo del Asesor. 
     * @throws Exception 
     */
    public static Usuario recuperarAsesor(Integer noPersonal) throws Exception {
        Usuario nombreAsesor = null;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            nombreAsesor = conn.selectOne("Usuario.getNombreUsuario", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return nombreAsesor;
    }

    /**
     * Método que recupera los datos de un usuario a partir de su número de personal.
     * @param noPersonal Número de personal del usuario a recuperar
     * @return Regresa un objeto Usuario
     * @throws Exception 
     */
    public static Usuario recuperarUsuario(Integer noPersonal) throws Exception {
        Usuario ingresado = new Usuario();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            ingresado = conn.selectOne("Usuario.getUsuario", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return ingresado;
    }

    /**
     * Recupera todos los asesores registrados
     * @return
     * @throws Exception 
     */
    public static List<Usuario> recuperarAsesores() throws Exception {
        List<Usuario> asesores;
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            asesores = conn.selectList("Usuario.getAsesores");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return asesores;
    }
}
