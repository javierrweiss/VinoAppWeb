package vinoapp.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "bodegapremios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodegapremio.findAll", query = "SELECT b FROM Bodegapremio b"),
    @NamedQuery(name = "Bodegapremio.findByPremiobodId", query = "SELECT b FROM Bodegapremio b WHERE b.premiobodId = :premiobodId"),
    @NamedQuery(name = "Bodegapremio.findByNombrePremiobod", query = "SELECT b FROM Bodegapremio b WHERE b.nombrePremiobod = :nombrePremiobod"),
    @NamedQuery(name = "Bodegapremio.findByDistincion", query = "SELECT b FROM Bodegapremio b WHERE b.distincion = :distincion"),
    @NamedQuery(name = "Bodegapremio.findByAno", query = "SELECT b FROM Bodegapremio b WHERE b.ano = :ano")})
public class Bodegapremio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "premiobod_id")
    private Integer premiobodId;
    @Column(name = "nombre_premiobod")
    private String nombrePremiobod;
    @Column(name = "distincion")
    private String distincion;
    @Column(name = "ano")
    private Integer ano;
    @JoinColumn(name = "bodega_id", referencedColumnName = "bodega_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bodega bodegaId;

    public Bodegapremio() {
    }

    public Bodegapremio(Integer premiobodId) {
        this.premiobodId = premiobodId;
    }

    public Integer getPremiobodId() {
        return premiobodId;
    }

    public void setPremiobodId(Integer premiobodId) {
        this.premiobodId = premiobodId;
    }

    public String getNombrePremiobod() {
        return nombrePremiobod;
    }

    public void setNombrePremiobod(String nombrePremiobod) {
        this.nombrePremiobod = nombrePremiobod;
    }

    public String getDistincion() {
        return distincion;
    }

    public void setDistincion(String distincion) {
        this.distincion = distincion;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Bodega getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Bodega bodegaId) {
        this.bodegaId = bodegaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (premiobodId != null ? premiobodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodegapremio)) {
            return false;
        }
        Bodegapremio other = (Bodegapremio) object;
        if ((this.premiobodId == null && other.premiobodId != null) || (this.premiobodId != null && !this.premiobodId.equals(other.premiobodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Bodegapremio[ premiobodId=" + premiobodId + " ]";
    }
    
}
