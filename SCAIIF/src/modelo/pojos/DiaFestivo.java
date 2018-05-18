package modelo.pojos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DiaFestivo {
    private Integer idDiasFestivos;
    private Date diaFestivo;
    private Integer idPeriodo;

    public DiaFestivo() {
    }

    public DiaFestivo(Integer idDiasFestivos, Date diaFestivo, Integer idPeriodo) {
        this.idDiasFestivos = idDiasFestivos;
        this.diaFestivo = diaFestivo;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdDiasFestivos() {
        return idDiasFestivos;
    }

    public void setIdDiasFestivos(Integer idDiasFestivos) {
        this.idDiasFestivos = idDiasFestivos;
    }

    public Date getDiaFestivo() {
        return diaFestivo;
    }

    public String getFormatoDiaFestivo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM", new Locale("ES"));
        String diaFestivoFormato = sdf.format(diaFestivo);
        return diaFestivoFormato;
    }

    public void setDiaFestivo(Date diaFestivo) {
        this.diaFestivo = diaFestivo;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM", new Locale("ES"));
        String diaFestivoFormato = sdf.format(diaFestivo);
        return diaFestivoFormato;
    }
}
