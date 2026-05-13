# 1.rühmatöö kirjeldus

# EKSAMI SIMULAATOR

#### Autorid: Viktorija Korjagina, Maria Elisa Vassiljeva

# Projekti kirjeldus
### Programmi eesmärk
"Eksami Simulaator" on interaktiivne tekstipõhine mäng, milles mängija kehastab üliõpilast, kellel on eksam tulemas. Mängu eesmärk on eksam edukalt läbida - selleks tuleb 3 mängupäeva jooksul teha õigeid valikuid ning koguda piisavalt teadmispunkte. Mängija otsused mõjutavad kahte näitajat: teadmisi ja enesekindlust. Lõplik eksamitulemus sõltub kogutud teadmistest, enesekindluse boonusest ning juhuslikust õnnetegurist. Eksami läbimiseks on vaja saavutada vähemalt 50 punkti.

### Programmi üldine töö
Mäng kestab 3 mängupäeva. Iga päev teeb mängija 3 valikut viie tegevuse vahel: õppimine, loengu vaatamine, ülesannete lahendamine, Netflixi vaatamine või puhkamine. Igal tegevusel on erinev mõju teadmistele ja enesekindlusele.
Kui mängija valib õppimise 3 korda järjest, siis õppimise nupp muutub ajutiselt mitteaktiivseks ning mängija peab valima mõne teise tegevuse.
Pärast kolmanda päeva lõppu toimub automaatselt eksam. Lõplik tulemus arvutatakse teadmiste, enesekindluse boonuse ja juhusliku õnneteguri põhjal.
Programm kasutab JavaFX graafilist kasutajaliidest. Kasutaja saab valida tegevusi nii hiirega (nuppe vajutades) kui ka klaviatuuriga (klahvid 1-5).
Programm salvestab mängu logi tekstifaili ning käivitamisel näidatakse kasutajale eelmise mängu viimast logirida. Pärast mängu lõppu saab kasutaja soovi korral alustada uut mängu.

### Lühike kasutusjuhis
1. Käivitage programm IntelliJ IDEA-s, vajutades Run failis EksamiSimulaatorFX.java.
2. Sisestage algusaknas oma nimi ja vajutage nuppu "Alusta mängu".
3. Iga päev tuleb teha 3 valikut.
4. Tegevusi saab valida:
  - hiirega vajutades nuppe
  - klaviatuuriga kasutades klahve 1, 2, 3, 4 ja 5
5. Vasakul kuvatakse mängija teadmised, enesekindlus ja juhuslik nõuanne.
6. Paremal kuvatakse tegevuste nupud ja mängu logi.
7. Pärast kolmandat päeva toimub automaatselt eksam ning kuvatakse lõpptulemus.
8. Pärast mängu lõppu saab vajutada nuppu "Alusta uuesti".

# Klasside kirjeldus
### Peaklass.java
`Peaklass` on programmi käivituspunkt, mis sisaldab meetodit `main()`. See korraldab kogu mängu üldise kulgu: kuvab tervitussõnumi, küsib mängija nime, loob `Õpilane` ja `NõuandeGeneraator` objektid, käivitab 3 päeva tsükli ning kutsub lõpuks välja eksami. Peaklass ühendab kõik teised klassid ühtseks tervikuks.
Olulisemad osad: `main()` meetod kogu mängu juhtimiseks, `JOptionPane.showInputDialog()` nime küsimiseks, for-tsükkel 3 päeva läbimiseks, Eksam objekti loomine ning eksami käivitamine.

