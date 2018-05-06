package modelo.pojos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DiasFestivos {
    private Integer idDiasFestivos;
    private Date diaFestivo;
    private Integer idCalendario;

    public DiasFestivos() {
    }

    public DiasFestivos(Integer idDiasFestivos, Date diaFestivo, Integer idCalendario) {
        this.idDiasFestivos = idDiasFestivos;
        this.diaFestivo = diaFestivo;
        this.idCalendario = idCalendario;
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

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

}
