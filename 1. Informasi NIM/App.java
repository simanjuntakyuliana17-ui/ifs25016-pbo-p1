import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Baca NIM
        String nim = input.nextLine();
        input.close();

        //2. Cek panjang NIM
        if(nim.length() != 8){
            System.out.println("NIM harus 8 karakter");
            return;
        }

        tampilkanInformasiNim(nim);
    }

    public static void tampilkanInformasiNim(String nim) {
        // 3. Ambil nama prodi
        String prefixProdi = nim.substring(0, 3);
        String namaProdi = ambilNamaProgramStudi(prefixProdi);

        // 3b. Jika kode prodi tidak dikenali, hentikan proses
        if (namaProdi.isEmpty()) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        // 4. Ambil angkatan
        String strAngkatan = "20" + nim.substring(3, 5);
        int angkatan = Integer.parseInt(strAngkatan);

        // 5. Ambil nomor urut 
        String strUrutan = nim.substring(5);
        int urutan = Integer.parseInt(strUrutan);

        System.out.printf("Informasi NIM %s: \n", nim);
        System.out.printf(">> Program Studi: %s\n", namaProdi);
        System.out.printf(">> Angkatan: %s\n", angkatan);
        System.out.printf(">> Urutan: %s\n", urutan);
    }

    // Method static getProdi
    public static String ambilNamaProgramStudi(String prefix) {
        String prodi = "";
        switch (prefix) {
            case "11S":
                prodi = "Sarjana Informatika";
                break;
            case "12S":
                prodi = "Sarjana Sistem Informasi";
                break;
            case "13S":
                prodi = "Sarjana Teknik Elektro";
                break;
            case "21S":
                prodi = "Sarjana Manajemen Rekayasa";
                break;
            case "22S":
                prodi = "Sarjana Teknik Metalurgi";
                break;
            case "31S":
                prodi = "Sarjana Teknik Bioproses";
                break;
            case "32S":
                prodi = "Sarjana Bioteknologi";
                break;
            case "114":
                prodi = "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
                break;
            case "113":
                prodi = "Diploma 3 Teknologi Informasi";
                break;
            case "133":
                prodi = "Diploma 3 Teknologi Komputer";
                break;
        }
        
        return prodi;
    }
}