### Õpilane.java
Klass `Õpilane` esindab mängijat ning hoiab tema andmeid. Klassil on kolm isendivälja: nimi (mängija nimi), teadmised (tegelikud teadmised, mis mõjutavad eksami tulemust) ja enesekindlus (mängija hinnang oma valmisolekule). Mõlemad punktinäitajad algavad nullist ega lange alla nulli.
Olulisemad meetodid: `Õpilane(String nimi)` - konstruktor, mis loob uue mängija objekti; `getNimi()`, `getTeadmised()`, `getEnesekindlus()`, `getÕppimisJärjestus()` - getterid väljade lugemiseks; `lisaTeadmised(int kogus)` - suurendab teadmiste näitajat, tagades et see ei lange alla nulli; `lisaEnesekindlus(int kogus)` - muudab enesekindluse näitajat, tagades et see ei lange alla nulli; `getStatsText()` - tagastab mängija hetkeseisu tekstina kuvamiseks aknas.

### Tegevus.java
Klass `Tegevus` esindab ühte tegevust, mida mängija saab valida (näiteks õppimine või puhkamine). Igal tegevusel on nimi, kirjeldus, teadmiste muutuse vahemik (minimaalne ja maksimaalne väärtus) ning fikseeritud enesekindluse muutus. Teadmiste kasv on juhuslik - iga kord võib tulemus olla erinev.
Olulisemad meetodid: `Tegevus(String nimi, String kirjeldus, int minTeadmised, int maxTeadmised, int enesekindlusMuutus)` - konstruktor; `getNimi()`, `getKirjeldus()` - getterid; `getTeadmistekasv()` - arvutab juhusliku teadmiste kasvu lubatud vahemikus, kasutades klassi `Random`; `getEnesekindlusMuutus()` - tagastab fikseeritud enesekindluse muutuse.

### Päev.java
Klass `Päev` korraldab ühe mängupäeva kulgu. See loob kõik 5 võimalikku tegevust, kuvab mängijale valikuakna ning töötleb sisestuse. Klass jälgib ka õppimise järjestust - kui mängija valib "Õppimine" 3 korda järjest, blokeeritakse see valik. Valikul 4 ("Ainult üks episood") on eriefekt: klassi `Random` abil otsustatakse, mitu episoodi mängija tegelikult vaatab (1, 2 või 3), mis mõjutab enesekindluse näitajat täiendavalt.
Olulisemad meetodid: `Päev(int päevaNr)` - konstruktor, mis loob päeva objekti ja seadistab kõik `Tegevus` objektid massiivis; `käivitaPaev(Õpilane õpilane)` - käivitab päeva, kordab 3 korda valikuringi ning tagastab päeva koondtulemused; `teeÜksTegevus(Õpilane õpilane, int valikNr)` - privaatne meetod, mis kuvab valikuakna, loeb ja kontrollib mängija sisestust, rakendab valitud tegevuse tulemuse mängijale ning uuendab õppimise järjestuse loenduri.

### Eksam.java
Klass `Eksam` korraldab mängu lõpliku eksami. Eksami tulemus arvutatakse kolme teguri põhjal: kogutud teadmised, enesekindluse boonus (enesekindlus / 5) ning juhuslik õnnetegur (vahemikus -20 kuni +20). Eksami läbimiseks on vajalik saavutada vähemalt 50 punkti. Sõltuvalt tulemusest ja selle saavutamise viisist kuvatakse erinev lõpusõnum.
Olulisemad meetodid: `sooritaEksam(Õpilane õpilane)` - viib läbi kogu eksami: kuvab eelinfot, arvutab lõpliku tulemuse, näitab punktide jaotust ning lõpptulemust; `getLabimiseSõnum(int teadmised, int õnn)` - tagastab sobiva läbimissõnumi vastavalt sellele, kuidas mängija eksami läbis; `getLabikukkumiseSõnum(int teadmised, int õnn)` - tagastab sobiva läbikukkumissõnumi vastavalt ebaõnnestumise põhjusele.

### NõuandeGeneraator.java
Klass `NõuandeGeneraator` kuvab mängijale iga päeva alguses juhusliku nõuande. Klassil on 12 erineva sisuga nõuannet sisaldav massiiv. Iga päeva alguses valitakse klassi `Random` abil üks nõuanne juhuslikult ning kuvatakse mängijale hüpikaknas.
Olulisemad meetodid: `NõuandeGeneraator()` - konstruktor; `getNõuanne()` - tagastab juhusliku nõuande massiivist, kasutades meetodit `random.nextInt(nõuanded.length)`.

