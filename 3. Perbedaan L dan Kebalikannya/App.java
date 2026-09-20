import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int n = Integer.parseInt(input.nextLine().trim());
            int[][] matrix = bacaMatrix(input, n);

            // Matriks 1x1 dan 2x2: pola L tidak terbentuk
            if (n == 1 || n == 2) {
                int nilaiTengah = hitungTengah(matrix, n);
                cetakHasilKhusus(nilaiTengah);
                return;
            }

            int nilaiL = hitungNilaiL(matrix, n);
            int nilaiKebalikanL = hitungKebalikanL(matrix, n);
            int nilaiTengah = hitungTengah(matrix, n);
            int perbedaan = hitungPerbedaan(nilaiL, nilaiKebalikanL);
            int dominan = tentukanDominan(nilaiL, nilaiKebalikanL, nilaiTengah, perbedaan);

            cetakHasil(nilaiL, nilaiKebalikanL, nilaiTengah, perbedaan, dominan);
        }
    }

    // Tanggung jawab: membaca isi matriks N x N dari input
    private static int[][] bacaMatrix(Scanner input, int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] token = input.nextLine().split("\\s+");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(token[j]);
            }
        }
        return matrix;
    }

    // Tanggung jawab: menghitung Nilai L (kolom pertama + baris terakhir, tanpa dobel sudut)
    private static int hitungNilaiL(int[][] matrix, int n) {
        int nilaiL = 0;
        for (int i = 0; i < n - 1; i++) {
            nilaiL += matrix[i][0];
        }
        for (int j = 0; j < n - 1; j++) {
            nilaiL += matrix[n - 1][j];
        }
        return nilaiL;
    }

    // Tanggung jawab: menghitung Nilai Kebalikan L (kolom terakhir + baris pertama, tanpa dobel sudut)
    private static int hitungKebalikanL(int[][] matrix, int n) {
        int nilaiKebalikanL = 0;
        for (int i = 1; i < n; i++) {
            nilaiKebalikanL += matrix[i][n - 1];
        }
        for (int j = 1; j < n; j++) {
            nilaiKebalikanL += matrix[0][j];
        }
        return nilaiKebalikanL;
    }

    // Tanggung jawab: menghitung Nilai Tengah.
    // n == 1 -> elemen tunggal, n == 2 -> jumlah semua elemen,
    // n ganjil -> satu elemen tepat di tengah, n genap -> jumlah 4 elemen tengah.
    private static int hitungTengah(int[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] + matrix[0][1] + matrix[1][0] + matrix[1][1];
        }
        if (n % 2 != 0) {
            return matrix[n / 2][n / 2];
        }
        int mid = n / 2;
        return matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                + matrix[mid][mid - 1] + matrix[mid][mid];
    }

    // Tanggung jawab: menghitung selisih absolut antara Nilai L dan Nilai Kebalikan L
    private static int hitungPerbedaan(int nilaiL, int nilaiKebalikanL) {
        int perbedaan = nilaiL - nilaiKebalikanL;
        return Math.abs(perbedaan);
    }

    // Tanggung jawab: menentukan nilai mana yang dominan
    private static int tentukanDominan(int nilaiL, int nilaiKebalikanL, int nilaiTengah, int perbedaan) {
        if (perbedaan == 0) {
            return nilaiTengah;
        }
        return (nilaiL > nilaiKebalikanL) ? nilaiL : nilaiKebalikanL;
    }

    // Tanggung jawab: mencetak hasil untuk kasus khusus n == 1 atau n == 2
    private static void cetakHasilKhusus(int nilaiTengah) {
        System.out.println("Nilai L: Tidak Ada");
        System.out.println("Nilai Kebalikan L: Tidak Ada");
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: Tidak Ada");
        System.out.println("Dominan: " + nilaiTengah);
    }

    // Tanggung jawab: mencetak hasil untuk kasus umum (n >= 3)
    private static void cetakHasil(int nilaiL, int nilaiKebalikanL, int nilaiTengah, int perbedaan, int dominan) {
        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}