create table orders(
    id uuid not null,
    book_id uuid references book(id),
    consumer_id uuid references consumer(id),

    order_time timestamp(4) not null default current_timestamp,
    return_time timestamp(4) default null,
    is_return boolean not null default false,

    primary key (id)
);