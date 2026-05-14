# Rühmatöö kirjeldus

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

### EksamiSimulaatorFX.java
`EksamiSimulaatorFX` on teise etapi programmi peaklass. See käivitab JavaFX rakenduse ja loob `MänguKontrollija` objekti.

### MänguKontrollija.java
`MänguKontrollija` juhib kogu JavaFX-versiooni mänguloogikat ning ühendab omavahel vaated, mängija, tegevused, faili kirjutamise ja eksami arvutamise. Klass töötleb mängija valikuid, uuendab teadmisi ja enesekindlust, kontrollib õppimise järjestust, liigub päevade vahel ning käivitab eksami.

Olulisemad meetodid: `naitaAlgusAkent()` - avab algusakna; `alustaMangu(String nimi)` - kontrollib nime, loob `Õpilane` objekti ja alustab mängu; `teeValik(int valik)` - töötleb mängija tegevusvalikut; `liiguEdasi()` - liigub järgmise valiku, päeva või eksamini; `sooritaEksam()` - käivitab eksami arvutamise ja kuvab tulemuse; `uuendaEkraani()` - uuendab JavaFX aknas kuvatavaid andmeid.

### AlgusVaade.java
`AlgusVaade` loob programmi esimese JavaFX akna. Selles aknas kuvatakse mängu lühitutvustus, kasutaja saab sisestada oma nime ning näeb viimast logirida eelmisest mängust. Klass tegeleb ainult algusakna kujunduse ja selle sündmustega.

Olulisemad meetodid: `AlgusVaade(MänguKontrollija kontrollija)` - konstruktor, mis salvestab viite kontrollijale; `looStseen()` - loob ja tagastab algusakna `Scene` objekti.

### MänguVaade.java
`MänguVaade` loob JavaFX abil peamise mänguakna. Seal kuvatakse mängija statistika, progressiribad, tegevusnupud ja mängu logi. Klass töötleb hiirega nupuvajutusi ja klaviatuuriga tehtud valikuid, kuid mängu arvutused tehakse `MänguKontrollija` klassis.

Olulisemad meetodid: `MänguVaade(MänguKontrollija kontrollija)` - konstruktor; `looStseen()` - loob põhilise mänguakna; `uuendaAndmeid(Õpilane õpilane, int päevaNr, int valikNr, String nõuanne)` - uuendab ekraanil kuvatavaid andmeid; `lisaLogiRida(String rida)` - lisab ühe rea logialasse; `keelaNupud()` - keelab nupud pärast mängu lõppu.

### Õpilane.java
`Õpilane` esindab mängijat ning hoiab tema andmeid. Klassil on väljad `nimi`, `teadmised`, `enesekindlus` ja `õppimisJärjestus`. Teadmised ja enesekindlus algavad nullist ega lange alla nulli. Õppimisjärjestust kasutatakse selleks, et blokeerida õppimine, kui mängija valib seda liiga mitu korda järjest.

Olulisemad meetodid: `Õpilane(String nimi)` - loob uue mängija; `getNimi()`, `getTeadmised()`, `getEnesekindlus()`, `getÕppimisJärjestus()` - tagastavad mängija andmed; `setÕppimisJärjestus(int õppimisJärjestus)` - muudab õppimise järjestust; `lisaTeadmised(int n)` - muudab teadmisi; `lisaEnesekindlus(int n)` - muudab enesekindlust; `getStatsText()` - tagastab hetkeseisu tekstina.

### Tegevus.java
`Tegevus` esindab ühte tegevust, mida mängija saab valida. Igal tegevusel on nimi, kirjeldus, teadmiste kasvu vahemik ja enesekindluse muutus. Teadmiste kasv arvutatakse juhuslikult lubatud vahemikus.

Olulisemad meetodid: `Tegevus(String nimi, String kirjeldus, int minTeadmised, int maxTeadmised, int enesekindlusMuutus)` - konstruktor; `getNimi()` - tagastab tegevuse nime; `getKirjeldus()` - tagastab tegevuse kirjelduse; `getTeadmistekasv()` - arvutab juhusliku teadmiste kasvu; `getEnesekindlusMuutus()` - tagastab enesekindluse muutuse.

### TegevusteLooja.java
`TegevusteLooja` loob kõik mängus kasutatavad tegevused ja tagastab need massiivina. See klass on eraldi selleks, et tegevuste nimekiri ei muudaks `MänguKontrollija` klassi liiga pikaks.

Olulisemad meetodid: `looTegevused()` - loob ja tagastab `Tegevus[]` massiivi.

### NõuandeGeneraator.java
`NõuandeGeneraator` annab mängijale juhusliku nõuande. Klass sisaldab nõuannete massiivi ning valib sealt `Random` abil ühe teksti.

Olulisemad meetodid: `NõuandeGeneraator()` - konstruktor; `getNõuanne()` - tagastab juhusliku nõuande.

### EksamiArvutaja.java
`EksamiArvutaja` arvutab mängu lõpliku eksamitulemuse. Tulemus sõltub õpilase teadmistest, enesekindluse boonusest ja juhuslikust õnnetegurist vahemikus -20 kuni +20.

Olulisemad meetodid: `arvutaTulemus(Õpilane õpilane)` - arvutab eksami tulemuse ja tagastab `EksamiTulemus` objekti.

### EksamiTulemus.java
`EksamiTulemus` hoiab eksami arvutatud tulemuse andmeid: teadmised, enesekindluse boonus, õnnetegur, lõplik skoor ja tulemuse tekst. Klass teeb tulemuse kuvamise lihtsamaks, sest kõik eksamiga seotud andmed on ühes objektis.

