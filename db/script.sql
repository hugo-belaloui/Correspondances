-- Generation en cascade, d'abord les stations et line puis le tracé avec line_stop





INSERT INTO station (name) values ('Marseille-Saint-Charles'), ('Aix-en-Provence Centre'), ('Aix-en-Provence TGV'), ('Aubagne'), ('La Ciotat'), ('Toulon'), ('Hyeres'), ('Les Arcs Draguignan'),('Saint-Raphael Valescure'), ('Cannes'), ('Antibes'), ('Nice-Ville'), ('Monaco-Monte-Carlo'), ('Menton'), ('Ventimiglia'), ('Avignon Centre'), ('Avignon TGV'), ('Arles'), ('Miramas'), ('Salon-de-Provence'), ('Cavaillon'), ('Orange'), ('Carpentras'), ('Pertuis'), ('Manosque-Greoux'), ('Sisteron'), ('Gap'), ('Briancon'), ('Digne-les-Bains'), ('Grasse');

INSERT INTO line (name) values ('L01: Marseille - Nice'), ('L02: Nice - Ventimiglia'), ('L03: Marseille - Avignon via Arles'), ('L04: Marseille - Avignon via Cavaillon'), ('L05: Marseille - Aix-en-Provence - Pertuis'), ('L06: Marseille - Toulon - Hyeres'), ('L07: Cannes - Grasse'), ('L08: Avignon - Carpentras'), ('L09: Marseille - Briancon via Gap'), ('L10: Avignon - Orange'), ('L11: Miramas - Marseille via Côte Bleue'), ('L12: Toulon - Les Arcs Draguignan');



