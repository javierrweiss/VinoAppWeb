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
@Table(name = "rankings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ranking.findAll", query = "SELECT r FROM Ranking r"),
    @NamedQuery(name = "Ranking.findByRankingId", query = "SELECT r FROM Ranking r WHERE r.rankingId = :rankingId"),
    @NamedQuery(name = "Ranking.findByRanking", query = "SELECT r FROM Ranking r WHERE r.ranking = :ranking"),
    @NamedQuery(name = "Ranking.findByTiendaId", query = "SELECT r FROM Ranking r WHERE r.tiendaId = :tiendaId")})
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ranking_id")
    private Integer rankingId;
    @Column(name = "ranking")
    private String ranking;
    @Column(name = "tienda_id")
    private Integer tiendaId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioId;
    @JoinColumn(name = "vino_id", referencedColumnName = "vino_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vino vinoId;

    public Ranking() {
    }

    public Ranking(Integer rankingId) {
        this.rankingId = rankingId;
    }

    public Integer getRankingId() {
        return rankingId;
    }

    public void setRankingId(Integer rankingId) {
        this.rankingId = rankingId;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public Integer getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(Integer tiendaId) {
        this.tiendaId = tiendaId;
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
        hash += (rankingId != null ? rankingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ranking)) {
            return false;
        }
        Ranking other = (Ranking) object;
        if ((this.rankingId == null && other.rankingId != null) || (this.rankingId != null && !this.rankingId.equals(other.rankingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinoapp.entidades.Ranking[ rankingId=" + rankingId + " ]";
    }
    
}
