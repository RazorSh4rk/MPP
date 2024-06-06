# Checkout book usecase

| User: logged in librarian | System |
| --- | --- |
| clicks checkout button | shows checkout form |
| enters book details, clicks (check) button | validate, shows correct message |
|   | creates checkout record |

```mermaid
sequenceDiagram
    Actor A as Librarian
    Participant MC as MainComponent
    Participant CC as CheckoutComponent
    Participant V as ValidatorComponent
    Participant DB as Database

    activate A
    A->>MC: checkoutBook()
    activate MC
    MC->>CC: setVisible()
    activate CC
    A->>CC: enterDetails()
    A->>CC: createCheckout()
    CC->>V: validate()
    activate V
    V-->CC: return
    deactivate V
    CC->>DB: updateUserDetails()
    activate DB
    deactivate DB
    CC->>DB: updateBookDetails()
    activate DB
    deactivate DB
    CC->>CC: showResult()
    activate CC
    deactivate CC
    CC-->MC: 
    deactivate CC
    deactivate MC
    deactivate A
```