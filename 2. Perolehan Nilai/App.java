import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] simbolOrder = {"PA", "T", "K", "P", "UTS", "UAS"};
            Map<String, String> namaKomponen = new LinkedHashMap<>();
            namaKomponen.put("PA", "Partisipatif");
            namaKomponen.put("T", "Tugas");
            namaKomponen.put("K", "Kuis");
            namaKomponen.put("P", "Proyek");
            namaKomponen.put("UTS", "UTS");
            namaKomponen.put("UAS", "UAS");

            Map<String, Integer> bobotAkhir = new LinkedHashMap<>();
            int totalBobotInput = 0;
            for (String s : simbolOrder) {
                int b = Integer.parseInt(sc.nextLine().trim());
                bobotAkhir.put(s, b);
                totalBobotInput += b;
            }

            if (totalBobotInput != 100) {
                System.out.println("Total bobot harus 100");
                return;
            }

            Map<String, Integer> sumBobotRecord = new LinkedHashMap<>();
            Map<String, Integer> sumPerolehanRecord = new LinkedHashMap<>();
            for (String s : simbolOrder) {
                sumBobotRecord.put(s, 0);
                sumPerolehanRecord.put(s, 0);
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().equals("---")) break;

                String[] parts = line.split("\\|", -1);
                if (parts.length != 3) {
                    System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                    continue;
                }

                String simbol = parts[0].trim();
                String bobotStr = parts[1].trim();
                String perolehanStr = parts[2].trim();

                int bobot, perolehan;
                try {
                    bobot = Integer.parseInt(bobotStr);
                    perolehan = Integer.parseInt(perolehanStr);
                } catch (NumberFormatException e) {
                    System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                    continue;
                }

                if (!namaKomponen.containsKey(simbol)) {
                    System.out.println("Simbol tidak dikenal");
                    continue;
                }

                if (perolehan > bobot) perolehan = bobot;
                if (perolehan < 0) perolehan = 0;

                sumBobotRecord.put(simbol, sumBobotRecord.get(simbol) + bobot);
                sumPerolehanRecord.put(simbol, sumPerolehanRecord.get(simbol) + perolehan);
            }

            double nilaiAkhir = 0;
            System.out.println("Perolehan Nilai:");
            for (String s : simbolOrder) {
                int bAkhir = bobotAkhir.get(s);
                int sb = sumBobotRecord.get(s);
                int sp = sumPerolehanRecord.get(s);

                int persentase = sb == 0 ? 0 : (sp * 100) / sb;
                double kontribusi = (persentase / 100.0) * bAkhir;
                kontribusi = Math.round(kontribusi * 100) / 100.0;
                nilaiAkhir += kontribusi;

                System.out.printf(Locale.US, ">> %s: %d/100 (%.2f/%d)%n", namaKomponen.get(s), persentase, kontribusi, bAkhir);
            }

            // Bulatkan ke 2 desimal untuk menghindari galat floating-point
            // (mis. 56.999999999999 seharusnya 57.00) sebelum dipakai untuk grading.
            nilaiAkhir = Math.round(nilaiAkhir * 100) / 100.0;

            System.out.println();
            System.out.printf(Locale.US, ">> Nilai Akhir: %.2f%n", nilaiAkhir);
            System.out.println(">> Grade: " + grade(nilaiAkhir));
        }
    }

    private static String grade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";
    }
}