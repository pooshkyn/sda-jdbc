#Zadania JDBC

W klasie `JdbcUserRepository` i `JdbcPostRepository` należy zaimplementować 
interfejsy odpowiednio `UserRepository` i `PostRepository` oraz metody tam zadeklarowane.
Do każdej metody w interfejsach zostały napisane testy automatyczne. 

Przed rozpoczęciem zadań należy:
1. włączyć annotation procesor w ustwieniach IntellJ 
    * Build, Execution, Deployment -> Compiler -> Annotation Processors
2. stworzyć bazę danych o nazwie `twitterexample` oraz odpowiednią strukturę (sprawdź do niej dostęp).
    * skrypty znajdują się w katalogu `/baza`
4. stworzyć osobnego brancha na którym będziesz pracować
3. zdefiniować poprawny URL do bazy danych w repozytoriach.
    * `"jdbc:mysql://localhost:3306/twitterexample?user=user&password=password&serverTimezone=UTC";`
4. dodać potrzebne zależności do pom.xml.
    * ```xml
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>8.0.17</version>
      </dependency>

Uwaga! Dane są usuwane z bazy przed każdym uruchomieniem testów.

Zadania do zrobienia:
1. Zaimplementuj metody które pozwolą wykonywać operacje na tabeli użytkowników:
    * Sprawdzić połączenie z bazą (użyj metody isValid()).
    * Pobrać wszystkich użytkowników. 
    * Dodać użytkownika. 
    * Znaleźć użytkownika po loginie. 
    * Zmienić hasło użytkownika.
    * Usunąć użytkownika.
2. Zaimplementuj metody które pozwolą wykonywać operacje na tabeli postów:
    * Sprawdzić połączenie z bazą (użyj metody isValid()).
    * Pobrać wszystkie posty użytkownika.
    * Pobrać posty z dnia dzisiejszego.
    * Znaleźć post po Id.
    * Wyedytować treść posta.
    * Usunąć post.