### EksamiSimulaatorFX.java
See on programmi uus peaklass teises etapis. Klass käivitab JavaFX rakenduse ning avab algusakna. Olulisem meetod on `start()`, mis loob `MänguKontrollija` objekti ja käivitab programmi.

### MänguKontrollija.java
See klass juhib kogu mängu loogikat ning ühendab kõik teised klassid omavahel. Klass kontrollib päevade arvu, töötleb mängija valikuid, uuendab statistikat ja käivitab eksami. Samuti võimaldab see pärast mängu lõppu uut mängu alustada.

### MänguVaade.java
See klass loob peamise mänguakna JavaFX abil. Siin kuvatakse nupud, progressiribad, mängu logi ning mängija statistika. Klass töötleb ka klaviatuuriga tehtud tegevusvalikuid.

### AlgusVaade.java
See klass loob programmi algusakna. Kasutaja sisestab siin oma nime ning saab näha eelmise mängu viimast logirida.

### FailiHaldur.java
See klass tegeleb failidega. Klass kirjutab mängu logi tekstifaili ning loeb sealt andmeid programmi käivitamisel.

### EksamiArvutaja.java
See klass arvutab lõpliku eksamitulemuse teadmiste, enesekindluse ja õnneteguri põhjal.

### EksamiTulemus.java
See klass hoiab eksami lõpptulemuse andmeid ning tagastab sobiva lõpusõnumi.

### Kujundus.java
See klass sisaldab JavaFX kujunduse meetodeid. Siin määratakse nuppude, tausta ja teiste visuaalsete elementide stiil.

### TegevusteLooja.java
See klass loob kõik mängus kasutatavad tegevused ning tagastab need massiivina.

# Projekti tegemise protsess
### 1. etapp - ideede genereerimine ja planeerimine
Esimeses kohtumises arutati võimalikke projektiteemasid. Otsustati luua humoorika suhtumisega üliõpilassimulatsioon, kuna see võimaldas kasutada kõiki nõutavaid programmeerimismõisteid ning tundus mõlemale rühmaliikmetele huvitav ja motiveeriv. Pandi paika programmi üldine kontseptsioon: 3 mängupäeva, 2 näitajat (teadmised ja enesekindlus) ning lõplik eksam juhusliku õnneteguriga. Koostati programmi struktuur ning otsustati klasside arv ja nende vastutusalad. Mõlemad rühmaliikmed osalesid selles etapis võrdselt.

### 2. etapp - töö jaotamine ja GitHub-i seadistamine
Lepiti kokku tööjaotuses. Viktorija võttis endale klassid Tegevus, NõuandeGeneraator ja Päev. Maria Elisa võttis endale klassid Õpilane, Eksam ja Peaklass. Seadistati ühine GitHub-i repositoorium, kus Viktorija töötas branchi`s "viktoria" ja Maria Elisa branchi`s "mariaelisa". Kokku lepiti, et rühmaliikmed abistavad teineteist vajadusel ning võivad teha täiendavaid muudatusi üksteise koodis, kui see on vajalik vigade parandamiseks või ühtlustamiseks.

### 3. etapp - programmeerimine
Mõlemad rühmaliikmed kirjutasid oma klassid iseseisvalt, konsulteerides teineteisega tekkinud probleemide korral. Kohtumistel arutati klasside omavahelist suhtlust - näiteks kuidas Päev kasutab Tegevus objekte ning kuidas Peaklass seob kõik klassid ühtseks tervikuks. Muudatuste sünkroonimiseks kasutati regulaarselt käske `git push` ja `git pull`.

