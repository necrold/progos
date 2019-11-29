-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2019. Nov 29. 13:40
-- Kiszolgáló verziója: 10.4.8-MariaDB
-- PHP verzió: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `prog3`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `diák`
--

CREATE TABLE `diák` (
  `diak_ID` int(11) NOT NULL,
  `diak_VEZN` varchar(40) COLLATE utf8_hungarian_ci NOT NULL,
  `diak_KERN` varchar(40) COLLATE utf8_hungarian_ci NOT NULL,
  `diak_AYNN` varchar(80) COLLATE utf8_hungarian_ci NOT NULL,
  `diak_SZHELY` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `diak_SZIDO` date NOT NULL,
  `diak_INT` int(11) NOT NULL,
  `diak_OM` varchar(11) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `diák`
--

INSERT INTO `diák` (`diak_ID`, `diak_VEZN`, `diak_KERN`, `diak_AYNN`, `diak_SZHELY`, `diak_SZIDO`, `diak_INT`, `diak_OM`) VALUES
(1, 'Kalapos', 'Károly', 'Sapkás Sára', 'Dunaújváros', '2019-10-11', 3, '777777777'),
(2, 'Kezes', 'Károly', 'Lábas Ludmilla', 'Mucsa', '2009-10-12', 2, '788888888'),
(3, 'Mucsai', 'Marika', 'Cipő Cecil', 'Gottham', '1990-10-10', 1, '111111111'),
(4, 'Teszt', 'Elek', 'Vég Eredmény', 'Mucsa', '2019-10-02', 2, '999999999'),
(5, 'Teemo', 'Tihamér', 'Rákos Réka', 'Gottham', '1990-10-11', 3, '123456'),
(7, 'Kiss', 'Ábel', 'Bangó Margit', 'Gottham', '2000-01-01', 1, '99999999');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `dolgozat`
--

CREATE TABLE `dolgozat` (
  `DO_ID` int(11) NOT NULL,
  `DO_KERDES` varchar(300) NOT NULL,
  `DO_FELADAT` varchar(300) NOT NULL,
  `DO_OSZTALY` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `dolgozat`
--

INSERT INTO `dolgozat` (`DO_ID`, `DO_KERDES`, `DO_FELADAT`, `DO_OSZTALY`) VALUES
(5, 'Mi a hiba a kódban?', 'int a=\"sajt\";', '10'),
(6, 'Mi a hiba a kódban?', 'for(int i=0;i<10<i+++)', '10'),
(7, 'Mi a kód végeredménye?', 'Int a=5;\nint b=10;\nif(a>b)\nsysout(\"alma\");\nelse\nsysout(\"körte\");', '10'),
(8, 'Milyen parancsal lehet megcsinálni a kövezkezőt?', 'Fájlba írás, úgy, hogy ne írja mindig felül a fájlt a\nprogram.', '12'),
(9, 'Mi a hiba a kódban?', 'int a=\"k\";', '10'),
(10, 'Mi a hiba a kódban?', 'int a=\"a\";', '10'),
(11, 'Mi a hiba a kódban?', 'int a=\"b\";', '10'),
(12, 'Mi a hiba a kódban?', 'int a=\"c\";', '10'),
(13, 'Mi a hiba a kódban?', 'int a=true;', '10'),
(14, 'Mi a hiba a kódban?', 'int a=false;', '10');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `feladatok`
--

CREATE TABLE `feladatok` (
  `feladat_ID` int(11) NOT NULL,
  `feladat_Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `feladatok`
--

INSERT INTO `feladatok` (`feladat_ID`, `feladat_Name`) VALUES
(1, 'Oktatás'),
(2, 'Butítás'),
(3, 'Közoktatás');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `fenntartok`
--

CREATE TABLE `fenntartok` (
  `fenntart_ID` int(11) NOT NULL,
  `fenntart_Name` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `fenntart_Type` varchar(30) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `fenntartok`
--

INSERT INTO `fenntartok` (`fenntart_ID`, `fenntart_Name`, `fenntart_Type`) VALUES
(1, 'Klik', 'Állami'),
(2, 'USZC', 'Egyházi'),
(3, 'OOK', 'Magán');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `iskolák`
--

CREATE TABLE `iskolák` (
  `iskola_ID` int(11) NOT NULL,
  `iskola_OM` varchar(11) COLLATE utf8_hungarian_ci NOT NULL,
  `iskola_Name` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `iskola_SETL` int(11) NOT NULL,
  `iskola_County` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `iskola_Fennt` int(11) NOT NULL,
  `iskola_Task` int(11) NOT NULL,
  `iskola_Enable` tinyint(1) NOT NULL,
  `iskola_AddR` varchar(30) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `iskolák`
--

INSERT INTO `iskolák` (`iskola_ID`, `iskola_OM`, `iskola_Name`, `iskola_SETL`, `iskola_County`, `iskola_Fennt`, `iskola_Task`, `iskola_Enable`, `iskola_AddR`) VALUES
(1, '11223344', 'Pesti Általános Iksola', 1, 'Mucsa Röcsöge', 1, 1, 1, 'Mucsaga Röcsöge Hamster út 6'),
(2, '1223334', 'Újpesti Szakközépiskola', 3, 'Veszprém', 3, 2, 0, 'Gottham, Batcave str. 1'),
(3, '4332222', 'Dunaújvárosi Egyetem', 2, 'Fejér', 2, 3, 1, 'Dunaújváros, Egyetem tér 1');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `város`
--

CREATE TABLE `város` (
  `varos_ID` int(11) NOT NULL,
  `varos_NAME` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `varos_ZIPPC` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `város`
--

INSERT INTO `város` (`varos_ID`, `varos_NAME`, `varos_ZIPPC`) VALUES
(1, 'Mucsa', 666),
(2, 'Gottham', 8731),
(3, 'Dunaújváros', 2704);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `diák`
--
ALTER TABLE `diák`
  ADD PRIMARY KEY (`diak_ID`),
  ADD KEY `diak_INT` (`diak_INT`),
  ADD KEY `diak_SZHELY` (`diak_SZHELY`);

--
-- A tábla indexei `dolgozat`
--
ALTER TABLE `dolgozat`
  ADD PRIMARY KEY (`DO_ID`);

--
-- A tábla indexei `feladatok`
--
ALTER TABLE `feladatok`
  ADD PRIMARY KEY (`feladat_ID`);

--
-- A tábla indexei `fenntartok`
--
ALTER TABLE `fenntartok`
  ADD PRIMARY KEY (`fenntart_ID`),
  ADD UNIQUE KEY `Kurva anyád` (`fenntart_ID`);

--
-- A tábla indexei `iskolák`
--
ALTER TABLE `iskolák`
  ADD PRIMARY KEY (`iskola_ID`),
  ADD UNIQUE KEY `Alma` (`iskola_ID`),
  ADD KEY `iskola_Fennt` (`iskola_Fennt`),
  ADD KEY `iskola_SETL` (`iskola_SETL`);

--
-- A tábla indexei `város`
--
ALTER TABLE `város`
  ADD PRIMARY KEY (`varos_ID`),
  ADD UNIQUE KEY `varos_NAME` (`varos_NAME`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `diák`
--
ALTER TABLE `diák`
  MODIFY `diak_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT a táblához `dolgozat`
--
ALTER TABLE `dolgozat`
  MODIFY `DO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT a táblához `feladatok`
--
ALTER TABLE `feladatok`
  MODIFY `feladat_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `fenntartok`
--
ALTER TABLE `fenntartok`
  MODIFY `fenntart_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT a táblához `iskolák`
--
ALTER TABLE `iskolák`
  MODIFY `iskola_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT a táblához `város`
--
ALTER TABLE `város`
  MODIFY `varos_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `diák`
--
ALTER TABLE `diák`
  ADD CONSTRAINT `diák_ibfk_1` FOREIGN KEY (`diak_INT`) REFERENCES `iskolák` (`iskola_ID`),
  ADD CONSTRAINT `diák_ibfk_2` FOREIGN KEY (`diak_SZHELY`) REFERENCES `város` (`varos_NAME`);

--
-- Megkötések a táblához `iskolák`
--
ALTER TABLE `iskolák`
  ADD CONSTRAINT `iskolák_ibfk_1` FOREIGN KEY (`iskola_Fennt`) REFERENCES `fenntartok` (`fenntart_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
