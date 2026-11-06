-- V2: fare history. A fare is a price per km for a class and a profile, valid over a period.
-- A price change closes the current fare (valid_to) and inserts a new one: nothing is overwritten.

CREATE EXTENSION IF NOT EXISTS btree_gist;

CREATE TABLE fare (
	fare_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	seat_class seat_class NOT NULL,
	profile traveler_profile NOT NULL,
	-- NULL for legacy fares: before V2 there was no grid, the amount paid is in reservation.price_cents
	price_per_km_cents NUMERIC(6, 2),
	valid_from DATE NOT NULL,
	valid_to DATE,
	CONSTRAINT ck_fare_price CHECK (price_per_km_cents > 0),
	CONSTRAINT ck_fare_period CHECK (valid_to IS NULL OR valid_to > valid_from),
	CONSTRAINT ex_fare_period_overlap EXCLUDE USING gist (
		seat_class WITH =,
		profile WITH =,
		daterange(valid_from, valid_to) WITH &&
	)
);

-- Legacy fares: every sale before the grid
INSERT INTO fare (seat_class, profile, price_per_km_cents, valid_from, valid_to) VALUES
	('FIRST', 'FULL_PRICE', NULL, '2025-01-01', '2027-01-01'),
	('FIRST', 'YOUNG', NULL, '2025-01-01', '2027-01-01'),
	('FIRST', 'SENIOR', NULL, '2025-01-01', '2027-01-01'),
	('FIRST', 'SUBSCRIBER', NULL, '2025-01-01', '2027-01-01'),
	('SECOND', 'FULL_PRICE', NULL, '2025-01-01', '2027-01-01'),
	('SECOND', 'YOUNG', NULL, '2025-01-01', '2027-01-01'),
	('SECOND', 'SENIOR', NULL, '2025-01-01', '2027-01-01'),
	('SECOND', 'SUBSCRIBER', NULL, '2025-01-01', '2027-01-01');

-- New fare grid, for sales from 2027
INSERT INTO fare (seat_class, profile, price_per_km_cents, valid_from) VALUES
	('FIRST', 'FULL_PRICE', 20.00, '2027-01-01'),
	('FIRST', 'YOUNG', 14.00, '2027-01-01'),
	('FIRST', 'SENIOR', 15.00, '2027-01-01'),
	('FIRST', 'SUBSCRIBER', 10.00, '2027-01-01'),
	('SECOND', 'FULL_PRICE', 12.00, '2027-01-01'),
	('SECOND', 'YOUNG', 8.40, '2027-01-01'),
	('SECOND', 'SENIOR', 9.00, '2027-01-01'),
	('SECOND', 'SUBSCRIBER', 6.00, '2027-01-01');

-- Link every existing reservation to the fare in force when it was booked.
ALTER TABLE reservation ADD COLUMN fare UUID;

UPDATE reservation r
SET fare = f.fare_id
FROM seat s, traveler t, fare f
WHERE s.seat_number = r.seat
	AND t.traveler_id = r.traveler
	AND f.seat_class = s.class
	AND f.profile = t.profile
	AND r.reserved_at::date >= f.valid_from
	AND (f.valid_to IS NULL OR r.reserved_at::date < f.valid_to);

-- Constraints only once every row has a value: rollback if a reservation was left without a fare
ALTER TABLE reservation ALTER COLUMN fare SET NOT NULL;
ALTER TABLE reservation ADD CONSTRAINT fk_reservation_fare FOREIGN KEY (fare) REFERENCES fare (fare_id) ON DELETE RESTRICT;
