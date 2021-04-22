## Projektbeschrieb

### Thema:
Ich habe mich entschieden ein Meetingorganisationtool zu entwickeln. In der Applikation soll ein Meeting organisiert werden können bei welchem Mitarbeiter eingeladen werden können sowie ein oder mehrere Räume verwendet werden können. Dabei kann ein Benutzer ein Meeting erstellen und Räume sowie Mitarbeiter hinzufügen. Ein Admin kann Räume und Mitarbeiter hinzufügen, löschen sowie bearbeiten. Als Meetingraum kann auf alle Meetings die in diesem Raum stattfinden zugegriffen werden, jedoch nicht editiert (Read Zugriff). 

### Personengruppen:

| Gruppe | Beschreibung | Bild |
| -----|:-------------:| ----- |
| Administrator | Als Administrator kann jeder Meetingraum sowie jeder Mitarbeiter verwaltet werden | ![](https://thispersondoesnotexist.com/image) |
| Mitarbeiter | Als Mitarbeiter kann ein Meeting erstellt werden, Benutzer können eingeladen werden sowie ein oder mehrere Räume | ![](https://thispersondoesnotexist.com/image) |
| Meetingraum | Als Meetingraum kann auf jedes Meeting in diesem Raum zugegriffen werden um Daten anzuzeigen | ![](https://www.schwadke.de/wp-content/uploads/2018/11/AdobeStock_36401035-2560x1280.jpeg) |

### User Stories:

| ID | User-Story  |
| -----|:-------------:|
| 01 | Als Mitarbeiter möchte ich alle Meetings sehen bei denen ich eingeladen bin oder erstellt habe. |
| 02 | Als Mitarbeiter möchte ich alle Meetingräume sowie Personen anzeigen können damit ich diese ablesen kann |
| 03 | Als Mitarbeiter möchte ich Details zu einem bestimmten Meeting sehen. |
| 04 | Als Mitarbeiter möchte ich eine Einladung zu einem Meeting annehmen oder ablehnen können. |
| 05 | Als Mitarbeiter möchte ich eine neue Einladung erstellen können und Meetingräume sowie Personen einladen. |
| 06 | Als Meetingraum möchte ich alle Meetings in meinem Raum sehen. |
| 07 | Als Meetingraum möchte ich Details zu einem bestimmten Meeting sehen. |
| 08 | Als Administrator möchte ich alle Meetingräume sowie Personen anzeigen können. |
| 09 | Als Administrator möchte ich alle Meetingräume sowie Personen bearbeiten um falsche Daten zu korrigieren. |
| 10 | Als Administrator möchte ich neue Meetingräume sowie Personen erfassen können um mehr Räume zur verfügung zu stellen. |

### Technologie:

Programmiersprache: [Java](https://www.java.com/)
Datenbankanbindung: [Spring](https://spring.io)
Datenbank: [Postrgres](https://www.postgresql.org/)
Cach System: [Redis](https://redis.io)