-- Авторизация
create table public.user_auth
(
    id       serial primary key,
    user_id  text unique,
    login    text unique,
    password text
);

-- Профиль
create table public.user_profile
(
    id           serial primary key,
    user_auth_id integer references public.user_auth (id),
    name         text,
    age          integer,
    bio          text,
    date         timestamp
);

-- Лайки
create table public.like
(
    id       serial primary key,
    src_user integer references public.user (id),
    dst_user integer references public.user (id),
    date     timestamp,
    unique (src_user, dst_user)
);

-- Матчі (можна не створювати окремо, а визначати по взаємних лайках)

-- Повідомлення
create table public.message
(
    id          serial primary key,
    chat_id     integer,
    sender_id   integer references public.user (id),
    receiver_id integer references public.user (id),
    text        text,
    date        timestamp
);

-- Чати (опціонально, якщо хочете групувати повідомлення)
create table public.chat
(
    id       serial primary key,
    user1_id integer references public.user (id),
    user2_id integer references public.user (id),
    date     timestamp
);