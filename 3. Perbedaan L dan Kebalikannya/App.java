import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Baca ukuran matriks (N x N)
        int n = Integer.parseInt(input.nextLine());

        // 2. Baca isi matriks baris per baris
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] token = input.nextLine().split("\\s+");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(token[j]);
            }
        }
        input.close();

        // 3. Kasus khusus: matriks 1x1 -> pola L tidak terbentuk
        if (n == 1) {
            int nilaiTengah = matrix[0][0];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilaiTengah);
            return;
        }

        // 4. Kasus khusus: matriks 2x2 -> pola L juga tidak terbentuk
        if (n == 2) {
            int nilaiTengah = matrix[0][0] + matrix[0][1] + matrix[1][0] + matrix[1][1];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilaiTengah);
            return;
        }

        // 5. Hitung Nilai L:
        int nilaiL = 0;
        for (int i = 0; i < n - 1; i++) {
            nilaiL = nilaiL + matrix[i][0];
        }
        for (int j = 0; j < n - 1; j++) {
            nilaiL = nilaiL + matrix[n - 1][j];
        }

        // 6. Hitung Nilai Kebalikan L:
        int nilaiKebalikanL = 0;
        for (int i = 1; i < n; i++) {
            nilaiKebalikanL = nilaiKebalikanL + matrix[i][n - 1];
        }
        for (int j = 1; j < n; j++) {
            nilaiKebalikanL = nilaiKebalikanL + matrix[0][j];
        }

        // 7. Hitung Nilai Tengah
        int nilaiTengah;
        if (n % 2 != 0) {
            // ukuran ganjil -> ambil satu elemen tepat di tengah
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            // ukuran genap -> jumlahkan 4 elemen di tengah
            int mid = n / 2;
            nilaiTengah = matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                    + matrix[mid][mid - 1] + matrix[mid][mid];
        }

        // 8. Hitung Perbedaan
        int perbedaan = nilaiL - nilaiKebalikanL;
        if (perbedaan < 0) {
            perbedaan = -perbedaan; // bikin jadi nilai mutlak (positif)
        }

        // 9. Tentukan Dominan
        int dominan;
        if (perbedaan == 0) {
            dominan = nilaiTengah;
        } else if (nilaiL > nilaiKebalikanL) {
            dominan = nilaiL;
        } else {
            dominan = nilaiKebalikanL;
        }

        // 10. Tampilkan hasil
        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}