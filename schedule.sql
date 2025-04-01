drop table schedule
create table schedule
(
    id BIGINT auto_increment primary key comment '글 id',
    username varchar(255) not null comment '작성 유저명',
    title varchar(255) not null comment '할 일 제목',
    content varchar(255) not null comment '할 일 내용',
    created_at timestamp not null comment '작성일',
    updated_at timestamp not null comment '수정일'
);