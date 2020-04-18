/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vinos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vino.findAll", query = "SELECT v FROM Vino v"),
    @NamedQuery(name = "Vino.findByVinoId", query = "SELECT v FROM Vino v WHERE v.vinoId = :vinoId"),
    @NamedQuery(name = "Vino.findByNombre", query = "SELECT v FROM Vino v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Vino.findByCepas", query = "SELECT v FROM Vino v WHERE v.cepas = :cepas"),
    @NamedQuery(name = "Vino.findByColor", query = "SELECT v FROM Vino v WHERE v.color = :color"),
    @NamedQuery(name = "Vino.findByCosecha", query = "SELECT v FROM Vino v WHERE v.cosecha = :cosecha"),
    @NamedQuery(name = "Vino.findByCategoria", query = "SELECT v FROM Vino v WHERE v.categoria = :categoria"),
    @NamedQuery(name = "Vino.findByEnologo", query = "SELECT v FROM Vino v WHERE v.enologo = :enologo"),
    @NamedQuery(name = "Vino.findByTerruno", query = "SELECT v FROM Vino v WHERE v.terruno = :terruno")})
public class Vino implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vino_id")
    private Integer vinoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cepas")
    private String cepas;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "cosecha")
    private int cosecha;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "enologo")
    private String enologo;
    @Column(name = "terruno")
    private String terruno;
    @OneToMany(mappedBy = "vinoId", fetch = FetchType.LAZY)
    private Collection<Notadecata> notadecataCollection;
    @OneToMany(mappedBy = "vinoId", fetch = FetchType.LAZY)
    private Collection<Ranking> rankingCollection;
    @OneToMany(mappedBy = "vinoId", fetch = FetchType.LAZY)
    private Collection<Premio> premioCollection;
    @JoinColumn(name = "bodega_id", referencedColumnName = "bodega_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bodega bodegaId;

    public Vino() {
    }

    public Vino(Integer vinoId) {
        this.vinoId = vinoId;
    }

    public Vino(Integer vinoId, String nombre, String color, int cosecha, String categoria) {
        this.vinoId = vinoId;
        this.nombre = nombre;
        this.color = color;
        this.cosecha = cosecha;
        this.categoria = categoria;
    }

    public Integer getVinoId() {
        return vinoId;
    }

    public void setVinoId(Integer vinoId) {
        this.vinoId = vinoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCepas() {
        return cepas;
    }

    public void setCepas(String cepas) {
        this.cepas = cepas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCosecha() {
        return cosecha;
    }

    public void setCosecha(int cosecha) {
        this.cosecha = cosecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEnologo() {
        return enologo;
    }

    public void setEnologo(String enologo) {
        this.enologo = enologo;
    }

    public String getTerruno() {
        return terruno;
    }

    public void setTerruno(String terruno) {
        this.terruno = terruno;
    }

    @XmlTransient
    public Collection<Notadecata> getNotadecataCollection() {
        return notadecataCollection;
    }

    public void setNotadecataCollection(Collection<Notadecata> notadecataCollection) {
        this.notadecataCollection = notadecataCollection;
    }

    @XmlTransient
    public Collection<Ranking> getRankingCollection() {
        return rankingCollection;
    }

    public void setRankingCollection(Collection<Ranking> rankingCollection) {
        this.rankingCollection = rankingCollection;
    }

    @XmlTransient
    public Collection<Premio> getPremioCollection() {
        return premioCollection;
    }

    public void setPremioCollection(Collection<Premio> premioCollection) {
        this.premioCollection = premioCollection;
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
        hash += (vinoId != null ? vinoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vino)) {
            return false;
        }
        Vino other = (Vino) object;
        if ((this.vinoId == null && other.vinoId != null) || (this.vinoId != null && !this.vinoId.equals(other.vinoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Vino[ vinoId=" + vinoId + " ]";
    }
    
}
