Multiplayer Snake specifikáció


A klasszikus „Snake” játék továbbfejlesztett változata. A játékban egy táblán egy kígyót irányítunk, 
a kígyó négy irányba tud menni (fel, le bal, jobb). Egyjátékos módban a célunk, hogy a pályán 
véletlenszerűen megjelenő almákat begyűjtsük, amik által a kígyó hossza egyel nő. Egy
elfogyasztott alma helyett egy véletlenszerű helyen egy új alma jelenik meg. Ha a kígyó feje 
hozzáér a teste maradék részéhez vagy nekimegy a pálya falának, akkor a játékos vesztett. A tábla 
mezőin lehetnek még bombák is, amikhez ha a kígyó hozzáér szintén vége a játéknak. A bombák 
véletlenszerűen helyezkednek el a pályán, a helyük fix. 

Többjátékos módnak két fajtája van:
• Játékos – játékos:
    Egy billentyűzetről két ember játszik egymás ellen, két kígyó van, az egyjátékos módtól 
    annyiban tér el, hogy ha a két kígyó ütközik akkor az a kígyó amelyik nekiment a másiknak 
    meghal. Nyerni kétféleképpen lehet: az idő letelte után nekünk van a hosszabb kígyónk 
    (több almát gyűjtöttünk) vagy ha az ellenfelünk az idő letelte előtt meghal.
  
• Játékos – gép:
    Az ember egy a gép által irányított kígyó ellen játszik. A játékos – játékos módhoz képest 
    a szabályok nem változnak.
    
A gép által irányított kígyó minden lépés előtt megkeresi a hozzá legközelebb elhelyezkedő almát, 
majd az ellenfél mozgását, a bombákat és a saját testének elhelyezkedését figyelembe véve 
kiszámolja hozzá a legrövidebb utat, amennyiben ez az út hosszabb, mint egy másik almától vett 
távolsága akkor megtervezi ahhoz az almához is az utat, ha az első tervezett út rövidebb volt, mint 
a mostani akkor az eredeti alma felé megy, ha nem akkor az új felé. Minden lépésében van 
valamekkora esély, hogy nem arra lép amerre akar, így válik állítható nehézségűvé a gép által 
irányított kígyó.

A pálya egy n x n-es tábla, ami mezőkből áll. A tábla mérete, az egyszerre a pályán levő almák 
száma, a bombák száma, a kígyók gyorsasága (két lépés közötti időkülönbség), többjátékos 
módban az időlimit és a gép „ügyessége” (annak a valószínűsége, hogy hibázik) tetszőlegesen 
állítható, de vannak előre definiált beállítások is.

Alapértelmezett értékek:
    • Táblaméret: 30 x 30
    • Időlimit: 120 sec
    • Kígyó gyorsasága: slow: 0,7 sec, normal: 0,5 sec, fast: 0,2 sec
    • Almák száma: few: 5 db, normal: 15 db, plenty: 50 db
    • Bombák száma: few: 5 db, normal: 15 db, plenty: 50 db
    • Kígyó ügyessége: easy: 15%, normal: 10%, hard: 4%
    
A programhoz tartozik még egy ranglista is, ahol egyjátékos módban az alapértelmezett értékekhez 
tároljuk a legjobb eredményeket (megevett almák száma), a játék végén a játékos által megadott 
névvel együtt. (mindegyik ranglistához egy külön „.ser” kiterjesztésű fájl tartozik) A ranglistába 
mentés automatikusan történik, amint a játékos befejez egy játékot és megad egy nevet.

A kígyók irányítása egyjátékos módban a nyíl billentyűkkel történik, többjátékos módban a 
második játékos a W, A, S, D billentyűket használja.

A programot Swingben fogom megvalósítani, 3 nézet fog hozzátartozni:
    • Játék: ahol a játék lebonyolítása történik
    • Ranglista: ahol meg lehet tekinteni a kiválasztott ranglistát
    • Beállítások: a játék attribútumait lehet megadni
