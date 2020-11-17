create table attaque
(
    id    integer
        constraint attaque_pk
            primary key autoincrement,
    nom   VARCHAR(25) not null,
    force integer     not null
);

create table dresseur
(
    id     integer
        constraint dresseur_pk
            primary key autoincrement,
    nom    varchar(25),
    prenom varchar(25)
);

create table pokemon
(
    id          integer
        constraint pokemon_pk
            primary key autoincrement,
    identifiant integer,
    nom         varchar(25),
    poids       float,
    taille      float,
    design      varchar(100),
    attaque01   integer
        references attaque,
    attaque02   integer
        references attaque,
    dresseur    integer
        references dresseur
);