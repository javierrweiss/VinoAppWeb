/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinoapp.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josejavier84
 */
@Entity
@Table(name = "tiendas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT t FROM Tienda t"),
    @NamedQuery(name = "Tienda.findByTiendaId", query = "SELECT t FROM Tienda t WHERE t.tiendaId = :tiendaId"),
    @NamedQuery(name = "Tienda.findByNombreT", query = "SELECT t FROM Tienda t WHERE t.nombreT = :nombreT"),
    @NamedQuery(name = "Tienda.findByPais", query = "SELECT t FROM Tienda t WHERE t.pais = :pais"),
    @NamedQuery(name = "Tienda.findByCiudad", query = "SELECT t FROM Tienda t WHERE t.ciudad = :ciudad"),
    @NamedQuery(name = "Tienda.findByDireccion", query = "SELECT t FROM Tienda t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Tienda.findByPaginaWeb", query = "SELECT t FROM Tienda t WHERE t.paginaWeb = :paginaWeb")})
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tienda_id")
    private Integer tiendaId;
    @Basic(optional = false)
    @Column(name = "nombre_T")
    private String nombreT;
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "pagina_web")
    private String paginaWeb;

    public Tienda() {
    }

    public Tienda(Integer tiendaId) {
        this.tiendaId = tiendaId;
    }

    public Tienda(Integer tiendaId, String nombreT, String ciudad, String direccion) {
        this.tiendaId = tiendaId;
        this.nombreT = nombreT;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Integer getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(Integer tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getNombreT() {
        return nombreT;
    }

    public void setNombreT(String nombreT) {
        this.nombreT = nombreT;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiendaId != null ? tiendaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.tiendaId == null && other.tiendaId != null) || (this.tiendaId != null && !this.tiendaId.equals(other.tiendaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Tienda[ tiendaId=" + tiendaId + " ]";
    }
    
}
