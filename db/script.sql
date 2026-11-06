-- Generation en cascade, d'abord les stations et line puis le tracé avec line_stop





INSERT INTO station (name) values ('Marseille-Saint-Charles'), ('Aix-en-Provence Centre'), ('Aix-en-Provence TGV'), ('Aubagne'), ('La Ciotat'), ('Toulon'), ('Hyeres'), ('Les Arcs Draguignan'),('Saint-Raphael Valescure'), ('Cannes'), ('Antibes'), ('Nice-Ville'), ('Monaco-Monte-Carlo'), ('Menton'), ('Ventimiglia'), ('Avignon Centre'), ('Avignon TGV'), ('Arles'), ('Miramas'), ('Salon-de-Provence'), ('Cavaillon'), ('Orange'), ('Carpentras'), ('Pertuis'), ('Manosque-Greoux'), ('Sisteron'), ('Gap'), ('Briancon'), ('Digne-les-Bains'), ('Grasse');

INSERT INTO line (name) values ('L01: Marseille - Nice'), ('L02: Nice - Ventimiglia'), ('L03: Marseille - Avignon via Arles'), ('L04: Marseille - Avignon via Cavaillon'), ('L05: Marseille - Aix-en-Provence - Pertuis'), ('L06: Marseille - Toulon - Hyeres'), ('L07: Cannes - Grasse'), ('L08: Avignon - Carpentras'), ('L09: Marseille - Briancon via Gap'), ('L10: Avignon - Orange'), ('L11: Miramas - Marseille via Côte Bleue'), ('L12: Toulon - Les Arcs Draguignan');

-- ligne ex Marseille - aix - pertuis : marseille 1, aix 2, pertuis NULL
-- virtual table declaration CTE tabeau temporaire 
WITH all_stops (line_name, station_name, position, distance) AS 
(VALUES
--L1
        ('L01: Marseille - Nice', 'Marseille-Saint-Charles', 1, 67.0),
        ('L01: Marseille - Nice', 'Toulon',                  2, 69.0),
        ('L01: Marseille - Nice', 'Les Arcs Draguignan',    3, 55.0),
        ('L01: Marseille - Nice', 'Saint-Raphael Valescure', 4, 33.0),
        ('L01: Marseille - Nice', 'Cannes',                  5, 11.0),
        ('L01: Marseille - Nice', 'Antibes',                 6, 20.0),
        ('L01: Marseille - Nice', 'Nice-Ville',              7, NULL),
--L2
        ('L02: Nice - Ventimiglia', 'Nice-Ville',          1, 16.0),
        ('L02: Nice - Ventimiglia', 'Monaco-Monte-Carlo', 2, 9.0),
        ('L02: Nice - Ventimiglia', 'Menton',             3, 11.0),
        ('L02: Nice - Ventimiglia', 'Ventimiglia',        4, NULL),
--L3
        ('L03: Marseille - Avignon via Arles', 'Marseille-Saint-Charles', 1, 51.0),
        ('L03: Marseille - Avignon via Arles', 'Miramas',                  2, 39.0),
        ('L03: Marseille - Avignon via Arles', 'Arles',                    3, 35.0),
        ('L03: Marseille - Avignon via Arles', 'Avignon Centre',           4, NULL),
--L4
        ('L04: Marseille - Avignon via Cavaillon', 'Marseille-Saint-Charles', 1, 48.0),
        ('L04: Marseille - Avignon via Cavaillon', 'Salon-de-Provence',       2, 32.0),
        ('L04: Marseille - Avignon via Cavaillon', 'Cavaillon',               3, 27.0),
        ('L04: Marseille - Avignon via Cavaillon', 'Avignon Centre',          4, NULL),

        ('L05: Marseille - Aix-en-Provence - Pertuis', 'Marseille-Saint-Charles', 1, 33.0),
        ('L05: Marseille - Aix-en-Provence - Pertuis', 'Aix-en-Provence Centre',  2, 24.0),
        ('L05: Marseille - Aix-en-Provence - Pertuis', 'Pertuis',                 3, NULL),
--L6
        ('L06: Marseille - Toulon - Hyeres', 'Marseille-Saint-Charles', 1, 17.0),
        ('L06: Marseille - Toulon - Hyeres', 'Aubagne',                 2, 22.0),
        ('L06: Marseille - Toulon - Hyeres', 'La Ciotat',               3, 38.0),
        ('L06: Marseille - Toulon - Hyeres', 'Toulon',                  4, 21.0),
        ('L06: Marseille - Toulon - Hyeres', 'Hyeres',                  5, NULL),
--L7
        ('L07: Cannes - Grasse', 'Cannes', 1, 19.5),
        ('L07: Cannes - Grasse', 'Grasse', 2, NULL),

--L8
        ('L08: Avignon - Carpentras', 'Avignon Centre', 1, 26.0),
        ('L08: Avignon - Carpentras', 'Carpentras',     2, NULL),

--L9
        ('L09: Marseille - Briancon via Gap', 'Marseille-Saint-Charles', 1, 33.0),
        ('L09: Marseille - Briancon via Gap', 'Aix-en-Provence Centre',  2, 55.0),
        ('L09: Marseille - Briancon via Gap', 'Manosque-Greoux',         3, 49.0),
        ('L09: Marseille - Briancon via Gap', 'Sisteron',                4, 48.0),
        ('L09: Marseille - Briancon via Gap', 'Gap',                     5, 87.0),
        ('L09: Marseille - Briancon via Gap', 'Briancon',                6, NULL),

--L10 
        ('L10: Avignon - Orange', 'Avignon Centre', 1, 28.0),
        ('L10: Avignon - Orange', 'Orange',         2, NULL),

--L11
        ('L11: Miramas - Marseille via Côte Bleue', 'Miramas',                  1, 60.0),
        ('L11: Miramas - Marseille via Côte Bleue', 'Marseille-Saint-Charles', 2, NULL),

--L12
        ('L12: Toulon - Les Arcs Draguignan', 'Toulon',               1, 69.0),
        ('L12: Toulon - Les Arcs Draguignan', 'Les Arcs Draguignan', 2, NULL)
)
INSERT INTO line_stop (line, station, position_on_line, _distance_from_next) SELECT 
    l.line_number, --line_id
    s.station_id,
    a.position,
    a.distance,
