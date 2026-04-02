# 1.rühmatöö kirjeldus

# EKSAMI SIMULAATOR

#### Autorid: Viktorija Korjagina, Maria Elisa Vassiljeva

# Projekti kirjeldus
### Programmi eesmärk
"Eksami Simulaator" on interaktiivne tekstipõhine mäng, milles mängija kehastab üliõpilast, kellel on eksam tulemas. Mängu eesmärk on eksam edukalt läbida - selleks tuleb 3 mängupäeva jooksul teha õigeid valikuid ning koguda piisavalt teadmispunkte. Mängija otsused mõjutavad kahte näitajat: teadmisi ja enesekindlust. Lõplik eksamitulemus sõltub kogutud teadmistest, enesekindluse boonusest ning juhuslikust õnnetegurist. Eksami läbimiseks on vaja saavutada vähemalt 50 punkti.

### Programmi üldine töö
Mäng kestab 3 mängupäeva. Iga päev teeb mängija 5 valikut, mida teha: õppida, vaadata loenguvideot, lahendada ülesandeid, vaadata Netflixi või puhata. Igal valikul on erinev mõju kahele näitajale: teadmistele ja enesekindlusele. Pärast 3. päeva toimub eksam, kus lisandub juhusliku õnneteguri mõju. Mängija läbib eksami, kui lõplik tulemus on 50 punkti või rohkem.

Programm kasutab kasutajaliidesena JOptionPane’i aknaid - kõik küsimused, valikud ja tulemused kuvatakse hüpikakendena. Mängija sisestab oma valiku (numbri 1–5) sisestusaknasse. Kui mängija valib "õppimise" 3 korda järjest, blokeeritakse see valik ning programm nõuab teise tegevuse valimist.

### Lühike kasutusjuhis
1. Käivitage programm IntelliJ IDEA's, vajutades rohelist nuppu Run failis Peaklass.java.
2. Sisestage oma nimi esimeses aknas.
3. Iga päev kuvatakse kõigepealt nõuandeaken, seejärel valikuteaken.
4. Sisestage valikuaknasse number 1-5 ja vajutage OK.
5. Pärast igat valikut kuvatakse tulemuseaken, kus on näha saadud punktid.
6. Pärast 3. mängupäeva toimub eksam - tulemus kuvatakse automaatselt.

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
`Tegevus.java`, `NõuandeGeneraator.java`, `Päev.java`; GitHub-i repositooriumi seadistamine; osalemine rühmatöö kirjelduse koostamisel.
Ajakulu: orienteeruvalt 8 tundi.

### Maria Elisa Vassiljeva:
`Õpilane.java`, `Eksam.java`, `Peaklass.java`; programmi testimine; osalemine rühmatöö kirjelduse koostamisel.
Ajakulu: orienteeruvalt 8 tundi.

# Tegemise mured
1. Git ja GitHub. Alguses valmistas raskusi harude (branch) loogika mõistmine ning muudatuste omavaheline sünkroonimine. Eriti keeruline oli olukord, kus mõlemad rühmaliikmed muutsid sama faili samaaegselt, mis nõudis konfliktide lahendamist.
2. `JOptionPane` kasutamine oli esmakordne kogemus. Tuli õppida eristama meetodeid `showInputDialog()`, `showMessageDialog()` ja `showConfirmDialog()` ning lisaks kontrollida, kas kasutaja sulges akna ilma midagi sisestamata (null-kontroll).
3. Klasside omavahelise struktuuri planeerimine valmistas alguses raskusi. Eriti keeruline oli läbi mõelda, kuidas klassid omavahel suhtlevad ja milline peaks olema nende vastutus. Näiteks tuli otsustada, kuidas Päev kasutab samaaegselt nii Tegevus kui ka Õpilane objekte ning kuidas andmed nende vahel liiguvad. See nõudis kogu programmi ülesehituse läbimõtlemist enne realiseerimist.
4. `try-catch` ploki kasutamine `NumberFormatException`'i püüdmiseks oli uus kontseptsioon, mida tuli eraldi uurida.
5. Projekti käigus ilmnes viga õppimise järjestuse jälgimisel. Alguses asus õppimisjärjestus isendiväljana klassis Päev. Kuna iga mängupäeva jaoks loodi uus Päev isend, siis vastav loendur lähtestati iga kord automaatselt väärtusele 0 (isendiväljade vaikimisi algväärtus). Selle tulemusena ei töötanud õppimise 3-kordse järjestuse blokeerimise mehhanism korrektselt. Probleemi lahendamiseks viidi õppimisjärjestus isendiväljaks klassi Õpilane, et selle väärtus säiliks kogu mängu vältel. Klass Päev muudeti vastavalt nii, et see kasutab väärtuse lugemiseks ja muutmiseks meetodeid getÕppimisjärjestus() ja setÕppimisjärjestus(). Pärast muudatust töötab blokeerimise loogika korrektselt üle kõikide mängupäevade.

