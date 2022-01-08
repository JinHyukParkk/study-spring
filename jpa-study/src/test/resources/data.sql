-- test 때 sequnece 가 증가하기 때문에 call next value for hibernate_sequence 를 해줌
-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'hyuk', 'hyuk@gmail.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'chul', 'chul@gmail.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'suk', 'suk@naver.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'gun', 'gun@gmail.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'gun', 'guni123@naver.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (6, 'hyuk', 'hyuk0819@naver.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (7, 'gun', 'gunee@naver.com', now(), now());

insert into publisher (`id`, `name`, `created_at`, `updated_at`) values (1, '패캠', now(), now());

insert into book (`id`, `name`, `publisher_id`, `deleted`, `created_at`, `updated_at`, `status`) values (1, 'Spring 학습', 1, false, now(), now(), 100);

-- Entity의 @Column columnDefinition으로 defalut 설정
insert into book (`id`, `name`, `publisher_id`, `deleted`, `status`) values (2, 'JPA 학습', 1, false, 100);

insert into book (`id`, `name`, `publisher_id`, `deleted`, `status`) values (3, 'Persistence 학습', 1, true, 200);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) value (1, '인생책', 'good', 5.0, 1, 1);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) value (2, '개망 책', '별로', 2.0, 2, 2);



