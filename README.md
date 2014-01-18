PA165 Manažer LEGO stavebnic
============================
Oficiální zadání:

Vžijme se opět do dětských let. Mějme firmu prodávající stavebnice Lego.
Aplikace bude sloužit jako manažer, který obstarává správu stavebnic,
setů skládajících se z různých kombinací a kategorií do kterých jsou zařazeny. 
Každá stavebnice bude mít soupis dílů které do ní patří stejně jako
každý set bude obsahovat soupis stavebnic ze kterých se skládá.
U každého dílu je zase potřeba uvádět barevné kombinace v jakých se vyrábí.
Manažer obstarává správu (tedy přídávání, odebírání, úprava) stavebnic a jejich dílů stejně jako setů.
Každá stavebnice se skládá z různých dílů. V nabídce jsou i sety složené z jednotlivých stavebnic.
Existují různé tématické kategorie pod které se stavebnice budou řadit. Je třeba uchovávat:

- Barevné kombinace u jednotlivých dílů
- Evidovat z jakých dílů v jaké barevné variantě je stavebnice složena
- Cena za jakou je stavebnice prodávána
- Je nutné evidovat věkovou hranici pro děti
- Z jakých stavebnic je složen set
- Za jakou cenu se každý set prodává (nemusí nezbytně odpovídat součtu cen stavebnic)
- Set i samotná stavebnice musí evidovat do které kategorie je přiřazena
- Kategorie také nese stručný popis


Spuštění
============================

Doporučujeme použít <b>Derby verze alespoň 10.7</b>.

Aplikace se zbuildí pomocí příkazu "<b>mvn clean install</b>".

Aplikace je spustitelná pomocí příkazu "<b>mvn tomcat7:run</b>" (v adresáři legoManager-web). 
url: http://localhost:8080/pa165/.

Poté je nutné provést naplnění databáze pomocí příkazu "<b>mvn sql:execute</b>" (v adresáři legoManager-backend). Bez tohoto kroku není možné se přihlásit (uživatelé jsou uloženi v databázi)

pro roli uživatel (pouze prohlížení) je přihlašovací jméno <b>user</b>, heslo je <b>heslo123</b>

pro roli administrátor (úprava dat a přidávání uživatelů) je přihlašovací jméno <b>admin</b>, heslo je <b>admin123</b>



Rest klient se spouští pomocí konzole z adresáře legoManager-rest-client následujícím příkazem:

        mvn exec:java -Dexec.args="[parametry]"

Parametry jsou poziční. První parametr je typ entity. V našem případe je to "brick" nebo "category".
Druhý parametr je "akce", další argumenty jsou variabilní dle zvolené akce.
Když se klient spustí bez parametrů vypíše se nápověda k použití.

Příkaz pro vypsání nápovědy:

    mvn exec:java -Dexec.mainClass=cz.muni.fi.PA165.App -Dexec.args=""



Pro entitu díly jsou dostupné tyto argumenty:

        list                              
        create <name> <color>             
        update <id> <newName> <newColor>  
        delete <id>                       
        findbyid <id>                     
        findbyname <name>                 
        findbycolor <color>               

Pro entitu kategore jsou dostupné tyto argumenty:

        list                              
        create <name> <description>       
        update <id> <name> <description>  
        delete <id>                       
        findbyid <id>                     
        findbyname <name>                 



Příklad 1: Vytvoření nového dílu:

        mvn exec:java -Dexec.args="brick create Figure darkgreen"

Příklad 2: Výpis všech dílů:

        mvn exec:java -Dexec.args="brick list"


Aplikace je lokalizovaná do angličtiny a češtiny.
