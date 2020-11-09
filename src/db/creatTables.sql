create table drivers
(
    id                       SERIAL PRIMARY KEY,
    name                     varchar(120) NOT NULL,
    driverLicenceNum         varchar(120) NOT NULL,
    category                 varchar(120) NOT NULL,
    dlvalidity               date         not null,
    medicalCertificateNumber varchar(120) NOT NULL,
    mcvalidaty               date         not null
);

create table trucks
(
    id                          SERIAL PRIMARY KEY,
    model                       varchar(120) NOT NULL,
    type                        varchar(120) NOT NULL,
    yearOfProduction            date         not null,
    registerSign                varchar(120) NOT NULL,
    insuranceNumber             varchar(120) NOT NULL,
    insuranceValidity           date         not null,
    technicalInspectionValidity date         not null
);

insert into drivers (name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty)
values ('Nick Nickys', '123456', 'ABC', '2030-05-10', '123456FA', '2021-02-02'),
       ('Ron Ronies', '789456', 'BCE', '2021-01-04', '115456FA', '2022-04-02'),
       ('Mark Markov', '456123', 'BCDE', '2025-08-09', '11486FA', '2024-02-02');

insert into trucks (model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity)
values ('MAZ 6501A8', 'tipper', '2016-01-01', 'AM 1224-1', 'AL145689', '2021-12-01', '2021-12-12'),
       ('MAZ 6501A8', 'tipper', '2019-01-01', 'AM 1224-1', 'AL145689', '2020-12-01', '2020-12-12'),
       ('MAZ 551605', 'tipper', '2018-01-01', 'KN 9987-1', 'OL895364', '2021-01-01', '2020-01-12');
