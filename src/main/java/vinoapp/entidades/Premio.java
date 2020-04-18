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

/**
 *
 * @author josejavier84
 */
@Entity
@Table(name = "premios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Premio.findAll", query = "SELECT p FROM Premio p"),
    @NamedQuery(name = "Premio.findByPremioId", query = "SELECT p FROM Premio p WHERE p.premioId = :premioId"),
    @NamedQuery(name = "Premio.findByNombrePremio", query = "SELECT p FROM Premio p WHERE p.nombrePremio = :nombrePremio"),
    @NamedQuery(name = "Premio.findByPuntaje", query = "SELECT p FROM Premio p WHERE p.puntaje = :puntaje"),
    @NamedQuery(name = "Premio.findByMedalla", query = "SELECT p FROM Premio p WHERE p.medalla = :medalla"),
    @NamedQuery(name = "Premio.findByAno", query = "SELECT p FROM Premio p WHERE p.ano = :ano")})
public class Premio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "premio_id")
    private Integer premioId;
    @Basic(optional = false)
    @Column(name = "nombre_premio")
    private String nombrePremio;
    @Column(name = "puntaje")
    private Integer puntaje;
    @Column(name = "medalla")
    private String medalla;
    @Basic(optional = false)
    @Column(name = "ano")
    private int ano;
    @JoinColumn(name = "vino_id", referencedColumnName = "vino_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vino vinoId;

    public Premio() {
    }

    public Premio(Integer premioId) {
        this.premioId = premioId;
    }

    public Premio(Integer premioId, String nombrePremio, int ano) {
        this.premioId = premioId;
        this.nombrePremio = nombrePremio;
        this.ano = ano;
    }

    public Integer getPremioId() {
        return premioId;
    }

    public void setPremioId(Integer premioId) {
        this.premioId = premioId;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombrePremio(String nombrePremio) {
        this.nombrePremio = nombrePremio;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getMedalla() {
        return medalla;
    }

    public void setMedalla(String medalla) {
        this.medalla = medalla;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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
        hash += (premioId != null ? premioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Premio)) {
            return false;
        }
        Premio other = (Premio) object;
        if ((this.premioId == null && other.premioId != null) || (this.premioId != null && !this.premioId.equals(other.premioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Premio[ premioId=" + premioId + " ]";
    }
    
}
