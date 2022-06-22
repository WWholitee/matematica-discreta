import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1:Elena Fernández Wyzynska
 * - Nom 2: Marc Gómez Barceló
 * - Nom 3:
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els predicats de dues
   * variables són de tipus `BiPredicate<Integer, Integer>` i similarment s'avaluen com
   * `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per utilitzar la força bruta)
   */
  static class Tema1 {
    /*
     * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
     */
   static boolean exercici1(int[] universe,BiPredicate<Integer, Integer> p,Predicate<Integer> q,Predicate<Integer> r) {
             int h = 0;
             for (int i = 0; i <universe.length; i ++) {
                  int x = universe[i];
                 for (int j = 0; j <universe.length; j ++) {
                     int y = universe[j];
                     if (x!=y && ((p.test(x,y) == false) || (p.test(x,y) == true && q.test(x) == true && r.test(y) == true))) {
                         h++;
                     }
                 }

             }
             if (h == (universe.length*(universe.length-1))) {
                 return true;
             } else
                 return false;
}

    /*
     * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
            int h;
            int k=0;
            for (int i = 0; i <universe.length; i ++) {
                h=0;
                int x = universe[i];
                for (int j = 0; j <universe.length; j ++) {
                    int y = universe[j];
                    if (x!=y && !((p.test(x) == false && q.test(y) == true))) {
                        h++;
                    }
                }
                if (h == (universe.length-1)) {
                    k++;
                }
            }
            if (k == 1) {
                return true;
            } else {
                return false;
            }

        }

    /*
     * És cert que ¬(∃x. ∀y. y ⊆ x) ?
     *
     * Observau que els membres de l'univers són arrays, tractau-los com conjunts i podeu suposar
     * que cada un d'ells està ordenat de menor a major.
     */
    static boolean exercici3(int[][] universe) {
     
      return false; // TO DO
    }

    /*
     * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
     */
    static boolean exercici4(int[] universe, int n) {
       int x;
      int y;
      int cont = 0;
      int contaux;
      for (int i = 0; i<universe.length; i++){
      contaux = 0;
      x = universe [i];
      for (int j = 0; j<universe.length; j++){
      y = universe[j];
      if (x!=y && ((x*y)%n==1)){
      contaux++;
      }
      }
      if (contaux == (universe.lenght-1)){
      cont++;
      }
      }
      if (cont ==universe.length){
      return true;
      }else return false;
       // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // ∀x,y. P(x,y) -> Q(x) ^ R(y)

      assertThat(
          exercici1(
              new int[] { 2, 3, 5, 6 },
              (x, y) -> x * y <= 4,
              x -> x <= 3,
              x -> x <= 3
          )
      );

      assertThat(
          !exercici1(
              new int[] { -2, -1, 0, 1, 2, 3 },
              (x, y) -> x * y >= 0,
              x -> x >= 0,
              x -> x >= 0
          )
      );

      // Exercici 2
      // ∃!x. ∀y. Q(y) -> P(x) ?

      assertThat(
          exercici2(
              new int[] { -1, 1, 2, 3, 4 },
              x -> x < 0,
              x -> true
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0  // x és múltiple de 4
          )
      );

      // Exercici 3
      // ¬(∃x. ∀y. y ⊆ x) ?

      assertThat(
          exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {} })
      );

      assertThat(
          !exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {}, {0, 1, 2, 3} })
      );

      // Exercici 4
      // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?

      assertThat(
          exercici4(
              new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
              11
          )
      );

      assertThat(
          !exercici4(
              new int[] { 0, 5, 7 },
              13
          )
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com arrays (sense
   * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant int[] a,
   * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar com f.apply(x) (on
   * x és un enter d'a i el resultat f.apply(x) és un enter de b).
   */
  static class Tema2 {
    /*
     * És `p` una partició d'`a`?
     *
     * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`. Podeu suposar que
     * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
     */
    static boolean exercici1(int[] a, int[][] p) {
        int campos = 0; // numero de campos grandes
        int espacio=0; // guardará el numero de caracteres que hay en a
        int numiguales=0;
        espacio= a.length;
        campos=p.length;
        int subespacios =0;// numero de espacio en un campo
        for(int i = 0; i<campos;i++){
            for(int j=0;j<p.length;j++){
                for(int k=0;k<p[j].length;k++){
                    if(a[k]==p[i][j]){
                        numiguales++;
                    }
                }
            }

        }
        if(numiguales== espacio){

            return true;

        }
       // TO DO
        return false;


    }

    /*
     * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que `x` n'és el mínim.
     *
     * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
     */
    static boolean exercici2(int[] a, int[][] rel, int x) {
        // TO DO
        //////////////////comprobamos que sea reflexiva
        int numreflexivo = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < rel.length; j++) {
                for (int z = 0; z < rel[j].length; z++) {
                    if (rel[j][z] == a[i]) {

                        if (rel[j][1] == a[i]) {
                            numreflexivo++;
                        }
                    }

                }

            }

        }
        if (numreflexivo != a.length) {
            return false;
        }
        //////////////// comprobamos que es antisimetrica 
        int posc1=0;
        int posc2=0;
        int numbuscando=0;
        for (int j = 0; j < rel.length; j++) {
            for (int z = 0; z < rel[j].length; z++) {
                    if(numbuscando==0){
                        posc1=rel[j][z];
                        z++;
                        posc2=rel[j][z];
                        numbuscando++;
                    }else if(posc2==rel[j][z]){
                        z++;
                        if(posc1==rel[j][z]){
                            return false;

                        }

                    }
            }
        }
        //////////comprobamos transitividad
        int tran=0;
        for(int h=0;h<rel.length;h++){
            int val_1=rel[h][0];
            int val_2=rel[h][1];
            for(int i=0;i<rel.length;i++){
                if(val_1==rel[i][1]){
                    if(val_2==rel[i][0]){
                    tran++;
                    }
                }else if(val_2==rel[i][0]){
                    val_2=rel[i][1];
                }
            }

        }

        if(tran>=1){
            return true;
        }
      return false; 

    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Trobau l'antiimatge de
     * `y` (ordenau el resultat de menor a major, podeu utilitzar `Arrays.sort()`). Podeu suposar
     * que `y` pertany a `codom` i que tant `dom` com `codom` també estàn ordenats de menor a major.
     */
   static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {

        int a =0;
        int cnum= dom.length;
        int[] antiimagen;
        ArrayList<Integer> guard = new ArrayList<Integer>();

        for(int i=0; i<cnum;i++){
            a=f.apply(dom[i]);
            if(a==y){
                guard.add(dom[i]);

            }

        }
        int p= guard.size();
        antiimagen =new int[p];
        for(int i=0;i<p;i++){
            antiimagen[i]=guard.get(i);

        }
        System.out.println(antiimagen[1]);
        return antiimagen;
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
     * - 3 si `f` és bijectiva
     * - 2 si `f` només és exhaustiva
     * - 1 si `f` només és injectiva
     * - 0 en qualsevol altre cas
     *
     * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per comoditat, podeu
     * utilitzar les constants definides a continuació:
     */
    static final int NOTHING_SPECIAL = 0;
    static final int INJECTIVE = 1;
    static final int SURJECTIVE = 2;
    static final int BIJECTIVE = INJECTIVE + SURJECTIVE;

   static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
        int p =dom.length;
        int o=codom.length;
        int inj=0;
        int isinj=0;
        int sobr=0;
        int issobr=0;
        int isbiy=0;
        int isnoth=0;
        //////////////////////comprobamos inejectiva
         if(o>=p){
             for(int i=0;i<p;i++){
                 if(codom[i]==dom[i]){
                     inj++;
                 }
             }
         }
         if(inj==p){
             isinj=1;
         }
         ///////////////////comprobamos sobreyectiva 
         for (int i=0;i<o;i++){
             if(dom[i]==codom[i]){
                 sobr++;
             }
         }
         if(sobr==o){
             issobr=1;
         }
         if(issobr==1){
             if(isinj==1){
                 isbiy=1;
                 issobr=0;
                 isinj=0;
             }
         }

         if(isinj==1){
             return 1;

         }else if(issobr==1){
             return 2;
         }else if(isbiy==1){
             return 3;
         }else
      return  0;
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `p` és una partició d'`a`?

      assertThat(
          exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {3, 5}, {4} }
          )
      );

      assertThat(
          !exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {5}, {1, 4} }
          )
      );

      // Exercici 2
      // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?

      ArrayList<int[]> divisibility = new ArrayList<int[]>();

      for (int i = 1; i < 8; i++) {
        for (int j = 1; j <= i; j++) {
          if (i % j == 0) {
            // i és múltiple de j, és a dir, j|i
            divisibility.add(new int[] { j, i });
          }
        }
      }

      assertThat(
          exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3 },
              new int[][] { {1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3} },
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              2
          )
      );

      // Exercici 3
      // calcular l'antiimatge de `y`

      assertThat(
          Arrays.equals(
              new int[] { 0, 2 },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1 },
                  x -> x % 2, // residu de dividir entre 2
                  0
              )
          )
      );

      assertThat(
          Arrays.equals(
              new int[] { },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1, 2, 3, 4 },
                  x -> x + 1,
                  0
              )
          )
      );

      // Exercici 4
      // classificar la funció en res/injectiva/exhaustiva/bijectiva

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> (x + 1) % 4
          )
          == BIJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3, 4 },
              x -> x + 1
          )
          == INJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1 },
              x -> x / 2
          )
          == SURJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> x <= 1 ? x+1 : x-1
          )
          == NOTHING_SPECIAL
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Aritmètica).
   *
   */
  static class Tema3 {
    /*
     * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
     *
     * Podeu suposar que `a` i `b` són positius.
     */
    static int exercici1(int a, int b) {
     while (b!=0){
      x=b;
      b=a%b;
      a=x;
      }
      return a;
      while (b!=0){
      x=b;
      b=a%b;
      a=x;
      }
      return a;
      int x;
      while (b!=0){
      x=b;
      b=a%b;
      a=x;
      }
      return a;
    }

    /*
     * Es cert que `a``x` + `b``y` = `c` té solució?.
     *
     * Podeu suposar que `a`, `b` i `c` són positius.
     */
    static boolean exercici2(int a, int b, int c) {
      int x;
      while(b!=0) {
      x=b;
      b=a%b;
      a=x;
      }
      if (c%a==0){
      return true;
      } else return false;
    }

    /*
     * Quin es l'invers de `a` mòdul `n`?
     *
     * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
     */
    static int exercici3(int a, int n) {
      int noexiste = -1;
      int cont = 0;
      for (int x=1; x<n; x++){
      if (((a%n)*(x%n))%n==1){
      return x;
      } else cont++;
      }
      if (cont==(n-1)){
      return noexiste;
      }
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `mcd(a,b)`

      assertThat(
              exercici1(2, 4) == 2
      );

      assertThat(
              exercici1(1236, 984) == 12
      );

      // Exercici 2
      // `a``x` + `b``y` = `c` té solució?

      assertThat(
              exercici2(4,2,2)
      );
      assertThat(
              !exercici2(6,2,1)
      );
      // Exercici 3
      // invers de `a` mòdul `n`
      assertThat(exercici3(2, 5) == 3);
      assertThat(exercici3(2, 6) == -1);
    }
  }

  static class Tema4 {
    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i la mida del graf.
     */
    static int[] exercici1(int[][] A) {
     int mida = 0;
    Int cont = 0;
    for (int x = 0,  x < A.length,  x++){
    for (y = cont, y<Alength, y++){
    mida = mida + A[x][y];
    }
    cont++;
    }
    int resultat[] = new int[2];
    resultat[0] = cont + 1;
    resultat[1] = mida;
    return resultat;
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es eulerià.
     */
    static boolean exercici2(int[][] A) {
      int total;
      int cont=0;
      for (int x=o, x<A.length, x++){
      total = 0;
      for (int y=0, y<A.length, y++){
      total = total + A[x][y];
      }
      if (total%2 == 0){
      cont++;
      }
      }
      if (cont == A.length){
      return true;
      }else return false;
    }

    /*
     * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills dels nodes interiors i de l'arrel,
     * retornau el nombre total de vèrtexos de l'arbre
     *
     */
    static int exercici3(int n, int d) {
      int aux = n;
      int total = n;
      for (aux=!0){
      aux= aux - d;
      aux++;
      total++;
      }
      return total;
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el graf conté algún cicle.
     */
    static boolean exercici4(int[][] A) {
      int contaux;
      int cont = 0;
      for (int x=0,;x<A.length; x++){
      contaux = 0;
      for (int y=0; y<A.length; y++){
      contaux = contaux+ A[x][y];
      }
      if (contaux ==  A.length){
      cont++;
      }
      }
      if (cont == A.length){
      return true;
      }else return false; 
    }
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `ordre i mida`

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}}), new int[] {3, 2})
      );

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0, 1}, {1, 0, 1, 1}, {0 , 1, 0, 1}, {1, 1, 1, 0}}), new int[] {4, 5})
      );

      // Exercici 2
      // `Es eulerià?`

      assertThat(
              exercici2(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici2(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}})
      );
      // Exercici 3
      // `Quants de nodes té l'arbre?`
      assertThat(exercici3(5, 2) == 9);
      assertThat(exercici3(7, 3) == 10);

      // Exercici 4
      // `Conté algún cicle?`
      assertThat(
              exercici4(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici4(new int[][] { {0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
      );

    }
  }


  /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
