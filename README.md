

## Multiplayer Snake specifikáció

<p float="center">
  <img src="https://user-images.githubusercontent.com/58141904/160290747-53bc65c4-5b95-48b3-8c70-6443d9a541b2.png" width="250" >
  <img src="https://user-images.githubusercontent.com/58141904/160290753-4dd0741c-552f-4c64-a9d8-8d193bc34384.png" width="250" >
  <img src="https://user-images.githubusercontent.com/58141904/160290757-dcbcc945-93f6-4d97-af24-8437f16cc731.png" width="220" >
  <img src="https://user-images.githubusercontent.com/58141904/160290763-41bc38c1-fd94-4b33-9fd3-23eab1417b5f.png" width="250" >
</p>

A klasszikus „Snake” játék továbbfejlesztett változata. A játékban egy táblán egy kígyót irányítunk, a kígyó négy irányba tud menni (fel, le bal, jobb). Egyjátékos módban a célunk, hogy a pályán véletlenszerűen megjelenő almákat begyűjtsük, amik által a kígyó hossza egyel nő. Egy elfogyasztott alma helyett egy véletlenszerű helyen egy új alma jelenik meg. Ha a kígyó feje hozzáér a teste maradék részéhez vagy nekimegy a pálya falának, akkor a játékos vesztett. A tábla mezőin lehetnek még bombák is, amikhez ha a kígyó hozzáér szintén vége a játéknak. A bombák véletlenszerűen helyezkednek el a pályán, a helyük fix.

Többjátékos módnak két fajtája van:

 - **Játékos – játékos:** Egy billentyűzetről két ember játszik egymás
   ellen, két kígyó van, az egyjátékos módtól annyiban tér el, hogy ha a
   két kígyó ütközik akkor az a kígyó amelyik nekiment a másiknak
   meghal. Nyerni kétféleképpen lehet: az idő letelte után nekünk van a
   hosszabb kígyónk (több almát gyűjtöttünk) vagy ha az ellenfelünk az
   idő letelte előtt meghal. 
   
 - **Játékos – gép:** 	Az ember egy a gép által    irányított kígyó ellen
   játszik. A játékos – játékos módhoz képest a    szabályok nem
   változnak.

A gép által irányított kígyó véletlenszerűen halad.

**Alapértelmezett értékek:**

 - **Táblaméret**: 30 x 30 
 -  **Időlimit**: 120 sec 
 -  **Kígyó gyorsasága**: slow: 0,7 sec, normal: 0,5 sec, fast: 0,2 sec
 - **Almák száma**: few: 5 db, normal: 15 db, plenty: 50 db     
 - **Bombák száma**: few: 5 db, normal: 15 db, plenty: 50 db     

A programhoz tartozik még egy ranglista is, ahol egyjátékos módban az alapértelmezett értékekhez tároljuk a legjobb eredményeket (megevett almák száma), a játék végén a játékos által megadott névvel együtt. (mindegyik ranglistához egy külön „.ser” kiterjesztésű fájl tartozik) A ranglistába mentés automatikusan történik, amint a játékos befejez egy játékot és megad egy nevet.
A kígyók irányítása egyjátékos módban a nyíl billentyűkkel történik, többjátékos módban a második játékos a W, A, S, D billentyűket használja.

A programot Swingben fogom megvalósítani, 3 nézet fog hozzátartozni:
 - **Játék**: ahol a játék lebonyolítása történik
 - **Ranglista**: ahol meg lehet tekinteni a kiválasztott ranglistát
 - **Beállítások**: a játék attribútumait lehet megadni
