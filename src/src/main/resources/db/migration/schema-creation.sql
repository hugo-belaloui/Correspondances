-- V1: initial schema, matches the MPD in docs/MERISE.svg

CREATE TYPE seat_class AS ENUM ('FIRST', 'SECOND');
CREATE TYPE traveler_profile AS ENUM ('FULL_PRICE', 'YOUNG', 'SENIOR', 'SUBSCRIBER');
CREATE TYPE journey_schedule AS ENUM ('EVERY_DAY', 'WEEKDAYS', 'WEEKEND', 'SPECIAL_DAYS');

CREATE TABLE station (
	station_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(63) NOT NULL,
	CONSTRAINT uq_station_name UNIQUE (name)
);

CREATE TABLE line (
	line_number UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(63) NOT NULL,
	CONSTRAINT uq_line_name UNIQUE (name)
);

CREATE TABLE line_stop (
	line_stop_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	line UUID NOT NULL,
	station UUID NOT NULL,
	position_on_line SMALLINT NOT NULL,
	distance_from_next DECIMAL(7, 3),
	CONSTRAINT fk_line_stop_line FOREIGN KEY (line) REFERENCES line (line_number) ON DELETE CASCADE,
	CONSTRAINT fk_line_stop_station FOREIGN KEY (station) REFERENCES station (station_id) ON DELETE RESTRICT,
	CONSTRAINT uq_line_stop_position UNIQUE (line, position_on_line),
	CONSTRAINT uq_line_stop_station UNIQUE (line, station),
	CONSTRAINT ck_line_stop_position CHECK (position_on_line > 0),
	CONSTRAINT ck_line_stop_distance CHECK (distance_from_next > 0)
);

CREATE TABLE train (
	train_number UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	avg_speed_kmh DECIMAL(5, 2) NOT NULL,
	CONSTRAINT ck_train_speed CHECK (avg_speed_kmh > 0)
);


CREATE TABLE wagon (
	wagon_number UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	train UUID NOT NULL,
	CONSTRAINT fk_wagon_train FOREIGN KEY (train) REFERENCES train (train_number) ON DELETE CASCADE
);

CREATE TABLE seat (
	seat_number UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	wagon UUID NOT NULL,
	class seat_class NOT NULL,
	CONSTRAINT fk_seat_wagon FOREIGN KEY (wagon) REFERENCES wagon (wagon_number) ON DELETE CASCADE
);

CREATE TABLE journey (
	journey_number UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	train UUID NOT NULL,
	line UUID NOT NULL,
	departure_time TIMESTAMP NOT NULL,
	stop_time_minutes SMALLINT NOT NULL,
	default_direction BOOLEAN NOT NULL DEFAULT TRUE,
	is_direct BOOLEAN NOT NULL DEFAULT FALSE,
	schedule journey_schedule NOT NULL,
	CONSTRAINT fk_journey_train FOREIGN KEY (train) REFERENCES train (train_number) ON DELETE RESTRICT,
	CONSTRAINT fk_journey_line FOREIGN KEY (line) REFERENCES line (line_number) ON DELETE RESTRICT,
	CONSTRAINT ck_journey_stop_time CHECK (stop_time_minutes >= 0)
);

CREATE TABLE traveler (
	traveler_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	profile traveler_profile NOT NULL DEFAULT 'FULL_PRICE',
	first_name VARCHAR(127) NOT NULL,
	last_name VARCHAR(127) NOT NULL,
	email VARCHAR(255) NOT NULL,
	CONSTRAINT uq_traveler_email UNIQUE (email)
);

CREATE TABLE reservation (
	reservation_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	journey UUID NOT NULL,
	traveler UUID NOT NULL,
	price_cents INTEGER NOT NULL,
	departure_station UUID NOT NULL,
	arrival_station UUID NOT NULL,
	reserved_at TIMESTAMP NOT NULL DEFAULT now(),
	travel_time_minutes INTEGER NOT NULL,
	seat UUID NOT NULL,
	departure_datetime TIMESTAMP NOT NULL,
	CONSTRAINT fk_reservation_journey FOREIGN KEY (journey) REFERENCES journey (journey_number) ON DELETE RESTRICT,
	CONSTRAINT fk_reservation_traveler FOREIGN KEY (traveler) REFERENCES traveler (traveler_id) ON DELETE RESTRICT,
	CONSTRAINT fk_reservation_departure FOREIGN KEY (departure_station) REFERENCES station (station_id) ON DELETE RESTRICT,
	CONSTRAINT fk_reservation_arrival FOREIGN KEY (arrival_station) REFERENCES station (station_id) ON DELETE RESTRICT,
	CONSTRAINT fk_reservation_seat FOREIGN KEY (seat) REFERENCES seat (seat_number) ON DELETE RESTRICT,
	CONSTRAINT uq_reservation_seat_segment UNIQUE (seat, journey, departure_station, arrival_station, departure_datetime),
	CONSTRAINT ck_reservation_price CHECK (price_cents >= 0),
	CONSTRAINT ck_reservation_stations CHECK (departure_station <> arrival_station),
	CONSTRAINT ck_reservation_travel_time CHECK (travel_time_minutes > 0),
	CONSTRAINT ck_reservation_booked_before CHECK (reserved_at <= departure_datetime)
);
