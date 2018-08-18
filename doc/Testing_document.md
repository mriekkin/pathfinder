# Testing document

Tämä dokumentti on vielä luonnosteluasteella. Olen kuitenkin kirjoittanut tähän hieman suunnitelmia testauksen suhteen.

*Miten voi määritellä, että onnistunut?*

**OIKEELLISUUS**

1. Aja benchmark-testit läpi ja vertaa lyhyimpien polkujen pituuksia referenssi-arvoihin.

   Kattava, realistinen (pelit). Polut pitkiä, joten pienetkin virheet poluissa kumuloituvat. Arvot ilmoitettu 8 desimaalin tarkkuudella, joten pienetkin virheet havaittavissa. Voitaneen olettaa, että useimmat virheet tai poikkeamat saadaan tällä tavalla esiin.

   Tämä kehitys ollut nähtävissä devaamisen aikana. Meni jonkin aikaa, että arvot saatiin mätsäämään referensseihin. A*:n tapauksessa piti vaihtaa heuristiikka Manhattanista "octileen". JPS:n tapauksessa piti fiksata joukko bugeja, ja toteuttaa "no corner-cutting" -optio. Varsinkin JPS:n tapauksessa oli havaittavissa, että arvot pikkuhiljaa konvergoituivat kohti oikeita arvoja jokaisen muutoksen myötä. Tällä hetkellä tarkkuus poluissa noin luokkaa 10^(-6). Kahdessa viimeisessä desimaalissa edelleen pientä heittoa. Voisikohan kenties johtua laskennan aikana tapahtuvista liukulukujen pyöristysvirheistä.

2. Yksityiskohtaiset yksikkötestit jokaiselle osa-alueelle.

   Reilut 120 yksikkötestiä, testikattavuus 96 %. Kuvaa tähän tarkemmin, miten testattu eri osa-alueita.

**SUORITUSKYKY**

Toistaiseksi olen tehnyt seuraavista vasta kohdan 1. Niinpä kohdat 2 ja 3 ovatkin enemmän pohdintaa siitä, mitä voisi tehdä, jos aikaa on riittävästi.

1. Algoritmien suhteellinen (keskinäinen) suorituskyky.

   Vertailen algoritmien keskinäistä suorituskykyä benchmark problemeilla. Omia tuloksia voi verrata papereissa esitettyihin. Esim JPS pitäisi saavuttaa ainakin kymmenkertainen parannus suhteessa A*. Tätä on jo aloitettu ja hieman dokumentoitukin. Jatkan tätä ensi viikolla.

2. Algoritmien absoluuttinen suorituskyky.

   Benchmarkkien tuloksien perusteella voi arvioida aikaa, joka menee eri ongelmien ratkomiseen. Esim. nykyisten testien perusteella yhden ongelman ratkomiseen menee omalla koneella aikaa luokkaa 10 ms. Mutta onko 10 ms hyvä vai huono tulos? Onko oma toteutus hyvin kirjoitettu? Sen selvittämiseksi pitäisi varmaankin ladata jostakin referenssitoteutukset, ajaa niitä, ja sitten vertailla tuloksia omaan ohjelmaan. Tämä jää varmaan kuitenkin tekemättä.

3. Verkon koon ja polun pituuden vaikutus suorituskykyyn, asymptoottinen suorituskyky (iso O)

   Tämän avulla saataisiin esiin se, miten nopeus riippuu verkon koosta. Pahimpien tapauksien aikavaativuuden selvitämiseksi pitäisi kuitenkin ilmeisesti generoida patologisia tapauksia. Tämä olisi varmasti mielenkiintoinen, mutta en tiedä, missä määrin ehdin tekemään? Nykyisessä analyysissä (1.) tulee esiin suoritusaika suhteessa polun pituuteen.
