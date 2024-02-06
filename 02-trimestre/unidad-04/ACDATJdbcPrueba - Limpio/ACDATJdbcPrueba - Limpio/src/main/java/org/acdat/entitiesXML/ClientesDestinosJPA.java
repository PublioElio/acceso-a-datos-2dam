package org.acdat.entitiesXML;

public class ClientesDestinosJPA {
    private int clienteId;
    private int destinoId;
    private ClientesJPA clientesByClienteId;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesDestinosJPA that = (ClientesDestinosJPA) o;

        if (clienteId != that.clienteId) return false;
        if (destinoId != that.destinoId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clienteId;
        result = 31 * result + destinoId;
        return result;
    }

    public ClientesJPA getClientesByClienteId() {
        return clientesByClienteId;
    }

    public void setClientesByClienteId(ClientesJPA clientesByClienteId) {
        this.clientesByClienteId = clientesByClienteId;
    }
}