### 4. etapp - testimine ja vigade parandamine
Pärast klasside valmimist testiti programmi ühiselt - mäng käivitati mitmeid kordi ning prooviti erinevaid stsenaariumeid. Avastati ja parandati mitmeid väikseid vigu. Viimistleti kommentaarid ning mängijale kuvatavad tekstid.

### 5. etapp - kirjelduse koostamine
Rühmatöö kirjeldus koostati ühiselt, arutades läbi iga punkti sisu. Mõlemad rühmaliikmed panustasid teksti kirjutamisse võrdselt.

# Rühmaliikmete panus ja ajakulu
### Viktorija Korjagina:
Tegeles peamiselt mängu loogikaga seotud klassidega: `Tegevus.java`, `TegevusteLooja.java`, `NõuandeGeneraator.java`, `Õpilane.java` ning osales `MänguKontrollija.java` loogika arendamises. Aitas parandada mängu tasakaalu, testida erinevaid tegevusi ning osales dokumentatsiooni koostamisel.
Ajakulu: orienteeruvalt 14 tundi.

### Maria Elisa Vassiljeva:
Tegeles peamiselt kasutajaliidese ja failidega seotud klassidega: `EksamiSimulaatorFX.java`, `AlgusVaade.java`, `MänguVaade.java`, `FailiHaldur.java`, `Kujundus.java`, `EksamiArvutaja.java`, `EksamiTulemus.java` ning osales programmi testimisel ja dokumentatsiooni koostamisel.
Ajakulu: orienteeruvalt 14 tundi.

# Tehisintellekti kasutamise kirjeldus
Kasutasime ChatGPT-d abivahendina peamiselt vigade otsimiseks, koodi kontrollimiseks ja mõnede ideede parandamiseks. Näiteks aitas see selgitada JavaFX kasutamist, failidega töötamist ning mõningaid loogikavigu. Kogu programmi põhiloogika kirjutasime ja mõistsime ise.

# Tegemise mured
1. Git ja GitHub. Alguses valmistas raskusi harude (branch) loogika mõistmine ning muudatuste omavaheline sünkroonimine. Eriti keeruline oli olukord, kus mõlemad rühmaliikmed muutsid sama faili samaaegselt, mis nõudis konfliktide lahendamist.
2. `JOptionPane` kasutamine oli esmakordne kogemus. Tuli õppida eristama meetodeid `showInputDialog()`, `showMessageDialog()` ja `showConfirmDialog()` ning lisaks kontrollida, kas kasutaja sulges akna ilma midagi sisestamata (null-kontroll).
3. Klasside omavahelise struktuuri planeerimine valmistas alguses raskusi. Eriti keeruline oli läbi mõelda, kuidas klassid omavahel suhtlevad ja milline peaks olema nende vastutus. Näiteks tuli otsustada, kuidas Päev kasutab samaaegselt nii Tegevus kui ka Õpilane objekte ning kuidas andmed nende vahel liiguvad. See nõudis kogu programmi ülesehituse läbimõtlemist enne realiseerimist.
4. `try-catch` ploki kasutamine `NumberFormatException`'i püüdmiseks oli uus kontseptsioon, mida tuli eraldi uurida.
5. Projekti käigus ilmnes viga õppimise järjestuse jälgimisel. Alguses asus õppimisjärjestus isendiväljana klassis Päev. Kuna iga mängupäeva jaoks loodi uus Päev isend, siis vastav loendur lähtestati iga kord automaatselt väärtusele 0 (isendiväljade vaikimisi algväärtus). Selle tulemusena ei töötanud õppimise 3-kordse järjestuse blokeerimise mehhanism korrektselt. Probleemi lahendamiseks viidi õppimisjärjestus isendiväljaks klassi Õpilane, et selle väärtus säiliks kogu mängu vältel. Klass Päev muudeti vastavalt nii, et see kasutab väärtuse lugemiseks ja muutmiseks meetodeid getÕppimisjärjestus() ja setÕppimisjärjestus(). Pärast muudatust töötab blokeerimise loogika korrektselt üle kõikide mängupäevade.

