# Expense Tracker - Hibernate (Eclipse / Maven)

Simple Java/Maven project that demonstrates using Hibernate ORM with an H2 embedded database.

## Quick start (Eclipse)
1. Unzip the project.
2. In Eclipse: **File → Import → Existing Maven Projects** and select the project folder.
3. Let Maven download dependencies.
4. Run `com.expensetracker.App` as a Java application.

## Switch to MySQL (optional)
- Replace H2 settings in `src/main/resources/hibernate.cfg.xml` with your MySQL connection properties.
- Add MySQL connector dependency in `pom.xml`:
```
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.33</version>
</dependency>
```

## Notes
- Uses `hibernate.hbm2ddl.auto = update` for development. Change for production.
- Java 17 is configured in pom.xml — adjust to your environment.

Generated on 2025-11-21T04:53:06.085432 UTC
