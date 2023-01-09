public class Megoldás {
    private int vonatSzam;
    private int allomas;
    private int ora;
    private int perc;
    private String status;

    public Megoldás() {
    }

    public Megoldás(int vonatSzam, int allomas, int ora, int perc, String status) {
        this.vonatSzam = vonatSzam;
        this.allomas = allomas;
        this.ora = ora;
        this.perc = perc;
        this.status = status;
    }

    public int getVonatSzam() {
        return vonatSzam;
    }

    public void setVonatSzam(int vonatSzam) {
        this.vonatSzam = vonatSzam;
    }

    public int getAllomas() {
        return allomas;
    }

    public void setAllomas(int allomas) {
        this.allomas = allomas;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getPerc() {
        return perc;
    }

    public void setPerc(int perc) {
        this.perc = perc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
