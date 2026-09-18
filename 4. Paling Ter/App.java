import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Integer> nilai = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.equals("---")) break;
                if (line.isEmpty()) continue;
                nilai.add(Integer.parseInt(line));
            }

            if (nilai.isEmpty()) {
                return;
            }

            Map<Integer, Integer> frekuensi = new LinkedHashMap<>();
            for (int v : nilai) {
                frekuensi.merge(v, 1, Integer::sum);
            }

            int tertinggi = nilai.get(0);
            int terendah = nilai.get(0);
            for (int v : nilai) {
                if (v > tertinggi) tertinggi = v;
                if (v < terendah) terendah = v;
            }

            int terbanyakNilai = 0;
            int terbanyakFrek = -1;
            int tersedikitNilai = 0;
            int tersedikitFrek = Integer.MAX_VALUE;
            int jumlahTertinggiNilai = 0;
            long jumlahTertinggiHasil = Long.MIN_VALUE;
            int jumlahTerendahNilai = 0;
            long jumlahTerendahHasil = Long.MAX_VALUE;

            for (Map.Entry<Integer, Integer> e : frekuensi.entrySet()) {
                int v = e.getKey();
                int f = e.getValue();
                long hasil = (long) v * f;

                if (f > terbanyakFrek || (f == terbanyakFrek && v > terbanyakNilai)) {
                    terbanyakFrek = f;
                    terbanyakNilai = v;
                }
                if (f < tersedikitFrek || (f == tersedikitFrek && v < tersedikitNilai)) {
                    tersedikitFrek = f;
                    tersedikitNilai = v;
                }
                if (hasil > jumlahTertinggiHasil || (hasil == jumlahTertinggiHasil && v > jumlahTertinggiNilai)) {
                    jumlahTertinggiHasil = hasil;
                    jumlahTertinggiNilai = v;
                }
                if (hasil < jumlahTerendahHasil || (hasil == jumlahTerendahHasil && v < jumlahTerendahNilai)) {
                    jumlahTerendahHasil = hasil;
                    jumlahTerendahNilai = v;
                }
            }

            System.out.println("Tertinggi: " + tertinggi);
            System.out.println("Terendah: " + terendah);
            System.out.println("Terbanyak: " + terbanyakNilai + " (" + terbanyakFrek + "x)");
            System.out.println("Tersedikit: " + tersedikitNilai + " (" + tersedikitFrek + "x)");
            System.out.println("Jumlah Tertinggi: " + jumlahTertinggiNilai + " * " + frekuensi.get(jumlahTertinggiNilai) + " = " + jumlahTertinggiHasil);
            System.out.println("Jumlah Terendah: " + jumlahTerendahNilai + " * " + frekuensi.get(jumlahTerendahNilai) + " = " + jumlahTerendahHasil);
        }
    }
}