FROM all_stops a JOIN line l ON l.name = a.line_name JOIN station s ON s.name = a.station_name;
--always read FROM first

--trains 
INSERT into train (avg_speed_kmh) SELECT 140.00 + ((v % 5)*10.00) FROM generate_series(1,20) as v;

--wagon
INSERT INTO wagon (train) SELECT t.train_number FROM train AS t CROSS JOIN generate_series(1,4) AS w;
-- should randomize later

--seat 

WITH numbered_wagons AS (SELECT w.wagon_number, ROW_NUMBER() OVER (PARTITION BY w.train) AS wagon_position FROM wagon as w) INSERT INTO seat (wagon, class) SELECT nw.wagon_number, CASE WHEN nw.wagon_position = 1 then 'FIRST'::seat_class ELSE 'SECOND'::seat_class END FROM numbered_wagons as nw CROSS JOIN generate_series(1, 40) as s;

--journey
WITH train_pool AS ( SELECT train_number, ROW() OVER() AS t_num FROM train) , line_pool AS (SELECT line_number, name FROM line) INSERT INTO journey (train, line, departure_time, stop_time_minutes, default_direction,is_direct, schedule) VALUES 
--L1
((SELECT train_number FROM train_pool WHERE t_num = 1),  (SELECT line_number FROM line_pool WHERE name = 'L01: Marseille - Nice'), '2026-01-01 06:30:00', 3, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 2),  (SELECT line_number FROM line_pool WHERE name = 'L01: Marseille - Nice'), '2026-01-01 08:30:00', 3, TRUE, FALSE, 'EVERY_DAY'),
    ((SELECT train_number FROM train_pool WHERE t_num = 3),  (SELECT line_number FROM line_pool WHERE name = 'L01: Marseille - Nice'), '2026-01-01 12:30:00', 3, TRUE, FALSE, 'EVERY_DAY'),
    ((SELECT train_number FROM train_pool WHERE t_num = 4),  (SELECT line_number FROM line_pool WHERE name = 'L01: Marseille - Nice'), '2026-01-01 17:30:00', 3, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 5),  (SELECT line_number FROM line_pool WHERE name = 'L02: Nice - Ventimiglia'), '2026-01-01 07:15:00', 2, TRUE, FALSE, 'EVERY_DAY'),
    ((SELECT train_number FROM train_pool WHERE t_num = 6),  (SELECT line_number FROM line_pool WHERE name = 'L02: Nice - Ventimiglia'), '2026-01-01 18:15:00', 2, TRUE, FALSE, 'EVERY_DAY'),
    ((SELECT train_number FROM train_pool WHERE t_num = 7),  (SELECT line_number FROM line_pool WHERE name = 'L03: Marseille - Avignon via Arles'), '2026-01-01 07:00:00', 3, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 8),  (SELECT line_number FROM line_pool WHERE name = 'L03: Marseille - Avignon via Arles'), '2026-01-01 17:00:00', 3, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 9),  (SELECT line_number FROM line_pool WHERE name = 'L04: Marseille - Avignon via Cavaillon'), '2026-01-01 08:00:00', 2, TRUE, FALSE, 'WEEKEND'),
    ((SELECT train_number FROM train_pool WHERE t_num = 10), (SELECT line_number FROM line_pool WHERE name = 'L05: Marseille - Aix-en-Provence - Pertuis'), '2026-01-01 07:45:00', 2, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 11), (SELECT line_number FROM line_pool WHERE name = 'L05: Marseille - Aix-en-Provence - Pertuis'), '2026-01-01 18:00:00', 2, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 12), (SELECT line_number FROM line_pool WHERE name = 'L06: Marseille - Toulon - Hyeres'), '2026-01-01 06:45:00', 2, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 13), (SELECT line_number FROM line_pool WHERE name = 'L06: Marseille - Toulon - Hyeres'), '2026-01-01 16:45:00', 2, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 14), (SELECT line_number FROM line_pool WHERE name = 'L07: Cannes - Grasse'), '2026-01-01 08:10:00', 2, TRUE, FALSE, 'EVERY_DAY'),
    ((SELECT train_number FROM train_pool WHERE t_num = 15), (SELECT line_number FROM line_pool WHERE name = 'L08: Avignon - Carpentras'), '2026-01-01 07:30:00', 2, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 16), (SELECT line_number FROM line_pool WHERE name = 'L09: Marseille - Briancon via Gap'), '2026-01-01 06:10:00', 4, TRUE, FALSE, 'EVERY_DAY'),
    ((SELECT train_number FROM train_pool WHERE t_num = 17), (SELECT line_number FROM line_pool WHERE name = 'L09: Marseille - Briancon via Gap'), '2026-01-01 14:10:00', 4, TRUE, FALSE, 'WEEKEND'),
    ((SELECT train_number FROM train_pool WHERE t_num = 18), (SELECT line_number FROM line_pool WHERE name = 'L10: Avignon - Orange'), '2026-01-01 09:00:00', 2, TRUE, FALSE, 'WEEKDAYS'),
    ((SELECT train_number FROM train_pool WHERE t_num = 19), (SELECT line_number FROM line_pool WHERE name = 'L11: Miramas - Marseille via Côte Bleue'), '2026-01-01 10:15:00', 3, TRUE, FALSE, 'WEEKEND'),
    ((SELECT train_number FROM train_pool WHERE t_num = 20), (SELECT line_number FROM line_pool WHERE name = 'L12: Toulon - Les Arcs Draguignan'), '2026-01-01 11:30:00', 2, TRUE, FALSE, 'EVERY_DAY');

--traveler 

INSERT INTO traveler (profile, first_name, last_name, email) SELECT CASE (g * % 4) WHEN 0 THEN 'FULL_PRICE'::traveler_profile WHEN 1 THEN 'YOUNG'::traveler_profile WHEN 2 THEN 'SENIOR'::traveler_profile ELSE 'SUBSCRIBER'::traveler_profile END, 'prenom' || g, 'nom' || g, 'traveler' || g || 'jondoe@mail.com' FROM generate_series(1, 5000) AS g;