Olulisemad meetodid: `EksamiTulemus(...)` - konstruktor; `getLõplikSkoor()` - tagastab lõpliku skoori; `getTulemusTekst()` - tagastab lühikese tulemuse teksti; `getÕnn()` - tagastab õnneteguri; `getTäisTekst()` - tagastab tulemuse pikema kirjelduse.

### FailiHaldur.java
`FailiHaldur` tegeleb faili lugemise ja kirjutamisega. Programm kirjutab mängu käigud logifaili ning loeb programmi käivitamisel failist viimase rea, et näidata eelmise mängu infot.

Olulisemad meetodid: `kirjutaLogisse(String tekst)` - kirjutab ühe rea logifaili; `loeViimaneRida()` - loeb failist viimase rea; `alustaUutMangu(String nimi)` - lisab faili uue mängu alguse.

### Kujundus.java
`Kujundus` sisaldab JavaFX kasutajaliidese stiilimeetodeid. Klass määrab tausta, nuppude, pealkirjade ja kaartide välimuse. Eraldi kujundusklass aitab vältida korduvat `setStyle()` koodi vaadete klassides.

Olulisemad meetodid: `taustaStiil(VBox juur)` - lisab aknale tausta; `pealkiri(Label label)` - kujundab suure pealkirja; `tavalineTekst(Label label)` - kujundab tavalise teksti; `nupp(Button nupp)` - kujundab tegevusnupu; `rohelineNupp(Button nupp)` - kujundab alustamise nupu; `kaart(Region region)` - lisab valge kaardi stiili.

# Projekti tegemise protsess
### 1. etapp - ideede genereerimine ja planeerimine
Esimeses kohtumises arutati võimalikke projektiteemasid. Otsustati luua humoorika suhtumisega üliõpilassimulatsioon, kuna see võimaldas kasutada kõiki nõutavaid programmeerimismõisteid ning tundus mõlemale rühmaliikmetele huvitav ja motiveeriv. Pandi paika programmi üldine kontseptsioon: 3 mängupäeva, 2 näitajat (teadmised ja enesekindlus) ning lõplik eksam juhusliku õnneteguriga. Koostati programmi struktuur ning otsustati klasside arv ja nende vastutusalad. Mõlemad rühmaliikmed osalesid selles etapis võrdselt.

### 2. etapp – töö jaotamine ja GitHubi seadistamine
Alguses lepiti kokku tööjaotus ning seadistati ühine GitHubi repositoorium. Viktorija võttis peamiselt enda peale mänguloogikaga seotud klassid: `Tegevus.java`, `TegevusteLooja.java`, `NõuandeGeneraator.java`, `Õpilane.java` ja `FailiHaldur.java`. Maria Elisa tegeles rohkem kasutajaliidese ja eksami kuvamisega seotud klassidega: `EksamiSimulaatorFX.java`, `AlgusVaade.java`, `MänguVaade.java`, `Kujundus.java`, `EksamiArvutaja.java` ja `EksamiTulemus.java`.
Mõlemad rühmaliikmed osalesid ka `MänguKontrollija.java` arendamises, kuna see klass ühendab kogu programmi loogika ning vajas mitmeid ühiseid arutelusid ja parandusi.
GitHubis töötas Viktorija harus **"viktoria"** ja Maria Elisa harus **"mariaelisa"**.
Projekti käigus aidati vajadusel teineteist vigade parandamisel, loogika muutmisel ning kasutajaliidese viimistlemisel.

### 3. etapp - programmeerimine
Mõlemad rühmaliikmed kirjutasid oma klassid iseseisvalt, konsulteerides teineteisega tekkinud probleemide korral. Kohtumistel arutati klasside omavahelist suhtlust - näiteks kuidas Päev kasutab Tegevus objekte ning kuidas Peaklass seob kõik klassid ühtseks tervikuks. Muudatuste sünkroonimiseks kasutati regulaarselt käske `git push` ja `git pull`.

### 4. etapp - testimine ja vigade parandamine
Pärast klasside valmimist testiti programmi ühiselt - mäng käivitati mitmeid kordi ning prooviti erinevaid stsenaariumeid. Avastati ja parandati mitmeid väikseid vigu. Viimistleti kommentaarid ning mängijale kuvatavad tekstid.

### 5. etapp - kirjelduse koostamine
Rühmatöö kirjeldus koostati ühiselt, arutades läbi iga punkti sisu. Mõlemad rühmaliikmed panustasid teksti kirjutamisse võrdselt.

# Rühmaliikmete panus ja ajakulu
### Viktorija Korjagina:
Tegeles peamiselt mängu loogikaga seotud klassidega: `Tegevus.java`, `TegevusteLooja.java`, `NõuandeGeneraator.java`, `Õpilane.java`, `FailiHaldur.java` ning osales `MänguKontrollija.java` loogika arendamises. Aitas parandada mängu tasakaalu, testida erinevaid tegevusi ning osales dokumentatsiooni koostamisel.
Ajakulu: orienteeruvalt 14 tundi.

### Maria Elisa Vassiljeva:
Tegeles peamiselt kasutajaliidese ja failidega seotud klassidega: `EksamiSimulaatorFX.java`, `AlgusVaade.java`, `MänguVaade.java`, `Kujundus.java`, `EksamiArvutaja.java`, `EksamiTulemus.java` ning osales programmi testimisel ja dokumentatsiooni koostamisel.
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
1. `Tegevus`:
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