# Hinnang lõpptulemusele
### Millega saime hästi hakkama
Programm töötab korrektselt ning täidab kõik ülesandes nõutud tingimused: mitu klassi koos konstruktorite, getterite ja setteritega, klassi `Random` kasutamine, `JOptionPane`-põhine kasutajaliides, mõistlikud kommentaarid ning kasutajasõbralik dialoog.
Mängu loogika on selge ning mängijale arusaadav - igal hetkel on näha, mis toimub ja millised on valiku tagajärjed. Õppimise blokeerimise mehhanism töötab täpselt nii nagu kavandatud. GitHub-i koostöövoog toimis projekti lõpuks hästi - mõlemad rühmaliikmed said muudatusi teha ning need edukalt ühendada.

### Mis vajab arendamist
Programmi visuaalne pool on minimaalne - `JOptionPane` aknad on lihtsad ega ole eriti atraktiivsed. Edasiarendusena võiks kasutada graafilist kasutajaliidest, näiteks `JavaFX`-i. Samuti puudub programmil võimalus tulemusi salvestada - iga mänguseanssi alustatakse nullist.

# Testimine
### Üksikute klasside testimine
Klasse testiti eraldi, lisades ajutiselt `Peaklass.java`-sse väikeseid testikoode.
1. `Tegevus`: loodi `Tegevus` objekt ning kutsuti meetodit `getTeadmistekasv()` 20 korda järjest, kontrollides, et tulemused jäävad alati lubatud vahemikku (näiteks 10–20 õppimise puhul). Kontrolliti ka, et meetod `getEnesekindlusMuutus()` tagastab alati korrektse fikseeritud väärtuse.
2. `Õpilane`: loodi `Õpilane` objekt ning kutsuti meetodeid `lisaTeadmised()` ja `lisaEnesekindlus()` nii positiivsete kui negatiivsete väärtustega. Kontrolliti, et kumbki näitaja ei lange alla nulli.
3. `NõuandeGeneraator`: kutsuti meetodit `getNõuanne()` korduvalt ning kontrolliti, et tagastatakse alati sisukas tekst ning erinevatel kordadel ilmuvad erinevad nõuanded.
4. `Eksam`: testiti kolme erinevat stsenaariumi - kõrged teadmised, madalad teadmised ning keskmised teadmised erineva õnnefaktoriga. Kontrolliti, et lõplik skoor arvutatakse vastavalt kehtestatud valemile korrektselt.

### Kogu programmi testimine
Programmi testiti tervikuna, mängides läbi mitu täismängu erinevate strateegiatega.
1. "Ideaalne tudeng" - valiti iga kord õppimine või ülesannete lahendamine. Veenduti, et teadmised kasvavad piisavalt ning eksam läbitakse üldjuhul edukalt.
2. "Prokrastineerija" - valiti peamiselt Netflixi vaatamine ja puhkamine. Veenduti, et teadmised jäävad madalaks ning eksam enamasti ebaõnnestub.
3. "Blokeerimise test" - valiti õppimine 3 korda järjest ning kontrolliti, et 4. korral kuvatakse blokeerimise hoiatus ning valikut ei aktsepteerita.
4. "Vale sisend" - sisestati valikuaknasse tähed, tühi vastus ning vahemikust väljas olevad arvud. Veenduti, et programm küsib alati uuesti ega lõpeta tööd veaga.
5. "Akna sulgemine" - vajutati valikuaknas nuppu X ning kontrolliti, et kuvatakse lahkumise kinnitusdialoog.

Kõik testid andsid oodatud tulemused ning programmi töös ei esinenud tõrkeid.
