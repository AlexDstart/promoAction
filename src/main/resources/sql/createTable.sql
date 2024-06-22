create table Prize(
    id serial primary key ,
    name_winner varchar(50),
    email_winner varchar(50),
    number_winner varchar(50),
    status_winner boolean default false,
    promo_cod varchar(50) not null ,
    name_prize varchar(50) not null ,
    image_path varchar(255)
    );