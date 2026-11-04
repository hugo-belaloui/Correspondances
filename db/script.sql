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

INSERT INTO line_stop (line, station, position_on_line, _distance_from_next)
SELECT 
    l.line_number, --line_id
    s.station_id,
    a.position,
    a.distance,
FROM all_stops a
JOIN line l ON l.name = a.line_name
JOIN station s ON s.name = a.station_name;



