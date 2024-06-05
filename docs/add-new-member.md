# Add a new member usecase

| User: logged in administrator | System |
| --- | --- |
| clicks add new member button | displays the form for the member detail fields |
| fills out the details | validates the details, saves them to database, shows results |

```mermaid
sequenceDiagram
    Actor A as Admin
    Participant MC as MainComponent
    Participant AC as AddComponent
    Participant DB as Database

    activate A
    A->>MC: addNew()
    activate MC
    MC->>AC: setVisible()
    activate AC
    A->>AC: enter details
    A->>AC: submit()
    AC->>AC: validate()
    activate AC
    deactivate AC
    AC->>DB: save()
    activate DB
    deactivate DB
    AC->>AC: showResult()
    activate AC
    deactivate AC
    AC-->AC: message
    deactivate AC
    deactivate MC
    deactivate A
```