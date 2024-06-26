create table users(
	id serial4 primary key, -- auto incrementing four-byte integer
	fullname varchar(100) default '',
	phone_number varchar(10) not null,
	address varchar(200) default '',
	"password" varchar(100) not null default '',
	created_at timestamp,
	updated_at timestamp,
	is_active boolean default true,
	date_of_birth timestamp,
	facebook_account_id int default 0,
	google_account_id int default 0
);

create table roles(
	id int primary key,
	name varchar(20) not null
);
select * from users;
alter table users add column role_id int;
alter table users add foreign key (role_id) references roles(id);

create table tokens(
	id serial4 primary key,
	"token" varchar(255) unique not null,
	token_type varchar(50) not null, -- bear/jwt
	expires_in timestamp,
	revoked boolean,
	expired boolean,
	user_id int,
	foreign key (user_id) references users(id)
);
-- Hỗ trợ đăng nhập bằng facebook và google
create table social_accounts(
	id serial4 primary key,
	provider varchar(20) not null,
	provider_id varchar(50) not null, -- when login success
	email varchar(150) not null,
	"name" varchar(100) not null,
	user_id int,
	foreign key (user_id) references users(id)
);
comment on column social_accounts.provider is 'Name of social network';
comment on column social_accounts.email is 'Email this account';
comment on column social_accounts.name is 'Name of user';

-- Danh mục sản phẩm: Đồ ăn, mĩ phẩm, đồ điện tử...
create table categories(
	id serial4 primary key,
	"name" varchar(100) not null default ''
);


-- Bảng chứa sản phẩm: Ipad air 2020, Ipad pro 2020

create table products(
	id serial4 primary key,
	"name" varchar(350),
	price float not null check(price >= 0),
	thumbnail varchar(300) default '',
	description text default '',
	created_at timestamp,
	updated_at timestamp,
	category_id int,
	foreign key (category_id) references categories(id)
);

-- Bảng ảnh sản phẩm

create table product_images(
    id serial4 primary key,
    product_id int,
    constraint fk_product_image_product_id
    foreign key (product_id) references products(id) on delete cascade,
    image_url varchar(300) default ''
);

-- Bảng đặt hàng

create table orders(
	id serial4 primary key,
	user_id int,
	fullname varchar(100) default '',
	email varchar(100) default '',
	phone_number varchar(20) not null,
	address varchar(200) not null, -- Địa chỉ nơi gửi
	note varchar(100) default '',
	order_date timestamp default current_timestamp,
	status varchar(20) check(status in ('pending','processing', 'shipped', 'delivered','cancelled')),
	total_money float check(total_money > 0),
	shipping_method varchar(100),   -- Phương thức vận chuyển
	shipping_address varchar(200),  -- Địa chỉ ship đến
	shipping_date timestamp,        -- Ngày gửi đến
	tracking_number varchar(100),   -- Số vận đơn
	payment_method varchar(100),     -- Phương thức thanh toán
	active boolean not null default true,
	foreign key (user_id) references users(id)
);

-- Bảng chi tiết đơn đặt hàng
create table order_details(
	product_id int,
	foreign key (product_id) references products(id),
	order_id int,
	foreign key (order_id) references orders(id),
	price float check(price >= 0),
	number_of_product int check(number_of_product > 0),
	total_money float check(total_money >= 0),
	color varchar(20) default '',
	size varchar(10) check(size in ('S','M','L'))
);
