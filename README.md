# Companie de telecomunicatii

## Feature-uri:

### Managementul Clientilor

- se pot adauga clienti in baza de date
- se pot filtra clientii dupa nume sau prenume
- daca se da doar numele atunci doar dupa nume
- daca se da doar prenumele doar dupa prenume
- daca nu se da nimic se afiseaza toti clientii

### Managementul Dispozitivelor in Contracte

- se poate modifica dispozitivele la un contract
- daca contractul nu este gasit atunci se da eroarea "Contract update error, no contract with given id"

### Managementul contractelor

- se poate adauga un nou contract pentru un client si abonament cu o lista de dispozitive
- daca nu exista clientul cerut, abonamentul cerut sau semnatura este invalida (nu exista) atunci se va da o eroare de forma "Contract save error" si motivul erorii

### Managementul abonamentelor

- daca clientul nu exista se va da o eroare
- dat numele unui client se pot vizualiza abonamentele si dispozitivele acestuia se poate crea un abonament nou

### Statistici

- statistici, se pot vedea cele mai populare abonamente cumparate prin contracte

## Entitati:

Client

- id
- firstName
- lastName

Signature

- hash
- date

Contract (Client, SubscriptionPlan)

- date
- billingInterval
- duration
- devices
- digitalSignature
- client
- subscriptionPlan

SubscriptionPlan

- id
- name
- details
- pricePerYear

Device

- id
- name
- type
- warranty

Warranty

- id
- details
- duration
- conditions

