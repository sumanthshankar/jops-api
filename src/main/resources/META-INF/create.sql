
    create table authors (
       author_id integer not null,
        author_emailid varchar(255),
        author_name varchar(255),
        primary key (author_id)
    ) engine=MyISAM;

    create table authors_books (
       author_id integer not null,
        book_id integer not null
    ) engine=MyISAM;

    create table books (
       book_id integer not null,
        book_description varchar(255),
        book_title varchar(255),
        primary key (book_id)
    ) engine=MyISAM;

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table roles (
       role_id integer not null,
        role varchar(255),
        primary key (role_id)
    ) engine=MyISAM;

    create table user_role (
       user_id integer not null,
        role_id integer not null,
        primary key (user_id, role_id)
    ) engine=MyISAM;

    create table users (
       user_id integer not null,
        active bit not null,
        email_id varchar(255) not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (user_id)
    ) engine=MyISAM;

    alter table user_role 
       add constraint UK_it77eq964jhfqtu54081ebtio unique (role_id);

    alter table users 
       add constraint UK_pwrpg821nujmmnavoq7s420jn unique (email_id);

    alter table authors_books 
       add constraint FKhljw0x9o8s80uce55w3iqhlqk 
       foreign key (book_id) 
       references books (book_id);

    alter table authors_books 
       add constraint FK1qmg0jp4gtkls4bn52xhg4jnf 
       foreign key (author_id) 
       references authors (author_id);

    alter table user_role 
       add constraint FKt7e7djp752sqn6w22i6ocqy6q 
       foreign key (role_id) 
       references roles (role_id);

    alter table user_role 
       add constraint FKj345gk1bovqvfame88rcx7yyx 
       foreign key (user_id) 
       references users (user_id);
