package org.acdat.entitiesXML;

public class DestinosAgenciasJPA {
    private int destinoId;
    private int agenciaId;
    private DestinosJPA destinosByDestinoId;
    private AgenciasJPA agenciasByAgenciaId;

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DestinosAgenciasJPA that = (DestinosAgenciasJPA) o;

        if (destinoId != that.destinoId) return false;
        if (agenciaId != that.agenciaId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = destinoId;
        result = 31 * result + agenciaId;
        return result;
    }

    public DestinosJPA getDestinosByDestinoId() {
        return destinosByDestinoId;
    }

    public void setDestinosByDestinoId(DestinosJPA destinosByDestinoId) {
        this.destinosByDestinoId = destinosByDestinoId;
    }

    public AgenciasJPA getAgenciasByAgenciaId() {
        return agenciasByAgenciaId;
    }

    public void setAgenciasByAgenciaId(AgenciasJPA agenciasByAgenciaId) {
        this.agenciasByAgenciaId = agenciasByAgenciaId;
    }
}
