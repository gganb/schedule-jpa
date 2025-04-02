
create table schedule
(
    id BIGINT auto_increment primary key comment '글 id',
    userid bigint not null comment '유저 고유 식별자',
    title varchar(255) not null comment '할 일 제목',
    content varchar(255) not null comment '할 일 내용',
    created_at timestamp not null comment '작성일',
    updated_at timestamp not null comment '수정일',
    foreign key (userid) references user (id)
);


create table user(
    id BIGINT auto_increment primary key comment '유저 id',
    username varchar(255) not null comment '유저명',
    password varchar(255) not null comment '비밀번호',
    email varchar(255) not null comment '이메일',
    created_at timestamp not null comment '작성일',
    updated_at timestamp not null comment '수정일'

)