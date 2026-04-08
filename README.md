# SprintTracker

A terminal-based athletic performance logging application built in Java. SprintTracker connects to a SQLite database and allows a user to record sprint session data in real time, those being: date, event, time, and notes, through an interactive command-line interface.

---

## The Problem It Solves

Tracking athletic performance over time is straightforward in concept and surprisingly inconsistent in practice. Spreadsheets get abandoned. Notes apps lack structure. SprintTracker exists to give a single, persistent, queryable record of every logged performance — structured from the first entry, ready to be visualized at any point.

---

## Tech Stack

| Technology | Role |
|---|---|
| Java | Application logic and terminal interface |
| SQLite | Lightweight, file-based relational database |
| JDBC (sqlite-jdbc) | Java-to-database connectivity layer |
| DB Browser for SQLite | Schema creation and direct database inspection |
| Tableau Public | Performance data visualization and dashboarding |
| Git / GitHub | Version control and project history |

---

## How It Works

When the program runs, it establishes a connection to the SQLite database via JDBC and enters an interactive loop:

1. The user is prompted: **"Do you want to record a new performance? (Y/N)"**
2. Entering **Y** triggers four sequential prompts — date, event, time (in seconds), and notes
3. Each captured value is bound to a parameterized SQL `INSERT` statement via `PreparedStatement`
4. `executeUpdate()` writes the record to the database and confirms success
5. The loop continues until the user enters **N**

No record is hardcoded. No data is assumed. Every entry is driven by the user at runtime.

---

## Key Technical Decisions

**`PreparedStatement` over `Statement`**
The previous iteration of this program used a hardcoded `Statement` with static values. Once user input was introduced, that approach became a liability. `PreparedStatement` separates the SQL structure from the user's input — the query is compiled first, and values are bound into placeholders after. This closes the door on SQL injection at the application layer.

**Manual JDBC over Maven**
Maven was available in the development environment but intentionally excluded. For a single-developer project at this scope, placing the `sqlite-jdbc` driver manually in a `lib/` folder achieves the same result with zero configuration overhead. Simplicity was the right call here.

**DB Browser for Schema Creation**
The database schema was created directly in DB Browser using `Execute SQL` rather than programmatically through the application. This kept the Java code focused on its actual session logic rather than database administration.

---

## Current Limitations

- SprintTracker currently supports record creation. Read, update, and delete operations are not yet implemented.
- Entering a non-numeric value where a decimal is expected (e.g., `Time_Seconds`) will cause the program to crash. Input is not sanitized at the application layer.
- **Hardcoded database path** — the connection URL points to an absolute local file path. Portability requires updating this value manually.
- There is no graphical interface. All interaction happens through the command line.

---

## Planned Improvements

- [ ] Full CRUD support — query, update, and delete existing entries
- [ ] Input validation — reject malformed data before it reaches the database
- [ ] Automatic CSV export on session close for direct Tableau pipeline
- [ ] GUI implementation — event dropdowns, calendar date picker, embedded performance visual
- [ ] Relative database path for portability across machines

---

## Data Visualization

Performance data is exported to CSV and connected to a Tableau Public dashboard tracking indoor and outdoor 400m progression over time, being split by season with pinpoint performances and yearly averages.

*Dashboard link — coming soon*

---

## Running the Project Locally

**Prerequisites:** Java JDK installed, `sqlite-jdbc` `.jar` placed in a `lib/` folder at the project root.

```bash
# Compile
javac -cp "lib/*" SprintTracker.java -d out

# Run (Mac/Linux)
java -cp "lib/*:out" SprintTracker

# Run (Windows)
java -cp "lib/*;out" SprintTracker
```

The program will prompt for input immediately on launch. A `test.db` file will be created in the project directory if one does not already exist.

---

## Contributions

This project is a personal development tool, but feedback, suggested improvements, and pull requests are welcome. If you work in athletics, sports analytics, or Java backend development and see something worth improving — open an issue.

---

## Author

Built by Marques — student-athlete and developer in progress.
