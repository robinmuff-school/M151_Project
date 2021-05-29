CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE public.meeting_user
(
    id         bigint                                              NOT NULL,
    firstname  character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname   character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password   character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_group character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username   character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT meeting_user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_91xx7nud7a1au9udbacyla3xj UNIQUE (username)
);

CREATE SEQUENCE user_sequence OWNED BY public.meeting_user.id;

CREATE TABLE public.meeting_room
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT meeting_room_pkey PRIMARY KEY (id),
    CONSTRAINT uk_bikn922oqql7b2am2fvsessoy UNIQUE (name)
);

CREATE SEQUENCE meetingroom_sequence OWNED BY public.meeting_room.id;

CREATE TABLE public.meeting
(
    id bigint NOT NULL,
    end_time timestamp without time zone NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    start_time timestamp without time zone NOT NULL,
    meeting_room_id bigint,
    CONSTRAINT meeting_pkey PRIMARY KEY (id),
    CONSTRAINT uk_tmtmoj1wqdj7nre52w9isocui UNIQUE (start_time),
    CONSTRAINT fkryit2txdgae5ii4ohq6h2v1tw FOREIGN KEY (meeting_room_id)
        REFERENCES public.meeting_room (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE meeting_sequence OWNED BY public.meeting.id;

CREATE TABLE public.invites
(
    id bigint NOT NULL,
    status integer NOT NULL,
    invited_id bigint,
    inviter_id bigint,
    meeting_id bigint,
    CONSTRAINT invites_pkey PRIMARY KEY (id),
    CONSTRAINT uk_l2qqog8aofb9sldr6iq1qlkaq UNIQUE (status),
    CONSTRAINT fk61quqphh4yo3ylp11da0huu84 FOREIGN KEY (inviter_id)
        REFERENCES public.meeting_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkgdhsfqcba3y7953vyg1g33wfn FOREIGN KEY (invited_id)
        REFERENCES public.meeting_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fknvtfmr8vsxle0g9ahhbk90lcg FOREIGN KEY (meeting_id)
        REFERENCES public.meeting (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE invites_sequence OWNED BY public.invites.id;

INSERT INTO public.meeting_user (id, firstname, lastname, password, user_group, username)
VALUES (1, 'Robin', 'Muff', crypt('secure', gen_salt('bf', 8)), 'Admin', 'robin');