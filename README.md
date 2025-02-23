# Tucil Stima #1

## Penjelasan Singkat
Program berikut merupakan program untuk mencari solusi dari ***IQ Puzzle Pro***. Program ini menggunakan algoritma *brute force* untuk mencari salah satu solusi.

## Prasyarat Program
Perangkat komputer yang digunakan harus memiliki **java** dan **jar** yang sudah terpasang.
Untuk memastikan apakah sudah terpasang atau belum, jalankan *prompt* berikut
```sh
java --version
jar --version
```

## Cara Mengkompilasi
Untuk mengkompilasi program tersebut, jalankan *prompt* berikut

```sh
javac -d bin src/Main.java src/utils/*.java src/objects/*.java
cd bin
jar cfm Run.jar MANIFEST.MF *.class objects/*.class utils/*.class
```

## Cara Menjalankan
Untuk menjalankan program, jalankan *prompt* berikut

```sh
cd bin
java -jar Run.jar
```

## Pembuat Program
Program ini dibuat oleh **Muhammad Adli Arindra** dengan NIM **18222089** dari prodi Sistem dan Teknologi Informasi dari K2 