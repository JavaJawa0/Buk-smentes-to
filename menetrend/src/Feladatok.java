import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Feladatok {
    List<Megoldás> menetrendek = new ArrayList<>();

    List<Integer> vonatok = new ArrayList<>();
    List<Integer> allomasok = new ArrayList<>();

    public int bekerVonat, bekerOra, bekerPerc;

    public void Beolv(String filename) {
        try {
            try (BufferedReader rdr = new BufferedReader(new FileReader(filename))) {
                String[] line = rdr.readLine().split("\t");

                String sor;
                rdr.readLine();

                while ((sor = rdr.readLine()) != null) {
                    Megoldás menetrend = sorbolMenetrend(sor);
                    menetrendek.add(menetrend);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Megoldás sorbolMenetrend(String sor) {
        String[] line = sor.split("\t");
        Megoldás menetrend = new Megoldás();

        menetrend.setVonatSzam(Integer.parseInt(line[0]));
        menetrend.setAllomas(Integer.parseInt(line[1]));
        menetrend.setOra(Integer.parseInt(line[2]));
        menetrend.setPerc(Integer.parseInt(line[3]));
        menetrend.setStatus(line[4]);

        return menetrend;
    }

    public int idoSzamol(int Kezdora, int Kezdperc, int Vegora, int Vegperc) {
        return ((Vegora * 60) + Vegperc) - ((Kezdora * 60) + Kezdperc);
    }

    public void Feladat2() {
        for (Megoldás menetrend : menetrendek) {
            if (!vonatok.contains(menetrend.getVonatSzam()))
                vonatok.add(menetrend.getVonatSzam());
            if (!allomasok.contains(menetrend.getAllomas()))
                allomasok.add(menetrend.getAllomas());
        }

        System.out.println("2. feladat\nAz állomások száma: " + allomasok.size());
        System.out.println("A vonatok száma: " + vonatok.size());
    }

    public void Feladat3() {
        int maxIdo = 0, maxVonat = 0, maxAllomas = 0;
        for (int i = 1; i < menetrendek.size() - 1; i++) {
            for (int j = i + 1; j < menetrendek.size(); j++) {
                if ((menetrendek.get(i).getVonatSzam() == menetrendek.get(j).getVonatSzam()) &&
                        (menetrendek.get(i).getAllomas() == (menetrendek.get(j).getAllomas())) &&
                        menetrendek.get(i).getStatus().equals("E") && menetrendek.get(j).getStatus().equals("I")) {
                    if (maxIdo < idoSzamol(menetrendek.get(i).getOra(), menetrendek.get(i).getPerc(),
                            menetrendek.get(j).getOra(), menetrendek.get(j).getPerc())) {
                        maxIdo = idoSzamol(menetrendek.get(i).getOra(), menetrendek.get(i).getPerc(),
                                menetrendek.get(j).getOra(), menetrendek.get(j).getPerc());
                        maxVonat = menetrendek.get(i).getVonatSzam();
                        maxAllomas = menetrendek.get(i).getAllomas();
                    }
                }
            }
        }
        System.out.println("3. feladat\nA(z) " + maxVonat + ". vonat a(z) " + maxAllomas + ". állomáson " + maxIdo + " percet állt!");
    }

    public void Feladat4() {
        Scanner scan = new Scanner(System.in);
        String bekerIdo = "";
        System.out.println("4. feladat");
        System.out.println("Adja meg egy vonat azonosítóját! ");
        bekerVonat = Integer.parseInt(scan.nextLine());
        System.out.println("Adjon meg egy időpontot (óra perc)! ");
        bekerIdo = scan.nextLine();
        bekerOra = Integer.parseInt(bekerIdo.split(" ")[0]);
        bekerPerc = Integer.parseInt(bekerIdo.split(" ")[1]);
    }

    public void Feladat5() {
        int kezdIdo = 0, vegIdo = 0, menetIdo = 0, vonalIdo = 142;
        for (int i = 0; i < menetrendek.size(); i++) {
            if (menetrendek.get(i).getVonatSzam() == bekerVonat && menetrendek.get(i).getStatus().equals("I")) {
                kezdIdo = (menetrendek.get(i).getOra() * 60) + menetrendek.get(i).getPerc();
                break;
            }
        }
        for (int i = menetrendek.size() - 1; i >= 0; i--) {
            if (menetrendek.get(i).getVonatSzam() == bekerVonat && menetrendek.get(i).getStatus().equals("E")) {
                vegIdo = (menetrendek.get(i).getOra() * 60) + menetrendek.get(i).getPerc();
                break;
            }
        }

        menetIdo = vegIdo - kezdIdo;
        if (menetIdo > vonalIdo) {
            System.out.println("A(z) " + bekerVonat + ". vonat útja " + (menetIdo - vonalIdo) + " perccel hosszabb volt az előírtnál.");
        } else if (menetIdo == vonalIdo) {
            System.out.println("A(z) " + bekerVonat + ". vonat útja pontosan az előírt ideig tartott.");
        } else {
            System.out.println("A(z) " + bekerVonat + ". vonat útja " + (vonalIdo - menetIdo) + " perccel rövidebb volt az előírtnál.");
        }
    }

    public void Feladat6() {
        try {
            FileWriter write = new FileWriter("halad" + bekerVonat + ".txt");
            for (int i = 0; i < menetrendek.size(); i++) {
                if (menetrendek.get(i).getVonatSzam() == bekerVonat && menetrendek.get(i).getStatus().equals("E")) {
                    write.write(menetrendek.get(i).getAllomas() + ". állomás: " + menetrendek.get(i).getOra()
                            + ":" + menetrendek.get(i).getPerc() + "\n");
                }
            }
            write.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void Feladat7() {
        List<Integer> vonatok = new ArrayList<>();
        for (int i = 0; i < menetrendek.size(); i++) {
            int db = 0;
            if (menetrendek.get(i).getOra() <= bekerOra && menetrendek.get(i).getPerc() <= bekerPerc &&
                    menetrendek.get(i).getStatus().equals("I")) {
                vonatok.add(menetrendek.get(i).getVonatSzam());
            }
        }

        HashMap<Integer, Integer> vonatstat = new HashMap<>();
        for (Integer vonat : vonatok) {
            if (vonatstat.containsKey(vonat)) {
                vonatstat.put(vonat, vonatstat.get(vonat) + 1);
            } else {
                vonatstat.put(vonat, 1);
            }
        }

        System.out.println("7. feladat");
        for (Integer vonat : vonatstat.keySet()) {
            if (vonatstat.get(vonat) % 2 == 0) {
                System.out.println("A(z) " + vonat + ". vonat megy.");
            } else {
                System.out.println("A(z) " + vonat + ". vonat áll.");
            }
        }
    }

}
