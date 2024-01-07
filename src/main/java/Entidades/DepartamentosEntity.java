package Entidades;

public class DepartamentosEntity {
    private int idDepartamento;
    private String nombreDepartamento;
    private Integer idDirector;
    private Integer idLocalizacion;

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartamentosEntity that = (DepartamentosEntity) o;

        if (idDepartamento != that.idDepartamento) return false;
        if (nombreDepartamento != null ? !nombreDepartamento.equals(that.nombreDepartamento) : that.nombreDepartamento != null)
            return false;
        if (idDirector != null ? !idDirector.equals(that.idDirector) : that.idDirector != null) return false;
        if (idLocalizacion != null ? !idLocalizacion.equals(that.idLocalizacion) : that.idLocalizacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDepartamento;
        result = 31 * result + (nombreDepartamento != null ? nombreDepartamento.hashCode() : 0);
        result = 31 * result + (idDirector != null ? idDirector.hashCode() : 0);
        result = 31 * result + (idLocalizacion != null ? idLocalizacion.hashCode() : 0);
        return result;
    }
}
