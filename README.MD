# Untuk menginstall dan menjalankan project ini, ikuti langkah-langkah berikut:


- Pastikan bahwa Anda telah menginstall Java SDK pada komputer Anda dan telah menambahkan path JDK ke environment variables.

- Clone atau download project ini dari repository.

- Buka terminal atau command prompt dan navigasi ke folder project.

- Compile semua file Java di dalam project dengan perintah berikut:

``
	javac -d bin -sourcepath src src/com/main/*.java
``

- Perintah ini akan meng-compile semua file Java yang ada di dalam folder src dan menyimpan hasil kompilasi di folder bin.


- Setelah semua file telah di-compile, jalankan program dengan perintah berikut:


``
	java -cp bin com.main.Main
``


- Perintah ini akan menjalankan program dari file Main.class yang telah di-compile sebelumnya. Pastikan bahwa folder saat ini di terminal/command prompt adalah folder utama project.

- Setelah program dijalankan, Anda dapat memasukkan perintah sesuai dengan pilihan menu yang tersedia pada aplikasi dan melihat output yang dihasilkan.

## Catatan: Jika Anda menggunakan IDE seperti Eclipse atau IntelliJ IDEA, Anda dapat membuka folder project ini sebagai project Java dan menjalankan program dari IDE.
`
