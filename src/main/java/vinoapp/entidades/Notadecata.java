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
@Table(name = "notasdecata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notadecata.findAll", query = "SELECT n FROM Notadecata n"),
    @NamedQuery(name = "Notadecata.findByNotaDeCataId", query = "SELECT n FROM Notadecata n WHERE n.notaDeCataId = :notaDeCataId"),
    @NamedQuery(name = "Notadecata.findByNotaDeCata", query = "SELECT n FROM Notadecata n WHERE n.notaDeCata = :notaDeCata")})
public class Notadecata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nota_de_cata_id")
    private Integer notaDeCataId;
    @Column(name = "nota_de_cata")
    private String notaDeCata;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioId;
    @JoinColumn(name = "vino_id", referencedColumnName = "vino_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vino vinoId;

    public Notadecata() {
    }

    public Notadecata(Integer notaDeCataId) {
        this.notaDeCataId = notaDeCataId;
    }

    public Integer getNotaDeCataId() {
        return notaDeCataId;
    }

    public void setNotaDeCataId(Integer notaDeCataId) {
        this.notaDeCataId = notaDeCataId;
    }

    public String getNotaDeCata() {
        return notaDeCata;
    }

    public void setNotaDeCata(String notaDeCata) {
        this.notaDeCata = notaDeCata;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Vino getVinoId() {
        return vinoId;
    }

    public void setVinoId(Vino vinoId) {
        this.vinoId = vinoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notaDeCataId != null ? notaDeCataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notadecata)) {
            return false;
        }
        Notadecata other = (Notadecata) object;
        if ((this.notaDeCataId == null && other.notaDeCataId != null) || (this.notaDeCataId != null && !this.notaDeCataId.equals(other.notaDeCataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Notadecata[ notaDeCataId=" + notaDeCataId + " ]";
    }
    
}
