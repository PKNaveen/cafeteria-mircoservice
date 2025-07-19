create table products (

    id uuid default gen_random_uuid() unique,
    product_code text not null unique ,
    product_name text not null unique,
    product_category text not null,
    product_price text not null,
    product_rating text,
    product_description text not null,
    product_imageURL text not null,
    primary key (id)

);
