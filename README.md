# Correspondances

## Dependencies and quick start

### Dependencies

- Java 25 or later
- PostgreSQL 16 or later

### 1. Create the database

In your SQL CLI or UI, connected to the default `postgres` database, run:

```sql
CREATE DATABASE correspondances;
```

The tables are created by Flyway when the application starts.

### 2. Set your credentials

Copy `.env.example` into `/src` as `.env`

Then fill in your own `DB_USER` and `DB_PASSWORD` in `src/.env`.  
This file is ignored by git: never commit it.

### 3. Run the application

From `src/`:

```
./mvnw spring-boot:run
```

The application starts on port 8080. 
To check it is connected to the database, open http://localhost:8080/actuator/health: it should return `"status":"UP"`.

### 4. Endpoints

Swagger endpoints : http://localhost:8080/swagger-ui/index.html

JSON OpenAPI spec : http://localhost:8080/v3/api-docs

| Method | URL | Description |
|---|---|---|
| GET | `/stations` | All stations, sorted by name |
| GET | `/stations/{stationId}/lines` | Lines serving a station |
| GET | `/lines/{lineNumber}/stops` | Stops of a line, in order |
| GET | `/journeys?lineNumber={lineNumber}&date={yyyy-MM-dd}` | Journeys of a line running on a date |
| GET | `/travelers/{travelerId}/reservations` | Reservations of a traveler, latest first |
| GET | `/fares?date={yyyy-MM-dd}` | Fares valid on a date (since V2) |

## Data generator

```
psql -h localhost -U postgres -d correspondances -f db/script.sql
```

It ends with `INSERT 0 500000` and `COMMIT`.

## Database migrations

The schema is versioned with Flyway. Migrations live in `src/src/main/resources/db/migration/` and run automatically when the application starts. Applied versions are recorded in the `flyway_schema_history` table.

### V1: initial schema (baseline)

On your machine, just start the application:
- **Empty database**: Flyway runs V1 and creates the 3 enum types and 9 tables.
- **Database already created with `init-db.sql`**: Flyway does not run V1, it marks the database as version 1 (baseline). Your data is kept.

To check, run `SELECT version, description, type FROM flyway_schema_history;`: you should get version `1`, either `init schema` (type `SQL`) or `<< Flyway Baseline >>` (type `BASELINE`).

Never edit a migration that has been applied: Flyway checks their checksum and refuses to start. Fix with a new version instead.

### V2: fare history

- New `fare` table: a price per km for a class and a profile, valid from `valid_from` to `valid_to` (`NULL` = current).
- Legacy fares cover every sale before 2027, when no fare grid existed: their price is `NULL`, the amount paid stays in `reservation.price_cents`. The new fare grid starts on 2027-01-01.
- New `reservation.fare` column: the fare applied when the reservation was booked. `price_cents` is unchanged.

Start the application: Flyway applies V2 automatically. `flyway_schema_history` should then show version `2`.

To check in Swagger: `GET /fares?date=2026-03-03` returns the 8 legacy fares (no price), `GET /fares?date=2027-03-03` returns the 8 fares of the new grid. Before V2, this route does not exist.