# Hinnang lõpptulemusele
### Millega saime hästi hakkama
Programm töötab korrektselt ning täidab kõik rühmatöö nõuded. Kasutasime mitut klassi, JavaFX graafilist kasutajaliidest, hiire ja klaviatuuri sündmusi, failide lugemist ja kirjutamist ning erinditöötlust.
Saime hästi hakkama programmi loogilise ülesehitusega, sest jagasime suure programmi väiksemateks klassideks, millel on erinevad ülesanded. Näiteks eraldasime kasutajaliidese, mänguloogika ja failidega töötamise erinevatesse klassidesse.
Samuti oleme rahul programmi visuaalse poolega. Võrreldes esimese etapiga on kasutajaliides palju mugavam ja selgem. Mängija näeb oma statistikat, tegevusnuppe ja mängu logi ühes aknas.
Lisaks töötab õppimise blokeerimise süsteem, eksami arvutamine ning võimalus pärast mängu lõppu uuesti alustada.

### Mis vajab arendamist
Programmi võiks tulevikus veel visuaalselt edasi arendada, näiteks lisada rohkem animatsioone, pilte või erinevaid mängustsenaariumeid. Samuti võiks tulevikus lisada rohkem juhuslikke sündmusi ja rohkem tegevusvalikuid.

# Testimine
### Üksikute klasside testimine
Üksikuid klasse testiti eraldi, kasutades ajutisi testandmeid ning programmi erinevaid osi eraldi käivitades.
1. `Tegevus`
Loodi `Tegevus` objekt ning kutsuti meetodit `getTeadmistekasv()` mitu korda järjest, et kontrollida, kas teadmiste väärtused jäävad alati etteantud vahemikku. Samuti kontrolliti, et enesekindluse muutused oleksid õiged.
2. `Õpilane`:
Loodi `Õpilane` objekt ning testiti meetodeid `lisaTeadmised()` ja `lisaEnesekindlus()` nii positiivsete kui ka negatiivsete väärtustega. Kontrolliti, et väärtused ei läheks alla nulli.
3. `NõuandeGeneraator`:
Kutsuti meetodit `getNõuanne()` korduvalt ning kontrolliti, et tagastatakse erinevaid nõuandeid.
4. `EksamiArvutaja`:
Testiti erinevaid olukordi (palju teadmisi, vähe teadmisi, erinev enesekindlus ja õnnetegur), et kontrollida lõpliku punktisumma korrektset arvutamist.
5. `FailiHaldur`:
Kontrolliti, kas mängu logi kirjutatakse õigesti faili ning kas programmi käivitamisel loetakse failist viimane logirida korrektselt.

### Kogu programmi testimine
Programmi testiti tervikuna, mängides läbi mitu täismängu erinevate strateegiatega.
1. "Ideaalne tudeng" - valiti iga kord õppimine või ülesannete lahendamine. Veenduti, et teadmised kasvavad piisavalt ning eksam läbitakse üldjuhul edukalt.
2. "Prokrastineerija" - valiti peamiselt Netflixi vaatamine ja puhkamine. Veenduti, et teadmised jäävad madalaks ning eksam enamasti ebaõnnestub.
3. "Blokeerimise test" - valiti õppimine 3 korda järjest ning kontrolliti, et 4. korral kuvatakse blokeerimise hoiatus ning valikut ei aktsepteerita.
4. "Vale sisend" - sisestati valikuaknasse tähed, tühi vastus ning vahemikust väljas olevad arvud. Veenduti, et programm küsib alati uuesti ega lõpeta tööd veaga.
5. "Akna sulgemine" - vajutati valikuaknas nuppu X ning kontrolliti, et kuvatakse lahkumise kinnitusdialoog.

Kõik testid andsid oodatud tulemused ning programmi töös ei esinenud tõrkeid.
