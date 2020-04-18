
package vinoapp.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josejavier84
 */
@Entity
@Table(name = "bodegas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodega.findAll", query = "SELECT b FROM Bodega b"),
    @NamedQuery(name = "Bodega.findByBodegaId", query = "SELECT b FROM Bodega b WHERE b.bodegaId = :bodegaId"),
    @NamedQuery(name = "Bodega.findByNombreBodega", query = "SELECT b FROM Bodega b WHERE b.nombreBodega = :nombreBodega"),
    @NamedQuery(name = "Bodega.findByPais", query = "SELECT b FROM Bodega b WHERE b.pais = :pais"),
    @NamedQuery(name = "Bodega.findByUbicacion", query = "SELECT b FROM Bodega b WHERE b.ubicacion = :ubicacion"),
    @NamedQuery(name = "Bodega.findByFundacion", query = "SELECT b FROM Bodega b WHERE b.fundacion = :fundacion")})
public class Bodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bodega_id")
    private Integer bodegaId;
    @Basic(optional = false)
    @Column(name = "nombre_bodega")
    private String nombreBodega;
    @Column(name = "pais")
    private String pais;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "fundacion")
    private Integer fundacion;
    @OneToMany(mappedBy = "bodegaId", fetch = FetchType.LAZY)
    private Collection<Bodegapremio> bodegapremioCollection;
    @OneToMany(mappedBy = "bodegaId", fetch = FetchType.LAZY)
    private Collection<Vino> vinoCollection;

    public Bodega() {
    }

    public Bodega(Integer bodegaId) {
        this.bodegaId = bodegaId;
    }

    public Bodega(Integer bodegaId, String nombreBodega) {
        this.bodegaId = bodegaId;
        this.nombreBodega = nombreBodega;
    }

    public Integer getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Integer bodegaId) {
        this.bodegaId = bodegaId;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getFundacion() {
        return fundacion;
    }

    public void setFundacion(Integer fundacion) {
        this.fundacion = fundacion;
    }

    @XmlTransient
    public Collection<Bodegapremio> getBodegapremioCollection() {
        return bodegapremioCollection;
    }

    public void setBodegapremioCollection(Collection<Bodegapremio> bodegapremioCollection) {
        this.bodegapremioCollection = bodegapremioCollection;
    }

    @XmlTransient
    public Collection<Vino> getVinoCollection() {
        return vinoCollection;
    }

    public void setVinoCollection(Collection<Vino> vinoCollection) {
        this.vinoCollection = vinoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bodegaId != null ? bodegaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodega)) {
            return false;
        }
        Bodega other = (Bodega) object;
        if ((this.bodegaId == null && other.bodegaId != null) || (this.bodegaId != null && !this.bodegaId.equals(other.bodegaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Bodega[ bodegaId=" + bodegaId + " ]";
    }
    
}
