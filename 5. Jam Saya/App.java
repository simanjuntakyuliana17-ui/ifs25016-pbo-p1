import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String jamAwalStr = sc.nextLine().trim();

            String[] hm = jamAwalStr.split(":", -1);
            boolean valid = hm.length == 2;
            int jam = 0, menit = 0;
            if (valid) {
                try {
                    jam = Integer.parseInt(hm[0].trim());
                    menit = Integer.parseInt(hm[1].trim());
                } catch (NumberFormatException e) {
                    valid = false;
                }
            }
            if (valid && (jam < 0 || jam > 23 || menit < 0 || menit > 59)) {
                valid = false;
            }

            if (!valid) {
                System.out.println("Jam tidak valid");
                return;
            }

            int totalMenit = jam * 60 + menit;
            int totalGeser = 0;
            int pergantianHari = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.equals("---")) break;
                if (line.isEmpty()) continue;

                if (line.length() < 2 || (line.charAt(0) != '+' && line.charAt(0) != '-')) {
                    System.out.println("Perintah tidak valid");
                    continue;
                }

                int n;
                try {
                    n = Integer.parseInt(line.substring(1));
                } catch (NumberFormatException e) {
                    System.out.println("Perintah tidak valid");
                    continue;
                }

                int geser = line.charAt(0) == '+' ? n : -n;
                totalMenit += geser;
                totalGeser += geser;

                while (totalMenit >= 1440) {
                    totalMenit -= 1440;
                    pergantianHari++;
                }
                while (totalMenit < 0) {
                    totalMenit += 1440;
                    pergantianHari++;
                }
            }

            int jamAkhir = totalMenit / 60;
            int menitAkhir = totalMenit % 60;

            String totalMenitStr;
            if (totalGeser > 0) {
                totalMenitStr = "+" + totalGeser;
            } else if (totalGeser == 0) {
                totalMenitStr = "0";
            } else {
                totalMenitStr = String.valueOf(totalGeser);
            }

            System.out.printf("Jam Awal: %02d:%02d%n", jam, menit);
            System.out.printf("Jam Akhir: %02d:%02d%n", jamAkhir, menitAkhir);
            System.out.println("Total Menit: " + totalMenitStr);
            System.out.println("Pergantian Hari: " + pergantianHari);
        }
    }
}