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

Run the whole content of `db/init-db.sql`.  
It creates 3 enum types and 9 tables.

### 2. Set your credentials

Copy `.env.example` into `/src` as `.env

Then fill in your own `DB_USER` and `DB_PASSWORD` in `src/.env`.  
This file is ignored by git: never commit it.

### 3. Run the application

From `src/`:

```
./mvnw spring-boot:run
```

The application starts on port 8080. 
To check it is connected to the database, open http://localhost:8080/actuator/health: it should return `"status":"UP"`.
