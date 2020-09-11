package model;

import java.io.Serializable;

public class Cursa implements Entity<Integer>, Serializable {
    private String capCilindrica;
    private Integer idCursa;
    private Integer numarParticipanti;


    public Cursa(String capCilindrica,int nrPart){
        this.capCilindrica=capCilindrica;
        this.numarParticipanti=nrPart;
    }

    public Cursa(){

    }

    public Cursa(String capCilindrica, int idCursa, int nrPart) {
        this.capCilindrica = capCilindrica;
        this.idCursa = idCursa;
        this.numarParticipanti=nrPart;
    }

    public String getCapCilindrica() {
        return capCilindrica;
    }

    public void setCapCilindrica(String capCilindrica) {
        this.capCilindrica = capCilindrica;
    }

    public Integer getNumarParticipanti() {
        return numarParticipanti;
    }

    public void setNumarParticipanti(Integer numarParticipanti) {
        this.numarParticipanti = numarParticipanti;
    }

    public void incrementParticipanti(){
        numarParticipanti++;
    }

    @Override
    public Integer getId() {
        return idCursa;
    }

    @Override
    public void setId(Integer integer) {
        this.idCursa = integer;
    }

    @Override
    public String toString() {
        return capCilindrica +" "+numarParticipanti;
    }
}
